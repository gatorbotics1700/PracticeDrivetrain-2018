
package org.usfirst.frc.team1700.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1700.robot.commands.AutoCGs.AutoForward;
import org.usfirst.frc.team1700.robot.commands.AutoCGs.CenterSwitchAutoCG;
import org.usfirst.frc.team1700.robot.commands.AutoCGs.LeftScaleAuto;
import org.usfirst.frc.team1700.robot.commands.AutoCGs.LeftSwitchAuto;
import org.usfirst.frc.team1700.robot.commands.AutoCGs.RightScaleAuto;
import org.usfirst.frc.team1700.robot.commands.AutoCGs.RightSwitchAuto;
import org.usfirst.frc.team1700.robot.commands.AutoCGs.CenterScaleAuto;
import org.usfirst.frc.team1700.robot.commands.AutoCGs.CenterSwitchAuto;
import org.usfirst.frc.team1700.robot.commands.AutoCGs.testAutoCG;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveCommand;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveForwardTimeOutCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorStopCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorToTicksCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorUpCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.FoldIntakeCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.ReleaseIntakeCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.RunIntakeCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.StopIntakeCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.GrabIntakeCommand;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem.AngleType;
import org.usfirst.frc.team1700.robot.subsystems.ElevatorSubsystem;
import org.usfirst.frc.team1700.robot.subsystems.IntakeSubsystem;

import com.kauailabs.navx.frc.AHRS;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final DriveSubsystem driveSubsystem = new DriveSubsystem();
	public static OI oi;
	public static final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();
	public static final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		chooser.addDefault("Default Auto (Drive Forward)", new AutoForward());
		chooser.addObject("Left Scale Auto", new LeftScaleAuto());
		chooser.addObject("Center Scale Auto", new CenterScaleAuto());
		chooser.addObject("Right Scale Auto", new RightScaleAuto());
		chooser.addObject("Left Switch Auto", new LeftSwitchAuto());
		chooser.addObject("Center Switch Auto", new CenterSwitchAutoCG());
		chooser.addObject("Right Switch Auto",  new RightSwitchAuto());
		chooser.addObject("Auto Line", new AutoForward());
		SmartDashboard.putData("Auto Mode", chooser);
		System.out.println("ROBOT INITIATED!! :)");
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();
		DriverStation.getInstance().reportWarning(autonomousCommand.getName(), false);
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
		System.out.println("AUTO INIT!!");
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
//		DriverStation.getInstance().reportWarning("1", false);
		if (autonomousCommand != null) {

//			DriverStation.getInstance().reportWarning("2", false);
			autonomousCommand.cancel();
//			DriverStation.getInstance().reportWarning("3", false);
		}
//		DriverStation.getInstance().reportWarning("4", false);
		System.out.println("\nTELEOPINIT!!\n");
//		DriverStation.getInstance().reportWarning("TeleopInit!", false);
//		new ElevatorUpCommand(); //ElevatorUp currently used as coJoy speed control
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		OI.foldUp.whenPressed(new FoldIntakeCommand(true));
		OI.foldDown.whenPressed(new FoldIntakeCommand(false));
		OI.releaseIntake.whileHeld(new ReleaseIntakeCommand()); 
		OI.releaseIntake.whenReleased(new RunIntakeCommand());
		OI.grab.whileHeld(new GrabIntakeCommand()); // if limit switches don't work
		OI.stopIntake.whileHeld(new StopIntakeCommand());
		OI.stopIntake.whenReleased(new RunIntakeCommand());
		OI.elevatorSwitch.whenPressed(new ElevatorToTicksCommand(Robot.elevatorSubsystem.switchTicks));
		OI.elevatorScale.whenPressed(new ElevatorToTicksCommand(Robot.elevatorSubsystem.scaleTicks));
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}


package org.usfirst.frc.team1700.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;
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

	double leftIntakeSpeed = 0;
	double rightIntakeSpeed = 0;
	Boolean desiredGrabIntakeState = false;
	Boolean desiredFoldIntakeState = true;
	double leftSpeed = 0;
	double rightSpeed = 0;
	double elevatorSpeed = 0;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
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
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
	}

	@Override
	public void teleopInit() {
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {

		// UPDATE ROBOT STATE
		leftSpeed = OI.leftJoy.getRawAxis(1);
		rightSpeed = OI.rightJoy.getRawAxis(1);
		elevatorSpeed = -OI.coJoy.getRawAxis(1);

		if (OI.releaseIntakeFast.get()){
			leftIntakeSpeed = -1;
			rightIntakeSpeed = -1;
			desiredGrabIntakeState = RobotMap.GRAB_INTAKE_OPEN;
		}
		else if (OI.releaseIntakeSlow.get()){
			leftIntakeSpeed = -0.4;
			rightIntakeSpeed = -0.4;
			desiredGrabIntakeState = RobotMap.GRAB_INTAKE_OPEN;
		}
		else if (OI.stopIntake.get()){
			leftIntakeSpeed = 0;
			rightIntakeSpeed = 0;
			//no update to desiredGrabIntakeState
		}
		else {
			leftIntakeSpeed = 0.5;
			rightIntakeSpeed = 0.3;
			if (OI.squeeze.get()){
				desiredGrabIntakeState = RobotMap.GRAB_INTAKE_CLOSE;
			}
			else if (OI.letGo.get()){
				desiredGrabIntakeState = RobotMap.GRAB_INTAKE_OPEN;
			}
			else{
				//no update to desiredGrabIntakeState
			}
		}

		if (OI.foldUp.get()){
			desiredFoldIntakeState = true;
		}
		else if (OI.foldDown.get()){
			desiredFoldIntakeState = false;
		}
		else {
			// no update to desiredFoldIntakeState
		}

		// EXECUTE
		driveSubsystem.driveTank(leftSpeed, rightSpeed);
		elevatorSubsystem.elevatorMove(elevatorSpeed);
		intakeSubsystem.fold(desiredFoldIntakeState);
		intakeSubsystem.grab(desiredGrabIntakeState);
		intakeSubsystem.runIntake(leftIntakeSpeed, rightIntakeSpeed);
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}

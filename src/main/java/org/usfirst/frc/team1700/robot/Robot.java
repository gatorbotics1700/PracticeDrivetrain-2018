
package org.usfirst.frc.team1700.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

import org.usfirst.frc.team1700.robot.autonmodes.AutonomousBase;

import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team1700.robot.subsystems.ElevatorSubsystem;
import org.usfirst.frc.team1700.robot.subsystems.IntakeSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;

	public static final DriveSubsystem driveSubsystem = new DriveSubsystem();
	public static final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();
	public static final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();

	public static AutonomousBase auto;

	// Control variables
	public static double leftIntakeSpeed = 0;
	public static double rightIntakeSpeed = 0;
	public static Boolean desiredGrabIntakeState = RobotMap.GRAB_INTAKE_CLOSE;
	public static Boolean desiredFoldIntakeState = true;
	public static double leftSpeed = 0;
	public static double rightSpeed = 0;
	public static double elevatorSpeed = 0;

	// State variables
	Boolean hasGameData = false;

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

	@Override
	public void autonomousInit() {
		// Choose auton mode based on starting location
		Boolean preferSwitch = true;

		auto = new AutonomousBase(AutonomousBase.StartLocation.LEFT, preferSwitch, driveSubsystem, intakeSubsystem, elevatorSubsystem);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		auto.periodic();

		// EXECUTE (Autonomous responsible for setting all of these values)
		driveSubsystem.driveTank(leftSpeed, rightSpeed);
		elevatorSubsystem.elevatorMove(elevatorSpeed);
		intakeSubsystem.fold(desiredFoldIntakeState);
		intakeSubsystem.grab(desiredGrabIntakeState);
		intakeSubsystem.runIntake(leftIntakeSpeed, rightIntakeSpeed);
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
	}
}

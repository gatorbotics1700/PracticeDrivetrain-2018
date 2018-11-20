
package org.usfirst.frc.team1700.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;

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

	// Control variables

	public static double leftSpeed = 0;
	public static double rightSpeed = 0;

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

		// EXECUTE
		driveSubsystem.driveTank(leftSpeed, rightSpeed);
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

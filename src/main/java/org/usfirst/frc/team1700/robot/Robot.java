package org.usfirst.frc.team1700.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team1700.robot.motionprofiling.TestProfilesAuto;
import org.usfirst.frc.team1700.robot.motionprofiling.TestProfilesAuto.AutoType;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {

/**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */

	@Override
  public void robotPeriodic() {
  }


	public static OI oi;

	public static final DriveSubsystem driveSubsystem = new DriveSubsystem();

	// Control variables
	public static double leftSpeed = 0;
	public static double rightSpeed = 0;
  	private TestProfilesAuto auto = new TestProfilesAuto(AutoType.DRIVE_FORWARD);


	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		System.out.println("ROBOT INITIATED TEST!! :)");
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

	public void autoInit(){
		auto.init();
	}

	public void autoPeriodic(){
		auto.periodic();
	}

	//Everything in teleopInit starts when teleop mode is turned on (so we don't want anything in it).
	@Override 
	public void teleopInit() {
		// set robot speed to 0 in init so that it doesn't start moving as soon as we turn on teleop
		driveSubsystem.driveTank(0, 0);
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

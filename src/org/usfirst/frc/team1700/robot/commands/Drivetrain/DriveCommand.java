package org.usfirst.frc.team1700.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1700.robot.OI;
import org.usfirst.frc.team1700.robot.Robot;

/**
 *
 */
public class DriveCommand extends Command {
	public DriveCommand() {
		requires(Robot.driveSubsystem);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		DriverStation.getInstance().reportWarning("Starting drive command.", false);
		System.out.println("Starting drive command.");
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double leftSpeed = OI.leftJoy.getRawAxis(1);
		double rightSpeed = OI.rightJoy.getRawAxis(1);
		String LencVal = Integer.toString(Robot.driveSubsystem.getLeftEncoderValue());
		String RencVal = Integer.toString(Robot.driveSubsystem.getRightEncoderValue());
		DriverStation.getInstance().reportWarning("Left Encoder:" + LencVal, false);
		DriverStation.getInstance().reportWarning("Right Encoder:" + RencVal, false);
		Robot.driveSubsystem.driveTank(leftSpeed, rightSpeed);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		DriverStation.getInstance().reportWarning("DriveCommand interrupted!", false);
		System.out.println("DriveCommand interrupted!");
	}
}

package org.usfirst.frc.team1700.robot.commands.Drivetrain;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.subsystems.IntakeSubsystem.IntakeState;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveUntilOverCommand extends Command {

	double driveSpeed = 0.3;
	double maxDistance = 10;
	
    public DriveUntilOverCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    	requires(Robot.intakeSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!Robot.intakeSubsystem.leftUltrasonicClose() && !Robot.intakeSubsystem.rightUltrasonicClose()) {
    		Robot.driveSubsystem.driveTank(driveSpeed, driveSpeed);
    	} else if (!Robot.intakeSubsystem.rightUltrasonicClose()) {
    		Robot.driveSubsystem.driveTank(0, driveSpeed);
    	} else if (!Robot.intakeSubsystem.leftUltrasonicClose()) {
    		Robot.driveSubsystem.driveTank(driveSpeed, 0);
    	} else {
    		Robot.intakeSubsystem.setState(IntakeState.OVER);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.intakeSubsystem.intakeState == IntakeState.OVER ||
        	   Robot.driveSubsystem.getLeftEncoderValue() > maxDistance ||
        	   Robot.driveSubsystem.getRightEncoderValue() > maxDistance;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.driveTank(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

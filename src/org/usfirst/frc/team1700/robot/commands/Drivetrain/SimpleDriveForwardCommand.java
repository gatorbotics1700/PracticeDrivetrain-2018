package org.usfirst.frc.team1700.robot.commands.Drivetrain;

import org.usfirst.frc.team1700.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SimpleDriveForwardCommand extends DriveAutoCommand {
	double dist;
    public SimpleDriveForwardCommand(double dist) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    	this.dist = dist;
    }


    // Called just before this Command runs the first time
    protected void initialize() {
    	super.initialize(dist, 0.0, false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

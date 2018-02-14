package org.usfirst.frc.team1700.robot.commands.Intake;

import org.usfirst.frc.team1700.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DigitalOutputCommand extends Command {
	
	int count = 0;

    public DigitalOutputCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intakeSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intakeSubsystem.setDigitalOutput(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	count += 1;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return count > 10;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intakeSubsystem.setDigitalOutput(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

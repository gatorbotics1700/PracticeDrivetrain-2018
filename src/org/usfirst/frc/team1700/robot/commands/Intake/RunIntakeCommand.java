package org.usfirst.frc.team1700.robot.commands.Intake;

import org.usfirst.frc.team1700.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunIntakeCommand extends Command {

    public RunIntakeCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intakeSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() { //at the beginning of intake, the claws should be open so they can receive the cube
    
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
 
    	// turn wheels in intake direction
    	Robot.intakeSubsystem.runIntake(1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.intakeSubsystem.hasCube();
    }

    // Called once after isFinished returns true
    protected void end() {
    	// stop wheels
    	Robot.intakeSubsystem.runIntake(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

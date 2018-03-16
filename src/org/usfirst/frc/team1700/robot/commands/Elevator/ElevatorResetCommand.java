package org.usfirst.frc.team1700.robot.commands.Elevator;

import org.usfirst.frc.team1700.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorResetCommand extends Command {

    public ElevatorResetCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevatorSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	Robot.elevatorSubsystem.elevatorMove(-0.3);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//        return Robot.elevatorSubsystem.touchingSwitch(false);
    	return Robot.elevatorSubsystem.getVelocity() == 0;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevatorSubsystem.resetEncoder();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

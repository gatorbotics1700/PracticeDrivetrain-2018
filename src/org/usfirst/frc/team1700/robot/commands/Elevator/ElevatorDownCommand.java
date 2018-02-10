package org.usfirst.frc.team1700.robot.commands.Elevator;

import org.usfirst.frc.team1700.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorDownCommand extends Command {

    public ElevatorDownCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    		requires(Robot.elevatorSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevatorSubsystem.disablePID();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		Robot.elevatorSubsystem.elevatorMove(-0.1);
		//Caution- elevator will down up forever
		//1 = up, 0 = stopped, -1 = down
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.elevatorSubsystem.touchingSwitch(false);
    }

    // Called once after isFinished returns true
    protected void end() {
    		Robot.elevatorSubsystem.elevatorMove(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

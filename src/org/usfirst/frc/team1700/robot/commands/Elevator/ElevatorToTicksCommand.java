package org.usfirst.frc.team1700.robot.commands.Elevator;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorToTicksCommand extends Command {

	int deadband = 5; //change later
	int ticks;
	
    public ElevatorToTicksCommand(int ticks) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.ticks = ticks;
    	requires(Robot.elevatorSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevatorSubsystem.driveToEncoderTicks(ticks);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//done when it's within the right encoder value or it's reached the very top
        return Math.abs(Robot.elevatorSubsystem.getEncoderValue() - ticks) <= deadband ||
        		Robot.elevatorSubsystem.touchingSwitch(true);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

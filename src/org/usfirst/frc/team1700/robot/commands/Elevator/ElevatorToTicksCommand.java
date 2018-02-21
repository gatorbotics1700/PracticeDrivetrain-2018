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
    	System.out.println("ELEVATORTOTICKS INIT!!");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	// Stop if elevator hits top or bottom limit switch
    	return Robot.elevatorSubsystem.touchingSwitch(false) || Robot.elevatorSubsystem.touchingSwitch(true);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevatorSubsystem.elevatorMove(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

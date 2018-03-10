package org.usfirst.frc.team1700.robot.commands.Elevator;

import org.usfirst.frc.team1700.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorForTimeCommand extends Command {

	int count;
	double time;
	double speed;
    public ElevatorForTimeCommand(double time, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.time = time;
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevatorSubsystem.elevatorMove(speed);
    	count = 0;
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevatorSubsystem.elevatorMove(speed);
    	count += 1;
//    	DriverStation.getInstance().reportWarning(Integer.toString(count), false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return count > 50*time;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevatorSubsystem.elevatorMove(0);
    	count = 0;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	count = 0;
    }
}

package org.usfirst.frc.team1700.robot.commands.Elevator;

import org.usfirst.frc.team1700.robot.OI;
import org.usfirst.frc.team1700.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorMoveCommand extends Command {

    public ElevatorMoveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevatorSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("INITIALIZING AN ELEVATOR MOVING COMMAND.");
    	DriverStation.getInstance().reportWarning("elevator up init", false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		Robot.elevatorSubsystem.elevatorMove(-OI.coJoy.getRawAxis(1));
//    	if (Robot.elevatorSubsystem.touchingSwitch(true) || Robot.elevatorSubsystem.touchingSwitch(false)) { 
//    		Robot.elevatorSubsystem.elevatorMove(0);
//    		DriverStation.getInstance().reportWarning("Elevator hit top or bottom!", false);
//    	} else {
//    	double currTicks = Robot.elevatorSubsystem.getCurrentPos();
//    	DriverStation.reportWarning("Elevator ticks: " + currTicks, false);
//    	if(OI.coJoy.getRawAxis(1) > 0) {
//    		if(currTicks < (Robot.elevatorSubsystem.switchHeight * Robot.elevatorSubsystem.inchesToTicks - 60)) {
//        		Robot.elevatorSubsystem.elevatorMove(-OI.coJoy.getRawAxis(1));
//        	}
//        	else if (currTicks < Robot.elevatorSubsystem.switchHeight * Robot.elevatorSubsystem.inchesToTicks){
//        		Robot.elevatorSubsystem.elevatorMove(-0.5*OI.coJoy.getRawAxis(1));
//        	}
//        	else {
//        		Robot.elevatorSubsystem.elevatorMove(0);
//        	}
//    	}
//    	else {
//    		Robot.elevatorSubsystem.elevatorMove(-OI.coJoy.getRawAxis(1));
//    	}
//    	
//    	DriverStation.getInstance().reportWarning(Integer.toString(Robot.elevatorSubsystem.getCurrentPos()), false);
//    	DriverStation.getInstance().reportWarning(Double.toString(-OI.coJoy.getRawAxis(1)), false);
		//1 = up, 0 = stopped, -1 = down
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
    	Robot.elevatorSubsystem.elevatorMove(0);
    	System.out.println("ElevatorUpCommand interrupted!");
    }
}

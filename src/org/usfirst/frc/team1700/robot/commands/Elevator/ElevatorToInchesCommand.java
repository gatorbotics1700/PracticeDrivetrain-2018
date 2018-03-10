package org.usfirst.frc.team1700.robot.commands.Elevator;

import org.usfirst.frc.team1700.robot.OI;
import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorToInchesCommand extends Command {

	int deadband = 5; //change later
	int ticks;
	double maxSpeed = 0.5;
	double P = 0.009; // not tuned
	double D = 0.000; // not tuned
	double tickDiff;
	double calcVel;
	
    public ElevatorToInchesCommand(double inches) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.ticks = (int) Math.round(inches * Robot.elevatorSubsystem.inchesToTicks);
    	requires(Robot.elevatorSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevatorSubsystem.resetEncoder();
    	System.out.println("ELEVATORTOTICKS INIT!!");
    	String printEnc = Double.toString(Robot.elevatorSubsystem.getCurrentPos()),
    			printGoal = Double.toString(ticks);
    	DriverStation.reportWarning("Elevator init. enc: " + printEnc, false);
    	//DriverStation.reportWarning("Elevator init. ticks: " + printGoal, false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	tickDiff = ticks - Robot.elevatorSubsystem.getCurrentPos();
    	calcVel = -P*tickDiff + D*Robot.elevatorSubsystem.getVelocity();
    	String printEnc = Double.toString(Robot.elevatorSubsystem.getCurrentPos()),
    			printTickDiff = Double.toString(tickDiff),
    			printCalcVel = Double.toString(calcVel);
    	DriverStation.reportWarning("Elevator Encoder Value: " + printEnc, false);
    	DriverStation.reportWarning("tickdiff: " + printTickDiff, false);
    	DriverStation.reportWarning("calcvel: " + printCalcVel, false);
    	if (Math.abs(tickDiff) <=7.0) {
    		Robot.elevatorSubsystem.elevatorMove(Robot.elevatorSubsystem.stallSpeed);
    		
    	} else if (calcVel > maxSpeed) {
    		Robot.elevatorSubsystem.elevatorMove(maxSpeed);
    	} else if (calcVel < -maxSpeed) {
    		Robot.elevatorSubsystem.elevatorMove(-maxSpeed);
    	} else {
    		Robot.elevatorSubsystem.elevatorMove(calcVel);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//    	return Math.abs(OI.coJoy.getRawAxis(1)) > 1;
    	return false;
//    	return Robot.elevatorSubsystem.touchingSwitch(false) || Robot.elevatorSubsystem.touchingSwitch(true) || Math.abs(OI.coJoy.getRawAxis(1)) > 0.1;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevatorSubsystem.elevatorMove(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.elevatorSubsystem.elevatorMove(0.0);
    }
}

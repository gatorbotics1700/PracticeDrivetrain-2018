package org.usfirst.frc.team1700.robot.commands.Intake;

import java.time.Duration;
import java.time.Instant;

import org.usfirst.frc.team1700.robot.OI;
import org.usfirst.frc.team1700.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunIntakeCommand extends Command {

	Instant start;
	boolean wasJustPressed = false;
    public RunIntakeCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intakeSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	Robot.intakeSubsystem.grab(true);
    	DriverStation.getInstance().reportWarning("Starting a RunIntakeCommand", false);
    	Robot.intakeSubsystem.runIntake(OI.coJoy.getRawAxis(2));
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
//    	Robot.intakeSubsystem.printUltraValues();
    	// turn wheels in intake direction
    	if (Robot.intakeSubsystem.hasCube() == 0) {
    		wasJustPressed = false;
    		Robot.intakeSubsystem.runIntake(0.7);
    		DriverStation.getInstance().reportWarning("Running intake", false);
    	} else {
	    		Robot.intakeSubsystem.runIntake(0);
	    		Robot.intakeSubsystem.grab(false);
    	}
    }
//    	} else if (Robot.intakeSubsystem.hasCube() == 2) {
//    		Robot.intakeSubsystem.runIntake(0);
//    		Robot.intakeSubsystem.grab(false);
//    		DriverStation.getInstance().reportWarning("Running intake at zero", false);
//    	} else {
//    		Robot.intakeSubsystem.runIntake(0.5);
//    	}
//    	DriverStation.getInstance().reportWarning("Intake is running!", false);
  

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intakeSubsystem.runIntake(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intakeSubsystem.runIntake(0);
    }
}

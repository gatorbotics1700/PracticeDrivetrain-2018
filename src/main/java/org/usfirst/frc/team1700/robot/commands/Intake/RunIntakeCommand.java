package org.usfirst.frc.team1700.robot.commands.Intake;

import java.time.Duration;
import java.time.Instant;

import org.usfirst.frc.team1700.robot.OI;
import org.usfirst.frc.team1700.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunIntakeCommand extends Command {

	boolean isActuated = false; 
	Instant start; 
    public RunIntakeCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intakeSubsystem);
    	start = Instant.now();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	Robot.intakeSubsystem.grab(true);
    	DriverStation.getInstance().reportWarning("Starting a RunIntakeCommand", false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	DriverStation.getInstance().reportWarning("beamBreak:" + Robot.intakeSubsystem.beamBreak.get(), false);

    	//initial state (no limit switches, not actuated) -> full speed
    	if (Robot.intakeSubsystem.beamBreak.get()) {
    		Robot.intakeSubsystem.runIntake(0.5,0.3);
    		DriverStation.getInstance().reportWarning("Not running intake", false);
        	Robot.intakeSubsystem.grab(true);
    		// Robot.intakeSubsystem.LEDs(false);
    		// start = Instant.now();
    	} else {
    		Robot.intakeSubsystem.runIntake(0, 0);
        	DriverStation.getInstance().reportWarning("Running intake", false);
        	Robot.intakeSubsystem.grab(false); 
    		// Robot.intakeSubsystem.LEDs(false);
//    		DriverStation.getInstance().reportWarning("NO SWITCHES, IS ACTUATED.", false);
    		// start = Instant.now();
    	}
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intakeSubsystem.runIntake(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intakeSubsystem.runIntake(0, 0);
    }
}

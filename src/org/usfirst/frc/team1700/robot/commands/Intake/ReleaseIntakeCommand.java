package org.usfirst.frc.team1700.robot.commands.Intake;

import org.usfirst.frc.team1700.robot.OI;
import org.usfirst.frc.team1700.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class ReleaseIntakeCommand extends Command {

	double speed;
    public ReleaseIntakeCommand(double speed) {
    	requires(Robot.intakeSubsystem);
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// backdrive intake
    	Robot.intakeSubsystem.runIntake(speed, speed);
    	Robot.intakeSubsystem.grab(true); 
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intakeSubsystem.runIntake(speed, speed);
    	DriverStation.getInstance().reportWarning("releasing intake", false);
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
    	DriverStation.reportWarning("INTERRUPTED!!!", false);
    }
}

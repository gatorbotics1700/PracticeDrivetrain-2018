package org.usfirst.frc.team1700.robot.commands.Intake;

import org.usfirst.frc.team1700.robot.OI;
import org.usfirst.frc.team1700.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GrabIntakeCommand extends Command {

	boolean letGo;
    public GrabIntakeCommand(boolean letGo) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intakeSubsystem);
    	this.letGo = letGo;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intakeSubsystem.grab(letGo);
    	Robot.intakeSubsystem.runIntake(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
    	Robot.intakeSubsystem.runIntake(0);
    }
}

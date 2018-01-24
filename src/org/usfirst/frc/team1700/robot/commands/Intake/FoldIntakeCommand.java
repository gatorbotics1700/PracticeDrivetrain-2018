package org.usfirst.frc.team1700.robot.commands.Intake;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.subsystems.IntakeSubsystem.IntakeState;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FoldIntakeCommand extends Command {

	boolean retracting;
	
    public FoldIntakeCommand(boolean retracting) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intakeSubsystem);
    	this.retracting = retracting;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (retracting && Robot.intakeSubsystem.intakeState == IntakeState.DOWN) {
    		Robot.intakeSubsystem.moveArm(-0.5);
    		Robot.intakeSubsystem.setState(IntakeState.IN_MOTION);
    	} else if (!retracting && Robot.intakeSubsystem.intakeState == IntakeState.RETRACTED){
    		Robot.intakeSubsystem.moveArm(0.5);
    		Robot.intakeSubsystem.setState(IntakeState.IN_MOTION);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.intakeSubsystem.doneMoving();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intakeSubsystem.moveArm(0);
    	if (retracting) {
    		Robot.intakeSubsystem.setState(IntakeState.RETRACTED);
    	} else {
    		Robot.intakeSubsystem.setState(IntakeState.DOWN);
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	if (Robot.intakeSubsystem.intakeState == IntakeState.IN_MOTION) {
    		System.out.println("Arm still moving in code, check whether it's moving in real life!");
    	}
    }
}

package org.usfirst.frc.team1700.robot.commands.Drivetrain;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.subsystems.IntakeSubsystem.IntakeState;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveUntilOverCommand extends Command {

	double driveSpeed = 0.3;
	double maxDistance = 10;
	
    public DriveUntilOverCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    	requires(Robot.intakeSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    //(left speed, right speed) 
    protected void execute() {
    	if (!Robot.intakeSubsystem.leftUltrasonicClose() && !Robot.intakeSubsystem.rightUltrasonicClose()) {
    		//when neither the right nor the left sensors are close, drive forward (both sides going at the same speed)
    		Robot.driveSubsystem.driveTank(driveSpeed, driveSpeed);
    	} else if (!Robot.intakeSubsystem.rightUltrasonicClose()) {
    		//when the right side is not close, but the left side is, 
    		//the left side shouldn't move, but the right side should move 
    		//so that the robot turns to the left
    		Robot.driveSubsystem.driveTank(0, driveSpeed);
    	} else if (!Robot.intakeSubsystem.leftUltrasonicClose()) {
    		//when the left side is not close, but the right side is, 
    		//the right side shouldn't move, but the left side should move 
    		//so that the robot turns to the right
    		Robot.driveSubsystem.driveTank(driveSpeed, 0);
    	} else {
    		//when both sides are close, stop moving because the robot should be in the position to drop a cube
    		Robot.intakeSubsystem.setState(IntakeState.OVER);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//returns intake state as "over", meaning that the robot should now drop the cube
    	//or returns that it has driven for a long time and is still not there 
        return Robot.intakeSubsystem.intakeState == IntakeState.OVER ||
        	   Robot.driveSubsystem.getLeftEncoderValue() > maxDistance ||
        	   Robot.driveSubsystem.getRightEncoderValue() > maxDistance;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//robot stops driving
    	Robot.driveSubsystem.driveTank(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

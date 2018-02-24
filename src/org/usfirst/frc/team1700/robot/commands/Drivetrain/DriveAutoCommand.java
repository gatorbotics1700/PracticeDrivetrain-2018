package org.usfirst.frc.team1700.robot.commands.Drivetrain;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem.AngleType;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public abstract class DriveAutoCommand extends Command {

	protected double distance;
	protected double angle;
	private double currentAngle;
	private double currentDistance;
	private double angleDifference;
	private double distDifference;
	
	// CONSTANTS (change these)
	private double minSpeed = 0.1;
	private double turningAngleProportion = 1.0;
	private double maxAngleSpeed = 0.5;
	private double distanceProportion = 1.0;
	private double maxDistanceSpeed = 0.5;
	private double angleTolerance = 0.5;
	private double distanceTolerance = 1;

    // Called just before this Command runs the first time
    protected void initialize() {
//    	this.requires(Robot.driveSubsystem);
    	currentAngle = Robot.driveSubsystem.getNavXAngle(AngleType.ROLL);
    	currentDistance = (Robot.driveSubsystem.getLeftEncoderValue() + Robot.driveSubsystem.getRightEncoderValue())/2;
    	angle += currentAngle;
    	distance += currentDistance;
    	distDifference = distance - currentDistance;
    	angleDifference = angle - currentAngle;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double angleSpeed = 0;
    	double distanceSpeed = 0;
    	double leftSpeed = 0;
    	double rightSpeed = 0;
    	distDifference = distance - currentDistance;
    	angleDifference = angle - currentAngle;
    	
    	// ANGLE SPEED
    	if (turningAngleProportion*angleDifference > maxAngleSpeed) {
    		angleSpeed = maxAngleSpeed;
    	} else if (turningAngleProportion*angleDifference < -maxAngleSpeed) {
    		angleSpeed = -maxAngleSpeed;
    	} else {
    		angleSpeed = Math.copySign(Math.max(minSpeed, angleDifference*turningAngleProportion), angleDifference);
    	}
    	
    	// DISTANCE SPEED
    	if (distanceProportion*distDifference > maxDistanceSpeed) {
    		distanceSpeed = maxDistanceSpeed;
    	} else if (distanceProportion*distDifference < -maxDistanceSpeed) {
    		distanceSpeed = -maxDistanceSpeed;
    	} else {
    		distanceSpeed = Math.copySign(Math.max(minSpeed, distDifference*distanceProportion), distDifference);
    	}
    	
    	// If the robot is tilting forward or backward, stop!
    	if (Math.abs(Robot.driveSubsystem.getNavXAngle(AngleType.YAW)) <= 30 || Math.abs(Robot.driveSubsystem.getNavXAngle(AngleType.YAW)) <= 30) {
    		leftSpeed = 0;
    		rightSpeed = 0;
    	} else {
	    	leftSpeed = distanceSpeed + angleSpeed;
	    	rightSpeed = distanceSpeed - angleSpeed;
    	}
    	
    	Robot.driveSubsystem.driveTank(leftSpeed, rightSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveSubsystem.nearZero(distDifference, distanceTolerance) &&
        	   Robot.driveSubsystem.nearZero(angleDifference, angleTolerance);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

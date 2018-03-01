package org.usfirst.frc.team1700.robot.commands.Drivetrain;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem.AngleType;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public abstract class DriveAutoCommand extends Command {

	protected double distance; //distance you want
	protected double angle; //angle you want
	private double currentAngle;
	private double currentDistance;
	private double angleDifference;
	private double distDifference;
	private double angleSpeed = 0;
	private double distanceSpeed = 0;
	private double leftSpeed = 0;
	private double rightSpeed = 0;
	
	// CONSTANTS (change these)
	private double minSpeed = 0.1;
	private double turningAngleProportion = 0.1;
	private double maxAngleSpeed = 0.5;
	private double distanceProportion = 0.3;
	private double maxDistanceSpeed = 0.5;
	private double angleTolerance = 0.5;
	private double distanceTolerance = 5;

    // Called just before this Command runs the first time
    protected void initialize() {
//    	this.requires(Robot.driveSubsystem);
    	Robot.driveSubsystem.resetNavX();
    	Robot.driveSubsystem.resetEncoders();
    	currentAngle = Robot.driveSubsystem.getNavXAngle(AngleType.YAW);
    	currentDistance = (Robot.driveSubsystem.getLeftEncoderValue() + Robot.driveSubsystem.getRightEncoderValue())/2;
    	distDifference = distance - currentDistance;
    	angleDifference = angle - currentAngle;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentAngle = Robot.driveSubsystem.getNavXAngle(AngleType.YAW);
    	currentDistance = (Robot.driveSubsystem.getLeftEncoderValue() + Robot.driveSubsystem.getRightEncoderValue())/2;
    	distDifference = distance - currentDistance;
    	angleDifference = angle - currentAngle;
    	
    	// ANGLE SPEED
    	if (turningAngleProportion*angleDifference > maxAngleSpeed) {
    		angleSpeed = maxAngleSpeed;
    	} else if (turningAngleProportion*angleDifference < -maxAngleSpeed) {
    		angleSpeed = -maxAngleSpeed;
    	} else {
    		angleSpeed = Math.copySign(Math.max(minSpeed, Math.abs(angleDifference*turningAngleProportion)), angleDifference);
    	}
    	
    	// DISTANCE SPEED
    	if (distanceProportion*distDifference > maxDistanceSpeed) {
    		distanceSpeed = maxDistanceSpeed;
    	} else if (distanceProportion*distDifference < -maxDistanceSpeed) {
    		distanceSpeed = -maxDistanceSpeed;
    	} else {
    		distanceSpeed = Math.copySign(Math.max(minSpeed, Math.abs(distDifference*distanceProportion)), distDifference);
    	}
    	
    	leftSpeed = -(distanceSpeed + angleSpeed);
    	rightSpeed = -(distanceSpeed - angleSpeed);
    	
    	Robot.driveSubsystem.driveTank(leftSpeed, rightSpeed);
    	String printLS = Double.toString(leftSpeed),
    		   printRS = Double.toString(rightSpeed),
    		   printDDiff = Double.toString(distDifference),
    		   printADiff = Double.toString(angleDifference),
    		   printDesiredDist = Double.toString(distance),
    		   printDesiredAngle = Double.toString(angle),
    		   printLEnc = Integer.toString(Robot.driveSubsystem.getLeftEncoderValue()),
    		   printREnc = Integer.toString(Robot.driveSubsystem.getRightEncoderValue());
    	DriverStation.getInstance().reportWarning("We want this distance: " + printDesiredDist, false);
    	DriverStation.getInstance().reportWarning("We want this angle: " + printDesiredAngle, false);
    	DriverStation.getInstance().reportWarning("DistanceDifference: " + printDDiff, false);
    	DriverStation.getInstance().reportWarning("AngleDifference: " + printADiff, false);
    	DriverStation.getInstance().reportWarning("Current Left Encoder Value: " + printLEnc, false);
    	DriverStation.getInstance().reportWarning("Current Right Encoder Value: " + printREnc, false);
    	DriverStation.getInstance().reportWarning("LeftSpeed: " + printLS, false);
    	DriverStation.getInstance().reportWarning("RightSpeed: " + printRS, false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveSubsystem.nearZero(distDifference, distanceTolerance) &&
        	   Robot.driveSubsystem.nearZero(angleDifference, angleTolerance);
    }

    // Called once after isFinished returns true
    protected void end() {
    	DriverStation.getInstance().reportWarning("Finished DriveAutoCommand!", false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

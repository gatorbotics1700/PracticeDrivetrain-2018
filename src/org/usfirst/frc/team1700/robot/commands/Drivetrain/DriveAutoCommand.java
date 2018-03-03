package org.usfirst.frc.team1700.robot.commands.Drivetrain;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem.AngleType;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public abstract class DriveAutoCommand extends Command {

	protected double distance = 0; //distance you want
	protected double angle = 0; //angle you want
	private double currentAngle = 0;
	private double currentDistance = 0;
	private double angleDifference = 0;
	private double distDifference = 0;
	private double angleSpeed = 0;
	private double distanceSpeed = 0;
	private double leftSpeed = 0;
	private double rightSpeed = 0;
	protected double minAngleSpeed = 0;
	protected double minSpeed = 0;
	private double targetAngle = 0;
	
	// CONSTANTS (change these)
	
	private double turningAngleProportion = 0.05;
	private double maxAngleSpeed = 0.6;
	private double distanceProportion = 0.025;
	private double maxDistanceSpeed = 0.5;
	private double angleTolerance = 2;
	private double distanceTolerance = 5;
	private int count = 0;

    // Called just before this Command runs the first time
    protected void initialize() {
    //	this.requires(Robot.driveSubsystem);
//    	Robot.driveSubsystem.resetNavX();
    	minSpeed = 0;
    	Robot.driveSubsystem.resetEncoders();
    	currentAngle = Robot.driveSubsystem.getNavXAngle(AngleType.YAW) % 360;
		currentDistance = (Robot.driveSubsystem.getLeftEncoderValue() + Robot.driveSubsystem.getRightEncoderValue())/2;
    	distDifference = distance-currentDistance;
    	angleDifference = angle;
    	targetAngle = (currentAngle + angle) % 360;
    	
    	//targetDistance = currentDistance + distDifference;
    	String printcurrDist = Double.toString(currentDistance),
     		   printcurrAngle = Double.toString(currentAngle),
     		   printDDiff = Double.toString(distDifference),
     		   printADiff = Double.toString(angleDifference),
     		   printDesiredDist = Double.toString(distance),
     		   printDesiredAngle = Double.toString(angle),
     		   printLEnc = Integer.toString(Robot.driveSubsystem.getLeftEncoderValue()),
     		   printREnc = Integer.toString(Robot.driveSubsystem.getRightEncoderValue()),
     		   printTargetAngle = Double.toString(targetAngle);
//    	DriverStation.getInstance().reportWarning("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", false);
//    	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//     	//DriverStation.getInstance().reportWarning("We want this distance: " + printDesiredDist, false);
//     	//DriverStation.getInstance().reportWarning("We want this angle: " + printDesiredAngle, false);
//     	//DriverStation.getInstance().reportWarning("DistanceDifference: " + printDDiff, false);
//     	//DriverStation.getInstance().reportWarning("AngleDifference: " + printADiff, false);
//    	//DriverStation.getInstance().reportWarning("Current Left Encoder Value: " + printLEnc, false);
//     	//DriverStation.getInstance().reportWarning("Current Right Encoder Value: " + printREnc, false);
//     	//DriverStation.getInstance().reportWarning("Current distance: " + printcurrDist, false);
//     	DriverStation.getInstance().reportWarning("Current angle: " + printcurrAngle, false);
//     	System.out.println("Current angle: " + printcurrAngle);
//     	DriverStation.getInstance().reportWarning("Target angle: " + printTargetAngle, false);
//     	System.out.println("Target angle: " + printTargetAngle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentAngle = Robot.driveSubsystem.getNavXAngle(AngleType.YAW) % 360;
    	currentDistance = (Robot.driveSubsystem.getLeftEncoderValue() + Robot.driveSubsystem.getRightEncoderValue())/2;
    	distDifference = distance - currentDistance;
    	angleDifference = (targetAngle - currentAngle + 720 + 180) % 360 - 180;
    	
    	// ANGLE SPEED
    	if (turningAngleProportion*angleDifference > maxAngleSpeed) {
    		angleSpeed = maxAngleSpeed;
    	} else if (turningAngleProportion*angleDifference < -maxAngleSpeed) {
    		angleSpeed = -maxAngleSpeed;
    	} else {
    		angleSpeed = Math.copySign(Math.max(minAngleSpeed, Math.abs(angleDifference*turningAngleProportion)), angleDifference);
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
    	//Robot.driveSubsystem.driveTank(0.5, 0.5);
    	String printLS = Double.toString(leftSpeed),
    		   printRS = Double.toString(rightSpeed),
    		   printDDiff = Double.toString(distDifference),
    		   printADiff = Double.toString(angleDifference),
    		   printDesiredDist = Double.toString(distance),
    		   printDesiredAngle = Double.toString(targetAngle),
    		   printLEnc = Integer.toString(Robot.driveSubsystem.getLeftEncoderValue()),
    		   printREnc = Integer.toString(Robot.driveSubsystem.getRightEncoderValue()),
    		   printminSpeed = Double.toString(minSpeed),
    		   printMinAngleSpeed = Double.toString(minAngleSpeed),
    		   printDistSpeed = Double.toString(distanceSpeed),
    		   printCurrAngle = Double.toString(currentAngle),
    		   printAngleSpeed = Double.toString(angleSpeed);
//    	//DriverStation.getInstance().reportWarning("We want this distance: " + printDesiredDist, false);
//    	//DriverStation.getInstance().reportWarning("target angle: " + printDesiredAngle, false);
//    	DriverStation.getInstance().reportWarning("DistanceDifference: " + printDDiff, false);
//    	DriverStation.getInstance().reportWarning("AngleDifference: " + printADiff, false);
//    	System.out.println("AngleDifference: " + printADiff);
//    	//DriverStation.getInstance().reportWarning("current angle (ex.): " + printCurrAngle, false);
//    	System.out.println("current angle (ex.): " + printCurrAngle);
//    	//DriverStation.getInstance().reportWarning("Current Left Encoder Value: " + printLEnc, false);
//    	//DriverStation.getInstance().reportWarning("Current Right Encoder Value: " + printREnc, false);
//    	DriverStation.getInstance().reportWarning("LeftSpeed: " + printLS, false);
//    	DriverStation.getInstance().reportWarning("RightSpeed: " + printRS, false);
//    	//DriverStation.getInstance().reportWarning("minSpeed: " + printminSpeed, false);
//    	//DriverStation.getInstance().reportWarning("min angle speed: " + printMinAngleSpeed, false);
    	//DriverStation.getInstance().reportWarning("distance speed: " + printDistSpeed, false);
    	//DriverStation.getInstance().reportWarning("angle speed: " + printAngleSpeed, false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (Robot.driveSubsystem.nearZero(distDifference, distanceTolerance) &&
        	   Robot.driveSubsystem.nearZero(angleDifference, angleTolerance)) {
        	count++;
        } else {
        	count = 0;
        }
//    	return true;
    	if (count > 5) {
    		count = 0;
    		return true;
    	}
    	return false;
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

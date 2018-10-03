package org.usfirst.frc.team1700.robot.commands.Drivetrain;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem.AngleType;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public abstract class DriveAutoCommand extends Command {

	protected double distance = 0; //distance you want
	protected double angle = 0; //angle you want
	protected boolean isAngle = false; 
	protected boolean isTimedOut = false; 
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
	private double calcAngleSpeed;
	private double calcDistSpeed;
	
	// CONSTANTS (change these)
	
	private double turningAngleProportion = 0.01;
	private double maxAngleSpeed = 0.7;
	private double driveD = -0.005;
	private double driveP = 0.006;
	private double maxDistanceSpeed = 0.8;
	private double angleTolerance = 4;
	private double distanceTolerance = 3*DriveSubsystem.inchesToTicks;
	private int count = 0;
	private Instant time;

    // Called just before this Command runs the first time
    protected void initialize(Double distance, Double angle, boolean isAngle) {
    //	this.requires(Robot.driveSubsystem);
//    	Robot.driveSubsystem.resetNavX();
    	this.distance = distance;
    	this.angle = angle;
    	this.isAngle = isAngle; 
    	if (isAngle) {
    		minAngleSpeed = 0.4;
    		minSpeed = 0;
    	} else {
    		minAngleSpeed = 0;
    		minSpeed = 0.075;
    	}
    	
    	Robot.driveSubsystem.resetEncoders();
    	DriverStation.getInstance().reportWarning("WE RESET THE ENCODERS. They are now " + Integer.toString(Robot.driveSubsystem.getLeftEncoderValue()) + Integer.toString(Robot.driveSubsystem.getRightEncoderValue()), false);
    	currentAngle = Robot.driveSubsystem.getNavXAngle(AngleType.YAW) % 360;
		currentDistance = Robot.driveSubsystem.getRightEncoderValue();
    	distDifference = distance-currentDistance;
    	angleDifference = angle;
    	targetAngle = (currentAngle + angle) % 360;
    	this.time = Instant.now();
    	
    	//targetDistance = currentDistance + distDifference;
//    	String printcurrDist = Double.toString(currentDistance),
//     		   printcurrAngle = Double.toString(currentAngle),
//     		   printDDiff = Double.toString(distDifference),
//     		   printADiff = Double.toString(angleDifference),
//     		   printDesiredDist = Double.toString(distance),
//     		   printDesiredAngle = Double.toString(angle),
//     		   printLEnc = Integer.toString(Robot.driveSubsystem.getLeftEncoderValue()),
//     		   printREnc = Integer.toString(Robot.driveSubsystem.getRightEncoderValue()),
//     		   printTargetAngle = Double.toString(targetAngle);
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
    	if (isTimedOut == true) {
        	Robot.driveSubsystem.driveTank(0.0, 0.0);
        	return;
    	}
    	DriverStation.getInstance().reportWarning("Encoders: " + Integer.toString(Robot.driveSubsystem.getLeftEncoderValue()) + Integer.toString(Robot.driveSubsystem.getRightEncoderValue()), false);

//    	count++;
    	currentAngle = Robot.driveSubsystem.getNavXAngle(AngleType.YAW) % 360;
    	currentDistance = Robot.driveSubsystem.getRightEncoderValue();
    	distDifference = distance - currentDistance;
    	angleDifference = (targetAngle - currentAngle + 720 + 180) % 360 - 180;
    	
    	// ANGLE SPEED
    	Double calcAngleSpeed = turningAngleProportion*angleDifference;
    	if (calcAngleSpeed > maxAngleSpeed) {
    		angleSpeed = maxAngleSpeed;
    	} else if (calcAngleSpeed < -maxAngleSpeed) {
    		angleSpeed = -maxAngleSpeed;
    	} else {
    		angleSpeed = Math.copySign(Math.max(minAngleSpeed, Math.abs(calcAngleSpeed)), angleDifference);
    	}
    	
    	// DISTANCE SPEED
    	Double calcDistSpeed = driveP*distDifference + driveD*Robot.driveSubsystem.getVelocity();
//    	String printP = Double.toString(driveP*distDifference),
//    			printEncVel = Double.toString(Robot.driveSubsystem.getVelocity());
//    	DriverStation.reportWarning("just p: " + printP, false);
//    	DriverStation.reportWarning("encoder velocity: " + printEncVel, false);
    	if (calcDistSpeed > maxDistanceSpeed) {
    		distanceSpeed = maxDistanceSpeed;
    	} else if (calcDistSpeed < -maxDistanceSpeed) {
    		distanceSpeed = -maxDistanceSpeed;
    	} else {
    		distanceSpeed = Math.copySign(Math.max(minSpeed, Math.abs(calcDistSpeed)), distDifference);
    	}
    	
    	leftSpeed = -(distanceSpeed + angleSpeed);
    	rightSpeed = -(distanceSpeed - angleSpeed);
    	
    	Robot.driveSubsystem.driveTank(leftSpeed, rightSpeed);
    	//Robot.driveSubsystem.driveTank(0.5, 0.5);
    	// PRINTING AND DEBUGGING
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
    		   printCalcDistSpeed = Double.toString(calcDistSpeed),
    		   printAngleSpeed = Double.toString(angleSpeed);
   
    	DriverStation.getInstance().reportWarning("We want this distance: " + printDesiredDist, false);
    	DriverStation.getInstance().reportWarning("target angle: " + printDesiredAngle, false);
    	DriverStation.getInstance().reportWarning("DistanceDifference: " + printDDiff, false);
    	DriverStation.getInstance().reportWarning("AngleDifference: " + printADiff, false);
    	DriverStation.getInstance().reportWarning("Current NavX Angle: " + printCurrAngle, false);
    	DriverStation.getInstance().reportWarning("current angle (ex.): " + printCurrAngle, false);
    	DriverStation.getInstance().reportWarning("Current Left Encoder Value: " + printLEnc, false);
    	DriverStation.getInstance().reportWarning("Current Right Encoder Value: " + printREnc, false);
    	DriverStation.getInstance().reportWarning("LeftSpeed: " + printLS, false);
    	DriverStation.getInstance().reportWarning("RightSpeed: " + printRS, false);
    	DriverStation.getInstance().reportWarning("minSpeed: " + printminSpeed, false);
    	DriverStation.getInstance().reportWarning("min angle speed: " + printMinAngleSpeed, false);
    	
    	DriverStation.getInstance().reportWarning("calc distance speed: " + printCalcDistSpeed, false);
    	DriverStation.getInstance().reportWarning("distance speed: " + printDistSpeed, false);
    	DriverStation.getInstance().reportWarning("angle speed: " + printAngleSpeed, false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	Instant now = Instant.now();
    	Duration duration = Duration.between(time, now);
    	if (duration.toMillis()>5000) {
    		if(isAngle == false) {
    			DriverStation.getInstance().reportWarning("DRIVE AUTO HAS TIMED OUT AFTER 4 SECONDS.", false);
    			return true; 
    		}
    		else if(isAngle == true) {
    			isTimedOut = true; 
    			DriverStation.getInstance().reportWarning("Drive auto timed out during a turn", false);
    		}
    	}
        if (isAngle) {
        	if (Robot.driveSubsystem.nearZero(angleDifference, angleTolerance)) {
             	count++;
             } else {
             	count = 0;
             }
        }
        else if (!isAngle) {
        	if (Robot.driveSubsystem.nearZero(distDifference, distanceTolerance) &&
             	   Robot.driveSubsystem.nearZero(angleDifference, angleTolerance)) {
             	count++;
             } else {
             	count = 0;
             }
        }
    	if (count > 5) {
    		count = 0;
    		DriverStation.getInstance().reportWarning("auto command finished", false);
    		return true;
    	}
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.driveTank(0, 0);
    	DriverStation.getInstance().reportWarning("Finished DriveAutoCommand!", false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

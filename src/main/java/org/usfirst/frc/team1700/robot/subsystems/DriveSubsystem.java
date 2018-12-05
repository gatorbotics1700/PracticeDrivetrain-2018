package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DigitalInput;


public class DriveSubsystem{

	AHRS imu = RobotMap.ahrs; 
	
	//Value needs to be changed based on what's on drivetrain
	private static double wheelCircumference = 4 * Math.PI; //in inches 
	private static double numTicks = 4096; //double-check this value!!
	public static final double inchesPerTick = wheelCircumference / numTicks;

	// MOTORS AND SENSORS : UPDATE BASED ON WHAT'S ON THE PRACTICE DRIVETRAIN
	TalonSRX L1 = RobotMap.leftFirstDrive; 
	TalonSRX L2 = RobotMap.leftSecondDrive;
	TalonSRX L3 = RobotMap.leftThirdDrive;
	TalonSRX R1 = RobotMap.rightFirstDrive;
	TalonSRX R2 = RobotMap.rightSecondDrive;
	TalonSRX R3 = RobotMap.rightThirdDrive;

	public DriveSubsystem() {
	}

	//Intakes the speeds of joysticks and updates motor speeds 
	public void driveTank(double leftPercentOutput, double rightPercentOutput) { 
		L1.set(ControlMode.PercentOutput, -leftPercentOutput);
		L2.set(ControlMode.PercentOutput, -leftPercentOutput);
		L3.set(ControlMode.PercentOutput, -leftPercentOutput);
		R1.set(ControlMode.PercentOutput, rightPercentOutput);
		R2.set(ControlMode.PercentOutput, rightPercentOutput);
		R3.set(ControlMode.PercentOutput, rightPercentOutput);
	}

	//Velocity 
	public double getSensorVelocity()
	{
		double x = imu.getVelocityX();
		double y = imu.getVelocityY();
		return Math.sqrt(x*x + y*y);
	}
	
	//Left wheel displacement
	public double getLeftWheelDisplacement()
	{
		double leftWheelDisplacement = L2.getSensorCollection().getQuadraturePosition();
		return leftWheelDisplacement; 
	}

	//Right wheel displacement
	public double getRightWheelDisplacement()
	{
		double rightWheelDisplacement = R2.getSensorCollection().getQuadraturePosition();
		return rightWheelDisplacement;  
	}

	//Scales speed of wheel by distance per pulse
	public Double getVelocity() 
	{
		Double velocity = (L2.getSensorCollection().getQuadratureVelocity()+R2.getSensorCollection().getQuadratureVelocity())/2.0;
		return velocity; //getQuadratureVelocity returns in units per 100ms
	}

	//Position
	public double getSensorDisplacement()
	{
		double x = imu.getDisplacementX();
		double y = imu.getDisplacementY();
		return Math.sqrt(x*x + y*y);
	}

	public boolean isMoving()
	{
		return imu.isMoving(); 
	}

}

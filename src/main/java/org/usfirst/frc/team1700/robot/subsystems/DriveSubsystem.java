package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class DriveSubsystem{

	// AUTO CONSTANTS
	public static final double inchesToTicks = 11.94; // 40 ticks/in. * 54/20*50/12 gearbox reduction / (12pi in/shaft rotation)
	
	// MOTORS AND SENSORS : UPDATE BASED ON WHAT'S ON THE PRACTICE DRIVETRAIN
	VictorSPX LF = RobotMap.leftFrontDrive; 
	VictorSPX RF = RobotMap.rightFrontDrive;
	TalonSRX LB = RobotMap.leftBackDrive;
	TalonSRX RB = RobotMap.rightBackDrive;

	public void driveTank(double leftSpeed, double rightSpeed) {
		LF.set(ControlMode.PercentOutput, -leftSpeed);
		LB.set(ControlMode.PercentOutput, -leftSpeed);
		RF.set(ControlMode.PercentOutput, rightSpeed);
		RB.set(ControlMode.PercentOutput, rightSpeed);
	}
}

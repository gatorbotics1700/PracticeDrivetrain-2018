package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class DriveSubsystem{

	// MOTORS AND SENSORS : UPDATE BASED ON WHAT'S ON THE PRACTICE DRIVETRAIN
	VictorSPX LF = RobotMap.leftFrontDrive; 
	VictorSPX RF = RobotMap.rightFrontDrive;
	TalonSRX LB = RobotMap.leftBackDrive;
	TalonSRX RB = RobotMap.rightBackDrive;

	//Intakes the speeds of joysticks and updates motor speeds 
	public void driveTank(double leftPercentOutput, double rightPercentOutput) { 
		LF.set(ControlMode.PercentOutput, -leftPercentOutput);
		LB.set(ControlMode.PercentOutput, -leftPercentOutput);
		RF.set(ControlMode.PercentOutput, rightPercentOutput);
		RB.set(ControlMode.PercentOutput, rightPercentOutput);
	}
}

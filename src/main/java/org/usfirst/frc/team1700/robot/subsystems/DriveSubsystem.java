package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class DriveSubsystem{

	// MOTORS AND SENSORS : UPDATE BASED ON WHAT'S ON THE PRACTICE DRIVETRAIN
	TalonSRX L1 = RobotMap.leftFirstDrive; 
	TalonSRX L2 = RobotMap.leftSecondDrive;
	TalonSRX L3 = RobotMap.leftThirdDrive;
	TalonSRX R1 = RobotMap.rightFirstDrive;
	TalonSRX R2 = RobotMap.rightSecondDrive;
	TalonSRX R3 = RobotMap.rightThirdDrive;

	//Intakes the speeds of joysticks and updates motor speeds 
	public void driveTank(double leftPercentOutput, double rightPercentOutput) { 
		L1.set(ControlMode.PercentOutput, -leftPercentOutput);
		L2.set(ControlMode.PercentOutput, -leftPercentOutput);
		L3.set(ControlMode.PercentOutput, -leftPercentOutput);
		R1.set(ControlMode.PercentOutput, rightPercentOutput);
		R2.set(ControlMode.PercentOutput, rightPercentOutput);
		R3.set(ControlMode.PercentOutput, rightPercentOutput);
	}
}

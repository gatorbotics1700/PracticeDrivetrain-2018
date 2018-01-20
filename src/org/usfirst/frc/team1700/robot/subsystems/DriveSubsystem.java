package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.commands.DriveCommand;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	TalonSRX LF = RobotMap.leftFrontDrive;
	TalonSRX LB = RobotMap.leftBackDrive;
	TalonSRX RF = RobotMap.rightFrontDrive;
	TalonSRX RB = RobotMap.rightBackDrive;
	Encoder LE = RobotMap.leftDriveEncoder;
	Encoder RE = RobotMap.rightDriveEncoder;

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DriveCommand());
	}
	
	public void driveTank(double leftSpeed, double rightSpeed) {
		LF.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, leftSpeed);
		LB.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, leftSpeed);
		RF.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, rightSpeed);
		RB.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, rightSpeed);
	}
	
	public int getLeftEncoderValue() {
		return LE.get();
	}
	
	public int getRightEncoderValue() {
		return RE.get();
	}
	
}

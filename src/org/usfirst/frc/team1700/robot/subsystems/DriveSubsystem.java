package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	CANTalon LF = RobotMap.leftFrontDrive;
	CANTalon LB = RobotMap.leftBackDrive;
	CANTalon RF = RobotMap.rightFrontDrive;
	CANTalon RB = RobotMap.rightBackDrive;

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	public void driveTank(double leftSpeed, double rightSpeed) {
		LF.set(leftSpeed);
		LB.set(leftSpeed);
		RF.set(rightSpeed);
		RB.set(rightSpeed);
	}
	
}

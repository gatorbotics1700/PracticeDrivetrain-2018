package org.usfirst.frc.team1700.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Drive {
	CANTalon leftFront;
	CANTalon leftBack;
	CANTalon rightFront;
	CANTalon rightBack;
	DoubleSolenoid shifter;
	
	/**
	 * The drive class controls the drivetrain of the robot, including shifting
	 * @param LFPort The left front motor controller ID/port number
	 * @param LBPort The left back motor controller ID/port number
	 * @param RFPort The right front motor controller ID/port number
	 * @param RBPort The right back motor controller ID/port number
	 */
	public Drive(int LFPort, int LBPort, int RFPort, int RBPort) {
		this.leftFront = new CANTalon(LFPort);
		this.leftBack = new CANTalon(LBPort);
		this.rightFront = new CANTalon(RFPort);
		this.rightBack = new CANTalon(RBPort);
	}
	
	/**
	 * This function allows the driver to control the robot by setting the speed of each side of the drivetrain appropriately
	 * @param leftSpeed Raw y axis value from left joystick
	 * @param rightSpeed Raw y axis value from right joystick
	 */
	public void driveTank(double leftSpeed, double rightSpeed) {
		leftFront.set(leftSpeed);
		leftBack.set(leftSpeed);
		rightFront.set(rightSpeed);
		rightBack.set(rightSpeed);
	}
	
	/**
	 * This function controls shifting
	 * @param up Shifts up if true, shifts down if false
	 */
	public void shift(boolean up) {
		if (up) {
			shifter.set(DoubleSolenoid.Value.kForward);
		} else {
			shifter.set(DoubleSolenoid.Value.kReverse);
		}
	}
}

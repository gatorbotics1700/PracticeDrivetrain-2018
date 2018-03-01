package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
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
	public AHRS navx = RobotMap.ahrs;
	public double ticksToInches = 1; //placeholder; change later
	public enum AngleType {
		PITCH, YAW, ROLL;
	}
	
	public DriveSubsystem() {
		RE.setReverseDirection(true);
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new DriveCommand());
	}
	
	public void driveTank(double leftSpeed, double rightSpeed) {
		LF.set(ControlMode.PercentOutput, -leftSpeed);
		LB.set(ControlMode.PercentOutput, -leftSpeed);
		RF.set(ControlMode.PercentOutput, rightSpeed);
		RB.set(ControlMode.PercentOutput, rightSpeed);
	}
	
	public double getNavXAngle(AngleType angleType) {
		if (angleType == AngleType.YAW) {
			return navx.getYaw();
		} else if (angleType == AngleType.PITCH) {
			return navx.getPitch();
		} else if (angleType == AngleType.ROLL) {
			return navx.getRoll();
		} else {
			System.out.println("ERROR: The angle type specified does not exist!");
			return 0;
		}
	}
	
	public void resetNavX() {
		navx.resetDisplacement();
	}
	
	public int getLeftEncoderValue() {
		return LE.get();
	}
	
	public int getRightEncoderValue() {
		return RE.get();
	}
	
	public void resetEncoders() {
		LE.reset();
		RE.reset();
	}
	
	public boolean nearZero(double number, double tolerance) {
		return (Math.abs(number)<tolerance);
	}
	
}

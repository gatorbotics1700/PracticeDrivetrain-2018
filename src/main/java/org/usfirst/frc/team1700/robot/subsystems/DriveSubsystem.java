package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {

	// AUTO CONSTANTS
	public static double inchesToTicks = 11.94; // 40 ticks/in. * 54/20*50/12 gearbox reduction / (12pi in/shaft rotation)
	public double distToSwitch = 140*inchesToTicks;
	public double distToAutoLine = 110*inchesToTicks;
	public double finalDistToSwitch = 30*inchesToTicks; 
	public double finalDistToScale = 23*inchesToTicks; // with bad encoder: 55

	public double crossScaleDist1 = 210*DriveSubsystem.inchesToTicks;
	public double crossScaleDist2 = 190*DriveSubsystem.inchesToTicks;
	public double crossScaleDist3 = 57*DriveSubsystem.inchesToTicks;
	
	public double sameScaleDist = 226*DriveSubsystem.inchesToTicks; // with bad encoder: 280
	
	public long waitTime = 250; 
	
	public double crossSwitchDist1 = 18*DriveSubsystem.inchesToTicks;
	public double crossSwitchDist2 = 127*DriveSubsystem.inchesToTicks;
	public double crossSwitchDist3 = 18*DriveSubsystem.inchesToTicks;
	
	public double centerLeftDist1 = 50*DriveSubsystem.inchesToTicks;
	public double centerLeftDist2 = 80*DriveSubsystem.inchesToTicks;
	public double centerLeftDist3 = 20*DriveSubsystem.inchesToTicks;
	
	public double centerRightDist1 = 50*DriveSubsystem.inchesToTicks;
	public double centerRightDist2 = 70*DriveSubsystem.inchesToTicks;
	public double centerRightDist3 = 25*DriveSubsystem.inchesToTicks;
		
	public double centerDistBack = -18*DriveSubsystem.inchesToTicks;
	
	public double sameSwitchDist = 140*DriveSubsystem.inchesToTicks;
	
	public double left = -90;
	public double right = 90;
	
	// MOTORS AND SENSORS
	VictorSPX LF = RobotMap.leftFrontDrive;
	VictorSPX RF = RobotMap.rightFrontDrive;
//	TalonSRX LF = RobotMap.leftFrontDrive;
//	TalonSRX RF = RobotMap.rightFrontDrive;
	TalonSRX LB = RobotMap.leftBackDrive;
	TalonSRX RB = RobotMap.rightBackDrive;
	Encoder LE = RobotMap.leftDriveEncoder;
	Encoder RE = RobotMap.rightDriveEncoder;
	public AHRS navx = RobotMap.ahrs;
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
		navx.reset();
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
	
	public Double getVelocity() {
		return (LE.getRate()+RE.getRate())/2;
	}
	
	
}

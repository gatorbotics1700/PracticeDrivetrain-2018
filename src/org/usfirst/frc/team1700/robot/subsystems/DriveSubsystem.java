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
	public double distToAutoLine = 72*inchesToTicks; // this is made up

	public double crossScaleDist1 = 245*DriveSubsystem.inchesToTicks;
	public double crossScaleDist2 = 186*DriveSubsystem.inchesToTicks;
	public double crossScaleDist3 = 20*DriveSubsystem.inchesToTicks;
	
	public double centerScaleDist1 = 210*DriveSubsystem.inchesToTicks;
	public double centerScaleDist2 = 42*DriveSubsystem.inchesToTicks;
	public double centerScaleDist3 = 57*DriveSubsystem.inchesToTicks;
	
	public double sameScaleDist1 = 153*DriveSubsystem.inchesToTicks;
	public double sameScaleDist2 = 21*DriveSubsystem.inchesToTicks;
	
	public double crossSwitchDist1 = 50*DriveSubsystem.inchesToTicks;
	public double crossSwitchDist2 = 110*DriveSubsystem.inchesToTicks;
	public double crossSwitchDist3 = 54*DriveSubsystem.inchesToTicks;
	
	public double sameSwitchDist1 = 106*DriveSubsystem.inchesToTicks;
	
	public double left = -90;
	public double right = 90;
	
	// MOTORS AND SENSORS
	VictorSPX LF = RobotMap.leftFrontDrive;
	TalonSRX LB = RobotMap.leftBackDrive;
	VictorSPX RF = RobotMap.rightFrontDrive;
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

package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveCommand;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends PIDSubsystem {

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


	public DriveSubsystem() {
		super("Drive", 2.0,0.0,0.0);// change values later
		setSetpoint(0.0);
		setAbsoluteTolerance(1.0);
		getPIDController().disable();
		// TODO Auto-generated constructor stub
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DriveCommand());	
	}
	
	public void driveTank(double leftSpeed, double rightSpeed) {
		LF.set(RobotMap.PERCENT_OUTPUT, -leftSpeed);
		LB.set(RobotMap.PERCENT_OUTPUT, -leftSpeed);
		RF.set(RobotMap.PERCENT_OUTPUT, rightSpeed);
		RB.set(RobotMap.PERCENT_OUTPUT, rightSpeed);
	}
	
	public double getNavXAngle() {
		return navx.getAngle();
	}
	
	public void resetNavX() {
		navx.reset();
		System.out.println("UPDATE RATE:");
		System.out.println(navx.getActualUpdateRate());
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

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return navx.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		this.driveTank(output, -output);
	}
	
	public void setDesiredAngle(double angle) {
		if (angle != 0.0) {
			getPIDController().enable();
			setSetpoint(angle);
		} else {
			getPIDController().disable();
		}
	}
	
	public boolean atAngle() {
		return onTarget();
	}
	
}

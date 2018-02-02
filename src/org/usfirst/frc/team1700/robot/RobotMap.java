package org.usfirst.frc.team1700.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//DRIVE TALONS
	public static TalonSRX leftFrontDrive = new TalonSRX(1),
					       leftBackDrive = new TalonSRX(2),
					       rightFrontDrive = new TalonSRX(3),
					       rightBackDrive = new TalonSRX(4);
	
	//INTAKE TALONS
//	public static TalonSRX elevatorMotor = new TalonSRX(5),
//						   leftIntakeMotor = new TalonSRX(6),
//						   rightIntakeMotor = new TalonSRX(7),
//						   intakeArmMotor = new TalonSRX(8);
	
	//TALON CONTROL MODES
	public static final ControlMode PERCENT_OUTPUT = com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput,
									POSITION = com.ctre.phoenix.motorcontrol.ControlMode.Position;
	
	//PNEUMATICS
//	public static DoubleSolenoid leftActuator = new DoubleSolenoid(0, 1),
//								 rightActuator = new DoubleSolenoid(2, 3);
	
	//NAVX
	public static AHRS ahrs = new AHRS(SPI.Port.kMXP, (byte) 200); /* Alternatives:  SPI.Port.kMXP, I2C.Port.kMXP or SerialPort.Port.kUSB */
	
	//DIGITAL SENSORS
//	public static DigitalInput elevatorTopLimitSwitch = new DigitalInput(8),
//							   elevatorBottomLimitSwitch = new DigitalInput(9),
//							   intakeBeamBreak = new DigitalInput(7),
//							   intakeArmUpLimitSwitch = new DigitalInput(6),
//							   intakeArmDownLimitSwitch = new DigitalInput(5);
							   
	public static Encoder	   leftDriveEncoder = new Encoder(new DigitalInput(0), new DigitalInput(1)),
							   rightDriveEncoder = new Encoder(new DigitalInput(2), new DigitalInput(3));
//							   elevatorEncoder = new Encoder(4,5);
}

package org.usfirst.frc.team1700.robot;


import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// DRIVE TALONS
	public static TalonSRX leftFrontDrive = new TalonSRX(1),
					        rightFrontDrive = new TalonSRX(3);
					       

	public static TalonSRX leftBackDrive = new TalonSRX(2),
					       rightBackDrive = new TalonSRX(4);
	
	// OTHER TALONS
	public static TalonSRX elevatorMotor = new TalonSRX(5),
						   leftIntakeMotor = new TalonSRX(6),
						   rightIntakeMotor = new TalonSRX(7);
	
	// PNEUMATICS
//	public static DoubleSolenoid foldingActuator = new DoubleSolenoid(0, 1),
//								 grabbingActuator = new DoubleSolenoid(2, 3);
//	
//	public static Compressor compressor = new Compressor(0); 
	
	// NAVX
	public static AHRS ahrs = new AHRS(SPI.Port.kMXP, (byte) 200); /* Alternatives:  SPI.Port.kMXP, I2C.Port.kMXP or SerialPort.Port.kUSB */
	
	// DIGITAL SENSORS
	public static DigitalInput elevatorTopLimitSwitch = new DigitalInput(6),
							   elevatorBottomLimitSwitch = new DigitalInput(7),
							   intakeLeftLimitSwitch = new DigitalInput(8),
							   intakeRightLimitSwitch = new DigitalInput(10);
							   
	public static Encoder	   leftDriveEncoder = new Encoder(new DigitalInput(9), new DigitalInput(1)),
							   rightDriveEncoder = new Encoder(new DigitalInput(2), new DigitalInput(3)),
							   elevatorEncoder = new Encoder(new DigitalInput(4),new DigitalInput (5));
	
	public static DigitalOutput ultrasonicActivator = new DigitalOutput(0);
	
	// ANALOG SENSORS
	public static AnalogInput leftUltrasonic = new AnalogInput(0),
							  rightUltrasonic = new AnalogInput(1);
}

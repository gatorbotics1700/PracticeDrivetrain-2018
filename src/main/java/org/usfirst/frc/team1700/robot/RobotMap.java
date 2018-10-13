package org.usfirst.frc.team1700.robot;


import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;

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
	public static VictorSPX leftFrontDrive = new VictorSPX(3),
					        rightFrontDrive = new VictorSPX(4);
//	public static TalonSRX leftFrontDrive = new TalonSRX(3),
//						   rightFrontDrive = new TalonSRX(4;)

	public static TalonSRX leftBackDrive = new TalonSRX(1),
					       rightBackDrive = new TalonSRX(2); //6
	
	// OTHER TALONS
	public static TalonSRX elevatorMotor = new TalonSRX(7), //7
						   leftIntakeMotor = new TalonSRX(5),
						   rightIntakeMotor = new TalonSRX(6);
	
	// PNEUMATICS
	public static DoubleSolenoid intakeArmSol = new DoubleSolenoid(0, 1),
								 carriageSol = new DoubleSolenoid(2, 3);
	
	public static Compressor compressor = new Compressor(0); 
	
	// NAVX
	public static AHRS ahrs = new AHRS(SPI.Port.kMXP, (byte) 200); /* Alternatives:  SPI.Port.kMXP, I2C.Port.kMXP or SerialPort.Port.kUSB */
	
	// DIGITAL SENSORS
	public static DigitalInput 	beamBreak = new DigitalInput(7),
								elevatorTopLimitSwitch = new DigitalInput(9),
							    elevatorBottomLimitSwitch = new DigitalInput(8); 
							   
	public static Encoder	   leftDriveEncoder = new Encoder(new DigitalInput(1), new DigitalInput(0)),
							   rightDriveEncoder = new Encoder(new DigitalInput(5), new DigitalInput(4)),
							   elevatorEncoder = new Encoder(new DigitalInput(2), new DigitalInput (3));
	
	// DIGITAL OUTPUT
	public static DigitalOutput arduinoTrigger = new DigitalOutput(10);

	// STATE ENUMS
	public static Boolean GRAB_INTAKE_OPEN = true;
	public static Boolean GRAB_INTAKE_CLOSE = false;
	 
	
}

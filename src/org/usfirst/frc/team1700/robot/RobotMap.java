package org.usfirst.frc.team1700.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;

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
	public static TalonSRX elevatorMotor = new TalonSRX(5),
						   leftIntakeMotor = new TalonSRX(6),
						   rightIntakeMotor = new TalonSRX(7),
						   intakeArmMotor = new TalonSRX(8);
	
	//TALON CONTROL MODES
	public static final ControlMode PERCENT_OUTPUT = com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput;
	
	//PNEUMATICS
//	public static DoubleSolenoid leftActuator = new DoubleSolenoid(0, 1),
//								 rightActuator = new DoubleSolenoid(2, 3);
	
	//DIGITAL SENSORS
	public static DigitalInput topLimitSwitch = new DigitalInput(8),
							   bottomLimitSwitch = new DigitalInput(9),
							   intakeBeamBreak = new DigitalInput(7);
							   
	public static Encoder	   leftDriveEncoder = new Encoder(0,1),
							   rightDriveEncoder = new Encoder(2,3),
							   elevatorEncoder = new Encoder(4,5);
					   
}

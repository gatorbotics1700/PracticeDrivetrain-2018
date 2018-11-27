package org.usfirst.frc.team1700.robot;


import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//left side of the drivetrain
	public static TalonSRX leftFirstDrive = new TalonSRX(1); 
	public static TalonSRX leftSecondDrive = new TalonSRX(2); 
	public static TalonSRX leftThirdDrive = new TalonSRX(3);

	//right side of the drivetrain
	public static TalonSRX rightFirstDrive = new TalonSRX(4);
	public static TalonSRX rightSecondDrive = new TalonSRX(5);
	public static TalonSRX rightThirdDrive = new TalonSRX(6);
	
}

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
	// DRIVE TALONS: UPDATE BASED ON WHAT'S ON THE DRIVETRAIN
	public static VictorSPX leftFrontDrive = new VictorSPX(3),
					        rightFrontDrive = new VictorSPX(4);

	public static TalonSRX leftBackDrive = new TalonSRX(1),
					       rightBackDrive = new TalonSRX(2); //6

	
}

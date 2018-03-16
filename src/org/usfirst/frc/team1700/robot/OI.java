package org.usfirst.frc.team1700.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	// JOYSTICKS
	public static Joystick leftJoy = new Joystick(0);
	public static Joystick rightJoy = new Joystick(1);
	public static Joystick coJoy = new Joystick(2);
	
	// BUTTONS
	// Christy
	public static Button stopIntake = new JoystickButton(leftJoy, 3);
	public static Button releaseIntakeSlow = new JoystickButton(rightJoy, 4);
	public static Button releaseIntakeFast = new JoystickButton(rightJoy, 5);
	
	// Lauren
	public static Button letGo = new JoystickButton(coJoy, 1);
	public static Button foldUp = new JoystickButton(coJoy, 2);
	public static Button foldDown = new JoystickButton(coJoy, 3);
	public static Button squeeze = new JoystickButton(coJoy, 4);
	public static Button elevatorReset = new JoystickButton(coJoy, 5);
	public static Button elevatorSwitch = new JoystickButton(coJoy, 6);
	public static Button elevatorScale = new JoystickButton(coJoy, 7);
	public static Button elevatorOverride = new JoystickButton(coJoy, 10);
}

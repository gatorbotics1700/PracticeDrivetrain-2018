package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.commands.Intake.RunIntakeCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {

	TalonSRX leftMotor = RobotMap.leftIntakeMotor;
	TalonSRX rightMotor = RobotMap.rightIntakeMotor;
	DigitalInput leftLS = RobotMap.intakeLeftLimitSwitch;
	DigitalInput rightLS = RobotMap.intakeRightLimitSwitch;
	DoubleSolenoid foldy = RobotMap.foldingActuator;
	DoubleSolenoid grabby = RobotMap.grabbingActuator;
	AnalogInput leftUltra = RobotMap.leftUltrasonic;
	AnalogInput rightUltra = RobotMap.rightUltrasonic;
	DigitalOutput ultrasonicActivator = RobotMap.ultrasonicActivator;
	Compressor compressor = RobotMap.compressor;
	
	public enum IntakeState {
		OVER, NOT_YET;
	}
	public IntakeState intakeState;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public IntakeSubsystem() {
		intakeState = IntakeState.NOT_YET;
		compressor.setClosedLoopControl(false);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }
    
    public void runIntake(double speed) { //motors start running
    	leftMotor.set(ControlMode.PercentOutput, speed); 
    	rightMotor.set(ControlMode.PercentOutput, speed);
    }
    
    public void fold(boolean in) { // pneumatics
    	if (in) { //called in because intake
    		foldy.set(DoubleSolenoid.Value.kForward);
    	} else {
    		foldy.set(DoubleSolenoid.Value.kReverse);
    	}
    }
    
    public void grab(boolean in) { // pneumatics
    	if (in) { //called in because intake
    		grabby.set(DoubleSolenoid.Value.kForward);
    	} else {
    		grabby.set(DoubleSolenoid.Value.kReverse);
    	}
    }
    
    public boolean leftUltrasonicClose() {
    	// return leftUltrasonic.get() < some amount of distance
    	return leftUltra.getValue()<150;
    }
    
    public boolean rightUltrasonicClose() {
    	// return rightUltrasonic.get() < same amount of distance
    	return rightUltra.getValue()<150;
    }
    
    public void printUltraValues() {
    	System.out.println("LEFT ULTRA:");
    	System.out.println(leftUltra.getValue());
    	System.out.println("RIGHT ULTRA:");
    	System.out.println(rightUltra.getValue());
    }
    
    public boolean hasCube() {
    	return leftLS.get() && rightLS.get();
    }
    
    public void setState(IntakeState state) {
    	this.intakeState = state;
    }
    
    public void setDigitalOutput(boolean state) {
    	ultrasonicActivator.set(true);
    }
}


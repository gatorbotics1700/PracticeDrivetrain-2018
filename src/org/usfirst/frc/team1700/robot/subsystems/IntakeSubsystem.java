package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.commands.Intake.RunIntakeCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
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
	DoubleSolenoid LA = RobotMap.leftActuator;
	DoubleSolenoid RA = RobotMap.rightActuator;
	AnalogInput leftUltra = RobotMap.leftUltrasonic;
	AnalogInput rightUltra = RobotMap.rightUltrasonic;
	
	public enum IntakeState {
		OVER, NOT_YET;
	}
	public IntakeState intakeState;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public IntakeSubsystem() {
		intakeState = IntakeState.NOT_YET;
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new RunIntakeCommand());
    }
    
    public void runIntake(double speed) { //motors start running
    	leftMotor.set(ControlMode.PercentOutput, speed); 
    	rightMotor.set(ControlMode.PercentOutput, speed);
    }
    
    public void actuate(boolean in) { // pneumatics
    	if (in) { //called in because intake
    		LA.set(DoubleSolenoid.Value.kForward);
    		RA.set(DoubleSolenoid.Value.kForward);
    	} else {
    		LA.set(DoubleSolenoid.Value.kReverse);
    		RA.set(DoubleSolenoid.Value.kReverse);
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
    
    public boolean hasCube() {
    	return leftLS.get() && rightLS.get();
    }
    
    public void setState(IntakeState state) {
    	this.intakeState = state;
    }
}


package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.commands.RunIntakeCommand;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {
	
	TalonSRX leftMotor = RobotMap.leftIntakeMotor;
	TalonSRX rightMotor = RobotMap.rightIntakeMotor;
	TalonSRX armMotor = RobotMap.intakeArmMotor;
//	DoubleSolenoid LA = RobotMap.leftActuator;
//	DoubleSolenoid RA = RobotMap.rightActuator;
	DigitalInput beamBreak = RobotMap.intakeBeamBreak;
	public enum State {
		RETRACTED, IN_MOTION, DOWN;
	}
	public State state = State.RETRACTED;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new RunIntakeCommand());
    }
    
    public void runIntake(double speed) { //motors start running
    	leftMotor.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, speed); 
    	rightMotor.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, speed);
    }
    
//    public void actuate(boolean in) { //pneumatics
//    	if (in) { //called in because intake
//    		LA.set(DoubleSolenoid.Value.kForward);
//    		RA.set(DoubleSolenoid.Value.kForward);
//    	} else {
//    		LA.set(DoubleSolenoid.Value.kReverse);
//    		RA.set(DoubleSolenoid.Value.kReverse);
//    	}
//    }
    
    public void moveArm(double speed) {
    	armMotor.set(RobotMap.PERCENT_OUTPUT, speed);
    }
    
    public boolean hasCube() {
    	return beamBreak.get();
    }
    
    public void setState(State state) {
    	this.state = state;
    }
    
    public boolean stalled() {
    	return (armMotor.getOutputCurrent() > 100.0);
    }
}


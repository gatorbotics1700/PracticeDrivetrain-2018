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
	DigitalInput armLS = RobotMap.intakeArmLimitSwitch;
	DoubleSolenoid fold = RobotMap.carriageSol;
	DoubleSolenoid grab = RobotMap.intakeArmSol;
	Compressor compressor = RobotMap.compressor;
	
	public enum IntakeState {
		OVER, NOT_YET;
	}
	public IntakeState intakeState;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public IntakeSubsystem() {
		intakeState = IntakeState.NOT_YET;
		compressor.setClosedLoopControl(true);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new RunIntakeCommand());
    }
    
    public void runIntake(double speed) { //motors start running
    	leftMotor.set(ControlMode.PercentOutput, speed); 
    	rightMotor.set(ControlMode.PercentOutput, speed*.5);
    }
    
   public void fold(boolean up) { // pneumatics
	   if (up) { //called in because intake
    		fold.set(DoubleSolenoid.Value.kForward);
    	} else {
    		fold.set(DoubleSolenoid.Value.kReverse);
    	}
    }
//   
//    
    public void grab(boolean release) { // pneumatics
    	if (release) { //called in because intake
    		grab.set(DoubleSolenoid.Value.kForward);
    	} else {
    		grab.set(DoubleSolenoid.Value.kReverse);
    	}
    }
    
//    public boolean leftUltrasonicClose() {
//    	// return leftUltrasonic.get() < some amount of distance
//    	return leftUltra.getValue()<150;
//    }
//    
//    public boolean rightUltrasonicClose() {
//    	// return rightUltrasonic.get() < same amount of distance
//    	return rightUltra.getValue()<150;
//    }
    
    public boolean hasCube() {
//    	return leftLS.get() || rightLS.get();
    	return false;
    }
    
    public void setState(IntakeState state) {
    	this.intakeState = state;
    }
    
    public boolean armHit() {
//    	return armLS.get();
    	return false;
    }
    
}


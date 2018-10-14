package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

/**
 *
 */
public class ElevatorSubsystem {

    // Put methods for controlling this subsystem
	TalonSRX EM = RobotMap.elevatorMotor;
	DigitalInput UL = RobotMap.elevatorTopLimitSwitch;
	DigitalInput BL = RobotMap.elevatorBottomLimitSwitch;
	Encoder enc = RobotMap.elevatorEncoder;
	
	public double scaleHeight = 80; // # ticks to reach scale level
	public double scaleAfterSwitchHeight = 40; 
	public double switchHeight = 26;
	public double slightly = 12;
	public double baseHeight = 9.75;
	public double inchesToTicks = 15.5;
	public double ticksToInches = 1/15.5;
	public double stallSpeed = 0.9;

	public ElevatorSubsystem() {
		enc.reset();
		enc.setDistancePerPulse(0.0645);
	}
    
    public void elevatorMove(double speed) {
		EM.set(ControlMode.PercentOutput, speed);
    }
    
    public boolean touchingSwitch(boolean top) {
//		if (BL.get()) {
//			enc.reset();
//		}
//		return (UL.get() && top) || (BL.get() && !top);
    	return false;
    }
    
	public double getCurrentPos() {
		return enc.get()*ticksToInches;
	}
	
	public void resetEncoder() {
		enc.reset();
	}
	
	public double getVelocity() {
		return enc.getRate()*ticksToInches;
	}
	
}


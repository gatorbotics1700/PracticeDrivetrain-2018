package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ElevatorSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
	TalonSRX EM = RobotMap.elevatorMotor;
	DigitalInput UL = RobotMap.elevatorTopLimitSwitch;
	DigitalInput BL = RobotMap.elevatorBottomLimitSwitch;
	Encoder enc = RobotMap.elevatorEncoder;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void elevatorMove(double speed) {
    		EM.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, speed );
    }
    
    public boolean touchingSwitch() {
    		return UL.get() || BL.get();
    		
    }
    
    public int getEncoderValue() {
    	return enc.get();
    }
}


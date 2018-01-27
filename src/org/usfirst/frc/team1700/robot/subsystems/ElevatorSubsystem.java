package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ElevatorSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
//	TalonSRX EM = RobotMap.elevatorMotor;
//	DigitalInput UL = RobotMap.elevatorTopLimitSwitch;
//	DigitalInput BL = RobotMap.elevatorBottomLimitSwitch;
//	Encoder enc = RobotMap.elevatorEncoder;
	
	//TODO: Test and set these
	public int scaleTicks = 100; // # ticks to reach scale level
	public int switchTicks = 50;

	public ElevatorSubsystem() {
//		EM.selectProfileSlot(0, 0);
//		EM.config_kF(0, 0.2, 10); //slot, value, timeoutMS
//		EM.config_kP(0, 0.2, 10);
//		EM.config_kI(0, 0, 10);
//		EM.config_kD(0, 0, 10);
//		enc.reset();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void elevatorMove(double speed) {
//    		EM.set(RobotMap.PERCENT_OUTPUT, speed);
    }
    
    public boolean touchingSwitch(boolean top) {
//    		if (BL.get()) {
//    			enc.reset();
//    		}
//    		return (UL.get() && top) || (BL.get() && !top);
    	return false;
    }
    
    public int getEncoderValue() {
//    	return enc.get();
    	return 1;
    }
    
    public void driveToEncoderTicks(int ticks) {
//    	EM.set(RobotMap.POSITION, ticks);
    }
}


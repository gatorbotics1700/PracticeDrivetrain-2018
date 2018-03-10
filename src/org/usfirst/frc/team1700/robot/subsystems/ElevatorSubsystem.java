package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorToTicksCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorMoveCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
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
	
	//TODO: Test and set these
	public int scaleTicks = 100; // # ticks to reach scale level
	public int switchTicks = 50;
	public int exchangeTicks = 20;

	public ElevatorSubsystem() {
		enc.reset();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new ElevatorMoveCommand());
    }
    
    public void elevatorMove(double speed) {
		EM.set(ControlMode.PercentOutput, speed);
    }
    
    public boolean touchingSwitch(boolean top) {
		if (BL.get()) {
			enc.reset();
		}
		return (UL.get() && top) || (BL.get() && !top);
    }
    
	public int getCurrentPos() {
		return enc.get();
	}
	
	public void resetEncoder() {
		enc.reset();
	}
	
	public double getVelocity() {
		return enc.getRate();
	}
	
}


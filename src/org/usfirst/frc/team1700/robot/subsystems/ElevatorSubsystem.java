package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorToTicksCommand;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ElevatorSubsystem extends PIDSubsystem {

    // Put methods for controlling this subsystem
	TalonSRX EM = RobotMap.elevatorMotor;
	//DigitalInput UL = RobotMap.elevatorTopLimitSwitch;
	//DigitalInput BL = RobotMap.elevatorBottomLimitSwitch;
	Encoder enc = RobotMap.elevatorEncoder;
	
	//TODO: Test and set these
	public int scaleTicks = 100; // # ticks to reach scale level
	public int switchTicks = 50;

	public ElevatorSubsystem() {
//		EM.selectProfileSlot(0, 0);
//		EM.config_kF(0, 0.2, 10); //slot, value, timeoutMS
//		EM.config_kP(0, 0.2, 10);
//		EM.config_kI(0, 0, 10);
//		EM.config_kD(0, 0, 10);
		super("Elevator", 0.5, 0.0, 0.0);
		System.out.println("ELEVATOR SUBSYSTEM INITIATED");
		setSetpoint(0.0);
		setAbsoluteTolerance(1);
		getPIDController().disable();
		enc.reset();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	System.out.println("SETTING DEFAULT");
        setDefaultCommand(new ElevatorToTicksCommand(enc.get()));
    }
    
    public void elevatorMove(double speed) {
		EM.set(RobotMap.PERCENT_OUTPUT, speed);
    }
    
    public void disablePID() {
    	System.out.println("DISABLING ELEVATOR PID");
    	getPIDController().disable();
    }
    
    public boolean touchingSwitch(boolean top) {
//    		if (BL.get()) {
//    			enc.reset();
//    		}
//    		return (UL.get() && top) || (BL.get() && !top);
    	return false;
    }
    
    @Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return enc.get();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		System.out.println("ELEVATOR PID OUTPUT:");
		System.out.println(output);
		elevatorMove(output);
	}
	
	public boolean atAngle() {
		return onTarget();
	}
	
	public int getCurrentPos() {
		return enc.get();
	}
	
	public void resetEncoder() {
		enc.reset();
	}
	
	public void moveToHeight(int ticks) {
		System.out.println("MOVING TO:");
		setSetpoint(ticks);
		System.out.println(ticks);
		getPIDController().enable();
	}
}


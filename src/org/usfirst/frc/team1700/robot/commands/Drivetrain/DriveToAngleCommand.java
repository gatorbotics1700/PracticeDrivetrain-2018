package org.usfirst.frc.team1700.robot.commands.Drivetrain;

import java.time.Duration;
import java.time.Instant;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Command to allow the robot to turn to a certain angle
 * -180 to 0 turns the robot left; 0 to 180 turns the robot right
 * driveangle turns the robot a certain number of degrees from its current location
 */
public class DriveToAngleCommand extends DriveAutoCommand {
	public double angleL;
	public double angleR;
	
    public DriveToAngleCommand(double angleL, double angleR) {
        this.angleL = angleL;
        this.angleR = angleR;
//        super.minAngleSpeed = 0.25;
//        super.minSpeed = 0;
    }
    
    @Override
    protected void initialize() {
    	Instant start = Instant.now();
    	String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	while (gameData.length() < 3) {
    		gameData = DriverStation.getInstance().getGameSpecificMessage();
    		if (Duration.between(start, Instant.now()).toMillis() > 100) {
    			super.initialize(0.0, 0.0, true);
    			return;
    		}
    	}
    	if(gameData.charAt(0) == 'L') {
			//Put left auto code here
    		super.initialize(0.0, angleL, true);
    	} else if (gameData.charAt(0) == 'R') {
    		super.initialize(0.0, angleR, true);
    	}
    }
}

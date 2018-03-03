package org.usfirst.frc.team1700.robot.commands.Drivetrain;

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
	public Double driveAngle;
	
    public DriveToAngleCommand(double driveAngle) {
        this.driveAngle = driveAngle;
        super.minAngleSpeed = 0.2;
        super.minSpeed = 0;
    }
    
    @Override
    protected void initialize() {
    	String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	if(gameData.charAt(0) == 'L') {
			//Put left auto code here
    		super.angle = -driveAngle;
    		DriverStation.reportWarning("in L: " + gameData, false);
		} else {
			//Put right auto code here
			super.angle = driveAngle;
    		DriverStation.reportWarning("in else: " + gameData, false);
		}
    	super.initialize();
    }
}

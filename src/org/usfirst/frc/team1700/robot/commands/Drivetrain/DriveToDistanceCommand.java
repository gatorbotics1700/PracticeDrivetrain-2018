package org.usfirst.frc.team1700.robot.commands.Drivetrain;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem.AngleType;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Super rough (so far) drive to distance method with encoders
 */
public class DriveToDistanceCommand extends DriveAutoCommand {
	private double distanceL;
	private double distanceR;
	
	public DriveToDistanceCommand(double distanceL, double distanceR) {
        this.distanceL = distanceL;
        this.distanceR = distanceR;
    }
	
	public void initialize() {
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	Integer count = 0;
    	while (gameData.length() < 3) {
    		DriverStation.reportWarning(Integer.toString(count) + gameData, false);
    		gameData = DriverStation.getInstance().getGameSpecificMessage();
    		count++;
    		if (count > 2500000) {
    			DriverStation.reportWarning("timed out :(", false);
    			super.initialize(0.0, 0.0, true);
    			return;
    		}
    	}
    	DriverStation.reportWarning("at drivetodistance logic", false);
    	if(gameData.charAt(0) == 'L') {
			//Put left auto code here
    		super.initialize(distanceL, 0.0, false);
    	} else if (gameData.charAt(0) == 'R') {
    		super.initialize(distanceR, 0.0, false);
    	} else {
		}
		
	}
}
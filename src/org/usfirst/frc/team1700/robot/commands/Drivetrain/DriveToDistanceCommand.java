package org.usfirst.frc.team1700.robot.commands.Drivetrain;

import java.time.Duration;
import java.time.Instant;

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
		Instant start = Instant.now();
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	while (gameData.length() < 3) {
    		gameData = DriverStation.getInstance().getGameSpecificMessage();
    		if (Duration.between(start, Instant.now()).toMillis() > 100) {
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
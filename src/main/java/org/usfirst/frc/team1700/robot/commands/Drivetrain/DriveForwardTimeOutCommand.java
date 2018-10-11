package org.usfirst.frc.team1700.robot.commands.Drivetrain;

import java.time.Duration;
import java.time.Instant;

import org.usfirst.frc.team1700.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForwardTimeOutCommand extends DriveAutoCommand {
	
	// if there is no valid gamedata and the loop times out, this will be set to true
	// if this is true, following commands should not be executed. if it's false, the rest
	// of the auto command should execute as planned
	public boolean executed = false;

    public DriveForwardTimeOutCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
	    String gameData = DriverStation.getInstance().getGameSpecificMessage();
		Instant start = Instant.now();
		while (gameData.length() < 3) {
			gameData = DriverStation.getInstance().getGameSpecificMessage();
			if (Duration.between(start, Instant.now()).toMillis() > 100) {
				super.initialize(Robot.driveSubsystem.distToAutoLine, 0.0, false);
				DriverStation.getInstance().reportWarning("Didn't get game data.", false);
				executed = true;
				return;
			}
		}
		super.initialize(0.0, 0.0, true);
    }
    
    @Override
    protected boolean isFinished() {
    	return super.isFinished();
    }

}

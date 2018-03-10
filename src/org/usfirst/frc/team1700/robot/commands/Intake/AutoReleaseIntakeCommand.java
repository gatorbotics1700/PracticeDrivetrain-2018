package org.usfirst.frc.team1700.robot.commands.Intake;

import org.usfirst.frc.team1700.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoReleaseIntakeCommand extends Command {

	boolean left;
	boolean go = true;
    public AutoReleaseIntakeCommand(boolean left) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.left = left;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	Integer count = 0;
    	while (gameData.length() < 3) {
    		DriverStation.reportWarning(Integer.toString(count) + gameData, false);
    		gameData = DriverStation.getInstance().getGameSpecificMessage();
    		count++;
    		if (count > 2500000) {
    			DriverStation.reportWarning("timed out :(", false);
    			return;
    		}
    	}
    	DriverStation.reportWarning("at drivetodistance logic", false);
    	if(gameData.charAt(0) == 'L') {
			//Put left auto code here
    		go = left;
    	} else if (gameData.charAt(0) == 'R') {
    		go = !left;
    	} else {
		}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (go) {
    		Robot.intakeSubsystem.runIntake(-0.7);
    	} else {
    		this.cancel();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

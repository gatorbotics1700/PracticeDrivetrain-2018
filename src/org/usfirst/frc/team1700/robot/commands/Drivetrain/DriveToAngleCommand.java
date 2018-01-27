package org.usfirst.frc.team1700.robot.commands.Drivetrain;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command to allow the robot to turn to a certain angle
 * -180 to 0 turns the robot left; 0 to 180 turns the robot right
 * driveangle turns the robot a certain number of degrees from its current location
 */
public class DriveToAngleCommand extends Command {
	double driveangle;
	// range chooses how far away from the actual angle is acceptable
	double range = 1;
	
    public DriveToAngleCommand(double driveangle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    	this.driveangle = driveangle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    		Robot.driveSubsystem.resetNavX();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		if (driveangle > 0) {
    			Robot.driveSubsystem.driveTank(-0.5, 0.5);
    		}
    		else {
    			Robot.driveSubsystem.driveTank(0.5, -0.5);
    		}
    	System.out.println(driveangle);
    	double navxAngle = Robot.driveSubsystem.getNavXAngle() %360;
    	System.out.println(navxAngle);
    	System.out.println((driveangle - range <= navxAngle) && (navxAngle <= driveangle + range));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double navxAngle = Robot.driveSubsystem.getNavXAngle()%360;
		return (driveangle - range <= navxAngle) && (navxAngle <= driveangle + range);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

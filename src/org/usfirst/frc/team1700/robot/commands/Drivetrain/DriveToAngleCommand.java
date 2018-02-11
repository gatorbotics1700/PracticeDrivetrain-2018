package org.usfirst.frc.team1700.robot.commands.Drivetrain;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command to allow the robot to turn to a certain angle
 * -180 to 0 turns the robot left; 0 to 180 turns the robot right
 * driveangle turns the robot a certain number of degrees from its current location
 */
public class DriveToAngleCommand extends DriveAutoCommand {
	
    public DriveToAngleCommand(double driveAngle) {
        super.angle = driveAngle;
    }
}

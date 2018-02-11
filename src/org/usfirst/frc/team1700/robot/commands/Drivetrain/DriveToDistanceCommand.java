package org.usfirst.frc.team1700.robot.commands.Drivetrain;

import org.usfirst.frc.team1700.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Super rough (so far) drive to distance method with encoders
 */
public class DriveToDistanceCommand extends DriveAutoCommand {
	
	public DriveToDistanceCommand(double distance) {
        super.distance = distance;
    }
}

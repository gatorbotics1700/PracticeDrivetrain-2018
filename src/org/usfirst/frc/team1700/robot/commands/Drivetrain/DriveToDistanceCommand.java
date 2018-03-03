package org.usfirst.frc.team1700.robot.commands.Drivetrain;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem.AngleType;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Super rough (so far) drive to distance method with encoders
 */
public class DriveToDistanceCommand extends DriveAutoCommand {
	
	public DriveToDistanceCommand(double distance) {
        super.distance = distance;
    }
	
	public void initialize() {
		super.angle = 0;
		super.minAngleSpeed = 0;
		super.minSpeed = 0.1;
		//super.distance = 0;
		super.initialize();
	}
}

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
		super.initialize(distanceL, 0.0, false);
	}
}
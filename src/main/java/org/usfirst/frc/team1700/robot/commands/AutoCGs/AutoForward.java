package org.usfirst.frc.team1700.robot.commands.AutoCGs;

import java.time.Duration;
import java.time.Instant;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToDistanceCommand;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.SimpleDriveForwardCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorResetCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.IntakeInCommand;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoForward extends CommandGroup {

    public AutoForward() {
    	requires(Robot.driveSubsystem);
		
    	Instant start = Instant.now();
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.finalDistToScale, 0)); //distance given in inches

        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.distToAutoLine, Robot.driveSubsystem.distToAutoLine));
    }
}

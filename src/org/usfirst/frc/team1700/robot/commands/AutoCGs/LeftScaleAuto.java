package org.usfirst.frc.team1700.robot.commands.AutoCGs;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveForwardTimeOutCommand;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToAngleCommand;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToDistanceCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorResetCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorToTicksCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.FoldIntakeCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.ReleaseIntakeCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleAuto extends CommandGroup {
    public LeftScaleAuto() {
    	addSequential(new DriveForwardTimeOutCommand());
//		addSequential(new ElevatorResetCommand());
		addParallel(new ElevatorToTicksCommand(Robot.elevatorSubsystem.scaleTicks));
		
    	addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.sameScaleDist1, Robot.driveSubsystem.crossScaleDist1)); //distance given in inches
		addSequential(new DriveToAngleCommand(Robot.driveSubsystem.right, Robot.driveSubsystem.right));
		addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.sameScaleDist2, Robot.driveSubsystem.crossScaleDist2));
		addSequential(new DriveToAngleCommand(0, Robot.driveSubsystem.left));
		
		
		addSequential(new DriveToDistanceCommand(0, Robot.driveSubsystem.crossScaleDist3));
		
		addSequential(new FoldIntakeCommand(true));
		addSequential(new ReleaseIntakeCommand(-1));
    }
}

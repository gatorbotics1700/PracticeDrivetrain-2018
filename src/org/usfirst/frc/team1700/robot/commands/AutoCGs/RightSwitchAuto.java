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
public class RightSwitchAuto extends CommandGroup {

    public RightSwitchAuto() {
    	addSequential(new DriveForwardTimeOutCommand());
		
    	addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.crossSwitchDist1, Robot.driveSubsystem.sameSwitchDist1)); //distance given in inches
		addSequential(new DriveToAngleCommand(Robot.driveSubsystem.left, 0));
		addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.crossSwitchDist2, 0));
		addSequential(new DriveToAngleCommand(Robot.driveSubsystem.right, 0));
		addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.crossSwitchDist3, 0));

		addSequential(new ElevatorResetCommand());
		addParallel(new ElevatorToTicksCommand(Robot.elevatorSubsystem.switchTicks));
		addSequential(new FoldIntakeCommand(true));
		addSequential(new ReleaseIntakeCommand());
    }
}

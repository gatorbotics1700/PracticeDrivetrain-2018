// DO NOT USE THIS

package org.usfirst.frc.team1700.robot.commands.AutoCGs;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveForwardTimeOutCommand;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToAngleCommand;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToDistanceCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorResetCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorToInchesCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.FoldIntakeCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.GrabIntakeCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.ReleaseIntakeCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleAuto extends CommandGroup {
    public LeftScaleAuto() {
//    	addSequential(new DriveForwardTimeOutCommand());
		addSequential(new ElevatorResetCommand());
		addParallel(new ElevatorToInchesCommand(Robot.elevatorSubsystem.scaleHeight));
		addSequential(new FoldIntakeCommand(false));
    	addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.sameScaleDist, Robot.driveSubsystem.crossScaleDist1)); //distance given in inches
		addSequential(new DriveToAngleCommand(0, Robot.driveSubsystem.right));
		addSequential(new DriveToDistanceCommand(0, Robot.driveSubsystem.crossScaleDist2));
		addSequential(new DriveToAngleCommand(0, Robot.driveSubsystem.left));
		addSequential(new DriveToDistanceCommand(0, Robot.driveSubsystem.crossScaleDist3));

		addSequential(new GrabIntakeCommand(true));
		addSequential(new ReleaseIntakeCommand(-1));
    }
}

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
public class RightScaleAuto extends CommandGroup {

    public RightScaleAuto() {
//    	addSequential(new DriveForwardTimeOutCommand());
		addSequential(new ElevatorResetCommand());
		addParallel(new ElevatorToInchesCommand(Robot.elevatorSubsystem.scaleHeight));
		addSequential(new FoldIntakeCommand(false));
		
    	addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.crossScaleDist1, Robot.driveSubsystem.sameScaleDist)); //distance given in inches
		addSequential(new DriveToAngleCommand(Robot.driveSubsystem.left, 0));
		addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.crossScaleDist2, 0));
		addSequential(new DriveToAngleCommand(Robot.driveSubsystem.right, 0));
		addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.crossScaleDist3, 0));

		addSequential(new GrabIntakeCommand(true));
		addSequential(new ReleaseIntakeCommand(-1));
    }
}

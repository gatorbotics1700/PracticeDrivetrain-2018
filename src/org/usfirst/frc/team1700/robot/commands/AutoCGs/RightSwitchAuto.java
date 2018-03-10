package org.usfirst.frc.team1700.robot.commands.AutoCGs;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveForwardTimeOutCommand;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToAngleCommand;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToDistanceCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorResetCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorToTicksCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.AutoReleaseIntakeCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.FoldIntakeCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.ReleaseIntakeCommand;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightSwitchAuto extends CommandGroup {

    public RightSwitchAuto() {
    	requires(Robot.intakeSubsystem);
    	requires(Robot.driveSubsystem);
//    	addSequential(new DriveForwardTimeOutCommand());
//		addSequential(new ElevatorResetCommand());
//		addParallel(new ElevatorToTicksCommand(Robot.elevatorSubsystem.switchTicks));
		
    	addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.distToAutoLine, Robot.driveSubsystem.distToAutoLine)); //distance given in inches
		addSequential(new DriveToAngleCommand(0,Robot.driveSubsystem.left));
//		addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.crossSwitchDist2, 0));
//		addSequential(new DriveToAngleCommand(Robot.driveSubsystem.right, 0));
//		addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.crossSwitchDist3, 0));

		addSequential(new FoldIntakeCommand(true));
		addSequential(new AutoReleaseIntakeCommand(false));
    }
}

package org.usfirst.frc.team1700.robot.commands.AutoCGs;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveForwardTimeOutCommand;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToAngleCommand;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToDistanceCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorResetCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorToTicksCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.FoldIntakeCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.ReleaseIntakeCommand;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterScaleAuto extends CommandGroup {

    public CenterScaleAuto() {
        
		addSequential(new DriveForwardTimeOutCommand());
//		addSequential(new ElevatorResetCommand());
    	addParallel(new ElevatorToTicksCommand(Robot.elevatorSubsystem.scaleTicks));
    	
    	addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.centerScaleDist1, Robot.driveSubsystem.centerScaleDist1)); //distance given in inches
		addSequential(new DriveToAngleCommand(Robot.driveSubsystem.left, Robot.driveSubsystem.right));
		addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.centerScaleDist2, Robot.driveSubsystem.centerScaleDist2));
		addSequential(new DriveToAngleCommand(Robot.driveSubsystem.right, Robot.driveSubsystem.left));
		addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.centerScaleDist3, Robot.driveSubsystem.centerScaleDist3));
		
		addSequential(new FoldIntakeCommand(true));
		addSequential(new ReleaseIntakeCommand(-2));
    }
}

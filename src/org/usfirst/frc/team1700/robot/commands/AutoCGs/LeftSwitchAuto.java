package org.usfirst.frc.team1700.robot.commands.AutoCGs;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveForwardTimeOutCommand;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToAngleCommand;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToDistanceCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorResetCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorToInchesCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.FoldIntakeCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.ReleaseIntakeCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.AutoReleaseIntakeCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftSwitchAuto extends CommandGroup {

    public LeftSwitchAuto() {
//    	addSequential(new DriveForwardTimeOutCommand());
		addSequential(new ElevatorResetCommand());
		addParallel(new ElevatorToInchesCommand(Robot.elevatorSubsystem.switchHeight));
		
    	addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.distToSwitch, Robot.driveSubsystem.distToSwitch)); //distance given in inches
		addSequential(new DriveToAngleCommand(Robot.driveSubsystem.right,0));
		
//		addSequential(new FoldIntakeCommand(true));
//		addSequential(new AutoReleaseIntakeCommand(true));
    }
}

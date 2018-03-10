package org.usfirst.frc.team1700.robot.commands.AutoCGs;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveForwardTimeOutCommand;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToAngleCommand;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToDistanceCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorResetCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorToInchesCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.FoldIntakeCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.ReleaseIntakeCommand;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterSwitchAuto extends CommandGroup {

    public CenterSwitchAuto() {
        
//    	addSequential(new DriveForwardTimeOutCommand());
		addSequential(new ElevatorResetCommand());
    	addParallel(new ElevatorToInchesCommand(Robot.elevatorSubsystem.switchHeight));
		
    	addSequential(new DriveToDistanceCommand(20*DriveSubsystem.inchesToTicks, 20*DriveSubsystem.inchesToTicks)); //distance given in inches
		addSequential(new DriveToAngleCommand(-45, 0));
		addSequential(new DriveToDistanceCommand(85*DriveSubsystem.inchesToTicks, 80*DriveSubsystem.inchesToTicks));
		addSequential(new DriveToAngleCommand(45, 0));
		
//    	addSequential(new FoldIntakeCommand(true));
//		addSequential(new ReleaseIntakeCommand(-0.4));
    }
}

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
public class CenterSwitchAuto extends CommandGroup {

    public CenterSwitchAuto() {
        
//    	addSequential(new DriveForwardTimeOutCommand());
//		addSequential(new ElevatorResetCommand());
//    	addParallel(new ElevatorToTicksCommand(Robot.elevatorSubsystem.switchTicks));
		
    	addSequential(new DriveToDistanceCommand(10*DriveSubsystem.inchesToTicks, 10*DriveSubsystem.inchesToTicks)); //distance given in inches
		addSequential(new DriveToAngleCommand(-45, 45));
		addSequential(new DriveToDistanceCommand(10*DriveSubsystem.inchesToTicks, 70*DriveSubsystem.inchesToTicks));
		addSequential(new DriveToAngleCommand(45, -45));
		
//    	addSequential(new FoldIntakeCommand(true));
//		addSequential(new ReleaseIntakeCommand(-0.4));
    }
}

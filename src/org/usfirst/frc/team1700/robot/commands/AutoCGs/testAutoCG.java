package org.usfirst.frc.team1700.robot.commands.AutoCGs;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToAngleCommand;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToDistanceCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorForTimeCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorToTicksCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.RunIntakeCommand;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class testAutoCG extends CommandGroup {

    public testAutoCG() {
    	this.requires(Robot.driveSubsystem);
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
//    	addSequential(new DriveToDistanceCommand(36*DriveSubsystem.inchesToTicks, 36*DriveSubsystem.inchesToTicks));
    	addSequential(new DriveToDistanceCommand(90,90));
//    	addSequential(new ElevatorToTicksCommand(200));
    }
}

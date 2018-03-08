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
public class FullScaleAuto extends CommandGroup {

    public FullScaleAuto() {
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
    	
    	double crossScaleDist1 = 245*DriveSubsystem.inchesToTicks;
    	double crossScaleDist2 = 186*DriveSubsystem.inchesToTicks;
    	double crossScaleDist3 = 20*DriveSubsystem.inchesToTicks;
    	
    	double centerScaleDist1 = 210*DriveSubsystem.inchesToTicks;
    	double centerScaleDist2 = 42*DriveSubsystem.inchesToTicks;
    	double centerScaleDist3 = 57*DriveSubsystem.inchesToTicks;
    	
    	double sameScaleDist1 = 153*DriveSubsystem.inchesToTicks;
    	double sameScaleDist2 = 21*DriveSubsystem.inchesToTicks;
    	
    	double left = -90;
    	double right = 90;
    	
    	if (Robot.driveSubsystem.getAutoSwitch() == 'L') {
    		addSequential(new DriveForwardTimeOutCommand());
    		
        	addSequential(new DriveToDistanceCommand(sameScaleDist1, crossScaleDist1)); //distance given in inches
    		addSequential(new DriveToAngleCommand(right, right));
    		addSequential(new DriveToDistanceCommand(sameScaleDist2, crossScaleDist2));
    		addSequential(new DriveToAngleCommand(0, left));
    		
    		addSequential(new ElevatorResetCommand());
    		addParallel(new ElevatorToTicksCommand(Robot.elevatorSubsystem.scaleTicks));
    		
    		addSequential(new DriveToDistanceCommand(0, crossScaleDist3));
    		
    		addSequential(new FoldIntakeCommand(true));
    		addSequential(new ReleaseIntakeCommand());
    	} else if (Robot.driveSubsystem.getAutoSwitch() == 'M') {
    		addSequential(new DriveForwardTimeOutCommand());
        	
        	addSequential(new DriveToDistanceCommand(centerScaleDist1, centerScaleDist1)); //distance given in inches
    		addSequential(new DriveToAngleCommand(left, right));
    		addSequential(new DriveToDistanceCommand(centerScaleDist2, centerScaleDist2));
    		addSequential(new DriveToAngleCommand(right, left));
    		addSequential(new DriveToDistanceCommand(centerScaleDist3, centerScaleDist3));
    		
    		addSequential(new ElevatorResetCommand());
        	addParallel(new ElevatorToTicksCommand(Robot.elevatorSubsystem.scaleTicks));
    		addSequential(new FoldIntakeCommand(true));
    		addSequential(new ReleaseIntakeCommand());
    	} else if (Robot.driveSubsystem.getAutoSwitch() == 'R') {
    		addSequential(new DriveForwardTimeOutCommand());
    		
        	addSequential(new DriveToDistanceCommand(crossScaleDist1, sameScaleDist1)); //distance given in inches
    		addSequential(new DriveToAngleCommand(left, left));
    		addSequential(new DriveToDistanceCommand(crossScaleDist2, sameScaleDist2));
    		addSequential(new DriveToAngleCommand(right, 0));
    		
    		addSequential(new ElevatorResetCommand());
    		addParallel(new ElevatorToTicksCommand(Robot.elevatorSubsystem.scaleTicks));
    		
    		addSequential(new DriveToDistanceCommand(crossScaleDist3, 0));
    		
    		addSequential(new FoldIntakeCommand(true));
    		addSequential(new ReleaseIntakeCommand());
    	} else {
    		addSequential(new AutoForward());
    	}
    }
}

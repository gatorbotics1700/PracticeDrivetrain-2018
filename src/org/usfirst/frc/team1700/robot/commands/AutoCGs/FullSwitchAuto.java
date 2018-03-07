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
public class FullSwitchAuto extends CommandGroup {

    public FullSwitchAuto() {
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
    	double crossSwitchDist1 = 50*DriveSubsystem.ticksToInches;
    	double crossSwitchDist2 = 110*DriveSubsystem.ticksToInches;
    	double crossSwitchDist3 = 54*DriveSubsystem.ticksToInches;
    	
    	double sameSwitchDist1 = 106*DriveSubsystem.ticksToInches;
    	
    	double left = -90;
    	double right = 90;
    	
    	if (Robot.driveSubsystem.getAutoSwitch() == 'L') {
    		addSequential(new DriveForwardTimeOutCommand());
    		
    		addSequential(new DriveToDistanceCommand(sameSwitchDist1, crossSwitchDist1)); //distance given in inches
    		addSequential(new DriveToAngleCommand(0, right));
    		addSequential(new DriveToDistanceCommand(0, crossSwitchDist2));
    		addSequential(new DriveToAngleCommand(0, left));
    		addSequential(new DriveToDistanceCommand(0, crossSwitchDist3));
    		
    		addSequential(new ElevatorResetCommand());
    		addParallel(new ElevatorToTicksCommand(Robot.elevatorSubsystem.switchTicks));
    		addSequential(new FoldIntakeCommand(true));
    		addSequential(new ReleaseIntakeCommand());
    	} else if (Robot.driveSubsystem.getAutoSwitch() == 'M') {
    		addSequential(new DriveForwardTimeOutCommand());
    		
        	addSequential(new DriveToDistanceCommand(42*DriveSubsystem.ticksToInches, 42*DriveSubsystem.ticksToInches)); //distance given in inches
    		addSequential(new DriveToAngleCommand(-45, 45));
    		addSequential(new DriveToDistanceCommand(50*DriveSubsystem.ticksToInches, 70*DriveSubsystem.ticksToInches));
    		addSequential(new DriveToAngleCommand(45, -45));
    		
    		addSequential(new ElevatorResetCommand());
        	addParallel(new ElevatorToTicksCommand(Robot.elevatorSubsystem.switchTicks));
        	addSequential(new FoldIntakeCommand(true));
    		addSequential(new ReleaseIntakeCommand());
    	} else if (Robot.driveSubsystem.getAutoSwitch() == 'R') {
    		addSequential(new DriveForwardTimeOutCommand());
    		
        	addSequential(new DriveToDistanceCommand(crossSwitchDist1, sameSwitchDist1)); //distance given in inches
    		addSequential(new DriveToAngleCommand(left, 0));
    		addSequential(new DriveToDistanceCommand(crossSwitchDist2, 0));
    		addSequential(new DriveToAngleCommand(right, 0));
    		addSequential(new DriveToDistanceCommand(crossSwitchDist3, 0));

    		addSequential(new ElevatorResetCommand());
    		addParallel(new ElevatorToTicksCommand(Robot.elevatorSubsystem.switchTicks));
    		addSequential(new FoldIntakeCommand(true));
    		addSequential(new ReleaseIntakeCommand());
    	} else {
    		addSequential(new AutoForward());
    		addSequential(new FoldIntakeCommand(true));
    	}
    }
}

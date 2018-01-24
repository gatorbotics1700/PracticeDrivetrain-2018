package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorDownCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorToTicksCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorUpCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.ReleaseIntakeCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterSwitchAutoCG extends CommandGroup {

    public CenterSwitchAutoCG() {
    	requires(Robot.driveSubsystem);
    	requires(Robot.elevatorSubsystem);
    	requires(Robot.intakeSubsystem);
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
    	addSequential(new DriveToDistanceCommand(100)); //distance given in inches
    	//new DriveToAngleCommand(90);
    	

    	//Start off in center
    	//Turn in the direction of our switch
	//Drive for some amount of time
	//Turn back to the original position
	//Drive until we are next to the switch
	//Move the elevator up
    	int ticks = 5; //TODO: Replace this with actual number!
    	addSequential(new ElevatorToTicksCommand(ticks));
	//Dump the cube on the witch
    	addSequential(new ReleaseIntakeCommand());
	//Retract the elevator
    	addSequential(new ElevatorDownCommand());
    	
    	
    }
}

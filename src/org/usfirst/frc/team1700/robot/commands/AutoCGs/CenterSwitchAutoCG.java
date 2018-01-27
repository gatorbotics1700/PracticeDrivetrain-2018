package org.usfirst.frc.team1700.robot.commands.AutoCGs;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToAngleCommand;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToDistanceCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorDownCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorToTicksCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorUpCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.FoldIntakeCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.ReleaseIntakeCommand;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterSwitchAutoCG extends CommandGroup {

    public CenterSwitchAutoCG(boolean left) {
    	requires(Robot.driveSubsystem);
    	requires(Robot.elevatorSubsystem);
    	requires(Robot.intakeSubsystem);
    	
    	//TODO: Set all constants
    	int angle;
    	String gameData = DriverStation.getInstance().getGameSpecificMessage();
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
		if(gameData.charAt(0) == 'L') {
			//Put left auto code here
    		angle = -90;
		} else {
			//Put right auto code here
    		angle = 90;
		}
		addSequential(new DriveToAngleCommand(angle));
		addSequential(new DriveToDistanceCommand(50));
		addSequential(new DriveToAngleCommand(-angle));
    	//addSequential(new ElevatorToTicksCommand(Robot.elevatorSubsystem.switchTicks));
    	//addSequential(new FoldIntakeCommand(false)); // unfolds intake
    	//addSequential(new ReleaseIntakeCommand());
	//Retract the elevator
    	//addSequential(new ElevatorDownCommand());
    }
}

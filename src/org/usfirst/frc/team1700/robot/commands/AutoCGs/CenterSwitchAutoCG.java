package org.usfirst.frc.team1700.robot.commands.AutoCGs;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveForwardTimeOutCommand;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToAngleCommand;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToDistanceCommand;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveUntilOverCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorResetCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorToTicksCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorUpCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.FoldIntakeCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.ReleaseIntakeCommand;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team1700.robot.subsystems.IntakeSubsystem.IntakeState;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterSwitchAutoCG extends CommandGroup {

    public CenterSwitchAutoCG() {
    	//gives access to drive, elevator, and intake subsystems so that 
    	//we can use the commands from those subsystems without worrying that they will 
    	//occur simultaneously when they are not supposed to
    	
    	//TODO: Set all constants
    	//int angle;
    	//String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	//gameData is now saved as a string L but it will soon be replaced by the code 
    	//above to get the game data 
//    	String gameData = "L";
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
    	
    	//the robot will drive forward 100 inches
    	double centerSwitchDist1 = 42*DriveSubsystem.ticksToInches;
    	double centerSwitchDist2 = 50*DriveSubsystem.ticksToInches;
    	
    	addSequential(new DriveForwardTimeOutCommand());
    	
    	addSequential(new DriveToDistanceCommand(centerSwitchDist1, centerSwitchDist1)); //distance given in inches
		addSequential(new DriveToAngleCommand(-45, 45));
		addSequential(new DriveToDistanceCommand(centerSwitchDist2, centerSwitchDist2));
		addSequential(new DriveToAngleCommand(45, -45));
		
		addSequential(new ElevatorResetCommand());
		addParallel(new ElevatorToTicksCommand(Robot.elevatorSubsystem.switchTicks));
		addSequential(new FoldIntakeCommand(true));
		addSequential(new ReleaseIntakeCommand());
    	
    	//when the intake state equals above switch/scale, this code will make the robot
    	//drop the cube
//    	if (Robot.intakeSubsystem.intakeState == IntakeState.OVER) {
//    		addSequential(new ReleaseIntakeCommand());
//    	} else {
//    		this.cancel();
//    	}
    }
    
}

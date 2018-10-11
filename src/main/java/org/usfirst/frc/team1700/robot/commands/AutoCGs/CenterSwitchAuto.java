package org.usfirst.frc.team1700.robot.commands.AutoCGs;

import java.time.Duration;
import java.time.Instant;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveForwardTimeOutCommand;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToAngleCommand;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToDistanceCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorResetCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorToInchesCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.FoldIntakeCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.GrabIntakeCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.IntakeInCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.ReleaseIntakeCommand;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterSwitchAuto extends CommandGroup {

    public CenterSwitchAuto() {
//    	addSequential(new DriveForwardTimeOutCommand());
    	addSequential(new ElevatorResetCommand());
		addParallel(new IntakeInCommand());
		
    	Instant start = Instant.now();
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	while (gameData.length() < 3) {
    		gameData = DriverStation.getInstance().getGameSpecificMessage();
    		if (Duration.between(start, Instant.now()).toMillis() > 100) {
    			DriverStation.getInstance().reportWarning("timed out :(", false);
        		addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.distToAutoLine, 0)); //distance given in inches
    			return;
    		}
    	}
    	DriverStation.getInstance().reportWarning("at drivetodistance logic", false);
    	if(gameData.charAt(0) == 'L') {
    		DriverStation.getInstance().reportWarning("Switch on left; switch", false);
        	addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.distToSwitch, 0)); //distance given in inches
        		
        	DriverStation.getInstance().reportWarning("Driven to Distance", false);
        	addParallel(new ElevatorToInchesCommand(Robot.elevatorSubsystem.switchHeight));
       		addSequential(new DriveToAngleCommand(Robot.driveSubsystem.right,0));
    		DriverStation.getInstance().reportWarning("Driven to Angle", false);
       		addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.finalDistToSwitch,0)); 

       		addSequential(new FoldIntakeCommand(false));
       		addSequential(new GrabIntakeCommand(true));
       		addSequential(new ReleaseIntakeCommand(-0.2));
       		DriverStation.getInstance().reportWarning("finished left switch auto", false);
    	} else if (gameData.charAt(0) == 'R') {
    		DriverStation.getInstance().reportWarning("Switch on right; switch", false);
    		addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.distToSwitch, 0)); //distance given in inches
			
    		DriverStation.getInstance().reportWarning("Driven to distance", false);
			addParallel(new ElevatorToInchesCommand(Robot.elevatorSubsystem.switchHeight));
			addSequential(new DriveToAngleCommand(Robot.driveSubsystem.left,0));
			DriverStation.getInstance().reportWarning("Driven to angle", false);
    		addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.finalDistToSwitch, 0));

    		addSequential(new FoldIntakeCommand(false));
    		addSequential(new GrabIntakeCommand(true));
    		addSequential(new ReleaseIntakeCommand(-0.2));
    		
    	} else {
    		addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.distToAutoLine, 0)); //distance given in inches
		}
		
		 // Second Cube 
    	// start = Instant.now();
		// while (Duration.between(start,  Instant.now()).toMillis() < 500) {
		// }
		
		// addSequential(new DrivetoDistanceCommand(Robot.driveSubsystem.centerDistBack, 0));
		  
		  
    }
}
package org.usfirst.frc.team1700.robot.commands.AutoCGs;

import java.time.Duration;
import java.time.Instant;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveForwardTimeOutCommand;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToAngleCommand;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToDistanceCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorResetCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorToInchesCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.AutoReleaseIntakeCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.FoldIntakeCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.GrabIntakeCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.IntakeInCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.ReleaseIntakeCommand;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightSwitchAuto extends CommandGroup {

    public RightSwitchAuto() {
    	requires(Robot.intakeSubsystem);
    	requires(Robot.driveSubsystem);
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
    			addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.distToAutoLine, 0)); //distance given in inches
				DriverStation.getInstance().reportWarning("Switch on left; cross line", false);
//    		if(gameData.charAt(1) == 'R') {
//    			DriverStation.getInstance().reportWarning("Switch on left, scale on right; scale", false);
//    			addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.sameScaleDist, 0));
//    			
//        		addParallel(new ElevatorToInchesCommand(Robot.elevatorSubsystem.scaleHeight));
//        		addSequential(new DriveToAngleCommand(-45,0));
//        		addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.finalDistToScale, 0)); //distance given in inches
//        		
//        		addSequential(new FoldIntakeCommand(false));
//        		
//        		start = Instant.now();
//        		while (Duration.between(start, Instant.now()).toMillis() < Robot.driveSubsystem.waitTime) {
//        		}
//        		
//        		addSequential(new GrabIntakeCommand(true));
//        		addSequential(new ReleaseIntakeCommand(-0.6));
//    			DriverStation.getInstance().reportWarning("finished right scale auto", false);
//    		} 
//    		else if(gameData.charAt(1) == 'L') {
//    			DriverStation.getInstance().reportWarning("Switch on left, scale on left; cross scale", false);
//    			addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.crossScaleDist1, 0));
//    			addSequential(new DriveToAngleCommand(-90, 0)); 
//    			addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.crossScaleDist2, 0));
//    		
//        		addParallel(new ElevatorToInchesCommand(Robot.elevatorSubsystem.scaleHeight));
//        		addSequential(new DriveToAngleCommand(90,0));
//        		addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.finalDistToScale, 0)); //distance given in inches
//    		
//    			addSequential(new FoldIntakeCommand(false)); 
//
//	    		start = Instant.now();
//	    		while (Duration.between(start, Instant.now()).toMillis() < Robot.driveSubsystem.waitTime) {
//	    		}
//    		
//        		addSequential(new GrabIntakeCommand(true));
//        		addSequential(new ReleaseIntakeCommand(-0.4));
//    			DriverStation.getInstance().reportWarning("finished left scale auto", false);
//    		}
//    		else {
//	    		addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.distToAutoLine, 0)); //distance given in inches
//				DriverStation.getInstance().reportWarning("Switch on left; cross line", false);
//    		}
    		
    	}
    	else if (gameData.charAt(0) == 'R') {
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
    		
//    		if(gameData.charAt(1) == 'R') {
//    			DriverStation.getInstance().reportWarning("Switch on right, scale on right; scale", false);
//    			addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.sameScaleDist, 0));
//    			
//        		addParallel(new ElevatorToInchesCommand(Robot.elevatorSubsystem.scaleHeight));
//        		addSequential(new DriveToAngleCommand(-45,0));
//        		addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.finalDistToScale, 0)); //distance given in inches
//    		
//        		addSequential(new FoldIntakeCommand(false));
//
//        		start = Instant.now();
//        		while (Duration.between(start, Instant.now()).toMillis() < Robot.driveSubsystem.waitTime) {
//        		}
//        		
//        		addSequential(new GrabIntakeCommand(true));
//        		addSequential(new ReleaseIntakeCommand(-0.6));
//    			DriverStation.getInstance().reportWarning("finished right scale auto", false);
//    		}
//    		else if(gameData.charAt(1) == 'L') {
//				DriverStation.getInstance().reportWarning("Switch on right; switch", false);
//	    		addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.distToSwitch, 0)); //distance given in inches
//				
//	    		DriverStation.getInstance().reportWarning("Driven to distance", false);
//				addParallel(new ElevatorToInchesCommand(Robot.elevatorSubsystem.switchHeight));
//				addSequential(new DriveToAngleCommand(Robot.driveSubsystem.left,0));
//				DriverStation.getInstance().reportWarning("Driven to angle", false);
//	    		addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.finalDistToSwitch, 0));
//	
//	    		addSequential(new FoldIntakeCommand(false));
//	    		addSequential(new GrabIntakeCommand(true));
//	    		addSequential(new ReleaseIntakeCommand(-0.2));
//    		}
    	} 
    	else {
    		addSequential(new DriveToDistanceCommand(Robot.driveSubsystem.distToAutoLine, 0)); //distance given in inches
		}

		
    }
}

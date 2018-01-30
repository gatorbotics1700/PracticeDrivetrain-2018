package org.usfirst.frc.team1700.robot.commands.AutoCGs;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToAngleCommand;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToDistanceCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorToTicksCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.FoldIntakeCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.ReleaseIntakeCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossScaleAutoCG extends CommandGroup {

    public CrossScaleAutoCG() {
    	requires(Robot.driveSubsystem);
    	requires(Robot.elevatorSubsystem);
    	requires(Robot.intakeSubsystem);
        
    	//TODO: Actually find these values
    	int inchesFromSwitchTurn = 200;
    	int scaleLength = 180;
    	int remainingDistanceToScale = 200;
    	
    	addSequential(new DriveToDistanceCommand(inchesFromSwitchTurn));
    	String gameData = "L";
    	if (gameData.charAt(0)=='L') {
    		addSequential(new DriveToAngleCommand(-90));
    		addSequential(new DriveToDistanceCommand(scaleLength));
    		addSequential(new DriveToAngleCommand(90));
    		addSequential(new DriveToDistanceCommand(remainingDistanceToScale));
    		addSequential(new DriveToAngleCommand(90));
    	}
    	else if (gameData.charAt(0)=='R'){
    		addSequential(new DriveToAngleCommand(90));
    		addSequential(new DriveToDistanceCommand(scaleLength));
    		addSequential(new DriveToAngleCommand(-90));
    		addSequential(new DriveToDistanceCommand(remainingDistanceToScale));
    		addSequential(new DriveToAngleCommand(-90));
    	}
    	else {
			this.cancel();
		}
    	addSequential(new ElevatorToTicksCommand(Robot.elevatorSubsystem.scaleTicks));
    	addSequential(new FoldIntakeCommand(false));
    	addSequential(new ReleaseIntakeCommand());
    	
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
    }
}

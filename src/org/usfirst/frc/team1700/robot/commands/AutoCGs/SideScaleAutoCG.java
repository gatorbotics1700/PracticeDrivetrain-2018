package org.usfirst.frc.team1700.robot.commands.AutoCGs;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToAngleCommand;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToDistanceCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorToTicksCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.FoldIntakeCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.ReleaseIntakeCommand;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SideScaleAutoCG extends CommandGroup {

    public SideScaleAutoCG(boolean left) {
    	requires(Robot.driveSubsystem);
    	requires(Robot.elevatorSubsystem);
    	requires(Robot.intakeSubsystem);
        
    	//TODO: Actually find these values
    	int inchesFromScale = 300;
    	
    	addSequential(new DriveToDistanceCommand(inchesFromScale));
    	String gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData.charAt(0) == 'L' && left)
		{
			//Put left auto code here
			addSequential(new DriveToAngleCommand(90));
    		
		} else if (gameData.charAt(0) !='L' && !left) {
			addSequential(new DriveToAngleCommand(-90));
			//Put right auto code here
    		
		} else {
			this.cancel();
		}
		addSequential(new ElevatorToTicksCommand(Robot.elevatorSubsystem.scaleTicks));
		addSequential(new FoldIntakeCommand(false));
		addSequential(new ReleaseIntakeCommand());
    }
}

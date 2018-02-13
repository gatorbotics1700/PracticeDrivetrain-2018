package org.usfirst.frc.team1700.robot.commands.AutoCGs;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToAngleCommand;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToDistanceCommand;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveUntilOverCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorToTicksCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.FoldIntakeCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.ReleaseIntakeCommand;
import org.usfirst.frc.team1700.robot.subsystems.IntakeSubsystem.IntakeState;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SideScaleAutoCG extends CommandGroup {

    public SideScaleAutoCG() {
    	//gives access to drive, elevator, and intake subsystems so that 
    	//we can use the commands from those subsystems without worrying that they will 
    	//occur simultaneously when they are not supposed to
    	requires(Robot.driveSubsystem);
    	requires(Robot.elevatorSubsystem);
    	requires(Robot.intakeSubsystem);
        
    	//TODO: Actually find these values
    	
    	//these values will dictate when the robot turns (we need the precise values)
    	int inchesFromScale = 300;
    	int angle = -90;
    	int overDistance = 10;
    	
    	//this command causes the robot to drive forward until it reaches 
    	//the point where it should turn at the switch
    	addSequential(new DriveToDistanceCommand(inchesFromScale));
    	String gameData = "L";
		if(gameData.charAt(0) == 'L')
		{
			angle = 90;
    		
		}
		addSequential(new DriveToAngleCommand(angle));
		addSequential(new ElevatorToTicksCommand(Robot.elevatorSubsystem.scaleTicks));
		addSequential(new FoldIntakeCommand(false));
		addSequential(new DriveUntilOverCommand());
		
		//when the intake state equals above switch/scale, this code will make the robot
    	//drop the cube
		if (Robot.intakeSubsystem.intakeState == IntakeState.OVER) {
			addSequential(new ReleaseIntakeCommand());
		} else {
			this.cancel();
		}
    }
}

package org.usfirst.frc.team1700.robot.commands.AutoCGs;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToAngleCommand;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToDistanceCommand;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveUntilOverCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorDownCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorToTicksCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorUpCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.FoldIntakeCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.ReleaseIntakeCommand;
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
    	requires(Robot.driveSubsystem);
    	requires(Robot.elevatorSubsystem);
    	requires(Robot.intakeSubsystem);
    	
    	//TODO: Set all constants
    	int angle;
    	//String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	//gameData is now saved as a string L but it will soon be replaced by the code 
    	//above to get the game data 
    	String gameData = "L";
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
    	addSequential(new DriveToDistanceCommand(100)); //distance given in inches
		if(gameData.charAt(0) == 'L') {
			//Put left auto code here
    		angle = -90;
		} else {
			//Put right auto code here
    		angle = 90;
		}
		
		//more commands that will make the robot drive to the switch
		addSequential(new DriveToAngleCommand(angle));
		addSequential(new DriveToDistanceCommand(50));
		addSequential(new DriveToAngleCommand(-angle));
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

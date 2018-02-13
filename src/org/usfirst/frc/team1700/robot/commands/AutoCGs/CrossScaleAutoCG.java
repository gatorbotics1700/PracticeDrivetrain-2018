package org.usfirst.frc.team1700.robot.commands.AutoCGs;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToAngleCommand;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveToDistanceCommand;
import org.usfirst.frc.team1700.robot.commands.Drivetrain.DriveUntilOverCommand;
import org.usfirst.frc.team1700.robot.commands.Elevator.ElevatorToTicksCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.FoldIntakeCommand;
import org.usfirst.frc.team1700.robot.commands.Intake.ReleaseIntakeCommand;
import org.usfirst.frc.team1700.robot.subsystems.IntakeSubsystem.IntakeState;

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
    	//these values will dictate when the robot turns (we need the precise values) 
    	int inchesFromSwitchTurn = 200;
    	int scaleLength = 180;
    	int remainingDistanceToScale = 200;
    	int angle = 90;
    	
    	//this command causes the robot to drive forward until it reaches 
    	//the point where it should turn at the switch
    	addSequential(new DriveToDistanceCommand(inchesFromSwitchTurn));
    	String gameData = "L";
    	if (gameData.charAt(0)=='L') {
    		angle = -90;
    	}
    	//all of these commands do a similar thing to the one above, dictating when 
    	//the robot should drive forward and when it should turn
		addSequential(new DriveToAngleCommand(angle));
		addSequential(new DriveToDistanceCommand(scaleLength));
		addSequential(new DriveToAngleCommand(-angle));
		addSequential(new DriveToDistanceCommand(remainingDistanceToScale));
		addSequential(new DriveToAngleCommand(-angle));
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

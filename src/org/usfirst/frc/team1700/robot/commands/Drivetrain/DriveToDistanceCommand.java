package org.usfirst.frc.team1700.robot.commands.Drivetrain;

import org.usfirst.frc.team1700.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Super rough (so far) drive to distance method with encoders
 */
public class DriveToDistanceCommand extends Command {
	
	double distance;

    public DriveToDistanceCommand(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.distance = distance;
    	requires(Robot.driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.driveTank(-0.2, -0.2);
    	System.out.println((Robot.driveSubsystem.getLeftEncoderValue() > distance/Robot.driveSubsystem.ticksToInches ||
        		Robot.driveSubsystem.getRightEncoderValue() > distance/Robot.driveSubsystem.ticksToInches));
    	System.out.println(Robot.driveSubsystem.getLeftEncoderValue());
    	System.out.println(Robot.driveSubsystem.getRightEncoderValue());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.driveSubsystem.getLeftEncoderValue() > distance/Robot.driveSubsystem.ticksToInches ||
        		Robot.driveSubsystem.getRightEncoderValue() > distance/Robot.driveSubsystem.ticksToInches);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.driveTank(0, 0);
    	Robot.driveSubsystem.resetEncoders();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

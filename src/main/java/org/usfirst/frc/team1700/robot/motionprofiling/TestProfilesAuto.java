package org.usfirst.frc.team1700.robot.motionprofiling;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.Timer;

public class TestProfilesAuto{
    private DrivetrainProfile drivetrainProfile = new DrivetrainProfile();

    protected double startTime;

    public static enum AutoType {
        DRIVE_FORWARD,
        CURVE;
    }

    AutoType autoType;

    public TestProfilesAuto(AutoType autoType) {
        this.autoType = autoType;
    }

    public void init(){
        if(autoType == AutoType.CURVE) {
            test2dProfileAutoInit();
        }
        else {
            driveForwardAutoInit();
        }
    }

    public void periodic(){
        if(autoType == AutoType.CURVE) {
            test2dProfileAutoPeriodic();
        }
        else {
            driveForwardAutoPeriodic();
        }
    }

    DriveForwardAutoStates driveForwardAutoState;

    protected enum DriveForwardAutoStates{
        DRIVING,
        END;
    }

    //fill this in later
    public void driveForwardAutoInit(){
        System.out.println("Drive Forward Auto Init");
        Robot.driveSubsystem.resetEncoders(); 
        drivetrainProfile.generateProfile("/home/lvuser/LeftTestStraight.csv", "/home/lvuser/RightTestStraight.csv");
        driveForwardAutoState = DriveForwardAutoStates.DRIVING;
        startTime = Timer.getFPGATimestamp();
    }

    public void driveForwardAutoPeriodic(){
        double currentDistanceL= Robot.driveSubsystem.getLeftEncoderValue();
        double currentDistanceR= Robot.driveSubsystem.getRightEncoderValue();
        double currentVelocityL = Robot.driveSubsystem.getVelocityL();
        double currentVelocityR = Robot.driveSubsystem.getVelocityR();

        if(driveForwardAutoState == DriveForwardAutoStates.DRIVING){
            if(currentDistanceL > 100) {
                driveForwardAutoState = DriveForwardAutoStates.END;
            }
            else{
                double time = (Timer.getFPGATimestamp() - startTime);
                Robot.leftSpeed = -drivetrainProfile.updateLeftVoltage(time, currentDistanceL, currentVelocityL);
                Robot.rightSpeed = -drivetrainProfile.updateRightVoltage(time, currentDistanceR, currentVelocityR);
            }
        }
        else{
            Robot.leftSpeed = 0;
            Robot.rightSpeed = 0;
        }

    }

    Test2dProfileStates test2dProfileState;

    protected enum Test2dProfileStates{
        DRIVING,
        END;
    }

    public void test2dProfileAutoInit() {
        Robot.driveSubsystem.resetEncoders();         
        drivetrainProfile.generateProfile("src/main/java/resources/LeftTestCurve.csv", "src/main/java/resources/RightTestCurve.csv");
        test2dProfileState = Test2dProfileStates.DRIVING;
        startTime = -1;   
    }

    public void test2dProfileAutoPeriodic() {
        double currentDistanceL= Robot.driveSubsystem.getLeftEncoderValue();
        double currentDistanceR= Robot.driveSubsystem.getRightEncoderValue();
        double currentVelocityL = Robot.driveSubsystem.getVelocityL();
        double currentVelocityR = Robot.driveSubsystem.getVelocityR();

        if(test2dProfileState == Test2dProfileStates.DRIVING){
            // total distance is kinda weird rn 
            if(currentDistanceL > 200) { //fix total distance 
               test2dProfileState = Test2dProfileStates.END;
            }
            else{
                double time = (Timer.getFPGATimestamp() - startTime);
                Robot.leftSpeed = -drivetrainProfile.updateLeftVoltage(time, currentDistanceL, currentVelocityL);
                Robot.rightSpeed = -drivetrainProfile.updateRightVoltage(time, currentDistanceR, currentVelocityR);
            }
        }
        else{
            Robot.leftSpeed = 0;
            Robot.rightSpeed = 0;
        }
    }
}
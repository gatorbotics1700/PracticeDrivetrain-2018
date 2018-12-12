package org.usfirst.frc.team1700.robot.motionprofiling;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;

public class TestProfilesAuto{
    private DriveSubsystem driveSubsystem;
    private DrivetrainProfile drivetrainProfile;

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
        driveSubsystem.resetEncoders(); 
        startTime = System.currentTimeMillis();
        
        drivetrainProfile.generateProfile("src/main/java/resources/LeftTestStraight.csv", "src/main/java/resources/RightTestStraight.csv");
        driveForwardAutoState = DriveForwardAutoStates.DRIVING;
    }

    public void driveForwardAutoPeriodic(){
        double currentDistanceL= driveSubsystem.getLeftEncoderValue();
        double currentDistanceR= driveSubsystem.getRightEncoderValue();
        double currentVelocityL = driveSubsystem.getVelocityL();
        double currentVelocityR = driveSubsystem.getVelocityR();

        if(driveForwardAutoState == DriveForwardAutoStates.DRIVING){
            if(currentDistanceL > drivetrainProfile.getTotalDistance()) {
                driveForwardAutoState = DriveForwardAutoStates.END;
            }
            else{
                double time = System.currentTimeMillis() - startTime;
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
        driveSubsystem.resetEncoders(); 
        startTime = System.currentTimeMillis();
        
        drivetrainProfile.generateProfile("src/main/java/resources/LeftTestCurve.csv", "src/main/java/resources/RightTestCurve.csv");
        test2dProfileState = Test2dProfileStates.DRIVING;   
    }

    public void test2dProfileAutoPeriodic() {
        double currentDistanceL= driveSubsystem.getLeftEncoderValue();
        double currentDistanceR= driveSubsystem.getRightEncoderValue();
        double currentVelocityL = driveSubsystem.getVelocityL();
        double currentVelocityR = driveSubsystem.getVelocityR();

        if(test2dProfileState == Test2dProfileStates.DRIVING){
            // total distance is kinda weird rn 
            if(currentDistanceL > drivetrainProfile.getTotalDistance()) {
               test2dProfileState = Test2dProfileStates.END;
            }
            else{
                double time = System.currentTimeMillis() - startTime;
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
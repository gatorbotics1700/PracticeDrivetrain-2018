package org.usfirst.frc.team1700.robot.reese;

import org.usfirst.frc.team1700.robot.reese.profile.Profile;
import org.usfirst.frc.team1700.robot.reese.profile.ProfileFromFile;
import org.usfirst.frc.team1700.robot.reese.profile.ProfilePoint;
import org.usfirst.frc.team1700.robot.autonmodes.AutonomousBase;

public class DrivetrainProfile extends Profile{
    Profile profile;

    // NOT TESTED
    protected double minVoltage = AutonomousBase.minDrivePowerDuringDrive;
    protected double Kp = 0.0001;
    protected double Kv = 0.0001;        
    protected double Kfa = 0;
    protected double Kfv = 0.01; 

    public DrivetrainProfile(){
    }

    public void generateProfile(String file1, String file2) {
        ProfileFromFile profileFromFile = new ProfileFromFile();
        profileFromFile.specifyFiles(file1, file2);
        profile = profileFromFile.load2DProfile_2Files();
    }

    public ProfilePoint getLeftCurrPoint(double currTime) {
        return getCurrPoint(0, currTime);
    }

    public ProfilePoint getRightCurrPoint(double currTime) {
        return getCurrPoint(1, currTime);
    }
}
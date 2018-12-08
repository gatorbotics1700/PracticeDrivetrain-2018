package org.usfirst.frc.team1700.robot.motionprofiling;

import org.usfirst.frc.team1700.robot.motionprofiling.profile.Profile;
import org.usfirst.frc.team1700.robot.motionprofiling.profile.ProfileFromFile;
import org.usfirst.frc.team1700.robot.motionprofiling.profile.ProfilePoint;

public class DrivetrainProfile extends Profile{
    Profile profile;

    // NOT TESTED
    protected double minVoltage = 0.1;
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
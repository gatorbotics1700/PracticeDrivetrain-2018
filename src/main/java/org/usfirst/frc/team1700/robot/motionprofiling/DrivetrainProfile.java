package org.usfirst.frc.team1700.robot.motionprofiling;

import org.usfirst.frc.team1700.robot.motionprofiling.profile.Profile;
import org.usfirst.frc.team1700.robot.motionprofiling.profile.ProfileFromFile;
import org.usfirst.frc.team1700.robot.motionprofiling.profile.ProfilePoint;

public class DrivetrainProfile extends Profile{
    // NOT TESTED
    protected double minVoltage = 0.04;
    protected double Kp = 0;
    protected double Kv = 0;        
    protected double Kfa = 0.001;
    protected double Kfv = 0.006; 
    public DrivetrainProfile(){
    }

    public void generateProfile(String file1, String file2) {
        ProfileFromFile profileFromFile = new ProfileFromFile();
        profileFromFile.specifyFiles(file1, file2);
        this.profile = profileFromFile.load2DProfile_2Files();
    }

    public ProfilePoint getLeftCurrPoint(double currTime) {
        return getCurrPoint(0, currTime);
    }

    public ProfilePoint getRightCurrPoint(double currTime) {
        return getCurrPoint(1, currTime);
    }

    // time should be the time since profile started (time = System.currentTimeMillis() - startTime)
    public double updateVoltage(double time, ProfilePoint point, double currDist, double currV) {
        double profilePosition = point.pos; 
        double profileVelocity = point.V;
        double profileAcceleration = point.A;
        //System.out.println(point.pos + ", " + point.V);
        
        double distanceError = currDist - profilePosition;
        double velocityError = currV - profileVelocity;
            
        double voltage = Kp*distanceError + Kv*velocityError + minVoltage 
        + Kfv*profileVelocity + Kfa*profileAcceleration;
        return voltage; 
    }

    public double updateRightVoltage(double time, double currDist, double currV) {
        ProfilePoint point = getCurrPoint(1, time);
        return updateVoltage(time, point, currDist, currV); 
    }

    public double updateLeftVoltage(double time, double currDist, double currV) {
        ProfilePoint point = getCurrPoint(0, time);
        return updateVoltage(time, point, currDist, currV);
    }
}
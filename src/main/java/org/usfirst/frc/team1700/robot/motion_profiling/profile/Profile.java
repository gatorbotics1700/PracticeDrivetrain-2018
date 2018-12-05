package org.usfirst.frc.team1700.robot.reese.profile;

import java.util.ArrayList;
import org.usfirst.frc.team1700.robot.reese.profile.ProfilePoint;

public class Profile{
    public ArrayList<ArrayList<ProfilePoint>> profile;

    public Profile() {
    }

    public void generateProfile() {
    }

    protected int i = 0;
    public ProfilePoint getCurrPoint(int index, double currTime) {
        ArrayList<ProfilePoint> chosenProfile = profile.get(index);
        while(currTime < chosenProfile.get(i).time && i < chosenProfile.size()){
            i++;
        }
        if(chosenProfile.size() < i-1) {
            return chosenProfile.get(i-1);
        }
        else {
            return chosenProfile.get(chosenProfile.size()-1); 
        }
    }

    // 1D profiles
    public ProfilePoint getCurrPoint(double currTime) {
        return getCurrPoint(0, currTime);
    }

    public double getTotalDistance() {
        ArrayList<ProfilePoint> firstProfile = profile.get(0);
        return (firstProfile.get(firstProfile.size()-1)).pos;
    }

    public void add(ArrayList<ProfilePoint> newProfilePart) {
        profile.add(newProfilePart); 
    }

    public double Kp;
    public double Kv;
    public double Kfa;
    public double Kfv; 
    public double minVoltage;

    // time should be the time since profile started (time = System.currentTimeMillis() - startTime)
    public double updateVoltage(double time, ProfilePoint point, double currDist, double currV) {
        double profilePosition = point.pos; 
        double profileVelocity = point.V;
        double profileAcceleration = point.A;
        
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

    // 1D profiles
    public double updateVoltage(double time, double currDist, double currV) {
        ProfilePoint point = getCurrPoint(0, time);
        return updateVoltage(time, point, currDist, currV);
    }
}
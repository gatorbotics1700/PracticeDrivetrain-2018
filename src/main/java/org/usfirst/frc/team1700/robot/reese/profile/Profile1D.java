package org.usfirst.frc.team1700.robot.reese.profile;

import java.util.ArrayList;


public class Profile1D extends Profile{
    double Vmax = 100; // in/sec 
    double Amax = 300; // in/sec/sec
    double Vi = 0;
    double Vf = 0;
    double dt = 0.001; 
    double Xi;
    double Xf;
    int checkInterval = 50; 

    double t1;
    double t2; 
    double t3;
    int totalPoints; 

    ArrayList<ProfilePoint> profile = new ArrayList<ProfilePoint>();

    public Profile1D() {
    }

    public Profile1D(double Xinitial, double Xfinal) {
        Xi = Xinitial;
        Xf = Xfinal; 
    }

    public void generateProfile(double Xi, double Xf) {
        t1 = Vmax/Amax; 
        double deltaX1 = Vi * t1 + 1.0/2.0*Amax*t1*t1;

        if(deltaX1 > (Xf - Xi)/2 ) {
            // can't reach max velocity, so triangle
            t1 = Math.sqrt(Math.abs((Xf-Xi)/Amax));
            t2 = t1; 
            t3 = 2*t1;
        }
        else {
            // can reach max velocity, so trapezoid 
            t1 = Vmax/Amax; 
            t2 = t1 + (Xf - Xi - Amax*t1*t1)/Vmax; 
            t3 = t1 + t2; 
        }
        totalPoints = (int)(t3/dt) + 1; 

        double prevV = 0;
        double prevA = 0; 
        double prevP = Xi; 

        // accelerating
        for(int i = 0; i <= (int)(t1/dt); i++) {
            double curr_t = i*dt;             
            double velocity = prevV + prevA*dt;
            double position = prevP + (prevV+velocity)/2*dt; 
            
            ProfilePoint point = new ProfilePoint(curr_t, position, velocity, Amax);
            profile.add(point);
            //System.out.println("1. i: " + i + ", p: " + position + ", v: " + velocity + ", t: " + curr_t);

            prevA = Amax; 
            prevV = velocity;
            prevP = position;
        } 
        
        // this only is a thing for the trapezoid (for triangle: t1 = t2)
        // coasting
        for(int i = (int)(t1/dt) + 1; i <= (int)(t2/dt); i++) {
            double curr_t = i*dt;             
            double velocity = prevV + prevA*dt; 
            double position = prevP + (prevV+velocity)/2*dt;
            ProfilePoint point = new ProfilePoint(curr_t, position, velocity, 0.0);
            //System.out.println("2. i: " + i + ", p: " + position + ", v: " + velocity + ", t: " + curr_t);
            profile.add(i, point);

            prevA = 0; 
            prevP = position;
            prevV = velocity;
        } 
        
        // decelerating
        for(int i = (int)(t2/dt) + 1; i <= (int)(t3/dt); i++) {
            double curr_t = i*dt; 
            double velocity = prevV + prevA*dt;
            double position = prevP + (prevV+velocity)/2*dt;
            
            ProfilePoint point = new ProfilePoint(curr_t, position, velocity, -Amax);
            profile.add(i, point);
            //System.out.println("3. i: " + i + ", p: " + position + ", v: " + velocity + ", t: " + curr_t);

            prevA = -Amax; 
            prevV = velocity;
            prevP = position;
        }
    super.profile.add(profile);
    }
    
    public void generateProfile() {
        generateProfile(Xi, Xf); 
    }
}
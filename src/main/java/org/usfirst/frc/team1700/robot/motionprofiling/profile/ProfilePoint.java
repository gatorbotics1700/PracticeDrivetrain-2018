package org.usfirst.frc.team1700.robot.motionprofiling.profile;

public class ProfilePoint {

    public double time;
    public double Lpos;
    public double Rpos; 
    public double Lv;
    public double Rv;
    public double La;
    public double Ra;

    public double pos;
    public double V;
    public double A; 

    public ProfilePoint(){
    }

    public ProfilePoint(double time, double Lpos, double Rpos, double Lv, double Rv, double La, double Ra) {
        this.time = time;
        this.Lpos = Lpos;
        this.Rpos = Rpos;
        this.Lv = Lv;
        this.Rv = Rv;
        this.La = La; 
        this.Ra = Ra;
    }

    public ProfilePoint(double time, double pos, double V, double A) {
        this.time = time;
        this.pos = pos;
        this.V = V;
        this.A = A; 
    }
}
package org.usfirst.frc.team1700.robot.motionprofiling.path;

import java.util.ArrayList;

// this is mainly math and i dont know what to do for it
public class PathProcessor{
    public ArrayList<PathPoint> path; 
    public double MaxV; 
    public double smallCurvature = 0.0001;
    public double trackWidth; 

    public PathProcessor(double MaxV, double trackWidth){
        this.MaxV = MaxV;
        this.trackWidth = trackWidth;
    }

    public ArrayList<PathPoint> processPath(ArrayList<PathPoint> path){
        this.path = path; 
        // hmmm maybe the path should know its size? (and put that where 100 is)
        for(PathPoint point: path) {
            addData(point); 
        }
        return path; 
    }

    // not neccesarily true, change
    double prevPosAlongPath = 0;
    double prevLPos = 0;
    double prevRPos = 0;

    // this is for turning right, but not sure how to tell which way it's turning? 
    public void addData(PathPoint point){
        // isn't this dist not actually true tho for distance bc like curve not straight line? 
        double dist = point.posAlongPath - prevPosAlongPath;
        double LPos;
        double RPos;
        double LMaxV;
        double RMaxV;
        
        if(Math.abs(point.curvature) <= smallCurvature) {
            // going straight/basically straight
            LMaxV = MaxV;
            RMaxV = MaxV;
            LPos = prevLPos + dist;
            RPos = prevRPos + dist;   
        }
        else {
            double radius = Math.abs(1/point.curvature);
            double Lradius;
            double Rradius;
            if(point.curvature < 0) {
                // turning right
                Lradius = radius + 0.5*trackWidth; 
                Rradius = radius - 0.5*trackWidth;
                LMaxV = MaxV;
                RMaxV = LMaxV * Rradius / Lradius;
            }
            else{
                // turning left 
                Lradius = radius - 0.5*trackWidth; 
                Rradius = radius + 0.5*trackWidth;
                RMaxV = MaxV;
                LMaxV = RMaxV * Lradius / Rradius;
            }
            double LDist = Lradius/radius * dist;
            double RDist = Rradius/radius * dist; 
            LPos = prevLPos + LDist;
            RPos = prevRPos + RDist; 
        }

        prevLPos = LPos;
        prevRPos = RPos;
        prevPosAlongPath = point.posAlongPath; 
        point.addSideData(LPos, RPos, LMaxV, RMaxV); 
    }
}
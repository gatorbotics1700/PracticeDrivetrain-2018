package org.usfirst.frc.team1700.robot.motionprofiling.path;

public class PathPoint {

    public double x;
    public double y;
    public double posAlongPath;
    public double curvature;
    public double heading;
    public double dcurvature;
    public double LPos;
    public double RPos; 
    public double LMaxVelocity;
    public double RMaxVelocity;

    public PathPoint(){
    }

    public PathPoint(double x, double y, double posAlongPath, double heading, double curvature, double dcurvature) {
        this.x = x;
        this.y = y;
        this.posAlongPath = posAlongPath;
        this.heading = heading;
        this.curvature = curvature; 
        this.dcurvature = dcurvature;
    }

    public void addSideData(double LPos, double RPos, double LMaxVelocity, double RMaxVelocity) {
        this.LPos = LPos;
        this.RPos = RPos; 
        this.LMaxVelocity = LMaxVelocity;
        this.RMaxVelocity = RMaxVelocity;
    }
}
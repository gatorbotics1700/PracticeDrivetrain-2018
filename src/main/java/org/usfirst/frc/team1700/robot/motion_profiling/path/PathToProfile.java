package org.usfirst.frc.team1700.robot.reese.path;

import java.util.ArrayList;
import org.usfirst.frc.team1700.robot.reese.path.PathPoint;
import org.usfirst.frc.team1700.robot.reese.profile.ProfilePoint;
import org.usfirst.frc.team1700.robot.reese.util.TextFileWriter;

public class PathToProfile{
    String fileName;
    double AMax = 300;
    double dt = 0.01;
    ArrayList<Integer> badAPoints = new ArrayList<Integer>();

    public ArrayList<PathPoint> path = new ArrayList<PathPoint>();
    public ArrayList<ProfilePoint> fromRight = new ArrayList<ProfilePoint>();
    public ArrayList<ProfilePoint> fromLeft = new ArrayList<ProfilePoint>(); 
    public ArrayList<ProfilePoint> combinedProfile = new ArrayList<ProfilePoint>();
    public ArrayList<ProfilePoint> finalProfile = new ArrayList<ProfilePoint>();

    public PathToProfile(){
    }

    public void generateProfile(ArrayList<PathPoint> path) {    
        this.path = path;    
        // from left means like go from Xi to Xf and right means opposite 
        generateFromLeft();
        generateFromRight();

        boolean pray = isReasonable();
        if(pray == false) {
            // bad bad bad, try aaaaalll over again 
        } 
        else {
            // yippee go you
            shrink();
            writeProfile();
        }
    }

    // to do: deal with doing radius' smaller than our robot width * 0.5 
    public void generateFromLeft(){
        double LPrevPos = 0;
        double RPrevPos = 0;
        double LPrevV = 0;
        double RPrevV = 0;
        double time = 0;
        int count = 0;
        int countR = 0;
        
        for (int i=0; i<path.size(); i++) {
            PathPoint point = path.get(i); 
            double LVMax = point.LMaxVelocity; 
            double RVMax = point.RMaxVelocity; 
            
            double deltaT;
            double Laccel;
            double Raccel;
            double Lv;
            double Rv; 

            double maybeNewdtL = 2*(point.LPos-LPrevPos)/(LVMax + LPrevV);
            double maybeNewdtR = 2*(point.RPos-RPrevPos)/(RVMax + RPrevV);
            double maybeNewLAccel = Math.min((LVMax-LPrevV)/maybeNewdtL, AMax);
            double maybeNewRAccel = Math.min((RVMax-RPrevV)/maybeNewdtR, AMax);
            double maybeNewRV = Math.sqrt(Math.abs(2*maybeNewRAccel*(point.RPos-RPrevPos) + RPrevV*RPrevV));
            double maybeNewLV = Math.sqrt(Math.abs(2*maybeNewLAccel*(point.LPos-LPrevPos) + LPrevV*LPrevV));
            maybeNewdtL = (point.LPos - LPrevPos)/(maybeNewLV + LPrevV)*2;
            maybeNewdtR = (point.RPos - RPrevPos)/(maybeNewRV + RPrevV)*2;
            double ratio = (point.RPos-RPrevPos)/(point.LPos-LPrevPos);
            
            if(maybeNewLAccel >= maybeNewRAccel) {
                Lv = maybeNewLV;
                Rv = maybeNewLV * ratio; 
                deltaT = 2*(point.LPos - LPrevPos)/(maybeNewLV + LPrevV); 
                if(Rv > maybeNewRV) {
                    Rv = maybeNewRV;
                    deltaT = maybeNewdtR;
                    Lv = Rv * 1/ratio;
                }
            }
            else {
                Rv = maybeNewRV;
                Lv = maybeNewRV * 1/ratio;
                deltaT = 2*(point.RPos - RPrevPos)/(maybeNewRV + RPrevV); 
                if(Lv > maybeNewLV) {
                    Lv = maybeNewLV;
                    deltaT = maybeNewdtL;
                    Rv = Lv * ratio;
                }
            }
            
            Laccel = 0.5*(Lv+LPrevV)*(Lv-LPrevV)/(point.LPos-LPrevPos); 
            Raccel = 0.5*(Rv+RPrevV)*(Rv-RPrevV)/(point.RPos-RPrevPos); 

            time += deltaT; 
            ProfilePoint newPoint = new ProfilePoint(time, point.LPos, point.RPos, Lv, Rv, Laccel, Raccel); 
            fromLeft.add(newPoint);
            RPrevV = Rv;
            LPrevV = Lv;
            LPrevPos = point.LPos;
            RPrevPos = point.RPos; 

            // max velocity test
            boolean hope = false;
            if(Lv <= Math.abs(point.LMaxVelocity+.0001) && Rv <= Math.abs(point.RMaxVelocity +.0001)) {
                hope = true; 
            }
            if(!hope) {
                count++;
            }
            // velocity ratio test 
            if(ratio >= Rv/Lv + .0001) {
                countR++; 
            }
        }
        System.out.println("The profile failed the max velocity test " + count + " times.");
        System.out.println("The profile failed the velocity ratio test " + countR + " times."); 
        test(fromLeft); 
    }

    public void generateFromRight() {
        ProfilePoint endpoint = fromLeft.get(fromLeft.size()-1); 
        double LPrevPos = endpoint.Lpos;
        double RPrevPos = endpoint.Rpos;
        double LPrevV = 0;
        double RPrevV = 0;
        double time = 0;
        int count = 0;
        int countR = 0;
        
        endpoint.time = 0;
        endpoint.Lv = 0;
        endpoint.Rv = 0;
        endpoint.La = -300;
        endpoint.Ra = -300;
        fromRight.add(endpoint);

        int negDtCount = 0;
        for (int i=fromLeft.size()-2; i>0; i--) {
            ProfilePoint point = fromLeft.get(i); 
            double LVDesired = point.Lv; 
            double RVDesired = point.Rv; 
            
            double deltaT;
            double Laccel;
            double Raccel;
            double Lv;
            double Rv; 

            double maybeNewdtL = -Math.abs(2*(LPrevPos-point.Lpos)/(LVDesired + LPrevV));
            double maybeNewdtR = -Math.abs(2*(RPrevPos-point.Rpos)/(RVDesired + RPrevV));
            double maybeNewLAccel = Math.max((LVDesired-LPrevV)/maybeNewdtL, -AMax);
            double maybeNewRAccel = Math.max((RVDesired-RPrevV)/maybeNewdtR, -AMax);
            double maybeNewRV = Math.sqrt(Math.abs(2*maybeNewRAccel*(point.Rpos-RPrevPos) + RPrevV*RPrevV));
            double maybeNewLV = Math.sqrt(Math.abs(2*maybeNewLAccel*(point.Lpos-LPrevPos) + LPrevV*LPrevV));
            maybeNewdtL = -Math.abs((LPrevPos-point.Lpos)/(maybeNewLV + LPrevV)*2);
            maybeNewdtR = -Math.abs((LPrevPos-point.Lpos)/(maybeNewRV + RPrevV)*2);
            double ratio = (RPrevPos-point.Rpos)/(LPrevPos-point.Lpos);

            if(maybeNewLAccel >= maybeNewRAccel) {
                Lv = maybeNewLV;
                Rv = maybeNewLV * ratio; 
                deltaT = 2*(point.Lpos - LPrevPos)/(maybeNewLV + LPrevV); 
                if(Rv > maybeNewRV) {
                    Rv = maybeNewRV;
                    deltaT = maybeNewdtR;
                    Lv = Rv * 1/ratio;
                }
            }
            else {
                Rv = maybeNewRV;
                Lv = maybeNewRV * 1/ratio;
                deltaT = 2*(point.Rpos - RPrevPos)/(maybeNewRV + RPrevV); 
                if(Lv > maybeNewLV) {
                    Lv = maybeNewLV;
                    deltaT = maybeNewdtL;
                    Rv = Lv * ratio;
                }
            }
            if(deltaT > 0) {
                negDtCount++;
            }
            Laccel = 0.5*(Lv+LPrevV)*(Lv-LPrevV)/(point.Lpos-LPrevPos); 
            Raccel = 0.5*(Rv+RPrevV)*(Rv-RPrevV)/(point.Rpos-RPrevPos); 

            time += deltaT; 
            ProfilePoint newPoint = new ProfilePoint(time, point.Lpos, point.Rpos, Lv, Rv, Laccel, Raccel); 
            fromRight.add(newPoint);
            RPrevV = Rv;
            LPrevV = Lv;
            LPrevPos = point.Lpos;
            RPrevPos = point.Rpos; 

            // velocity ratio test 
            if(ratio >= Rv/Lv + .0001) {
                countR++; 
            }
        }
        reorder(); 
        System.out.println("The profile failed the max velocity test " + count + " times.");
        System.out.println("The profile failed the velocity ratio test " + countR + " times."); 
        System.out.println("The profile had a positive dt " + negDtCount + " times."); 
        test(combinedProfile);
    }

    public void reorder() {
        // fix the starting point & set the totalTime
        ProfilePoint point1 = fromRight.get(fromRight.size()-1);
        ProfilePoint point2 = fromRight.get(fromRight.size()-2);
        double timeFrom1to2 = point2.time-point1.time;
        point1.Lv = 2*(point2.Lpos - point1.Lpos)/timeFrom1to2 - point2.Lv;
        point1.Rv = 2*(point2.Rpos - point1.Rpos)/timeFrom1to2 - point2.Rv;
        double totalTime = point1.time;
        point1.time = 2*point1.Lpos/point1.Lv; 
        totalTime -= point1.time;

        // add points in reverse order to combinedProfile (and fix times)
        combinedProfile.add(point1); 
        for(int i=fromRight.size()-2; i>=0; i--) {
            ProfilePoint point = fromRight.get(i);
            point.time = -totalTime + point.time; 
            combinedProfile.add(point);
        }
    }

    public void test(ArrayList<ProfilePoint> testProfile) {
        double prevT = 0;
        double prevLV = 0;
        double prevRV = 0;
        double prevLPos = 0;
        double prevRPos = 0;
        int Vcount = 0;
        int Acount = 0;
        int Pcount = 0;
        for(int i=0; i<testProfile.size(); i++) {
            ProfilePoint point = testProfile.get(i);
            boolean posGood = false;
            boolean velGood = false;
            boolean accGood = false;

            double dt = point.time - prevT;
            if(Math.abs(point.Rpos - (point.Rv*dt + prevRPos)) <= 0.01 && Math.abs(point.Lpos - (point.Lv*dt + prevLPos)) <= 0.01){
                posGood = true; 
            }
            if(Math.abs(point.Rv - (point.Ra*dt + prevRV)) <= 0.01 && Math.abs(point.Lv - (point.La*dt + prevLV)) <= 0.01) {
                velGood = true; 
            }
            if(testProfile == fromLeft) {
                if(point.La <= 301 && point.Ra <= 301) {
                    accGood = true;
                }
            }
            else {
                if(point.La <= 301 && point.La >= -301 && point.Ra <= 301 && point.Ra >= -301) {
                    accGood = true;
                }
            }
            if(!accGood) {
                Acount++;
                //System.out.println("A bad: " + i);
                badAPoints.add(i);
            }
            if(!posGood) {
                Pcount++;
                //System.out.println("P bad: " + i);
            }
            if(!velGood) {
                Vcount ++;
                //System.out.println("V bad: " + i);
            }

            prevT = point.time;
            prevLV = point.Lv;
            prevRV = point.Rv;
            prevLPos = point.Lpos;
            prevRPos = point.Rpos;
        }
        System.out.println("Failed velocity: " + Vcount + ", failed accel: " + Acount + ", failed pos: " + Pcount);
    }

    public boolean isReasonable() {
        // check the bad acceleration points 
        int prevIndex = 0;
        for(int index: badAPoints) {
            if(index == prevIndex + 1) {
                // error lasts for longer than 1 point -- bad
                // To do: decide a margin that is good enough to fix and fix those, or else return false
                System.out.println("Acceleration error lasted for longer than 1 point, starting at: " + prevIndex);
                return false;
            }
            else {
                prevIndex = index;
            }
        }
        // what else do we need to check???? 

        // confidence is key 
        return true; 
    }

    public void shrink() {
        int multiple = 1;
        double prevDifference = dt-combinedProfile.get(0).time;
        for(int i=1; i<combinedProfile.size()-1; i++) {
            ProfilePoint point = combinedProfile.get(i);
            double difference = Math.abs(multiple*dt-point.time);
            if(difference < prevDifference) {
                prevDifference = difference;
            }
            else {
                finalProfile.add(combinedProfile.get(i-1)); 
                multiple++; 
                prevDifference = Math.abs(multiple*dt-point.time);
            }
        }
    }

    public void writeProfile() {
        String fileL = "src/main/java/resources/LeftTestFile.csv";
        String fileR = "src/main/java/resources/RightTestFile.csv"; 
        TextFileWriter writerL = new TextFileWriter(fileL);
        TextFileWriter writerR = new TextFileWriter(fileR);
        
        for(int i=0; i<finalProfile.size(); i++) {
            ProfilePoint stats = finalProfile.get(i);
            
            // not sure what these will actually be till make profile
            String Lpos = Double.toString(stats.Lpos);
            String Rpos = Double.toString(stats.Rpos);
            Double LV = stats.Lv; 
            Double RV = stats.Rv;
            Double LA = stats.La;
            Double RA = stats.Ra; 
            
            String contentL = stats.time + "," + Lpos + "," + LV + ","  + LA;
            String contentR = stats.time + "," +  Rpos + "," + RV + ","  + RA;
            writerL.writeLine(contentL);
            writerR.writeLine(contentR);
        }
        writerL.closeFile();
        writerR.closeFile();
    }
}
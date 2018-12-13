package org.usfirst.frc.team1700.robot.autoTest;

import org.usfirst.frc.team1700.robot.motionprofiling.path.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class GeneratePathTest {   
    String fileL = "src/main/resources/LeftTestStraight.csv";
    String fileR = "src/main/resources/RightTestStraight.csv";
    Waypoint waypoint1 = new Waypoint(0, 0, 0);
    Waypoint waypoint2 = new Waypoint(100, 100, 0);          

    GeneratePath generatePath = new GeneratePath();
    PathProcessor pathProcessor = new PathProcessor(100, 26); // check what track width actually is 
    PathToProfile pathToProfile = new PathToProfile(fileL, fileR); 
    ArrayList<PathPoint> path;  

    @Test
    public void testFun(){
        System.out.println("hello");
        path = generatePath.generatePath(waypoint1, waypoint2);
        path = pathProcessor.processPath(path);
        System.out.println("hello2");
        pathToProfile.generateProfile(path); 
    }

}
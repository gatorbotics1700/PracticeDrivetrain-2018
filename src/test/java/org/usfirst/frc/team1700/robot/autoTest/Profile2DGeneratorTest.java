package org.usfirst.frc.team1700.robot.autoTest;

import org.usfirst.frc.team1700.robot.reese.path.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class Profile2DGeneratorTest {   
    String fileL = "src/main/java/resources/LeftTestCurve.csv";
    String fileR = "src/main/java/resources/RightTestCurve.csv"; 

    GeneratePath generatePath = new GeneratePath();
    PathProcessor pathProcessor = new PathProcessor(100, 26); // check what track width actually is 
    PathToProfile pathToProfile = new PathToProfile(fileL, fileR); 
    ArrayList<PathPoint> path;  

    @Test
    public void testFun(){
        path = generatePath.generatePath();
        path = pathProcessor.processPath(path);
        pathToProfile.generateProfile(path); 
    }

}
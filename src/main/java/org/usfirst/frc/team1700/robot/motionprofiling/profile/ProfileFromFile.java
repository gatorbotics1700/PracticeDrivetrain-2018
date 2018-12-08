package org.usfirst.frc.team1700.robot.motionprofiling.profile;

import org.usfirst.frc.team1700.robot.motionprofiling.util.TextFileReader;
import org.usfirst.frc.team1700.robot.motionprofiling.profile.Profile;

import java.util.ArrayList;
import java.util.StringTokenizer;


public class ProfileFromFile{
    String fileNameL; 
    String fileNameR;
    String fileName;
    int numFiles;

    public ProfileFromFile() {
    }
    
    public ProfileFromFile(String fileNameL, String fileNameR) {
        this.fileNameL = fileNameL;
        this.fileNameR = fileNameR;
        numFiles = 2;
    }

    public ProfileFromFile(String fileName) {
        this.fileName = fileName;
        numFiles = 1;
    }

    public Profile load2DProfile_2Files() {
        Profile profile = new Profile();
        ArrayList<ProfilePoint> profileL = new ArrayList<ProfilePoint>();
        ArrayList<ProfilePoint> profileR = new ArrayList<ProfilePoint>();
        // Left
        TextFileReader readerL = new TextFileReader(fileNameL);
        String fileL = readerL.readWholeFile(); 
        
        StringTokenizer tokenizerL = new StringTokenizer(fileL, "\n");
        while(tokenizerL.hasMoreTokens()) {
            StringTokenizer line_tokenizerL = new StringTokenizer(tokenizerL.nextToken(), ", ");
            
            double position = Double.parseDouble(line_tokenizerL.nextToken());
            double velocity = Double.parseDouble(line_tokenizerL.nextToken());
            // fix this so dt is actually the time and acceleration is right 
            double dt = Double.parseDouble(line_tokenizerL.nextToken());
            double acceleration = 0; 
            ProfilePoint stats = new ProfilePoint(dt, position, velocity, acceleration);

            profileL.add(stats);
        }
        profile.add(profileL);

        // Right
        TextFileReader readerR = new TextFileReader(fileNameR);
        String fileR = readerR.readWholeFile(); 
        
        StringTokenizer tokenizerR = new StringTokenizer(fileR, "\n");

        while(tokenizerR.hasMoreTokens()) {
            StringTokenizer line_tokenizerR = new StringTokenizer(tokenizerR.nextToken(), ", ");
            
            double position = Double.parseDouble(line_tokenizerR.nextToken());
            double velocity = Double.parseDouble(line_tokenizerR.nextToken());
            // fix dt and acceleration
            double dt = Double.parseDouble(line_tokenizerR.nextToken());
            double acceleration = 0;
            ProfilePoint stats = new ProfilePoint(dt, position, velocity, acceleration);
            profileR.add(stats);
        }
        profile.add(profileR);
        return profile;
    }

    public Profile load2DProfile_1File() {
        Profile profile = new Profile();
        ArrayList<ProfilePoint> profileL = new ArrayList<ProfilePoint>();
        ArrayList<ProfilePoint> profileR = new ArrayList<ProfilePoint>();
        TextFileReader reader = new TextFileReader(fileName);
        String file = reader.readWholeFile(); 
        
        StringTokenizer tokenizer = new StringTokenizer(file, "\n");

        while(tokenizer.hasMoreTokens()) {
            StringTokenizer line_tokenizer = new StringTokenizer(tokenizer.nextToken(), ", ");
            
            double time = Double.parseDouble(line_tokenizer.nextToken());
            double LPos = Double.parseDouble(line_tokenizer.nextToken());
            double RPos = Double.parseDouble(line_tokenizer.nextToken());
            double Lv = Double.parseDouble(line_tokenizer.nextToken());
            double Rv = Double.parseDouble(line_tokenizer.nextToken());
            double La = Double.parseDouble(line_tokenizer.nextToken());
            double Ra = Double.parseDouble(line_tokenizer.nextToken());

            ProfilePoint statsL = new ProfilePoint(time, LPos, Lv, La);
            ProfilePoint statsR = new ProfilePoint(time, RPos, Rv, Ra);

            profileL.add(statsL);
            profileR.add(statsR);
        }
        profile.add(profileL);
        profile.add(profileR);
        return profile;
    }

    public Profile load1DProfile() {
        Profile profile = new Profile();
        ArrayList<ProfilePoint> profilePart = new ArrayList<ProfilePoint>();
        TextFileReader reader = new TextFileReader(fileName);
        String file = reader.readWholeFile(); 
        
        StringTokenizer tokenizer = new StringTokenizer(file, "\n");

        while(tokenizer.hasMoreTokens()) {
            StringTokenizer line_tokenizer = new StringTokenizer(tokenizer.nextToken(), ", ");
            
            double time = Double.parseDouble(line_tokenizer.nextToken());
            double pos = Double.parseDouble(line_tokenizer.nextToken());
            double V = Double.parseDouble(line_tokenizer.nextToken());
            double A = Double.parseDouble(line_tokenizer.nextToken());

            ProfilePoint stats = new ProfilePoint(time, pos, V, A);

            profilePart.add(stats);
        }
        profile.add(profilePart);
        return profile;
    }

    public void specifyFiles(String fileL, String fileR) {
        this.fileNameL = fileL;
        this.fileNameR = fileR; 
    }
}
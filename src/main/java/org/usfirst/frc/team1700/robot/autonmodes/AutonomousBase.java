/* This class implements the camera to be used in autonomous via the IP Camera. 
 */
package org.usfirst.frc.team1700.robot.autonmodes;

import java.time.Duration;
import java.time.Instant;
import edu.wpi.first.wpilibj.DriverStation;

public class AutonomousBase{	

    // Auto game data / decision making variables

    public enum StartLocation {
        LEFT,
        CENTER,
        RIGHT;
    }

    protected enum GameDataState {
        WAITING,
        RECEIVED,
        TIMEOUT;
    }

    protected enum AutoDecisionState {
        SWITCH,
        SCALE,
        CROSS_SCALE,
        CROSS_LINE;
    }

    Instant start;
    GameDataState gameDataState = GameDataState.WAITING;
    AutoDecisionState autoDecisionState;
    AutoDecisionState crossSideDefault = AutoDecisionState.CROSS_SCALE;
    StartLocation startLocation;
    String gameData;
    Boolean preferSwitch;

    public AutonomousBase(StartLocation inputLocation, Boolean inputPreferSwitch) {
        startLocation = inputLocation;
        preferSwitch = inputPreferSwitch;
    }
    
    public void periodic(){
        
        if(gameDataState == GameDataState.WAITING){
            updateGameData();
            if(gameDataState == GameDataState.TIMEOUT){
                autoDecisionState = AutoDecisionState.CROSS_LINE;
            }else if(gameDataState == GameDataState.RECEIVED){
                // make decision here
                makeDecision();
            }
        }
        // gameDataState is update so this can check again (similar to else if)
        if(gameDataState != GameDataState.WAITING){
            
        }
    }

	protected void updateGameData(){
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        if (gameData.length() < 3) {
            // game data not received if length < 3
            gameData = DriverStation.getInstance().getGameSpecificMessage();
            if (Duration.between(start, Instant.now()).toMillis() > 100) {
                DriverStation.reportWarning("timed out :(", false);
                gameDataState = GameDataState.TIMEOUT;
            }
            else{
                // gameDataState still WAITING
            }
        }
        else{
            // Game data received
            gameDataState = GameDataState.RECEIVED;
        }
    }

    protected void makeDecision(){
        if(startLocation == StartLocation.LEFT){
            if(preferSwitch){
                if(gameData.charAt(0) == 'L'){
                    // Left side, left switch auto
                    autoDecisionState = AutoDecisionState.SWITCH;
                }
                else if(gameData.charAt(1) == 'L'){
                    // Left side, left scale auto
                    autoDecisionState = AutoDecisionState.SCALE;
                }
                else{
                    autoDecisionState = crossSideDefault;
                }
            }
            else{
                if(gameData.charAt(1) == 'L'){
                    // Left side, left scale auto
                    autoDecisionState = AutoDecisionState.SCALE;
                }
                else if(gameData.charAt(0) == 'L'){
                    // Left side, left switch auto
                    autoDecisionState = AutoDecisionState.SWITCH;
                }
                else{
                    autoDecisionState = crossSideDefault;
                }
            }
        }
        else if(startLocation == StartLocation.RIGHT){
            if(preferSwitch){
                if(gameData.charAt(0) == 'R'){
                    // Right side, right switch auto
                    autoDecisionState = AutoDecisionState.SWITCH;
                }
                else if(gameData.charAt(1) == 'R'){
                    // Right side, right scale auto
                    autoDecisionState = AutoDecisionState.SCALE;
                }
                else{
                    autoDecisionState = crossSideDefault;
                }
            }
            else{
                if(gameData.charAt(1) == 'R'){
                    // Right side, right scale auto
                    autoDecisionState = AutoDecisionState.SCALE;
                }
                else if(gameData.charAt(0) == 'R'){
                    // Right side, right switch auto
                    autoDecisionState = AutoDecisionState.SWITCH;
                }
                else{
                    autoDecisionState = crossSideDefault;
                }
            }
        }
        else{
            if(preferSwitch){
                autoDecisionState = AutoDecisionState.SWITCH;
            }
            else{
                // You probably don't want to do this (scale from center)
                autoDecisionState = AutoDecisionState.SCALE;
            }
        }
    }
}
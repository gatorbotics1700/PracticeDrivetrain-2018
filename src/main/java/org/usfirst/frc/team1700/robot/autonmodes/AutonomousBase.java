/* This class implements the camera to be used in autonomous via the IP Camera. 
 */
package org.usfirst.frc.team1700.robot.autonmodes;

import java.time.Duration;
import java.time.Instant;
import edu.wpi.first.wpilibj.DriverStation;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team1700.robot.subsystems.ElevatorSubsystem;
import org.usfirst.frc.team1700.robot.subsystems.IntakeSubsystem;

public class AutonomousBase{	

    // Auto game data / decision making variables

    public static enum StartLocation {
        LEFT,
        CENTER,
        RIGHT;
    }

    protected static enum GameDataState {
        WAITING,
        RECEIVED,
        TIMEOUT;
    }

    protected static enum AutoDecisionState {
        SWITCH,
        SCALE,
        CROSS_SCALE,
        CROSS_LINE;
    }

	private DriveSubsystem driveSubsystem;
	private ElevatorSubsystem elevatorSubsystem;
	private IntakeSubsystem intakeSubsystem;

    Instant start;
    GameDataState gameDataState = GameDataState.WAITING;
    AutoDecisionState autoDecisionState;
    AutoDecisionState crossSideDefault = AutoDecisionState.CROSS_SCALE;
    StartLocation startLocation;
    String gameData;
    Boolean preferSwitch;

	// Autonomous constants
	public static final long waitTime = 250; // milliseconds
	// All distances in inches 
	public static final double distToSwitch = 140;
	public static final double distToAutoLine = 110;
	public static final double finalDistToSwitch = 30; 
	public static final double finalDistToScale = 23; // with bad encoder: 55

	public static final double crossScaleDist1 = 210;
	public static final double crossScaleDist2 = 190;
	public static final double crossScaleDist3 = 57;
	
	public static final double sameScaleDist = 226; // with bad encoder: 280
	
	public static final double crossSwitchDist1 = 18;
	public static final double crossSwitchDist2 = 127;
	public static final double crossSwitchDist3 = 18;
	
	public static final double centerLeftDist1 = 50;
	public static final double centerLeftDist2 = 80;
	public static final double centerLeftDist3 = 20;
	
	public static final double centerRightDist1 = 50;
	public static final double centerRightDist2 = 70;
	public static final double centerRightDist3 = 25;
		
	public static final double centerDistBack = -18;
	
	public static final  double sameSwitchDist = 140;
	
	public static final  double left = -90;
    public static final  double right = 90;
       
    // Auto progression constants
	private static final double angleTolerance = 4;
    private static final double distanceTolerance = 3;
    private static final double velocityTolerance = 0.1;
    private static final double angleVelocityTolerance = 1;

	private static final double elevatorTolerance = 5; //change later

    // Autonomous variables
    // Drivetrain info
    double desiredAngle;
    double desiredDistance;
    double currentDistance;
    double currentVelocity;
    double currentAngle;
    double currentAngleVelocity;
    double distanceError;
    double angleError;

    // Elevator Info
    double desiredElevatorPosition;
    double currentElevatorPosition;
    double currentElevatorVelocity;
    double elevatorError; 

    public AutonomousBase(StartLocation inputLocation, Boolean inputPreferSwitch, DriveSubsystem drive, IntakeSubsystem intake, ElevatorSubsystem elevator) {
        startLocation = inputLocation;
        preferSwitch = inputPreferSwitch;
        driveSubsystem = drive;
        intakeSubsystem = intake;
        elevatorSubsystem = elevator;
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
        // gameDataState is updated so this can check again (similar to else if)
        if(gameDataState != GameDataState.WAITING){
            if(autoDecisionState == AutoDecisionState.CROSS_LINE){

            }
            else if(autoDecisionState == AutoDecisionState.SWITCH){

            }
            else if(autoDecisionState == AutoDecisionState.SCALE){

            }
            else{

            }
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
                    switchAutoInit();
                }
                else if(gameData.charAt(1) == 'L'){
                    // Left side, left scale auto
                    autoDecisionState = AutoDecisionState.SCALE;
                    scaleAutoInit();
                }
                else{
                    autoDecisionState = crossSideDefault;
                }
            }
            else{
                if(gameData.charAt(1) == 'L'){
                    // Left side, left scale auto
                    autoDecisionState = AutoDecisionState.SCALE;
                    scaleAutoInit();
                }
                else if(gameData.charAt(0) == 'L'){
                    // Left side, left switch auto
                    autoDecisionState = AutoDecisionState.SWITCH;
                    switchAutoInit();
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
                // Center switch autons not implemented
            }
            else{
                // Center scale autons not implemented
            }
        }
    }

    protected enum CrossTheLineStates{
        DRIVING,
        END;
    }
    // crossTheLineAuto parameters
    CrossTheLineStates crossTheLineState;
    protected void crossTheLineAutoInit(){
        crossTheLineState = CrossTheLineStates.DRIVING;
        desiredAngle = 0;
        desiredDistance = distToAutoLine;
    }
    protected void crossTheLineAutoPeriodic(){
        // Read sensors
        currentDistance = driveSubsystem.getRightEncoderValue();
        currentVelocity = driveSubsystem.getVelocity();
        currentAngle = driveSubsystem.getNavXAngle(DriveSubsystem.AngleType.YAW);
        currentAngleVelocity = 0;
        
        distanceError = currentDistance - desiredDistance;
        angleError = (desiredAngle - currentAngle + 720 + 180) % 360 - 180;

        // Update state
        if(crossTheLineState == CrossTheLineStates.DRIVING){
            if(Math.abs(distanceError)<distanceTolerance 
              && (Math.abs(angleError))<angleTolerance
              && (Math.abs(currentVelocity)) < velocityTolerance
              && (Math.abs(currentAngleVelocity) < angleVelocityTolerance)){
                  crossTheLineState = CrossTheLineStates.END;
            }
            else{
                // Stay in DRIVING state
            }
        }
        else{
            // Stay in END state
        }

        // Update commands
        if(crossTheLineState == CrossTheLineStates.DRIVING){
            driveTrainPID(distanceError, currentVelocity, angleError, currentAngleVelocity, minDrivePowerDuringDrive, minTurnPowerDuringTurn);
            Robot.leftIntakeSpeed = 0.5;
            Robot.rightIntakeSpeed = 0.3;
            Robot.elevatorSpeed = 0;
        }
        else{
            Robot.leftSpeed = 0;
            Robot.rightSpeed = 0;
            Robot.leftIntakeSpeed = 0.5;
            Robot.rightIntakeSpeed = 0.3;
            Robot.elevatorSpeed = 0;
        }

        // Execute commands are done in Robot main
    }

    
    protected static enum SwitchStates{
        DRIVING,
        TURNING,
        WAITING_FOR_ELEVATOR,
        SCORING,
        END;
    }
    SwitchStates switchState;
    protected void switchAutoInit(){
        crossTheLineState = CrossTheLineStates.DRIVING;
        desiredAngle = 0;
        desiredDistance = sameSwitchDist;
        desiredElevatorPosition = elevatorSubsystem.switchHeight;
    }

    protected void switchAutoPeriodic(){
        // Read sensors
        currentDistance = driveSubsystem.getRightEncoderValue();
        currentVelocity = driveSubsystem.getVelocity();
        currentAngle = driveSubsystem.getNavXAngle(DriveSubsystem.AngleType.YAW);
        currentAngleVelocity = 0;
        distanceError = currentDistance - desiredDistance;
        angleError = (desiredAngle - currentAngle + 720 + 180) % 360 - 180;
        
        currentElevatorPosition = elevatorSubsystem.getCurrentPos();
        currentElevatorVelocity = elevatorSubsystem.getVelocity();
        elevatorError = desiredElevatorPosition - currentElevatorPosition;

        // Update state
        if(switchState == SwitchStates.DRIVING){
            if(Math.abs(distanceError)<distanceTolerance 
                && (Math.abs(angleError))<angleTolerance
                && (Math.abs(currentVelocity)) < velocityTolerance
                && (Math.abs(currentAngleVelocity) < angleVelocityTolerance)){
                    switchState = SwitchStates.TURNING;
                    if(startLocation == StartLocation.LEFT){
                        desiredAngle = right;
                    }
                    else if(startLocation == StartLocation.RIGHT){
                        desiredAngle = left;
                    }
            }
            else{
                // Stay in DRIVING state
            }
        }
        else if(switchState == SwitchStates.TURNING){
            if((Math.abs(angleError))<angleTolerance
                && (Math.abs(currentVelocity)) < velocityTolerance
                && (Math.abs(currentAngleVelocity) < angleVelocityTolerance)){
                    if(Math.abs(elevatorError) < elevatorTolerance){
                        switchState = SwitchStates.SCORING;
                        driveSubsystem.resetEncoders();
                        desiredDistance = finalDistToSwitch;
                    }
                    else{
                        switchState = SwitchStates.WAITING_FOR_ELEVATOR;
                    }
            }
            else{
                // Stay in TURNING state
            }
        }
        else if(switchState == SwitchStates.WAITING_FOR_ELEVATOR){
            if(Math.abs(elevatorError) < elevatorTolerance){
                switchState = SwitchStates.SCORING;
                driveSubsystem.resetEncoders();
                desiredDistance = finalDistToSwitch;
            }
            else{
                // Stay in WAITING_FOR_ELEVATOR
            }
        }
        else if(switchState == SwitchStates.SCORING){
            if(Math.abs(distanceError)<distanceTolerance 
            && (Math.abs(angleError))<angleTolerance
            && (Math.abs(currentVelocity)) < velocityTolerance
            && (Math.abs(currentAngleVelocity) < angleVelocityTolerance)){
                switchState = SwitchStates.END;
            }
        }
        else{
            // Stay in END state
        }

        if(switchState == SwitchStates.DRIVING){
            driveTrainPID(distanceError, currentVelocity, angleError, currentAngleVelocity, minDrivePowerDuringDrive, minTurnPowerDuringDrive);
            elevatorPID(currentElevatorPosition, currentElevatorVelocity);
            Robot.leftIntakeSpeed = 0.5;
            Robot.rightIntakeSpeed = 0.3;
        }
        else if(switchState == SwitchStates.TURNING){
            driveTrainPID(0, 0, angleError, currentAngleVelocity, minDrivePowerDuringTurn, minTurnPowerDuringTurn);
            elevatorPID(currentElevatorPosition, currentElevatorVelocity);
            Robot.leftIntakeSpeed = 0.5;
            Robot.rightIntakeSpeed = 0.3;
        }
        else if(switchState == SwitchStates.WAITING_FOR_ELEVATOR){
            Robot.leftSpeed = 0;
            Robot.rightSpeed = 0;
            elevatorPID(elevatorError, currentElevatorVelocity);
            Robot.leftIntakeSpeed = 0.5;
            Robot.rightIntakeSpeed = 0.3;
        }
        else if(switchState == SwitchStates.SCORING){
            driveTrainPID(distanceError, currentVelocity, angleError, currentAngleVelocity, minDrivePowerDuringDrive, minTurnPowerDuringDrive);
            elevatorPID(elevatorError, currentElevatorVelocity);
            Robot.leftIntakeSpeed = 0.5;
            Robot.rightIntakeSpeed = 0.3;   
        }
        else{
            Robot.leftSpeed = 0;
            Robot.rightSpeed = 0;
            elevatorPID(elevatorError, currentElevatorVelocity);
            Robot.leftIntakeSpeed = -1;
            Robot.rightIntakeSpeed = -1;
        }
    }

    protected static enum ScaleStates{
        DRIVING,
        TURNING,
        WAITING_FOR_ELEVATOR,
        SCORING,
        END;
    }
    ScaleStates scaleState;
    protected void scaleAutoInit(){
        return;
    }
    protected void scaleAuto(){
        return;
    }

    protected static enum CrossScaleStates{
        DRIVING,
        TURNING,
        CROSSING,
        WAITING_FOR_ELEVATOR,
        SCORING,
        END;
    }
    CrossScaleStates crossScaleState;
    protected void crossScaleAutoInit(){
        return;
    }
    protected void crossScaleAuto(){
        return;
    }

    // Drivetrain PID constants
    private static final double turningAngleProportion = 0.01;
    private static final double maxAngleSpeed = 0.4;
    private static final double driveD = -0.005;
    private static final double driveP = 0.006;
    private static final double maxDistanceSpeed = 0.8;

    private static final double minDrivePowerDuringTurn = 0;
    private static final double minDrivePowerDuringDrive = 0.075;
    private static final double minTurnPowerDuringTurn = 0.4;
    private static final double minTurnPowerDuringDrive = 0;

    private void driveTrainPID(double distanceError, double distanceVelocity, double angleError, double angleVelocity, double minDriveVoltage, double minTurnVoltage){
        // ANGLE SPEED
        double calcAngleSpeed = turningAngleProportion*angleError;
        double angleSpeed;
    	if (calcAngleSpeed > maxAngleSpeed) {
    		angleSpeed = maxAngleSpeed;
    	} else if (calcAngleSpeed < -maxAngleSpeed) {
    		angleSpeed = -maxAngleSpeed;
    	} else {
    		angleSpeed = Math.copySign(Math.max(minTurnVoltage, Math.abs(calcAngleSpeed)), angleError);
    	}
    	
    	// DISTANCE SPEED
    	double calcDistSpeed = driveP*distanceError + driveD*distanceVelocity;
        double distanceSpeed;
    	if (calcDistSpeed > maxDistanceSpeed) {
    		distanceSpeed = maxDistanceSpeed;
    	} else if (calcDistSpeed < -maxDistanceSpeed) {
    		distanceSpeed = -maxDistanceSpeed;
    	} else {
    		distanceSpeed = Math.copySign(Math.max(minDriveVoltage, Math.abs(calcDistSpeed)), distanceError);
    	}
    	
    	Robot.leftSpeed = -(distanceSpeed + angleSpeed);
    	Robot.rightSpeed = -(distanceSpeed - angleSpeed);
    }

    // Elevator PID constants
	double maxSpeed = 0.88;
	double elevatorP = 0.01; // not tuned
	double elevatorD = 0.000; // not tuned
	
    private void elevatorPID(double elevatorError, double elevatorVelocity){
        double calcVel = -elevatorP*elevatorError + elevatorD*elevatorVelocity;
    	
    	if (Math.abs(elevatorError) <= elevatorTolerance) {
    		Robot.elevatorSpeed = elevatorSubsystem.stallSpeed;
        } 
        else if (calcVel > maxSpeed) {
    		Robot.elevatorSpeed = maxSpeed;
        } 
        else if (calcVel < -maxSpeed) {
    		Robot.elevatorSpeed = -maxSpeed;
        } 
        else {
    		Robot.elevatorSpeed = calcVel;
    	}
    }
}
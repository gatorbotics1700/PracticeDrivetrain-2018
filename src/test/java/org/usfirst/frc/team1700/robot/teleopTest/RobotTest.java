package org.usfirst.frc.team1700.robot.teleopTest;

import org.junit.jupiter.api.Test;
import org.usfirst.frc.team1700.robot.Robot;
import org.junit.jupiter.api.BeforeEach;

public class RobotTest {
  private Robot robot;
  
  @BeforeEach
  public void setUp(){
      System.out.println("setup function");
  }

    @Test
    public void testFun(){
        System.out.println("This means it runs");
    }
}
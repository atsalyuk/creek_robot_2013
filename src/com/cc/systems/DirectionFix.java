/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cc.systems;

import edu.wpi.first.wpilibj.Gyro;

/**
 *
 * @author dillon
 */
public class DirectionFix 
{
  private static DirectionFix INSTANCE=null;
  private double robot_direction;
  private Gyro gyro;
  
  private DirectionFix()
  {
      
  }
  
  public static DirectionFix getInstance()
  {
      if(INSTANCE==null)
          INSTANCE=new DirectionFix();
      
      return INSTANCE;
  }
   
 
  public void initGyro(Gyro gyro)
  {
      this.gyro=gyro;
  }
  
  
  public double leftOffset()
  {
      return 0.0; //temp value
  }
    
  public double rightOffset()
  {
      return 0.0; //temp value
  }
  
}

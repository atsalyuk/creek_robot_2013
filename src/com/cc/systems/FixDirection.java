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
public class FixDirection 
{
  private Gyro gyro;
  private double knownDirection;
  private double leftOffSet;
  private double rightOffSet; 
  
  public FixDirection(Gyro gyro)
  {
      this.gyro=gyro;
  }
  
  public double getKnownDirection()
  {
      return knownDirection;
  }
  
  public void setKnownDirection(double direction)
  {
      knownDirection=direction;
  }
  
  public void checkDirection()  //negative is motion to the left positive to the left
  {
    double diff=knownDirection-gyro.getAngle();
    if(diff>0 && Math.abs(diff)>=5)
    {
        leftOffSet=0.05;
        rightOffSet=-0.05;
    }
    else if(diff<0 && Math.abs(diff)>=5)
    {
        leftOffSet=-0.05;
        rightOffSet=0.05;
    }
  }
  
  public double getLeftOffset()
  {
      return leftOffSet;
  }
  
  public double getRightOffset()
  {
      return rightOffSet;
  }
}

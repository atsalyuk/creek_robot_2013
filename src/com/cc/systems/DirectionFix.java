/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cc.systems;

/**
 *
 * @author dillon
 */
public class DirectionFix 
{
  private DirectionFix INSTANCE;
  
  private DirectionFix()
  {
      
  }
  
  public DirectionFix getInstance()
  {
      if(INSTANCE==null)
          INSTANCE=new DirectionFix();
      
      return INSTANCE;
  }
    
    
}

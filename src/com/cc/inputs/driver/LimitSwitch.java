/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cc.inputs.driver;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author TestingMachine
 */
public class LimitSwitch
{
    
    DigitalInput di = null;
    
    public LimitSwitch(int channel)
    {
        di = new DigitalInput(channel);
        
    }
    
    public boolean get()
    {
     return di.get();   
        
    }
    
}

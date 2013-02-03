/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cc.systems;

import com.cc.outputs.motor.CCVictor;
import com.cc.inputs.digitalInputs.LimitSwitch;

/**
 *
 * @author Henry
 */
public class Mechanism
{
    private static Mechanism INSTANCE= null;
    CCVictor armMotor = null;
    LimitSwitch armSwitch = null;
    
    
    private Mechanism()
    {
        armMotor = new CCVictor ( 6, false );
        armSwitch = new LimitSwitch ( 1 );
        
    }
    
    public static Mechanism getInstance()
    {
        if ( INSTANCE == null )
        {
            INSTANCE = new Mechanism();       
        }
        
        return INSTANCE;
    }
    
    public void moveArm( double rotVal )
    {
        armMotor.set( rotVal );
        //Needs more work.
      
    }
    
    public void stopArm()
    {
        armMotor.set( 0.0 );
    }
    
}

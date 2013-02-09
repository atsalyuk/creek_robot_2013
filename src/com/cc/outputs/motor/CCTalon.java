/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cc.outputs.motor;

import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author Henry
 */
public class CCTalon extends Talon
{
    
    private boolean reversed = false;
    
    public CCTalon ( int channel, boolean reversed )
    {
        super( channel );
        this.reversed = reversed;
    }
    
    public void set( double speed )
    {
        if( reversed )
        {
            super.set ( speed * -1.0 );
        }
        else
        {
            super.set( speed );
        }
    }
}

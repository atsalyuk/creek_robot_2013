/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cc.outputs.motor;

import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author Dillon
 */
public class CCVictor extends Victor
{

    private boolean reversed = false;

    public CCVictor( int channel, boolean reversed )
    {
        super( channel );
        this.reversed = reversed;
    }

    public void set( double speed )
    {
        if ( reversed )
        {
            super.set( speed * -1.0 );
        }
        else
        {
            super.set( speed );
        }
    }
}

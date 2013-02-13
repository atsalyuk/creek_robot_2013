/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cc.inputs.digitalInputs;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Henry
 */
public class Sonar
{

    DigitalOutput trigger;
    DigitalInput echo;
    private static final double SOUNDSPEED = 0.0;

    public Sonar( int triggerChannel, int echoChannel )
    {
        trigger = new DigitalOutput( triggerChannel );
        echo = new DigitalInput( echoChannel );
    }

    public double getDistanceInches()
    {
        double timeTaken = 0.0;

        trigger.set( true );

        while ( !echo.get() )
        {
            timeTaken += 0.001;
            Timer.delay( 0.001 );
        }
        
        return ( timeTaken*SOUNDSPEED )/2;
    }

    public double calibrate()
    {
        double timeTaken = 0.0;

        trigger.set( true );

        while ( !echo.get() )
        {
            timeTaken += 0.001;
            Timer.delay( 0.001 );
        }

        return timeTaken;
    }
}

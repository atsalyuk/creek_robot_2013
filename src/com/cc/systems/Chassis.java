/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cc.systems;

import com.cc.outputs.motor.CCVictor;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Watchdog;

/**
 *
 * @author Adam
 */
public class Chassis
{

    private static Chassis INSTANCE = null;
    CCVictor leftMotor1 = null;
    CCVictor rightMotor1 = null;

    private Chassis()
    {
        leftMotor1 = new CCVictor( 9, true );
        rightMotor1 = new CCVictor( 10, false );
    }

    public static Chassis getInstance()
    {
        if ( INSTANCE == null )
        {
            INSTANCE = new Chassis();
        }

        return INSTANCE;
    }

    public void drive( double xVal, double yVal )
    {
        double tmpLeft = yVal;

        tmpLeft += xVal;
        tmpLeft = normalize( tmpLeft );

        leftMotor1.set( tmpLeft );

        double tmpRight = yVal;

        tmpRight += xVal * -1.0;
        tmpRight = normalize( tmpRight );

        rightMotor1.set( tmpRight );

//        System.out.println( "x: " + xVal + "  y: " + yVal + "  left: " + tmpLeft + "   rgt: " + tmpRight );
//        Timer.delay( 0.5 );
    }

    private double normalize( double val )
    {
        if ( val < -1.0 )
        {
            return -1.0;
        }
        else if ( val > 1.0 )
        {
            return 1.0;
        }
        else
        {
            return val;
        }
    }

    public void stop()
    {
        leftMotor1.set( 0.0 );
        rightMotor1.set( 0.0 );
    }

    /*
     * Will send maximum and minimum values to Jaguars continuosly
     */
    public void calibrate()
    {
        Watchdog.getInstance().setEnabled( false );

        drive( -1.0, -1.0 );
        Timer.delay( 5.0 );
        drive( 1.0, 1.0 );
        Timer.delay( 5.0 );
    }
}

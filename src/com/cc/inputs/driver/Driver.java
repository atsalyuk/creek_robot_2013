/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cc.inputs.driver;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author Adam
 */
public class Driver
{

    private static Driver INSTANCE = null;
    private final Joystick joy;
    private static final double XCENTER = 0.008;
    private static final double YCENTER = 0.031;
    private static final double ROTCENTER = 0.016;
    private static final double XMIN = -0.641;
    private static final double XMAX = 0.654;
    private static final double YMIN = -0.570 - YCENTER;
    private static final double YMAX = 0.646 - YCENTER;
    private static final double ZMIN = -1.0;
    private static final double ZMAX = 1.0;
    private static final double ROTMIN = -0.648 - ROTCENTER;
    private static final double ROTMAX = 0.717 - ROTCENTER;
    private static final double XEXPO = 0.4;
    private static final double YEXPO = 0.4;
    private static final double ROTEXPO = 0.6;
    private static final double MAX_SPEED = 0.75;
    private static final double MIN_SPEED = -0.75;

    private Driver()
    {
        this.joy = new Joystick( 1 );
    }

    public static Driver getInstance()
    {
        if ( INSTANCE == null )
        {
            INSTANCE = new Driver();
        }

        return INSTANCE;
    }

    public double reduceSpeed( double speed )
    {
        if ( speed < MIN_SPEED )
        {
            return MIN_SPEED;
        }
        else if ( speed > MAX_SPEED )
        {
            return MAX_SPEED;
        }
        else
        {
            return speed;
        }
    }

    public double getY()
    {
        double y = normalize( joy.getAxis( Joystick.AxisType.kY ) - YCENTER, YMIN, YMAX ) * -1.0;
        y = reduceSpeed( y );
        y = expo( y, YEXPO );

        //System.out.print("y: " + y);

        return y;
    }

    public double getX()
    {
//        System.out.println(joy.getAxis(Joystick.AxisType.kX));

        double x = normalize( joy.getAxis( Joystick.AxisType.kX ) - XCENTER, XMIN, XMAX );
        x = reduceSpeed( x );
        x = expo( x, XEXPO );

//        System.out.println( " x: " + x );

        return x;
    }

    public double getZ()
    {
        // Invert the Z axis so that up on the stick is positive
        return normalize( joy.getAxis( Joystick.AxisType.kZ ), ZMIN, ZMAX ) * -1.0;
    }

    public double getRot()
    {
        double rot = normalize( joy.getRawAxis( 5 ), ROTMIN, ROTMAX ) * -1.0;

        rot = expo( rot, ROTEXPO );

//        System.out.println(" rot: " + rot);

        return rot;
    }

    // Normalize input values to -1.0 to 1.0
    private double normalize( double joyVal, double min, double max )
    {

        double retVal = 0.0;

        if ( joyVal < 0.0 )
        {
            retVal = Math.abs( joyVal ) / min;
        }
        else if ( joyVal > 0.0 )
        {
            retVal = Math.abs( joyVal ) / max;
        }

        if ( retVal < -1.0 )
        {
            retVal = -1.0;
        }
        else if ( retVal > 1.0 )
        {
            retVal = 1.0;
        }

        return retVal;
    }

    // Apply exponential function 
    private double expo( double x, double a )
    {
        return (a * (x * x * x) + (1 - a) * x);
    }

    public void getRawAxis()
    {
        for ( int i = 1; i <= 6; i++ )
        {
            print( "" + i + "", joy.getRawAxis( i ) );
        }
        System.out.println();
    }

    private void print( String str, double val )
    {
        System.out.print( " " + str + ":" + val );
    }

    public boolean getLeftFrontSw()
    {
        return joy.getRawButton( 1 );
    }

    public boolean getRightFrontSw()
    {
        return joy.getRawButton( 2 );
    }

    public void getRawButton()
    {
        for ( int i = 1; i <= 12; i++ )
        {
            print( "" + i + "", joy.getRawButton( i ) );
        }
        System.out.println();
    }

    private void print( String str, boolean val )
    {
        System.out.print( " " + str + ":" + val );
    }
}

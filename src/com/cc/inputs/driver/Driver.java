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
    private static final double XCENTER = 0.000;
    private static final double YCENTER = 0.016;
    private static final double ROTCENTER = 0.039;
    private static final double XMIN = -0.711;
    private static final double XMAX = 0.803;
    private static final double YMIN = -0.586 - YCENTER;
    private static final double YMAX = 0.685 - YCENTER;
    private static final double ZMIN = -1.0;
    private static final double ZMAX = 1.0;
    private static final double ROTMIN = -0.734 - ROTCENTER;
    private static final double ROTMAX = 0.882 - ROTCENTER;
    private static final double XEXPO = 0.2;
    private static final double YEXPO = 0.4;
    private static final double ROTEXPO = 0.0;
    private static final double XSCALE = 1.3;
    private static double maxSpeed = 1.0;
    private static double minSpeed = -1.0;
    //Left hand y-axis max 0.843
    //left hand y-axis min -0.406
    //Left switch facing away: false
    //Right switch facing away: false

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

    public double reduceSpeed( double speed, boolean xAxis )
    {
        if ( xAxis )
        {
            maxSpeed = XMAX;
            minSpeed = XMIN;
        }
        else
        {
            if ( joy.getRawButton( 2 ) )
            {
                maxSpeed = 0.75;
                minSpeed = -0.75;
            }
            else
            {
                maxSpeed = 0.4;
                minSpeed = -0.4;
            }
        }

        if ( speed < minSpeed )
        {
            return minSpeed;
        }
        else if ( speed > maxSpeed )
        {
            return maxSpeed;
        }
        else
        {
            return speed;
        }
    }

    public double getY()
    {
        double y = normalize( joy.getAxis( Joystick.AxisType.kY ) - YCENTER, YMIN, YMAX ) * -1.0;
        y = reduceSpeed( y, false );
        y = expo( y, YEXPO );

        //System.out.print("y: " + y);

        return y;
    }

    public double getX()
    {
//        System.out.println(joy.getAxis(Joystick.AxisType.kX));

        double x = normalize( joy.getAxis( Joystick.AxisType.kX ) - XCENTER, XMIN, XMAX );
        x = reduceSpeed( x, true );
        x = expo( x, XEXPO );

//        System.out.println( " x: " + x );

        return x * XSCALE;
    }

    public double getZ()
    {
        // Invert the Z axis so that up on the stick is positive
        return normalize( joy.getAxis( Joystick.AxisType.kZ ), ZMIN, ZMAX ) * -1.0;
    }

    public double getRot()
    {
        double rot = normalize( joy.getRawAxis( 5 ), ROTMIN, ROTMAX );

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
        return joy.getRawButton( 2 );
    }

    public boolean getRightFrontSw()
    {
        return joy.getRawButton( 3 );
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

    //1=red 4=black 
    public boolean getRedButton() //returns true if red button is pressed
    {
        return joy.getRawButton( 1 );
    }

    public boolean getBlackButton() //returns true if black button is pressed
    {
        return joy.getRawButton( 4 );
    }

    public boolean getLeftSwitch() //returns true if left switch towards driver
    {
        return joy.getRawButton( 2 );
    }

    public boolean getRightSwitch()
    {
        return joy.getRawButton( 3 );
    }
}

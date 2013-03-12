/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cc.systems;

import com.cc.outputs.motor.CCTalon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.Encoder;

/**
 *
 * @author Adam
 */
public class Chassis
{

    private static Chassis INSTANCE = null;
    CCTalon leftMotor1 = null;
    CCTalon rightMotor1 = null;
    Encoder encoder = null;
    Gyro gyro = null;
    private final static double TICKSPERINCH = 15.43; //For the Competition Robot
    private final static double Kp = 0.30;

    private Chassis()
    {
        leftMotor1 = new CCTalon( 4, false );
        rightMotor1 = new CCTalon( 3, true );
        encoder = new Encoder( 13, 14 );
        gyro = new Gyro( 1 );
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

        tmpLeft += xVal;// * -1.0;      
        tmpLeft = normalize( tmpLeft );

        leftMotor1.set( tmpLeft );

        double tmpRight = yVal;

        tmpRight += xVal * -1.0;
        tmpRight = normalize( tmpRight );

        rightMotor1.set( tmpRight );

//        System.out.println( "x: " + xVal + "  y: " + yVal + "  left: " + tmpLeft + "   rgt: " + tmpRight );
//        System.out.println( "left: " + leftMotor1.get() + " right: " + rightMotor1.get());
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
        leftMotor1.stopMotor();
        rightMotor1.stopMotor();
    }

    /*
     * Will send maximum and minimum values to Talons/Victors continuosly
     */
    public void calibrate()
    {
        Watchdog.getInstance().setEnabled( false );

        drive( -1.0, -1.0 );
        Timer.delay( 5.0 );
        drive( 1.0, 1.0 );
        Timer.delay( 5.0 );
    }

    public void driveDistance( double distanceToTravel, double speed, boolean useFeet )
    {
        double ticksToTravel;

        if ( useFeet )
        {
            ticksToTravel = (distanceToTravel * 12) * TICKSPERINCH;
        }
        else
        {
            ticksToTravel = distanceToTravel * TICKSPERINCH;
        }

        encoder.start();
        gyro.reset();

        while ( Math.abs( encoder.get() ) < ticksToTravel )
        {
            double angle = gyro.getAngle();

            this.drive( -angle * Kp, speed );
            Timer.delay( 0.05 );
//            System.out.println( "Encoder: " + encoder.get() );
        }

        this.stop();

        encoder.stop();
        encoder.reset();
    }

    private double maxNormalize( double val, double max )
    {
        if ( val < -max )
        {
            return -max;
        }
        else if ( val > max )
        {
            return max;
        }
        else
        {
            return val;
        }
    }

//    public void turnAngle( double angleToTurn, double speed )
//    {
//        boolean done = false;
//        double tKp = 0.025;
//        double Ki = 0.002;
//        double errorSum = 0.0;
//        Timer timer = new Timer();
//
//        gyro.reset();
//        timer.start();
//
//        while (!done)
//        {
//            double error = angleToTurn - gyro.getAngle();
//
//            errorSum += error;
//
//            double xSpeed = maxNormalize( error * tKp /*+ errorSum * Ki*/ , speed );
//
//            this.drive( xSpeed , 0.0);
//
////            if ( Math.abs( xSpeed ) < 0.10 && Math.abs( error ) < 1.0 )
////            {
////                done = true;
////            }
//            if (timer.get() > 5)
//                done = true;
//
//            System.out.println("Timer: " + timer.get());
////            System.out.println( "Speed: " + xSpeed + " Error: " + error);
//            Timer.delay( 0.1 );
//        }
//
//        timer.stop();
//
////        while ( Math.abs( gyro.getAngle() ) < angleToTurn )
////        {
////            this.drive( speed, 0.0 );
////        }
//
//        this.stop();
//        System.out.println( "Angle of Robot: " + gyro.getAngle() );
//
//        gyro.reset();
//    }
    public void turnAngle( double angleToTurn, double speed )
    {
        boolean done = false;
        double kP = 0.025;
        double kD = 0.01;
        double error = 0.0;
        double prevError = 0.0;

        gyro.reset();

        while ( !done )
        {
            prevError = error;
            error = normalize( (angleToTurn - gyro.getAngle()) / 100.0 );

            double p = error * kP;
            double d = (error - prevError) * kD;

            this.drive( p + d, 0.0 );

            if ( Math.abs( error ) < 0.01 )
            {
                done = true;
            }

            System.out.println( "Gyro: " + gyro.getAngle() );
            Timer.delay( 0.05 );
        }

        this.stop(); 
    }

    public void driveSonar( double speed, double distanceToTravel, boolean useFeet )
    {
        double ticksToTravel;

        if ( useFeet )
        {
            ticksToTravel = (distanceToTravel * 12) * TICKSPERINCH;
        }
        else
        {
            ticksToTravel = distanceToTravel * TICKSPERINCH;
        }

        encoder.start();

        while ( Math.abs( encoder.get() ) < ticksToTravel )
        {
            this.drive( 0.0, speed );
        }

        this.stop();

        encoder.stop();
        encoder.reset();
    }

    public void printValues()
    {
        System.out.println( "Gyro: " + gyro.getAngle() + " Encoder: " + encoder.get() );
    }
}

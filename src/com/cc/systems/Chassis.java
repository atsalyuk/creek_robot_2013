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
        //Set speed at 0.59 to be around desired distance

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
//        double Kp = 0.065;
//        double Ki = 0.002;
//        double Kd = 0.1;
//        double error = 0.0;
//        double errorSum = 0.0;
//        double prevError = 0.0;
////        Timer timer = new Timer();
//
//        gyro.reset();
////        timer.start();
//
//        while (!done)
//        {
//            prevError = error;          
//            error = angleToTurn - gyro.getAngle();
////            errorSum += error;
//            double pOut = error * Kp;
//            double dOut = (error-prevError)*Kd;
//
//            double xSpeed = maxNormalize( pOut /*+ errorSum * Ki*/ + dOut , speed );
//
//            this.drive( xSpeed , 0.0);
//
//            if ( Math.abs( error ) < 0.1 )
//            {
//                done = true;
//            }
//            
////            if (timer.get() > 5)
////            {
////                done = true;
////            }
//            
//            System.out.println( "Gyro: " + gyro.getAngle() + "  Error: " + error + "  pOut: " + pOut + "dOut: " + dOut );
////            Timer.delay( 0.1 );
//        }
//
////        timer.stop();
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
                             // Speed at .75;  .7;    .65;   .6;  
        double kP = 1.0;     // kP = 1.0;      1.0;   1.0;   1.0;
        double kI = 0.06;    // kI = .05;      .06;   .07;   .10;
        double kD = 0.11;    // kD = .08;      .11;   .14;   .16;
        double error = 0.0;
        double prevError = 0.0;
        double errorSum = 0.0;

        gyro.reset();

        while ( !done )
        {
            prevError = error;
            error = normalize( (angleToTurn - gyro.getAngle() ) / 100.0 );
            errorSum += error;
            errorSum = maxNormalize( errorSum, 5 );
            
            double p = error * kP;
            double i = errorSum * kI;
            double d = (error - prevError) * kD;       

            this.drive( maxNormalize(p + i + d, speed) , 0.0 );

            if ( Math.abs( errorSum ) < 0.01 )
            {
                done = true;
            }

//            System.out.println( "Gyro: " + gyro.getAngle() + " errorSum: " + errorSum + " error: " + error);
//            Timer.delay( 0.05 );
        }
        
        System.out.println( "Gyro: " + gyro.getAngle() + " errorSum: " + errorSum + " error: " + error);
//        System.out.println("Out of loop");
        
        this.stop(); 
        done = false;
        gyro.reset();
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

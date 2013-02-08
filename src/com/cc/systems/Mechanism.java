/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cc.systems;

import com.cc.outputs.motor.CCVictor;
import com.cc.inputs.digitalInputs.LimitSwitch;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Henry
 */
public class Mechanism
{

    private static Mechanism INSTANCE = null;
//    private boolean armIsMoving = false;
    private int armState = 0;
    CCVictor armMotor = null;
    LimitSwitch armSwitch = null;
//    AnalogChannel analogOne = null;
    Gyro gyro;

    private Mechanism()
    {
        armMotor = new CCVictor( 6, false );
        armSwitch = new LimitSwitch( 3 );
//        analogOne = new AnalogChannel( 2 );
        gyro = new Gyro( 2 );

    }

    public static Mechanism getInstance()
    {
        if ( INSTANCE == null )
        {
            INSTANCE = new Mechanism();
        }

        return INSTANCE;
    }

    public void moveArm( boolean red, boolean black ) //dump the cargo and what not
    {

        switch( armState )
        {
            case 0: //Arm Down, Not Moving
                
                if( red )
                {
                    armState = 1;
                    armMotor.set( 0.75 );
                    gyro.reset();
                }
                
                break;
                
            case 1: // Stop Motor
                
                if( gyro.getAngle() > 90)
                {
                    armState = 2;
                    this.stopArm();
                }
                
                break;
                
            case 2: // Arm Up, Not Moving
                
                if( black )
                {
                    armState = 3;
                    armMotor.set( -0.15 );
                    gyro.reset();
                }
                
                break;
                
            case 3: // Motor stop
                
                if( gyro.getAngle() < -100 )
                {
                    armState = 0;
                    this.stopArm();
                }
                
                break;                
         
        }
        
        System.out.println( gyro.getAngle() );
        
//        if ( red && !black && !armIsMoving )
//        {
//            armMotor.set( 1.0 );
//            armIsMoving = true;
//            while ( analogOne.getValue() > 500 )
//            {
//                //Do Nothing
//            }
//            this.stopArm();
//
//        }
//        else if ( !red && black && armIsMoving )
//        {
//            armMotor.set( -0.15 );
//            armIsMoving = false;
//            while ( analogOne.getValue() < 520 )
//            {
//                //Do Nothing
//            }
//            this.stopArm();
//        }
//        else if ( red && black )
//        {
//            //Do nothing
//        }
        
    }

    public void driveArm( double speed )
    {
        armMotor.set( normalize( speed ) );
    }

    public void stopArm()
    {
        armMotor.set( 0.0 );
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
}

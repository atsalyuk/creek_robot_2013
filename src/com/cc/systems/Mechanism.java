/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cc.systems;

import com.cc.outputs.motor.CCVictor;
import com.cc.inputs.digitalInputs.LimitSwitch;
import com.cc.outputs.motor.CCTalon;
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
    ;
    private int armState = 0;
    CCTalon armMotor = null;
//    AnalogChannel analogOne = null;
    Gyro gyro;

    private Mechanism()
    {
        armMotor = new CCTalon( 6, false );
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

        switch ( armState )
        {
            case 0: //Arm Down, Not Moving

                if ( red )
                {
                    armState = 1;
                    armMotor.set( 0.75 );
                    gyro.reset();
                }

                break;

            case 1: // Stop Motor

                Timer.delay( 0.5 );

                armState = 2;
                this.stopArm();


                break;

            case 2: // Arm Up, Not Moving

                if ( black )
                {
                    armState = 3;
                    armMotor.set( -0.25 );
                    gyro.reset();
                }

                break;

            case 3: // Motor stop

                Timer.delay( 1.0 );

                armState = 0;
                this.stopArm();

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

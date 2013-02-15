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
//    CCVictor armMotor = null;

    private Mechanism()
    {
        armMotor = new CCTalon( 1, false );
//        armMotor = new CCVictor ( 1, false );

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
                    armMotor.set( 0.625 );
                }

                break;

            case 1: // Stop Motor

               Timer.delay( 0.12 );

                armState = 2;
                this.stopArm();


                break;

            case 2: // Arm Up, Not Moving

                if ( black )
                {
                    armState = 3;
                    armMotor.set( -0.25 );
                }

                break;

            case 3: // Motor stop

                Timer.delay( 1.0 );

                armState = 0;
                this.stopArm();

                break;

        }

    }

    public void driveArm( double speed )
    {
        armState = 0;
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

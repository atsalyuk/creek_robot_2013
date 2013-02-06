/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cc.systems;

import com.cc.outputs.motor.CCVictor;
import com.cc.inputs.digitalInputs.LimitSwitch;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Henry
 */
public class Mechanism
{

    private static Mechanism INSTANCE = null;
    private boolean armIsMoving = false;
    CCVictor armMotor = null;
    LimitSwitch armSwitch = null;

    private Mechanism()
    {
        armMotor = new CCVictor( 6, false );
        armSwitch = new LimitSwitch( 3 );

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

        if ( red && !black && !armIsMoving )
        {
            armMotor.set( 1.0 );
            armIsMoving = true;
            Timer.delay( 0.55 );
            this.stopArm();
            
        }
        else if ( !red && black && armIsMoving )
        {
            armMotor.set( -0.15 );
            armIsMoving = false;
            Timer.delay( 0.8 );
            this.stopArm();
        }
        else if ( red && black )
        {
            //Do nothing
        }
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

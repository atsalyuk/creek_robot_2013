/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cc.autonomous;

import com.cc.systems.Chassis;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Adam
 */
public abstract class AutoCommand
{

    Chassis chassis = null;

    public AutoCommand()
    {
        chassis = Chassis.getInstance();
    }

    public abstract void doAuto();

    public void doBackup()
    {
        chassis.driveDistance( 2.4, -0.45, true );
        Timer.delay( 0.25 );
        chassis.turnAngle( 82, 1 );
        Timer.delay( 0.25 );
    }

    public void doCommon()
    {
        Timer.delay( 0.25 );
        chassis.turnAngle( -74, 1 );
        Timer.delay( 0.25 );
        chassis.driveDistance( 16.85, 0.55, true );
        Timer.delay( 0.25 );
        chassis.turnAngle( 35.055555555, 1 );
        Timer.delay( 0.25 );
        chassis.driveDistance( 5.62, 0.55, true );
        Timer.delay( 0.25 );
    }
}

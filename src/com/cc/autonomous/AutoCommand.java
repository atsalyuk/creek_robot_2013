/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cc.autonomous;

import com.cc.systems.Chassis;
import com.cc.systems.Mechanism;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Adam
 */
public abstract class AutoCommand
{

    Chassis chassis = null;
    Mechanism mechanism = null;

    public AutoCommand()
    {
        chassis = Chassis.getInstance();
        mechanism = Mechanism.getInstance();
    }

    public abstract void doAuto();

//    public void doBackup()
//    {
//        chassis.driveDistance( 2.4, -0.45, true );
//        Timer.delay( 0.25 );
//        chassis.turnAngle( 82, 1 );
//        Timer.delay( 0.25 );
//    }
//
//    public void doCommon()
//    {
//        Timer.delay( 0.25 );
//        chassis.turnAngle( -74, 1 );
//        Timer.delay( 0.25 );
//        chassis.driveDistance( 16.85, 0.55, true );
//        Timer.delay( 0.25 );
//        chassis.turnAngle( 35.055555555, 1 );
//        Timer.delay( 0.25 );
//        chassis.driveDistance( 5.62, 0.55, true );
//        Timer.delay( 0.25 );
//    }
    
    public void doArm()
    {
        mechanism.driveArm( -0.5 );
        Timer.delay( 3 );
        mechanism.stopArm();
        Timer.delay( 0.2 );
        mechanism.driveArm( 0.8 );
        Timer.delay( 3 );
        mechanism.stopArm();
    }
}

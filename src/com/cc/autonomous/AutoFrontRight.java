/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cc.autonomous;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Henry
 */
public class AutoFrontRight extends AutoCommand
{
    public void doAuto()
    {
        chassis.driveDistance( 49, 0.59, false);
        Timer.delay( 0.5 );
        chassis.turnAngle( 56.31, 3 );
        Timer.delay( 0.5 );
        chassis.driveDistance( 81, 0.59, false);
        Timer.delay( 0.5 );
        
        doArm();
    }
}

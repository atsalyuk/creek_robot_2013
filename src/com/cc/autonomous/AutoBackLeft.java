/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cc.autonomous;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author TestingMachine
 */
public class AutoBackLeft extends AutoCommand
{
    public void doAuto()
    {
        chassis.driveDistance(159, 0.59, false);
        Timer.delay(0.5);
        chassis.turnAngle(90, 3);
        Timer.delay(0.5);
        chassis.driveDistance(168, 0.59, false);
        Timer.delay(0.5);
        chassis.turnAngle(-33.69, 3);
        Timer.delay(0.5);
        chassis.driveDistance(58, 0.59, false);
        Timer.delay(0.5);
        
        doArm();
    }
}

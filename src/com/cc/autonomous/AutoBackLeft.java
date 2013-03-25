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
        chassis.driveDistance(130, 0.59, false);
        Timer.delay(0.5);
        chassis.turnAngle(88, 3);
        Timer.delay(0.5);
        chassis.driveDistance(170, 0.59, false);
        Timer.delay(0.5);
        chassis.turnAngle(-31, 3);
        Timer.delay(0.5);
        chassis.driveDistance(52, 0.59, false);
        Timer.delay(0.5);
        
        doArm();
    }
}

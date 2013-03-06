/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cc.autonomous;

/**
 *
 * @author TestingMachine
 */
public class AutoLeft extends AutoCommand
{

    public void doAuto()
    {
//        doBackup();
        
        chassis.driveDistance( 14.12, 0.55, true );
        
//        doCommon();
    }
    
}

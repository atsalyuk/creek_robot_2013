/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cc.autonomous;

/**
 *
 * @author Adam
 */
public class AutoCenter extends AutoCommand
{

    public void doAuto()
    {
//        doBackup();
        
        chassis.driveDistance( 9.81, 0.55, true );
        
//        doCommon();
    }
    
}

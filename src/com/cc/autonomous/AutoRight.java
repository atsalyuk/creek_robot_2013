/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cc.autonomous;

/**
 *
 * @author Adam
 */
public class AutoRight extends AutoCommand
{

    public void doAuto()
    {
//         doBackup();
        
        chassis.driveDistance( 3.3, 0.55, true );
        
//        doCommon();
   }
    
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import com.cc.inputs.driver.Driver;
import com.cc.systems.Chassis;
import com.cc.systems.Mechanism;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends SimpleRobot
{

    Chassis chassis = null;
    Mechanism mechanism = null;
    Driver driver = null;
    DigitalInput swIn;
    DigitalOutput ledOut;

    protected void robotInit()
    {
        super.robotInit();

        driver = Driver.getInstance();
        chassis = Chassis.getInstance();
        mechanism = Mechanism.getInstance();
//        swIn = new DigitalInput(1);
//        ledOut = new DigitalOutput(2);
    }

    protected void disabled()
    {
        super.disabled();
    }

    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous()
    {
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl()
    {
        System.out.println( "In operatorControl()" );

        while ( isEnabled() )
        {
            // Drive it
            chassis.drive( driver.getX(), driver.getY() );
            mechanism.moveArm( driver.getRot() );
        }
    }

    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test()
    {
        System.out.println( "In test" );
        
        chassis.turnAngle( -90, 0.450 );
    }
}

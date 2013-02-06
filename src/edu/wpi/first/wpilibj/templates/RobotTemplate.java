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
    DigitalInput switchOne;
    DigitalInput switchTwo;

    protected void robotInit()
    {
        super.robotInit();

        driver = Driver.getInstance();
        chassis = Chassis.getInstance();
        mechanism = Mechanism.getInstance();
        switchOne = new DigitalInput( 1 );
        switchTwo = new DigitalInput( 2 );
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
        boolean sOne = switchOne.get();
        boolean sTwo = switchTwo.get();

        if ( sOne || sTwo )
        {
            chassis.driveDistance( 6.01, -0.25, true );
            Timer.delay( 0.5 );
            chassis.turnAngle( 95, 0.50 );
            Timer.delay( 0.5 );
            if ( sOne && !sTwo )
            {
                chassis.driveDistance( 60, 0.25, false );
            }
            else if( !sOne && sTwo)
            {
                chassis.driveDistance( 107, 0.25, false );
            }
            else
            {
                chassis.driveDistance( 154, 0.25, false );
            }
            Timer.delay( 0.5 );
            chassis.turnAngle( 95, -0.50 );
            Timer.delay( 0.5 );
            chassis.driveDistance( 200, 0.25, false );
            Timer.delay( 0.5 );
            chassis.turnAngle( 37, 0.5 );
            Timer.delay( 0.5 );
            chassis.driveDistance( 95.8, 0.25, false );

        }
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

//            mechanism.driveArm( driver.getRot() );
            
            mechanism.moveArm( driver.getRedButton() , driver.getBlackButton() );

        }
    }

    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test()
    {
        System.out.println( "In test" );

        while ( isEnabled() )
        {

//            System.out.println( "Switch One: " + switchOne.get() + " Switch Two: " + switchTwo.get() );
//            Timer.delay( 0.5 );
            
            mechanism.driveArm( driver.getRot() );

        }
    }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import com.cc.inputs.driver.Driver;
import com.cc.outputs.motor.CCVictor;
import com.cc.systems.Chassis;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
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
    Driver driver = null;
    CCVictor dumper;
    DigitalInput swIn;
    DigitalOutput ledOut;
    Encoder encoder;
    Gyro gyro;

    protected void robotInit()
    {
        super.robotInit();

        driver = Driver.getInstance();
        chassis = Chassis.getInstance();
        dumper = new CCVictor( 6, false );
//        swIn = new DigitalInput(1);
//        ledOut = new DigitalOutput(2);
        encoder = new Encoder(13,14);
//        gyro = new Gyro();
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
            dumper.set( driver.getRot());
        }
    }

    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test()
    {
        System.out.println( "In test" );
        encoder.start();
       while (isEnabled())
        {
            
            chassis.drive( 0.0 , 0.2 );
//            ledOut.set( swIn.get());
//            driver.getX();
//            ledOut.set( true );
            Timer.delay( 1.0 );
//            ledOut.set( false );
//            Timer.delay( 0.5 );
            System.out.println("Encoder val: "+encoder.get());
            chassis.stop();
            encoder.reset();
            Timer.delay( 5.0);
        }
       encoder.stop();
    }
}

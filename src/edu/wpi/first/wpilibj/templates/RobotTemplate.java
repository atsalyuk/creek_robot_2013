/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import com.cc.inputs.digitalInputs.Sonar;
import com.cc.inputs.driver.Driver;
import com.cc.systems.Chassis;
import com.cc.systems.Mechanism;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Talon;
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
    Sonar sonar;
//    Encoder encoder;
//    DriverStationLCD driverLCD;
    
    protected void robotInit()
    {
        super.robotInit();
        
        driver = Driver.getInstance();
        chassis = Chassis.getInstance();
        mechanism = Mechanism.getInstance();
        switchOne = new DigitalInput( 1 );
        switchTwo = new DigitalInput( 2 );
        sonar = new Sonar( 3, 4);
//        encoder = new Encoder( 13, 14 );
        
//        driver station hello world code
//          driverLCD=DriverStationLCD.getInstance();
//          driverLCD.println(DriverStationLCD.Line.kUser1,0,"hello word, dirver lcd test");
        //end of hello world
        
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
            chassis.driveDistance( 3.0, -0.40, true );
            Timer.delay( 0.5 );
            chassis.turnAngle( 95, 0.50 );
            Timer.delay( 0.5 );
            if ( sOne && !sTwo )
            {
                chassis.driveDistance( 60, 0.40, false );
            }
            else if ( !sOne && sTwo )
            {
                chassis.driveDistance( 107, 0.40, false );
            }
            else
            {
                chassis.driveDistance( 154, 0.40, false );
            }
            Timer.delay( 0.5 );
            chassis.turnAngle( 91, -0.50 );
            Timer.delay( 0.5 );
            chassis.driveDistance( 224, 0.50, false );
            Timer.delay( 0.5 );
            chassis.turnAngle( 58.055555555, 0.5 );
            Timer.delay( 0.5 );
            chassis.driveDistance( 31.8, 0.40, false );
            Timer.delay( 0.5 );

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
            chassis.printValues();
            if( driver.getRightSwitch() )
            {
                mechanism.driveArm( driver.getRot() );
            }
            else
            {
                mechanism.moveArm( driver.getRedButton(), driver.getBlackButton() );
            }

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
            System.out.println( sonar.calibrate() );
            
            
//            encoder.start();
//            chassis.drive( 0.0, 0.35);
//            Timer.delay( 5.0 );
//            chassis.stop();
//            System.out.println( "Encoder: " + encoder.get() );
//            encoder.stop();
//            encoder.reset();

        }

    }
}
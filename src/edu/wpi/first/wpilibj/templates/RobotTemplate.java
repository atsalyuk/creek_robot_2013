/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import com.cc.autonomous.AutoCenter;
import com.cc.autonomous.AutoCommand;
import com.cc.autonomous.AutoLeft;
import com.cc.autonomous.AutoRight;
import com.cc.autonomous.DoNothing;
import com.cc.inputs.digitalInputs.Sonar;
import com.cc.inputs.driver.Driver;
import com.cc.systems.Chassis;
import com.cc.systems.Mechanism;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    SendableChooser autoChooser;
    AutoCenter autoCenter;
    AutoLeft autoLeft;
    AutoRight autoRight;
    DoNothing doNothing;

    protected void robotInit()
    {
        super.robotInit();

        driver = Driver.getInstance();
        chassis = Chassis.getInstance();
        mechanism = Mechanism.getInstance();
        switchOne = new DigitalInput(1);
        switchTwo = new DigitalInput(2);
        sonar = new Sonar(7, 6);
//        encoder = new Encoder( 13, 14 );

//        driver station hello world code
//          driverLCD=DriverStationLCD.getInstance();
//          driverLCD.println(DriverStationLCD.Line.kUser1,0,"hello word, dirver lcd test");
        //end of hello world
        autoCenter = new AutoCenter();
        autoLeft = new AutoLeft();
        autoRight = new AutoRight();
        doNothing = new DoNothing();

        autoChooser = new SendableChooser();
        autoChooser.addDefault("Left Side", autoLeft);
        autoChooser.addObject("Center", autoCenter);
        autoChooser.addObject("Right Side", autoRight);
        autoChooser.addObject("Do Nothing", doNothing);
        SmartDashboard.putData("Auto Chooser", autoChooser);

    }

    protected void disabled()
    {
        super.disabled();
    }

    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        AutoCommand command = (AutoCommand) autoChooser.getSelected();
        command.doAuto();

//        if ( sOne || sTwo )
//        {
////            chassis.driveDistance( 2.4, -0.45, true );
////            Timer.delay( 0.25 );
////            chassis.turnAngle( 82, 0.60 );
////            Timer.delay( 0.25 );
////            if ( sOne && !sTwo )
//            {
//                chassis.driveDistance( 3.3, 0.55, true );
//            }
//            else if ( !sOne && sTwo )
//            {
//                chassis.driveDistance( 9.81, 0.55, true );
//            }
//            else
//            {
//                chassis.driveDistance( 14.12, 0.55, true );
//            }
//            Timer.delay( 0.25 );
//            chassis.turnAngle( 74, -0.60 );
//            Timer.delay( 0.25 );
//            chassis.driveDistance( 16.85, 0.55, true );
//            Timer.delay( 0.25 );
//            chassis.turnAngle( 35.055555555, 0.6 );
//            Timer.delay( 0.25 );
//            chassis.driveDistance( 5.62, 0.55, true );
//            Timer.delay( 0.25 );
//
//        }
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        System.out.println("In operatorControl()");

        while (isEnabled())
        {
            // Drive it
            chassis.drive(driver.getX(), driver.getY());
//            chassis.printValues();
            if (driver.getRightSwitch())
            {
                mechanism.driveArm(driver.getRot());
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
    public void test() {
//        System.out.println( "In test" );

//        while ( isEnabled() )
        {

            chassis.turnAngle(90, 0.6);

        }
    }
}
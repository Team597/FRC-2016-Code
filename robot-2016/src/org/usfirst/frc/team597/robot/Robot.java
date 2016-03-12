
package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	final String lowBarTouch = "Lowbar Touch";
	final String lowBarCross = "Lowbar Cross";
	final String lowBarLowGoal = "Lowbar Low-goal";
	final String lowBarHighGoal = "Lowbar High-goal";
	
	String autoSelected;
	SendableChooser chooser;
	
	// Joysticks for both drivers
	Joystick joystickLeft;
	Joystick joystickRight;
	Joystick joystickShooting;
	
	// Enables camera
	CameraServer server;
	
	// Starts compresor
	Compressor comp;
	
	// Initializes classes
	Drive tankDrive;
	Shifting driveShift;
	Shooter shooter;
	Arm telescoping;	// fix and finish

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		// SmartDashboard stuff
		chooser = new SendableChooser();
		chooser.addObject("Lowbar Touch", lowBarTouch);
		chooser.addObject("Lowbar Cross", lowBarCross);
		chooser.addDefault("Lowbar Low-goal", lowBarLowGoal);
		chooser.addDefault("Lowbar High-goal", lowBarHighGoal);
		
		SmartDashboard.putData("Auto choices", chooser);
		
		// Initializes joysticks
		joystickLeft = new Joystick(0);
		joystickRight = new Joystick(1);
		joystickShooting = new Joystick(2);
		
		// Sets up camera
		server = CameraServer.getInstance();
		server.setQuality(50);
		server.startAutomaticCapture("cam0");
		
		// Sets up classes
		tankDrive = new Drive(joystickLeft,joystickRight);
		driveShift = new Shifting(joystickLeft, joystickRight);
		shooter = new Shooter(joystickShooting);
		telescoping = new Arm(joystickShooting);	// fix and finish
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	public void autonomousInit() {
		autoSelected = (String) chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
		
		switch (autoSelected) {
		case lowBarTouch:
			shooter.armAuto(0.60);		// Lower arm - run motors at 70% speed for 0.5 sec 
			Timer.delay(0.20);
			shooter.armAuto(0);
			Timer.delay(0.1);
			
			tankDrive.auto(0.600, 0.600);	// Move forward - run motors at 80% speed for 2 sec
			Timer.delay(4.5);
			tankDrive.auto(0, 0);
			Timer.delay(15);
			
			break;
		case lowBarCross:
			
			
			break;
		
		/*
		 * More advanced autonomi below. 	
		 */
		
		case lowBarLowGoal:
			// Code here
			break;
		case lowBarHighGoal:
			// Code here
			break;
		default:
			// Default code
			break;
	}
	}

	/**
	 * 
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		// Class teleop functions
		tankDrive.teleopPeriodic();
		driveShift.teleopPeriodic();
		telescoping.teleopPeriod();
		
		// Shooter functions
		shooter.shoot();
		shooter.intake();
		shooter.armBasic();
		shooter.pistons();
		
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}

}

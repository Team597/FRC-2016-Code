
package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
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
	final String lowBarHighGoal = "Lowbar High-goal";
	final String lowBarCross = "Lowbar Cross";
	
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

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		// SmartDashboard stuff
		chooser = new SendableChooser();
		chooser.addDefault("Lowbar High-goal", lowBarHighGoal);
		chooser.addObject("Lowbar Cross", lowBarCross);
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
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
	}

	/**
	 * 
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case lowBarCross:
			// Custom auto code here
			break;
		case lowBarHighGoal:
		default:
			// Default auto code here
			break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		// Class teleop functions
		tankDrive.teleopPeriodic();
		driveShift.teleopPeriodic();
		shooter.teleopPeriodic();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}

}

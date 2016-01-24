
package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
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
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser chooser;

	Joystick joystickLeft;
	Joystick joystickRight;

	VictorSP leftDriveMotorOne;
	VictorSP leftDriveMotorTwo;
	VictorSP rightDriveMotorOne;
	VictorSP rightDriveMotorTwo;

	VictorSP shooterMotor;
	VictorSP shooterMoverMotor;

	boolean toggleStateOne;
	boolean toggleStateTwo;
	boolean toggleShift;
	
	final Value speedMode = Value.kForward;
	final Value torqueMode = Value.kReverse;
	
	CameraServer server;
	Compressor comp;
	DoubleSolenoid soleShift;

	Toggle toggleRight;
	Toggle toggleLeft;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		chooser = new SendableChooser();
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);

		joystickLeft = new Joystick(0);
		joystickRight = new Joystick(1);

		leftDriveMotorOne = new VictorSP(0);
		leftDriveMotorTwo = new VictorSP(1);
		rightDriveMotorOne = new VictorSP(2);
		rightDriveMotorTwo = new VictorSP(3);

		shooterMotor = new VictorSP(4);
		shooterMoverMotor = new VictorSP(5);

		toggleStateOne = false;
		toggleStateTwo = false;
		toggleShift = false;

		soleShift = new DoubleSolenoid(0, 1);

		server = CameraServer.getInstance();
		server.setQuality(50);
		server.startAutomaticCapture("cam0");
		toggleRight = new Toggle();
		toggleLeft = new Toggle();
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
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {

		
		toggleRight.input(joystickLeft.getRawButton(7));
		toggleLeft.input(joystickRight.getRawButton(7)); 
		toggleShift = toggleRight.toggleState();
		toggleShift = toggleLeft.toggleState();

		if (toggleShift == false) {
			soleShift.set(torqueMode);
		}
		if (toggleShift == false) {
			soleShift.set(speedMode);
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}

}

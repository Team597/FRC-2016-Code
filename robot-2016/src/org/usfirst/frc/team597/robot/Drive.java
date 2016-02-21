package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive {
	// Motors for tank-drive
	VictorSP victorLeftFront;
	VictorSP victorLeftBack;
	VictorSP victorRightFront;
	VictorSP victorRightBack;
	Joystick joystickLeft;
	Joystick joystickRight;
	// added custom gyro class
	Gyro Gyro;
	// created variables for left speed and right speed
	double rightSpeed;
	double leftSpeed;
	String driveMode;

	public Drive(Joystick jsLeft, Joystick jsRight) {
		// ports for motors on RoboRio
		victorLeftFront = new VictorSP(0);
		victorLeftBack = new VictorSP(1);
		victorRightFront = new VictorSP(2);
		victorRightBack = new VictorSP(3);
		// imported joystick from the main class
		joystickLeft = jsLeft;
		joystickRight = jsRight;
		Gyro = new Gyro();
		driveMode = "regular Tank";

	}
	// This void is used to activate the drive compensation class which helps
	// the driver drive straight, this class can be activated by pressing button
	// the trigger on either of the driving Joysticks

	public void teleopPeriodic() {
		// set the value of the left and right speed variables
		leftSpeed = joystickLeft.getY();
		rightSpeed = joystickRight.getY();

		if (joystickLeft.getRawButton(1) == false && joystickRight.getRawButton(1) == false) {
			// sets the value of the motors to the value of the left and right
			// speed variables.
			driveMode = "regular tank";
			victorLeftFront.set(leftSpeed);
			victorLeftBack.set(leftSpeed);
			victorRightFront.set(rightSpeed);
			victorRightBack.set(rightSpeed);
		} else if (joystickLeft.getRawButton(1) == true || joystickRight.getRawButton(1) == true) {
			// sets the left motors equal to the value of left speed from the
			// gyro class
			driveMode = "Code-tank";
			victorLeftFront.set(Gyro.leftSpeed());
			victorLeftBack.set(Gyro.leftSpeed());
			// set the right motors equal to the value of right speed from the
			// gyro class
			victorRightFront.set(Gyro.rightSPeed());
			victorRightBack.set(Gyro.rightSPeed());
		}
		SmartDashboard.putString("tank mode", driveMode);

	}

}

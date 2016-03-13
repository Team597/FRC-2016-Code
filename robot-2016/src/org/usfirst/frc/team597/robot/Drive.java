package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive {
	// Driver's joysticks
	ToggleButton toggleButton;

	Joystick joystickLeft;
	Joystick joystickRight;

	// Motors for tank-drive
	VictorSP victorLeftFront;
	VictorSP victorLeftBack;
	VictorSP victorRightFront;
	VictorSP victorRightBack;
	// added custom gyro class
	Gyro Gyro;

	// created variables for left speed and right speed
	double rightSpeed;
	double leftSpeed;
	boolean output;
	String drivePosition = "Normal Mode";

	public Drive(Joystick jsLeft, Joystick jsRight) {
		// imported joystick from the main class
		joystickLeft = jsLeft;
		joystickRight = jsRight;

		toggleButton = new ToggleButton();

		// ports for motors on RoboRio
		victorLeftFront = new VictorSP(0);
		victorLeftBack = new VictorSP(1);
		victorRightFront = new VictorSP(2);
		victorRightBack = new VictorSP(3);

		// Initializes gyro
		Gyro = new Gyro();

		// String to print in DS
		output = false;
	}

	/**
	 * This void is used to activate the drive compensation class which helps
	 * the driver drive straight, this class can be activated by pressing button
	 * the trigger on either of the driving Joysticks
	 */
	public void teleopPeriodic() {
		SmartDashboard.putString("Drive State", drivePosition);
		// set the value of the left and right speed variables
		toggleButton.input(joystickRight.getRawButton(5));

		output = toggleButton.Output();

		if (output == false && joystickRight.getRawButton(1) == false) {
			leftSpeed = joystickLeft.getY() * -1;
			rightSpeed = joystickRight.getY() * -1;
			drivePosition = "NORMAL";
		} else if (output == true && joystickRight.getRawButton(1) == false) {
			leftSpeed = joystickRight.getY();
			rightSpeed = joystickLeft.getY();
			drivePosition = "BACKWARD";
		} else if (joystickRight.getRawButton(1) == true) {
			leftSpeed = -joystickRight.getY();
			rightSpeed = -joystickRight.getY();
			drivePosition = "STRAIGHT";
		}
		victorLeftFront.set(leftSpeed);
		victorLeftBack.set(leftSpeed);
		victorRightFront.set(rightSpeed);
		victorRightBack.set(rightSpeed);

//		if (joystickLeft.getRawButton(1) == false && joystickRight.getRawButton(1) == false) {
//			// sets the value of the motors to the value of the left and right
//			// speed variables.
//			victorLeftFront.set(leftSpeed);
//			victorLeftBack.set(leftSpeed);
//			victorRightFront.set(rightSpeed);
//			victorRightBack.set(rightSpeed);
//		} else if (joystickLeft.getRawButton(1) == true || joystickRight.getRawButton(1) == true) {
//			// sets the left motors equal to the value of left speed from the
//			// gyro class
//			victorLeftFront.set(Gyro.leftSpeed());
//			victorLeftBack.set(Gyro.leftSpeed());
//			// set the right motors equal to the value of right speed from the
//			// gyro class
//			victorRightFront.set(Gyro.rightSPeed());
//			victorRightBack.set(Gyro.rightSPeed());
//		}

	}

	// For autonomous
	public void auto(double leftMotorSpeed, double rightMotorSpeed) {
		// Left motors
		victorLeftFront.set(-leftMotorSpeed);
		victorLeftBack.set(-leftMotorSpeed);

		// Right motors
		victorRightFront.set(-rightMotorSpeed);
		victorRightBack.set(-rightMotorSpeed);

	}

}

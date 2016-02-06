package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.VictorSP;

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

	public Drive(Joystick jsLeft, Joystick jsRight) {
		// ports for motors on RoboRio
		victorLeftFront = new VictorSP(0);
		victorLeftBack = new VictorSP(1);
		victorRightFront = new VictorSP(2);
		victorRightBack = new VictorSP(3);
		// imported joystick from the main class
		joystickLeft = jsLeft;
		joystickRight = jsRight;
		// set the value of the left and right speed variables
		leftSpeed = joystickLeft.getY();
		rightSpeed = joystickRight.getY() * -1;

	}

	public void teleopPeriodic() {

		if (joystickLeft.getRawButton(1) == false || joystickRight.getRawButton(1) == false) {
			// sets the value of the motors to the value of the left and right
			// speed variables.
			victorLeftFront.set(leftSpeed);
			victorLeftBack.set(leftSpeed);
			victorRightFront.set(rightSpeed);
			victorRightBack.set(rightSpeed);
		}

	}

}

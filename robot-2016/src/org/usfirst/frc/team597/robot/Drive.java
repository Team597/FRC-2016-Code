package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.VictorSP;

public class Drive {
	// Motors for tank-drive
	// Motors for tank-drive
	VictorSP victorLeftFront;
	VictorSP victorLeftBack;
	VictorSP victorRightFront;
	VictorSP victorRightBack;
	Joystick joystickLeft;
	Joystick joystickRight;
	Gyro Gyro;

	double rightSpeed;
	double leftSpeed;

	public Drive(Joystick jsLeft, Joystick jsRight) {
		// ports for motors on RoboRio
		victorLeftFront = new VictorSP(0);
		victorLeftBack = new VictorSP(1);
		victorRightFront = new VictorSP(2);
		victorRightBack = new VictorSP(3);
		joystickLeft = jsLeft;
		joystickRight = jsRight;

		leftSpeed = joystickLeft.getY();
		rightSpeed = joystickRight.getY() * -1;

	}

	public void teleopPeriodic() {
		if (joystickLeft.getRawButton(1) == false || joystickRight.getRawButton(1) == false) {
			victorLeftFront.set(leftSpeed);
			victorLeftBack.set(leftSpeed);
			victorRightFront.set(rightSpeed);
			victorRightBack.set(rightSpeed);
		} else if (joystickLeft.getRawButton(1) == true && joystickRight.getRawButton(1) == true) {
			victorLeftFront.set(1);
			victorLeftBack.set(1);
			victorRightFront.set(-1);
			victorRightBack.set(-1);
		}

	}

}

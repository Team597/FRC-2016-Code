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
	

	public Drive(Joystick jsLeft, Joystick jsRight) {
		// ports for motors on RoboRio
		victorLeftFront = new VictorSP(0);
		victorLeftBack = new VictorSP(1);
		victorRightFront = new VictorSP(2);
		victorRightBack = new VictorSP(3);
		joystickLeft = jsLeft;
		joystickRight = jsRight;
		

	}

	public void teleopPeriodic() {
		double leftSpeed = joystickLeft.getY();
		double rightSpeed = joystickRight.getY();
		
		// sets left motors to value of left joystick
		victorLeftFront.set(leftSpeed);
		victorLeftBack.set(leftSpeed);
		victorRightFront.set(rightSpeed);
		victorRightBack.set(rightSpeed);

	}

}

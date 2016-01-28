package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.VictorSP;

public class Drive {
	// Motors for tank-drive
	VictorSP victorLeftFront;
	VictorSP victorLeftBack;
	VictorSP victorRightFront;
	VictorSP victorRightBack;

	public Drive() {
		// ports for motors on RoboRio
		victorLeftFront = new VictorSP(0);
		victorLeftBack = new VictorSP(1);
		victorRightFront = new VictorSP(2);
		victorRightBack = new VictorSP(3);

	}

	public void input(double doubleInputLeft, double doubleInputRight) {
		// sets left motors to value of left joystick
		victorLeftFront.set(doubleInputLeft);
		victorLeftBack.set(doubleInputLeft);
		// set right motors to value of right joystick
		victorRightFront.set(doubleInputRight);
		victorRightBack.set(doubleInputRight);

	}


}

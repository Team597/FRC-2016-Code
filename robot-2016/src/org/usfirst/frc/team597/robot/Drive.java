package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.VictorSP;

public class Drive {
	//
	VictorSP victorLeftFront;
	VictorSP victorLeftBack;
	VictorSP victorRightFront;
	VictorSP victorRightBack;

	public Drive() {
		// gives arguments(Ports) to the objects used in our Drive
		victorLeftFront = new VictorSP(0);
		victorLeftBack = new VictorSP(1);
		victorRightFront = new VictorSP(2);
		victorRightBack = new VictorSP(3);

	}

	public void input(double doubleInputLeft, double doubleInputRight) {
		// set the value of the front left victor and the value of the back left
		// victor to the value of the left input
		victorLeftFront.set(doubleInputLeft);
		victorLeftBack.set(doubleInputLeft);
		// set the value of the front right victor and the value of back right
		// victor to the value of the right inputs
		victorRightFront.set(doubleInputRight);
		victorRightBack.set(doubleInputRight);

	}


}

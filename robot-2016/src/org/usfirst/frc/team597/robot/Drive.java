package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.VictorSP;

public class Drive {
	Joystick joystickLeft;
	Joystick joystickRight;
	VictorSP victorLeftFront;
	VictorSP victorLeftBack;
	VictorSP victorRightFront;
	VictorSP victorRightBack;

	public Drive() {
		victorLeftFront = new VictorSP(0);
		victorLeftBack = new VictorSP(1);
		victorRightFront = new VictorSP(2);
		victorRightBack = new VictorSP(3);
		
	}
	public void autonomousPeriodic(double autoInputLeft, double autoInputRight){
		victorLeftFront.set(autoInputLeft);
		victorLeftBack.set(autoInputLeft);
		victorRightFront.set(autoInputRight);
		victorRightBack.set(autoInputRight);
	
	}
	public void teleopPeriodic(double leftStick, double rightStick){
		victorLeftFront.set(leftStick);
		victorLeftBack.set(leftStick);
		victorRightFront.set(rightStick);
		victorRightBack.set(rightStick);
		
	}

}

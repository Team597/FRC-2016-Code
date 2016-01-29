package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Shifting {
	Toggle toggleButton;
	Joystick joystickLeft;
	Joystick joystickRight;

	public Shifting(Joystick jsLeft, Joystick jsRight) {
		toggleButton = new Toggle();
		joystickLeft = jsLeft;
		joystickRight = jsRight;
	}
	
	public void teleopPeriodic(){
		toggleButton.input(joystickLeft.getRawButton(7));
	}
}

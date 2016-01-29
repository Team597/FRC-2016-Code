package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Joystick;

public class Shifting {

	Toggle toggleDrive;
	boolean shifting;
	DoubleSolenoid driveShifting;
	Joystick joystickLeft;
	Joystick joystickRight;

	public Shifting(Joystick jsLeft, Joystick jsRight) {
		toggleDrive = new Toggle();
		shifting = false;
		driveShifting = new DoubleSolenoid(0, 1);
		joystickLeft = jsLeft;
		joystickRight = jsRight;

	}

	public void teleOp() {
		toggleDrive.input(joystickLeft.getRawButton(7), joystickRight.getRawButton(7));
		shifting = toggleDrive.switchState;
		if (shifting == false) {
			driveShifting.set(Value.kForward);
		}
		if (shifting == true) {
			driveShifting.set(Value.kReverse);
		}

	}

}

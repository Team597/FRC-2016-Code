package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Joystick;

public class Shifting {
	Toggle toggleButton;
	Joystick joystickLeft;
	Joystick joystickRight;
	DoubleSolenoid driveShifter;
	boolean toggleState;
	final Value speedMode = Value.kForward;
	final Value torqueMode = Value.kReverse;

	public Shifting(Joystick jsLeft, Joystick jsRight) {
		toggleButton = new Toggle();
		joystickLeft = jsLeft;
		joystickRight = jsRight;
		driveShifter = new DoubleSolenoid(0, 1);
		toggleState = false;
	}

	public void teleopPeriodic() {
		toggleButton.input(joystickLeft.getRawButton(7), joystickRight.getRawButton(7));
		toggleState = toggleButton.toggleOutput;

		if (toggleState == false) {
			driveShifter.set(speedMode);
		}
		if (toggleState == true) {
			driveShifter.set(torqueMode);
		}
	}
}

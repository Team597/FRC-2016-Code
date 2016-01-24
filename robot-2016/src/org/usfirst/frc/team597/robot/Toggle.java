package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Toggle {
	// button used for first toggle statement
	boolean toggleButtonOne;
	// button used for second toggle statement
	boolean toggleButtonTwo;
	// boolean being toggled
	boolean toggleState;
	DoubleSolenoid soleShift;

	public Toggle() {
		toggleButtonOne = false;
		toggleButtonTwo = false;
		toggleState = false;
		soleShift = new DoubleSolenoid(0, 1);
	}

	public void input(boolean valueOne, boolean valueTwo) {
		/**
		 * if the toggle button is not equal to valueOne and if the value of the
		 * toggle button is true, run the code below. This is done so that the
		 * code only activates when the button is being pressed.
		 */
		// In summary, press once for on, again for off, again for on.
		if (toggleButtonOne != valueOne && valueOne == true) {
			// set the toggle state to the opposite of the toggle state
			toggleState = !toggleState;
		}
		toggleButtonOne = valueOne;
		if (toggleButtonTwo != valueTwo && valueTwo == true) {
			toggleState = !toggleState;
		}
		toggleButtonTwo = valueTwo;

		if (toggleState == false) {
			soleShift.set(Value.kForward);
		}
		if (toggleState == true) {
			soleShift.set(Value.kReverse);
		}
	}

}
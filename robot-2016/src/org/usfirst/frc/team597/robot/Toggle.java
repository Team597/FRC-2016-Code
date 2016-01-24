package org.usfirst.frc.team597.robot;

public class Toggle {
	//button used for first toggle statement
	boolean toggleButtonOne;
	//button used for second toggle statement
	boolean toggleButtonTwo;
	//boolean being toggled
	boolean toggleState;

	public Toggle() {
		toggleButtonOne = false;
		toggleButtonTwo = false;
		toggleState = false;

	}

	public void input(boolean valueOne, boolean valueTwo) {
		/**
		 * if the toggle button is not equal to the stickValue and if the value
		 * of the toggle button is true, run the code below. This is done so
		 * that the code only activates when the button is being pressed.
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

	}

}
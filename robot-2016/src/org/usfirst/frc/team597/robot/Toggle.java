package org.usfirst.frc.team597.robot;

class Toggle {
	// button used for first toggle statement
	boolean toggleButtonOne;
	// button used for second toggle statement
	boolean toggleButtonTwo;
	// boolean being toggled
	boolean toggleState;
	// the value sent out when the toggle class is called
	boolean toggleOutput;

	public Toggle() {
		// gives the value of false to toggleButtonOne
		toggleButtonOne = false;
		// gives the value of false to toggleButtonTwo
		toggleButtonTwo = false;
		// gives the value of false to toggleState
		toggleState = false;
		// gives the value of false to toggleOutput
		toggleOutput = false;
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
		// sets the value of toggleButtonOne equal to ValueOne so that the
		// previous loop doesn't trigger
		toggleButtonOne = valueOne;
		// if toggleButtonTwo is not equal to valueTwo and toggleButtonTwo is
		// true the if statement bellow is triggered
		if (toggleButtonTwo != valueTwo && valueTwo == true) {
			// switches the toggle from on to off
			toggleState = !toggleState;
		}
		//sets the value of toggleButtonOne equal to valueOne so that the previous loop doesn't trigger
		toggleButtonTwo = valueTwo;
		// sets the value of the value sent out equal to the toggleState
		toggleOutput = toggleState;

	}
}
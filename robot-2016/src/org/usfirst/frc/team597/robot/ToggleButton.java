package org.usfirst.frc.team597.robot;

class ToggleButton { // boolean being toggled
	boolean toggleState;
	boolean lastCheck;

	public ToggleButton() {
		// gives the value of false to toggleState
		toggleState = false;
		lastCheck = false;
	}

	// Utilizes valueOne to change the toggle state from true to false and send
	// the value out when this it is called
	public void input(boolean valueOne) {
		// if valueOne is not equal to last check and ValueOne is true, the
		// following code is runs
		if (valueOne != lastCheck && valueOne == true) {
			// sets the value of toggle state equal to the opposite value of
			// toggle state
			toggleState = !toggleState;
		}
		// sets last check equal to valueOne so that it only toggles once
		lastCheck = valueOne;
	}

	public boolean Output() {
		// sends out the current value of toggle State
		return toggleState;
	}
}
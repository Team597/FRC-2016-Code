package org.usfirst.frc.team597.robot;

class ToggleButton { // boolean being toggled
	boolean toggleState;
	boolean lastCheck;

	public ToggleButton() {
		// gives the value of false to toggleState
		toggleState = false;
		lastCheck = false;
	}

	public void input(boolean valueOne) {
		if (valueOne != lastCheck && valueOne == true) {
			toggleState = !toggleState;
		}
		lastCheck = valueOne;
	}

	public boolean Output() {
		return toggleState;
	}
}
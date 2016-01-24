package org.usfirst.frc.team597.robot;

public class Toggle {
	boolean toggleButton;
	boolean toggleState;

	public Toggle() {
		toggleButton = false;
		toggleState = false;

	}

	public void input(boolean stickValue) {
		if (toggleButton != stickValue && stickValue == true) {
			toggleState = !toggleState;
		}
		toggleButton = stickValue;
	}

	public boolean toggleState() {
		return toggleState;
	}

}
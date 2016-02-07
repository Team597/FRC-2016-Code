package org.usfirst.frc.team597.robot;

class ToggleButton {
	// boolean being toggled
	boolean toggleState;

	EdgeButton edgeButton;

	public ToggleButton() {
		// gives the value of false to toggleState
		toggleState = false;
		edgeButton = new EdgeButton();
	}

	public void input(boolean valueOne) {
		edgeButton.SetInput(valueOne);
		if (edgeButton.GetRisingEdge()) {
			toggleState = !toggleState;
		}

	}

	public boolean Output() {
		return toggleState;
	}
}
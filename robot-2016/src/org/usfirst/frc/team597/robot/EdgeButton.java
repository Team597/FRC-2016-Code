package org.usfirst.frc.team597.robot;

public class EdgeButton {
	
	boolean buttonState;
	boolean risingEdge;
	boolean fallingEdge;

	public EdgeButton() {
		buttonState = false;
		risingEdge = false;
		fallingEdge = false;
	}

	public void SetInput(boolean buttonInput) {

		if (buttonState != buttonInput) {
			if (buttonInput == true) {
				risingEdge = true;
			} else {
				risingEdge = false;
			}
			if (buttonInput == false) {
				fallingEdge = true;
			} else {
				fallingEdge = false;
			}
		}

		buttonState = buttonInput;
	}

	public boolean GetOutput() {

		return buttonState;

	}

	public boolean GetRisingEdge() {

		return risingEdge;
	}

	public boolean GetFallingEdge() {
		return fallingEdge;
	}

}

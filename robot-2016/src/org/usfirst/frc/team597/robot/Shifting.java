package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shifting {
	ToggleButton toggleButton;

	// creates a Joystick object and names it joystickLeft
	Joystick joystickLeft;
	// creates a Joystick object and names it joystickRight
	Joystick joystickRight;
	// creates a doubleSolenoid and names it driveShifter
	DoubleSolenoid driveShifter;
	// creates a bolean and names it toggleState
	boolean toggleState;
	// Creates a Value named speedMode and sets it equal to the Value that sends
	// the actuator outward.
	final Value speedMode = Value.kForward;
	// Creates a value named torqueMode and sets it equal to the value that
	// sends the actuator inward.
	final Value torqueMode = Value.kReverse;
	
	String driveMode = "Torque Mode";

	public Shifting(Joystick jsLeft, Joystick jsRight) {
		// finished creating object by making it a new toggle
		toggleButton = new ToggleButton();

		// finished creating object by making it equal to jsLeft
		joystickLeft = jsLeft;
		// finished creating object by making it equal to jsRight
		joystickRight = jsRight;
		// finished creating object by making it a new doublSolenoid and giving
		// it the PCM port(0,1)
		driveShifter = new DoubleSolenoid(0, 1);
		// finished creating the boolean by making it false
		toggleState = false;
	}

	// This class uses the toggle output to change between speed and torque mode
	public void teleopPeriodic() {
		// calls on the input void in the toggle class and sets the 7th button
		// on the left and right joysticks as inputs for the toggle class
		toggleButton.input(joystickLeft.getRawButton(7) || joystickRight.getRawButton(7));
		// sets toggle state equal to the output boolean from the toggle class
		toggleState = toggleButton.Output();

		// if toggleState is false it sets the driveShifter to torqueMode
		if (toggleState == false) {
			driveShifter.set(torqueMode);
			driveMode = "TORQUE";
		} else {
			// if toggleState is true it sets the driverShifter to speedMode
			driveShifter.set(speedMode);
			driveMode = "SPEED";
		}
		SmartDashboard.putString("Drive Mode", driveMode);
	}
}

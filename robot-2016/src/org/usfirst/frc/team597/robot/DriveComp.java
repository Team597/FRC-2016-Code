package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.PIDOutput;

public class DriveComp implements PIDOutput {
	double leftSpeed;
	double rightSpeed;

	public DriveComp() {

	}
	// Implements the PIDOutput which we use to the set the value of left and
	// right speed which are sent out when this class is called.

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		// sets left speed equal to the pid output
		leftSpeed = output;
		// sets right speed equal to the opposite of pid
		rightSpeed = output;

	}

	public double leftSpeed() {
		// sends the value of left speed, when this public is called
		return leftSpeed;
	}

	public double rightSpeed() {
		// sends the value of right speed, when this public is called
		return rightSpeed;
	}

}

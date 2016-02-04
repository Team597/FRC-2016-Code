package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.VictorSP;

public class DriveComp implements PIDOutput {
	VictorSP victorLeftFront;
	VictorSP victorLeftBack;
	VictorSP victorRightFront;
	VictorSP victorRightBack;
	double leftSpeed;
	double rightSpeed;

	public DriveComp(VictorSP speedLeftFront, VictorSP speedLeftBack, VictorSP speedRightFront, VictorSP speedRightBack,
			Joystick jsLeft) {
		victorLeftFront = speedLeftFront;
		victorLeftBack = speedLeftBack;
		victorRightFront = speedRightFront;
		victorRightBack = speedRightBack;
	}

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		leftSpeed = output;
		rightSpeed = output * -1;
		victorLeftFront.set(leftSpeed);
		victorLeftBack.set(leftSpeed);
		victorRightFront.set(rightSpeed);
		victorRightBack.set(rightSpeed);
	}

}

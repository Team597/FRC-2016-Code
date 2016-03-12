package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm {
	AnalogInput hallEffectTop;
	AnalogInput hallEffectBot;
	Encoder armEncoder;
	PIDController armPID;
	DriveComp PIDOutput;
	VictorSP armVictor;
	int armState;
	int pidSetpoint;
	boolean decreaseCheck;
	boolean increaseCheck;
	Joystick armStick;

	public Arm(Joystick jsArm) {
		hallEffectTop = new AnalogInput(1);
		hallEffectBot = new AnalogInput(2);
		armEncoder = new Encoder(3, 4);
		PIDOutput = new DriveComp();
		armPID = new PIDController(1.0 / 100.0, 0.0, 0.0, armEncoder, PIDOutput);
		armVictor = new VictorSP(4);
		armState = 0;
		pidSetpoint = 1000;
		decreaseCheck = false;
		increaseCheck = false;
		armStick = jsArm;
	}

	public void teleopPeriod() {
		SmartDashboard.putNumber("Hall effect top", hallEffectTop.getValue());
		SmartDashboard.putNumber("Hall effect bot", hallEffectBot.getValue());
		if (armStick.getRawButton(1) == false) {
			armVictor.set(armStick.getZ() * .80);
		} else if (armStick.getRawButton(1) == true) {
			armVictor.set(armStick.getZ());
		}
		// if (pidSetpoint <= 10000) {
		// if (armStick.getRawButton(12) != increaseCheck &&
		// armStick.getRawButton(12) == true) {
		// pidSetpoint += 1000;
		// }
		// } else {
		// pidSetpoint = 10000;
		// }
		// increaseCheck = armStick.getRawButton(12);
		//
		// if (pidSetpoint >= 0) {
		// if (armStick.getRawButton(11) != decreaseCheck &&
		// armStick.getRawButton(11) == true) {
		// pidSetpoint -= 1000;
		// }
		// } else {
		// pidSetpoint = 0;
		// }
		// decreaseCheck = armStick.getRawButton(11);
		//
		// if (armStick.getRawButton(4) == true) {
		// armPID.setSetpoint(pidSetpoint);
		// }
		// armPID.enable();
		// if (armState == 0) {
		// if (hallEffectTop.getValue() < 170) {
		// armState = 1;
		// } else if (hallEffectBot.getValue() < 170) {
		// armState = 2;
		// }
		// armVictor.set(PIDOutput.leftSpeed());
		// } else if (armState == 1) {
		// if (PIDOutput.leftSpeed() > 0) {
		// armVictor.set(0);
		// } else if (PIDOutput.leftSpeed() <= 0) {
		// armState = 0;
		// }
		// } else if (armState == 2) {
		// if (PIDOutput.leftSpeed() < 0) {
		// armVictor.set(0);
		// } else if (PIDOutput.leftSpeed() >= 0) {
		// armState = 0;
		// }
		// }

	}
}

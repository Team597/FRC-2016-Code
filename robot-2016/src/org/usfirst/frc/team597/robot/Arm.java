package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.VictorSP;

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
		armVictor = new VictorSP(10);
		armState = 0;
		pidSetpoint = 1000;
		decreaseCheck = false;
		increaseCheck = false;
		armStick = jsArm;
	}

	public void teleopPeriod() {
		if (pidSetpoint <= 10000) {
			if (armStick.getRawButton(3) != increaseCheck && armStick.getRawButton(3) == true) {
				pidSetpoint += 1000;
			}
		} else {
			pidSetpoint = 10000;
		}
		increaseCheck = armStick.getRawButton(3);

		if (pidSetpoint >= 0) {
			if (armStick.getRawButton(4) != decreaseCheck && armStick.getRawButton(4) == true) {
				pidSetpoint -= 1000;
			}
		} else {
			pidSetpoint = 0;
		}
		decreaseCheck = armStick.getRawButton(4);

		if (armStick.getRawButton(1) == true) {
			armPID.setSetpoint(pidSetpoint);
		}
		armPID.enable();
		if (armState == 0) {
			if (hallEffectTop.getValue() < 170) {
				armState = 1;
			} else if (hallEffectBot.getValue() < 170) {
				armState = 2;
			}
			armVictor.set(PIDOutput.leftSpeed());
		} else if (armState == 1) {
			if (PIDOutput.leftSpeed() > 0) {
				armVictor.set(0);
			} else if (PIDOutput.leftSpeed() <= 0) {
				armState = 0;
			}
		} else if (armState == 2) {
			if (PIDOutput.leftSpeed() < 0) {
				armVictor.set(0);
			} else if (PIDOutput.leftSpeed() >= 0) {
				armState = 0;
			}
		}

	}
}

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
		} else if (armStick.getRawButton(1) == true && armState == 0) {
			armVictor.set(armStick.getZ());
		}

		if (hallEffectTop.getValue() < 170) {
			armState = 1;
		}
		if (hallEffectBot.getValue() < 170) {
			armState = 2;
		} else {
			armState = 0;
		}

		if (armState == 1) {
			if(armStick.getZ() > 0){
				armVictor.set(0);
			}else{
				armState = 0;
			}
		}
		if(armState == 2){
			if(armStick.getZ() < 0){
				armVictor.set(0);
			}else{
				armState = 0;
			}
		}

	}
}

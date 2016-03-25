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
	VictorSP winchVictor;
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
		winchVictor = new VictorSP(7);
		armState = 0;
		pidSetpoint = 1000;
		decreaseCheck = false;
		increaseCheck = false;
		armStick = jsArm;
	}

	public void teleopPeriod() {
		SmartDashboard.putNumber("Hall effect top", hallEffectTop.getValue());
		SmartDashboard.putNumber("Hall effect bot", hallEffectBot.getValue());
			if (hallEffectTop.getValue() < 170) {
				if (-armStick.getZ() < 0) {
					armVictor.set(-armStick.getZ());
				} else {
					armVictor.set(0);
				}
			} else if (hallEffectBot.getValue() < 170) {
				if (-armStick.getZ() > 0) {
					armVictor.set(-armStick.getZ());
				} else {
					armVictor.set(0);
				}
			} else {
				armVictor.set(-armStick.getZ());
			}

//			if(-winchStick1.getY() > 0){
//			 winchVictor1.set(-winchStick1.getY() * .50);
//			 winchVictor2.set(-winchStick1.getY() * .50);
//			}else{
//				winchVictor1.set(0);
//				winchVictor2.set(0);
//			 }
	}
}

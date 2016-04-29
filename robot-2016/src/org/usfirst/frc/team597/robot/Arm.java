package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm {
	AnalogInput hallEffectTop;
	AnalogInput hallEffectBot;
	VictorSP armVictor;
	VictorSP winchVictor;
	Joystick armStick;
	DoubleSolenoid winch;
	ToggleButton toggleWinch;
	String winchState;
	Encoder armEn;

	public Arm(Joystick jsArm) {
		hallEffectTop = new AnalogInput(1);
		hallEffectBot = new AnalogInput(2);
		armVictor = new VictorSP(4);
		winchVictor = new VictorSP(8);
		armStick = jsArm;
		winch = new DoubleSolenoid(2, 3);
		toggleWinch = new ToggleButton();
		winchState = "Active";
		armEn = new Encoder(8, 9);
	}

	public void teleopPeriod() {
		SmartDashboard.putNumber("Hall effect top", hallEffectTop.getValue());
		SmartDashboard.putNumber("Hall effect bot", hallEffectBot.getValue());
		SmartDashboard.putString("Winch State:", winchState);
		if (armStick.getRawButton(6) == true) {
			if (armStick.getZ() > 0) {
				winchVictor.set(armStick.getZ() * .58);
			} else {
				winchVictor.set(0);
			}
			if (armStick.getRawButton(1) == false) {
				winch.set(Value.kForward);
				winchState = "Active";
			} else {
				winch.set(Value.kReverse);
				winchState = "Inactive";
			}
		}
		if (armStick.getRawButton(6) == false) {
			if (armStick.getRawButton(6) == false) {
				if (hallEffectTop.getValue() < 170) {
					armEn.reset();
					if (armStick.getZ() < 0) {
						armVictor.set(-armStick.getZ() * .50);
					} else {
						armVictor.set(0);
					}
				} else if (hallEffectBot.getValue() < 170) {
					if (armStick.getZ() > 0) {
						armVictor.set(-armStick.getZ() * .50);
					} else {
						armVictor.set(0);
					}
				} else {
					if (armStick.getRawButton(3) == false) {
						if (armEn.get() < 4200) {
							armVictor.set(-armStick.getZ());
						} else if (armEn.get() > 4200) {
							armVictor.set(-armStick.getZ() * .40);
						}
					} else {
						armVictor.set(-armStick.getZ());
					}
				}
			}
		}
	}
}

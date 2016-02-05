package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.VictorSP;

public class Arm {
	// Motor to extend arm
	VictorSP extendingMotor;
	
	// Encoder on arm motor
	Encoder armEncoder;
	
	// PID
	PIDController armLimit;
	
	// Potentiometer
	
	// Driver's shooting joystick
	Joystick joystickShooting;
	
	public Arm(Joystick jsArm) {
		extendingMotor = new VictorSP(8);
		
		armEncoder = new Encoder(2,3);
		
		// armLimit = new PIDController();
				
		joystickShooting = jsArm;
	}
	
	
}

package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.VictorSP;

public class Shooter {
	// Motors for pivoting the shooter
	VictorSP articulatingMotorOne; 
	VictorSP articulatingMotorTwo;
	
	// 2 motors and 1 piston for shooting boulder
	VictorSP shootingMotorOne;
	VictorSP shootingMotorTwo;
	DoubleSolenoid solShoot;
	// Motor speeds for intake
	double intakeSpeed = -0.3;		//30% of motor full speed
	// Motor speeds for shooting
	double shootingSpeed = 1.0;		//100% of motor speed
	
	// Driver's shooting joystick 
	Joystick joystickShooting;
	
	public Shooter() {
		// Initializing objects
		shootingMotorOne = new VictorSP(4);
		shootingMotorTwo = new VictorSP(5);
		solShoot = new DoubleSolenoid(2, 3);
		
		joystickShooting = new Joystick(2);
	}
	
	// Things to run during teleop
	public void teleopPeriodic() {
		intake();
		
	}
	
	// Code for in-taking boulders 
	public void intake() {
		// Activates active intake when button 2 is pressed
		if (joystickShooting.getRawButton(2) == true) {
			shootingMotorOne.set(intakeSpeed);
			shootingMotorTwo.set(intakeSpeed);
		}
		
	}
	
	// Code for shooting boulders
	public void shoot() {
		// Shoots boulder if trigger is pressed
		if (joystickShooting.getRawButton(1) == true) {
			shootingMotorOne.set(shootingSpeed);
			shootingMotorTwo.set(shootingSpeed);
			
			// Active piston
			solShoot.set(Value.kForward);
			
		}
		else {
			// Retract piston
			solShoot.set(Value.kReverse);
			
		}
	}
	
	
	
}

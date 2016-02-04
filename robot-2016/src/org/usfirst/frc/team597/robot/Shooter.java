package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
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
	
	Timer timer;
	
	public Shooter() {
		// Initializing objects
		shootingMotorOne = new VictorSP(4);
		shootingMotorTwo = new VictorSP(5);
		solShoot = new DoubleSolenoid(2, 3);
		
		joystickShooting = new Joystick(2);
		
		timer = new Timer();
	}
	
	// Things to run during teleop
	public void teleopPeriodic() {
		intake();
		shoot();
		articulate();
		
	}
	
	// Code for in-taking boulders 
	public void intake() {
		// Activates active-intake when button 2 is pressed
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
			
			Timer.delay(1);
			
			// Active piston
			solShoot.set(DoubleSolenoid.Value.kForward);
			
			Timer.delay(0.5);
			
			// Deactivates piston
			solShoot.set(DoubleSolenoid.Value.kReverse);
			
		}
		
	}
	
	// Code to articulate shooter
	public void articulate() {
		// Moves shooter to joystick Y position
		if (joystickShooting.getRawButton(7) == true) {
			articulatingMotorOne.set(joystickShooting.getY());
			articulatingMotorTwo.set(joystickShooting.getY());
			
		}
		
	}
	
	
	
}

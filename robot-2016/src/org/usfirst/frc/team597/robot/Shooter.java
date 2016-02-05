package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.VictorSP;

public class Shooter {
	// 2 motors for pivoting the shooter
	VictorSP pivotingMotorOne; 
	VictorSP pivotingMotorTwo;
	
	// 2 motors and 1 piston for shooting boulder
	VictorSP shootingMotorOne;
	VictorSP shootingMotorTwo;
	DoubleSolenoid solShoot;
	
	// Motor speed for intake
	double intakeSpeed = -0.3;		//30% of motor full speed
	// Motor speed for shooting
	double shootingSpeed = 1.0;		//100% of motor speed
	
	// Encoder on articulating motor
	Encoder pivotingEncoder;
	
	// PID for articulating shooter
	PIDController pivotingController;
	
	// Value to stop arm
	double STOPHERE = 5.0;
	
	// Driver's shooting joystick 
	Joystick joystickShooting;
	
	
	public Shooter(Joystick jsShooting) {
		// Initializing objects
		pivotingMotorOne = new VictorSP(4);
		pivotingMotorTwo = new VictorSP(5);
		
		shootingMotorOne = new VictorSP(6);
		shootingMotorTwo = new VictorSP(7);
		solShoot = new DoubleSolenoid(2, 3);
		
		pivotingEncoder = new Encoder(0,1);
		
		pivotingController = new PIDController(0.1, 0.001, 0.0, pivotingEncoder, pivotingMotorOne);
		
		joystickShooting = jsShooting;
		
		
	}
	
	// Things to run during teleop
	public void teleopPeriodic() {
		intake();
		shoot();
		articulate();
		
	}
	
	// Code for in-taking boulders 
	public void intake() {
		// Activates active-intake with button 3
		if (joystickShooting.getRawButton(3) == true) {
			shootingMotorOne.set(intakeSpeed);
			shootingMotorTwo.set(intakeSpeed);
		}
		
	}
	
	// Code for shooting boulders
	public void shoot() {
		// Rev-up shooting motors with trigger
		if (joystickShooting.getRawButton(1) == true) {
			shootingMotorOne.set(shootingSpeed);
			shootingMotorTwo.set(shootingSpeed);
			
			// Activate piston with button 2
			if (joystickShooting.getRawButton(2) == true) {
				solShoot.set(Value.kForward);
			}
			else { 
				solShoot.set(Value.kReverse);
			}
		}
		else {
			shootingMotorOne.set(0);
			shootingMotorTwo.set(0);
		}
		
		
	}
	
	// Code for articulating shooter
	public void articulate() {
		// Enable PIDController
		pivotingController.enable();
		
		// Set shooter to joystick Y position when button 7 is pressed
		if (joystickShooting.getRawButton(7) == true) {
			pivotingController.setSetpoint( (joystickShooting.getY() + 1.0) * 2.5 );	// test this
			
		}
		
	}
	
	
	
}

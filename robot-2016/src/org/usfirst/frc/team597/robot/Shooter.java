package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.VictorSP;

public class Shooter {
	double intakeSpeed = 1.0;							// 100% intake motor speed
	final Value defaultPistonSetting = Value.kForward;	// Constantly activates arm pistons
	
	// Driver's shooting joystick 
	Joystick joystickShooting;
		
	// 2 pistons that support arm
	DoubleSolenoid pistons;
	
	// 2 motors for pivoting the shooter
	VictorSP pivotingMotorOne;
	VictorSP pivotingMotorTwo;
	
	// 1 motor for in-take roller
	VictorSP intakeMotor;
	
	/*
	PID pivoting objects below. Ignore for now.
	
	double HIGH_GOAL = 5000;		// High goal PID position
	double LOW_GOAL = 2000;			// Low goal PID position
	double encoderNewPosition = 0;	// Encoder PID variable
	
	// Encoder on articulating motor
	Encoder pivotingEncoder;
	
	// Limit switch for resetting encoder
	DigitalInput botLimitSwitch;
	
	// PIDs for articulating shooter
	PIDController leftPivotingController;
	PIDController rightPivotingController;
	
	*/
	
	public Shooter(Joystick jsShooting) {
		// Initializes joystick
		joystickShooting = jsShooting;
		
		// Initializes solenoids
		pistons = new DoubleSolenoid(4 , 5);
		
		// Initializes motors
		pivotingMotorOne = new VictorSP(5);
		pivotingMotorTwo = new VictorSP(6);
		intakeMotor = new VictorSP(9);
		
		/* 
		PID pivoting objects below. Ignore for now.
		
		// Initializes encoder
		pivotingEncoder = new Encoder(0, 1);
		
		// Initializes limit switch
		botLimitSwitch = new DigitalInput(2);
		
		// Initializes PID
		leftPivotingController = new PIDController(0.01, 0.0, 0.0, pivotingEncoder, pivotingMotorOne);
		rightPivotingController = new PIDController(0.01, 0.0, 0.0, pivotingEncoder, pivotingMotorTwo);
		
		*/
		
	}
	
	// Code for in-taking and shooting boulders 
	public void intake() {
		// Activates intake rollers
		if (joystickShooting.getRawButton(4) == true) {
			intakeMotor.set(-intakeSpeed);
		}
		// Activates shooting rollers
		else if (joystickShooting.getRawButton(2) == true) {
			intakeMotor.set(intakeSpeed);
		}
		// Stops intake motors
		else {
			intakeMotor.set(0);
		}
	}
	
	public void armPivot() {
		// Pivots arm with shooting joystick's Y axis
		pivotingMotorOne.set( (-joystickShooting.getY()) * 0.79);
		pivotingMotorTwo.set( (-joystickShooting.getY()) * 0.79);
	}
	
	// Activates supporting arm pistons
	public void armPistons() {
		pistons.set(defaultPistonSetting);
	}
	
	// For use in autonomous
	public void armAuto(double speed) {
		pivotingMotorOne.set(-speed);
		pivotingMotorTwo.set(-speed);
	}
	
}

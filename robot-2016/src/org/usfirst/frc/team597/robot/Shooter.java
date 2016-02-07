package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	
	// Limit switch for resetting encoder
	DigitalInput botLimitSwitch;
	
	// PID for articulating shooter
	PIDController pivotingController;
		
	// Arm pivoting positions for PID
	double TOP_POS = 5000;
	
	// Driver's shooting joystick 
	Joystick joystickShooting;
	
	
	public Shooter(Joystick jsShooting) {
		// Initializes motors
		pivotingMotorOne = new VictorSP(8);		// motor on port 8 being tested with PID rn
		pivotingMotorTwo = new VictorSP(5);
		shootingMotorOne = new VictorSP(6);
		shootingMotorTwo = new VictorSP(7);
		
		// Initializes solenoid
		solShoot = new DoubleSolenoid(2, 3);
		
		// Initializes encoder
		pivotingEncoder = new Encoder(0, 1);
		
		// Initializes limit switch
		botLimitSwitch = new DigitalInput(2);
		
		// Initializes PID
		pivotingController = new PIDController(0.01, 0.0, 0.0, pivotingEncoder, pivotingMotorOne);
		
		// Initializes joystick
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
		// Pivot shooter to joystick Y position when button 7 is pressed
		if (joystickShooting.getRawButton(7) == true) {
			// Enable PID
			pivotingController.enable();
			
			// PID maps motor to joystick
			pivotingController.setSetpoint(joystickShooting.getY() * 5000);
		}
		else {
			// Disable PID and reset Encoder
			pivotingController.disable();
			pivotingEncoder.reset();
		}
		
		// PID to pivot shooter to preset positions
		if (joystickShooting.getRawButton(4) == true) {
			pivotingController.enable();
			pivotingController.setSetpoint(TOP_POS);
		}
		else {
			// Disable PID
			pivotingController.disable(); 
		}
		
		// SmartDashboard debugging stuff			
		SmartDashboard.putNumber("joystick value", joystickShooting.getY() );
		SmartDashboard.putNumber("motor value", pivotingMotorOne.get() );
		
		SmartDashboard.putNumber("encoder get", pivotingEncoder.get() );
		SmartDashboard.putNumber("encoder rate", pivotingEncoder.getRate() );
		SmartDashboard.putNumber("encoder distance", pivotingEncoder.getDistance() );
	}
	
	
	
}

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
	double shootingSpeed = 1.0;		// 100% Shooting motors speed
	double intakeSpeed = -0.3;		// 30% Intake motor speed
	
	double HIGH_GOAL = 5000;		// High goal PID position
	double LOW_GOAL = 2000;			// Low goal PID position
	
	double encoderNewPosition = 0;	// Encoder PID variable
	
	// Driver's shooting joystick 
	Joystick joystickShooting;
		
	// 2 motors and 1 piston for shooting boulder
	VictorSP shootingMotorOne;
	VictorSP shootingMotorTwo;
	DoubleSolenoid solShoot;
		
	// 2 motors for pivoting the shooter
	VictorSP pivotingMotorOne;
	VictorSP pivotingMotorTwo;
	
	// 1 motor for in-take roller
	VictorSP intakeMotor;
	
	// Encoder on articulating motor
	Encoder pivotingEncoder;
	
	// Limit switch for resetting encoder
	DigitalInput botLimitSwitch;
	
	// PIDs for articulating shooter
	PIDController leftPivotingController;
	PIDController rightPivotingController;
	
	public Shooter(Joystick jsShooting) {
		// Initializes joystick
		joystickShooting = jsShooting;
				
		// Initializes motors
		shootingMotorOne = new VictorSP(7);
		shootingMotorTwo = new VictorSP(8);
		pivotingMotorOne = new VictorSP(5);
		pivotingMotorTwo = new VictorSP(6);
		intakeMotor = new VictorSP(9);
		
		// Initializes solenoid
		solShoot = new DoubleSolenoid(2, 3);
		
		// Initializes encoder
		pivotingEncoder = new Encoder(0, 1);
		
		// Initializes limit switch
		botLimitSwitch = new DigitalInput(2);
		
		// Initializes PID
		leftPivotingController = new PIDController(0.01, 0.0, 0.0, pivotingEncoder, pivotingMotorOne);
		rightPivotingController = new PIDController(0.01, 0.0, 0.0, pivotingEncoder, pivotingMotorTwo);
		
	}
	
	// Things to run during teleop
	public void teleopPeriodic() {
		shoot();
		manualArticulate();
		setPositionArticulate();
		intake();
	}
	
	// Code for shooting boulders
	public void shoot() {
		// Rev-up shooting motors with green button
		if (joystickShooting.getRawButton(1) == true) {
			shootingMotorOne.set(shootingSpeed);
			shootingMotorTwo.set(shootingSpeed);
			
			// Activate piston with red button
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
	
	// Code for manually articulating shooter
	public void manualArticulate() {
		// Pivot shooter up when left joystick is pushed up
		if (joystickShooting.getRawButton(8) == true) {
			leftPivotingController.enable();		// Enables PIDs
			rightPivotingController.enable();
			
			encoderNewPosition = pivotingEncoder.get() + 50;
		}
		// Pivot shooter down when left joystick is pushed down
		if (joystickShooting.getRawButton(7) == true) {
			leftPivotingController.enable();		// Enables PIDs
			rightPivotingController.enable();
			
			encoderNewPosition = pivotingEncoder.get() - 50;
		}
		// PIDs pivot shooter
		leftPivotingController.setSetpoint(encoderNewPosition);
		rightPivotingController.setSetpoint(encoderNewPosition);
		
		// Disable PIDs
		leftPivotingController.disable();
		rightPivotingController.disable();
		
		// Reset encoder when limitswitch is hit
		if (botLimitSwitch.get() == false) {
			pivotingEncoder.reset();
		}

		// SmartDashboard debugging stuff
		SmartDashboard.putNumber("pivoting motor value", pivotingMotorOne.get() );
		SmartDashboard.putBoolean("limit switch state", botLimitSwitch.get() );
		SmartDashboard.putNumber("encoder get", pivotingEncoder.get() );
		
	}
	
	// Code for pivoting shooter to preset positions
	public void setPositionArticulate() {
		// Left joystick at right position pivots shooter to high goal position
		if (joystickShooting.getRawButton(6) == true) {
			leftPivotingController.enable();
			rightPivotingController.enable();
			
			leftPivotingController.setSetpoint(HIGH_GOAL);
			rightPivotingController.setSetpoint(HIGH_GOAL);
		}
		// Left joystick at left position pivots shooter to low goal position
		else if (joystickShooting.getRawButton(5) == true) {
			leftPivotingController.enable();
			rightPivotingController.enable();
			
			leftPivotingController.setSetpoint(LOW_GOAL);
			rightPivotingController.setSetpoint(LOW_GOAL);
		}
		else {
			leftPivotingController.disable();
			rightPivotingController.disable();
		}
	}
	
	// Code for in-taking boulders 
	public void intake() {
		// Activates active-intake with button 3
		if (joystickShooting.getRawButton(3) == true) {
			shootingMotorOne.set(intakeSpeed);
			shootingMotorTwo.set(intakeSpeed);
			intakeMotor.set(intakeSpeed);
		}
		
	}
	
}

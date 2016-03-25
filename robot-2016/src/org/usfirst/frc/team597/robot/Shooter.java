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
	double shootingSpeed = 1.0;		// 100% shooting motors speed
	double intakeSpeed = 1.0;		// 100% intake motor speed
	double pivotingSpeed = 0.70;		// 100% pivoting motor speed
	final Value defaultPistonSetting = Value.kForward;
	
	// Driver's shooting joystick 
	Joystick joystickShooting;
	
	// 2 motors and 1 piston for shooting boulder
//	VictorSP shootingMotorOne;
//	VictorSP shootingMotorTwo;
//	DoubleSolenoid solShoot;
	
	// 2 pistons to support arm
	DoubleSolenoid pistons;
	
	// 2 motors for pivoting the shooter
	VictorSP pivotingMotorOne;
	VictorSP pivotingMotorTwo;
	
	// 1 motor for in-take roller
	VictorSP intakeMotor;
	
	/*
	 * Stuff for pivoting arm with PID below. Ignore for now.
	 */
	
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
	
	public Shooter(Joystick jsShooting) {
		// Initializes joystick
		joystickShooting = jsShooting;
		
		// Initializes motors
//		shootingMotorOne = new VictorSP(7);
//		shootingMotorTwo = new VictorSP(8);
		pivotingMotorOne = new VictorSP(5);
		pivotingMotorTwo = new VictorSP(6);
		intakeMotor = new VictorSP(9);
		
		// Initializes solenoids
//		solShoot = new DoubleSolenoid(2, 3);
		pistons = new DoubleSolenoid(4 , 5);
		
		/*
		 * Stuff for pivoting arm with PID below. Ignore for now.
		 */
		
		// Initializes encoder
		pivotingEncoder = new Encoder(0, 1);
		
		// Initializes limit switch
		botLimitSwitch = new DigitalInput(2);
		
		// Initializes PID
		leftPivotingController = new PIDController(0.01, 0.0, 0.0, pivotingEncoder, pivotingMotorOne);
		rightPivotingController = new PIDController(0.01, 0.0, 0.0, pivotingEncoder, pivotingMotorTwo);
		
	}
	
	// Code for shooting boulders
	public void shoot() {
		// Green button revs up motors
//		if (joystickShooting.getRawButton(8) == true) {
//			shootingMotorOne.set(shootingSpeed);
//			shootingMotorTwo.set(shootingSpeed);
//		}
//		else {
//			shootingMotorOne.set(0);
//			shootingMotorTwo.set(0);
//		}
		
		// Red button activates piston
//		if (joystickShooting.getRawButton(6) == true) {
//			solShoot.set(Value.kReverse);
//		}
//		else {
//			solShoot.set(Value.kForward);
//		}
	}
	
	// Code for in-taking boulders 
	public void intake() {
		// Yellow button activates intake
		if (joystickShooting.getRawButton(4) == true) {
			intakeMotor.set(-intakeSpeed);			// Rollers
//			shootingMotorOne.set(-intakeSpeed);		// Shooters
//			shootingMotorTwo.set(-intakeSpeed);
		}
		else if(joystickShooting.getRawButton(2) == true){
			intakeMotor.set(intakeSpeed);
//			shootingMotorOne.set(intakeSpeed);
//			shootingMotorTwo.set(intakeSpeed);
		}
		
		else{
			intakeMotor.set(0);				// Rollers
//			shootingMotorOne.set(0);		// Shooters
//			shootingMotorTwo.set(0);
		}
	}
	
	public void armBasic() {
		// Left js in up position moves arm up
		pivotingMotorOne.set( (-joystickShooting.getY()) * 0.79);
		pivotingMotorTwo.set( (-joystickShooting.getY()) * 0.79);
//		if (joystickShooting.getRawButton(8) == true) {
//			pivotingMotorOne.set(pivotingSpeed);
//			pivotingMotorTwo.set(pivotingSpeed);
//		}
//		// Right js in down position moves arm down
//		else if (joystickShooting.getRawButton(7) == true) {
//			pivotingMotorOne.set(-pivotingSpeed);
//			pivotingMotorTwo.set(-pivotingSpeed);
//		}
//		else {
//			pivotingMotorOne.set(0);
//			pivotingMotorTwo.set(0);
//		}
	}
	
	// Activates lifitng pistons
	public void pistons() {
		pistons.set(defaultPistonSetting);
	}
	
	public void armAuto(double speed) {
		pivotingMotorOne.set(-speed);
		pivotingMotorTwo.set(-speed);
	}
	
	/*
	 * Stuff for pivoting arm with PID. Ignore for now. 
	 */
	
	// Pivoting arm with PID
	public void articulateWithPID() {
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
	
	// Pivoting shooter to preset positions with PID
	public void pivotingToPositions() {
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
	
}

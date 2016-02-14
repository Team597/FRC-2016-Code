package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;

public class PracticeAuto {
	VictorSP rightFrontMotor;
	VictorSP leftFrontMotor;
	VictorSP rightBackMotor;
	VictorSP leftBackMotor;
	VictorSP leftShooter;
	VictorSP rightShooter;
	
	
	public void robotinit(){
	rightFrontMotor = new VictorSP(0);
	leftFrontMotor = new VictorSP(1);
	rightBackMotor = new VictorSP(2);
	leftBackMotor = new VictorSP(3);
	leftShooter = new VictorSP(4);
	rightShooter = new VictorSP(5);
	
	}
	public void autonomousPeriodic() {
	// 1meter = 1
	// 3 seconds = 90 degrees
		rightFrontMotor.set(1);
		leftFrontMotor.set(1);
		leftBackMotor.set(1);
		rightBackMotor.set(1);
		Timer.delay(2);
		rightFrontMotor.set(1);
		leftFrontMotor.set(-1);
		leftBackMotor.set(-1);
		rightBackMotor.set(1);
		Timer.delay(3);
		rightFrontMotor.set(1);
		leftFrontMotor.set(1);
		leftBackMotor.set(1);
		rightBackMotor.set(1);
		Timer.delay(25);
		rightFrontMotor.set(-1);
		leftFrontMotor.set(1);
		leftBackMotor.set(1);
		rightBackMotor.set(-1);
		Timer.delay(3);
		rightFrontMotor.set(1);
		leftFrontMotor.set(1);
		leftBackMotor.set(1);
		rightBackMotor.set(1);
		Timer.delay(5);
		rightFrontMotor.set(-1);
		leftFrontMotor.set(1);
		leftBackMotor.set(1);
		rightBackMotor.set(-1);
		Timer.delay(1.5);
		rightFrontMotor.set(0);
		leftFrontMotor.set(0);
		leftBackMotor.set(0);
		rightBackMotor.set(0);
		leftShooter.set(1);
		rightShooter.set(1);
		Timer.delay(3);
		leftShooter.set(0);
		rightShooter.set(0);
		
		
		
		
		
		
		
		
	
	
		
		
}
}
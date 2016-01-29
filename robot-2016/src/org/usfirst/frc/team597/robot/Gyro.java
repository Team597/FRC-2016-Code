package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Gyro {
	AnalogGyro turnAngle;

	public Gyro() {
		turnAngle = new AnalogGyro(0);
	}

	public void turnAngle(){
		SmartDashboard.putDouble("Gyro Angle:", turnAngle.getAngle());
	}
	
}
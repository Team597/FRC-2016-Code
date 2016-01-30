package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Gyro {
	AnalogGyro driveGyro;

	public Gyro() {
		driveGyro = new AnalogGyro(0);
	}
	@SuppressWarnings("deprecation")
	public void TeleopPeriodic(){
	SmartDashboard.putDouble("gyro", driveGyro.getAngle());	
	}

}

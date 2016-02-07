package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Gyro {
	Joystick leftJoystick;
	Joystick rightJoystick;
	AnalogGyro driveGyro;
	PIDController drivePID;
	PIDOutput PIDOutput;
	DriveComp DriveComp;

	public Gyro(Joystick jsLeft, Joystick jsRight) {
		leftJoystick = jsLeft;
		rightJoystick = jsRight; 
		driveGyro = new AnalogGyro(0);
		drivePID = new PIDController(1.0, 0.0, 0.0, driveGyro, PIDOutput);
		DriveComp = new DriveComp(jsLeft, jsRight);
	}

	public void TeleopPeriodic() {
		SmartDashboard.putNumber("gyro", driveGyro.getAngle());
	}

	public double leftSpeed() {
		return DriveComp.leftSpeed();
	}

	public double rightSPeed() {
		return DriveComp.rightSpeed();
	}

}

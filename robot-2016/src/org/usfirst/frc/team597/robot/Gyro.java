package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Gyro {
	AnalogGyro driveGyro;
	DriveComp DriveComp;
	PIDController drivePID;
	int gyroState;
	double gyroAngle;
	boolean gyroGet;

	public Gyro() {
		driveGyro = new AnalogGyro(0);
		DriveComp = new DriveComp();
		drivePID = new PIDController(1 / 10.0, 0.0, 0.0, driveGyro, DriveComp);
		gyroState = 0;
		gyroAngle = 0;
		gyroGet = false;
	}

	// This class is used to activate the pid loop which allows the code to
	// drive straight if the drive is not. Doesnt set the value of the motors,
	// but rather it sends a value which can be used to send the value of the
	// motors
	public void TeleopPeriodic() {
		// sends the Angle to the smart dashboard in the drive station
		SmartDashboard.putNumber("gyro", driveGyro.getAngle());
	}

	public void SetSetpoint(Joystick js) {
		// when button six is pressed on the joystick, it sets the value of gyro
		// angle to the angle of the gyro
		if (js.getRawButton(6) != gyroGet && js.getRawButton(6) == true) {
			gyroAngle = driveGyro.getAngle();
		}
		gyroGet = js.getRawButton(6);
	}

	public double leftSpeed() {
		// sends left speed from drive compensation when this public is called
		return DriveComp.leftSpeed();
	}

	public double rightSPeed() {
		// sends right sped from drive compensation when this public is called
		return DriveComp.rightSpeed();
	}

}

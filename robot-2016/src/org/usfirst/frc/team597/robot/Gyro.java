package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Gyro {
	AnalogGyro driveGyro;
	PIDController drivePID;
	PIDOutput PIDOutput;
	DriveComp DriveComp;
	VictorSP victorLeftFront;
	VictorSP victorLeftBack;
	VictorSP victorRightFront;
	VictorSP victorRightBack;

	public Gyro(Joystick jsLeft, Joystick jsRight) {
		driveGyro = new AnalogGyro(0);
		drivePID = new PIDController(1.0, 0.0, 0.0, driveGyro, PIDOutput);
	}

	public void TeleopPeriodic() {
		SmartDashboard.putNumber("gyro", driveGyro.getAngle());
	}

}

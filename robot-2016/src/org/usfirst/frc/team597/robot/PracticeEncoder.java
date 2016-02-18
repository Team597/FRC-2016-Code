package org.usfirst.frc.team597.robot;

import edu.wpi.first.wpilibj.Encoder;

public class PracticeEncoder {
	Encoder enc;

	public void robotInit() {
		enc = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
		enc.setMaxPeriod(.1);
		enc.setMinRate(10);
		enc.setDistancePerPulse(5);
		enc.setReverseDirection(true);
		enc.setSamplesToAverage(7);
		
		}
	public void teleopPeriodic() {
		int count = enc.get();
	}
	}
 
package org.usfirst.frc.team597.robot;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.CameraServer;

public class CameraFeeds {
	public static final int btCamCenter = 1;
	public static final int btCamRight = 2;

	public static final String camNameCenter = "cam0";
	public static final String camNameRight = "cam1";
	public static final int imgQuality = 60;

	private final int camCenter;
	private final int camRight;
	private int curCam;
	private Image frame;
	private CameraServer server;
	ToggleButton toggleButton;

	Joystick joystickShooting;

	public CameraFeeds(Joystick jsShooting) {
		// Get camera ids by supplying camera name ex 'cam0', found on roborio
		// web interface
		camCenter = NIVision.IMAQdxOpenCamera(camNameCenter,
				NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		camRight = NIVision.IMAQdxOpenCamera(camNameRight,
				NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		curCam = camCenter;
		// Img that will contain camera img
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		// Server that we'll give the img to
		server = CameraServer.getInstance();
		server.setQuality(imgQuality);

		joystickShooting = jsShooting;

		toggleButton = new ToggleButton();
	}

	public void init() {
		changeCam(camCenter);
	}

	public void run() {
//		toggleButton.input(joystickShooting.getRawButton(5));
//		if (toggleButton.Output() == false) { // change ports for Hector
//			changeCam(camCenter);
//		} else if (toggleButton.Output() == true) { // change ports
//													// for
//			// Hector
//			changeCam(camRight);
//		}
		if(joystickShooting.getRawButton(7)){
		changeCam(camCenter);
		}else if(joystickShooting.getRawButton(8)){
			changeCam(camRight);
		}
		updateCam();
	}

	/**
	 * Stop aka close camera stream
	 */
	public void end() {
		NIVision.IMAQdxStopAcquisition(curCam);
	}

	/**
	 * Change the camera to get imgs from to a different one
	 * 
	 * @param newId
	 *            for camera
	 */
	public void changeCam(int newId) {
		NIVision.IMAQdxStopAcquisition(curCam);
		NIVision.IMAQdxConfigureGrab(newId);
		NIVision.IMAQdxStartAcquisition(newId);
		curCam = newId;
	}

	/**
	 * Get the img from current camera and give it to the server
	 */
	public void updateCam() {
		NIVision.IMAQdxGrab(curCam, frame, 1);
		server.setImage(frame);
	}
}
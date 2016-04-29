package org.usfirst.frc.team597.robot;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.VisionException;

import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.CameraServer;

public class CameraFeeds {
	public static final int btCamCenter = 1;
	public static final int btCamRight = 2;

	public static final String camNameCenter = "cam0";
	public static final String camNameRight = "cam1";
	public static final int imgQuality = 60;

	private int camCenter;
	private int camRight;
	private int curCam;
	private Image frame;
	private CameraServer server;
	ToggleButton toggleButton;

	boolean cameraState = true;

	Joystick joystickShooting;

	public CameraFeeds(Joystick jsShooting) {
		// Get camera ids by supplying camera name ex 'cam0', found on roborio
		// web interface
		try{
			camCenter = NIVision.IMAQdxOpenCamera(camNameCenter,
					NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		}
		catch(VisionException d){
			cameraState = false;
		}
		if (cameraState == true){
		//camCenter = NIVision.IMAQdxOpenCamera(camNameCenter,
		//		NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		camRight = NIVision.IMAQdxOpenCamera(camNameRight,
				NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		curCam = camCenter;
		// Img that will contain camera img
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		// Server that we'll give the img to
		server = CameraServer.getInstance();
		server.setQuality(imgQuality);
		}
		joystickShooting = jsShooting;
		toggleButton = new ToggleButton();
	}

	public void init() {
		changeCam(camCenter);
	}

	public void run() {
		if (joystickShooting.getRawButton(7)) {
			changeCam(camCenter);
		} else if (joystickShooting.getRawButton(8)) {
			changeCam(camRight);
		}
		updateCam();
	}

	/**
	 * Stop aka close camera stream
	 */
	public void end() {
		try {
			NIVision.IMAQdxStopAcquisition(curCam);
		} catch (VisionException c) {
			cameraState = false;
		}
		if (cameraState == true) {
			NIVision.IMAQdxStopAcquisition(curCam);

		}
	}

	/**
	 * Change the camera to get imgs from to a different one
	 * 
	 * @param newId
	 *            for camera
	 */
	public void changeCam(int newId) {

		NIVision.IMAQdxStopAcquisition(curCam);
		try {
			NIVision.IMAQdxConfigureGrab(newId);
		} catch (VisionException e) {
			newId = curCam;
			try {
				NIVision.IMAQdxConfigureGrab(newId);

			} catch (VisionException a) {
				cameraState = false;
			}
		} finally {
			if (cameraState == true) {
				NIVision.IMAQdxConfigureGrab(newId);
				NIVision.IMAQdxStartAcquisition(newId);
				curCam = newId;
			}
		}
	}

	/**
	 * Get the img from current camera and give it to the server
	 */
	public void updateCam() {
		try {
			NIVision.IMAQdxGrab(curCam, frame, 1);
		} catch (VisionException b) {
			cameraState = false;
		}
		if (cameraState == true) {
			NIVision.IMAQdxGrab(curCam, frame, 1);
			server.setImage(frame);
		}
	}
}

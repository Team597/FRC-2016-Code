package org.usfirst.frc.team597.robot;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.CameraServer;

public class CameraFeeds {
	private final int camCenter;
	private final int camRight;
	private int curCam;
	private Image frame;
	private CameraServer server;
	
	Joystick joystickShooting;
	
	public CameraFeeds(Joystick jsShooting) {
        // Get camera ids by supplying camera name ex 'cam0', found on roborio web interface
        camCenter = NIVision.IMAQdxOpenCamera(Config.CameraFeeds.camNameCenter, NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        camRight = NIVision.IMAQdxOpenCamera(Config.CameraFeeds.camNameRight, NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        curCam = camCenter;
        // Img that will contain camera img
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        // Server that we'll give the img to
        server = CameraServer.getInstance();
        server.setQuality(Config.CameraFeeds.imgQuality);
        
        joystickShooting = jsShooting;
	}
	
	public void init()
	{
		changeCam(camCenter);
	}
	
	public void run()
	{
		if(joystickShooting.getRawButton(10))		// change ports for Hector
			changeCam(camCenter);
		
		if(joystickShooting.getRawButton(11))		// change ports for Hector
			changeCam(camRight);
		
		updateCam();
	}
	
	/**
	 * Stop aka close camera stream
	 */
	public void end()
	{
		NIVision.IMAQdxStopAcquisition(curCam);
	}
	
	/**
	 * Change the camera to get imgs from to a different one
	 * @param newId for camera
	 */
	public void changeCam(int newId)
    {
		NIVision.IMAQdxStopAcquisition(curCam);
    	NIVision.IMAQdxConfigureGrab(newId);
    	NIVision.IMAQdxStartAcquisition(newId);
    	curCam = newId;
    }
    
	/**
	 * Get the img from current camera and give it to the server
	 */
    public void updateCam()
    {
    	NIVision.IMAQdxGrab(curCam, frame, 1);
        server.setImage(frame);
    }
}

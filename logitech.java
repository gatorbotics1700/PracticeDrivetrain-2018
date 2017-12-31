package org.usfirst.frc.team1700.robot;

import org.opencv.core.Mat;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class logitech extends Camera {

	public logitech(int port) {
		CameraServer.getInstance().
		this.videoCamera = new UsbCamera("logitech", port);
	}

	@Override
	public double getFOV() {
		return 50;
	}

}

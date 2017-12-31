package org.usfirst.frc.team1700.robot;

import org.opencv.core.Mat;

import edu.wpi.cscore.VideoCamera;

public abstract class Camera {
	public abstract double getFOV();
	protected VideoCamera videoCamera;
	protected int exposure;
	public enum Resolution {
		k240, k480, k720, k1080, k2160
	}
	public enum AspectRatio {
		k4x3, k16x9
	}
	protected AspectRatio aspectRatio;
	protected Resolution res;
	public Camera (Resolution resolution) {
		res = resolution;
	}
	public int getYResolution() {
		switch (res) {
			case k240: 
				return 240;
			case k480:
				return 480;
			case k720:
				return 720;
			case k1080:
				return 1080;
			case k2160:
				return 2160;
			default:
				return -1;
		}
	}
}

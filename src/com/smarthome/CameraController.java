package com.smarthome;

import rajawali.Camera;

/**
 * The Camera controller is used for a smooth turn of the
 * viewpoint and it used the singleton pattern for easy
 * access the camera from everywhere.
 * @author Björn & Joschka
 *
 */
public class CameraController {
	
	//*******************************************
	//**		Variables
	//*******************************************
	
	public SmartHomeActivity activity;
//	private float t;
	private long timestamp;
	private float duration = 750;
/*	private float[] p0 = new float[6], p1 = new float[6],
			p2 = new float[6], p3 = new float[6];*/
	
	public Camera src = new Camera();		//Source Camera
	public Camera tar = new Camera();		//Target Camera
	
	//*******************************************
	//**		Methods
	//*******************************************
		
	
	/**
	 * checks if the camera is ready
	 * 
	 * @return boolean true if the camera is ready else false
	 */
	public boolean ready() {
		return src != null && tar != null;
	}
	
	/**
	 * The method b is used to determiate a point on a 
	 * bezier path with a time parameter.
	 * 
	 * @param t the time to get the point
	 * @param p0 point0
	 * @param p1 point1
	 * @param p2 point2
	 * @param p3 point3
	 * @return returns a point of the bezier path
	 */
	public float b(float t, float p0, float p1, float p2, float p3) {
		float tn = 1 - t;
		float tn2 = tn * tn;
		float t2 = t * t;
		return tn * tn2 * p0 + 3 * tn2 * t * p1 + 3 * tn * t2 * p2 + t2 * t * p3;
	}
	
	/**
	 * 
	 * 
	 * @param effort
	 */
	public void logSource(boolean effort) {
		//t = 0;
		timestamp = System.currentTimeMillis();
		src.setPosition(activity.mRenderer.getCamera().getPosition());
		src.setRotation(activity.mRenderer.getCamera().getRotation());
		if (effort) {
			tar.setPosition(src.getPosition());
			tar.setRotation(src.getRotation());
		}
	}
	
	/**
	 * The Method returns a Camera Object with the preferences of the
	 * source camera.
	 * 
	 * @return A new Camera Instance with the source camera preferences
	 */
	public Camera giveSource() {
		Camera cam = new Camera();
		cam.setPosition(src.getPosition());
		cam.setRotation(src.getRotation());
		return cam;
	}
	
	/**
	 * The Method returns a Camera Object with the preferences of the
	 * target camera.
	 * 
	 * @return A new Camera Instance with the target camera preferences
	 */
	public Camera giveTarget() {
		Camera cam = new Camera();
		cam.setPosition(tar.getPosition());
		cam.setRotation(tar.getRotation());
		return cam;
	}
	
	/**
	 * Sets the target Camera
	 * 
	 * @param target the target camera
	 */
	public void setTarget(Camera target) {
		tar = target;
	}
	
	/**
	 * Björn magic or maybe useless?
	 */
	public void setCamera() {
		setCamera(Math.min(1, (System.currentTimeMillis() - timestamp) / duration));
	}
	
	/**
	 * Björn magic or maybe useless?
	 * 
	 * @param t time?!?
	 */
	public void setCamera(float t) {
		Camera cam = activity.mRenderer.getCamera();
		cam.setX(b(t, src.getX(), src.getX(), tar.getX(), tar.getX()));
		cam.setY(b(t, src.getY(), src.getY(), tar.getY(), tar.getY()));
		cam.setZ(b(t, src.getZ(), src.getZ(), tar.getZ(), tar.getZ()));
		cam.setRotX(b(t, src.getRotX(), src.getRotX(), tar.getRotX(), tar.getRotX()));
		cam.setRotY(b(t, src.getRotY(), src.getRotY(), tar.getRotY(), tar.getRotY()));
		cam.setRotZ(b(t, src.getRotZ(), src.getRotZ(), tar.getRotZ(), tar.getRotZ()));
	}
	
	/**
	 * This method creates a new camera that is on a point of a 
	 * bezier path.
	 * 
	 * @param t time
	 * @return a new Camera Object with a point of a bezier path
	 */
	public Camera getCamera(float t) {
		Camera cam = new Camera();
		cam.setX(b(t, src.getX(), src.getX(), tar.getX(), tar.getX()));
		cam.setY(b(t, src.getY(), src.getY(), tar.getY(), tar.getY()));
		cam.setZ(b(t, src.getZ(), src.getZ(), tar.getZ(), tar.getZ()));
		cam.setRotX(b(t, src.getRotX(), src.getRotX(), tar.getRotX(), tar.getRotX()));
		cam.setRotY(b(t, src.getRotY(), src.getRotY(), tar.getRotY(), tar.getRotY()));
		cam.setRotZ(b(t, src.getRotZ(), src.getRotZ(), tar.getRotZ(), tar.getRotZ()));
		return cam;
	}
	
	/**
	 * Creates a new Camera with a position and rotation
	 * 
	 * @param x the x position of the camera
	 * @param y the y position of the camera
	 * @param z the z position of the camera
	 * @param rotX the x rotation of the camera
	 * @param rotY the y rotation of the camera
	 * @param rotZ the z rotation of the camera
	 */
	public void createRelativeDisplacement(float x, float y, float z, float rotX, float rotY, float rotZ) {
		Camera cam = giveTarget();
		cam.setX(cam.getX() + x);
		cam.setY(cam.getY() + y);
		cam.setZ(cam.getZ() + z);
		cam.setRotX(cam.getRotX() + rotX);
		cam.setRotY(cam.getRotY() + rotY);
		cam.setRotZ(cam.getRotZ() + rotZ);
		setTarget(cam);
	}
}

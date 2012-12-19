package com.smarthome;

import java.util.HashMap;
import java.util.LinkedList;

import rajawali.Camera;

public class Room {
	public float x, y, z = -35, rotx = 0, roty = 0, rotz = 0;
	public Camera camera;
	private int id;
	public LinkedList<Gesture> gestures = new LinkedList<Gesture>();

	public Room(float x, float y, int id, SmartHomeActivity activity) {
		this.x = x;
		this.y = y;
		camera = new Camera();
		camera.setPosition(x, y, z);
		camera.setRotation(rotx, roty, rotz);
		this.id = id;
	}
	
	public boolean fire(float x, float y, SmartHomeActivity activity, boolean isLong) {
		for (Gesture gesture: gestures)
			if (gesture.fire(x, y, activity, isLong)) return true;
		return false;
	}
	
	public boolean gesture(SmartHomeActivity activity, String gestureStr) {
		for (Gesture gesture: gestures)
			if (gesture.gesture(activity, gestureStr)) return true;
		return false;
	}
	
	public int getID() {
		return this.id;
	}
	
	public void moveTo(SmartHomeActivity activity) {
		activity.camera.logSource(true);
		activity.camera.setTarget(camera);
	}
	
	public void appear(SmartHomeActivity activity) {
		for (Gesture gesture: gestures)
			gesture.appear(activity);
	}
	public void disappear(SmartHomeActivity activity) {
		for (Gesture gesture: gestures)
			gesture.disappear(activity);
	}
}

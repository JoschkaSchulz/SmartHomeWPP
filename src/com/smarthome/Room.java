package com.smarthome;

import java.util.LinkedList;

public class Room {
	private float x, y;
	public LinkedList<Gesture> gestures = new LinkedList<Gesture>();
	
	public Room(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void fire(float x, float y, SmartHomeActivity activity) {
		for (Gesture gesture: gestures) {
			gesture.fire(x, y, activity);
		}
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
}
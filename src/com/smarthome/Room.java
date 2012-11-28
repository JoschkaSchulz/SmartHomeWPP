package com.smarthome;

import java.util.LinkedList;

public class Room {
	private float x, y;
	private int id;
	public LinkedList<Gesture> gestures = new LinkedList<Gesture>();
	
	public Room(float x, float y, int id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public boolean fire(float x, float y, SmartHomeActivity activity) {
		for (Gesture gesture: gestures)
			if (gesture.fire(x, y, activity)) return true;
		return false;
	}
	
	public int getID() {
		return this.id;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
}

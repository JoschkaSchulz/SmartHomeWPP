package com.smarthome;

import java.util.LinkedList;

public class Room {
	public float x, y;
	public LinkedList<Gesture> gestures = new LinkedList<Gesture>();
	public void fire(float x, float y, SmartHomeActivity activity) {
		for (Gesture gesture: gestures) {
			gesture.fire(x, y, activity);
		}
	}
}

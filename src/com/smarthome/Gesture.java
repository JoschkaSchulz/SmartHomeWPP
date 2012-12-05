package com.smarthome;

import java.util.HashSet;

public abstract class Gesture {
	public static HashSet<Gesture> gestures = new HashSet<Gesture>();
	public float x1, y1, x2, y2;
	public Gesture parent;
	public static HashSet<Gesture> byParent(Gesture parent) {
		HashSet<Gesture> result = new HashSet<Gesture>();
		for (Gesture gesture : gestures)
			if (gesture.parent == parent) result.add(gesture);
		return result;
	}
	public Gesture() {gestures.add(this);}
	public Gesture(float x1, float y1, float x2, float y2) {
		gestures.add(this);
		this.x1 = x1 * SmartHomeActivity.screenWidth / 800;
		this.y1 = y1 * SmartHomeActivity.screenWidth / 800;
		this.x2 = x2 * SmartHomeActivity.screenHeight / 480;
		this.y2 = y2 * SmartHomeActivity.screenHeight / 480;
	}
	public boolean match(float x, float y) {
		return x >= x1 && x <= x2 && y >= y1 && y <= y2;
	}
	public boolean fire(float x, float y, SmartHomeActivity activity) {
		if (match(x, y)) {
			click(activity);
			System.out.println("Event " + toString() + " fired at [" + x + ", " + y + "]");
		}
		else return false;
		return true;
	}
	public void click(SmartHomeActivity activity) {
		imitate(activity, this);
	}
	public void imitate(SmartHomeActivity activity, Gesture parent) {
		for (Gesture gesture : byParent(this)) {
			gesture.imitate(activity, parent);
		}
	}
	public String toString() {
		return "Gesture: [" + x1 + ", " + y1 + ", " + x2 + ", " + y2 + "]";
	}
}

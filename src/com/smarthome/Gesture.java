package com.smarthome;

public abstract class Gesture {
	public float x1, y1, x2, y2;
	public Gesture() {}
	public Gesture(float x1, float y1, float x2, float y2) {
		this.x1 = x1 * 800 / 800;
		this.y1 = y1 * 800 / 800;
		this.x2 = x2 * 480 / 480;
		this.y2 = y2 * 480 / 480;
	}
	public boolean match(float x, float y) {
		return x > x1 && x < x2 && y > y1 && y < y2;
	}
	public void fire(float x, float y, SmartHomeActivity activity) {
		if (match(x, y)) click(activity);
	}
	public abstract void click(SmartHomeActivity activity);
}

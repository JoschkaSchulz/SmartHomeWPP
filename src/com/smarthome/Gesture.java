package com.smarthome;

public abstract class Gesture {
	public float x1, y1, x2, y2;
	public boolean match(float x, float y) {
		return x > x1 && x < x2 && y > y1 && y < y2;
	}
	public void fire(float x, float y) {
		if (match(x, y)) click();
	}
	public abstract void click();
}

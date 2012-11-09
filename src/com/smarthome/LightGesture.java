package com.smarthome;

public class LightGesture extends Gesture {
	private boolean on = false;
	private String action;
	
	public LightGesture() {}
	
	public LightGesture(float x1, float y1, float x2, float y2, String action) {
		super(x1, y1, x2, y2);
		this.action = action;
	}
	public void click(SmartHomeActivity activity) {
		on = !on;
		JSONBuilder.light(action, on ? 255 : 0, on ? 255 : 0, on ? 255 : 0, 0);
	}
}

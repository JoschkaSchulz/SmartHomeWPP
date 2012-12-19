package com.smarthome;

public class SliderGesture extends DebugGesture {
	public SliderGesture() {}
	public SliderGesture(float x1, float y1, float x2, float y2, String action, ActionListener listener) {
		super(x1, y1, x2, y2, action, listener);
	}
	public void click(SmartHomeActivity activity, boolean isLong) {
		listener.actionPerformed(action, activity);
		super.click(activity, isLong);
	}
	public String toString() {
		return super.toString() + " fires event " + action;
	}
}

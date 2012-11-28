package com.smarthome;

public class DebugGesture extends Gesture {
	public String action;
	public ActionListener listener;
	public DebugGesture() {}
	public DebugGesture(float x1, float y1, float x2, float y2, String action, ActionListener listener) {
		super(x1, y1, x2, y2);
		this.action = action;
		this.listener = listener;
	}
	public void click(SmartHomeActivity activity) {
		listener.actionPerformed(action, activity);
	}
	public String toString() {
		return super.toString() + " fires event " + action;
	}
}

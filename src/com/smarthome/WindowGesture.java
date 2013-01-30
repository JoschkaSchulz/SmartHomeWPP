package com.smarthome;


public class WindowGesture extends Gesture {
	public WindowUnit windowUnit;
	
	public WindowGesture() {}
	
	public WindowGesture(float x1, float y1, float x2, float y2) {
		super(x1, y1, x2, y2);
	}
	public WindowGesture(float x1, float y1, float x2, float y2, WindowUnit windowUnit) {
		super(x1, y1, x2, y2);
		this.windowUnit = windowUnit;
	}
	public void click(SmartHomeActivity activity, boolean isLong) {
		windowUnit.click(activity, isLong);
		if (!isLong) {
			super.click(activity, isLong);
		}
	}
	public void setOpen(boolean open) {
		windowUnit.setOpen(open);
	}
	public void imitate(SmartHomeActivity activity, Gesture parent, String action, int[] parameters) {
		windowUnit.imitate(activity, parent, action, parameters);
	}
	public void setCm(int cm) {
		windowUnit.setCm(cm);
	}
	public String toString() {
		return super.toString() + windowUnit.toString();
	}
	public void appear(SmartHomeActivity activity) {
		createSample(R.drawable.lamp, activity);
		activity.imagePane.addView(images.get(0));
	}
	public void disappear(SmartHomeActivity activity) {
		activity.imagePane.removeView(images.get(0));
	}
}
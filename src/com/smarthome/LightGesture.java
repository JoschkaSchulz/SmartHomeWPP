package com.smarthome;


public class LightGesture extends Gesture {
	public LightUnit lightUnit;
	
	public LightGesture() {}
	
	public LightGesture(float x1, float y1, float x2, float y2) {
		super(x1, y1, x2, y2);
	}
	public LightGesture(float x1, float y1, float x2, float y2, LightUnit lightUnit) {
		super(x1, y1, x2, y2);
		this.lightUnit = lightUnit;
	}
	public void click(SmartHomeActivity activity, boolean isLong) {
		lightUnit.click(activity, isLong);
		if (!isLong) {
			super.click(activity, isLong);
		}
	}
	public void setLight(boolean on) {
		lightUnit.setLight(on);
	}
	public void imitate(SmartHomeActivity activity, Gesture parent, String action, int[] parameters) {
		lightUnit.imitate(activity, parent, action, parameters);
	}
	public void setColor(int red, int green, int blue) {
		lightUnit.setColor(red, green, blue);
	}
	public String toString() {
		return super.toString() + lightUnit.toString();
	}
	public void appear(SmartHomeActivity activity) {
		if (lightUnit.isIntensity)
			createSample(R.drawable.lamp, activity);
		else
			createSample(R.drawable.lamp, activity);
		activity.imagePane.addView(images.get(0));
	}
	public void disappear(SmartHomeActivity activity) {
		activity.imagePane.removeView(images.get(0));
	}
}

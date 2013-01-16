package com.smarthome;


public class CurtainGesture extends Gesture {
	public CurtainUnit curtainUnit;
	
	public CurtainGesture() {}
	
	public CurtainGesture(float x1, float y1, float x2, float y2) {
		super(x1, y1, x2, y2);
	}
	public CurtainGesture(float x1, float y1, float x2, float y2, CurtainUnit curtainUnit) {
		super(x1, y1, x2, y2);
		this.curtainUnit = curtainUnit;
	}
	public void click(SmartHomeActivity activity, boolean isLong) {
		curtainUnit.click(activity, isLong);
		super.click(activity, isLong);
	}
	public void setCurtain(boolean open) {
		curtainUnit.setCurtain(open);
	}
	public String toString() {
		return super.toString() + curtainUnit.toString();
	}
	public void appear(SmartHomeActivity activity) {
		createSample(R.drawable.curtain, activity);
		activity.imagePane.addView(images.get(0));
	}
	public void disappear(SmartHomeActivity activity) {
		activity.imagePane.removeView(images.get(0));
	}
}

package com.smarthome;

import android.widget.ImageView;
import android.widget.ImageView.ScaleType;


public class LightGesture extends Gesture {
	private boolean on = false;
	private String action;
	
	public LightGesture() {}
	
	public LightGesture(float x1, float y1, float x2, float y2, String action) {
		super(x1, y1, x2, y2);
		this.action = action;
	}
	public void click(SmartHomeActivity activity, boolean isLong) {
		if (isLong) {
			activity.slider.lightGesture = this;
			activity.slider.actionPerformed("enter", activity);
		} else {
			System.out.println("Light "+(on ? "on" : "off"));
			setLight(!on);
			super.click(activity, isLong);
		}
	}
	public void setLight(boolean on) {
		this.on = on;

		for (LightController lc : LightController.byRole(action)) {
			lc.light.setPower(on ? 5f : 0f);
		}
		
		try {
			sendMessageToProxy send = new sendMessageToProxy();
//			send.execute("172.16.0.200", "12349", "LP.LIGHTCONTROL", "topic", JSONBuilder.light(action, on ? 255 : 0, on ? 255 : 0, on ? 255 : 0, 0));	
			send.execute("172.16.0.200", "12349", "LP.LIGHTCONTROL", "topic", JSONBuilder.light(action, on, 255));
		} catch(Exception e) {
			System.out.println("Senden Fehlgeschlagen");
			this.on = !on;
		}
	}
	public String toString() {
		return super.toString() + " to light " + action + " (currently " + (on ? "on" : "off") + ")";
	}
	public void appear(SmartHomeActivity activity) {
		createSample(R.drawable.lamp, activity);
		activity.imagePane.addView(images.get(0));
	}
	public void disappear(SmartHomeActivity activity) {
		activity.imagePane.removeView(images.get(0));
	}
}

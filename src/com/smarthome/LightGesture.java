package com.smarthome;

import android.graphics.AvoidXfermode;

public class LightGesture extends Gesture {
	private boolean on = false;
	private String action;
	
	public LightGesture() {}
	
	public LightGesture(float x1, float y1, float x2, float y2, String action) {
		super(x1, y1, x2, y2);
		this.action = action;
	}
	public void click(SmartHomeActivity activity) {
		System.out.println("Light "+(on ? "on" : "off"));
		on = !on;
		
		switch(activity.mRenderer.room.getID()) {
			default:
				break;
			case 0:
				activity.mRenderer.setLight0(on);
				break;
			case 1:
				activity.mRenderer.setLight1(on);
				break;
			case 2:
				activity.mRenderer.setLight2(on);
				break;
			case 3:
				activity.mRenderer.setLight3(on);
				break;
		}
		
		try {
			sendMessageToProxy send = new sendMessageToProxy();
			send.execute("172.16.0.200", "12349", "LP.LIGHTCONTROL", "topic", JSONBuilder.light(action, on ? 255 : 0, on ? 255 : 0, on ? 255 : 0, 0));	
		} catch(Exception e) {
			System.out.println("Senden Fehlgeschlagen");
			on = !on;
		}
	}
}

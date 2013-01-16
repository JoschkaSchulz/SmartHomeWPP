package com.smarthome;


public class LightUnit {
	public boolean on = false;
	public int red = 255;
	public int green = 255;
	public int blue = 255;
	private String action;
	
	public LightUnit() {}
	
	public LightUnit(String action) {
		this.action = action;
	}
	public void click(SmartHomeActivity activity, boolean isLong) {
		if (isLong) {
			activity.slider.lightUnit = this;
			activity.slider.actionPerformed("enter", activity);
		} else {
			System.out.println("Light "+(on ? "on" : "off"));
			setLight(!on);
		}
	}
	public void setLight(boolean on) {
		this.on = on;

		for (LightController lc : LightController.byRole(action)) {
			lc.light.setPower(on ? 5f : 0f);
		}
		
		try {
			sendMessageToProxy send = new sendMessageToProxy();
			send.execute("172.16.0.200", "12349", "LP.LIGHTCONTROL", "topic", JSONBuilder.light(action, on ? red : 0, on ? green : 0, on ? blue : 0, 0));	
//			send.execute("172.16.0.200", "12349", "LP.LIGHTCONTROL", "topic", JSONBuilder.light(action, on, 255));
		} catch(Exception e) {
			System.out.println("Senden Fehlgeschlagen");
			this.on = !on;
		}
	}
	public void imitate(SmartHomeActivity activity, Gesture parent, String action, int[] parameters) {
		if (action.equals("setLight")) {
			setLight(parameters[0] != 0 ? true : false);
		}
		if (action.equals("setColor")) {
			setColor(parameters[0], parameters[1], parameters[2]);
		}
	}
	public void setColor(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		setLight(true);
	}
	public String toString() {
		return " to light " + action + " (currently " + (on ? "on" : "off") + ")";
	}
}

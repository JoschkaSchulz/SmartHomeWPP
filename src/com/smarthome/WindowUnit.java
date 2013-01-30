package com.smarthome;


public class WindowUnit {
	public boolean open = false;
	public int cm = 20;
	public boolean isIntensity = false;
	private String action;
	
	public WindowUnit() {}
	
	public WindowUnit(String action) {
		this.action = action;
	}

	public WindowUnit(String action, boolean isIntensity) {
		this.action = action;
		this.isIntensity = isIntensity;
	}

	public void click(SmartHomeActivity activity, boolean isLong) {
		if (isLong) {
			activity.wSlider.windowUnit = this;
			activity.wSlider.actionPerformed("enter", activity);
		} else {
			System.out.println("Light "+(open ? "on" : "off"));
			setOpen(!open);
		}
	}
	public void setOpen(boolean open) {
		this.open = open;

		for (LightController lc : LightController.byRole(action)) {
			lc.light.setPower(open ? 5f : 0f);
		}
		
		try {
			sendMessageToProxy send = new sendMessageToProxy();
			send.execute("172.16.0.200", "12349", "LP.LIGHTCONTROL", "topic", JSONBuilder.light(action, open, cm));
		} catch(Exception e) {
			System.out.println("Senden Fehlgeschlagen");
			this.open = !open;
		}
	}
	public void imitate(SmartHomeActivity activity, Gesture parent, String action, int[] parameters) {
		if (action.equals("setOpen")) {
			setOpen(parameters[0] != 0 ? true : false);
		}
		if (action.equals("setCm")) {
			setCm(parameters[0]);
		}
	}
	public void setCm(int cm) {
		this.cm = cm;
		setOpen(true);
	}
	public String toString() {
		return " to window " + action + " (currently " + (open ? "open" : "closed") + ")";
	}
}

package com.smarthome;


public class CurtainUnit {
	public boolean open = false;
	private String action;
	
	public CurtainUnit() {}
	
	public CurtainUnit(String action) {
		this.action = action;
	}
	public void click(SmartHomeActivity activity, boolean isLong) {
		System.out.println("Curtain "+(open ? "open" : "close"));
		setCurtain(!open);
	}
	public void setCurtain(boolean open) {
		this.open = open;		
		try {
			sendMessageToProxy send = new sendMessageToProxy();
			send.execute("172.16.0.200", "12349", "LP.LIGHTCONTROL", "topic", JSONBuilder.curtain(action, open));	
		} catch(Exception e) {
			System.out.println("Senden Fehlgeschlagen");
			this.open = !open;
		}
	}
	public String toString() {
		return " to curtain " + action + " (currently " + (open ? "open" : "close") + ")";
	}
}

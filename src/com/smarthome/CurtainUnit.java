package com.smarthome;


public class CurtainUnit {
	
	//*******************************************
	//**		Variables
	//*******************************************
	
	public boolean open = false;
	private String action;
	
	//*******************************************
	//**		Constructor
	//*******************************************
	
	/**
	 * Initialize a empty Unit
	 */
	public CurtainUnit() {}
	
	/**
	 * Initialize the Unit with a action String
	 * @param action
	 */
	public CurtainUnit(String action) {
		this.action = action;
	}
	
	//*******************************************
	//**		Methods
	//*******************************************
	
	/**
	 * Inverts the status of the curtain
	 * 
	 * @param activity the Android activity
	 * @param isLong true if the long click is activated
	 */
	public void click(SmartHomeActivity activity, boolean isLong) {
		System.out.println("Curtain "+(open ? "open" : "close"));
		setCurtain(!open);
	}
	
	/**
	 * Sets the Curtain and send the status to the activeMQ
	 * 
	 * @param open the status of the curtain
	 */
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
	
	/**
	 * This method is used to generate LogCat Logs
	 */
	public String toString() {
		return " to curtain " + action + " (currently " + (open ? "open" : "close") + ")";
	}
}

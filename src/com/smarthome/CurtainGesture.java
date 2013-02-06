package com.smarthome;

/**
 * The Gesture Class for the blinds and curtains
 * 
 * @author Björn & Joschka
 *
 */
public class CurtainGesture extends Gesture {
	//*******************************************
	//**		Variables
	//*******************************************
		
	public CurtainUnit curtainUnit;
	
	//*******************************************
	//**		Constructor
	//*******************************************
		
	
	/**
	 * Initialize an empty Gesture
	 */
	public CurtainGesture() {}
	
	/**
	 * Initialize the CurtainGesture without a CurtainUnit
	 * 
	 * @param x1 the top left point
	 * @param y1 the top left point
	 * @param x2 the bottom right point
	 * @param y2 the bottom right point
	 */
	public CurtainGesture(float x1, float y1, float x2, float y2) {
		super(x1, y1, x2, y2);
	}
	
	/**
	 * Initialize the CurtainGesture with a CurtainUnit
	 * 
	 * @param x1 the top left point
	 * @param y1 the top left point
	 * @param x2 the bottom right point
	 * @param y2 the bottom right point
	 * @param curtainUnit the Unit with these one will response
	 */
	public CurtainGesture(float x1, float y1, float x2, float y2, CurtainUnit curtainUnit) {
		super(x1, y1, x2, y2);
		this.curtainUnit = curtainUnit;
	}
	
	//*******************************************
	//**		Methods
	//*******************************************
		
	/**
	 * Fires the click method of the Unit and the super class
	 */
	public void click(SmartHomeActivity activity, boolean isLong) {
		curtainUnit.click(activity, isLong);
		super.click(activity, isLong);
	}
	
	/**
	 * Sets the CurtainUnit
	 * 
	 * @param open boolean if the curtain is open
	 */
	public void setCurtain(boolean open) {
		curtainUnit.setCurtain(open);
	}
	
	/**
	 * toString method, is used to create logs on LogCat
	 */
	public String toString() {
		return super.toString() + curtainUnit.toString();
	}
	
	/**
	 * Draws the Icon
	 */
	public void appear(SmartHomeActivity activity) {
		createSample(R.drawable.curtain, activity);
		//moveSample(activity, (x1 * 3 + x2) / 4f, y1, (x1 + x2 * 3) / 4f, y2);
		//moveSample(activity, x1, y1, x2, y2);
		activity.imagePane.addView(images.get(0));
	}
	
	/**
	 * Removes the icon
	 */
	public void disappear(SmartHomeActivity activity) {
		activity.imagePane.removeView(images.get(0));
	}
}

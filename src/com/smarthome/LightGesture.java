package com.smarthome;


public class LightGesture extends Gesture {
	
	//*******************************************
	//**		Variables
	//*******************************************
	
	public LightUnit lightUnit;
	
	//*******************************************
	//**		Constructor
	//*******************************************
	
	/**
	 * Initialize an empty Gesture
	 */
	public LightGesture() {}
	
	/**
	 * Initialize the LightGesture without a LightUnit
	 * 
	 * @param x1 the top left point
	 * @param y1 the top left point
	 * @param x2 the bottom right point
	 * @param y2 the bottom right point
	 */
	public LightGesture(float x1, float y1, float x2, float y2) {
		super(x1, y1, x2, y2);
	}
	
	/**
	 * Initialize the LightGesture with a LightUnit
	 * 
	 * @param x1 the top left point
	 * @param y1 the top left point
	 * @param x2 the bottom right point
	 * @param y2 the bottom right point
	 * @param lightUnit the Unit with these one will response
	 */
	public LightGesture(float x1, float y1, float x2, float y2, LightUnit lightUnit) {
		super(x1, y1, x2, y2);
		this.lightUnit = lightUnit;
	}
	
	//*******************************************
	//**		Methods
	//*******************************************
	
	/**
	 * Fires the click method of the Unit and the super class
	 */
	public void click(SmartHomeActivity activity, boolean isLong) {
		lightUnit.click(activity, isLong);
		if (!isLong) {
			super.click(activity, isLong);
		}
	}
	
	/**
	 * Sets the LightUnit
	 * 
	 * @param on boolean if the light is on
	 */
	public void setLight(boolean on) {
		lightUnit.setLight(on);
	}
	
	/**
	 * 
	 */
	public void imitate(SmartHomeActivity activity, Gesture parent, String action, int[] parameters) {
		lightUnit.imitate(activity, parent, action, parameters);
	}
	
	/**
	 * sets the color of the light
	 * 
	 * @param red the red color 0 - 255
	 * @param green the green color 0 - 255
	 * @param blue the blue color 0 - 255
	 */
	public void setColor(int red, int green, int blue) {
		lightUnit.setColor(red, green, blue);
	}
	
	/**
	 * creates a string for the LogCat log
	 */
	public String toString() {
		return super.toString() + lightUnit.toString();
	}
	
	/**
	 * Draws the icon
	 */
	public void appear(SmartHomeActivity activity) {
		createSample(R.drawable.lamp, activity);
		activity.imagePane.addView(images.get(0));
	}
	
	/**
	 * Removes the icon
	 */
	public void disappear(SmartHomeActivity activity) {
		activity.imagePane.removeView(images.get(0));
	}
}

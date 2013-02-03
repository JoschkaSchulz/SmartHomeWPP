package com.smarthome;

import java.util.HashSet;
import java.util.LinkedList;

import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class Gesture {
	//*******************************************
	//**		Variables
	//*******************************************
	
	public static HashSet<Gesture> gestures = new HashSet<Gesture>();
	public float x1, y1, x2, y2;
	public Gesture parent;
	public LinkedList<ImageView> images = new LinkedList<ImageView>();
	
	public static HashSet<Gesture> byParent(Gesture parent) {
		HashSet<Gesture> result = new HashSet<Gesture>();
		for (Gesture gesture : gestures)
			if (gesture.parent == parent) result.add(gesture);
		return result;
	}
	
	//*******************************************
	//**		Constructor
	//*******************************************
	
	public Gesture() {gestures.add(this);}
	
	public Gesture(float x1, float y1, float x2, float y2) {
		gestures.add(this);
		this.x1 = x1 * SmartHomeActivity.screenWidth / 800;
		this.y1 = y1 * SmartHomeActivity.screenWidth / 800;
		this.x2 = x2 * SmartHomeActivity.screenHeight / 480;
		this.y2 = y2 * SmartHomeActivity.screenHeight / 480;
	}
	
	//*******************************************
	//**		Methods
	//*******************************************
	
	/**
	 * Checks if the point x and y matches the gesture coordinates
	 * 
	 * @param x point x to check
	 * @param y point y to check
	 * @return true if the point matches
	 */
	public boolean match(float x, float y) {
		return x >= x1 && x <= x2 && y >= y1 && y <= y2;
	}
	
	/**
	 * fire checks if a touch events matches the Event
	 * 
	 * @param x point x to match
	 * @param y point y to match
	 * @param activity the activity class
	 * @param isLong true if it is a long click
	 * @return true if the point matches the Gesture
	 */
	public boolean fire(float x, float y, SmartHomeActivity activity, boolean isLong) {
		if (match(x, y)) {
			click(activity, isLong);
			System.out.println("Event " + toString() + " fired at [" + x + ", " + y + "]");
		}
		else return false;
		return true;
	}
	
	/**
	 * unused
	 * 
	 * @param activity
	 * @param gesture
	 * @return
	 */
	public boolean gesture(SmartHomeActivity activity, String gesture) {
		return false;
	}
	
	/**
	 * fires the imitate method
	 * 
	 * @param activity the activity
	 * @param isLong true if longclick is activated
	 */
	public void click(SmartHomeActivity activity, boolean isLong) {
		imitate(activity, this, isLong);
	}
	
	/**
	 * Starts the imitate method for each parent
	 * 
	 * @param activity the activity
	 * @param parent the Gesture parent
	 * @param isLong true if long click
	 */
	public void imitate(SmartHomeActivity activity, Gesture parent, boolean isLong) {
		for (Gesture gesture : byParent(this)) {
			gesture.imitate(activity, parent, isLong);
		}
	}
	
	/**
	 * Starts the imitate method for each parent
	 * 
	 * @param activity the activity
	 * @param parent the Gesture parent
	 * @param isLong true if long click
	 * @param parameters int parameters
	 */
	public void imitate(SmartHomeActivity activity, Gesture parent, String action, int[] parameters) {
		for (Gesture gesture : byParent(this)) {
			gesture.imitate(activity, parent, action, parameters);
		}
	}
	
	/**
	 * creates a String for the LogCat log
	 */
	public String toString() {
		return "Gesture: [" + x1 + ", " + y1 + ", " + x2 + ", " + y2 + "]";
	}
	
	/**
	 * Adds a resource to the screen
	 * 
	 * @param resource the resource
	 * @param activity the activity
	 */
	public void createSample(int resource, SmartHomeActivity activity) {
		if (images.size() == 0) {
	        ImageView image = new ImageView(activity);
	        image.setImageResource(resource);
	        activity.prepareImageScaled((int)x1, (int)y1, (int)x2, (int)y2, image, 128, 128);
	        image.setScaleType(ScaleType.FIT_XY);
	        images.add(image); 
		}
	}
	
	/**
	 * Moves the sample to the given rect
	 * 
	 * @param activity the activity
	 * @param x1 the top left point
	 * @param y1 the top left point
	 * @param x2 the bottom right point
	 * @param y2 the bottom right point
	 */
	public void moveSample(SmartHomeActivity activity, float x1, float y1, float x2, float y2) {
		if (images.size() > 0)
			activity.prepareImageScaled((int)x1, (int)y1, (int)x2, (int)y2, images.get(0), 128, 128);
	}
	
	/**
	 * empty/not used 
	 * @param activity
	 */
	public void appear(SmartHomeActivity activity) {
		
	}
	
	/**
	 * empty/not used
	 * @param activity
	 */
	public void disappear(SmartHomeActivity activity) {
		
	}
}

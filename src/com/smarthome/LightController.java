package com.smarthome;

import java.util.HashSet;

import rajawali.lights.PointLight;

public class LightController {
	
	//*******************************************
	//**		Variables
	//*******************************************
	
	public PointLight light = new PointLight();
	public HashSet<String> roles = new HashSet<String>();
	public static HashSet<LightController> lights = new HashSet<LightController>();
	
	public static HashSet<LightController> byRole(String role) {
		HashSet<LightController> result = new HashSet<LightController>();
		for (LightController light : lights)
			if (light.hasRole(role)) result.add(light);
		return result;
	}
	
	//*******************************************
	//**		Methods
	//*******************************************
	
	/**
	 * Chcks if it contains the role
	 * 
	 * @param role the role that is searched
	 * @return true if the given role is found
	 */
	public boolean hasRole(String role) {
		return roles.contains(role);
	}
	
	/**
	 * Adds a role to the roles Set
	 * 
	 * @param role th role wich should be add
	 */
	public void addRole(String role) {
		if (!hasRole(role)) roles.add(role);
	}
	
	/**
	 * checks if the lights Set contains the instance
	 * 
	 * @return true if the lights contains the instance
	 */
	public boolean isPublished() {
		return lights.contains(this);
	}
	
	/**
	 * Add the instance to the lights set
	 */
	public void publish() {
		if (!isPublished()) lights.add(this);
	}
	
	/**
	 * Removes the instance from the lights set
	 */
	public void unPublish() {
		if (isPublished()) lights.remove(this);
	}
}

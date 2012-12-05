package com.smarthome;

import java.util.HashSet;

import rajawali.lights.PointLight;

public class LightController {
	public PointLight light = new PointLight();
	public HashSet<String> roles = new HashSet<String>();
	public static HashSet<LightController> lights = new HashSet<LightController>();
	public static HashSet<LightController> byRole(String role) {
		HashSet<LightController> result = new HashSet<LightController>();
		for (LightController light : lights)
			if (light.hasRole(role)) result.add(light);
		return result;
	}
	public boolean hasRole(String role) {
		return roles.contains(role);
	}
	public void addRole(String role) {
		if (!hasRole(role)) roles.add(role);
	}
	public boolean isPublished() {
		return lights.contains(this);
	}
	public void publish() {
		if (!isPublished()) lights.add(this);
	}
	public void unPublish() {
		if (isPublished()) lights.remove(this);
	}
}

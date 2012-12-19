package com.smarthome;

public interface ActionListener {
	public void actionPerformed(String action, SmartHomeActivity activity);
	public void actionPerformed(String action, SmartHomeActivity activity, float x, float y);
}

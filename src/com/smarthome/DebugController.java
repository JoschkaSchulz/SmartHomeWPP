package com.smarthome;

import android.view.View;

public class DebugController extends Room implements ActionListener {
	SmartHomeActivity activity;
	int mode = 0;
	int element = 0;
	public DebugController(float x, float y, int id, SmartHomeActivity activity) {
		super(x, y, id);
		this.activity = activity;
		//this.gestures.add(new DebugGesture(0, 0, 800, 480, "debug", this));
		this.gestures.add(new DebugGesture(0, 0, 200, 80, "mode", this));
		this.gestures.add(new DebugGesture(200, 0, 500, 80, "element", this));
		this.gestures.add(new DebugGesture(500, 0, 800, 80, "leave", this));
		this.gestures.add(new DebugGesture(0, 100, 200, 380, "<<", this));
		this.gestures.add(new DebugGesture(200, 100, 400, 380, "<", this));
		this.gestures.add(new DebugGesture(400, 100, 600, 380, ">", this));
		this.gestures.add(new DebugGesture(600, 100, 800, 380, ">>", this));
	}
	public String getMode() {
		switch (mode) {
		case 0: return "Camera";
		case 1: return "Light";
		}
		return "Unknown";
	}
	public String getElement() {
		switch (mode) {
		case 0:
			switch (element) {
			case 0: return "X";
			case 1: return "Y";
			case 2: return "Z";
			case 3: return "rotX";
			case 4: return "rotY";
			case 5: return "rotZ";
			}
			break;
		case 1:
			switch (element) {
			case 0: return "X";
			case 1: return "Y";
			case 2: return "Z";
			case 3: return "Intensity";
			}
			break;
		}
		return "unknown";
	}
	public String getInfo() {
		switch (mode) {
		case 0:
			return "[" + activity.camera.tar.getX() + ", " +
				activity.camera.tar.getY() + ", " +
				activity.camera.tar.getZ() + ", " +
				activity.camera.tar.getRotX() + ", " +
				activity.camera.tar.getRotY() + ", " +
				activity.camera.tar.getRotZ() + "]";
		case 1:
			return "[" + activity.mRenderer.mLight.getX() + ", " + activity.mRenderer.mLight.getY() + ", " + activity.mRenderer.mLight.getZ() + "]:" + activity.mRenderer.mLight.getPower();
		}
		return "unknown";
	}
	public void addMode() {
		if (++mode > 1) mode = 0;
		element = 0;
	}
	public void addElement() {
		++element;
		if (mode == 0) if (element > 5) element = 0;
		if (mode == 1) if (element > 3) element = 0;
	}
	public void updateMessage() {
		message = getMode() + " (" + getElement() + ") " + getInfo();
		messageUpdated = true;
	}
	public String message = "Info:";
	public boolean messageUpdated = true;
	public void actionPerformed(String action, SmartHomeActivity activity) {
		if (action.equals("enter")) {
			activity.image.setVisibility(View.VISIBLE);
			activity.label.setText(message);
			messageUpdated = false;
			activity.isDebug = true;
			return;
		}
		if (action.equals("leave")) {
			activity.image.setVisibility(View.INVISIBLE);
			activity.label.setText("Info:");
			activity.isDebug = false;
			if (activity.room == null) activity.room = activity.rooms.get(0);
			return;
		}
		if (action.equals("mode")) addMode();
		if (action.equals("element")) addElement();
		int change = 0;
		if (action.equals("<<")) change = -5;
		if (action.equals("<")) change = -1;
		if (action.equals(">")) change = 1;
		if (action.equals(">>")) change = 5;
		if (mode == 0 && change != 0) {
			activity.room = null;
			activity.camera.logSource(false);
			if (element == 0) activity.camera.createRelativeDisplacement(change, 0, 0, 0, 0, 0);
			if (element == 1) activity.camera.createRelativeDisplacement(0, change, 0, 0, 0, 0);
			if (element == 2) activity.camera.createRelativeDisplacement(0, 0, change, 0, 0, 0);
			if (element == 3) activity.camera.createRelativeDisplacement(0, 0, 0, change, 0, 0);
			if (element == 4) activity.camera.createRelativeDisplacement(0, 0, 0, 0, change, 0);
			if (element == 5) activity.camera.createRelativeDisplacement(0, 0, 0, 0, 0, change);
		}
		if (mode == 1 && element == 0) activity.mRenderer.mLight.setX(activity.mRenderer.mLight.getX() + change);
		if (mode == 1 && element == 1) activity.mRenderer.mLight.setY(activity.mRenderer.mLight.getY() + change);
		if (mode == 1 && element == 2) activity.mRenderer.mLight.setZ(activity.mRenderer.mLight.getZ() + change);
		if (mode == 1 && element == 3) activity.mRenderer.mLight.setPower(activity.mRenderer.mLight.getPower() + change);
		updateMessage();
		if (messageUpdated) {
			activity.label.setText(message);
			messageUpdated = false;
		}
	}
}

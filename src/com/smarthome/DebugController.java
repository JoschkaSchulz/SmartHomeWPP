package com.smarthome;

public class DebugController extends Room implements ActionListener {
	SmartHomeRenderer renderer;
	int mode = 0;
	int element = 0;
	public DebugController(float x, float y, int id, SmartHomeRenderer renderer) {
		super(x, y, id);
		this.renderer = renderer;
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
		case 1:
			switch (element) {
			case 0: return "X";
			case 1: return "Y";
			case 2: return "Z";
			case 3: return "Intensity";
			}
		}
		return "unknown";
	}
	public String getInfo() {
		switch (mode) {
		case 0:
			return "[" + renderer.getCamera().getX() + ", " + renderer.getCamera().getY() + ", " + renderer.getCamera().getZ() + "]";
		case 1:
			return "[" + renderer.mLight.getX() + ", " + renderer.mLight.getY() + ", " + renderer.mLight.getZ() + "]:" + renderer.mLight.getPower();
		}
		return "unknown";
	}
	public void addMode() {
		if (++mode > 1) mode = 0;
	}
	public void addElement() {
		++element;
		if (mode == 0 || mode == 1) if (element > 3) element = 0;
	}
	public void updateMessage() {
		message = getMode() + " (" + getElement() + ") " + getInfo();
		messageUpdated = true;
	}
	public String message = "Info:";
	public boolean messageUpdated = true;
	public void actionPerformed(String action) {
		if (action.equals("mode")) addMode();
		if (action.equals("element")) addElement();
		int change = 0;
		if (action.equals("<<")) change = -5;
		if (action.equals("<")) change = -1;
		if (action.equals(">")) change = 1;
		if (action.equals(">>")) change = 5;
		if (mode == 1 && element == 0) renderer.mLight.setX(renderer.mLight.getX() + change);
		if (mode == 1 && element == 1) renderer.mLight.setY(renderer.mLight.getY() + change);
		if (mode == 1 && element == 2) renderer.mLight.setZ(renderer.mLight.getZ() + change);
		if (mode == 1 && element == 3) renderer.mLight.setPower(renderer.mLight.getPower() + change);
		updateMessage();
	}
}

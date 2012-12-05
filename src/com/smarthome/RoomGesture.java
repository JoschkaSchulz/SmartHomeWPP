package com.smarthome;

import rajawali.Camera;

public class RoomGesture extends Gesture {
	public Room room;
	public RoomGesture() {}
	public RoomGesture(float x1, float y1, float x2, float y2, Room room) {
		super(x1, y1, x2, y2);
		this.room = room;
	}
	public void click(SmartHomeActivity activity) {
		activity.room = room;
		Camera camera = new Camera();
		camera = activity.camera.giveSource();
		camera.setX(room.getX());
		camera.setY(room.getY());
		activity.camera.logSource(true);
        activity.camera.setTarget(camera);
	}
	public String toString() {
		return super.toString() + " to room " + room.getID();
	}
}

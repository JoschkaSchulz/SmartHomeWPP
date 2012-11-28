package com.smarthome;

public class RoomGesture extends Gesture {
	public Room room;
	public RoomGesture() {}
	public RoomGesture(float x1, float y1, float x2, float y2, Room room) {
		super(x1, y1, x2, y2);
		this.room = room;
	}
	public void click(SmartHomeActivity activity) {
		activity.mRenderer.room = room;
	}
	public String toString() {
		return super.toString() + " to room " + room.getID();
	}
}

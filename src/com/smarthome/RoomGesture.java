package com.smarthome;

public class RoomGesture extends Gesture {
	public Room room;
	public RoomGesture() {}
	public String direction;
	public RoomGesture(float x1, float y1, float x2, float y2, Room room, String direction) {
		super(x1, y1, x2, y2);
		this.room = room;
		this.direction = direction;
	}
	public boolean gesture(SmartHomeActivity activity, String gesture) {
		if (gesture.equals(direction)) {
			click(activity, false);
			return true;
		}
		return false;
	}
	public void click(SmartHomeActivity activity, boolean isLong) {
		activity.room.disappear(activity);
		activity.room = room;
		activity.room.appear(activity);
		activity.room.moveTo(activity);
		super.click(activity, isLong);
	}
	public String toString() {
		return super.toString() + " to room " + room.getID();
	}
}

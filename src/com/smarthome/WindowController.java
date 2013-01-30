package com.smarthome;

import android.view.View;

public class WindowController extends Room implements ActionListener {
	SmartHomeActivity activity;
	WindowUnit windowUnit;
	float minx, maxx;
	int accessMode = 0; // 0 = current, 2 = all
	public WindowController(float x, float y, int id, SmartHomeActivity activity) {
		super(x, y, id, activity);
		this.activity = activity;
		//this.gestures.add(new DebugGesture(0, 0, 800, 480, "debug", this));
		this.gestures.add(new DebugGesture(100, 100, 209, 220, "open", this));
		this.gestures.add(new DebugGesture(210, 100, 319, 220, "close", this));
		this.gestures.add(new DebugGesture(320, 100, 448, 220, "target", this));
		this.gestures.add(new DebugGesture(450, 100, 578, 220, "room", this));
		this.gestures.add(new DebugGesture(590, 100, 700, 220, "all", this));
		this.gestures.add(new DebugGesture(100, 250, 700, 280, "cm", this) {
			public void appear(SmartHomeActivity activity) {
				createSample(R.drawable.slider, activity);
				activity.imagePane.addView(images.get(0));
			}
			public void disappear(SmartHomeActivity activity) {
				activity.imagePane.removeView(images.get(0));
			}
		});
		this.gestures.add(new DebugGesture(80, 80, 720, 400, "screen", this));
		this.gestures.add(new DebugGesture(0, 0, 800, 480, "leave", this) {
			public void appear(SmartHomeActivity activity) {
				createSample(R.drawable.light, activity);
				activity.imagePane.addView(images.get(0));
			}
			public void disappear(SmartHomeActivity activity) {
				activity.imagePane.removeView(images.get(0));
			}
		});
		minx = this.gestures.get(5).x1;
		maxx = this.gestures.get(5).x2;
	}
	public void actionPerformed(String action, SmartHomeActivity activity) {
		if (action.equals("enter")) {
			activity.room.disappear(activity);
			accessMode = 0;
			appear(activity);
			moveSlider(windowUnit.cm, gestures.get(5), activity);
			activity.isWSlider = true;
		}
		if (action.equals("leave")) {
			disappear(activity);
			activity.isWSlider = false;
			activity.room.appear(activity);
			/*
			activity.image.setVisibility(View.INVISIBLE);
			activity.label.setText("Info:");
			activity.isDebug = false;
			if (activity.room == null) activity.room = activity.rooms.get(0);
			return;
			*/
		}
		WindowUnit windowUnit = this.windowUnit;
		if (accessMode == 2) {
		}
		if (action.equals("target")) {
			accessMode = 0;
		}
		if (action.equals("room")) {
			accessMode = 1;
		}
		if (action.equals("all")) {
			accessMode = 2;
		}
		if (windowUnit == null) return;
		if (action.equals("open")) {
			if (accessMode == 2) {
				activity.lightGroup.imitate(activity, activity.lightGroup, "setOpen", new int[]{1});
			} else {
				windowUnit.setOpen(true);
			}
		}
		if (action.equals("close")) {
			if (accessMode == 2) {
				activity.lightGroup.imitate(activity, activity.lightGroup, "setOpen", new int[]{0});
			} else {
				windowUnit.setOpen(false);
			}
		}
	}
	public void moveSlider(int color, Gesture slider, SmartHomeActivity activity) {
		int height = 250;
		height = (height * activity.screenHeight) / 480;
		slider.moveSample(activity, minx + (color * (maxx - minx) / 20) - 25, height - 10, minx + (color * (maxx - minx) / 20) + 25, height + 40);
	}
	public void actionPerformed(String action, SmartHomeActivity activity, float x, float y) {
		if (windowUnit == null) return;
		boolean colorSet = false;
		if (action.equals("cm")) {
			windowUnit.cm = (int)((x - minx) * 20 / (maxx - minx));
			colorSet = true;
			moveSlider(windowUnit.cm, gestures.get(5), activity);
		}
		if (colorSet) {
			if (accessMode == 2) {
				activity.lightGroup.imitate(activity, activity.lightGroup, "setCm", new int[]{windowUnit.cm});
				activity.lightGroup.imitate(activity, activity.lightGroup, "setOpen", new int[]{1});
			} else {
				windowUnit.setOpen(true);
			}
		}
		actionPerformed(action, activity);
	}
}

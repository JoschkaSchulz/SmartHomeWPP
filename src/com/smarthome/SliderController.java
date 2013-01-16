package com.smarthome;

import android.view.View;

public class SliderController extends Room implements ActionListener {
	SmartHomeActivity activity;
	LightGesture lightGesture;
	float minx, maxx;
	int accessMode = 0; // 0 = current, 2 = all
	public SliderController(float x, float y, int id, SmartHomeActivity activity) {
		super(x, y, id, activity);
		this.activity = activity;
		//this.gestures.add(new DebugGesture(0, 0, 800, 480, "debug", this));
		this.gestures.add(new DebugGesture(100, 100, 209, 220, "on", this));
		this.gestures.add(new DebugGesture(210, 100, 319, 220, "off", this));
		this.gestures.add(new DebugGesture(320, 100, 448, 220, "target", this));
		this.gestures.add(new DebugGesture(450, 100, 578, 220, "room", this));
		this.gestures.add(new DebugGesture(590, 100, 700, 220, "all", this));
		this.gestures.add(new DebugGesture(100, 250, 700, 280, "red", this) {
			public void appear(SmartHomeActivity activity) {
				createSample(R.drawable.slider, activity);
				activity.imagePane.addView(images.get(0));
			}
			public void disappear(SmartHomeActivity activity) {
				activity.imagePane.removeView(images.get(0));
			}
		});
		this.gestures.add(new DebugGesture(100, 300, 700, 330, "green", this) {
			public void appear(SmartHomeActivity activity) {
				createSample(R.drawable.slider, activity);
				activity.imagePane.addView(images.get(0));
			}
			public void disappear(SmartHomeActivity activity) {
				activity.imagePane.removeView(images.get(0));
			}
		});
		this.gestures.add(new DebugGesture(100, 350, 700, 380, "blue", this) {
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
			moveSlider(0, lightGesture.red, gestures.get(5), activity);
			moveSlider(1, lightGesture.green, gestures.get(6), activity);
			moveSlider(2, lightGesture.blue, gestures.get(7), activity);
			activity.isSlider = true;
		}
		if (action.equals("leave")) {
			disappear(activity);
			activity.isSlider = false;
			activity.room.appear(activity);
			/*
			activity.image.setVisibility(View.INVISIBLE);
			activity.label.setText("Info:");
			activity.isDebug = false;
			if (activity.room == null) activity.room = activity.rooms.get(0);
			return;
			*/
		}
		LightGesture lightGesture = this.lightGesture;
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
		if (lightGesture == null) return;
		if (action.equals("on")) {
			if (accessMode == 2) {
				activity.lightGroup.imitate(activity, activity.lightGroup, "setLight", new int[]{1});
			} else {
				lightGesture.setLight(true);
			}
		}
		if (action.equals("off")) {
			if (accessMode == 2) {
				activity.lightGroup.imitate(activity, activity.lightGroup, "setLight", new int[]{0});
			} else {
				lightGesture.setLight(false);
			}
		}
	}
	public void moveSlider(int whichColor, int color, Gesture slider, SmartHomeActivity activity) {
		int height = 0;
		switch (whichColor) {
		case 0: height = 250; break;
		case 1: height = 300; break;
		case 2: height = 350; break;
		}
		height = (height * activity.screenHeight) / 480;
		slider.moveSample(activity, minx + (color * (maxx - minx) / 255) - 25, height - 10, minx + (color * (maxx - minx) / 255) + 25, height + 40);
	}
	public void actionPerformed(String action, SmartHomeActivity activity, float x, float y) {
		if (lightGesture == null) return;
		boolean colorSet = false;
		if (action.equals("red")) {
			lightGesture.red = (int)((x - minx) * 255 / (maxx - minx));
			colorSet = true;
			moveSlider(0, lightGesture.red, gestures.get(5), activity);
		}
		if (action.equals("green")) {
			lightGesture.green = (int)((x - minx) * 255 / (maxx - minx));
			colorSet = true;
			moveSlider(1, lightGesture.green, gestures.get(6), activity);
		}
		if (action.equals("blue")) {
			lightGesture.blue = (int)((x - minx) * 255 / (maxx - minx));
			colorSet = true;
			moveSlider(2, lightGesture.blue, gestures.get(7), activity);
		}
		if (colorSet) {
			if (accessMode == 2) {
				activity.lightGroup.imitate(activity, activity.lightGroup, "setColor", new int[]{lightGesture.red, lightGesture.green, lightGesture.blue});
				activity.lightGroup.imitate(activity, activity.lightGroup, "setLight", new int[]{1});
			} else {
				lightGesture.setLight(true);
			}
		}
		actionPerformed(action, activity);
	}
}

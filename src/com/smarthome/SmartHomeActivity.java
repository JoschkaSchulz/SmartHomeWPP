package com.smarthome;

import rajawali.RajawaliActivity;
import android.os.Bundle;

public class SmartHomeActivity extends RajawaliActivity {

	private SmartHomeRenderer mRenderer;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	mRenderer = new SmartHomeRenderer(this);
    	mRenderer.setSurfaceView(mSurfaceView);
    	super.setRenderer(mRenderer);
    }
}

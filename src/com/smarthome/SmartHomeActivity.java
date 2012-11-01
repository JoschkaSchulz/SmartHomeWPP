package com.smarthome;

import rajawali.RajawaliActivity;
import android.os.Bundle;
import android.text.method.Touch;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SmartHomeActivity extends RajawaliActivity implements OnTouchListener {

	private SmartHomeRenderer mRenderer;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	mRenderer = new SmartHomeRenderer(this);
    	mRenderer.setSurfaceView(mSurfaceView);
    	super.setRenderer(mRenderer);
    	mSurfaceView.setOnTouchListener(this);
    	
    	LinearLayout ll = new LinearLayout(this);
    	ll.setOrientation(LinearLayout.VERTICAL);
        ll.setGravity(Gravity.TOP);
        
        TextView label = new TextView(this);
        label.setText("Info:");
        label.setTextSize(20);
        label.setGravity(Gravity.LEFT);
        label.setHeight(100);
        ll.addView(label);
        
        mLayout.addView(ll);
    }

	public boolean onTouch(View v, MotionEvent event) {
		if( event.getAction() == MotionEvent.ACTION_DOWN) {
			System.out.println("TOUCH! x:"+event.getX()+" y:"+event.getY());
		}
		if(event.getAction() == MotionEvent.ACTION_MOVE) {
			System.out.println("Moved!");
		}
		return super.onTouchEvent(event);
	}
}

package com.smarthome;

import rajawali.RajawaliActivity;
import android.graphics.Point;
import android.os.Bundle;
import android.text.method.Touch;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SmartHomeActivity extends RajawaliActivity implements OnTouchListener {

	
	public static int screenWidth;
	public static int screenHeight;
	public SmartHomeRenderer mRenderer;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	Display display = getWindowManager().getDefaultDisplay();
    	Point size = new Point();
    	display.getSize(size);
    	screenHeight = size.y;
    	screenWidth = size.x;
    	
    	System.out.println("--SCREEN--");
    	System.out.println("Width: "+screenWidth);
    	System.out.println("Height: "+screenHeight);
    	
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
			/*if(event.getX() > 700) {
				mRenderer.getCamera().setX(0.5f + mRenderer.getCamera().getX());
			}else if(event.getX() < 50) {
				mRenderer.getCamera().setX(-0.5f + mRenderer.getCamera().getX());
			}
			
			if(event.getY() > 400) {
				mRenderer.getCamera().setY(-0.5f + mRenderer.getCamera().getY());
			}else if(event.getY() < 50) {
				mRenderer.getCamera().setY(0.5f + mRenderer.getCamera().getY());
			}
			if(event.getY() <= 400 && event.getY() >= 50 && event.getX() <= 700 && event.getX() >= 50){
				System.out.println("Camera Info:");
				System.out.println("X: "+mRenderer.getCamera().getX());
				System.out.println("Y: "+mRenderer.getCamera().getY());
				System.out.println("Z: "+mRenderer.getCamera().getZ());
			}*/
			mRenderer.room.fire(event.getX(), event.getY(), this);
		}
		if(event.getAction() == MotionEvent.ACTION_MOVE) {
			System.out.println("Moved!");
		}
		return super.onTouchEvent(event);
	}
}

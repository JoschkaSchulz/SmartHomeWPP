package com.smarthome;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;

import rajawali.BaseObject3D;
import rajawali.lights.DirectionalLight;
import rajawali.materials.DiffuseMaterial;
import rajawali.parser.Max3DSParser;
import rajawali.renderer.RajawaliRenderer;

public class SmartHomeRenderer extends RajawaliRenderer {
	private DirectionalLight mLight;
	private BaseObject3D mLivingPlace;
	private DiffuseMaterial mMaterial;
	
	public SmartHomeRenderer(Context context) {
		super(context);
		setFrameRate(30);
	}
	
	public void initScene() {
		mLight = new DirectionalLight(1f, 0.2f, 1.0f);
		mLight.setPower(2);
		
	    mMaterial = new DiffuseMaterial();		
		mMaterial.setUseColor(true);
		Max3DSParser objParser = new Max3DSParser(this, R.raw.livingplace);
		objParser.parse();
		mLivingPlace = objParser.getParsedObject();
		mLivingPlace.addLight(mLight); 

		addChild(mLivingPlace);
		
		mLivingPlace.setMaterial(mMaterial);
		mLivingPlace.setColor(0xffcccccc);
		
		mLivingPlace.setRotY(180);
		mLivingPlace.setRotZ(90);

		mCamera.setZ(-4.0f);
	}
	
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		super.onSurfaceCreated(gl, config);
	}
	
	public void onDrawFrame(GL10 glUnused) {
		super.onDrawFrame(glUnused);
//		mCamera.setRotY(mCamera.getRotY() + 1);
//		mLivingPlace.setRotZ(mLivingPlace.getRotZ() + 1);
		mLivingPlace.setRotY(mLivingPlace.getRotY() + 2);
	}
}

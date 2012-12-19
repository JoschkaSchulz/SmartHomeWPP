package com.smarthome;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import rajawali.BaseObject3D;
import rajawali.Camera;
import rajawali.parser.ObjParser;
import rajawali.renderer.RajawaliRenderer;

public class SmartHomeRenderer extends RajawaliRenderer {	
	private LightController l;
	
	private BaseObject3D mLivingPlace;
	
	private BaseObject3D windowButton1, windowButton2, windowButton3;
	private BaseObject3D lightButton1, lightButton2, lightButton3;
	
	public SmartHomeActivity activity;
	
	public SmartHomeRenderer(SmartHomeActivity context) {
		super(context);
		activity = context;

		System.out.println(activity.initializationState + " at " + Thread.currentThread().getStackTrace()[2].getClassName() + "." + Thread.currentThread().getStackTrace()[2].getMethodName());
		
		if (activity.initializationState == 127) return;

		setFrameRate(50);
		activity.initializationState |= 4;
	}
	
	private void setAmbient() {
		System.out.println(activity.initializationState + " at " + Thread.currentThread().getStackTrace()[2].getClassName() + "." + Thread.currentThread().getStackTrace()[2].getMethodName());

		l = new LightController();
		l.light.setPosition(25.5f, -19f, -100.0f);
		l.addRole("ambient");
		l.addRole("top");
		l.publish();
		
		//West
		l = new LightController();
		l.light.setPosition(-90f, 20f, -15.0f);
		l.addRole("ambient");
		l.addRole("side");
		l.publish();
		
		//North
		l = new LightController();
		l.light.setPosition(30f, -100f, -15.0f);
		l.addRole("ambient");
		l.addRole("side");
		l.publish();
		
		//East
		l = new LightController();
		l.light.setPosition(150f, 20f, -15.0f);
		l.addRole("ambient");
		l.addRole("side");
		l.publish();
		
		//South
		l = new LightController();
		l.light.setPosition(20f, 140f, -15.0f);
		l.addRole("ambient");
		l.addRole("side");
		l.publish();
		
		activity.initializationState |= 8;
	}
	
	private void setUpLights() {
		System.out.println(activity.initializationState + " at " + Thread.currentThread().getStackTrace()[2].getClassName() + "." + Thread.currentThread().getStackTrace()[2].getMethodName());

		l = new LightController();
		l.light.setPosition(7f, -8f, -15.0f);
		l.addRole("dining_light");
		l.addRole("wohnung");
		l.publish();
		
		l = new LightController();
		l.light.setPosition(25.5f, -8f, -15.0f);
		l.addRole("kitchen_main_light");
		l.addRole("wohnung");
		l.publish();
		
		l = new LightController();
		l.light.setPosition(12.0f, -30.5f, -15.0f);
		l.addRole("sleeping_light");
		l.addRole("wohnung");
		l.publish();
		
		l = new LightController();
		l.light.setPosition(41.5f, -8f, -15.0f);
		l.addRole("lounge_light");
		l.addRole("wohnung");
		l.publish();
		
		l = new LightController();
		l.light.setPosition(17.5f, -19f, -15.0f);
		l.addRole("corridor_light");
		l.addRole("wohnung");
		l.publish();
		
		for (LightController lc : LightController.byRole("wohnung")) {
			lc.light.setPower(0f);
		}
		
		for (LightController lc : LightController.byRole("top")) {
			lc.light.setPower(220f);
		}
		
		for (LightController lc : LightController.byRole("side")) {
			lc.light.setPower(50f);
		}
		
		activity.initializationState |= 16;
	}
	
	private void setUpLivingPlaceModel() {
		System.out.println(activity.initializationState + " at " + Thread.currentThread().getStackTrace()[2].getClassName() + "." + Thread.currentThread().getStackTrace()[2].getMethodName());

		ObjParser objParser = new ObjParser(mContext.getResources(), mTextureManager, R.raw.livingplace_obj);
		objParser.parse();
		mLivingPlace = objParser.getParsedObject();
		
		for (LightController lc : LightController.lights) {
			mLivingPlace.addLight(lc.light);	//Add this light for a brighter model
		}
		
		addChild(mLivingPlace);
		mLivingPlace.setScale(1.0f);
		mLivingPlace.setRotX(90f);
		mLivingPlace.setRotY(-90f);
		
		mLivingPlace.setDoubleSided(true);
		
//		mMaterial = new DiffuseMaterial();
//		mMaterial.setShininess(0.8f);
//		mMaterial.setUseColor(true);
//		mLivingPlace.setMaterial(mMaterial);
//		mLivingPlace.setColor(0xff666666);

		activity.initializationState |= 32;
	}
	
	public void initScene() {
		System.out.println(activity.initializationState + " at " + Thread.currentThread().getStackTrace()[2].getClassName() + "." + Thread.currentThread().getStackTrace()[2].getMethodName());

		if (activity.initializationState == 127) return;

		this.setAmbient();
		
		this.setUpLights();
		
		this.setUpLivingPlaceModel();

		mCamera.setZ(-35.0f);
		activity.camera.logSource(true);
		
        activity.initializationState |= 64;
	}
	
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		super.onSurfaceCreated(gl, config);
	}
	
	public void onDrawFrame(GL10 glUnused) {
		super.onDrawFrame(glUnused);
		
		if (activity.camera.ready()) activity.camera.setCamera();
		/*if (activity.room != null)
			this.getCamera().setPosition(activity.room.getX(), activity.room.getY(), -50.0f);*/
	}
	
	public Camera getCamera() {
		return this.mCamera;
	}
}

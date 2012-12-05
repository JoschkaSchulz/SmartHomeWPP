package com.smarthome;

import java.util.LinkedList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import rajawali.BaseObject3D;
import rajawali.Camera;
import rajawali.lights.ALight;
import rajawali.lights.DirectionalLight;
import rajawali.lights.PointLight;
import rajawali.materials.CubeMapMaterial;
import rajawali.materials.DiffuseMaterial;
import rajawali.materials.PhongMaterial;
import rajawali.materials.SimpleMaterial;
import rajawali.materials.ToonMaterial;
import rajawali.parser.Max3DSParser;
import rajawali.parser.ObjParser;
import rajawali.primitives.Cube;
import rajawali.renderer.RajawaliRenderer;

public class SmartHomeRenderer extends RajawaliRenderer {
	public PointLight mLight;
	private PointLight mLight0, mLight1, mLight2, mLight3, mLight4;
	
	private BaseObject3D mLivingPlace;
	
	private BaseObject3D windowButton1, windowButton2, windowButton3;
	private BaseObject3D lightButton1, lightButton2, lightButton3;
	
	public SmartHomeActivity activity;
	
	public SmartHomeRenderer(SmartHomeActivity context) {
		super(context);
		activity = context;
		setFrameRate(50);
	}
	
	private void setUpLights() {
		LightController l;
		l = new LightController();
		l.light.setPosition(1f, 0.2f, -400.0f);
		l.addRole("ambient");
		for (LightController lc : LightController.byRole("ambient")) {
			lc.light.setPower(25f);
		}
		
		
		mLight = new PointLight();
		mLight.setPosition(1f, 0.2f, -400.0f);
		mLight.setPower(25f); 
		
		mLight0 = new PointLight();
		mLight0.setPower(0);
		
		mLight1 = new PointLight(); 
		mLight1.setPower(0);
		
		mLight2 = new PointLight();
		mLight2.setPower(0);
		
		mLight3 = new PointLight();
		mLight3.setPower(0);
		 
		mLight4 = new PointLight();
		mLight4.setPower(0);
	}
	
	private void setUpLivingPlaceModel() {
		ObjParser objParser = new ObjParser(mContext.getResources(), mTextureManager, R.raw.livingplace_obj);
		objParser.parse();
		mLivingPlace = objParser.getParsedObject();
		
		mLivingPlace.addLight(mLight);	//Add this light for a brighter model
		mLivingPlace.addLight(mLight0);	//Light(Esszimmer)
		mLivingPlace.addLight(mLight1);	//Light(K�che)
		mLivingPlace.addLight(mLight2);	//Light(Schlafzimmer)
		mLivingPlace.addLight(mLight3);	//Light(Wohnzimmer)
		mLivingPlace.addLight(mLight4);	//Light(Flur)
		
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
	}
	
	public void setLight0(boolean on) {
		mLight0.setPower(on ? 5f : 0);
	}
	public void setLight1(boolean on) {
		mLight1.setPower(on ? 5f : 0);
	}
	public void setLight2(boolean on) {
		mLight2.setPower(on ? 5f : 0);
	}
	public void setLight3(boolean on) {
		mLight3.setPower(on ? 5f : 0);
	}
	public void setLight4(boolean on) {
		mLight4.setPower(on ? 5f : 0);
	}
	
	private void setUpRooms() {
		//F�ge R�ume hinzu
		
		//Raum: Esszimmer
		mLight0.setPosition(7f, -8f, -15.0f);
		
		//Raum: K�che
		mLight1.setPosition(25.5f, -8f, -15.0f);
		
		//Raum: Schlafzimmer
		mLight2.setPosition(12.0f, -30.5f, -15.0f);
		
		//Raum: Wohnzimmer
		mLight3.setPosition(41.5f, -8f, -15.0f);
		
		//Raum: Flur
		mLight4.setPosition(17.5f, -19f, -15.0f);
	}
	
	public void initScene() {
		this.setUpLights();
		
		this.setUpLivingPlaceModel();
		
		this.setUpRooms();

		mCamera.setZ(-50.0f);
		activity.camera.logSource(true);
		
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

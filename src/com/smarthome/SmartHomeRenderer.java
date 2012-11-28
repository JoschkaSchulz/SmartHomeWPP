package com.smarthome;

import java.util.LinkedList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;

import rajawali.BaseObject3D;
import rajawali.Camera;
import rajawali.lights.ALight;
import rajawali.lights.DirectionalLight;
import rajawali.lights.PointLight;
import rajawali.materials.CubeMapMaterial;
import rajawali.materials.DiffuseMaterial;
import rajawali.materials.PhongMaterial;
import rajawali.materials.ToonMaterial;
import rajawali.parser.Max3DSParser;
import rajawali.parser.ObjParser;
import rajawali.primitives.Cube;
import rajawali.renderer.RajawaliRenderer;

public class SmartHomeRenderer extends RajawaliRenderer {
	private PointLight mLight;
	private PointLight mLight0, mLight1, mLight2, mLight3, mLight4;
	
	private BaseObject3D mLivingPlace;
	private DiffuseMaterial mMaterial;
	private ToonMaterial mToonMaterial;
	private PhongMaterial mPhongMaterial;
	
	public Room room;
	private LinkedList<Room> rooms;
	
	private BaseObject3D windowButton1, windowButton2,windowButton3;
	private BaseObject3D lightButton1, lightButton2,lightButton3;
	
	public SmartHomeRenderer(Context context) {
		super(context);
		setFrameRate(50);
	}
	
	private void setUpLights() {
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
		mLivingPlace.addLight(mLight0);	//Light(Wohnzimmer)
		mLivingPlace.addLight(mLight1);	//Light(Küche)
		mLivingPlace.addLight(mLight2);	//Light(Esszimmer)
		mLivingPlace.addLight(mLight3);	//Light(Schlafzimmer)
		mLivingPlace.addLight(mLight4);	//Light(Korridor)
		
		addChild(mLivingPlace);
		mLivingPlace.setScale(1.0f);
		mLivingPlace.setRotX(90f);
		mLivingPlace.setRotY(-90f);
		
//		mLivingPlace.setDoubleSided(true);
		
//		mMaterial = new DiffuseMaterial();
//		mMaterial.setShininess(0.8f);
//		mMaterial.setUseColor(true);
//		mLivingPlace.setMaterial(mMaterial);
//		mLivingPlace.setColor(0xff666666);
	}
	
	public void setLight0(boolean on) {
		mLight0.setPower(on ? 0.2f : 0);
	}
	public void setLight1(boolean on) {
		mLight1.setPower(on ? 0.2f : 0);
	}
	public void setLight2(boolean on) {
		mLight2.setPower(on ? 0.2f : 0);
	}
	public void setLight3(boolean on) {
		mLight3.setPower(on ? 0.2f : 0);
	}
	public void setLight4(boolean on) {
		mLight4.setPower(on ? 0.2f : 0);
	}
	
	private void setUpRooms() {
		rooms = new LinkedList<Room>();
		
		//Füge Räume hinzu
		
		//Raum: Esszimmer
		rooms.add(new Room(7f, -8f, 0));
		mLight0.setPosition(7f, -8f, -2.0f);
		
		//Raum: Küche
		rooms.add(new Room(25.5f, -8f, 1));
		mLight1.setPosition(25.5f, -8f, -2.0f);
		
		//Raum: Wohnzimmer
		rooms.add(new Room(12.0f, -30.5f, 2));
		mLight2.setPosition(12.0f, -30.5f, -2.0f);
		
		//Raum: Flur
		rooms.add(new Room(41.5f, -8f, 3));
		mLight3.setPosition(41.5f, -8f, -2.0f);
		
		//Raum: Flur
		rooms.add(new Room(17.5f, -19f, 4));
		mLight4.setPosition(17.5f, -19f, -2.0f);
		
		//Füge die Raumwechsel hinzu
		rooms.get(0).gestures.add(new RoomGesture(700, 0, 800, 480, rooms.get(1)));	//Esszimmer -> Küche
		rooms.get(0).gestures.add(new RoomGesture(0, 380, 800, 480, rooms.get(4)));	//Esszimmer -> Flur

		rooms.get(1).gestures.add(new RoomGesture(0, 0, 100, 480, rooms.get(0)));	//Küche	-> Esszimmer
		rooms.get(1).gestures.add(new RoomGesture(0, 380, 800, 480, rooms.get(4)));	//Küche	-> Flur
		rooms.get(1).gestures.add(new RoomGesture(700, 0, 800, 480, rooms.get(3)));	//Küche	-> Wohnzimmer
		
		rooms.get(2).gestures.add(new RoomGesture(0, 0, 800, 100, rooms.get(4)));
		
		rooms.get(3).gestures.add(new RoomGesture(0, 0, 100, 480, rooms.get(1)));
		
		rooms.get(4).gestures.add(new RoomGesture(0, 0, 400, 100, rooms.get(0)));
		rooms.get(4).gestures.add(new RoomGesture(401, 0, 800, 100, rooms.get(1)));
		rooms.get(4).gestures.add(new RoomGesture(0, 380, 800, 480, rooms.get(2)));
		
		//Füge die Lichtsteuerung hinzu
		rooms.get(0).gestures.add(new LightGesture(100,100,700,380, "dining_light_color"));
		rooms.get(1).gestures.add(new LightGesture(100,100,700,380, "kitchen_light_color"));
		rooms.get(2).gestures.add(new LightGesture(100,100,700,380, "lounge_light_color"));
		rooms.get(3).gestures.add(new LightGesture(100,100,700,380, "sleeping_light_color"));
		rooms.get(4).gestures.add(new LightGesture(100,100,700,380, "corridor_light_color"));
		
		this.room = rooms.get(0);
	}
	
	public void initScene() {
		this.setUpLights();
		
		this.setUpLivingPlaceModel();
		
		this.setUpRooms();

		mCamera.setZ(-50.0f);
		
	}
	
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		super.onSurfaceCreated(gl, config);
	}
	
	public void onDrawFrame(GL10 glUnused) {
		super.onDrawFrame(glUnused);
		
		this.getCamera().setPosition(this.room.getX(), this.room.getY(), -50.0f);
	}
	
	public Camera getCamera() {
		return this.mCamera;
	}
	
	public void moveLP(float x,float y) {
		mLivingPlace.setPosition(mLivingPlace.getX()+x, mLivingPlace.getY()+y, mLivingPlace.getZ());
	}
}

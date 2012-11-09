package com.smarthome;

import java.util.LinkedList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;

import rajawali.BaseObject3D;
import rajawali.Camera;
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
	private DirectionalLight mLight;
	private PointLight mLight0, mLight1, mLight2, mLight3;
	
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
		mLight = new DirectionalLight(1f, 0.2f, 1.0f);
		mLight.setPower(1); 
		
		mLight0 = new PointLight();
		mLight0.setPower(0);
		
		mLight1 = new PointLight();
		mLight1.setPower(0);
		
		mLight2 = new PointLight();
		mLight2.setPower(0);
		
		mLight3 = new PointLight();
		mLight3.setPower(0);
	}
	
	private void setUpLivingPlaceModel() {
		ObjParser objParser = new ObjParser(mContext.getResources(), mTextureManager, R.raw.livingplace_obj);
		objParser.parse();
		mLivingPlace = objParser.getParsedObject();
		
//		mLivingPlace.addLight(mLight);	//Add this light for a brighter model
		mLivingPlace.addLight(mLight0);	//Light(Wohnzimmer)
		mLivingPlace.addLight(mLight1);	//Light(Küche)
		mLivingPlace.addLight(mLight2);	//Light(Esszimmer)
		mLivingPlace.addLight(mLight3);	//Light(Schlafzimmer)
		
		addChild(mLivingPlace);
		mLivingPlace.setScale(10.0f);

		mMaterial = new DiffuseMaterial();
		mMaterial.setUseColor(true);
		mLivingPlace.setMaterial(mMaterial);
		mLivingPlace.setColor(0xff666666);
	}
	
	public void setLight0(boolean on) {
		mLight0.setPower(on ? 2 : 0);
	}
	
	private void setUpRooms() {
		rooms = new LinkedList<Room>();
		
		//Füge Räume hinzu
		rooms.add(new Room(-2.5f, 4.5f));
		mLight0.setPosition(-2.5f, 4.5f, -5f);
		
		rooms.add(new Room(5.0f, 5.0f));
		mLight1.setPosition(5.0f, 5.0f, -5f);
		
		rooms.add(new Room(-1.5f, -4.0f));
		mLight2.setPosition(-1.5f, -4.0f, -5f);
		
		rooms.add(new Room(11.0f, 5.0f));
		mLight3.setPosition(11.0f, 5.0f, -5f);
		
		//Füge die Raumwechsel hinzu
		rooms.get(0).gestures.add(new RoomGesture(700, 0, 800, 480, rooms.get(1)));
		rooms.get(0).gestures.add(new RoomGesture(0, 380, 800, 480, rooms.get(2)));

		rooms.get(1).gestures.add(new RoomGesture(0, 0, 100, 480, rooms.get(0)));
		rooms.get(1).gestures.add(new RoomGesture(0, 380, 800, 480, rooms.get(2)));
		rooms.get(1).gestures.add(new RoomGesture(700, 0, 800, 480, rooms.get(3)));
		
		rooms.get(2).gestures.add(new RoomGesture(0, 0, 400, 100, rooms.get(0)));
		rooms.get(2).gestures.add(new RoomGesture(401, 0, 800, 100, rooms.get(1)));
		
		rooms.get(3).gestures.add(new RoomGesture(0, 0, 100, 480, rooms.get(1)));
		
		//Füge die Lichtsteuerung hinzu
		rooms.get(0).gestures.add(new LightGesture(100,100,700,380, "dining_light_color"));
		rooms.get(1).gestures.add(new LightGesture(100,100,700,380, "kitchen_light_color"));
		rooms.get(2).gestures.add(new LightGesture(100,100,700,380, "lounge_light_color"));
		rooms.get(3).gestures.add(new LightGesture(100,100,700,380, "sleeping_light_color"));
		
		this.room = rooms.get(0);
	}
	
	public void initScene() {
		this.setUpLights();
		
		this.setUpLivingPlaceModel();
		
		this.setUpRooms();
		
		mLivingPlace.setRotY(180);
		mLivingPlace.setRotX(90);

		mCamera.setZ(-15.0f);
		
	}
	
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		super.onSurfaceCreated(gl, config);
	}
	
	public void onDrawFrame(GL10 glUnused) {
		super.onDrawFrame(glUnused);
		
		this.getCamera().setPosition(this.room.getX(), this.room.getY(), -15.0f);
	}
	
	public Camera getCamera() {
		return this.mCamera;
	}
	
	public void moveLP(float x,float y) {
		mLivingPlace.setPosition(mLivingPlace.getX()+x, mLivingPlace.getY()+y, mLivingPlace.getZ());
	}
}

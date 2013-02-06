package com.smarthome;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONBuilder {
	
	//*******************************************
	//**		Methods
	//*******************************************
	
	/**
	 * This method buid a JSON string for the activeMQ. It is used
	 * for controlling the colored lights
	 * 
	 * @param action the action wich should be used
	 * @param red the red color 0 - 255
	 * @param green the green color 0 - 255
	 * @param blue the blue color 0 - 255
	 * @param fadetime the time the lights fade on or off
	 * @return a JSON string for the activeMQ
	 */
	public static String light(String action, int red, int green, int blue, int fadetime) {
		
		Map<String,String> valueMap = new HashMap<String, String>();
		valueMap.put("red",Integer.toString(red));
		valueMap.put("green",Integer.toString(green));
		valueMap.put("blue",Integer.toString(blue));
		valueMap.put("fadeTime",Integer.toString(fadetime));
		
		Map<String,Object> aendernMap = new HashMap<String, Object>();
		
		JSONObject jsov = new JSONObject(valueMap);

		aendernMap.put("values", jsov);
		aendernMap.put("Id", "client_1578909153");
		aendernMap.put("Version", null);
		aendernMap.put("action", action + "_color");
		JSONArray jsoa = new JSONArray(Arrays.asList(new Integer[]{1,2,3}));
		aendernMap.put("anAry", jsoa );
		JSONObject msgFarbeEsszimmerAendern = new JSONObject(aendernMap);
		return msgFarbeEsszimmerAendern.toString();
	}
	
	/**
	 * This method buid a JSON string for the activeMQ. It is used
	 * for controlling the intensity lights
	 * @param action the action that should be used
	 * @param on true turns it on and false off
	 * @param intensity the intensity the lights shine
	 * @return a JSON string for the activeMQ
	 */
	public static String light(String action, boolean on, int intensity) {
		
		Map<String,String> valueMap = new HashMap<String, String>();
		if (on) valueMap.put("intensity",Integer.toString(intensity));
		
		Map<String,Object> aendernMap = new HashMap<String, Object>();
		
		JSONObject jsov = new JSONObject(valueMap);
		
		aendernMap.put("values", jsov);
		aendernMap.put("Id", "client_1578909153");
		aendernMap.put("Version", null);
		aendernMap.put("action", action + (on ? "_on" : "_off"));
		JSONArray jsoa = new JSONArray(Arrays.asList(new Integer[]{1,2,3}));
		aendernMap.put("anAry", jsoa );
		JSONObject msgFarbeEsszimmerAendern = new JSONObject(aendernMap);
		return msgFarbeEsszimmerAendern.toString();
	}
	
	/**
	 * This method buid a JSON string for the activeMQ. It is used
	 * for controlling the curtain and blinds.
	 * 
	 * @param action the action that should be used
	 * @param open true opens the curtains and false closed them
	 * @return a JSON string for the activeMQ
	 */
	public static String curtain(String action, boolean open) {
		
		Map<String,String> valueMap = new HashMap<String, String>();
//		if (on) valueMap.put("intensity",Integer.toString(intensity));
		
		Map<String,Object> aendernMap = new HashMap<String, Object>();
		
		JSONObject jsov = new JSONObject(valueMap);
		
		aendernMap.put("values", jsov);
		aendernMap.put("Id", "client_1578909153");
		aendernMap.put("Version", null);
		aendernMap.put("action", action + (open ? "_open" : "_close"));
		JSONArray jsoa = new JSONArray(Arrays.asList(new Integer[]{1,2,3}));
		aendernMap.put("anAry", jsoa );
		JSONObject msgFarbeEsszimmerAendern = new JSONObject(aendernMap);
		return msgFarbeEsszimmerAendern.toString();
	}
	
	/**
	 * (experimental method)
	 * This method buid a JSON string for the activeMQ. It is used
	 * for controlling the windows.
	 * @param windowid the window id wich should be used
	 * @param position the position of the window 0 - 20
	 * @return a JSON string for the activeMQ
	 */
	public static String window(String windowid, Integer position) {
		
		Map<String,Object> valueMap = new HashMap<String, Object>();
		
		//		if (on) valueMap.put("intensity",Integer.toString(intensity));
		
		Map<String,Object> aendernMap = new HashMap<String, Object>();
		
		JSONObject jsov = new JSONObject(valueMap);
		
//		aendernMap.put("values", jsov);
//		aendernMap.put("Id", "client_1578909153");
//		aendernMap.put("Version", null);
//		aendernMap.put("action", action + "" + open);
//		JSONArray jsoa = new JSONArray(Arrays.asList(new Integer[]{1,2,3}));
//		aendernMap.put("anAry", jsoa );
		JSONArray value = new JSONArray(Arrays.asList(new String[]{position.toString(), "FAST"}));
		aendernMap.put(windowid, value );
		
		JSONObject msgFarbeEsszimmerAendern = new JSONObject(aendernMap);
		return msgFarbeEsszimmerAendern.toString();
	}
}

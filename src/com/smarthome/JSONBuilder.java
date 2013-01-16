package com.smarthome;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONBuilder {
	
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
}

package com.ahb.mobileautomation.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CapsJsonParser {

	static final File jsonFile = new File("./caps/capability.json");



	public static JSONObject getJSONObjectValue(String key)
	{
		JSONObject fileJsonObject = null ;

		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(jsonFile));
			fileJsonObject = (JSONObject) obj;

			JSONArray platformArray = (JSONArray) fileJsonObject.get("platforms");

			for(int i = 0; i < platformArray.size();i++)
			{
				JSONObject eachPlatform = (JSONObject) platformArray.get(i);

				if(eachPlatform.containsKey(key))
				{
					return (JSONObject) eachPlatform.get(key);
				}
			}


		} catch (IOException | ParseException e ) {

			e.printStackTrace();
		}

		return null;
	}

	


}

package com.testplugs.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.xml.Parser;



public class JsonDataProvider {
	public static Object[] jsonDataProviderService() throws IOException, ParseException {

		JSONParser json_parser = new JSONParser();
		JSONObject json_object = null;

		// Read JSON File

		FileReader json_file = new FileReader(
				System.getProperty("user.dir") + "\\src\\test\\java\\com\\twoplugs\\testData\\services.json");
		Object parsed_object = json_parser.parse(json_file);
		json_object = (JSONObject) parsed_object;

		// Array to store JSON Data
		Object[] data = new Object[1];

		// Store JSON data as key/value pair in Hashmap
		HashMap<String, String> json_hashmap = new LinkedHashMap<String, String>();
		if (json_object != null) {
			Set<String> jsonObj_Keys = json_object.keySet();
			for (String jsonObj_SingleKey : jsonObj_Keys) {
				json_hashmap.put(jsonObj_SingleKey, (String) json_object.get(jsonObj_SingleKey));
			}
		} else {
			json_hashmap.put("Error", "No Data from JSON File");
			System.out.println("Unable to retrive JSON Data");
		}

		// Store Hashmap in an array and return array
		data[0] = json_hashmap;
		return data;
	}
}

package com.ahb.mobileautomation.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppiumProperties {

	public static String getProperty(String key)
	{
		Properties prop = new Properties();
		InputStream input = null;
		
		try {
			input = new FileInputStream("./config/appiumConfig.properties");
			prop.load(input);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return prop.getProperty(key);
	}
	
}

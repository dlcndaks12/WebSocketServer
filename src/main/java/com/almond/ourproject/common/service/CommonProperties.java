package com.almond.ourproject.common.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class CommonProperties {
	
	String path = (getClass().getResource("/config/config.properties")).getPath();
	Properties prop;

	public CommonProperties() {
		prop = new Properties();
		
		try {
			prop.load(new FileInputStream(path));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Properties getProperties() {
		return prop;
	}
}

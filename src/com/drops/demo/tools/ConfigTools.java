package com.drops.demo.tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigTools {
	public static Properties getConfiguration() throws FileNotFoundException, IOException{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("config.properties");
		Properties config = new Properties();
		config.load(input);				
		return config;
	}
}

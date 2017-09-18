package com.drops.demo.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringTools {
	public static String coalesce(String value){
		return coalesce(value, "");
	}
	
	public static String coalesce(String value, String valueIfNull){
		return value == null ? valueIfNull : value;
	}
	
	public static String getFortune() throws IOException, InterruptedException{
		ProcessBuilder fortuneBuilder = new ProcessBuilder("fortune","-l");
		Process fortune = fortuneBuilder.start();
		fortune.waitFor();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(fortune.getInputStream()));
		StringBuffer result = new StringBuffer();
		String buffer;
		while((buffer = reader.readLine()) != null){
			result.append(buffer).append("\n");
		}
		
		return result.toString();
	}
}

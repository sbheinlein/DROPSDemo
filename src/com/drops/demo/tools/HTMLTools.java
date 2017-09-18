package com.drops.demo.tools;

public class HTMLTools {
	public static String tagElement(String tag, String element){
		return tagElement(tag, "", element);
	}
	
	public static String tagElement(String tag, String options, String element){
		StringBuffer result = new StringBuffer("<").append(tag);
		
		if(!options.equals(""))
			result.append(" ").append(options).append(" ");
		
		result	.append(">")
				.append(element)
				.append("</").append(tag).append(">");
		
		return result.toString();
	}
}

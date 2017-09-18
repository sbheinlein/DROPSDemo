package com.drops.demo.tools;

public class BootstrapUI {
	public static String glyphIcon(String glyph){
		return glyphIcon(glyph, "");
	}
	public static String glyphIcon(String glyph, String label){
		return String.format("<span class='glyphicon glyphicon-%1$s' aria-hidden='true'></span>&nbsp;&nbsp;%2$s", glyph, label);
	}
}

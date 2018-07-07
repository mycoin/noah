package com.breakidea.noah.framework.util.security;

public class FilterEL {

	public static String asNumber(String input) {
		return Filter.asNumber(input);
	}

	public static String asNumberDefault(String input, String defaultNumber) {
		return Filter.asNumber(input, defaultNumber);
	}

	public static String asCssColor(String input) {
		return Filter.asCssColor(input);
	}

	public static String asCssColorDefault(String input, String defaultColor) {
		return Filter.asCssColor(input, defaultColor);
	}

	public static String asURL(String input) {
		return Filter.asURL(input);
	}

	public static String asFlexibleURL(String input) {
		return Filter.asFlexibleURL(input);
	}
}

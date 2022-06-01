package com.qpros.quanta.utils;

public class StringUtil {
    
    private StringUtil() { }
    
    public static String capitalize(String s) {
        return Character.toLowerCase(s.charAt(0)) + s.substring(1);
    }
    
    public static boolean isNotNullOrEmpty (String str) {
		return str != null && !str.trim().isEmpty();
    }
}

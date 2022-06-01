package com.qpros.quanta.utils;

public class IntUtils {

    private IntUtils() {
    }

    public static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}

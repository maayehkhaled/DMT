package com.qpros.quanta.viewdefs;

import java.util.EnumMap;
import java.util.Map;

import com.qpros.quanta.Status;

public class MaterialIcon {

    private static Map<Status, String> map = new EnumMap<>(Status.class);
    
    public void override(Status status, String icon) {
        map.put(status, icon);
    }
    
    public String getIcon(Status status) {
        if (map.containsKey(status))
            return map.get(status);

        String s = status.toString().toLowerCase();

        switch (s) {
	        case "fail":
	            return "cancel";
	        case "fatal":
	            return "cancel";
	        case "error":
	            return "error";
	        case "warning":
	            return "warning";
	        case "skip":
	            return "redo";
	        case "pass":
	            return "check_circle";
	        case "debug":
	            return "low_priority";
	        case "info":
	            return "info_outline";
	        default:
	            return "help";
	    }
    }

}

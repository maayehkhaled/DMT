package com.qpros.common.api;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public abstract class RestController {


    public void setRequest(HttpRequester httpRequester) {

    }

    public abstract HashMap<String, String> setHeaders();


    public abstract String getParamsString(Map<String, String> params)
            throws UnsupportedEncodingException;

    public abstract String getResponse();
}

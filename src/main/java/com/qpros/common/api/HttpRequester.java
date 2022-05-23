package com.qpros.common.api;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.annotation.Nullable;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.*;
import java.util.Map;

public class HttpRequester {

    public String responseBody;
    public String requestBody;
    public String verb;
    public String responseCode;
    public Map<String, String> responseHeader;
    public Map<String, String> requestHeaders;
    public HttpURLConnection connection;
    public String baseUrl;
    public String endpoint;


    public void request(Verb verb, String baseUrl, String endpoint, Boolean isVersioned, Boolean isParametrized, @Nullable String version) {
        try {
            //Create a URL object
            URL url = new URL(baseUrl + endpoint);
            //Obtain a URLConnection object from the URL
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8888));
            if (!proxy.address().toString().isEmpty()) {
                connection = (HttpURLConnection) url.openConnection(proxy);
            } else {
                connection = (HttpURLConnection) url.openConnection();

            }
            connection.setRequestMethod(verb.name());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getParamsString(Map<String, String> params)
            throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }

    public Reader response() throws IOException {
        responseCode = String.valueOf(connection.getResponseCode());
        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        int status = connection.getResponseCode();

        Reader streamReader = null;

        if (status > 299) {
            streamReader = new InputStreamReader(connection.getErrorStream());
        } else {
            streamReader = new InputStreamReader(connection.getInputStream());
        }
        connection.disconnect();
        return streamReader;
    }

    public String getResponseBodyAsString() throws IOException {
        Reader initialReader = response();
        char[] arr = new char[8 * 1024];
        StringBuilder buffer = new StringBuilder();
        int numCharsRead;
        while ((numCharsRead = initialReader.read(arr, 0, arr.length)) != -1) {
            buffer.append(arr, 0, numCharsRead);
        }
        initialReader.close();
        return buffer.toString();
    }

    public JSONObject getResponseBodyAsJson() throws IOException {

        return new JSONObject(getResponseBodyAsString());
    }


    public Document getResponseBodyAsXML() throws IOException {

        return convertStringToXMLDocument(getResponseBodyAsString());
    }



    private static Document convertStringToXMLDocument(String xmlString)
    {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try
        {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();

            //Parse the content to Document object
            return builder.parse(new InputSource(new StringReader(xmlString)));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}


package com.qpros.utils;

import com.qpros.common.api.HttpRequester;

import java.io.FileWriter;
import java.io.IOException;

public class SazParser {


    HttpRequester httpRequester = new HttpRequester();

    public void requestFileContentGenerator(int index, HttpRequester httpRequester) throws IOException {
        FileWriter fileWriter = new FileWriter(index + "_c.txt", true);
        fileWriter.write(httpRequester.verb);
        fileWriter.append(" ").append(httpRequester.baseUrl).append("/").append(httpRequester.endpoint).append("\n")
                .append(httpRequester.requestHeaders.toString()).append("\n")
                .append(httpRequester.requestBody);

        FileWriter response = new FileWriter(index+"_s.txt",true);
        response.append(httpRequester.responseHeader.toString()).append("\n")
                .append(httpRequester.responseBody);

    }
// will take the request set it in _c.txt file // done
// will take the response and set it in _s.txt file //done

//compress the folder content into .saz format with build name

}

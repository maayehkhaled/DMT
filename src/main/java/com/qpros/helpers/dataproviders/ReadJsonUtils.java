package com.qpros.helpers.dataproviders;

import com.qpros.common.LogManager;
import com.qpros.helpers.ExcelUtils;
import com.qpros.helpers.PropertiesUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ReadJsonUtils {

    public  static LogManager logManager= new LogManager(ExcelUtils.class.getSimpleName());

    /**
     * Read JSON into TestNG data provider multi-dimensional array.
     *
     * @param resourcePath resource path from the /resources dir
     * @param fileName     file name to read
     * @return data provider array of JSON
     */
    public static Object[][] getJsonData(final String resourcePath, final String fileName) {

        Object[][] jsonDataArray = new Object[1][1];

        try {

            File jsonFile = PropertiesUtils.getFileFromResources(resourcePath + fileName);
            InputStream is = new FileInputStream(jsonFile);
            JSONObject json = new JSONObject(IOUtils.toString(is, StandardCharsets.UTF_8));
            jsonDataArray[0][0] = json;

        } catch (Exception e) {
            e.printStackTrace();
            logManager.DEBUG(String.format("Could not read the Excel sheet due to exception=%s", e.getMessage()));
        }

        return (jsonDataArray);
    }

}

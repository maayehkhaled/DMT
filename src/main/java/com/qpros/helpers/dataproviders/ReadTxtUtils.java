package com.qpros.helpers.dataproviders;

import com.qpros.common.LogManager;
import com.qpros.helpers.FileUtils;
import com.qpros.helpers.PropertiesUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;


public class ReadTxtUtils {

    public  static LogManager logManager= new LogManager(ReadTxtUtils.class.getSimpleName());

    /**
     * Read .txt into TestNG data provider multi-dimensional array.
     *
     * @param resourcePath resource path from the /resources dir
     * @param fileName     file name to read
     * @param totalCols    total number of columns to read
     * @param totalRows    total number of rows to read
     * @return data provider array
     */
    public static Object[][] getTextDataArray(final String resourcePath, final String fileName, final int totalCols, final int totalRows) {

        String[][] tabArray = null;

        try {

            File file = PropertiesUtils.getFileFromResources(resourcePath + fileName);
            List<String> contents = FileUtils.readFile(file);

            int startRow = 1;
            int cellIndex;
            int innerCellIndex;
            tabArray = new String[totalRows][totalCols];
            cellIndex = 0;

            for (int i = startRow; i <= totalRows; i++, cellIndex++) {
                innerCellIndex = 0;
                String columnValue = contents.get(cellIndex);
                if (StringUtils.isNoneEmpty(columnValue)) {
                    tabArray[cellIndex][innerCellIndex] = columnValue;
                }
            }

        } catch (Exception e) {
            logManager.DEBUG(String.format("Could not read the Text file due to exception=%s", e.getCause()));
        }

        return (tabArray);

    }

}

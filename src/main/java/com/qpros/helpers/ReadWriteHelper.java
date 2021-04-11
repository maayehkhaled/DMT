package com.qpros.helpers;


import org.testng.Assert;

import java.io.*;
import java.util.Properties;

public class ReadWriteHelper {

    private static String Par = "";

    public static String ReadData(String par) {
        File file = new File("src/main/resources/config.properties");

        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (Throwable e) {
            e.printStackTrace(System.out);
            Assert.fail("\nPlease check config file if exist\n");
        }
        Properties prop = new Properties();

        // load properties file
        try {
            prop.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace(System.out);
            Assert.fail("\nPlease check config file Inputs\n");
        }

        return Par = prop.getProperty(par);
    }
    /**
     * @param fileName CSV File name
     * @param linesToRead Numbers of lines to read.
     * @param columnsToRead Number of columns to be read.
     * @return
     */
    public static String[][] readCSVFile(String fileName, int linesToRead, int columnsToRead) {
        //Possible future implementation: Make separators as input. However this looks better for reusability
        String line = "";
        String csvSplitBy = ",";
        String[] currentLine = null;
        String[][] finalResult = new String[linesToRead][columnsToRead];
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") +
                "/src/main/resources/DataProvider/" + fileName + ".csv"))) {
            int j = 0;
            while ((line = br.readLine()) != null) {
                // use comma as separator
                currentLine = line.split(csvSplitBy);
                for (int i = 0; i < currentLine.length; i++) {
                    finalResult[j][i] = currentLine[i];
                }
                j++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalResult;
    }

    /**
     * @param fileName CSV File name
     * @param linesToRead Numbers of lines to read.
     * @param columnsToRead Number of columns to be read.
     * @return
     */
    public static String[][] readCSVFile(String directory,String fileName, int linesToRead, int columnsToRead) {
        //Possible future implementation: Make separators as input. However this looks better for reusability
        String line = "";
        String csvSplitBy = ",";
        String[] currentLine = null;
        String[][] finalResult = new String[linesToRead][columnsToRead];
        try (BufferedReader br = new BufferedReader(new FileReader(directory+
                "/" + fileName + ".txt"))) {
            int j = 0;
            while ((line = br.readLine()) != null) {
                // use comma as separator
                currentLine = line.split(csvSplitBy);
                for (int i = 0; i < currentLine.length; i++) {
                    finalResult[j][i] = currentLine[i];
                }
                j++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalResult;
    }

    public static void writeCSVFirstCell(String filePath, String content) { //"username, password \n
        try (PrintWriter writer = new PrintWriter(new File("src/main/resources/DataProvider/" + filePath + ".csv"))) {
            writer.write(content);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeCSVFirstCell(String directory,String filePath, String content) { //"username, password \n
        try (PrintWriter writer = new PrintWriter(new File(directory+"/" + filePath + ".txt"))) {
            writer.write(content);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}

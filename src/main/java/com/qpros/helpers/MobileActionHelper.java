package com.qpros.helpers;

import com.qpros.common.LogManager;
import com.qpros.common.mobile.MobileBase;
import io.appium.java_client.MobileElement;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

public class MobileActionHelper extends MobileBase {
    public static LogManager logManager= new LogManager(MobileActionHelper.class.getSimpleName());

    public static void waitForSeconds(Integer timeWait) {
        driver.manage().timeouts().implicitlyWait(timeWait, TimeUnit.SECONDS);
    }

    /**
     * Wrapper for driver.findElement
     *
     * @param by Element location found by css, xpath, id etc...
     **/
    public static MobileElement element(final By by) {
        return driver.findElement(by);
    }

    /**
     * Wrapper for clear data and sendKeys in Input Text box
     *
     * @param by Element location found by css, xpath, id etc...
     **/

    public static void clickElement(By by) {
        logManager.INFO("The User Click on : " + element(by).getText(),true);
        element(by).click();
    }

    /**
     * send keys in specific test box with clearing original value
     *
     * @param by
     * @param text
     * @throws Exception
     */
    public static void sendKeys(By by, String text) {
        try {
            element(by).clear();
            element(by).sendKeys(text);
            logManager.INFO("Send keys with Clear [" + text + "] inside element [" + element(by).toString().substring(element(by).toString().lastIndexOf(":") + 2),true);
        } catch (Exception ex) {
            logManager.ERROR(ex.getMessage());
            throw ex;
        }
    }

    public static String takeScreenShot() throws Exception {
        File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        return encodeFileToBase64Binary(file);

    }

    private static String encodeFileToBase64Binary(File file) throws Exception{
        FileInputStream fileInputStreamReader = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        fileInputStreamReader.read(bytes);
        return new String(Base64.encodeBase64(bytes), "UTF-8");
    }

}

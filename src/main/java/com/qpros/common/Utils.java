package com.qpros.common;

import com.qpros.helpers.ActionsHelper;
import com.qpros.reporting.QuantaTestManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.qpros.helpers.ActionsHelper.*;


public class Utils {

    public static String CreateUniqueRandomUsername(String Username){
        int x = ThreadLocalRandom.current().ints(0, 100).distinct().limit(1).findFirst().getAsInt();

        Username = Username.replaceAll("[0-9]", ""); // to avoid long stream of char
        String[] f;
        if(Username.contains(Character.toString('@')))
        { f=Username.split("@");
            return f[0]+ x +"@" +f[1];
        }
        else
            return Username + x;
    }

    public static String GenerateRandomPassword(){ //8-12 char
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~!@#$%^&*()-_=+[{]}\\|;:\'\"<>/?";
        String pwd = RandomStringUtils.random( 8, characters ); // 8 char
        return pwd +"Aa1$";// to ensure the existence of letters, digit, special char
    }

    public static void uploadfile(WebDriver drv, String filepath , WebElement browse  ) {


        try {
         scrollTo(browse);

        retryClick(browse,30);

            driverWait(2000);

        Robot rb = new Robot();
        setClipboardData(filepath);
        // press Contol+V for pasting
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        // release Contol+V for pasting
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);
        // for pressing and releasing Enter
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            logManger.ERROR("Upload attachment function is failed",false);
            QuantaTestManager.getTest().skip("Error with upload Documents");
        }
    }

    public static void setClipboardData(String string) {
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }


    /**
     * this function created for HomeRelocationInZone test
     *        to set the department no.
     * @return random int value
     */
    public static int GenerateRandomNumberWith3digits(){
        Random random = new Random();
        return random.nextInt(100) + 100;
    }

    /*
       i need to clear cache before login
     */
    public static void  ClearBrowserCache(WebDriver driver)  {
        driver.manage().deleteAllCookies(); //delete all cookies
        driverWait(5000); //wait 5 seconds to clear cookies.
    }

    public static String  GenerateRandomEmaritePhoneNumber(){
        Random rnd = new Random();
        int n = 10000000 + rnd.nextInt(90000000);
        return "9715" + n;
    }

    public static String  GenerateRandomName(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 4;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }

        return buffer.toString();
    }

}

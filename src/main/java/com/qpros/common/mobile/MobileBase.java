package com.qpros.common.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;

public class MobileBase {
    public static AppiumDriver<MobileElement> driver;


    @BeforeMethod(enabled = true)
    public AppiumDriver<MobileElement> setup() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.APPLICATION_NAME, "com.example.android.contactmanager");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".ContactManager");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        capabilities.setCapability(MobileCapabilityType.APP, "C:\\Users\\RanaIbrahim\\Desktop\\Q-Pros Automation Framework\\src\\main\\resources\\apk\\ContactManager.apk");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        return driver;
    }

}

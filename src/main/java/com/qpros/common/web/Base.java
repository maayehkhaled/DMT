package com.qpros.common.web;


import com.qpros.common.DriverType;
import com.qpros.common.LogManager;
import com.qpros.helpers.ReadWriteHelper;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Base {

    public static ThreadLocal<WebDriver> driver= new ThreadLocal<>();
    public com.qpros.common.OsValidator OsValidator;
    public LogManager logManager = new LogManager(getClass().getSimpleName());



    @BeforeTest(enabled = false) public synchronized void setUpBrowser() {
        String OsType = OsValidator.getDeviceOs();
        DriverType browser = getBrowser();
        initiateDriver(OsType, browser);
        driver.get().navigate().to(ReadWriteHelper.ReadData("BaseURL"));
    }


    public synchronized WebDriver initiateDriver(String deviceOsType, DriverType driverType) {

        switch (driverType) {
            case FIREFOX:
                try {

                    setFireFoxBrowser(deviceOsType);
                    driver.set( new FirefoxDriver());
                } catch (Throwable e) {
                    e.printStackTrace(System.out);
                    Assert.fail("Please check Browser is exist Browser Unable to start");
                }
                break;
            case CHROME:
                try {
                    setChromeBrowser(deviceOsType);
                    Map<String, Object> prefs = new HashMap<String, Object>();
                    //Put this into prefs map to switch off browser notification
                    prefs.put("profile.default_content_setting_values.notifications", 2);
                    //Create chrome options to set this prefs
                    ChromeOptions options = new ChromeOptions();
                    options.setExperimentalOption("prefs", prefs);
                    options.addArguments("--ignore-certificate-errors");
                    options.addArguments("--disable-web-security");
                    options.addArguments("--allow-running-insecure-content");
                    options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
                    driver.set(new ChromeDriver(options));
                    //Dimension targetSize = new Dimension(1920, 1080); //your screen resolution here
                    //driver.get().manage().window().setSize(targetSize);

                } catch (Throwable e) {
                    e.printStackTrace(System.out);
                    Assert.fail("Please check Browser is exist Browser Unable to start");
                }
                break;
            case INTERNETEXPLORER: {
                try {
                    System.setProperty(ReadWriteHelper.ReadData("IEDriverPath"), ReadWriteHelper.ReadData("IEBrowserPathWindows"));
                    driver.set( new InternetExplorerDriver());
                } catch (Throwable e) {
                    e.printStackTrace(System.out);
                    Assert.fail("Please check Browser is exist Browser Unable to start");
                }
                break;
            }
            case Safari: {
                try {
                    System.setProperty(ReadWriteHelper.ReadData("SafariDriverPath"), ReadWriteHelper.ReadData("SafariBrowserPath"));

                    driver.set( new SafariDriver());
                } catch (Throwable e) {
                    e.printStackTrace(System.out);
                    Assert.fail("Please check Browser is exist Browser Unable to start");
                }
            }
            case appium:
                setAppiumDriver();
                break;
        }
        if (!driverType.equals(DriverType.appium)) {
            driver.get().manage().window().maximize();
        }
        return driver.get();
    }

    public synchronized DriverType getBrowser() {
        String browserName = ReadWriteHelper.ReadData("browser");

        if (browserName == null || browserName.equalsIgnoreCase("chrome"))
            return DriverType.CHROME;
        else if (browserName.equalsIgnoreCase("firefox"))
            return DriverType.FIREFOX;
        else if (browserName.equals("iexplorer") || browserName.equalsIgnoreCase("internetExplorer") || browserName.equalsIgnoreCase("ie"))
            return DriverType.INTERNETEXPLORER;
        else if (browserName.equalsIgnoreCase("safari"))
            return DriverType.Safari;
        else if (browserName.equalsIgnoreCase("appium"))
            return DriverType.appium;
        else
            throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched : " + browserName);
    }


    private synchronized void setChromeBrowser(String deviceOsType) {
        if (deviceOsType.equalsIgnoreCase("mac")) {
            System.setProperty(ReadWriteHelper.ReadData("ChromeDriverPath"), ReadWriteHelper.ReadData("ChromeDriverLinkMac"));
        } else if (deviceOsType.equalsIgnoreCase("windows")) {
            System.setProperty(ReadWriteHelper.ReadData("ChromeDriverPath"), ReadWriteHelper.ReadData("chromeDriverLinkWindows"));
        } else if (deviceOsType.equalsIgnoreCase("Unix")) {
            System.setProperty(ReadWriteHelper.ReadData("ChromeDriverPath"), ReadWriteHelper.ReadData("chromeDriverLinkLinux"));
        }
    }

    private synchronized void setFireFoxBrowser(String deviceOsType) {
        if (deviceOsType.equalsIgnoreCase("mac")) {
            System.setProperty(ReadWriteHelper.ReadData("FireFoxDriverPath"), ReadWriteHelper.ReadData("FireFoxBrowserPathMac"));
        } else if (deviceOsType.equalsIgnoreCase("windows")) {
            System.setProperty(ReadWriteHelper.ReadData("FireFoxDriverPath"), ReadWriteHelper.ReadData("FireFoxBrowserPathWindows"));
        }
    }

    public void setAppiumDriver() {
        DesiredCapabilities capabilities = getAppiumDesiredCapabilities();
        try {
            driver.set( new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities));
        } catch (MalformedURLException e) {
            e.getMessage();
        }
    }

    private static DesiredCapabilities getAppiumDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device", "Android");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        return capabilities;
    }

    @AfterMethod(enabled = true) public synchronized void stopDriver() {
        try {
            System.out.println(Thread.currentThread().getId()+ "killed");
            driver.get().quit();
        } catch (Throwable e) {
            e.getStackTrace();
        }
    }

}



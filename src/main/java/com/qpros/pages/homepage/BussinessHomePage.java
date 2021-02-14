package com.qpros.pages.homepage;

import com.qpros.helpers.ActionsHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BussinessHomePage {

    public static By business = By.xpath("//a[.='Business']");
    public static By mobile = By.xpath("//ul[@class='nav navbar-nav navbar-left']//a[.='Mobile']");
    public static By internetAndCalls = By.xpath("//ul[@class='nav navbar-nav navbar-left']//a[.='Internet & Calls']");
    public static By digitalSolutions = By.xpath("//ul[@class='nav navbar-nav navbar-left']//a[.='Digital Solutions']");
    public static By cloudApps = By.xpath("//ul[@class='nav navbar-nav navbar-left']//a[.='Cloud Apps']");
    public static By businessEdge = By.xpath("//span[.='Business Edge']");
    public static By quickPay = By.xpath("//span[.='Quick Pay']");
    public static By mobilePlans = By.xpath("//span[.='Mobile Plans']");
    public static By internetBundle = By.xpath("//span[.='Internet Bundles']");
    public static By support = By.xpath("//span[.='Support']");

    public BussinessHomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);

    }

    public void  exploreBussinessHomePage() throws Exception {
        ActionsHelper.scrollTo(business);
        ActionsHelper.isElementPresent(business);
        ActionsHelper.retryClick(business,30);
        ActionsHelper.driverWait(3000);
        ActionsHelper.isElementPresent(business);
        ActionsHelper.scrollTo(mobile);
        ActionsHelper.isElementPresent(mobile);
        ActionsHelper.driverWait(3000);

        ActionsHelper.scrollTo(internetAndCalls);
        ActionsHelper.isElementPresent(internetAndCalls);
        ActionsHelper.driverWait(3000);

        ActionsHelper.scrollTo(digitalSolutions);
        ActionsHelper.isElementPresent(digitalSolutions);
        ActionsHelper.driverWait(3000);

        ActionsHelper.scrollTo(cloudApps);
        ActionsHelper.isElementPresent(cloudApps);
        ActionsHelper.driverWait(3000);

        ActionsHelper.scrollTo(businessEdge);
        ActionsHelper.isElementPresent(businessEdge);
        ActionsHelper.driverWait(3000);

        ActionsHelper.scrollTo(quickPay);
        ActionsHelper.isElementPresent(quickPay);
        ActionsHelper.driverWait(3000);

        ActionsHelper.scrollTo(mobilePlans);
        ActionsHelper.isElementPresent(mobilePlans);
        ActionsHelper.driverWait(3000);

        ActionsHelper.scrollTo(internetBundle);
        ActionsHelper.isElementPresent(internetBundle);
        ActionsHelper.driverWait(3000);

        ActionsHelper.scrollTo(support);
        ActionsHelper.isElementPresent(support);
        ActionsHelper.driverWait(5000);


    }
}

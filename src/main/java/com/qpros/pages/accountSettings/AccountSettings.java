package com.qpros.pages.accountSettings;

import com.qpros.helpers.ActionsHelper;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.qpros.helpers.ActionsHelper.*;
import static com.qpros.helpers.ActionsHelper.driverWait;

public class AccountSettings {

    public static By myAccount = By.xpath("//a[.='mdotoum']");
    public static By myEtisalat = By.xpath("//a[.='My Etisalat']");
    public static By account = By.id("accountName");
    public static By profileAndSettings = By.xpath("//ul[@class='sub-account-menu']//a[contains(.,'PROFILE & SETTINGS')]");


    public static void viewAccountsettings() throws Exception {

        driverWait(3000);
        retryClick(myAccount, 30);
        retryClick(myEtisalat, 30);
        driverWait(3000);
        Assert.assertEquals("https://www.etisalat.ae/b2c/dashboard.html", ActionsHelper.getCurrentUrl());
        driverWait(3000);
        retryClick(account, 30);
        retryClick(profileAndSettings, 30);
        waitForExpectedElement(By.xpath("//li[.='PROFILE & SETTINGS']"));
        try {
            isElementPresent(By.xpath("//h2[contains(.,'PROFILE & SETTINGS')]"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        isElementPresent(By.xpath("//h3[@class='mb0 mt0']"));
        driverWait(300);
        scrollTo(By.xpath("//button[@class='btn btn-default active ripple-effect btn-big']/span[contains(.,'UPDATE')]"));
        isElementPresent(By.xpath("//h3[contains(.,'Contact Preferences')]"));
    }

    public static void operateAccountSettings() throws Exception {
        driverWait(3000);
        retryClick(account, 30);
        retryClick(profileAndSettings, 30);
        waitForExpectedElement(By.xpath("//li[.='PROFILE & SETTINGS']"));
        waitForExpectedElement(By.xpath("//h2[contains(.,'PROFILE & SETTINGS')]"));
        waitForExpectedElement(By.xpath("//h3[@class='mb0 mt0']"));
        driverWait(300);
        scrollTo(By.xpath("//button[@class='btn btn-default active ripple-effect btn-big']/span[contains(.,'UPDATE')]"));
        waitForExpectedElement(By.xpath("//h3[contains(.,'Contact Preferences')]"));
        driverWait(300);
        if (element(By.id("setAsPrimaryAccount2")).isSelected()) {
            isElementPresent(By.id("setAsPrimaryAccount"));
            clickAction(element(By.id("setAsPrimaryAccount")));
        } else {
            isElementPresent(By.id("setAsPrimaryAccount2"));
            clickAction(element(By.id("setAsPrimaryAccount2")));
        }
        driverWait(300);
        retryClick(By.xpath("//button[@class='btn btn-default active ripple-effect btn-big']/span[contains(.,'UPDATE')]"), 30);
        driverWait(5000);
    }
}

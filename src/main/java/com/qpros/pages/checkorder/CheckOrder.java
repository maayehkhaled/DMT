package com.qpros.pages.checkorder;

import com.qpros.helpers.ActionsHelper;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.qpros.helpers.ActionsHelper.*;

public class CheckOrder {
    public static By myAccount = By.xpath("//a[.='mdotoum']");
    public static By myEtisalat = By.xpath("//a[.='My Etisalat']");
    public static By account = By.id("accountName");
    public static By myOrders =By.xpath("//ul[@class='sub-account-menu']//a[contains(.,'My orders')]");
    public static By orderID =By.xpath("//div[@class='cart']/div[2]//div[@class='col-sm-6 col-md-3 order-info-item']/div[contains(.,'Order ID')]");


    public static void checkOrders() {
        driverWait(3000);
        navigate("https://www.etisalat.ae/b2c/dashboard.html");
        driverWait(3000);
        Assert.assertEquals("https://www.etisalat.ae/b2c/dashboard.html", ActionsHelper.getCurrentUrl());
        driverWait(3000);
        retryClick(account, 30);
        retryClick(myOrders, 30);
        driverWait(3000);
        try {
            isElementPresent(orderID);
            isElementPresent(By.xpath("//div[.='690141201']"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        driverWait(5000);
    }
}

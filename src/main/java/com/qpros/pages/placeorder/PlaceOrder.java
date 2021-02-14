package com.qpros.pages.placeorder;

import com.qpros.helpers.ActionsHelper;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.qpros.helpers.ActionsHelper.*;
import static com.qpros.helpers.ActionsHelper.driverWait;

public class PlaceOrder {


    public static void checkPlaceOrder() throws Exception {
        driverWait(5000);
        retryClick(By.xpath("//body[@class='consumer']//div[1]/div[@class='tile-table device-card']//span[.='BUY NOW']"), 30);
        driverWait(4000);
        if(ActionsHelper.isElementPresent(By.xpath("//h3[@class='check-label ng-binding']"))){
            driver.findElement(By.xpath("//h3[@class='check-label ng-binding']")).click();
            driverWait(1000);
            retryClick(By.xpath("//button[contains(.,'Accept')]"),30);
            driverWait(1000);
        }
        retryClick(By.xpath("//button[.='ADD TO CART']"), 30);
        driverWait(5000);
        retryClick(By.xpath("//div[@class='ng-binding']"),30);
        driverWait(5000);
        Assert.assertTrue(isElementPresent(By.xpath("//h3[@class='ng-binding']")));
        driverWait(5000);
    }
}

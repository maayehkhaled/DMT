package com.qpros.pages.payment;

import com.qpros.helpers.ActionsHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import javax.swing.*;

public class Payment {

    public Payment(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public static By consumer = By.xpath("//a[.='Consumer']");
    public static By mobile = By.xpath("//a[.='Mobile']");
    public static By home = By.xpath("//a[.='Home']");
    public static By devices = By.xpath("//a[.='Devices']");
    public static By trending = By.xpath("//a[.='Trending']");
    public static By dsfOffers = By.xpath("//a[.='DSF Offers']");
    public static By bestSeller = By.xpath("//h1[@class='nv-section-title nv-mb-lg-24 nv-mt-2']");
    public static By quickPay = By.linkText("Quick Pay");
    public static By fiveG = By.linkText("5G");
    public static By footer = By.xpath("//a[contains(.,'Esaad Offer')]");

    public void navigateToQuickPay() throws Exception {
//        ActionsHelper.isElementPresent(consumer);
//        ActionsHelper.driverWait(3000);
//        ActionsHelper.isElementPresent(mobile);
//        ActionsHelper.driverWait(1000);
//        ActionsHelper.isElementPresent(home);
//        ActionsHelper.driverWait(1000);
//        ActionsHelper.isElementPresent(devices);
//        ActionsHelper.driverWait(3000);
//        ActionsHelper.scrollTo(trending);
//        ActionsHelper.isElementPresent(trending);
//        ActionsHelper.driverWait(3000);
//
//        ActionsHelper.scrollTo(dsfOffers);
//        ActionsHelper.isElementPresent(dsfOffers);
//        ActionsHelper.driverWait(3000);
//
//        ActionsHelper.scrollTo(bestSeller);
//        ActionsHelper.isElementPresent(bestSeller);
//        ActionsHelper.driverWait(3000);
//        ActionsHelper.scrollTo(footer);
//        ActionsHelper.driverWait(3000);
//
        ActionsHelper.scrollTo(quickPay);
        ActionsHelper.isElementPresent(quickPay);
        ActionsHelper.driverWait(3000);
        ActionsHelper.retryClick(quickPay, 30);
        ActionsHelper.driverWait(3000);
        ActionsHelper.isElementPresent(consumer);
        ActionsHelper.driverWait(3000);
        ActionsHelper.isElementPresent(mobile);
        ActionsHelper.driverWait(1000);
        ActionsHelper.isElementPresent(home);
        ActionsHelper.driverWait(1000);
        ActionsHelper.isElementPresent(devices);
        ActionsHelper.driverWait(3000);
        ActionsHelper.scrollTo(quickPay);
        ActionsHelper.isElementPresent(By.xpath("//div[@class=' col-xs-12 col-sm-12 col-md-8 col-md-offset-2 col-md-offset-right-2']//div[@class='addLabel']"));
        ActionsHelper.driverWait(1000);
        if (ActionsHelper.isElementPresent(By.id("Rt_Decline"))) {
            ActionsHelper.retryClick(By.id("Rt_Decline"), 30);
        }
        ActionsHelper.sendKeysWithClear(By.id("quickPayAcNum"), "0568440488");
        ActionsHelper.driverWait(1000);
        ActionsHelper.retryClick(By.xpath("//button[@class='btn btn-default active ripple-effect btn-big']"), 30);
        ActionsHelper.driverWait(3000);
        ActionsHelper.isElementPresent(By.id("quickPostpayeMain"));
        ActionsHelper.isElementPresent(By.id("qPayAmount"));
        ActionsHelper.driverWait(500);
        ActionsHelper.sendKeysWithClear(By.id("qPayAmount"), "1");
        ActionsHelper.isElementPresent(By.className("price-info-box"));
        ActionsHelper.isElementPresent(By.id("acceptContinue"));
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(By.id("acceptContinue"), 30);
        ActionsHelper.driverWait(3000);
        ActionsHelper.isElementPresent(By.xpath("//p[.='Credit/Debit Cards']"));
        ActionsHelper.driverWait(3000);
        ActionsHelper.isElementPresent(By.id("payNowBtn"));
        ActionsHelper.retryClick(By.id("payNowBtn"), 30);
        ActionsHelper.driverWait(10000);
        ActionsHelper.isElementPresent(By.id("cardNumber"));
        ActionsHelper.sendKeysWithClear(By.id("cardNumber"), "5147405000249601");
        ActionsHelper.driverWait(300);
        ActionsHelper.retryClick(By.className("month"), 30);
        ActionsHelper.retryClick(By.xpath("//span[contains(.,'06 - JUN')]"), 30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(By.className("year"), 30);
        ActionsHelper.retryClick(By.xpath("//span[contains(.,'2023')]"), 30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.isElementPresent(By.id("ValidationCode"));
        ActionsHelper.sendKeysWithClear(By.id("ValidationCode"),"123");
        ActionsHelper.driverWait(300);
        ActionsHelper.retryClick(By.xpath("//input[@value='Pay']"),30);
        ActionsHelper.driverWait(10000);




    }

}

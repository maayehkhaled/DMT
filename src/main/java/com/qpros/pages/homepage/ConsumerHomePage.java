package com.qpros.pages.homepage;

import com.qpros.common.Base;
import com.qpros.helpers.ActionsHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ConsumerHomePage extends Base {
    public ConsumerHomePage(WebDriver driver) {
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

    public static void ExploreConsumerHomePage() throws Exception {
        ActionsHelper.isElementPresent(consumer);
        ActionsHelper.driverWait(3000);
        ActionsHelper.isElementPresent(mobile);
        ActionsHelper.driverWait(1000);
        ActionsHelper.isElementPresent(home);
        ActionsHelper.driverWait(1000);
        ActionsHelper.isElementPresent(devices);
        ActionsHelper.driverWait(3000);
        ActionsHelper.scrollTo(trending);
        ActionsHelper.isElementPresent(trending);
        ActionsHelper.driverWait(3000);

//        ActionsHelper.scrollTo(dsfOffers);
//        ActionsHelper.isElementPresent(dsfOffers);
//        ActionsHelper.driverWait(3000);

        ActionsHelper.scrollTo(bestSeller);
        ActionsHelper.isElementPresent(bestSeller);
        ActionsHelper.driverWait(3000);

        ActionsHelper.scrollTo(quickPay);
        ActionsHelper.isElementPresent(quickPay);
        ActionsHelper.driverWait(3000);

        ActionsHelper.isElementPresent(fiveG);
        ActionsHelper.driverWait(5000);

    }
}

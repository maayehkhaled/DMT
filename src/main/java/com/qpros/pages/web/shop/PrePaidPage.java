package com.qpros.pages.web.shop;

import com.qpros.common.annotation.STEP;
import com.qpros.common.web.Base;
import com.qpros.reporting.QuantaTestManager;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qpros.pages.web.common.Forms;
import com.qpros.pages.web.data.URL;

import java.util.List;

import static com.qpros.helpers.ActionsHelper.*;

@Getter
public class PrePaidPage extends Base {

    public PrePaidPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public long DRIVER_WAIT = 1000;

    @FindBy(linkText = "Buy Now")
    private WebElement prepaidPAYGbutton;

    @FindBy(linkText = "Buy Now")
    private WebElement buybutton;

    @FindBy(css = ".order__items")
    private WebElement orderitems;

    @FindBy(css = ".device-card-container")
    private WebElement mobilecards;

    @FindBy(linkText = "Choose")
    private WebElement choosebutton;

    @FindBy(css = ".pt-4 > .row > .col")
    private WebElement deviceselection;

    @FindBy(id = "search-home")
    private WebElement locationtextbox;

    @FindBy(linkText = "Confirm location")
    private WebElement confirmlocation;

    @FindBy(css = ".drop-down")
    private WebElement plandropdown;

    @FindBy(css = ".filter-radio-group > .du-custom-radio:nth-child(2) .du-radio-details-val")
    private WebElement nocontractselection;

    @FindBy(linkText = "Select")
    private WebElement selectbutton;

    @FindBy(css = ".v-sheet--outlined")
    private WebElement devicesheet;

    @FindBy(css = ".pack-basic:nth-child(1) > .du-radio-status2")
    private WebElement freeselection;

    @FindBy(className = "home-plans-steps")
    private WebElement freepackages;

    @FindBy(css = ".sticky-button")
    private WebElement submitbtn;

    @FindBy(id = "use-map")
    private WebElement mapcontainer;

    @FindBy(css = ".pill-buttons .radio-group:nth-child(1)")
    private WebElement maleradio;//.pill-buttons .radio-group:nth-child(1) > label

    @FindBy(name = "dob")
    private WebElement Dateofbirth;

    @FindBy(name = "address-floorno")
    private WebElement floorno;

    @FindBy(css = ".cartSummaryGenerateOTPButton")
    private WebElement confirmButton;

    @FindBy(xpath = "//*[@id=\"eid-reconfirmation\"]/div/div/div/div[2]/div/div/div/div[3]/a[2]")
    private WebElement yesMatch;

    @FindBy(id = "personalDetailsForm")
    private WebElement personalDetailsForm;

    @FindBy(css = ".du-customer-details-continue")
    private WebElement continueBtn;
    @FindBy(css = ".confirmation__copy")
    private WebElement confirmationcopy;

    @FindBy(css = ".order__items")
    private WebElement ordersummary;


    @STEP(name = "prepaid flexi", description = "prepaid flexi")
    public void prepaidflexi() {
        navigate(URL.PREPAID_FLEXI_PLAN_URL);
        waitToBeClickable(buybutton, 120);
        moveToElementByActions(buybutton);
        clickElementActions(buybutton);

        List<WebElement> orderSummary = driver.findElements(By.className(".order-confirmation-page"));
        System.out.println(orderSummary.size());
        if (orderSummary.size() == 0)
            driver.navigate().refresh();
        // ctrl+F5
        driverWait(5000);
        Actions actionObject = new Actions(driver);
        actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.F5).keyUp(Keys.CONTROL).perform();
        driverWait(5000);
        actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.F5).keyUp(Keys.CONTROL).perform();

        waitVisibility(confirmButton, 90);
        scrollTo(confirmButton);
        retryClick(confirmButton, 60);
        waitVisibility(personalDetailsForm, 60);
        yesMatchOperation();

    }

    @STEP(name = "Pay as you go", description = "Pay as you go")
    public void Payasyougo() {//prepaid
        isElementPresent(By.xpath("//section[@class='quickLinks text-center']//a[@href='https://www.du.ae/personal/mobile/prepaid-plans']"));
        logManager.STEP("The User Click on PrePaid Icon","");
        logManager.INFO("Click on Prepaid icon",false);
        retryClick(By.xpath("//section[@class='quickLinks text-center']//a[@href='https://www.du.ae/personal/mobile/prepaid-plans']"),30);
        int count =0;
        while(!isElementPresent(By.xpath("//ul[@id='du-tabs']//a[.='Flexi']"))){
            count++;
            driverWait(1000);

            if(count==5) {
                QuantaTestManager.getTest().fail("Prepaid Plans is not shown in the Breadcrumb menu");
            }
        }

        logManager.STEP("The User Click on \"Pay as you Go Tab\" Icon","https://www.du.ae/prepaid/payg");
        logManager.INFO("Click on Pay as you Go Tab",false);
        retryClick(By.xpath("//ul[@id='du-tabs']//a[.='Flexi']"),30);
        while (!isElementPresent(prepaidPAYGbutton)){
            driverWait(1000);
        }
        scrollTo(By.xpath("//p[@class='du-black subtitle1']"));
        logManager.STEP("The User Click on \"Buy Now\" button","");
        logManager.INFO("Click on \"Buy Now\" button",false);
        retryClick(prepaidPAYGbutton, 30);
        waitVisibility(orderitems, 60);
        while (!isElementPresent(By.className("cartSummaryGenerateOTPButton"))){
            driverWait(1000);
        }
        scrollTo(confirmButton);
        driverWait(5000);
        retryClick(confirmButton, 60);
        waitVisibility(personalDetailsForm, 60);
        yesMatchOperation();
    }

    @STEP(name = "Easy Plan", description = "Easy Plan")
    public void EasyPlan() {
        navigate(URL.EASY_PLAN_URL);
        retryClick(buybutton,30);
        waitVisibility(ordersummary, 60);
        waitUntilElementIsDisplayed(By.className("cartSummaryGenerateOTPButton"));
        isElementPresent(confirmButton);
        scrollTo(confirmButton);
        driverWait(5000);
        retryClick(confirmButton, 60);
        yesMatchOperation();
    }


    public void yesMatchOperation() {
        Forms forms = new Forms(driver);
        Forms.fillForm(driver);
        waitVisibility(yesMatch, 60);
        isElementPresent(yesMatch);
        retryClick(yesMatch, 30);
        logManger.STEP("Click on Submit and place order","The User Clicks on Submit and place order");
        isElementPresent(continueBtn);
        retryClick(continueBtn, 40);
    }

}

package com.qpros.pages.web.shop;

import com.qpros.common.annotation.STEP;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.pages.web.common.Forms;
import com.qpros.pages.web.data.TestData;
import com.qpros.pages.web.data.URL;
import com.qpros.reporting.QuantaTestManager;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import static com.qpros.helpers.ActionsHelper.*;

@Getter
public class HomePlansPage extends Base {

    public long DRIVER_WAIT = 1000;

    public HomePlansPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    @FindBy(id = "search-home")
    public WebElement locationtextbox;

    @FindBy(linkText = "Confirm location")
    public WebElement confirmlocation;

    @FindBy(css = ".drop-down")
    public WebElement plandropdown;

    @FindBy(css = ".filter-radio-group > .du-custom-radio:nth-child(2) .du-radio-details-val")
    public WebElement nocontractselection;

    @FindBy(linkText = "Select")
    public WebElement selectbutton;

    @FindBy(css = ".v-sheet--outlined")
    public WebElement devicesheet;

    @FindBy(css = ".pack-basic:nth-child(1) > .du-radio-status2")
    public WebElement freeselection;

    @FindBy(className = "home-plans-steps")
    public WebElement freepackages;

    @FindBy(css = ".sticky-button")
    public WebElement submitbtn;

    @FindBy(id = "use-map")
    public WebElement mapcontainer;

    @FindBy(className = "file-drop-container")
    public WebElement uploadersdiv;

    @FindBy(className = "book-appointment")
    public WebElement bookappointment;

    @FindBy(css = ".du-radio-status2")
    public WebElement radiostatus2;

    @FindBy(name = "dob")
    public WebElement dob;

    @FindBy(css = ".datepicker:nth-child(27) > .datepicker-years .next > .fa")
    public WebElement next;

    @FindBy(css = ".datepicker:nth-child(27) .year:nth-child(1)")
    public WebElement years;

    @FindBy(css = ".datepicker-top .month:nth-child(1)")
    public WebElement month;

    @FindBy(css = ".old:nth-child(2)")
    public WebElement day;

    @FindBy(css = ".form-section-title:nth-child(28)")
    public WebElement documentsuploadersdiv;

    @FindBy(css = ".du-text-field")
    public WebElement dobdiv;

    @FindBy(css = ".pack-basic:nth-child(1) > .du-radio-details")
    public WebElement free;

    @FindBy(css = ".custom-radio-group:nth-child(1) > .du-custom-radio:nth-child(1) .du-radio-details-val")
    public WebElement free2;

    @FindBy(css = ".checkout-container")
    public WebElement checkoutcontainer;

    @FindBy(css = ".confirmation__copy")
    public WebElement confirmationcopy;

    @STEP(name = "In zone", description = "The User Should be able to Purchase plan ,check it out and reach Thank You Page")
    public void Inzone() {
        navigate(URL.HOME_PLANS_URL);
        logManager.STEP("Select Zone", "The User Select In Zone");
        logManager.INFO("The User Select In Zone", false);

        searchHome(TestData.INZONE_LOCATION);
        waitVisibility(plandropdown, 30);
        plandropdown.click();
        nocontractselection.click();

        waitVisibility(selectbutton, 50);
        ActionsHelper.moveToElementByActions(selectbutton);
        selectbutton.click();

        if (driver.get().findElements(By.id("search-home")).size() == 1) {
            searchHome(TestData.INZONE_LOCATION);
        }
        if (driver.get().findElements(By.className("shop-optimization-reveal")).size() == 1) {
            driver.get().findElement(By.className("close-button")).click();
        }
        retryClick(free, 60);

        retryClick(free2, 60);

        //continuetofillform();


    }

    @STEP(name = "Out zone ", description = "Out zone")
    public void Outzone() {
        navigate(URL.HOME_PLANS_URL);
        ActionsHelper.actionsClick(locationtextbox, TestData.OUTZONE_LOCATION);

        waitVisibility(confirmlocation, 30);
        retryClick(confirmlocation, 60);

        waitVisibility(selectbutton, 30);
        moveToElementByActions(selectbutton);
        retryClick(selectbutton, 30);

        if (driver.get().findElements(By.id("search-home")).size() == 1) {
            locationtextbox.sendKeys(TestData.OUTZONE_LOCATION);
            locationtextbox.sendKeys(Keys.RETURN);
            waitVisibility(confirmlocation, 30);
            confirmlocation.click();
        }

        if (driver.get().findElements(By.className("shop-optimization-reveal")).size() == 1)
            driver.get().findElement(By.className("close-button")).click();

//        waitVisibility(selectbutton, 30);
//        moveToElementByActions(selectbutton);
//        retryClick(selectbutton, 60);


        //continuetofillform();

    }

    public void searchHome(String location) {
        isElementPresent(locationtextbox);
        ActionsHelper.sendKeysWithClear(locationtextbox, location + Keys.RETURN);
        waitVisibility(confirmlocation, 30);
        if (isElementPresent(confirmlocation)) {
            retryClick(confirmlocation, 30);
        } else {
            QuantaTestManager.getTestNode().fail("Confirm Location is not Accessible or Shown in WebPage");
        }
    }

    public void continuetofillform() {
        WebElement contbtn = driver.get().findElement(By.className("js-sticky-ctrl"));
        ((JavascriptExecutor) driver.get()).executeScript("window.scrollTo(0," + contbtn.getLocation().x + ")");
        retryClick(contbtn, 30);
        waitVisibility(element(By.className("step2FormSubmit")), 60);

        WebElement continueToFillForm = driver.get().findElement(By.className("step2FormSubmit"));
        ((JavascriptExecutor) driver.get()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        waitVisibility(continueToFillForm, 60);

        continueToFillForm.click();

        waitVisibility(checkoutcontainer, 20);
        isElementPresent(checkoutcontainer);
        Forms form = new Forms(driver.get());
        form.fillZoneForm();

        ((JavascriptExecutor) driver.get()).executeScript("document.getElementsByClassName(\"du-sticky\")[0].style.zIndex=99;");
        driverWait(30);

        WebElement SubmitButton = driver.get().findElement(By.className("js-form-ctrl2"));
        waitVisibility(SubmitButton, 60);
        ActionsHelper.waitToBeClickable(SubmitButton, 60);
        isElementPresent(SubmitButton);
        retryClick(SubmitButton, 30);

        waitVisibility(confirmationcopy, 60);
    }

}

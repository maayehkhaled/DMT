package com.qpros.pages.web.shop;

import com.qpros.common.annotation.STEP;
import com.qpros.common.web.Base;
import com.qpros.reporting.QuantaTestManager;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qpros.pages.web.common.Forms;
import com.qpros.pages.web.data.URL;

import java.util.List;

import static com.qpros.helpers.ActionsHelper.*;

@Getter
public class DevicesPage extends Base {

    public DevicesPage(WebDriver driver) {
        PageFactory.initElements(Base.driver, this);
    }

    @FindBy(xpath = "//span[contains(.,'Add to cart')]")
    private WebElement addtocartbtn;

    @FindBy(css = ".order__items")
    private WebElement ordersummary;

    @FindBy(css = ".cartSummaryGenerateOTPButton")
    private WebElement confirmButton;

    @FindBy(css = ".more-link")
    private WebElement morelink;

    @FindBy(css = ".v-sheet--outlined")
    private WebElement devicesheet;

    @FindBy(css = ".checkout-container")
    private WebElement checkoutcontainer;

    @FindBy(xpath = "//*[@id=\"eid-reconfirmation\"]/div/div/div/div[2]/div/div/div/div[3]/a[2]")
    private WebElement yesMatch;

    @FindBy(css = ".du-customer-details-continue")
    private WebElement continueBtn;

    @FindBy(xpath = "//span[contains(text(),'Existing')]")
    private WebElement existingduPlanBtn;

    @FindBy(css = ".v-btn--has-bg > .v-btn__content")
    private WebElement sendPINBtn;

    @FindBy(css = ".otp-input")
    private WebElement PINNumberDiv;

    @FindBy(className = "device-card-container")
    private List<WebElement> allMobilesCardsList;

    @FindBy(className = "secondary-btn--primary-color")
    private WebElement chooseBtn;

    @FindBy(className = "v-size--x-small")
    private WebElement inStockLabel;

    @FindBy(className = "AED0")
    private List<WebElement> mobiledevicesList;

    @STEP(name = "Navigate to the device page and click on \"Choose and Customize\"", description = "")
    public void MobilePhonesNewCustomerFlexiOrNational() {
        isElementPresent(By.cssSelector("a[aria-label='Devices']"));
        retryClick(By.cssSelector("a[aria-label='Devices']"), 30);
        waitForExpectedElement(By.xpath("//ul[@id='du-tabs']//a[.='Devices']"));
        isElementPresent(By.xpath("//ul[@id='du-tabs']//a[.='Devices']"));
        int Index = 0;
        boolean ISInStock = false;
        for (int k = 0 + Index; k < allMobilesCardsList.size(); k++) {
            WebElement MobileCard = allMobilesCardsList.get(k);
            String Plan = MobileCard.getText();
            if (Plan.contains("+ Postpaid plan")) {
                MobileCard.findElement(By.className("secondary-btn--primary-color")).click(); // choose
                waitVisibility(inStockLabel, 30);
                if (inStockLabel.getText().contains("In stock")) {
                    ISInStock = true;
                    logManager.STEP("Choose the required Variant , Color and Storage", "");
                    break;
                } else {
                    navigate(URL.MOBILE_PHONES_DEVICE_ONLY_URL);
                    allMobilesCardsList = driver.findElements(By.className("device-card-container"));
                    Index++;
                }

            }
            if (ISInStock)
                break;
        }

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

        waitVisibility(addtocartbtn, 30);
        isElementPresent(addtocartbtn);
        retryClick(addtocartbtn, 30);
        driverWait(60);
        waitVisibility(ordersummary, 60);
        scrollTo(confirmButton);
        driverWait(5000);
        logManager.STEP("Verify Details and Click the \"Confirm\" button", "");
        retryClick(confirmButton, 60);
        waitVisibility(checkoutcontainer, 60);
        Forms forms = new Forms(driver);
        forms.fillFormWithUploaders();
        scrollTo(continueBtn);
        waitToBeClickable(continueBtn, 30);
        retryClick(continueBtn, 60);
        System.out.println(driver.getCurrentUrl());

    }

    @STEP(name = "DIP Existing Device With plan", description = "DIP Existing Device With plan ")
    public Boolean DIPExistingDeviceWithplan() {
        logManager.STEP("Navigate to the device page and click on \"Choose and Customize\"\n", "the user Navigate to the device page and click on \"Choose and Customize\"");
        isElementPresent(By.cssSelector("a[aria-label='Devices']"));
        logManager.INFO("Navigate to the device page", false);
        retryClick(By.cssSelector("a[aria-label='Devices']"), 30);
        waitForExpectedElement(By.xpath("//ul[@id='du-tabs']//a[.='Devices']"));
        isElementPresent(By.xpath("//ul[@id='du-tabs']//a[.='Devices']"));
        int Index = 0;
        boolean ISInStock = false;
        for (int k = 0 + Index; k < allMobilesCardsList.size(); k++) {
            WebElement MobileCard = allMobilesCardsList.get(k);
            String Plan = MobileCard.getText();
            if (Plan.contains("+ Postpaid plan")) {
                MobileCard.findElement(By.className("secondary-btn--primary-color")).click(); // choose
                waitVisibility(inStockLabel, 90);
                if (driver.findElement(By.className("v-size--x-small")).getText().contains("In stock")) {
                    ISInStock = true;
                    break;
                } else {
                    navigate(URL.MOBILE_PHONES_DEVICE_ONLY_URL);
                    allMobilesCardsList = driver.findElements(By.className("device-card-container"));
                    Index++;
                }

            }
            if (ISInStock)
                break;
        }

        if (driver.findElements(By.className("v-dialog--active")).size() > 0) {
            //were sorry popup
            retryClick(driver.findElement(By.className("v-btn")), 60); // press okay
            System.out.println("This option is temporarily unavailable for this specific model.\n" +
                    "\n" +
                    "Please proceed with a new mobile plan, or explore other models which allow purchases with an existing plan.");
            return true;
        } else {
            retryClick(existingduPlanBtn, 60);
            driver.findElements(By.xpath("//input[contains(@id,'input-')]")).get(2).sendKeys("0514993124");
            waitToBeClickable(sendPINBtn, 60);
            retryClick(sendPINBtn, 60);
            waitVisibility(PINNumberDiv, 60);
            return PINNumberDiv.isDisplayed();
        }


    }

    @STEP(name = "Mobile Phones Device Only ", description = "Mobile Phones Device Only")
    public void MobilePhonesDeviceOnly() {
        navigate(URL.MOBILE_PHONES_DEVICE_ONLY_URL);
        List<WebElement> casts = driver.findElements(By.id("csat_container"));// survey
        if (casts.size() == 1)
            driver.findElement(By.linkText("No thanks")).click();
        int Index = 0;
        WebElement Device;
        if (mobiledevicesList.size() > 0) {
            for (int i = 0 + Index; i < mobiledevicesList.size(); i++) {
                while (!mobiledevicesList.get(i).isDisplayed()) {
                    retryClick(morelink, 60);
                }
                Device = mobiledevicesList.get(i);
                scrollTo(mobiledevicesList.get(i));
                waitVisibility(mobiledevicesList.get(i), 60);
                retryClick(mobiledevicesList.get(i).findElement(By.className("btn-choose")), 60);
                driverWait(3000);
                if (inStockLabel.getText().contains("In stock")) {
                    retryClick(addtocartbtn, 30);
                    waitVisibility(ordersummary, 60);
                    waitUntilElementIsDisplayed(By.className("cartSummaryGenerateOTPButton"));
                    scrollTo(confirmButton);
                    driverWait(8000);
                    retryClick(confirmButton, 60);
                    waitVisibility(checkoutcontainer, 60);
                    Forms forms = new Forms(driver);
                    Forms.fillForm(driver);
                    waitVisibility(yesMatch, 60);
                    retryClick(yesMatch, 60);
                    waitToBeClickable(continueBtn, 60);
                    retryClick(continueBtn, 60);
                    driverWait(1000);
                    waitVisibility(driver.findElement(By.name("MasterCard")), 90);
                    isElementPresent(By.cssSelector("body > center > center:nth-child(6) > table > tbody > tr > td"));
                    isElementPresent(By.cssSelector("/html/body/center/table[5]/tbody/tr[1]/td/table/tbody/tr/td/a"));
                    retryClick(By.xpath("/html/body/center/table[5]/tbody/tr[1]/td/table/tbody/tr/td/a"), 30);

                    break;

                    //TODO Check(done)
                }
                System.out.println("the first one is out stock !!!");
                navigate(URL.MOBILE_PHONES_DEVICE_ONLY_URL);
                // mobiledevicesList = driver.findElements(By.className("AED0"));
                System.out.println(Device);
                Index++;
            }
        } else {
            System.out.println("there is no devices");
            logManger.WARN("There is no Devices ");
            QuantaTestManager.getTest().skip("There is no devices to conduct this Test");
        }
        // DO Nothing,  the test failed because there is no mobile only
        driverWait(1000);

    }


}

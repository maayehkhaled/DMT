package com.qpros.pages.web.shop;

import com.qpros.common.annotation.STEP;
import com.qpros.common.web.Base;
import com.qpros.pages.web.common.Forms;
import com.qpros.pages.web.data.TestData;
import com.qpros.pages.web.data.URL;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static com.qpros.common.Utils.uploadfile;
import static com.qpros.helpers.ActionsHelper.*;

@Getter

public class PostPaidPage extends Base {

    public PostPaidPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    @FindBy(linkText = "Postpaid plans")
    private WebElement postPaidPlansLinkText;

    @FindBy(linkText = "Prepaid Easy")
    private WebElement PrepaidEasyLinkText;

    @FindBy(linkText = "Pay As You Go")
    private WebElement payAsYouGolinkText;

    @FindBy(css = "#form2 .v-btn__content")
    private WebElement buyNowButton2;

    @FindBy(css = "#form3 .v-btn__content")
    private WebElement buyNowButton3;

    @FindBy(css = "#form4 .v-btn__content")
    private WebElement buyNowButton4; // without country 200


    @FindBy(css = "#form16 .v-btn__content")
    private WebElement buyNowButton5; // 1200 no contract  national

    @FindBy(css = "#form17 .v-btn__content")
    private WebElement buyNowPlan600;


    @FindBy(css = ".cartSummaryGenerateOTPButton") //
    private WebElement confirmButton; //

    @FindBy(linkText = "Shop")
    private WebElement shopLinkText;

    @FindBy(css = ".newnav-main-bar")
    private WebElement mainMenu;

    @FindBy(css = ".glide__slides")
    private WebElement glideSlides;

    @FindBy(css = ".checkout-steps-list")
    private WebElement checkOutStepsList;

    @FindBy(css = ".checkout-container")
    private WebElement checkoutContainer;

    @FindBy(id = "customerEmail")
    private WebElement customerEmailTextBox;

    @FindBy(id = "phone")
    private WebElement phoneTextBox;

    @FindBy(id = "inputFullAddress")
    private WebElement inputFullAddressTextBox;

    @FindBy(id = "emirates")
    private WebElement citiesSelect;

    @FindBy(id = "inputLandmark")
    private WebElement inputLandmarkTextBox;

    @FindBy(id = "openImageFrontOcrBtn")
    private WebElement openImageFrontOcrBtn;

    @FindBy(id = "openImageBackOcrBtn")
    private WebElement openImageBackOcrBtn;

    @FindBy(xpath = "//*[@id=\"personalDetailsForm\"]/div[1]/fieldset[6]/div")
    private WebElement checkValidate;

    @FindBy(css = ".group:nth-child(10) label") //name="checkDqFlag"
    private WebElement checkDqFlag;

    @FindBy(css = ".du-customer-details-continue")
    private WebElement continueBtn; //


    @FindBy(xpath = "//*[@id=\"eid-reconfirmation\"]/div/div/div/div[2]/div/div/div/div[3]/a[2]")
    private WebElement yesMatch;

    @FindBy(css = ".du-newnav-main-bar")
    private WebElement newNavMain;

    @FindBy(name = "idd")
    private WebElement countrySelect;

    @FindBy(id = "address.idNumber")
    private WebElement addressIdNumber;

    @FindBy(id = "address.firstName")
    private WebElement addressFirstName;

    @FindBy(id = "nationality")
    private WebElement nationality;

    @FindBy(id = "gender")
    private WebElement gender;
    @FindBy(id = "expiryDatePd")
    private WebElement expiryDatePd;
    @FindBy(id = "dateOfBirthPd")
    private WebElement dateOfBirthPd;

    @FindBy(id = "personalDetailsForm")
    private WebElement personalDetailsForm;

    @FindBy(css = ".inverse > .v-btn__content")
    private WebElement moreOptions;

    @FindBy(css = "#minutesFilter > .v-radio:nth-child(1) > .v-label")
    private WebElement flexiOption;

    @FindBy(css = "#minutesFilter > .v-radio:nth-child(2) > .v-label")
    private WebElement nationalOption;


    @FindBy(css = "#contractFilter > .v-radio:nth-child(1) .v-input--selection-controls__ripple")
    private WebElement monthContract;

    @FindBy(css = "#contractFilter > .v-radio:nth-child(2) > .v-label")
    private WebElement noContract;


    @FindBy(css = "#minutesFilter > .v-radio:nth-child(2) > .v-label")
    private WebElement nationalMinutes;

    @FindBy(id = "postpaid")
    private WebElement postpaidDiv;

    @FindBy(xpath = "//span[contains(.,'Power Plans')]")
    private WebElement powerPlanElement;

    @FindBy(css = ".du-new-nav")
    private WebElement topMenue;

    @FindBy(css = ".confirmation__copy")
    private WebElement confirmationCopy;

    @FindBy(linkText = "Mobile Plans")
    private WebElement mobilePlansLinkText;

    @FindBy(id = "enableOCREditButton")
    private WebElement enableOCREditButton;

    @FindBy(id = "nameOnCard")
    private WebElement nameOnCard;

    @FindBy(xpath = "/html/body/main/div/div/div/div/div[2]/span[2]/button")
    private WebElement confirmBtn;

    @FindBy(linkText = "Skip, Get plan only")
    private WebElement SkipGetplanonlyLink;

    @FindBy(css = ".buyPlanButton")
    private WebElement buyPlanButton;

    @FindBy(css = ".deviceModelPlans-newCustomerButton")
    private WebElement IwantanewoneButton;

    @FindBy(linkText = "Order")
    private WebElement orderButton;

    @FindBy(css = ".deviceModelPlans-existingCustomerButton")
    private WebElement ModifymyexistingduplanButton;

    @FindBy(css = ".deviceModelPlans-migratingCustomerButton")
    private WebElement ChangeexistingnumbertoduButton;

    @FindBy(id = "nameOnCard")
    private WebElement nameOnCardTextBox;

    @FindBy(id = "cardNumber")
    private WebElement cardNumberCardTextBox;

    @FindBy(id = "cardExpiry")
    private WebElement cardExpiryTextBox;

    @STEP(name = "Postpaid 12 months Flexi", description = "Postpaid 12 months Flexi")
    public void Postpaid12monthsFlexi() {
        navigateToPostPaidPowerPlans();

        glideCheck(buyNowButton2);

        waitVisibility(checkOutStepsList, 20);
        isElementPresent(checkOutStepsList);

        Select countries = new Select(countrySelect);
        countries.selectByVisibleText("Egypt");
        scrollTo(confirmButton);
        driverWait(5000);
        isElementPresent(confirmButton);
        retryClick(confirmButton, 60);
        Forms form = new Forms(driver.get());
        form.fillForm();

        waitVisibility(yesMatch, 60);
        yesMatch.click();
        waitToBeClickable(continueBtn, 40);
        continueBtn.click();

        //TODO : check if the Payment Page element is exist  get page title , unique element in this scenario (plan name , value )

    }

    @STEP(name = "Postpaid no contract national", description = "Postpaid no contract national")
    public void postPaidNoContractNational() {

        waitVisibility(shopLinkText, 30);
        moveToElementByActions(shopLinkText);
        clickElementActions(shopLinkText);

        waitVisibility(mobilePlansLinkText, 20);
        moveToElementByActions(mobilePlansLinkText);
        clickElementActions(mobilePlansLinkText);

        waitVisibility(postPaidPlansLinkText, 10);
        moveToElementByActions(postPaidPlansLinkText);
        clickElementActions(postPaidPlansLinkText);

        waitVisibility(postpaidDiv, 40);

        JavascriptExecutor js = (JavascriptExecutor) driver.get();
        js.executeScript("document.getElementsByClassName(\"du-new-nav\")[0].style.zIndex=0;");

        ((JavascriptExecutor) driver.get()).executeScript("arguments[0].scrollIntoView(true);", moreOptions);
        driverWait(10);

        waitVisibility(moreOptions, 60);
        moveToElementByActions(moreOptions);
        clickElementActions(moreOptions);

        waitVisibility(nationalOption, 20);
        nationalOption.click();

        waitVisibility(noContract, 20);
        noContract.click();

        waitVisibility(glideSlides, 20);
        moveToElementByActions(glideSlides);

        waitVisibility(buyNowPlan600, 20);
        clickElementActions(buyNowPlan600);


        waitVisibility(confirmBtn, 30);
        moveToElementByActions(confirmBtn);
        clickElementActions(confirmBtn);

        waitVisibility(personalDetailsForm, 60);
        Forms form = new Forms(driver.get());
        form.fillForm();

        waitVisibility(yesMatch, 60);
        yesMatch.click();

        waitToBeClickable(continueBtn, 40);
        continueBtn.click();


    }

    @STEP(name = "Postpaid 1200 No contract National", description = "Postpaid 1200 No contract National")
    public void Postpaid1200NoContractNational() {
        navigateToPostPaidPowerPlans();

        moreOptions(nationalOption, noContract);
        glideCheck(buyNowButton5);

        waitVisibility(checkOutStepsList, 60);
        logManager.STEP("Navigate to the Order summary page and check details", "the System Navigate User to the Order summary page and check details");
        logManager.INFO("the System Navigate User to the Order summary page and check details", false);
        isElementPresent(By.xpath("//tbody[1]//strong[.='Power Plan 1200']"));
        isElementPresent(By.xpath("//span[contains(.,'120 National Data,')]"));

        isElementPresent(checkOutStepsList);
        waitUntilElementIsDisplayed(By.className("cartSummaryGenerateOTPButton"));
        logManager.STEP("Click on Confirm", "the User Click on Confirm");
        isElementPresent(By.className("cartSummaryGenerateOTPButton"));
        logManager.INFO("the User Click on Confirm", false);
        scrollTo(confirmButton);
        driverWait(5000);
        isElementPresent(confirmButton);
        retryClick(confirmButton, 60);
        logManager.STEP("Post verification reach your details page enter all necessary details", "the User Post verification reach your details page enter all necessary details");
        waitVisibility(personalDetailsForm, 60);
        isElementPresent(customerEmailTextBox);
        customerEmailTextBox.sendKeys(TestData.CUSTOMER_EMAIL);
        logManager.INFO("the User enter Email in the customer Email Text Box", false);

        isElementPresent(phoneTextBox);
        phoneTextBox.sendKeys(TestData.PHONE_NUMBER);//"971508975431"
        logManager.INFO("the User enter phone in the customer phone Text Box", false);

        inputFullAddressTextBox.sendKeys(TestData.FULL_ADDRESS);
        logManager.INFO("the User enter address in the customer address Text Box", false);

        Select cities = new Select(citiesSelect);
        cities.selectByVisibleText(TestData.CITY);
        ((JavascriptExecutor) driver.get()).executeScript("arguments[0].scrollIntoView(true);", openImageFrontOcrBtn);
        logManager.INFO("the User enter city in the customer City Drop Down", false);

        driverWait(60);

        uploadfile(driver.get(), "E:\\workspace\\DU-Rana\\Q-Pros Automation Framework -Rana-The Latest one\\src\\main\\resources\\images\\s1.jpg", openImageFrontOcrBtn);
        uploadfile(driver.get(), "E:\\workspace\\DU-Rana\\Q-Pros Automation Framework -Rana-The Latest one\\src\\main\\resources\\images\\s2.jpg", openImageBackOcrBtn);
        logManager.INFO("the User upload Required Documents", false);

        driverWait(10000);


//        List<WebElement> invalidEditors = driver.get().findElements(By.className("parsley-error"));
//        if (invalidEditors.size() > 0) {
//            waitUntilElementIsDisplayed(By.id("enableOCREditButton"));
//            scrollTo(enableOCREditButton);
//            driverWait(1000);
//            retryClick(enableOCREditButton, 60);
//            invalidEditors.get(0).sendKeys("11/11/2024");
//        }

        WebElement x = driver.get().findElement(By.className("checkbox-group"));
        scrollTo(x);
        retryClick(x, 30);

        driverWait(10000); // wait  for opecity
        WebElement checkInvoice = driver.get().findElement(By.xpath("//*[@id=\"personalDetailsForm\"]/div[1]/fieldset[6]/div"));
        scrollTo(checkInvoice);
        retryClick(checkInvoice, 60);
        isElementPresent(By.xpath("//label[contains(.,'I consent to list my number in the directory enquiry database')]"));
        retryClick(By.xpath("//label[contains(.,'I consent to list my number in the directory enquiry database')]"), 60);
        waitToBeClickable(continueBtn, 90);
        retryClick(continueBtn, 60);
        logManager.STEP("6. Click on \"Continue\" and reach payment gateway page", "the User Click on \"Continue\" and reach payment gateway page");
        logManager.INFO("the User Click on \"Continue\" and reach payment gateway page", false);

        driverWait(1000);

        if (driver.get().findElements(By.className("callout")).size() > 0) {

            System.out.println(" We can see you have already reached the limit of permitted rate plans with this Emirates ID. Please go to your nearest retail store for more support with this request.");
        } else {
            waitVisibility(nameOnCard, 60);
            nameOnCardTextBox.sendKeys(TestData.NAME_ON_CARD);
            cardNumberCardTextBox.sendKeys(TestData.CARD_NUMBER);
            cardExpiryTextBox.sendKeys(TestData.CARD_EXPIRY);
            waitToBeClickable(driver.get().findElement(By.className("sticky-button")), 60);
            retryClick(driver.get().findElement(By.className("sticky-button")), 60);
        }

    }

    @STEP(name = "Postpaid No contract National", description = "Postpaid No contract National")
    public void PostpaidNoContractNational() {
        navigateToPostPaidPowerPlans();

        moreOptions(nationalOption, noContract);
        glideCheck(buyNowPlan600);

        waitVisibility(checkOutStepsList, 60);
        logManager.STEP("Navigate to the Order summary page and check details", "the System Navigate User to the Order summary page and check details");
        logManager.INFO("the System Navigate User to the Order summary page and check details", false);
        isElementPresent(By.xpath("//tbody[1]//strong[.='Power Plan 600']"));
        isElementPresent(By.xpath("//span[contains(.,'50 GB National Data,')]"));
        isElementPresent(checkOutStepsList);

        waitUntilElementIsDisplayed(By.className("cartSummaryGenerateOTPButton"));
        logManager.STEP("Click on Confirm", "the User Click on Confirm");
        isElementPresent(By.className("cartSummaryGenerateOTPButton"));
        logManager.INFO("the User Click on Confirm", false);
        scrollTo(confirmButton);
        driverWait(5000);
        retryClick(confirmButton, 60);
        logManager.STEP("Post verification reach your details page enter all necessary details", "the User Post verification reach your details page enter all necessary details");
        Forms forms = new Forms(driver.get());
        forms.fillForm();

        waitVisibility(yesMatch, 60);
        yesMatch.click();
        waitToBeClickable(continueBtn, 40);
        continueBtn.click();
        // TODO Payment check () no contract and national , get title , offer name , value of price
    }

    @STEP(name = "Postpaid New Emarati Plan", description = "Postpaid New Emarati Plan")
    public void PostpaidNewEmaratiPlan() {
        navigate(URL.POSTPAID_NEW_EMIRATI_PLANS_URL);
        retryClick(driver.get().findElement(By.className("js-card-info-relay")), 60);
        retryClick(SkipGetplanonlyLink, 60);

    }

    @STEP(name = "Postpaid Control Plan I want a new one", description = "Postpaid Control Plan I want anew one")
    public boolean PostpaidControlPlanIwantanewone() {
        navigate(URL.POSTPAID_CONTROL_PLANS_URL);
        if (driver.get().findElements(By.className("csat_container")).size() > 0)
            driver.get().findElement(By.linkText("No thanks")).click();

        retryClick(buyPlanButton, 60);
        retryClick(IwantanewoneButton, 60);

        if (driver.get().findElements(By.xpath("//button[contains(text(),'Add')]")).size() > 0) {
            // Additional add ones
            scrollTo(driver.get().findElements(By.xpath("//button[contains(text(),'Add')]")).get(0));
            retryClick(driver.get().findElements(By.xpath("//button[contains(text(),'Add')]")).get(0), 30);
        }


        retryClick(orderButton, 60);

        Forms form = new Forms(driver.get());
        form.fillForm2(driver.get());

        waitToBeClickable(driver.get().findElement(By.className("js-form-ctrl-secondary")), 60);
        retryClick(driver.get().findElement(By.className("js-form-ctrl-secondary")), 60);// continue button

        driverWait(1000);
        waitVisibility(driver.get().findElement(By.className("icon-filled-green")), 60);//Success icon in thank you div
        return driver.get().findElement(By.className("icon-filled-green")).isDisplayed();

        // waitVisibility(driver.get().findElement(By.className("mnmi-wrap")),60);//thank you div


    }

    @STEP(name = "Postpaid Control Plan Modify my existing du plan", description = "Post paid Control Plan Modify my existing du plan")
    public boolean PostpaidControlPlanModifymyexistingduplan() {
        navigate(URL.POSTPAID_CONTROL_PLANS_URL);

        if (driver.get().findElements(By.className("csat_container")).size() > 0)
            driver.get().findElement(By.linkText("No thanks")).click();

        retryClick(buyPlanButton, 60);

        retryClick(ModifymyexistingduplanButton, 60);
        //  retryClick(ChangeexistingnumbertoduButton,60);

        waitVisibility(driver.get().findElement(By.cssSelector("button.btn")), 60);
        scrollTo(driver.get().findElement(By.cssSelector("button.btn")));

        retryClick(driver.get().findElement(By.cssSelector("button.btn")), 30);

        retryClick(orderButton, 60);
        waitVisibility(driver.get().findElement(By.className("checkout-content")), 60);
        Forms form = new Forms(driver.get());
        form.fillForm2(driver.get());

        waitToBeClickable(driver.get().findElement(By.className("js-form-ctrl-secondary")), 60);
        retryClick(driver.get().findElement(By.className("js-form-ctrl-secondary")), 60);// continue button
        driverWait(1000);
        waitVisibility(driver.get().findElement(By.className("icon-filled-green")), 60);//Success icon in thank you div
        return driver.get().findElement(By.className("icon-filled-green")).isDisplayed();

        // waitVisibility(driver.get().findElement(By.className("mnmi-wrap")),60);//thank you div


    }

    @STEP(name = "Postpaid Control Plan Change existing number to du", description = "Post paid Control Plan Change existing number to du")
    public boolean PostpaidControlPlanChangeexistingnumbertodu() {
        navigate(URL.POSTPAID_CONTROL_PLANS_URL);

        if (driver.get().findElements(By.className("csat_container")).size() > 0)
            driver.get().findElement(By.linkText("No thanks")).click();

        retryClick(buyPlanButton, 60);

        retryClick(ChangeexistingnumbertoduButton, 60);

        waitVisibility(driver.get().findElement(By.cssSelector("button.btn")), 60);
        scrollTo(driver.get().findElement(By.cssSelector("button.btn")));

        retryClick(driver.get().findElement(By.cssSelector("button.btn")), 30);

        retryClick(orderButton, 60);
        waitVisibility(driver.get().findElement(By.className("checkout-content")), 60);

        Forms form = new Forms(driver.get());
        form.fillForm2(driver.get());

        waitToBeClickable(driver.get().findElement(By.className("js-form-ctrl-secondary")), 60);
        retryClick(driver.get().findElement(By.className("js-form-ctrl-secondary")), 60);// continue button
        driverWait(1000);
        waitVisibility(driver.get().findElement(By.className("icon-filled-green")), 60);//Success icon in thank you div
        return driver.get().findElement(By.className("icon-filled-green")).isDisplayed();

        // waitVisibility(driver.get().findElement(By.className("mnmi-wrap")),60);//thank you div


    }


    @STEP(name = "navigate to https://www.du.ae/business", description = "the user navigate to https://www.du.ae/business")
    public void navigateToBusinessPage() {
        actionClickStepClick("navigate to https://www.du.ae/business", By.xpath("//div[@class='du-segment-links']/a[.='Business']"));
    }

    public void navigateToSmallBusiness() {
        driverWait(2000);
        actionClickStepClick("click on \"small business\" icon", By.xpath("//div[@class='sub-nav__inner']//a[@href='/smallbusiness']/span[@class='btn-icon spin circle animate']"));
    }

    public void navigateToLargeBusiness() {
        driverWait(2000);
        actionClickStepClick("click on \"Large business\" icon", By.xpath("//div[@class='sub-nav__inner']//a[@href='/enterprise']/span[@class='btn-icon spin circle animate']"));
    }

    public void navigateToGovernment() {
        driverWait(2000);
        actionClickStepClick("click on \"Government\" icon", By.xpath("//div[@class='sub-nav__inner']//a[@href='/government']/span[@class='btn-icon spin circle animate']"));
    }

    public void navigateToSmallBusinessMobilePlan() {
        actionClickStepClick("Click on \" Mobile Plans\"", By.xpath("//span[.='Mobile Plans']"));
        retryClick(By.xpath("//a[.='Executive Plan']"), 30);
    }

    public void navigateToLargeBusinessMobilePlan() {
        actionClickStepClick("Click on \" Mobile Plans\"", By.xpath("//span[.='Mobile Plans']"));
        retryClick(By.xpath("//li[@class='is-active']/a[.='Business Mobile Plan']"), 30);
    }


    public void navigateToDevicesTab() {
        actionClickStepClick("Click on \" Devices\"", By.xpath("//a[@id='devices-tabs']"));
    }

    public void navigateToDevicesTabPhones() {
        actionClickStepClick("Click on \" Phones\"", By.xpath("//a[@id='Phones-linktabs']"));
    }

    public void navigateToDevicesTabTablets() {
        actionClickStepClick("Click on \" Phones\"", By.xpath("//a[@id='Tablets-linktabs']"));
    }

    public void navigateToDevicesTabRouters() {
        actionClickStepClick("Click on \" Routers\"", By.xpath("//li[contains(.,'Routers')]"));

    }

    public void navigateToDevicesTabWatches() {
        actionClickStepClick("Click on \" Watches\"", By.xpath("//a[@id='Watches-linktabs']"));

    }

    public void navigateToDevicesTabPhonesAndChoose() {

        actionClickScrollStepClick("Click on \" choose\" to select plan", By.className("primary-btn--primary-color"));

    }

    public void navigateToSmallBusinessMobilePlanInternationalLocal() {

        logManager.STEP("Select National or International and select 12 month", "the user Select National or International and select 12 month");
        waitVisibility(element(By.xpath("//label[.='International (Local & International)']")), 30);
        isElementPresent(By.xpath("//label[.='International (Local & International)']"));
        logManager.INFO("the user Select National or International and select 12 month", false);
        retryClick(By.xpath("//div[@class='radio-group active']/label[.='International (Local & International)']"), 30);

    }

    public void navigateToSmallBusinessMobilePlanInternationalLocal24() {

        logManager.STEP("Select National or International and select 24 month", "the user Select National or International and select 12 month");
        waitVisibility(element(By.xpath("//label[.='International (Local & International)']")), 30);
        isElementPresent(By.xpath("//label[.='International (Local & International)']"));
        logManager.INFO("the user Select National or International and select 24 month", false);

        retryClick(By.xpath("//div[@class='radio-group active']/label[.='International (Local & International)']"), 30);


    }

    public void navigateToSmallBusinessMobilePlanInternationalLocalSelectPlan() {
        actionClickStepClick("Choose one of the displayed plans and click select",
                By.xpath("//div[@class='cards__scrollable-inner']/div[13]//span[contains(.,'select')]"));

    }

    public void navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCart() {
        actionClickScrollStepClick("Click \"add to cart \"button", By.xpath("//a[contains(.,'Add to cart')]"));
    }

    public void navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartDevices() {

        actionClickScrollStepClick("Click \"add to cart \"button", By.xpath("//button[@class='primary-btn--action-color gtm-device-details-continueButton btn-continue deviceDetailsPageContinueButton']"));
    }

    public void navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartChekOut() {
        actionClickStepClick("Click \"checkout\"", By.xpath("//button[@class='cartSummaryGenerateOTPButton-sme primary-btn--action-color']"));
    }

    public void navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartChekOutIdentifyExistingAccount() {

        logManager.STEP("Click \"checkout\"", "the user Click \"checkout\"");
        isElementPresent(By.xpath("//div[@id='existing-business']//div[@class='subtitle1']"));
        isElementPresent(By.xpath("//div[@id='existing-business']//div[@class='body2']"));
        isElementPresent(By.xpath("//input[@class='js-acc-dot focus']"));
        isElementPresent(By.xpath("//button[@class='cartSummaryGenerateOTPButton-sme primary-btn--action-color']"));
        logManager.INFO("the user Click \"checkout\"", false);
        sendKeys(By.xpath("//input[@class='js-acc-dot focus']"), "00998811");

    }


    public void moreOptions(WebElement minutesFilter, WebElement contractFilter) {

        waitVisibility(moreOptions, 60);
        moveToElementByActions(moreOptions);
        isElementPresent(moreOptions);
        clickElementActions(moreOptions);
        driverWait(1000);

        waitVisibility(minutesFilter, 60);
        isElementPresent(minutesFilter);
        minutesFilter.click();
        waitVisibility(contractFilter, 60);
        isElementPresent(contractFilter);
        contractFilter.click();
        driverWait(1000);
    }

    public void glideCheck(WebElement buyNowBtn) {
        waitVisibility(glideSlides, 60);
        isElementPresent(glideSlides);
        moveToElementByActions(glideSlides);
        waitVisibility(buyNowBtn, 60);
        isElementPresent(buyNowBtn);
        clickElementActions(buyNowBtn);
    }

    public void navigateToPostPaidPowerPlans() {
        logManager.STEP("Navigate to the postpaid power plan page", "the User Navigate to the postpaid power plan page");
        isElementPresent(By.xpath("//div[@id='slick-thumb']//div[@class='slick-slide slick-active']//p[.='Postpaid Power Plans']"));
        logManager.INFO("the User Navigate to the postpaid power plan page", false);
        retryClick(By.xpath("//div[@id='slick-thumb']//div[@class='slick-slide slick-active']//p[.='Postpaid Power Plans']"), 30);
        isElementPresent(By.xpath("//div[@id='slick-spotlight']//div[@class='slick-slide slick-current slick-active']//a[.='Buy online']"));
        logManager.INFO("the User clicks on Buy Now Button in the Navigation Slider Panel", false);
        retryClick(By.xpath("//div[@id='slick-spotlight']//div[@class='slick-slide slick-current slick-active']//a[.='Buy online']"), 30);
        //TODO : change to element name
        waitVisibility(postpaidDiv, 60);
        isElementPresent(postpaidDiv);
        isElementPresent(powerPlanElement);
        logManager.STEP("Select the SIM only plan and click on \"Order\"", "the User Select the SIM only plan and click on \"Order\"");
        logManager.INFO("the User Select the SIM only plan and click on \"Order\"", false);
        JavascriptExecutor js = (JavascriptExecutor) driver.get();
        js.executeScript("document.getElementsByClassName(\"du-new-nav\")[0].style.zIndex=0;");
        ((JavascriptExecutor) driver.get()).executeScript("arguments[0].scrollIntoView(true);", moreOptions);

    }

    public void actionClickStepClick(String step, By locator) {
        logManager.STEP(step, "the user " + step);
        waitVisibility(element(locator), 30);
        isElementPresent(locator);
        logManager.INFO("the user " + step, false);
        retryClick(locator, 30);

    }

    public void actionClickScrollStepClick(String step, By locator) {
        logManager.STEP(step, "the user " + step);
        scrollTo(locator);
        waitVisibility(element(locator), 30);
        isElementPresent(locator);
        logManager.INFO("the user " + step, false);
        retryClick(locator, 30);

    }

}

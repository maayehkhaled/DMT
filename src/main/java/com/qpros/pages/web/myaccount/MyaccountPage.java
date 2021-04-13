package com.qpros.pages.web.myaccount;

import com.qpros.common.UserType;
import com.qpros.common.annotation.STEP;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.helpers.ReadWriteHelper;
import com.qpros.pages.web.common.LoginPage;
import com.qpros.pages.web.data.*;
import com.qpros.reporting.QuantaManager;
import com.qpros.reporting.QuantaTestManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.qpros.pages.web.common.Forms;

import java.util.ArrayList;
import java.util.List;

import static com.qpros.helpers.ActionsHelper.*;

public class MyaccountPage extends Base {

    public MyaccountPage(WebDriver driver) {
        //
        PageFactory.initElements(Base.driver.get(), this);
    }

    @FindBy(xpath = "//*[@id=\"MSISDN\"]")
    WebElement phoneTextbox;

    @FindBy(xpath = "//*[@id=\"next\"]")
    WebElement nextButton;

    @FindBy(xpath = "//*[@id=\"parsley-id-5\"]/li")
    WebElement requiredLabel;

    @FindBy(linkText = "Link another account")
    WebElement linkAnotherAccountLinkText;
    WebElement menuIcon;

    @FindBy(className = "svg-bg-icon")
    WebElement SIMCardIcon;

    @FindBy(linkText = "My plan")
    WebElement MyplanLinkText;

    @FindBy(linkText = "Change / upgrade my plan")
    WebElement changeUpgradeMyPlanLinkText;

    @FindBy(xpath = "//*[@id=\"content\"]/div/div[3]/div[1]/div/div[1]/ul/li[1]")
    WebElement usedPlaninDetalisPageLable;

    @FindBy(linkText = "Quick Pay")
    WebElement quickPayLinkText;

    @FindBy(id = "MSISDN")
    WebElement MSISDN;// phone or account number textbox

    @FindBy(id = "next")
    WebElement nextBtn;

    @FindBy(name = "pay-amount")
    WebElement payAmountTextBox;

    @FindBy(id = "cc_number")
    WebElement cc_numberTextBx;

    @FindBy(id = "full_name")
    WebElement full_nameTextBox;

    @FindBy(id = "cc_expiry")
    WebElement cc_expiryTextBox;

    @FindBy(id = "cc_code")
    WebElement cc_codeTextBox;

    @FindBy(id = "checkTnC")
    WebElement checkTnCCheckBox;

    @FindBy(id = "pay_now")
    WebElement pay_now;

    @FindBy(xpath = "//*[@id=\"dunningPayment\"]/div/div/div[2]/button")
    WebElement MakeaPayment;

    @FindBy(name = "pay_amt")
    WebElement pay_amtTextBox;

    @FindBy(name = "cc_number")
    WebElement cc_numberTextBoxByName;

    @FindBy(name = "full_name")
    WebElement fullnameTextBox;

    @FindBy(name = "cc_expiry")
    WebElement ccexpiryTextBox;

    @FindBy(name = "cc_code")
    WebElement cccodeTextBox;

    @FindBy(className = "action-button")
    WebElement PaynowButton;

    @FindBy(className = "card-title-sub")
    List<WebElement> savedAndUnsavedCardLink;

    @FindBy(css = "label[data-value='Other']")
    WebElement otherCheckBox;

    @FindBy(id = "myInput")
    WebElement myInputAmountTextBox;

    @FindBy(className = "du-custom-radio")
    WebElement ducustomradio;

    @FindBy(linkText = "Recharge")
    WebElement rechargeLinkText;

    @FindBy(name = "otherAmount")
    WebElement otherAmountTextBox;

    @FindBy(className = "primary-btn--primary-color")
    WebElement requestOTPButton;

    @FindBy(className = "renewal__modal_inner")
    WebElement OTPDiv;

    @FindBy(xpath = "//i[@data-fragment='du-home']")
    List<WebElement> homePlanIcons;

    @FindBy(xpath = "//a[@href='#home0']")
    WebElement homePlanLink;

    @FindBy(id = "menu-home0")
    WebElement homePlanMenu;

    @FindBy(linkText = "Moving to a new Home?")
    WebElement MovingtoanewHomeLink;

    @FindBy(linkText = "Settings")
    WebElement settingsTab;

    @FindBy(linkText = "Payments")
    WebElement paymentsButton;


    @FindBy(linkText = "Add now")
    WebElement RechargeforfriendButton;

    @FindBy(id = "saveFriendName")
    WebElement saveFriendNameTextBox;

    @FindBy(id = "saveFriendNumber")
    WebElement saveFriendNumberTextBox;


    @FindBy(className = "rd-loader-cta")
    WebElement saveButton;

    @FindBy(linkText = "Make a payment")
    WebElement makeAPaymentButton;


    @FindBy(linkText = "Recharge")
    WebElement RechargeButton;

    @FindBy(linkText = "Forgot login or password?")
    WebElement forgotPasswordLink;

    @FindBy(linkText = "Pay/ Recharge for a friend")
    WebElement PayRechargeorafriendShortcutLinkText;

    @FindBy(className = "js-account-select")
    WebElement friendsDropDownlist;

    @FindBy(name = "recharge-card")
    WebElement rechargeCardTextBox;

    @FindBy(linkText = "Internet & TV")
    WebElement InternetAndTVLinkText;

    @FindBy(linkText = "Login to upgrade")
    WebElement LogintoupgradeLinkText;


    @FindBy(linkText = "Confirm location")
    WebElement ConfirmlocationLinkText;

    @FindBy(name = "friendAccNumber")
    WebElement friendAccNumberTextBox;

    @FindBy(name = "friendNickname")
    WebElement friendNicknameTextBox;

    @FindBy(id = "friendContinue")
    WebElement friendContinueButton;

    @FindBy(xpath = "//*[@id=\"postpaid0-label\"]/div/div/div[2]/span")
    WebElement planName;

    @FindBy(linkText = "Renew ID")
    WebElement renewId;

    @FindBy(id = "menu-postpaid0")
    WebElement menuPostPaid;

    @FindBy(className = "du-text-field")
    WebElement duTextField;

    @FindBy(className = "plans-accordion__header")
    List<WebElement> listOfPlans;

    @FindBy(xpath = "//button[contains(text(),'Recharge')]")
    List<WebElement> listOfRechargeBTN;

    @STEP(name = "Account linking", description = "Account linking")
    public synchronized void Accountlinking() { // internal server error
        driverWait(5000);
        waitVisibility(linkAnotherAccountLinkText, 60);
        retryClick(linkAnotherAccountLinkText, 60);
    }

    @STEP(name = "Rate plan benefits", description = "Rate plan benefits ")
    public synchronized boolean Rateplanbenefits() {
        driverWait(10000);
        waitVisibility(menuPostPaid, 60);
        waitVisibility(SIMCardIcon, 60);
        driverWait(1000);

//        retryClick(menuIcon, 120);
//        waitVisibility(MyplanLinkText, 60);
//        retryClick(MyplanLinkText, 60);
//        driverWait(10000);
//        int count=0;
//            if (!isElementPresent(changeUpgradeMyPlanLinkText)) {
//                QuantaTestManager.getTestNode().fail("Couldn't find the Expected Element to continue the test "+ changeUpgradeMyPlanLinkText);
//
//            }
//        retryClick(changeUpgradeMyPlanLinkText, 30);
//        driverWait(5000);
//        String usedPlaninDetalisPage = "";
//        if(isElementPresent(usedPlaninDetalisPageLable)) {
//            QuantaTestManager.getTestNode().fail("Couldn't find the Expected Element to continue the test "+ changeUpgradeMyPlanLinkText);
//
//        }
//        return planName.getText().replaceAll("\\s+", "").equalsIgnoreCase(usedPlaninDetalisPage.replaceAll("\\s+", ""));
        return true;
    }

    @STEP(name = "Non logged in bill payment Quick Pay with 1AED", description = "Non logged in bill payment Quick Pay with 1AED")
    public synchronized void NonloggedinbillpaymentQuickPaywith1AED() {
        navigate(URL.DU_HOME_PAGE_URL);
        logManager.STEP("click \"Quick pay\" from top navigation bar", "The User click \"Quick pay\" from top navigation bar");
        waitVisibility(quickPayLinkText, 60);
        isElementPresent(quickPayLinkText);
        logManager.INFO("The User click \"Quick pay\" from top navigation bar", false);
        retryClick(quickPayLinkText, 60);
        driverWait(1000);
        logManager.STEP("Enter the postpaid phone# or Accout# and click the \"Next\" button", "The User Enter the postpaid phone# or Accout# and click the \"Next\" button");
        waitVisibility(MSISDN, 60);
        isElementPresent(MSISDN);
        logManager.INFO("The User  Enter the postpaid phone# or Accout# and click the \"Next\" button", false);
        MSISDN.sendKeys(TestData.MSISDN);//// 0535432532: User doesn't exist  //  971551499312-
        retryClick(nextButton, 60);
        driverWait(3000);
        logManager.STEP("Enter/edit the amount (1 AED)", "The User Enter/edit the amount (1 AED)");
        waitVisibility(payAmountTextBox, 60);
        payAmountTextBox.clear();
        isElementPresent(payAmountTextBox);
        logManager.INFO("The User Enter/edit the amount (1 AED)", false);
        payAmountTextBox.sendKeys(TestData.PAY_AMOUNT_1_AED);
        enterCreditCardDetails();
        // moveToElement(pay_now);
        //  retryClick(pay_now,120);
        driverWait(8000);
    }

    @STEP(name = "Partial Pay with 1AED pay", description = "Partial Pay with 1AED pay")
    public synchronized void PartialPaywith1AEDpay() {
        driverWait(10000);
        waitVisibility(MakeaPayment, 60);
        retryClick(MakeaPayment, 60);
        driverWait(5000);
        waitVisibility(pay_amtTextBox, 60);
        if (savedAndUnsavedCardLink.size() > 0) {
            if (savedAndUnsavedCardLink.get(0).getText().contains(Messages.Pay_with_another_credit_card)) {// this code for saved card
                pay_amtTextBox.clear();
                pay_amtTextBox.sendKeys(TestData.PAY_AMOUNT_1_AED);
                cccodeTextBox.sendKeys(TestData.CC_CODE);
                moveToElement(checkTnCCheckBox);
            }
        } else {
            pay_amtTextBox.clear();
            pay_amtTextBox.sendKeys(TestData.PAY_AMOUNT_1_AED);
            cc_numberTextBoxByName.sendKeys(TestData.CC_NUMBER);
            fullnameTextBox.sendKeys(TestData.CARD_TYPE);
            ccexpiryTextBox.sendKeys(TestData.CC_EXPIRY);
            cccodeTextBox.sendKeys(TestData.CC_CODE);
            moveToElement(checkTnCCheckBox);
        }

        moveToElement(PaynowButton);

    }

    @STEP(name = "Recharge your line with 1AED pay", description = "Recharge your line with 1AED pay ")
    public synchronized String Rechargeyourlinewith1AEDpay() {
        /// for the first occurance
        driverWait(1000);
        if (listOfRechargeBTN.size() > 0) {
            for (int i = 0; i < listOfPlans.size(); i++) {
                retryClick(listOfPlans.get(i), 60);
                driverWait(1000);
                if (listOfRechargeBTN.get(0).isDisplayed()) {
                    retryClick(listOfRechargeBTN.get(0), 60);
                    driverWait(5000);
                    retryClick(otherCheckBox, 60);
                    waitVisibility(myInputAmountTextBox, 60);
                    myInputAmountTextBox.sendKeys(TestData.PAY_AMOUNT_1_AED);
                    retryClick(ducustomradio, 60);
                    waitVisibility(cccodeTextBox, 60);
                    cccodeTextBox.sendKeys(TestData.CC_CODE);
                    moveToElement(checkTnCCheckBox);
                    moveToElement(PaynowButton);
                    retryClick(PaynowButton, 60);
                    driverWait(2000);
                    break;
                }
            }

        } else {
            logManager.WARN("There is no Plan to be recharged.");

        }

        driverWait(10000);
        return driver.get().getTitle();

    }

    @STEP(name = "Non logged in Recharge Quick Recharge with 1AED", description = "Non loggedin Recharge Quick Recharge with 1AED")
    public synchronized void NonloggedinRechargeQuickRechargewith1AED() {
        driverWait(1000);
        retryClick(rechargeLinkText, 60);
        driverWait(1000);
        MSISDN.sendKeys(Account.PrePaidPhone);// prepaid phone#
        retryClick(nextBtn, 60);// take alot of time
        waitVisibility(otherCheckBox, 120);
        retryClick(otherCheckBox, 60);
        waitVisibility(otherAmountTextBox, 60);
        otherAmountTextBox.sendKeys(TestData.PAY_AMOUNT_1_AED);
        otherAmountTextBox.sendKeys(Keys.TAB);
        retryClick(duTextField, 30);
        retryClick(cc_numberTextBoxByName, 30);
        enterCreditCardDetails();
        moveToElement(pay_now);
        System.out.println(pay_now);
        retryClick(pay_now, 60);
        driverWait(8000);


    }
         //TODO : let farah review the steps
    @STEP(name = "ID renewal", description = "ID renewal")
    public synchronized boolean IDrenewal() {
        logManager.STEP("Navigate to Homepage ", URL.DU_HOME_PAGE_URL);
        logManager.INFO("The User Navigate to " + URL.DU_HOME_PAGE_URL, false);
        logManager.STEP("Click on Renew Id Enter MSISDN and Click Renew Registration", "");
        logManager.INFO("The User Click on Renew Id", false);
        isElementPresent(renewId);
        retryClick(renewId, 60);
        logManager.INFO("The User Perform Login", false);
        String[][] credentials = ReadWriteHelper.readCSVFile("C:\\Users\\Khaled Maayeh\\OneDrive - Quality Professionals",Files.Login_Credentials, 1, 2);
        LoginPage login = new LoginPage(driver.get());
        login.FillUserName(UserType.LoginUser.getUserName());
        login.FillPassword(UserType.LoginUser.getPassword());
        retryClick(requestOTPButton, 60);//login button //TODO : Change to dynamic locator
        driverWait(3000);
        // the run stop here: Please contact your company administrator or account manager for renewal !!

        //TODO : add While condition with counted waits
        if (driver.get().findElements(By.className("popup-reveal")).size() > 0) {
            return true;
            // the run stop here: Please contact your company administrator or account manager for renewal !!
        } else {
            retryClick(driver.get().findElements(By.className("du-custom-radio")).get(0), 60);// Choose the first phone number
            retryClick(requestOTPButton, 60);
            waitVisibility(OTPDiv, 60);
            return OTPDiv.isDisplayed();
        }

    }

    @STEP(name = "Home Realocation inzone", description = "Home Realocation inzone ")
    public synchronized void HomeRealocationinzone() { //Alternate
        driverWait(4000);
        // in case there is home plan visible  do the following
        if (homePlanIcons.size() > 0) {
            waitVisibility(homePlanLink, 60);
            retryClick(homePlanLink, 90);
            driverWait(1000);

            retryClick(homePlanMenu, 90);
            retryClick(MovingtoanewHomeLink, 90);
            List<WebElement> casts = driver.get().findElements(By.id("csat_container"));// survey

            if (casts.size() > 0)
                driver.get().findElement(By.linkText("No thanks")).click();
            Forms form = new Forms(driver.get());
            form.fillNewAddressForm(driver.get(), "IN");
        } else {
            logManger.WARN("there isnt any Home Plan");
            System.out.println("there isnt any Home Plan");

        }

    }

    @STEP(name = "Home Realocation outzone", description = "Home Realocation outzone")
    public synchronized void HomeRealocationoutzone() { //Alternate Ajman
        driverWait(4000);
        if (homePlanIcons.size() > 0) {
            // there are home plans
            waitVisibility(homePlanLink, 60);
            retryClick(homePlanLink, 90);
            driverWait(1000);
            retryClick(homePlanMenu, 90);
            retryClick(MovingtoanewHomeLink, 90);
            List<WebElement> casts = driver.get().findElements(By.id("csat_container"));// survey
            if (casts.size() > 0)
                driver.get().findElement(By.linkText("No thanks")).click();
            Forms form = new Forms(driver.get());
            form.fillNewAddressForm(driver.get(), "OUT");
        } else {
            logManger.INFO("there isn't any Home Plan",false);
            System.out.println("there isnt any Home Plan");
        }

    }

    @STEP(name = "Recharge for friend with 1AED pay", description = "Recharge for friend with 1AED pay ")
    public synchronized void Rechargeforfriendwith1AEDpay() { // Alternate Prepaid
        driverWait(4000);
        retryClick(settingsTab, 60);
        retryClick(paymentsButton, 60);
        ActionsHelper.scrollToEndOfPage();
        if (driver.get().findElements(By.linkText("Recharge")).size() > 0) {
            waitVisibility(RechargeButton, 90);
            retryClick(RechargeButton, 90);
        } else { // adding new friend
            retryClick(RechargeforfriendButton, 60);
            waitVisibility(saveFriendNameTextBox, 60);
            saveFriendNameTextBox.sendKeys("PREPAID NUMBER");
            saveFriendNumberTextBox.sendKeys(TestData.PRE_PAID_NUMBER);
            retryClick(saveButton, 60);
        }

        while (isElementPresent(otherCheckBox)) {
            retryClick(otherCheckBox, 90);
            break;
        }
        waitVisibility(driver.get().findElement(By.id("myInput")), 90);
        driver.get().findElement(By.id("myInput")).sendKeys("1");
        driver.get().findElement(By.id("myInput")).sendKeys(Keys.TAB);


//        if (driver.get().findElements(By.name("cc_number")).size() > 0) {
//            retryClick(duTextField, 30);
//            // cc_numberTextBx.click();
//            retryClick(cc_numberTextBoxByName, 30);
//            cc_numberTextBoxByName.sendKeys(TestData.CC_NUMBER);
//            fullnameTextBox.sendKeys(TestData.NAME_ON_CARD);
//            ccexpiryTextBox.sendKeys(TestData.CC_EXPIRY);
//        }

        cccodeTextBox.sendKeys("114");
        moveToElement(driver.get().findElement(By.id("checkTnC")));
        // retryClick( driver.get().findElement(By.className("action-button")),90);// recharge button


    }

    @STEP(name = "Pay for friend with 1AED pay", description = "Pay for friend with 1AED pay")
    public synchronized void Payforfriendwith1AEDpay() { // Alternate postpaid
        driverWait(4000);
        retryClick(settingsTab, 60);
        retryClick(paymentsButton, 60);
        ActionsHelper.scrollToEndOfPage();
        System.out.println(driver.get().findElements(By.linkText("Make a payment")).size());
        if (driver.get().findElements(By.linkText("Make a payment")).size() > 0) {
            waitVisibility(makeAPaymentButton, 90);
            retryClick(makeAPaymentButton, 90);
        } else {
            // adding new friend
            retryClick(RechargeforfriendButton, 60);
            waitVisibility(saveFriendNameTextBox, 60);
            saveFriendNameTextBox.sendKeys("POSTPAID NUMBER");
            saveFriendNumberTextBox.sendKeys(TestData.POST_PAID_NUMBER);
            retryClick(saveButton, 60);

        }

        driver.get().findElement(By.name("pay_amt")).sendKeys("1");
        if (driver.get().findElements(By.name("cc_number")).size() > 0) {
            driver.get().findElement(By.name("cc_number")).sendKeys(TestData.CC_NUMBER);
            fullnameTextBox.sendKeys(TestData.NAME_ON_CARD);
            driver.get().findElement(By.name("cc_expiry")).sendKeys(TestData.CC_EXPIRY);
        }

        cccodeTextBox.sendKeys("114");
        moveToElement(driver.get().findElement(By.id("checkTnC")));
        // retryClick( driver.get().findElement(By.className("action-button")),90);// recharge button

    }


    @STEP(name = "Forgot your password", description = "Forgot your password ")
    public synchronized void Forgotyourpassword() {
        navigate(URL.DU_LOGIN_URL);
        retryClick(forgotPasswordLink, 60);
        QuantaTestManager.getTest().skip("blocked by CAPTCHA");
        // blocked by CAPTCHA
    }

    @STEP(name = "Recharge for friend Shortcut", description = "Recharge for friend Shortcut")
    public synchronized void RechargeforfriendShortcut() {

        driverWait(1000);
        retryClick(PayRechargeorafriendShortcutLinkText, 60);
        if (driver.get().findElements(By.className("js-account-select")).size() > 1) {
            // There is at least one friend
            // so you can select one
            waitVisibility(friendsDropDownlist, 60);
            Select FriendsList = new Select(friendsDropDownlist);
            FriendsList.selectByIndex(1); // index of 0 is the default

        } else {
            // There is'nt any friend on the list
            //You must add one
            System.out.println("There is'nt any friend on the list");
            waitVisibility(friendAccNumberTextBox, 60);
            friendAccNumberTextBox.sendKeys(TestData.PRE_PAID_NUMBER);
            friendNicknameTextBox.sendKeys("PRE PAID NUMBER " + Keys.TAB);

            // friendAccNumberTextBox.sendKeys(TestData.POST_PAID_NUMBER);
            //  friendNicknameTextBox.sendKeys("POST PAID NUMBER " + Keys.TAB);
            retryClick(friendContinueButton, 60);

        }

        driverWait(2000);
        ActionsHelper.scrollToEndOfPage();
        if (driver.get().findElements(By.xpath("//input[@name='recharge-card']")).get(6).isDisplayed()) //  postpaid Number
        {
            // Postpaid Number
            System.out.println("Postpaid # ");
            driver.get().findElements(By.xpath("//input[@name='recharge-card']")).get(6).sendKeys("1" + Keys.TAB);

        } else {

            // prepaid Number
            System.out.println("PrePaid # ");
            otherCheckBox.click();
            driver.get().findElement(By.id("recharge-amount")).sendKeys("1" + Keys.TAB);

        }

        retryClick(cc_numberTextBoxByName, 30);
        cc_numberTextBoxByName.sendKeys(TestData.CC_NUMBER);
        fullnameTextBox.sendKeys(TestData.NAME_ON_CARD);
        ccexpiryTextBox.sendKeys(TestData.CC_EXPIRY);
        cccodeTextBox.sendKeys("114");
        moveToElement(driver.get().findElement(By.id("checkTnC")));


    }


    @STEP(name = "Home Fixed Journey (Internet & TV)-Login to upgrade ", description = "Home Fixed Journey (Internet & TV)-Login to upgrade ")
    public synchronized void HomeFixedJourneyInternetandTVLogintoupgrade() throws Exception { // alternate
        while (isElementPresent(driver.get().findElement(By.xpath("//a[.='Internet & TV']")))) {
            retryClick(driver.get().findElement(By.xpath("//a[.='Internet & TV']")), 60);
            break;
        }
        driverWait(1000);
        while (isElementPresent(driver.get().findElement(By.xpath("//a[@href='/personal/at-home/packages']")))) {
            retryClick(driver.get().findElement(By.xpath("//a[@href='/personal/at-home/packages']")), 60);
            break;
        }
        driverWait(3000);
        retryClick(LogintoupgradeLinkText, 60);
        driverWait(2000);
        List tabs = new ArrayList(driver.get().getWindowHandles());
        System.out.println(tabs.get(1));
        driver.get().switchTo().window(tabs.get(1).toString());
        driverWait(5000);
        System.out.println(driver.get().getCurrentUrl());
        String[][] Credentials = {{"dutest123456@gmail.com", "Dubai@12345"}};
        LoginPage login = new LoginPage(driver.get());
        login.PerformLogin(UserType.User);
        driverWait(5000);
        driver.get().findElement(By.id("search-home")).sendKeys("Dubai - United Arab Emirates" + Keys.RETURN);
        retryClick(ConfirmlocationLinkText, 60);
        driverWait(2000);
        System.out.println(driver.get().findElements(By.linkText("Select")).size());
        retryClick(driver.get().findElements(By.linkText("Select")).get(0), 60);
        if (driver.get().findElements(By.id("use-map")).size() > 0) {
            driver.get().findElement(By.id("search-home")).sendKeys("Dubai - United Arab Emirates" + Keys.RETURN);
            retryClick(ConfirmlocationLinkText, 60);
        }
        driverWait(2000);
        //  retryClick(driver.get().findElement(By.xpath("//*[@id=\"step-1\"]/div[3]/div[3]/div[2]/div/label[1]")),60);
        waitVisibility(driver.get().findElement(By.xpath("//*[@id=\"step-1\"]/div[3]/div[3]/div[2]/div/label[1]/div[3]")), 90);
        retryClick(driver.get().findElement(By.xpath("//*[@id=\"step-1\"]/div[3]/div[3]/div[2]/div/label[1]/div[3]")), 60);
        waitVisibility(driver.get().findElement(By.id("requestcallback")), 60);
        retryClick(driver.get().findElement(By.id("requestcallback")), 60);

        driverWait(2000);
        waitVisibility(driver.get().findElement(By.name("contactNumberConfirm")), 60);
        driver.get().findElement(By.name("contactNumberConfirm")).sendKeys("551499312");
        driver.get().findElement(By.name("emailIDConfirm")).sendKeys("farahm@q-pros.com");
        retryClick(driver.get().findElement(By.id("confirm-callback")), 60);


    }

    public synchronized void enterCreditCardDetails() {
        logManager.STEP("enter the Card details and check \"I agree\" radio button","the User 5.enter the Crad details and check \"I agree\" radio button");
        isElementPresent(cc_numberTextBx);
        cc_numberTextBx.sendKeys(TestData.CC_NUMBER);
        logManager.INFO("The User Enter enter Credit Card Number",false);
        isElementPresent(full_nameTextBox);
        full_nameTextBox.sendKeys(TestData.CARD_TYPE);
        logManager.INFO("The User Enter enter Full Name",false);
        isElementPresent(cc_expiryTextBox);
        cc_expiryTextBox.sendKeys(TestData.CC_EXPIRY);
        logManager.INFO("The User Enter enter Credit Card expiry Date",false);
        isElementPresent(cc_codeTextBox);
        cc_codeTextBox.sendKeys(TestData.CC_CODE);
        logManager.INFO("The User Enter enter Credit Card CVV",false);
        moveToElement(checkTnCCheckBox);
    }


}

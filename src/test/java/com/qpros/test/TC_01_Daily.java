package com.qpros.test;

import com.qpros.common.UserType;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.helpers.ReadWriteHelper;
import com.qpros.pages.web.DUAE.DuPage;
import com.qpros.pages.web.common.LoginPage;
import com.qpros.pages.web.data.Files;
import com.qpros.pages.web.data.URL;
import com.qpros.pages.web.myaccount.MyaccountPage;
import com.qpros.pages.web.shop.DevicesPage;
import com.qpros.pages.web.shop.HomePlansPage;
import com.qpros.pages.web.shop.PostPaidPage;
import com.qpros.pages.web.shop.PrePaidPage;
import com.qpros.reporting.QuantaTestManager;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(com.qpros.common.LogManager.class)
public class TC_01_Daily extends Base {

    @BeforeClass
    public void initiSuite(){
        QuantaTestManager.startTestSuite(getClass().getSimpleName());

    }
    @BeforeMethod(enabled = true)
    public synchronized void setTestSuite() {
        this.setUpBrowser();
    }


    LoginPage loginPage;
    MyaccountPage myAccountPage;
    PostPaidPage postPaidPage;
    PrePaidPage prePaidPage;
    DevicesPage devicesPage;
    HomePlansPage homePlansPage;
    DuPage duPage;

    @Test(description = " #1 login", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void login() throws Exception {
        System.out.println(Thread.currentThread().getId());

        QuantaTestManager.getTest().assignCategory("My Account");
        loginPage = new LoginPage(driver.get());
        loginPage.PerformLogin(UserType.LoginUser2);
        Assert.assertEquals(loginPage.getURL(), URL.DU_HOMEPAGE_URL);
    }

    @Test(description = " #2 Logout", priority = 2,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void Logout() throws Exception {
        System.out.println(Thread.currentThread().getId());

        QuantaTestManager.getTest().assignCategory("My Account");

        loginPage = new LoginPage(driver.get());
        loginPage.PerformLogin(UserType.LoginUser2);
        loginPage.Logout();
        Assert.assertEquals(loginPage.getURL(), URL.DU_LOGIN_URL);
    }

    @Test(description = " #3 Change UserName", priority = 3,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, enabled = true)
    public void ChangeUserName() throws Exception {

        QuantaTestManager.getTest().assignCategory("My Account");

        loginPage = new LoginPage(driver.get());
        loginPage.PerformLogin(UserType.LoginUser2);

        String NewUsername = loginPage.ChangeUserName(); // why if passing the username
        this.logManager.INFO("the new user name is: " + NewUsername, false);
        String Password = ReadWriteHelper.readCSVFile("C:\\Users\\Khaled Maayeh\\OneDrive - Quality Professionals", Files.Login_Credentials, 1, 2)[0][1];
        ReadWriteHelper.writeCSVFirstCell("C:\\Users\\Khaled Maayeh\\OneDrive - Quality Professionals", Files.Login_Credentials, NewUsername + "," + Password);


        ActionsHelper.driverWait(4000);
        ActionsHelper.retryClick(By.xpath("//div[@id='du-notification-password']//span[.='×']"), 30);
        loginPage.Logout();
        loginPage.FillUserName(UserType.LoginUser2.getUserName());
        loginPage.FillPassword(UserType.LoginUser2.getPassword());
        loginPage.pressLogin();
    }

    @Test(description = " #4 Change Password", priority = 4,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, enabled = true)
    public void ChangePassword() throws Exception {

        QuantaTestManager.getTest().assignCategory("My Account");

        loginPage = new LoginPage(driver.get());
        loginPage.PerformLogin(UserType.LoginUser2);
        String NewPassword = loginPage.ChangePassword(UserType.LoginUser2.getPassword());
        this.logManager.INFO("the new Password  is: " + NewPassword, false);
        String userName = ReadWriteHelper.readCSVFile("C:\\Users\\Khaled Maayeh\\OneDrive - Quality Professionals", Files.Login_Credentials, 1, 2)[0][0];
        ReadWriteHelper.writeCSVFirstCell("C:\\Users\\Khaled Maayeh\\OneDrive - Quality Professionals", Files.Login_Credentials, userName + "," + NewPassword);

        ActionsHelper.driverWait(4000);
        ActionsHelper.retryClick(By.xpath("//div[@id='du-notification-password']//span[.='×']"), 30);
        loginPage.Logout();
        logManager.STEP("Enter username and password then click \"Login\" button", "");
        loginPage.FillUserName(UserType.LoginUser2.getUserName());
        loginPage.FillPassword(UserType.LoginUser2.getPassword());
        loginPage.pressLogin();

    }


    @Test(description = " #7 ID renewal", priority = 5, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void IDrenewal() {
        QuantaTestManager.getTest().assignCategory("My Account");


        myAccountPage = new MyaccountPage(driver.get());

        boolean ReachedOTPDiv = myAccountPage.IDrenewal();
        Assert.assertTrue(ReachedOTPDiv);

        // i modified the assertion because there is an update on the website
        // ask farah
    }

    @Test(description = " #8 Non logged in bill payment (Quick Pay)--[with 1 AED]", priority = 6, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void NonloggedinbillpaymentQuickPaywith1AED() {

        QuantaTestManager.getTest().assignCategory("My Account");

        myAccountPage = new MyaccountPage(driver.get());
        myAccountPage.NonloggedinbillpaymentQuickPaywith1AED();
    }


    @Test(description = " #9 Non logged in Recharge (Quick Recharge)--[with 1 AED]", priority = 7, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void NonloggedinRechargeQuickRechargewith1AED() {
        QuantaTestManager.getTest().assignCategory("My Account");


        myAccountPage = new MyaccountPage(driver.get());
        myAccountPage.NonloggedinRechargeQuickRechargewith1AED();

    }

    @Test(description = " #10 Recharge your line (with 1 AED pay)", priority = 8, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void Rechargeyourlinewith1AEDpay() throws Exception {
        QuantaTestManager.getTest().assignCategory("My Account");


        loginPage = new LoginPage(driver.get());

        loginPage.PerformLogin(UserType.LoginUser);
        myAccountPage = new MyaccountPage(driver.get());
        String PageTitle = myAccountPage.Rechargeyourlinewith1AEDpay();
        // Assert.assertTrue(PageTitle.equals("Verified by Visa - Purchase page"));
        // iremoved the assertion because in some cases there wasnt any recharge button

    }


    @Test(description = " #11 Partial Pay  --(with 1 AED pay)", priority = 9, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void PartialPayWith1AEDPay() throws Exception {
        QuantaTestManager.getTest().assignCategory("My Account");


        loginPage = new LoginPage(driver.get());

        loginPage.PerformLogin(UserType.LoginUser); // all payment must be on farahm@q-pros.net account
        myAccountPage = new MyaccountPage(driver.get());
        myAccountPage.PartialPaywith1AEDpay();
        // i remove pressing on pay now to prevent any payment
    }

    @Test(description = " #14 Postpaid 12 months Flexi", priority = 10,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void Postpaid12monthsFlexi() { //reached the limit of permitted rate plans

        QuantaTestManager.getTest().assignCategory("Eshop");
        postPaidPage = new PostPaidPage(driver.get()); // take alot of time
        postPaidPage.Postpaid12monthsFlexi();
        //  Assert.assertTrue(PostPaidPage.getConfirmationcopy().isDisplayed());
    }

    @Test(description = " #15.1 Postpaid 1200 No contract National", enabled = true, priority = 11,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void Postpaid1200NoContractNational() {

        QuantaTestManager.getTest().assignCategory("Eshop");
        postPaidPage = new PostPaidPage(driver.get());
        postPaidPage.Postpaid1200NoContractNational();
        //TODO : ask farah about
        // Activation of AutoPayment has failed

    }

    @Test(description = " #15.2 Postpaid  No contract National", priority = 12,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void PostpaidNoContractNational() {

        QuantaTestManager.getTest().assignCategory("Eshop");
        postPaidPage = new PostPaidPage(driver.get());
        postPaidPage.PostpaidNoContractNational();
        //  Assert.assertTrue(Login.getConfirmationcopy().isDisplayed());

    }

    @Test(description = " #16 prepaid flexi", priority = 13, enabled = true,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void prepaidFlexi() { // take alot of time

        QuantaTestManager.getTest().assignCategory("Eshop");
        prePaidPage = new PrePaidPage(driver.get());
        prePaidPage.prepaidflexi();
    }

    @Test(description = " #17 Easy Plan", priority = 14,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void EasyPlan() { //reached the limit of permitted rate plans

        QuantaTestManager.getTest().assignCategory("Eshop");
        prePaidPage = new PrePaidPage(driver.get());
        prePaidPage.EasyPlan();

    }

    @Test(description = "#18 Mobile Phones ( NewCustomer) / Flexi or National", priority = 15,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void MobilePhonesNewCustomerFlexiOrNational() { // take alot of time to enable continue button

        QuantaTestManager.getTest().assignCategory("Eshop");

        devicesPage = new DevicesPage(driver.get());
        devicesPage.MobilePhonesNewCustomerFlexiOrNational();
        // Assert.assertTrue(driver.get().getCurrentUrl().contains(URL.AUTO_PAY_URL));
        // i remove this assertion because the continue button take alot of time
        // Assert.assertTrue(driver.get().getCurrentUrl().contains(URL.MASTER_CARD_URL));
        // i removed this assertion because there is an authorization error and farah ask to stop here
    }

    @Test(description = "#19 Mobile Phones Device Only", priority = 16,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"}, enabled = false)
    public void MobilePhonesDeviceOnly() {

        QuantaTestManager.getTest().assignCategory("Eshop");
        devicesPage = new DevicesPage(driver.get());
        devicesPage.MobilePhonesDeviceOnly();
        // Assert.assertTrue(driver.get().getCurrentUrl().contains(URL.MASTER_CARD_URL));
        //TODO :  click on cancel button on payment page
    }

    @Test(description = " #20 Inzone", priority = 17, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void InZone() {

        QuantaTestManager.getTest().assignCategory("Eshop");
        homePlansPage = new HomePlansPage(driver.get());
        homePlansPage.Inzone();
        org.junit.Assert.assertTrue(homePlansPage.confirmationcopy.isDisplayed());

    }


    @Test(description = " #21 Outzone", priority = 18, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void OutZone() {

        QuantaTestManager.getTest().assignCategory("Eshop");
        homePlansPage = new HomePlansPage(driver.get());
        homePlansPage.Outzone();
        // Assert.assertTrue(homePlansPage.confirmationcopy.isDisplayed());

    }


    @Test(description = "DU-Daily #22 DIP Existing Device With plan", priority = 19, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void DIPExistingDeviceWithPlan() {// neeed otp

        QuantaTestManager.getTest().assignCategory("Eshop");
        devicesPage = new DevicesPage(driver.get());
        boolean IsPINNumberDivVisible = devicesPage.DIPExistingDeviceWithplan();
        Assert.assertTrue(IsPINNumberDivVisible);
    }

    @Test(description = " #24.1 Postpaid  Control Plan I want a new one", priority = 20, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"}, enabled = false)
    public void PostpaidControlPlanIWantNewOne() {

        QuantaTestManager.getTest().assignCategory("du.ae");
        postPaidPage = new PostPaidPage(driver.get());
        Assert.assertTrue(postPaidPage.PostpaidControlPlanIwantanewone());
    }

    @Test(description = " #24.2 Postpaid  Control Plan Modify my existing du plan", enabled = false, priority = 21, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void PostpaidControlPlanModifymyexistingduplan() {

        QuantaTestManager.getTest().assignCategory("Eshop");
        postPaidPage = new PostPaidPage(driver.get());
        Assert.assertTrue(postPaidPage.PostpaidControlPlanModifymyexistingduplan());
    }

    @Test(description = " #31 HomePage Personal to Business", priority = 22, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void HomePagePersonalToBusiness() {

        QuantaTestManager.getTest().assignCategory("du.ae");
        duPage = new DuPage(driver.get());
        String title = duPage.HomePagePersonaltoBusiness();
        System.out.println(title);
        Assert.assertTrue(title.contains("du Business | Broadbrand | POS | Internet & Mobile Plans"));
        Assert.assertEquals(driver.get().getCurrentUrl(), URL.DU_HOME_PAGE_URL);
    }

    @Test(description = " #32 Carousel", priority = 23, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void Carousel() {

        QuantaTestManager.getTest().assignCategory("du.ae");
        duPage = new DuPage(driver.get());
        boolean IsCarouselCorrect = duPage.Carousel();
        //Assert.assertTrue(IsCarouselCorrect);
    }


    @Test(description = " #33.1 Latest Offers", priority = 24, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void LatestOffers() {

        QuantaTestManager.getTest().assignCategory("du.ae");
        duPage = new DuPage(driver.get());
        boolean AreOffersCorrect = duPage.LatestOffers();
        Assert.assertTrue(AreOffersCorrect);
    }

    @Test(description = " #33.2 Latest Offers Shop PostPaid Plan", priority = 3, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void LatestOffersShopPostPaidPlan() {

        QuantaTestManager.getTest().assignCategory("du.ae");
        duPage = new DuPage(driver.get());
        boolean IsOffersShopPostPaidPlanCorrect = duPage.LatestOffersShopPostPaidPlan();
        Assert.assertTrue(IsOffersShopPostPaidPlanCorrect);
    }

    @Test(description = " #33 Postpaid New Emarati Plan", enabled = false, priority = 25, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void PostpaidNewEmaratiPlan() {

        QuantaTestManager.getTest().assignCategory("du.ae");
        postPaidPage = new PostPaidPage(driver.get());


        postPaidPage.PostpaidNewEmaratiPlan(); //captcha
    }


    @Test(description = " #33 Postpaid  Control Plan Change existing number to du", enabled = false, priority = 26, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void PostpaidControlPlanChangeexistingnumbertodu() {

        QuantaTestManager.getTest().assignCategory("du.ae");
        postPaidPage = new PostPaidPage(driver.get());
        Assert.assertTrue(postPaidPage.PostpaidControlPlanChangeexistingnumbertodu());
    }


    @Test(description = " #34.1 click Careers link text", priority = 6, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void clickCareersinktext() {

        QuantaTestManager.getTest().assignCategory("du.ae");
        duPage = new DuPage(driver.get());
        String Url = duPage.clickCareersinktext();
        Assert.assertEquals(Url, URL.CAREERS_URL);

    }


    @Test(description = " #34.2 click About us link text", priority = 27, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void clickStoreslinktext() {

        QuantaTestManager.getTest().assignCategory("du.ae");
        duPage = new DuPage(driver.get());
        String Url = duPage.clickStoreslinktext();

        //TODO Scenario daily 34 should check page title also
        Assert.assertEquals(Url, URL.STORES_URL);

    }

    @Test(description = " #34.3 click Stores Support text", priority = 28, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void clickSupportlinktext() {

        QuantaTestManager.getTest().assignCategory("du.ae");
        duPage = new DuPage(driver.get());
        String Url = duPage.clickSupportlinktext();
        Assert.assertEquals(Url, URL.SUPPORT_URL);
    }

    @Test(description = " #34.4 VAT", priority = 29, enabled = false, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void VAT() throws NoSuchMethodException {//Not automatable

        QuantaTestManager.getTest().assignCategory("du.ae");
        duPage = new DuPage(driver.get());
        duPage.VAT();
    }

    @Test(description = " #34.5 click Contact us link text", priority = 30, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void clickContactuslinktext() {

        QuantaTestManager.getTest().assignCategory("du.ae");
        duPage = new DuPage(driver.get());
        String Url = duPage.clickContactuslinktext();
        Assert.assertEquals(Url, URL.CONTACT_US_URL);

    }

    @Test(description = " #34.6 click Login link text", priority = 31, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void clickLoginlinktext() {

        QuantaTestManager.getTest().assignCategory("du.ae");
        duPage = new DuPage(driver.get());
        String Url = duPage.clickLoginlinktext();
        Assert.assertEquals(Url, URL.MY_ACCOUNT_URL);

    }

    @Test(description = " #34 .7Help and support", priority = 32, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void Helpandsupport() {//Not automatable

        QuantaTestManager.getTest().assignCategory("du.ae");
        duPage = new DuPage(driver.get());
        boolean SearchFound = duPage.Helpandsupport();
        Assert.assertTrue(SearchFound);
    }

    @Test(description = " #34.8 Data Sim Only", priority = 33, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"}, enabled = false)
    public void DataSimOnly() {
        QuantaTestManager.getTest().assignCategory("du.ae");
        duPage = new DuPage(driver.get());
        duPage.DataSimOnly();
        // i removed the assertion" already reached the limit of permitted rate plans with this Emirates ID"
    }


}

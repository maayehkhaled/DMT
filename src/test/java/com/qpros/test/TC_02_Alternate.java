package com.qpros.test;

import com.qpros.common.UserType;
import com.qpros.common.web.Base;
import com.qpros.pages.web.common.LoginPage;
import com.qpros.pages.web.myaccount.MyaccountPage;
import com.qpros.pages.web.shop.PostPaidPage;
import com.qpros.pages.web.shop.PrePaidPage;
import com.qpros.quanta.model.Category;
import com.qpros.quanta.model.SubCategory;
import com.qpros.reporting.QuantaTestManager;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(com.qpros.common.LogManager.class)
public class TC_02_Alternate extends Base {

    @BeforeClass
    public void initiSuite() {
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


    @Test(description = " #6 Rate plan benefits", priority = 1, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Alternate"}, enabled = false)
    public void Rateplanbenefits() throws Exception { // there is an error 21/2/21
        QuantaTestManager.getTest().assignAuthor("My Account");
        //QuantaTestManager.getTest().assignCategory("My Account");
        loginPage = new LoginPage(driver.get());
        loginPage.PerformLogin(UserType.LoginUser4);
        myAccountPage = new MyaccountPage(driver.get());
        boolean IsPassed = myAccountPage.Rateplanbenefits();
        Assert.assertTrue(IsPassed, "The plan in the login page isnt the same one in the upgrade page ");

    }


    @Test(description = " #35 Home Realocation  (inzone)", priority = 7, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Alternate"}, enabled = true)
    public void HomeRealocationinzone() throws Exception {
        //QuantaTestManager.getTest().assignCategory("My Account");

        QuantaTestManager.getTest().assignAuthor("My Account");

        loginPage = new LoginPage(driver.get());

        loginPage.PerformLogin(UserType.LoginUser3);

        myAccountPage = new MyaccountPage(driver.get());
        myAccountPage.HomeRealocationinzone();
    }

    @Test(description = " #36 Home Realocation  (outzone)", priority = 8, enabled = true, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Alternate"})
    public void HomeRealocationoutzone() throws Exception {
        QuantaTestManager.getTest().assignAuthor("My Account");


        loginPage = new LoginPage(driver.get());

        loginPage.PerformLogin(UserType.LoginUser);
        myAccountPage = new MyaccountPage(driver.get());

        myAccountPage.HomeRealocationoutzone();
    }

    @Test(description = " #37 Recharge for friend --(with 1 AED pay) settings for Prepaid#", priority = 9, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Alternate"})
    public void Rechargeforfriendwith1AEDpay() throws Exception {  // alternate
        QuantaTestManager.getTest().assignAuthor("My Account");

        //QuantaTestManager.getTest().assignCategory("My Account");

        loginPage = new LoginPage(driver.get());

        loginPage.PerformLogin(UserType.LoginUser);
        myAccountPage = new MyaccountPage(driver.get());

        myAccountPage.Rechargeforfriendwith1AEDpay();
        // make in your consideration to check if there friends or not and remove the exist one
    }

    @Test(description = " #38 Recharge for friend --(with 1 AED pay) >> Shortcuts", priority = 12, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Alternate"})
    public void RechargeforfriendShortcut() throws Exception {  // alternate
        QuantaTestManager.getTest().assignAuthor("My Account");
        //QuantaTestManager.getTest().assignCategory("My Account");
        loginPage = new LoginPage(driver.get());
        loginPage.PerformLogin(UserType.LoginUser);
        myAccountPage = new MyaccountPage(driver.get());
        myAccountPage.RechargeforfriendShortcut();

    }

    @Test(description = " #39 Pay for friend --(with 1 AED pay) settings for postpaid#", priority = 10, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Alternate"})
    public void Payforfriendwith1AEDpay() throws Exception {  // alternate
        QuantaTestManager.getTest().assignAuthor("My Account");
        loginPage = new LoginPage(driver.get());


        loginPage.PerformLogin(UserType.LoginUser);
        myAccountPage = new MyaccountPage(driver.get());

        myAccountPage.Payforfriendwith1AEDpay();
        // make in your consideration to check if there friends or not and remove the exist one
    }


    @Test(description = " #41 Forgot your password", priority = 11, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Alternate"})
    public void Forgotyourpassword() {  // alternate
        QuantaTestManager.getTest().assignAuthor("My Account");
        //QuantaTestManager.getTest().assignCategory("My Account");
        myAccountPage = new MyaccountPage(driver.get());
        myAccountPage.Forgotyourpassword();
        // make in your consideration to check if there friends or not and remove the exist one
    }


    @Test(description = " #42 Pay as you go", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Alternate"})
    public void Payasyougo() {
        QuantaTestManager.getTest().assignAuthor("du.ae");
        prePaidPage = new PrePaidPage(driver.get());
        prePaidPage.Payasyougo();
    }

    @Test(description = " #44 Home Fixed Journey (Internet & TV)-Login to upgrade", priority = 13, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Alternate"}, enabled = false)
    public void HomeFixedJourneyInternetandTVLogintoupgrade() throws Exception { // alternate
        QuantaTestManager.getTest().assignCategory(new String[]{"Alternate"}, new String[]{"du.ae"});
        //QuantaTestManager.getTest().assignCategory("My Account");
        myAccountPage = new MyaccountPage(driver.get());
        myAccountPage.HomeFixedJourneyInternetandTVLogintoupgrade();
    }


    @Test(description = " #46 Small business-12 Months/ Business Mobile Plans/Flexi or National (Postpaid Plans)", enabled = true, priority = 16, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Alternate"})
    public void smallBusinessPostpaid12Months() {
        QuantaTestManager.getTest().assignAuthor("du.ae");
        postPaidPage = new PostPaidPage(driver.get());
        postPaidPage.navigateToBusinessPage();
        postPaidPage.navigateToSmallBusiness();
        postPaidPage.navigateToSmallBusinessMobilePlan();
        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocal();
        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlan();
        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCart();
        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartChekOut();
        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartChekOutIdentifyExistingAccount();
    }

    @Test(description = " #47 Small business-24 Months/ Business Mobile Plans/Flexi or National (Postpaid Plans)", enabled = true, priority = 16, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Alternate"})
    public void smallBusinessPostpaid24Months() {
        QuantaTestManager.getTest().assignAuthor("du.ae");
        postPaidPage = new PostPaidPage(driver.get());
        postPaidPage.navigateToBusinessPage();
        postPaidPage.navigateToSmallBusiness();
        postPaidPage.navigateToSmallBusinessMobilePlan();
        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocal24();
        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlan();
        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCart();
        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartChekOut();
        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartChekOutIdentifyExistingAccount();
    }

    @Test(description = " #48.1 Small business-Devices (New Customers)/Smartphones ", enabled = true, priority = 16, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Alternate"})
    public void smallBusinessDevicesPhones() {
        QuantaTestManager.getTest().assignAuthor("du.ae");
        postPaidPage = new PostPaidPage(driver.get());
        postPaidPage.navigateToBusinessPage();
        postPaidPage.navigateToSmallBusiness();
        postPaidPage.navigateToSmallBusinessMobilePlan();
        postPaidPage.navigateToDevicesTab();
        postPaidPage.navigateToDevicesTabPhones();
        postPaidPage.navigateToDevicesTabPhonesAndChoose();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocal();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartDevices();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartChekOut();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartChekOutIdentifyExistingAccount();

    }

    @Test(description = " #48.2 Small business-Devices (New Customers)/ Watches ", enabled = true, priority = 16, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Alternate"})
    public void smallBusinessDevicesWatches() {
        QuantaTestManager.getTest().assignAuthor("du.ae");
        postPaidPage = new PostPaidPage(driver.get());
        postPaidPage.navigateToBusinessPage();
        postPaidPage.navigateToSmallBusiness();
        postPaidPage.navigateToSmallBusinessMobilePlan();
        postPaidPage.navigateToDevicesTab();
        postPaidPage.navigateToDevicesTabWatches();
//        postPaidPage.navigateToDevicesTabPhonesAndChoose();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocal();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartDevices();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartChekOut();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartChekOutIdentifyExistingAccount();

    }

    @Test(description = " #49 Small business-Devices (Pay in full )\\Smartphones or Routers ", enabled = true, priority = 16, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Alternate"})
    public void smallBusinessDevicesPayInFull() {
        QuantaTestManager.getTest().assignAuthor("du.ae");
        postPaidPage = new PostPaidPage(driver.get());
        postPaidPage.navigateToBusinessPage();
        postPaidPage.navigateToSmallBusiness();
        postPaidPage.navigateToSmallBusinessMobilePlan();
        postPaidPage.navigateToDevicesTab();
        postPaidPage.navigateToDevicesTabPhones();
        postPaidPage.navigateToDevicesTabPhonesAndChoose();
//        postPaidPage.navigateToDevicesTabPhonesAndChoose();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocal();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartDevices();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartChekOut();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartChekOutIdentifyExistingAccount();

    }

    @Test(description = " #50 Small business-Devices (new customer )\\Tablets ", enabled = true, priority = 16, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Alternate"})
    public void smallBusinessDevicesTablets() {
        QuantaTestManager.getTest().assignAuthor("du.ae");
        postPaidPage = new PostPaidPage(driver.get());
        postPaidPage.navigateToBusinessPage();
        postPaidPage.navigateToSmallBusiness();
        postPaidPage.navigateToSmallBusinessMobilePlan();
        postPaidPage.navigateToDevicesTab();
        postPaidPage.navigateToDevicesTabTablets();
        postPaidPage.navigateToDevicesTabPhonesAndChoose();
//        postPaidPage.navigateToDevicesTabPhonesAndChoose();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocal();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartDevices();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartChekOut();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartChekOutIdentifyExistingAccount();

    }

    @Test(description = " #51 Small business-Devices (Existing Customer )\\Tablets ", enabled = true, priority = 16, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Alternate"})
    public void smallBusinessDevicesTabletsExistingCustomer() {
        QuantaTestManager.getTest().assignAuthor("du.ae");
        postPaidPage = new PostPaidPage(driver.get());
        postPaidPage.navigateToBusinessPage();
        postPaidPage.navigateToSmallBusiness();
        postPaidPage.navigateToSmallBusinessMobilePlan();
        postPaidPage.navigateToDevicesTab();
        postPaidPage.navigateToDevicesTabTablets();
        postPaidPage.navigateToDevicesTabPhonesAndChoose();
//        postPaidPage.navigateToDevicesTabPhonesAndChoose();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocal();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartDevices();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartChekOut();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartChekOutIdentifyExistingAccount();

    }

    @Test(description = " #52 Large business-12 Months Business Mobile Plan ", enabled = true, priority = 16, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Alternate"})
    public void LargeBusiness12MonthsBusinessMobilePlan() {
        QuantaTestManager.getTest().assignAuthor("du.ae");
        postPaidPage = new PostPaidPage(driver.get());
        postPaidPage.navigateToBusinessPage();
        postPaidPage.navigateToLargeBusiness();
        postPaidPage.navigateToSmallBusinessMobilePlan();
        postPaidPage.navigateToDevicesTab();
        postPaidPage.navigateToDevicesTabTablets();
        postPaidPage.navigateToDevicesTabPhonesAndChoose();
//        postPaidPage.navigateToDevicesTabPhonesAndChoose();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocal();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartDevices();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartChekOut();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartChekOutIdentifyExistingAccount();

    }

    @Test(description = " #53 Large business-12 Months Business (new customer)\\ Smartphones  ", enabled = true, priority = 16, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Alternate"})
    public void LargeBusinessnewCustomerSmartPhones() {
        QuantaTestManager.getTest().assignAuthor("du.ae");
        postPaidPage = new PostPaidPage(driver.get());
        postPaidPage.navigateToBusinessPage();
        postPaidPage.navigateToLargeBusiness();
        postPaidPage.navigateToSmallBusinessMobilePlan();
        postPaidPage.navigateToDevicesTab();
        postPaidPage.navigateToDevicesTabPhones();
        postPaidPage.navigateToDevicesTabPhonesAndChoose();
//        postPaidPage.navigateToDevicesTabPhonesAndChoose();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocal();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartDevices();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartChekOut();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartChekOutIdentifyExistingAccount();

    }

    @Test(description = " #53.2 Large business-12 Months Business (new customer)\\ watches  ", enabled = true, priority = 16, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Alternate"})
    public void LargeBusinessnewCustomerWatches() {
        QuantaTestManager.getTest().assignAuthor("du.ae");
        postPaidPage = new PostPaidPage(driver.get());
        postPaidPage.navigateToBusinessPage();
        postPaidPage.navigateToLargeBusiness();
        postPaidPage.navigateToSmallBusinessMobilePlan();
        postPaidPage.navigateToDevicesTab();
        postPaidPage.navigateToDevicesTabWatches();
        postPaidPage.navigateToDevicesTabPhonesAndChoose();
//        postPaidPage.navigateToDevicesTabPhonesAndChoose();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocal();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartDevices();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartChekOut();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartChekOutIdentifyExistingAccount();

    }


    @Test(description = " #54 Large business Devices  ", enabled = true, priority = 16, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Alternate"})
    public void LargeBusinessDevices() {
        QuantaTestManager.getTest().assignAuthor("du.ae");
        postPaidPage = new PostPaidPage(driver.get());
        postPaidPage.navigateToBusinessPage();
        postPaidPage.navigateToLargeBusiness();
        postPaidPage.navigateToSmallBusinessMobilePlan();
        postPaidPage.navigateToDevicesTab();
        postPaidPage.navigateToDevicesTabPhones();
        postPaidPage.navigateToDevicesTabPhonesAndChoose();
//        postPaidPage.navigateToDevicesTabPhonesAndChoose();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocal();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartDevices();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartChekOut();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartChekOutIdentifyExistingAccount();

    }

    @Test(description = " #55 Government  business-12 Months/ Business Mobile Plan/Flexi or National  ", enabled = true, priority = 16, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Alternate"})
    public void Governmentbusiness12Months() {
        QuantaTestManager.getTest().assignAuthor("du.ae");
        postPaidPage = new PostPaidPage(driver.get());
        postPaidPage.navigateToBusinessPage();
        postPaidPage.navigateToGovernment();
        postPaidPage.navigateToSmallBusinessMobilePlan();
        postPaidPage.navigateToDevicesTab();
        postPaidPage.navigateToDevicesTabPhones();
        postPaidPage.navigateToDevicesTabPhonesAndChoose();
//        postPaidPage.navigateToDevicesTabPhonesAndChoose();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocal();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartDevices();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartChekOut();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartChekOutIdentifyExistingAccount();

    }

    @Test(description = " #56 Government  business-Devices (New Customers)\\Smartphones or Tablets or Watches  ", enabled = true, priority = 16, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Alternate"})
    public void governmentBusinessDevices() {
        QuantaTestManager.getTest().assignAuthor("du.ae");
        postPaidPage = new PostPaidPage(driver.get());
        postPaidPage.navigateToBusinessPage();
        postPaidPage.navigateToGovernment();
        postPaidPage.navigateToSmallBusinessMobilePlan();
        postPaidPage.navigateToDevicesTab();
        postPaidPage.navigateToDevicesTabPhones();
        postPaidPage.navigateToDevicesTabPhonesAndChoose();
//        postPaidPage.navigateToDevicesTabPhonesAndChoose();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocal();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartDevices();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartChekOut();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartChekOutIdentifyExistingAccount();

    }

    @Test(description = " #57 Government  business-Devices (Existing Customers) ", enabled = true, priority = 16, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Alternate"})
    public void governmentBusinessDevicesExistingCustomer() {
        QuantaTestManager.getTest().assignAuthor("du.ae");
        postPaidPage = new PostPaidPage(driver.get());
        postPaidPage.navigateToBusinessPage();
        postPaidPage.navigateToGovernment();
        postPaidPage.navigateToSmallBusinessMobilePlan();
        postPaidPage.navigateToDevicesTab();
        postPaidPage.navigateToDevicesTabPhones();
        postPaidPage.navigateToDevicesTabPhonesAndChoose();
//        postPaidPage.navigateToDevicesTabPhonesAndChoose();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocal();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartDevices();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartChekOut();
//        postPaidPage.navigateToSmallBusinessMobilePlanInternationalLocalSelectPlanAddToCartChekOutIdentifyExistingAccount();

    }
}

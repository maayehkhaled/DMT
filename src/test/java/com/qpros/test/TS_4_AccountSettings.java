package com.qpros.test;

import com.qpros.common.Base;
import com.qpros.common.DriverType;
import com.qpros.common.UserType;
import com.qpros.helpers.ReadWriteHelper;
import com.qpros.pages.homepage.ConsumerHomePage;
import com.qpros.pages.login.LoginPage;
import org.testng.annotations.*;

@Listeners(com.qpros.common.LogManger.class)

public class TS_4_AccountSettings extends Base {
    LoginPage loginPage;

    @BeforeClass
    public void setUpBrowser() {
        String OsType = OsValidator.getDeviceOs();
        DriverType browser = getBrowser();
        initiateDriver(OsType, browser);
        driver.navigate().to(ReadWriteHelper.ReadData("BaseURL"));
    }

    @Test(description = "The User Explore Account Settings Page",suiteName = "Account_Settings", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void exploreAccountSettings() throws Exception {
        loginPage = new LoginPage(driver);
        loginPage.signIn(UserType.B2C.getUserName(), UserType.B2C.getPassword() );
        com.qpros.pages.accountSettings.AccountSettings.viewAccountsettings();



    }
    @Test (description = "The User Update Account Settings",suiteName = "Account_Settings",priority = 2,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void updateAccountSettings() throws Exception {

        com.qpros.pages.accountSettings.AccountSettings.operateAccountSettings();
    }





    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }
}

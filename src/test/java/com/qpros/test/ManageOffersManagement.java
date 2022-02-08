package com.qpros.test;
import com.qpros.common.web.Base;
import com.qpros.pages.web.SSA.LoginPage;
import com.qpros.pages.web.SSA.ManageOffersManagementPage;
import com.qpros.pages.web.SSA.UserType;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.common.locators.urls;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

@Listeners(com.qpros.common.LogManager.class)
public class ManageOffersManagement extends Base {
    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());
    }

    @BeforeMethod(enabled = true)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser();
    }

    public void startMethod(){
        driver.get().navigate().to(urls.agentLogin);
        loginPage.loginWithUser(UserType.CM2);
    }
    LoginPage loginPage = new LoginPage(driver.get());
    ManageOffersManagementPage managePage = new ManageOffersManagementPage(driver.get());

    @Test(description = "Specific EID", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void validateClickOnEID() {
        startMethod();
        managePage.clickOnEID();
    }

    @Test(description = "Change opportunity Status", priority = 2,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void validateChangeStatus() {
        startMethod();
        managePage.clickOnEID();
        managePage.changeStatus();
    }

    @Test(description = "add Partner Comment", priority = 3,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void validateAddPartnerComment() {
        startMethod();
        managePage.clickOnEID();
        managePage.addPartnerComment();
    }

    @Test(description = "validate View All Info", priority = 4,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void validateViewAllInfo() {
        startMethod();
        managePage.clickOnEID();
        managePage.viewAllInfo();
    }
}

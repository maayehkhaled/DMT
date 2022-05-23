package com.qpros.test;

import com.qpros.common.web.Base;
import com.qpros.pages.web.SSA.LoginPage;
import com.qpros.pages.web.SSA.ManageOffersHouseholdPage;
import com.qpros.pages.web.SSA.UserType;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.common.locators.urls;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

@Listeners(com.qpros.common.LogManager.class)
public class ManageOffersHousehold extends Base {
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
        managePage.searchForEID();
    }

    LoginPage loginPage = new LoginPage(driver.get());
    ManageOffersHouseholdPage managePage = new ManageOffersHouseholdPage(driver.get());

    @Test(description = "First Time Edit", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void validateFirstTimeEdit(){
        startMethod();
        managePage.catchFromSpan();
       // managePage.firstTimeEdit();
    }
    @Test(description = "Second Time Edit", priority = 2,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void validateSecondTimeEdit() {
        startMethod();
        managePage.secondTimeEdit();
    }

    @Test(description = "Edit Status To Reject", priority = 3,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void validateEditStatusOnly(){
        startMethod();
        managePage.editStatusOnly();
    }
}

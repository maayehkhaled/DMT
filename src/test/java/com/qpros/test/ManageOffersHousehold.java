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

    LoginPage loginPage = new LoginPage(driver.get());
    ManageOffersHouseholdPage managePage = new ManageOffersHouseholdPage(driver.get());

    @Test(description = "Household Portfolio ", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void AddActivationOpportunity() {
        driver.get().navigate().to(urls.agentLogin);
        loginPage.loginWithUser(UserType.CM2);
        managePage.searchForEID();
        managePage.openEmpowermentPlan();
        managePage.editPartnerComment();
    }
}

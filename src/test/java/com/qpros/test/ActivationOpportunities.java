package com.qpros.test;

import com.qpros.common.web.Base;
import com.qpros.pages.web.SSA.ActivationOpportunitiesPage;
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
public class ActivationOpportunities extends Base {
    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());
    }

    @BeforeMethod(enabled = true)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser();
    }
    LoginPage loginPage = new LoginPage(driver.get());
    ActivationOpportunitiesPage activation=new ActivationOpportunitiesPage(driver.get());
    ManageOffersHouseholdPage managePage = new ManageOffersHouseholdPage(driver.get());
    public void startMethod(){
        driver.get().navigate().to(urls.agentLogin);
        loginPage.loginWithUser(UserType.CM2);
        activation.openCreatedOpportunity();
    }

    @Test(description = "Add Activation Opportunities and recommend it to EID", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void AddActivationOpportunity() {
        driver.get().navigate().to(urls.activationOpportunities);
        loginPage.loginWithUser(UserType.ProgramManager);
        activation.clickAddOpportunity();
        activation.createdRequest();
        activation.openCreatedOpportunity();
        activation.openSecondCreatedOpportunity();
    }

    @Test(description = "First Time Edit", priority = 2,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void validateFirstTimeEdit() {
        startMethod();
        //activation.firstTimeEdit();
    }

    @Test(description = "Second Time Edit", priority = 3,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void validateSecondTimeEdit() {
        startMethod();
        //activation.secondTimeEdit();
    }

    @Test(description = "Edit Status To Reject", priority = 4,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void validateEditStatusOnly(){
        startMethod();
        //activation.editStatusOnly();
    }
}

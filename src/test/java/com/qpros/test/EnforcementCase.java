package com.qpros.test;

import com.qpros.common.web.Base;
import com.qpros.pages.web.SSA.*;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.common.locators.urls;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
@Listeners(com.qpros.common.LogManager.class)
public class EnforcementCase extends Base {
    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());
    }

    @BeforeMethod(enabled = true)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser();
    }

    LoginPage loginPage = new LoginPage(driver.get());
    EnforcementCasePage enforcementPage = new EnforcementCasePage(driver.get());

    public void startPage(){
        driver.get().navigate().to(urls.agentLogin);
        loginPage.loginWithUser(UserType.CaseManagerHead);
        enforcementPage.openEnforcementCase();
    }

    @Test(description = "Add new Enforcement case source", priority = 2,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void addEnforcementCase() {
        startPage();
        enforcementPage.createEnforcementCase();
    }

    @Test(description = "Edit Enforcement case ", priority = 3,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void editEnforcementCase() {
        startPage();
        enforcementPage.searchByEID();
        enforcementPage.editEnforcementCase();
    }

    @Test(description = "Add Action", priority =4,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void addAction() {
        startPage();
        enforcementPage.searchByEID();
        enforcementPage.addAction();
    }

    @Test(description = "Edit Action", priority = 5,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void editAction() {
        startPage();
        enforcementPage.searchByEID();
        enforcementPage.editAction();
    }

    @Test(description = "Delete Action", priority = 1, dependsOnMethods = {"addAction"} , alwaysRun=true,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void deleteAction() {
        startPage();
        enforcementPage.searchByEID();
        enforcementPage.deleteAction();
        //Assert.assertEquals(enforcementPage.checkDeletedTableSize(),3);
    }

    @Test(description = "Add Logs", priority = 6,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void addLogs() {
        startPage();
        enforcementPage.searchByEID();
        enforcementPage.addLogs();
    }

    @Test(description = "Delete Logs", priority = 7,dependsOnMethods = {"addLogs"}, alwaysRun=true,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void deleteLogs() {
        startPage();
        enforcementPage.searchByEID();
        enforcementPage.deleteLogs();
    }
}
package com.qpros.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.pages.web.SSA.AcocTriggerPage;
import com.qpros.pages.web.SSA.LoginPage;
import com.qpros.pages.web.SSA.UserType;
import com.qpros.pages.web.SSA.modules.ApproveApplicationModule;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.common.locators.urls;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

@Listeners(com.qpros.common.LogManager.class)

public class AcocTrigger extends Base {

    ApproveApplicationModule approveApplicationModule = new ApproveApplicationModule(driver.get());
    LoginPage loginPage = new LoginPage(driver.get());
    AcocTriggerPage acocTriggerPage=new AcocTriggerPage(driver.get());
    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());
    }
    @BeforeMethod(enabled = true)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser();
    }
    @Test(
            description = " Add communication", retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void addCommunication() throws JsonProcessingException, InterruptedException, AWTException {
        approveApplicationModule.approveApplication(false);
        ActionsHelper.navigate(urls.agentLogin);
        loginPage.loginWithUser(UserType.CM2);
        acocTriggerPage.addCommunication();
    }
    @Test(description = "add Comment", retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void addComment() throws JsonProcessingException, InterruptedException, AWTException {
        approveApplicationModule.approveApplication(false);
        ActionsHelper.navigate(urls.agentLogin);
        loginPage.loginWithUser(UserType.CM2);
        acocTriggerPage.addCommunication();
        acocTriggerPage.addComment();
    }
    @Test(description = "add Change Circumstances", retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void addChangeCircumstances() throws JsonProcessingException, InterruptedException, AWTException {
        approveApplicationModule.approveApplication(false);
        ActionsHelper.navigate(urls.agentLogin);
        loginPage.loginWithUser(UserType.CM2);
        acocTriggerPage.addCommunication();
        acocTriggerPage.addComment();
        acocTriggerPage.addChangeCircumstances();
    }
}

package com.qpros.test;

import com.qpros.common.LogManager;
import com.qpros.common.web.Base;
import com.qpros.pages.web.SSA.HomePage;
import com.qpros.pages.web.SSA.LoginPage;
import com.qpros.pages.web.SSA.UserType;
import com.qpros.pages.web.SSA.modules.ApproveApplicationModule;
import com.qpros.pages.web.SSA.modules.RejectApplicationModule;
import com.qpros.reporting.QuantaTestManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

@Listeners(LogManager.class)
public class ReassessmentApprove extends Base {
    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());
    }

    @BeforeMethod(enabled = true)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser();
    }
    RejectApplicationModule rejectApplicationModule = new RejectApplicationModule(driver.get());
    ApproveApplicationModule approveApplication = new ApproveApplicationModule(driver.get());
    HomePage homePage = new HomePage(driver.get());
    LoginPage loginPage = new LoginPage(driver.get());
/*
    @Test(description = "reApplication-Approve", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void reassessmentApprove() throws Exception {
        rejectApplicationModule.RejectApplication();
        approveApplication.approveApplication(false);
    }


 */

    @Test(description = "Re Assessment Approve", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void reassessmentApprove() throws Exception {
        approveApplication.approveApplication(false);
        homePage.navigateToLogin();
        loginPage.loginWithUser(UserType.Committee1);


    }




}



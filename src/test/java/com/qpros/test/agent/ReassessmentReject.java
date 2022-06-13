package com.qpros.test.agent;

import com.qpros.common.LogManager;
import com.qpros.common.web.Base;
import com.qpros.pages.web.SSA.modules.ApproveApplicationModule;
import com.qpros.pages.web.SSA.modules.RejectApplicationModule;
import com.qpros.reporting.QuantaTestManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

@Listeners(LogManager.class)
public class ReassessmentReject extends Base {

    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());

    }

    @BeforeMethod(enabled = true)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser(true);
    }
    RejectApplicationModule rejectApplicationModule = new RejectApplicationModule(driver.get());
    ApproveApplicationModule approveApplication = new ApproveApplicationModule(driver.get());


    @Test(description = "Approve an application", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void approveApplication() throws Exception {
        approveApplication.approveApplication(false);
        rejectApplicationModule.RejectApplication();
    }
}
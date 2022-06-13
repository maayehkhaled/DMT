package com.qpros.test.agent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qpros.common.LogManager;
import com.qpros.common.web.Base;
import com.qpros.pages.web.SSA.*;
import com.qpros.pages.web.SSA.modules.ApproveApplicationModule;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.service.SubmitApplicationService;
import com.ssa.core.service.VerifyEligibilityService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

@Listeners(LogManager.class)
public class ReassessmentIncreaseApprove extends Base {

    @BeforeClass
    public void initiSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());

    }

    @BeforeMethod(enabled = true)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser(true);
    }

    ApproveApplicationModule approveApplication = new ApproveApplicationModule(driver.get());
    ReassessmentPage reassessmentPage = new ReassessmentPage(driver.get());
    @Test(description = "Approve an application", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void approveApplication() throws JsonProcessingException, AWTException, InterruptedException {
       approveApplication.approveApplication(true);
        reassessmentPage.reassessToAmount();
        reassessmentPage.increaseAmount();
        reassessmentPage.reassessAfterAmount();
    }
}
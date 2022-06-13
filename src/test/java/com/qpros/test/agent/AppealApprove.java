package com.qpros.test.agent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.pages.web.SSA.*;
import com.qpros.pages.web.SSA.commonSSA.Steps;
import com.qpros.pages.web.SSA.modules.ApproveApplicationModule;
import com.qpros.pages.web.SSA.modules.RejectApplicationModule;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.common.data.TestData;
import com.ssa.core.common.locators.urls;
import com.ssa.core.service.DeleteEmirateId;
import com.ssa.core.service.SubmitApplicationService;
import com.ssa.core.service.VerifyEligibilityService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import javax.jws.soap.SOAPBinding;
import java.awt.*;
import java.io.IOException;
import java.util.List;

@Listeners(com.qpros.common.LogManager.class)
public class AppealApprove extends Base {

    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());

    }

    @BeforeMethod(enabled = true)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser(true);
    }

    ApproveApplicationModule approveApplicationModule = new ApproveApplicationModule(driver.get());
    ClaimantLogin claimantLogin = new ClaimantLogin(driver.get());
    AppealPage appealPage = new AppealPage(driver.get());



    @Test(description = "AppealApprove")
    public void AppealApprove() throws JsonProcessingException, InterruptedException, AWTException {
        //1st assessment - Approve
        QuantaTestManager.getTest().assignCategory("Agent");

        approveApplicationModule.approveApplication(false);
        // approveApplicationModule.approveExistingApplication(ApproveApplicationModule.refCode);
        logManager.STEP("2. Login to beneficiary side with the EID", "the Beneficiary User conduct login using EID" + TestData.EID);
        claimantLogin.claimantLogin(TestData.EID);
        appealPage.appealApprove();

        //auditorsManagementPage.selectSpecialist(UserType.Specialist2.getUserName(), referenceNumber);
        //agentPage.logOut();
    }






}

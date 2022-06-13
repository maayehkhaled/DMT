package com.qpros.test.rmi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.pages.web.SSA.*;
import com.qpros.pages.web.SSA.modules.ApproveApplicationModule;
import com.qpros.pages.web.SSA.modules.RejectApplicationModule;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.common.data.TestData;
import com.ssa.core.service.SubmitApplicationService;
import com.ssa.core.service.VerifyEligibilityService;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

@Listeners(com.qpros.common.LogManager.class)
public class RMIReassessment extends Base {

    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());
    }

    @BeforeMethod(enabled = true)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser(true);
    }

    ApproveApplicationModule approveApplicationModule = new ApproveApplicationModule(driver.get());
    ClaimantApplicationPage claimantApplicationPage = new ClaimantApplicationPage(driver.get());
    RejectApplicationModule rejectApplicationModule = new RejectApplicationModule(driver.get());
    ClaimantLogin claimantLogin = new ClaimantLogin(driver.get());
    COCPage cocPage = new COCPage(driver.get());
    SubmitApplicationService submitApplicationService = new SubmitApplicationService();
    VerifyEligibilityService verifyEligibilityService = new VerifyEligibilityService();
    HomePage homePage = new HomePage(driver.get());
    LoginPage loginPage = new LoginPage(driver.get());
    AgentPage agentPage = new AgentPage(driver.get());
    AuditorsManagementPage auditorsManagementPage = new AuditorsManagementPage(driver.get());
    BusinessParametersPage businessParametersPage = new BusinessParametersPage(driver.get());
    PaymentSpecialistPage paymentSpecialistPage = new PaymentSpecialistPage(driver.get());
    RMIPage rmiPage = new RMIPage(driver.get());

    @Test(description = "RMI- Reassesmsnet ")
    public void RMIReassesmsnet() throws JsonProcessingException, InterruptedException, AWTException {
        //1st assessment - Approve
        approveApplicationModule.approveApplication(false);
        // approveApplicationModule.approveExistingApplication(ApproveApplicationModule.refCode);
        rmiPage.rmiReassessment();



    }

}

package com.qpros.test;

import com.qpros.common.web.Base;
import com.qpros.pages.web.SSA.*;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.service.SubmitApplicationService;
import com.ssa.core.service.VerifyEligibilityService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

@Listeners(com.qpros.common.LogManager.class)
public class RejectApplication extends Base {
    @BeforeClass
    public void initiSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());

    }

    @BeforeMethod(enabled = true)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser();
    }
    SubmitApplicationService submitApplicationService = new SubmitApplicationService();
    VerifyEligibilityService verifyEligibilityService = new VerifyEligibilityService();
    HomePage homePage = new HomePage(driver.get());
    LoginPage loginPage = new LoginPage(driver.get());
    AgentPage agentPage = new AgentPage(driver.get());
    AuditorsManagementPage auditorsManagementPage = new AuditorsManagementPage(driver.get());
    BusinessParametersPage businessParametersPage = new BusinessParametersPage(driver.get());
    PaymentSpecialistPage paymentSpecialistPage = new PaymentSpecialistPage(driver.get());

    @Test(description = "Approve an application", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void RejectApplication() throws Exception {
    //URL: https://10.231.1.100/DCDAgentPortalTheme/Login.aspx
        driver.get().navigate().to("https://10.231.1.100/DCDAgentPortalTheme/Login.aspx");

        this.logManager.STEP("VE from 12x12 API", "The System Verify the User Eligibility by calling 12X12 API");
        this.logManager.INFO("Verify Eligibility Service Call", false);
        verifyEligibilityService.requestService();
        if(verifyEligibilityService.getresponse(verifyEligibilityService).application.isEligible) {
          QuantaTestManager.getTest().assignCategory("1st Assessment");
            submitApplicationService.requestService();
            String refCode = submitApplicationService.getresponse(submitApplicationService).applicationSummary.referenceNumber;
            if(!(submitApplicationService.getresponse(submitApplicationService).applicationSummary.supportAmount >= 1)) return;
            //String refCode = "SSP-10519";
            homePage.navigateToLogin();

            loginPage.loginWithUser(UserType.Superuser);
            auditorsManagementPage.selectSpecialist(UserType.Specialist2.getUserName(), refCode);
            agentPage.logOut();

            loginPage.loginWithUser(UserType.Specialist2);
            String seniorSpecialist = agentPage.specialistRejectApplication(refCode);
            System.out.println(seniorSpecialist);
            seniorSpecialist = seniorSpecialist.replace("Supervisor", "").replace("\n", "");

            System.out.println(seniorSpecialist);
            agentPage.logOut();

            loginPage.loginWithUser(UserType.valueOf(seniorSpecialist));
            String committeeName = agentPage.seniorSpecialistRejectApplication(refCode);
            System.out.println(committeeName);
            agentPage.logOut();

            homePage.navigateToLogin();

            //String refCode = "SSP-10679";
            loginPage.loginWithUser(UserType.Superuser);
            driver.get().navigate().to("https://10.231.1.100/DCDBusinessParameters/BusinessParameters.aspx");
            businessParametersPage.releaseAppliaction(refCode);
            agentPage.logOut();
            loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
            Assert.assertFalse(paymentSpecialistPage.checkPaymentExistence(refCode));
        }
    }
}

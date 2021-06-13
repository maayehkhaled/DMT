package com.qpros.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qpros.common.LogManager;
import com.qpros.common.web.Base;
import com.qpros.pages.web.SSA.*;
import com.qpros.quanta.Status;
import com.qpros.quanta.markuputils.MarkupHelper;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.service.SubmitApplicationService;
import com.ssa.core.service.VerifyEligibilityService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

@Listeners(LogManager.class)
public class ReapplicationApprove extends Base {
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

    @Test(description = "reApplication-Approve", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void rejectApproveApplication() throws JsonProcessingException, AWTException {
        driver.get().navigate().to("https://10.231.1.100/DCDAgentPortalTheme/Login.aspx");

        this.logManager.STEP("VE from 12x12 API", "The System Verify the User Eligibility by calling 12X12 API");
        this.logManager.INFO("Verify Eligibility Service Call", false);
        verifyEligibilityService.requestService();
        QuantaTestManager.getTest().log(Status.INFO, MarkupHelper.createCodeBlock(verifyEligibilityService.response.getBody()));
        if (verifyEligibilityService.getresponse(verifyEligibilityService).application.isEligible) {
            QuantaTestManager.getTest().assignCategory("1st Assessment");
            this.logManager.STEP("Submit Application from 12x12 API", "The System Submit Application by calling 12X12 API");
            this.logManager.INFO("Submit Application Service Call", false);
            submitApplicationService.requestService();
            QuantaTestManager.getTest().log(Status.INFO, MarkupHelper.createCodeBlock(submitApplicationService.response.getBody()));

            String refCode = submitApplicationService.getresponse(submitApplicationService).applicationSummary.referenceNumber;

            homePage.navigateToLogin();

            loginPage.loginWithUser(UserType.Superuser);
            this.logManager.STEP("VE from 12x12 API", "The System Verify the User Eligibility by calling 12X12 API");
            this.logManager.STEP(" Login by super user, and assign the application to specialist from ادارة المراجعين ", "");


            auditorsManagementPage.selectSpecialist(UserType.Specialist2.getUserName(), refCode);
            agentPage.logOut();


            loginPage.loginWithUser(UserType.Specialist2);
            String seniorSpecialist = agentPage.specialistRejectApplication(refCode);
            System.out.println(seniorSpecialist);
            seniorSpecialist = seniorSpecialist.replace("Supervisor", "").replace("\n", "");


            loginPage.loginWithUser(UserType.valueOf(seniorSpecialist));

            agentPage.logOut();


            //URL: https://10.231.1.100/DCDAgentPortalTheme/Login.aspx
            driver.get().navigate().to("https://10.231.1.100/DCDAgentPortalTheme/Login.aspx");

            this.logManager.STEP("VE from 12x12 API", "The System Verify the User Eligibility by calling 12X12 API");
            this.logManager.INFO("Verify Eligibility Service Call", false);
            verifyEligibilityService.requestService();
            QuantaTestManager.getTest().log(Status.INFO, MarkupHelper.createCodeBlock(verifyEligibilityService.response.getBody()));
            if (verifyEligibilityService.getresponse(verifyEligibilityService).application.isEligible) {
                QuantaTestManager.getTest().assignCategory("1st Assessment");
                this.logManager.STEP("Submit Application from 12x12 API", "The System Submit Application by calling 12X12 API");
                this.logManager.INFO("Submit Application Service Call", false);
                submitApplicationService.requestService();
                QuantaTestManager.getTest().log(Status.INFO, MarkupHelper.createCodeBlock(submitApplicationService.response.getBody()));

                String refsCode = submitApplicationService.getresponse(submitApplicationService).applicationSummary.referenceNumber;
                //String refCode = "SSP-10676";
                homePage.navigateToLogin();

                loginPage.loginWithUser(UserType.Superuser);
                this.logManager.STEP("VE from 12x12 API", "The System Verify the User Eligibility by calling 12X12 API");
                this.logManager.STEP(" Login by super user, and assign the application to specialist from ادارة المراجعين ", "");

                auditorsManagementPage.selectSpecialist(UserType.Specialist2.getUserName(), refsCode);
                agentPage.logOut();
                loginPage.loginWithUser(UserType.Specialist2);
                String seniorsSpecialist = agentPage.specialistApproval(refsCode);
                System.out.println(seniorsSpecialist);
                seniorsSpecialist = seniorSpecialist.replace("Supervisor", "").replace("\n", "");
                agentPage.logOut();
                //String seniorSpecialist = UserType.SeniorSpecialist100.getUserName();
                loginPage.loginWithUser(UserType.valueOf(seniorsSpecialist));
                // loginPage.loginWithUser(UserType.SeniorSpecialist100);
                String committeeName = agentPage.seniorSpecialistApproval(refsCode);
                System.out.println("Committee: " + committeeName);
                driver.get().navigate().to("https://10.231.1.100/DCDAgentFrontEnd/TasksList.aspx");
                //committeeName = committeeName.replace("Committee", "").replace("\n", "");
                agentPage.logOut();
                if (committeeName.contains("ApplicationDirector1")) {
                    committeeName = committeeName.replace("Manager", "").replace("\n", "");
                    loginPage.loginWithUser(UserType.ApplicationDirector1);
                    agentPage.seniorSpecialistApproval(refsCode);
                    driver.get().navigate().to("https://10.231.1.100/DCDAgentFrontEnd/TasksList.aspx");
                    agentPage.logOut();
                } else {
                    loginPage.loginWithUser(UserType.valueOf(committeeName));
                    agentPage.committeeSpecialistApproval(refsCode);
                    //driver.get().navigate().to("https://10.231.1.100/DCDAgentFrontEnd/TasksList.aspx");
                    agentPage.logOut();
                }
                driver.get().navigate().to("https://10.231.1.100/DCDAgentPortalTheme/Login.aspx");
                //String refCode = "SSP-10679";
                loginPage.loginWithUser(UserType.Superuser);
                driver.get().navigate().to("https://10.231.1.100/DCDBusinessParameters/BusinessParameters.aspx");
                businessParametersPage.releaseAppliaction(refsCode);
                agentPage.logOut();
                loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
                Assert.assertTrue(paymentSpecialistPage.checkPaymentExistence(refsCode));
            }
        }
    }
}



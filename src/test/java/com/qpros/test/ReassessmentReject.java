package com.qpros.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qpros.common.LogManager;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.pages.web.SSA.*;
import com.qpros.quanta.Status;
import com.qpros.quanta.markuputils.MarkupHelper;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.service.SubmitApplicationService;
import com.ssa.core.service.VerifyEligibilityService;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

@Listeners(LogManager.class)
public class ReassessmentReject extends Base {

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
    public void approveApplication() throws JsonProcessingException, AWTException {
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

            String refCode = submitApplicationService.getresponse(submitApplicationService).applicationSummary.referenceNumber;
            //String refCode = "SSP-10676";
            homePage.navigateToLogin();

            loginPage.loginWithUser(UserType.Superuser);
            this.logManager.STEP("VE from 12x12 API", "The System Verify the User Eligibility by calling 12X12 API");
            this.logManager.STEP(" Login by super user, and assign the application to specialist from ادارة المراجعين ", "");


            auditorsManagementPage.selectSpecialist(UserType.Specialist2.getUserName(), refCode);
            agentPage.logOut();

            loginPage.loginWithUser(UserType.Specialist2);
            String seniorSpecialist = agentPage.specialistApproval(refCode);
            if(seniorSpecialist.contains("--")){
                agentPage.getAssigneeNameFromAllApplications(refCode);
            }

            System.out.println(seniorSpecialist);
            seniorSpecialist = seniorSpecialist.replace("Supervisor", "").replace("\n", "");

            agentPage.logOut();
            //String seniorSpecialist = UserType.SeniorSpecialist100.getUserName();

            loginPage.loginWithUser(UserType.valueOf(seniorSpecialist));
            // loginPage.loginWithUser(UserType.SeniorSpecialist100);
            String committeeName = agentPage.seniorSpecialistApproval(refCode);
            System.out.println("Committee: " + committeeName);
            driver.get().navigate().to("https://10.231.1.100/DCDAgentFrontEnd/TasksList.aspx");
            //committeeName = committeeName.replace("Committee", "").replace("\n", "");
            agentPage.logOut();
            if (committeeName.contains("ApplicationDirector")) {
                committeeName = committeeName.replace("Manager", "").replace("\n", "");
                loginPage.loginWithUser(UserType.valueOf(committeeName));
                agentPage.seniorSpecialistApproval(refCode);
                driver.get().navigate().to("https://10.231.1.100/DCDAgentFrontEnd/TasksList.aspx");
                agentPage.logOut();
            } else  {
                committeeName = committeeName.replace("Committee", "").replace("\n", "");
                loginPage.loginWithUser(UserType.valueOf(committeeName));
                agentPage.committeeSpecialistApproval(refCode);
                //driver.get().navigate().to("https://10.231.1.100/DCDAgentFrontEnd/TasksList.aspx");
                agentPage.logOut();
            }

            driver.get().navigate().to("https://10.231.1.100/DCDAgentPortalTheme/Login.aspx");
            //String refCode = "SSP-10679";
            loginPage.loginWithUser(UserType.Superuser);
            driver.get().navigate().to("https://10.231.1.100/DCDBusinessParameters/BusinessParameters.aspx");
            businessParametersPage.releaseAppliaction(refCode);
            agentPage.logOut();
            loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
            Assert.assertTrue(paymentSpecialistPage.checkPaymentExistence(refCode));



        loginPage.loginWithUser(UserType.Committee1);
        ActionsHelper.navigate("https://10.231.1.100/DCDBusinessParameters/ReassessApplications.aspx");
        ActionsHelper.isElementPresent(By.id("DCDAgentPortalTheme_wt10_block_wtFilterContainer_wt92"));
        ActionsHelper.sendKeys(By.id("DCDAgentPortalTheme_wt10_block_wtFilterContainer_wt92"),refCode + Keys.ENTER);
        }
    }
}
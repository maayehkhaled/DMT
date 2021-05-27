package com.qpros.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qpros.common.web.Base;
import com.qpros.pages.web.SSA.*;
import com.qpros.reporting.QuantaTestManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

@Listeners(com.qpros.common.LogManager.class)
public class ApproveApplication extends Base {
    @BeforeClass
    public void initiSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());

    }

    @BeforeMethod(enabled = true)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser();
    }
    SubmitApplicationService submitApplicationService= new SubmitApplicationService();
    VerifyEligibilityService verifyEligibilityService= new VerifyEligibilityService();
    HomePage homePage=new HomePage(driver.get());
    LoginPage loginPage= new LoginPage(driver.get());
    AgentPage agentPage= new AgentPage(driver.get());
    AuditorsManagementPage auditorsManagementPage= new AuditorsManagementPage(driver.get());

    @Test(description = "Approve an application", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void approveApplication() throws JsonProcessingException {
    //URL: https://10.231.1.100/DCDAgentPortalTheme/Login.aspx
        driver.get().navigate().to("https://10.231.1.100/DCDAgentPortalTheme");
        String refCode = ""; //TODO: Get this from the response
        homePage.navigateToLogin();
        loginPage.loginWithUser(UserType.Superuser);
        auditorsManagementPage.selectSpecialist("Specalist2",refCode);
        agentPage.logOut();

        loginPage.loginWithUser(UserType.Specialist2);
        String seniorSpecialist = agentPage.specialistApproval(refCode);
        agentPage.logOut();

        loginPage.loginWithUser(seniorSpecialist, "123456"); //TODO: khaled to implement password reading. Also must parse the seniorSpecalist string
        String committeeName = agentPage.seniorSpecialistApproval(refCode);
        agentPage.logOut();

        loginPage.loginWithUser(committeeName, "123456"); //TODO: read password from CSV file. Parse committeeName
        agentPage.committeeSpecialistApproval(refCode);
        agentPage.logOut();

        homePage.navigateToLogin();
        loginPage.loginWithUser(UserType.Superuser);

    }
}

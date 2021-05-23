package com.qpros.test;

import com.qpros.common.web.Base;
import com.qpros.pages.web.SSA.AgentPage;
import com.qpros.pages.web.SSA.AuditorsManagementPage;
import com.qpros.pages.web.SSA.HomePage;
import com.qpros.pages.web.SSA.LoginPage;
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

    @BeforeMethod(enabled = false)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser();
    }
    HomePage homePage;
    LoginPage loginPage;
    AgentPage agentPage;
    AuditorsManagementPage auditorsManagementPage;

    @Test(description = "Approve an application", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void approveApplication() throws Exception {
    //URL: https://10.231.1.100/DCDAgentPortalTheme/Login.aspx
        driver.get().navigate().to("https://10.231.1.100/DCDAgentPortalTheme");
        String refCode = ""; //TODO: Get this from the response
        homePage.navigateToLogin();
        loginPage.loginWithUser("superuser", "123456");
        auditorsManagementPage.selectSpecialist("Specalist2",refCode);
        agentPage.logOut();

        loginPage.loginWithUser("Specalist2", "Specialist2");
        String seniorSpecialist = agentPage.specialistApproval(refCode);
        agentPage.logOut();

        loginPage.loginWithUser(seniorSpecialist, "123456"); //TODO: khaled to implement password reading. Also must parse the seniorSpecalist string
        String committeeName = agentPage.seniorSpecialistApproval(refCode);
        agentPage.logOut();

        loginPage.loginWithUser(committeeName, "123456"); //TODO: read password from CSV file. Parse committeeName
        agentPage.committeeSpecialistApproval(refCode);
        agentPage.logOut();

        homePage.navigateToLogin();
        loginPage.loginWithUser("superuser", "123456");

    }
}

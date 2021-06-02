package com.qpros.test;


import com.qpros.common.LogManager;
import com.qpros.common.web.Base;
import com.qpros.pages.web.SSA.AgentPage;
import com.qpros.pages.web.SSA.AuditorsManagementPage;
import com.qpros.pages.web.SSA.HomePage;
import com.qpros.pages.web.SSA.LoginPage;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.service.SubmitApplicationService;
import com.ssa.core.service.VerifyEligibilityService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.io.IOException;

@Listeners(LogManager.class)
public class RMIFirstAssessment extends Base {
    @BeforeClass
    public void initiSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());

    }

    @BeforeMethod(enabled = true)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser();
    }
    SubmitApplicationService submitApplicationService;
    VerifyEligibilityService verifyEligibilityService;
    HomePage homePage=new HomePage(driver.get());
    LoginPage loginPage= new LoginPage(driver.get());
    AgentPage agentPage= new AgentPage(driver.get());
    AuditorsManagementPage auditorsManagementPage= new AuditorsManagementPage(driver.get());

}

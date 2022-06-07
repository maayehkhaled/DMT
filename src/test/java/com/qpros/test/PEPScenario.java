package com.qpros.test;

import com.qpros.common.web.Base;
import com.qpros.pages.web.SSA.LoginPage;
import com.qpros.pages.web.SSA.PEPPage;
import com.qpros.pages.web.SSA.UserType;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.common.locators.urls;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
@Listeners(com.qpros.common.LogManager.class)
public class PEPScenario extends Base {
    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());
    }

    @BeforeMethod(enabled = true)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser();
    }
    LoginPage loginPage = new LoginPage(driver.get());
    PEPPage PEP = new PEPPage(driver.get());

    public void startMethod(){
        driver.get().navigate().to(urls.agentLogin);
        loginPage.loginWithUser(UserType.CM2);
    }

    @Test(description = "Open First Member Data", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void openFirstMemberData() {
        startMethod();
        PEP.openJobInfo();
        PEP.clickOnFirstEID();
        PEP.clickEditJobInfo();
        PEP.editJobStatus();
        PEP.editQualificationInfo();
        PEP.addNewExperience();
        PEP.experienceJobTable();
        PEP.editMoreInfo();
        PEP.openCreatedRequest();
    }
}

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

    @Test(description = "Edit First Member Data", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void editFirstMemberData() {
        driver.get().navigate().to(urls.agentLogin);
        loginPage.loginWithUser(UserType.CM2);
        PEP.openJobInfo();
        PEP.clickOnFirstEID();
        PEP.editJobInfo();
        PEP.editJobStatus();
        PEP.editQualificationInfo();
        PEP.editExperienceJobInfo();
        PEP.addFirstWorkExperience();
        PEP.addSecondWorkExperience();
        PEP.editMoreInfo();
    }

    @Test(description = "Edit Second Member Data", priority = 2,
         retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void editSecondMemberData() {
        driver.get().navigate().to(urls.agentLogin);
        loginPage.loginWithUser(UserType.CM2);
        PEP.openJobInfo();
        PEP.clickOnSecondEID();
        PEP.editJobInfo();
        PEP.editJobStatus();
        PEP.editQualificationInfo();
        PEP.editExperienceJobInfo();
        PEP.addFirstWorkExperience();
        PEP.addSecondWorkExperience();
        PEP.editMoreInfo();
    }
}

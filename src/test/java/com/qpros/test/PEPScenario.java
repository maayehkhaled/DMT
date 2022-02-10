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

    @Test(description = "Edit First Member Data", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void openFirstMemberData() {
        startMethod();
        PEP.openJobInfo();
        PEP.clickOnFirstEID();
        PEP.clickEditJobInfo();
    }
    @Test(description = "edit First Member Date", priority = 2, dependsOnMethods = {"openFirstMemberData"},
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void editFirstMemberDate(){
        PEP.editJobStatus();
        PEP.editQualificationInfo();
        PEP.editExperienceJobInfo();
        PEP.addFirstWorkExperience();
        PEP.addSecondWorkExperience();
        PEP.editMoreInfo();
    }

    @Test(description = "Edit Second Member Data", priority = 3,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void openSecondMemberData() {
        startMethod();
        PEP.openJobInfo();
        PEP.clickOnSecondEID();
        PEP.clickEditJobInfo();
    }
    @Test(description = "Edit Second Member Data", priority = 4,dependsOnMethods = {"openSecondMemberData"},
         retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void editSecondMemberData() {
       //validateOpenJobInfo
        PEP.editJobStatus();
        PEP.editQualificationInfo();
        PEP.editExperienceJobInfo();
        PEP.addFirstWorkExperience();
        PEP.addSecondWorkExperience();
        PEP.editMoreInfo();
    }
}

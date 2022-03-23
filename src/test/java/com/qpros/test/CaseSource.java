package com.qpros.test;

import com.qpros.common.web.Base;
import com.qpros.pages.web.SSA.*;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.common.locators.urls;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import java.io.IOException;

@Listeners(com.qpros.common.LogManager.class)
public class CaseSource extends Base{
    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());

    }

    @BeforeMethod(enabled = true)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser();
    }

    LoginPage loginPage = new LoginPage(driver.get());
    CaseAdministrationPage adminCase=new CaseAdministrationPage(driver.get());

    public void startMethod(){
        driver.get().navigate().to(urls.agentLogin);
        loginPage.loginWithUser(UserType.CaseManagerHead);
    }
    @Test(description = "Add new case source", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void addCaseSource()  {
        startMethod();
        adminCase.createNewCase(adminCase.getAddNewSource(),adminCase.getSaveNewRow(),adminCase.getElementToScroll());
        logManager.STEP("In Case source section, create new source, and make sure that the Source Status is checked","");
        String successMsgTxt= adminCase.getSuccessMsg();
        Assert.assertEquals(successMsgTxt,"تم إضافة مصدر الحالة بنجاح");
        adminCase.lastStep(adminCase.getElementToScroll());
    }

    @Test(description = "Add new social issue", priority = 2,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void addSocialType()  {
        startMethod();
        logManager.STEP("In social issue type section, add new social issue type, and make sure that the social issue status is checked.","");
        adminCase.createNewCase(adminCase.getSocialIssueLink(),adminCase.getSaveNewRow(),adminCase.getSocialEleScroll());
        String successMsgText=adminCase.getSuccessMsg();
        Assert.assertEquals(successMsgText,"تم إضافة نوع المسألة الاجتماعية بنجاح");
        adminCase.lastStep(adminCase.getSocialEleScroll());
    }

    @Test(description = "Add new partner social issue", priority = 3,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void AddPartner_SocialIssue()  {
        startMethod();
        logManager.STEP("In Partner list to refer social issue section, add new partner, and make sure that the partner Status is checked.","");
        adminCase.createNewCase(adminCase.getPartnerSocialLink(),adminCase.getSaveNewRow(),adminCase.getPartnerSocialScroll());
        String successMsgText=adminCase.getSuccessMsg();
        Assert.assertEquals(successMsgText,"تم إضافة شريك المسألة الاجتماعية بنجاح");
        adminCase.lastStep(adminCase.getPartnerSocialScroll());
    }

    @Test(description = "Add new Case Log", priority = 4,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void addCaseLog()  {
        startMethod();
        logManager.STEP("In case log file section, add new log file, and make sure that the log file status is checked.","");
        adminCase.createNewCase(adminCase.getLogCaseLink(),adminCase.getSaveNewRow(),adminCase.getCaseLogScroll());
        String successMsgText=adminCase.getSuccessMsg();
        Assert.assertEquals(successMsgText,"تم إضافة نوع السجل بنجاح");
        adminCase.lastStep(adminCase.getPartnerSocialScroll());
    }
//enforcementCaseLink
@Test(description = "Add new Enforcement Type", priority = 5,
        retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
public void addEnforcementType()  {
    startMethod();
    logManager.STEP("In enforcement type section, add new enforcement type, and make sure that the enforcement type status is checked.","");
    adminCase.createNewCase(adminCase.getEnforcementCaseLink(),adminCase.getSaveNewRow(),adminCase.getFooterScroll());
    String successMsgText=adminCase.getSuccessMsg();
    Assert.assertEquals(successMsgText,"تم إضافة نوع عدم الامتثال بنجاح");
    adminCase.lastStep(adminCase.getPartnerSocialScroll());
}
}

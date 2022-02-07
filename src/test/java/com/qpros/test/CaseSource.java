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

    @Test(description = "Add new case source", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void addCaseSource()  {
        driver.get().navigate().to(urls.agentLogin);
        loginPage.loginWithUser(UserType.CaseManagerHead);
        adminCase.createNewCase(adminCase.getAddNewSource(),adminCase.getSaveNewRow());
        String successMsgTxt= adminCase.getSuccessMsg();
        Assert.assertEquals("تم إضافة مصدر الحالة بنجاح",successMsgTxt);
    }

    @Test(description = "Add new social issue", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void addSocialType()  {
        driver.get().navigate().to(urls.agentLogin);
        loginPage.loginWithUser(UserType.CaseManagerHead);
        adminCase.createNewCase(adminCase.getSocialIssueLink(),adminCase.getSaveNewRow());
        String successMsgText=adminCase.getSuccessMsg();
        Assert.assertEquals("تم إضافة نوع المسألة الاجتماعية بنجاح",successMsgText);
    }
}
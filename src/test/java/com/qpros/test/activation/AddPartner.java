package com.qpros.test.activation;

import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.pages.web.SSA.AddPartnerPage;
import com.qpros.pages.web.SSA.LoginPage;
import com.qpros.pages.web.SSA.UserType;
import com.qpros.pages.web.SSA.modules.ApproveApplicationModule;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.common.locators.urls;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

@Listeners(com.qpros.common.LogManager.class)

public class AddPartner extends Base {


    LoginPage loginPage = new LoginPage(driver.get());
    AddPartnerPage addPartnerPage=new AddPartnerPage(driver.get());
    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());
    }
    @BeforeMethod(enabled = true)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser(true);
    }
    @Test(description = "Add partner ", retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void addPartner()
    {
        ActionsHelper.navigate(urls.agentLogin);
        loginPage.loginWithUser(UserType.ProgramManager);
        addPartnerPage.addPartner();
    }

}

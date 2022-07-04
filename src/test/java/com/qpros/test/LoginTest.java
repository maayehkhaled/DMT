package com.qpros.test;

import com.qpros.common.web.Base;
import com.qpros.pages.web.dmt.Login;
import com.qpros.reporting.QuantaTestManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

@Listeners(com.qpros.common.LogManager.class)
public class LoginTest extends Base {
    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());

    }
    Login login= new Login(driver.get());
    @BeforeMethod(enabled = true)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser(false);
    }

    @Test(description = "Login with UAE PASS", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void operateLogin(){
        QuantaTestManager.getTest().assignCategory("Login");

        login.performLogin();
    }

}

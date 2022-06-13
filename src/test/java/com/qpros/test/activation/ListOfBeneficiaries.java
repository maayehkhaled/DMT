package com.qpros.test.activation;
import com.qpros.common.web.Base;
import com.qpros.pages.web.SSA.ListOfBeneficiariesPage;
import com.qpros.pages.web.SSA.LoginPage;
import com.qpros.pages.web.SSA.UserType;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.common.locators.urls;
import org.testng.annotations.*;

import java.io.IOException;

@Listeners(com.qpros.common.LogManager.class)
public class ListOfBeneficiaries extends Base {
    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());
    }

    @BeforeClass(enabled = true)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser(true);
    }

    public void startMethod(){
        driver.get().navigate().to(urls.agentLogin);
        loginPage.loginWithUser(UserType.CM2);
    }

    LoginPage loginPage = new LoginPage(driver.get());
    ListOfBeneficiariesPage beneficiaryList=new ListOfBeneficiariesPage(driver.get());

    @Test(description = "open Beneficiaries List", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void validateOpenBeneficiariesList() {
        startMethod();
        beneficiaryList.openBeneficiariesList();
        //beneficiaryList.clickMoreFilter();
        beneficiaryList.searchBeneficiaryEID();
        beneficiaryList.searchBeneficiarySSP();
    }

    @Test(description = "Count Beneficiaries List", priority = 2,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void countBeneficiariesList() {
        beneficiaryList.openBeneficiariesList();
        beneficiaryList.countAll();
    }
    @AfterClass
    public synchronized void stopDriver() {
        try {
            System.out.println(Thread.currentThread().getId()+ "closed driver");
            driver.get().quit();
        } catch (Throwable e) {
            e.getStackTrace();
        }
    }
}

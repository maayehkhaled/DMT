package com.qpros.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.pages.web.SSA.ClaimantApplicationPage;
import com.qpros.pages.web.SSA.ClaimantLogin;
import com.qpros.pages.web.SSA.modules.ApproveApplicationModule;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.common.locators.urls;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class BCOCApprove extends Base {


    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());

    }

    @BeforeMethod(enabled = true)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser();
    }
    ApproveApplicationModule approveApplicationModule = new ApproveApplicationModule(driver.get());
    ClaimantApplicationPage claimantApplicationPage = new ClaimantApplicationPage(driver.get());
    ClaimantLogin claimantLogin = new ClaimantLogin(driver.get());
    /*
    @Test(description = "Approve an application", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void approveApplication() throws JsonProcessingException, AWTException, InterruptedException {
        approveApplicationModule.approveApplication(false);
    }


     */

    @Test(description = "Bcoc Approve", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void bcocApprove() throws JsonProcessingException, AWTException {
        try {
            driver.get().navigate().to(urls.claimantLogin);
            ActionsHelper.driverWait(1000);
            claimantLogin.claimantLogin("784198546504758");
            //claimantLogin.navigateToUpdateFamilyInformation();
            claimantApplicationPage.bocc();
            //claimantApplicationPage.uploadBill();
            //claimantApplicationPage.doPensionCheckboxes();
            //claimantApplicationPage.doSalaryCertifications();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }






}

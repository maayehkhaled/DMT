package com.qpros.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.pages.web.SSA.*;
import com.qpros.pages.web.SSA.modules.ApproveApplicationModule;
import com.qpros.quanta.Status;
import com.qpros.quanta.markuputils.MarkupHelper;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.service.SubmitApplicationService;
import com.ssa.core.service.VerifyEligibilityService;
import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

@Listeners(com.qpros.common.LogManager.class)
public class ApproveApplication extends Base {
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
    @Test(description = "Approve an application", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void approveApplication() throws JsonProcessingException, AWTException {

        approveApplicationModule.approveApplication();
    }


    @Test(description = "Approve an testest", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void test() throws JsonProcessingException, AWTException {
        try {
            driver.get().navigate().to("https://10.231.1.100/DCDClaimantFrontEnd/BenefitsApplication.aspx?GUID=ed345448-0069-4bc2-89ca-9318c9b701ea");
            ActionsHelper.driverWait(1000);
            //claimantLogin.claimantLogin("784194683719275");
            //claimantLogin.navigateToUpdateFamilyInformation();
            //claimantApplicationPage.uploadBill();
            //claimantApplicationPage.doPensionCheckboxes();
            claimantApplicationPage.doSalaryCertifications();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

package com.qpros.test.restfull12;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qpros.common.web.Base;
import com.qpros.helpers.ExcelUtils;
import com.qpros.helpers.FileUtils;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.service.VerifyEligibilityService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

@Listeners(com.qpros.common.LogManager.class)
public class AddFamilyMember extends Base {


    VerifyEligibilityService verifyEligibilityService = new VerifyEligibilityService();

    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());

    }

    @BeforeMethod(enabled = false)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser();
    }



    @Test(description = "Confirm Single Addition", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void confirmSingleAddition() {
        logManager.STEP("Read Test Data from Source","");
        //TODO ExcelFileUtils
        logManager.STEP("The User Trigger Verify Eligibility Service","");
        verifyEligibilityService.requestServiceWithParam();
        logManager.STEP("Read Test Data from Source","");
        logManager.STEP("Read Test Data from Source","");


    }

}

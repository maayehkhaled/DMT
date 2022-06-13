package com.qpros.test.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.qpros.common.web.Base;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.service.CancelApplication;
import com.ssa.core.service.UpdateWealthIncome;
import com.ssa.core.service.VerifyEligibilityService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

@Listeners(com.qpros.common.LogManager.class)
public class UpdateWealthIncome_UpdateSuccessfully extends Base{

    UpdateWealthIncome updateWealthIncome=new UpdateWealthIncome();
    VerifyEligibilityService verifyEligibilityService=new VerifyEligibilityService();
    CancelApplication cancelApp=new CancelApplication();

    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());
    }

    @BeforeMethod(enabled = false)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser(true);
    }
    @Test(description = "Update Wealth Income Successfully", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void UpdateWealthIncomeSuccessfully() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        //
        String emirateId = "";
        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 88) {
                count++;
                emirateId = nextLine[1];
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        logManager.STEP("The User Trigger Verify Eligibility Service", "");
        verifyEligibilityService.requestServiceWithParam(emirateId);
        Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).responseStatus.statusCode, 200);
        Assert.assertTrue(verifyEligibilityService.getresponse(verifyEligibilityService).claimant.familyBookEmirateEN.contains("familybook"));


        logManager.STEP("Update Wealth Income", "");
        updateWealthIncome.requestServiceWithParam(emirateId);
        updateWealthIncome.getResponse(updateWealthIncome);
        //Statuscode 200
        Assert.assertEquals(updateWealthIncome.getResponse(updateWealthIncome).responseStatus.statusCode, 200);
        //then .contains successfully submitted
        Assert.assertTrue(updateWealthIncome.getResponse(updateWealthIncome).responseStatus.message.contains("successfully submitted"));
        //$.Application.HasDataIssues false
        Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).application.hasDataIssues, false);
        //$.Application.IsEligible true
        Assert.assertTrue(verifyEligibilityService.getresponse(verifyEligibilityService).application.isEligible);
        //$.ResponseTrail[0].IsSuccess true
        //????

        logManager.STEP("Cancel Application_Successfully", "");
        cancelApp.requestServiceWithParam(emirateId);
        cancelApp.getresponse(cancelApp);
        Assert.assertEquals(cancelApp.getresponse(cancelApp).responseStatus.statusCode, 200);
        Assert.assertTrue(cancelApp.getresponse(cancelApp).responseStatus.message.contains("Success"));
    }
}

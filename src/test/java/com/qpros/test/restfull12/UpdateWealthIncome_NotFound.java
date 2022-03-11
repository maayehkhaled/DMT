package com.qpros.test.restfull12;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.qpros.common.web.Base;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.service.CancelApplication;
import com.ssa.core.service.UpdateWealthIncome;
import com.ssa.core.service.VerifyEligibilityService;
import kong.unirest.HttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

@Listeners(com.qpros.common.LogManager.class)
public class UpdateWealthIncome_NotFound extends Base {
    UpdateWealthIncome updateWealthIncome=new UpdateWealthIncome();
    VerifyEligibilityService verifyEligibilityService=new VerifyEligibilityService();

    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());
    }

    @BeforeMethod(enabled = false)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser();
    }
    @Test(description = "Update Wealth Income", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void UpdateWealthIncome() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        //
        String emirateId = "";
        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 89) {
                count++;
                emirateId = nextLine[1];
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        updateWealthIncome.requestBodyWithParam(emirateId);
        logManager.STEP("The User Assert The Status Code Is 200", "");
        Assert.assertEquals(updateWealthIncome.getResponse(updateWealthIncome).responseStatus.statusCode,200);
        //Contains : could not retrieve.
        Assert.assertEquals(updateWealthIncome.getResponse(updateWealthIncome).responseStatus.message,"could not retrieve");

        logManager.STEP("The User Trigger Verify Eligibility Service", "");
        Assert.assertFalse(verifyEligibilityService.getresponse(verifyEligibilityService).application.isEligible);
        logManager.STEP("The User Assert The App Has Data Issues", "");
        Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).application.hasDataIssues,false);

    }
}

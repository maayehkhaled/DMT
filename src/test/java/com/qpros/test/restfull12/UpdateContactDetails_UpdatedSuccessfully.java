package com.qpros.test.restfull12;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.qpros.common.web.Base;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.service.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

@Listeners(com.qpros.common.LogManager.class)
public class UpdateContactDetails_UpdatedSuccessfully extends Base {
    VerifyEligibilityService verifyEligibilityService = new VerifyEligibilityService();
    GetFamilyData getFamilyData = new GetFamilyData();
    UpdateContactDetails updateContact = new UpdateContactDetails();
    CancelApplication cancelApplication = new CancelApplication();

    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());
    }

    @BeforeMethod(enabled = false)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser();
    }


    @Test(description = "Verify Eligibility", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void VerifyEligibility() throws JsonProcessingException {
        //CSV
        String emirateId = "";
            int count = 0;
            CSVReader csvReader = null;
            try {
                csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
                String[] nextLine;
                while ((nextLine = csvReader.readNext()) != null && count <= 27) {
                    count++;
                    emirateId = nextLine[1];
                }

            } catch (IOException | CsvValidationException e) {
                e.printStackTrace();
            }


        logManager.STEP("The User Trigger Verify Eligibility Service", "");
        verifyEligibilityService.requestServiceWithParam(emirateId);
        Assert.assertTrue(verifyEligibilityService.getresponse(verifyEligibilityService).application.isEligible);
        Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).headOfFamilyBook.emiratesId, emirateId);


            logManager.STEP("The User Trigger Family Get Data Service", "");
            getFamilyData.requestServiceWithParam(emirateId);
            getFamilyData.getresponse(getFamilyData);

            logManager.STEP("The User Trigger Update Contact Details", "");
            updateContact.requestServiceWithParam(emirateId);
            updateContact.getresponse(updateContact);


            logManager.STEP("The User Trigger Get Family Data Service", "");
            logManager.STEP("The User Trigger Family Get Data Service", "");
            getFamilyData.requestServiceWithParam(emirateId);
            getFamilyData.getresponse(getFamilyData);

            logManager.STEP("The User Trigger Cancel Application Service", "");
            cancelApplication.requestServiceWithParam(emirateId);
            Assert.assertEquals(cancelApplication.getresponse(cancelApplication).responseStatus.statusCode, 200);

    }
}

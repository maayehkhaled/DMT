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
public class UpdateActivationInformation_ActivationInformationSaved extends Base{
    //Verify
    VerifyEligibilityService verifyEligibilityService = new VerifyEligibilityService();
    //GetActivationInformation
    GetActivationInformation getActivationInfo=new GetActivationInformation();
    //UpdateActivationInformation
    UpdateActivationInformation updateActivationInfo=new UpdateActivationInformation();
    //GetActivationInformation2
    //Cancel
    CancelApplication cancelApp=new CancelApplication();
    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());
    }

    @BeforeMethod(enabled = false)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser();
    }
    String emirateId = "";

    @Test(description = "Verify Eligibility", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void verifyEligibility() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        //

        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 78) {
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
    }

    @Test(description = "Get Activation Information", priority = 2, dependsOnMethods = "verifyEligibility",
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void getActivationInformation() throws JsonProcessingException {
        logManager.STEP("The User Trigger Get Activation Information Service", "");
        getActivationInfo.requestServiceWithParam(emirateId);
        getActivationInfo.getresponse(getActivationInfo);
    }

    @Test(description = "Update Activation Information", priority = 3, dependsOnMethods = "getActivationInformation",
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void updateActivationInformation() throws JsonProcessingException {
        logManager.STEP("The User Trigger Update Activation Information Service", "");
        updateActivationInfo.requestServiceWithParam(emirateId);
        updateActivationInfo.getresponse(updateActivationInfo);
        getActivationInformation();
    }

    @Test(description = "Cancel Application", priority = 4, dependsOnMethods = "updateActivationInformation",
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void cancelApplication() throws JsonProcessingException {
        logManager.STEP("The User Trigger Cancel Application Service", "");
        cancelApp.requestServiceWithParam(emirateId);
        cancelApp.getresponse(cancelApp);
    }
    }

package com.qpros.test.restfull12;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.qpros.common.web.Base;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.service.CancelApplication;
import com.ssa.core.service.RemoveFBMember;
import com.ssa.core.service.VerifyEligibilityService;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

@Listeners(com.qpros.common.LogManager.class)
public class RemoveFBMember_IndividualNotFound extends Base{
    VerifyEligibilityService verifyEligibilityService = new VerifyEligibilityService();
    RemoveFBMember removeMember=new RemoveFBMember();
    CancelApplication cancelApplication=new CancelApplication();

    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());
    }

    @BeforeMethod(enabled = false)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser();
    }

    //CSV
    String emirateId = "";

    public void readCSV() {

        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 16) {
                count++;
                emirateId = nextLine[1];
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    @Test(description = "Verify Eligibility", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void VerifyEligibility(){
        logManager.STEP("The User Trigger Verify Eligibility Service","");
        verifyEligibilityService.requestServiceWithParam(emirateId);
        Assert.assertTrue(verifyEligibilityService.getresponse(verifyEligibilityService).application.isEligible);
        Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).headOfFamilyBook.emiratesId,emirateId);

    }
    @Test(description = "Remove FB Member", priority = 2,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void RemoveFBMember() throws JsonProcessingException {
        logManager.STEP("The User Trigger Remove FB Member Service","");
        removeMember.requestServiceWithEid(emirateId);
        removeMember.getresponse(removeMember);

    }
    @Test(description = "Cancel Application Successfully", priority = 3,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void CancelApplication() throws JsonProcessingException {
        logManager.STEP("The User Trigger Cancel Application Service","");
        cancelApplication.requestServiceWithParam(emirateId);
        Assert.assertEquals(cancelApplication.getresponse(cancelApplication).responseStatus.statusCode,200);
    }

}

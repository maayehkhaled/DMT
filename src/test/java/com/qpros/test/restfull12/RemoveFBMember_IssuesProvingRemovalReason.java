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
public class RemoveFBMember_IssuesProvingRemovalReason extends Base {
    VerifyEligibilityService verifyEligibilityService=new VerifyEligibilityService();
    GetFamilyData getFamilyData=new GetFamilyData();
    RemoveFBMember removeFBMember=new RemoveFBMember();
    CancelApplication cancelApp=new CancelApplication();

    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());
    }

    @BeforeMethod(enabled = false)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser();
    }
    @Test(description = "RemoveFBMember_IssuesProvingRemovalReason", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void RemoveFBMemberRemovalReason() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        //
        String emirateId = "";
        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 103) {
                count++;
                emirateId = nextLine[1];
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        logManager.STEP("The User Trigger Verify Eligibility Service", "");
        verifyEligibilityService.requestServiceWithParam(emirateId);
        Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).responseStatus.statusCode,200);
        //contains: familybook
        Assert.assertTrue(verifyEligibilityService.getresponse(verifyEligibilityService).claimant.familyBookEmirateEN.contains("familybook"));

    //GetFamilyData_ConfirmedFamily

        logManager.STEP("The User Trigger GetFamilyData_ConfirmedFamily Service", "");
        getFamilyData.requestServiceWithParam(emirateId);
        //statusCode 200

        //familybooknumber

        //$.Household[0].IsRemoved  false

        //$.Application.HasDataIssues false

        //$.Household[4].IsRemoved  true


    //RemoveFBMember_ConfirmedMarriage
        logManager.STEP("The User Trigger RemoveFBMember_ConfirmedMarriage Service", "");
        removeFBMember.requestServiceWithEid(emirateId);
        removeFBMember.getresponse(removeFBMember);
        //status 200
        Assert.assertEquals(removeFBMember.response.getStatus(),200);
        //contains could not prove
        Assert.assertTrue(removeFBMember.response.getStatusText().contains("could not prove"));
        //$.Individual.IsRemoved
        Assert.assertEquals(removeFBMember.getresponse(removeFBMember).toRemoveEmiratesId,false);
        //$.Application.HasDataIssues

    //CancelApplication_Successfully
        logManager.STEP("Cancel Application_Successfully", "");
        cancelApp.requestServiceWithParam(emirateId);
        cancelApp.getresponse(cancelApp);
        Assert.assertEquals(cancelApp.getresponse(cancelApp).responseStatus.statusCode,200);
        Assert.assertTrue(cancelApp.getresponse(cancelApp).responseStatus.message.contains("Success"));
    }
}

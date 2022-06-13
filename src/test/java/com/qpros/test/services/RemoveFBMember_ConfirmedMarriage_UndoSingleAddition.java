package com.qpros.test.services;

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
public class RemoveFBMember_ConfirmedMarriage_UndoSingleAddition extends Base {
    VerifyEligibilityService verifyEligibilityService = new VerifyEligibilityService();
    RemoveFBMember removeFBMember = new RemoveFBMember();
    GetFamilyData getFamilyData = new GetFamilyData();
    AddNewMember addMember = new AddNewMember();
    CancelApplication cancelApp = new CancelApplication();

    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());
    }

    @BeforeMethod(enabled = false)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser(true);
    }
    String emirateId = "";
    @Test(description = "RemoveFBMember_ConfirmedMarriage", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void removeFBMemberConfirmedMarriage() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 100) {
                count++;
                emirateId = nextLine[1];
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        logManager.STEP("The User Trigger Verify Eligibility Service", "");
        verifyEligibilityService.requestServiceWithParam(emirateId);
        Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).responseStatus.statusCode, 200);
        //contains: familybook
        Assert.assertTrue(verifyEligibilityService.getresponse(verifyEligibilityService).claimant.familyBookEmirateEN.contains("familybook"));


        //GetFamilyData_ConfirmedFamily
        GetFamilyData_ConfirmedFamily();

        logManager.STEP("The User Trigger RemoveFBMember_ConfirmedMarriage Service", "");
        removeFBMember.requestServiceWithEid(emirateId);
        removeFBMember.getresponse(removeFBMember);

        //status 200
        Assert.assertEquals(removeFBMember.response.getStatus(), 200);
        //contains successfuly removed
        Assert.assertTrue(removeFBMember.response.getStatusText().

                contains("successfully removed"));
        //$.Individual.IsRemoved
        Assert.assertEquals(removeFBMember.getresponse(removeFBMember).toRemoveEmiratesId, true);
        //$.Individual.RemovalReasonKey
        Assert.assertEquals(removeFBMember.getresponse(removeFBMember).removalReasonKey, "Married");

        GetFamilyData_ConfirmedFamily();

        //AddNewMember_ConfirmSingleAddition
        logManager.STEP("The User Trigger AddNewMember_ConfirmSingleAddition Service", "");
        addMember.requestServiceWithParam(emirateId);
        //addMember.getResponse(addMember);
        //statusCode 200
        Assert.assertEquals(addMember.response.getStatus(), 200);
        //Contains : IsEligible
        //Assert.assertTrue(addMember.getResponse(addMember).relationshipToHoHKey.contains("IsEligible"));
        //$.Individual.NeedsRemovalReason true

        //$.Application.IsAdded true

        //$.Application.HasDataIssues

        //GetFamilyData_ConfirmedFamily2
        GetFamilyData_ConfirmedFamily();

        //CancelApplication_Successfully
        logManager.STEP("Cancel Application_Successfully", "");
        cancelApp.requestServiceWithParam(emirateId);
        cancelApp.getresponse(cancelApp);
        Assert.assertEquals(cancelApp.getresponse(cancelApp).responseStatus.statusCode, 200);
        Assert.assertTrue(cancelApp.getresponse(cancelApp).responseStatus.message.contains("Success"));
    }
    public void GetFamilyData_ConfirmedFamily() throws JsonProcessingException {
        logManager.STEP("The User Trigger GetFamilyData_ConfirmedFamily Service", "");
        getFamilyData.requestServiceWithParam(emirateId);
        //statusCode 200

        //familybooknumber

        //$.Household[0].IsRemoved  false

        //$.Application.HasDataIssues false

        //$.Household[4].IsRemoved  true
    }
}

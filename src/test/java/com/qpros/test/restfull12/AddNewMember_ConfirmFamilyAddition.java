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
public class AddNewMember_ConfirmFamilyAddition extends Base {
    VerifyEligibilityService verifyEligibilityService=new VerifyEligibilityService();
    AddNewMember addMember=new AddNewMember();
    GetFamilyData getFamilyData=new GetFamilyData();
    CancelApplication cancelApp=new CancelApplication();
    String emirateId = "";

    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());
    }

    @BeforeMethod(enabled = false)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser();
    }
    public void getData() {
        logManager.STEP("Read Test Data from Source", "");
        //
        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 108) {
                count++;
                emirateId = nextLine[1];
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
    @Test(description = "AddNewMember_ConfirmFamilyAddition ", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void addNewMember_ConfirmFamilyAddition () throws JsonProcessingException {
        getData();
        //Call VE api
        logManager.STEP("The User Trigger Verify Eligibility Service", "");
        verifyEligibilityService.requestServiceWithParam(emirateId);
        Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).responseStatus.statusCode,200);
        //verifyEligibilityService.response.getStatus();
        //contains: familybook
        Assert.assertTrue(verifyEligibilityService.getresponse(verifyEligibilityService).claimant.familyBookEmirateEN.contains("familybook"));
        Assert.assertTrue(verifyEligibilityService.getresponse(verifyEligibilityService).claimant.familyBookEmirateEN.equalsIgnoreCase("familybook"));

        //AddNewMember_ConfirmSingleAddition Call Add FM API
        logManager.STEP("The User Trigger AddNewMember_ConfirmSingleAddition Service", "");
        addMember.requestServiceWithParam(emirateId);
        //Assert.assertEquals(addMember.getResponse(addMember).responseStatus.statusCode,200);
        //statusCode 200
        Assert.assertEquals(addMember.response.getStatus(),200);
        //Contains : IsEligible
        Assert.assertTrue(addMember.getResponse(addMember).responseStatus.message.contains("IsEligible"));
        Assert.assertTrue(addMember.getResponse(addMember).application.isEligible);
        //$.Application.IsAdded true ?
        Assert.assertTrue(addMember.getResponse(addMember).application.messageEN.contains("Is Added"));
        //$.Application.HasDataIssues false
        Assert.assertFalse(addMember.getResponse(addMember).application.hasDataIssues);

        //Call get FM data API
        logManager.STEP("The User Trigger GetFamilyData_ConfirmedFamily Service", "");
        getFamilyData.requestServiceWithParam(emirateId);
        //statusCode 200
        Assert.assertEquals(getFamilyData.response.getStatus(),200);
        //familybooknumber
        /*
        Assert.assertTrue(getFamilyData.getresponse(getFamilyData)..contains("familybooknumber"));
        //$.Household[0].IsRemoved  false
        Assert.assertFalse(getFamilyData.getresponse(getFamilyData).household.get(0).isRemoved);
        //$.Application.HasDataIssues false
        Assert.assertFalse(getFamilyData.getresponse(getFamilyData).application.hasDataIssues);*/
        //contains 784199931703088 ?

        //Cancel API
        logManager.STEP("Cancel Application_Successfully", "");
        cancelApp.requestServiceWithParam(emirateId);
        cancelApp.getresponse(cancelApp);
        Assert.assertEquals(cancelApp.getresponse(cancelApp).responseStatus.statusCode,200);
        Assert.assertTrue(cancelApp.getresponse(cancelApp).responseStatus.message.contains("Success"));
    }
}

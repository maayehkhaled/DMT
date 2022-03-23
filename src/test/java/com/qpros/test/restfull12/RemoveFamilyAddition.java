package com.qpros.test.restfull12;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.qpros.common.web.Base;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.service.*;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

@Listeners(com.qpros.common.LogManager.class)
public class RemoveFamilyAddition extends Base {
    VerifyEligibilityService verifyEligibilityService=new VerifyEligibilityService();
    AddNewMember addMember=new AddNewMember();
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
    @Test(description = "Verify Eligibility", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void verifyEligibility() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        //
        String emirateId = "";
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
        Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).responseStatus.statusCode,200);
        //contains: familybook
        Assert.assertTrue(verifyEligibilityService.getresponse(verifyEligibilityService).claimant.familyBookEmirateEN.contains("familybook"));
    }
    //AddNewMember_ConfirmSingleAddition
    @Test(description = "AddNewMember_ConfirmSingleAddition", priority = 2,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void AddNewMember_ConfirmSingleAddition() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        //
        String emirateId = "";
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
        logManager.STEP("The User Trigger AddNewMember_ConfirmSingleAddition Service", "");
        addMember.requestServiceWithParam(emirateId);
        //addMember.getResponse(addMember);
        //statusCode 200
        Assert.assertEquals(addMember.response.getStatus(),200);
        //Contains : IsEligible
        Assert.assertTrue(addMember.getResponse(addMember).relationshipToHoHKey.contains("IsEligible"));
        //$.Individual.NeedsRemovalReason true
        //$.Application.IsAdded true
        //$.Application.HasDataIssues false
    }
    //GetFamilyData_ConfirmedFamily
    @SneakyThrows
    @Test(description = "Get Data Of Confirmed Family", priority = 3,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void getFamily()  {
        logManager.STEP("Read Test Data from Source","");
        //TODO ExcelFileUtils
        String emirateId="";
        int count=0;
        CSVReader csvReader= null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String [] nextLine;
            while((nextLine= csvReader.readNext())!=null&& count<=100){
                count++;
                emirateId=nextLine[1];
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        logManager.STEP("The User Trigger Verify Eligibility Service","");

        logManager.STEP("Get Data Of Confirmed Family","");
        getFamilyData.requestServiceWithParam(emirateId);
       //check EID 784199931703088 it should be exist
        Assert.assertEquals(getFamilyData.getresponse(getFamilyData).emiratesId,emirateId);
    }

    //RemoveFBMember_Success
    @Test(description = "RemoveFBMember_Success", priority = 4,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void RemoveFBMember_Success() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        //
        String emirateId = "";
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
        logManager.STEP("The User Trigger RemoveFBMember_ConfirmedMarriage Service", "");
        removeFBMember.requestServiceWithEid(emirateId);
        removeFBMember.getresponse(removeFBMember);
        //status 200
        Assert.assertEquals(removeFBMember.response.getStatus(),200);
        //contains successfully removed
        Assert.assertTrue(removeFBMember.response.getStatusText().contains("successfully removed"));
        //$.Individual.IsDeleted true
        Assert.assertEquals(removeFBMember.getresponse(removeFBMember).toRemoveEmiratesId,true);
        //$.Individual.RemovalReasonKey Married
        Assert.assertEquals(removeFBMember.getresponse(removeFBMember).removalReasonKey,"Married");
    }
    //GetFamilyData2_AfterRemove
    //Call get family data API  check 784199931703088 it should not be exist
    @SneakyThrows
    @Test(description = "Get Data Of Confirmed Family", priority = 3,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void getFamily2()  {
        logManager.STEP("Read Test Data from Source","");
        //TODO ExcelFileUtils
        String emirateId="";
        int count=0;
        CSVReader csvReader= null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String [] nextLine;
            while((nextLine= csvReader.readNext())!=null&& count<=100){
                count++;
                emirateId=nextLine[1];
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        logManager.STEP("The User Trigger Get Family Data Service","");
        getFamilyData.requestServiceWithParam(emirateId);
        //check EID 784199931703088 it should not be exist
        Assert.assertNotEquals(getFamilyData.getresponse(getFamilyData).emiratesId,emirateId);
    }
    //CancelApplication_Successfully
    @Test(description = "Cancel Application_Successfully", priority = 5,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void CancelApplication_Successfully() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        //
        String emirateId = "";
        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 105) {
                count++;
                emirateId = nextLine[1];
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        logManager.STEP("Cancel Application_Successfully", "");
        cancelApp.requestServiceWithParam(emirateId);
        cancelApp.getresponse(cancelApp);
        Assert.assertEquals(cancelApp.getresponse(cancelApp).responseStatus.statusCode,200);
        Assert.assertTrue(cancelApp.getresponse(cancelApp).responseStatus.message.contains("Success"));
    }
}

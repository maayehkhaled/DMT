package com.qpros.test.restfull12;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.qpros.common.web.Base;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.service.AddNewMember;
import com.ssa.core.service.CancelApplication;
import com.ssa.core.service.GetFamilyData;
import com.ssa.core.service.VerifyEligibilityService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

@Listeners(com.qpros.common.LogManager.class)
public class AddFamilyMemberNonUaeNational extends Base {


    VerifyEligibilityService verifyEligibilityService = new VerifyEligibilityService();
    AddNewMember addMember=new AddNewMember();
    CancelApplication cancelApp=new CancelApplication();
    GetFamilyData getFamilyData=new GetFamilyData();
    String emirateId="";
    int count=0;
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
    public void addFamilyMemberNonUaeNational() throws JsonProcessingException {
        getData();
        verifyEligibility();
        getFamilyConfirmedData();
        addNewMemberConfirmSingle();
        getFamilyConfirmedData2();
        cancelApp();
    }
        public void getData() {
            logManager.STEP("Read Test Data from Source","");
            //TODO ExcelFileUtils

            CSVReader csvReader= null;
            try {
                csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
                String [] nextLine;
                while((nextLine= csvReader.readNext())!=null&& count<=122){
                    count++;
                    emirateId=nextLine[1];
                }


            } catch (IOException | CsvValidationException e) {
                e.printStackTrace();
            }
        }
        public void verifyEligibility() throws JsonProcessingException {
            logManager.STEP("The User Trigger Verify Eligibility Service","");
            verifyEligibilityService.requestServiceWithParam(emirateId);
            Assert.assertTrue(verifyEligibilityService.getresponse(verifyEligibilityService).application.isEligible);
            Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).claimant.isHoFB,true);
            Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).application.hasDataIssues,false);
            Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).responseStatus.statusCode,200);
        }
        public void getFamilyConfirmedData() throws JsonProcessingException {
            String emirateId="";
            int count=0;
            CSVReader csvReader= null;
            try {
                csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
                String [] nextLine;
                while((nextLine= csvReader.readNext())!=null&& count<=10){
                    count++;

                    emirateId=nextLine[1];
                }


            } catch (IOException | CsvValidationException e) {
                e.printStackTrace();
            }
        logManager.STEP("getFamilyConfirmedData","");
        getFamilyData.requestServiceWithEid(emirateId);
        //Assert.assertEquals(getFamilyData.getresponse(getFamilyData).responseStatus.statusCode,200);
        //Assert.assertFalse(getFamilyData.getresponse(getFamilyData).application.hasDataIssues);

        }
    public void getFamilyConfirmedData2() throws JsonProcessingException {
        String emirateId="";
        int count=0;
        CSVReader csvReader= null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String [] nextLine;
            while((nextLine= csvReader.readNext())!=null&& count<=10){
                count++;

                emirateId=nextLine[1];
            }


        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        logManager.STEP("getFamilyConfirmedData2","");
        getFamilyData.requestServiceWithEid(emirateId);
        /*Assert.assertEquals(getFamilyData.getresponse(getFamilyData).responseStatus.statusCode,200);
        Assert.assertFalse(getFamilyData.getresponse(getFamilyData).application.hasDataIssues);*/

    }
    public void addNewMemberConfirmSingle() throws JsonProcessingException {
        String emirateId="";
        int count=0;
        CSVReader csvReader= null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String [] nextLine;
            while((nextLine= csvReader.readNext())!=null&& count<=0){
                count++;

                emirateId=nextLine[1];
            }


        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        logManager.STEP("Add new member","");
        addMember.requestServiceWithParam(emirateId);
        Assert.assertEquals(addMember.getResponse(addMember).responseStatus.statusCode,200);
        Assert.assertFalse(addMember.getResponse(addMember).application.hasDataIssues);

    }
public void cancelApp() throws JsonProcessingException {
    logManager.STEP("Cancel application","");
    cancelApp.requestServiceWithEid(emirateId);
    Assert.assertEquals(cancelApp.getresponse(cancelApp).responseStatus.statusCode,200);
    Assert.assertEquals(cancelApp.getresponse(cancelApp).responseStatus.message,"Success");

}
}



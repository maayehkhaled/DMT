package com.qpros.test.services;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.qpros.common.web.Base;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.service.AddNewMember;
import com.ssa.core.service.SubmitApplication;
import com.ssa.core.service.VerifyEligibilityService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

@Listeners(com.qpros.common.LogManager.class)
public class SubmitApplicationClass extends Base {


    VerifyEligibilityService verifyEligibilityService = new VerifyEligibilityService();
    AddNewMember addMember=new AddNewMember();
   SubmitApplication submitApplication=new SubmitApplication();
    String emirateId="";
    int count=0;
    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());
    }

    @BeforeMethod(enabled = false)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser(true);
    }



    @Test(description = "Submit Application", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void submitApplication() throws JsonProcessingException {
        getData();
        verifyEligibility();
        submit();
    }
        public void getData() {
            logManager.STEP("Read Test Data from Source","");
            //TODO ExcelFileUtils

            CSVReader csvReader= null;
            try {
                csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
                String [] nextLine;
                while((nextLine= csvReader.readNext())!=null&& count<=125){
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
        public void submit() throws JsonProcessingException {
        logManager.STEP("Submit Application","");
        submitApplication.requestServiceWithEid(emirateId);
        Assert.assertEquals(submitApplication.getresponse(submitApplication).responseStatus.statusCode,200);
        Assert.assertTrue(submitApplication.getresponse(submitApplication).application.isEligible);


//
        }

}



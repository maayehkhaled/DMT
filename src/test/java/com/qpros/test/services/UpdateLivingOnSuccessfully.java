package com.qpros.test.services;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.qpros.common.web.Base;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.service.CancelApplication;
import com.ssa.core.service.UpdateLivingOn;
import com.ssa.core.service.VerifyEligibilityService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

@Listeners(com.qpros.common.LogManager.class)
public class UpdateLivingOnSuccessfully extends Base {


    VerifyEligibilityService verifyEligibilityService = new VerifyEligibilityService();
    UpdateLivingOn updateLivingOn=new UpdateLivingOn();
    CancelApplication cancelApp=new CancelApplication();
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



    @Test(description = "UpdateLivingOnSuccessfully", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void updateLivingOnSuccessfully() throws JsonProcessingException {
        getData();
        verifyEligibility();
        getUpdateLivingOn();
        cancelApp();

    }
        public void getData() {
            logManager.STEP("Read Test Data from Source","");
            //TODO ExcelFileUtils

            CSVReader csvReader= null;
            try {
                csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
                String [] nextLine;
                while((nextLine= csvReader.readNext())!=null&& count<=93){
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
            Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).responseStatus.statusCode,200);
        }
        public void getUpdateLivingOn() throws JsonProcessingException {
        logManager.STEP("updateLivingOnSuccessfully","");
        updateLivingOn.requestServiceWithEid(emirateId);
        Assert.assertEquals(updateLivingOn.getresponse(updateLivingOn).responseStatus.statusCode,200);
        Assert.assertTrue(updateLivingOn.getresponse(updateLivingOn).application.isEligible);


        Assert.assertEquals(updateLivingOn.getresponse(updateLivingOn).application.hasDataIssues,false);

        }
public void cancelApp() throws JsonProcessingException {
    logManager.STEP("Cancel application","");
    cancelApp.requestServiceWithEid(emirateId);
    Assert.assertEquals(cancelApp.getresponse(cancelApp).responseStatus.statusCode,200);
    Assert.assertEquals(cancelApp.getresponse(cancelApp).responseStatus.message,"Success");

}
}



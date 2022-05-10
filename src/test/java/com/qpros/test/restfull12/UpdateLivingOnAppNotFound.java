package com.qpros.test.restfull12;
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
public class UpdateLivingOnAppNotFound extends Base {


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
        this.setUpBrowser();
    }



    @Test(description = "UpdateLivingOnAppNotFound", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void updateLivingOnAppNotFound() throws JsonProcessingException {
        getData();

        getUpdateLivingOnAppNotFound();

    }
        public void getData() {
            logManager.STEP("Read Test Data from Source","");
            //TODO ExcelFileUtils

            CSVReader csvReader= null;
            try {
                csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
                String [] nextLine;
                while((nextLine= csvReader.readNext())!=null&& count<=94){
                    count++;
                    emirateId=nextLine[1];
                }


            } catch (IOException | CsvValidationException e) {
                e.printStackTrace();
            }
        }

        public void getUpdateLivingOnAppNotFound() throws JsonProcessingException {
        logManager.STEP("UpdateLivingOnAppNotFound","");
        updateLivingOn.requestServiceWithEid(emirateId);
        Assert.assertEquals(updateLivingOn.getresponse(updateLivingOn).responseStatus.statusCode,200);
        Assert.assertFalse(updateLivingOn.getresponse(updateLivingOn).application.isEligible);


        Assert.assertEquals(updateLivingOn.getresponse(updateLivingOn).application.hasDataIssues,true);

        }

}



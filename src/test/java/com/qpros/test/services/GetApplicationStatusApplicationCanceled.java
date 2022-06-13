package com.qpros.test.services;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.qpros.common.web.Base;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.service.GetApplicationStatus;
import com.ssa.core.service.VerifyEligibilityService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

@Listeners(com.qpros.common.LogManager.class)
public class GetApplicationStatusApplicationCanceled extends Base {


    VerifyEligibilityService verifyEligibilityService = new VerifyEligibilityService();
    GetApplicationStatus getApplicationStatus=new GetApplicationStatus();
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



    @Test(description = "GetApplicationStatusApplicationCanceled", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void getApplicationStatusApplicationCanceled() throws JsonProcessingException {
        getData();
        getApplicationStatusCanceled();

    }
        public void getData() {
            logManager.STEP("Read Test Data from Source","");
            //TODO ExcelFileUtils

            CSVReader csvReader= null;
            try {
                csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
                String [] nextLine;
                while((nextLine= csvReader.readNext())!=null&& count<=70){
                    count++;
                    emirateId=nextLine[1];
                }


            } catch (IOException | CsvValidationException e) {
                e.printStackTrace();
            }
        }

        public void getApplicationStatusCanceled() throws JsonProcessingException {
        logManager.STEP("GetApplicationStatusApplicationCanceled","");
        getApplicationStatus.requestServiceWithEid(emirateId);
        Assert.assertEquals(getApplicationStatus.getresponse(getApplicationStatus).responseStatus.statusCode,200);
        Assert.assertFalse(getApplicationStatus.getresponse(getApplicationStatus).application.isEligible);



        }

}



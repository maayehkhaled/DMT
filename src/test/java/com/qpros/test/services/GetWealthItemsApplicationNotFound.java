package com.qpros.test.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.qpros.common.web.Base;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.service.CancelApplication;
import com.ssa.core.service.GetWealthItems;
import com.ssa.core.service.VerifyEligibilityService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

@Listeners(com.qpros.common.LogManager.class)
public class GetWealthItemsApplicationNotFound extends Base {


    VerifyEligibilityService verifyEligibilityService = new VerifyEligibilityService();
    GetWealthItems getWealthItems=new GetWealthItems();
    CancelApplication cancelApp=new CancelApplication();
    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());
    }

    @BeforeMethod(enabled = false)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser(true);
    }



    @Test(description = "Get Wealth Items Application Not Found", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void getWealthItemsApplicationNotFound() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source","");
        //TODO ExcelFileUtils
        String emirateId="";
        int count=0;
        CSVReader csvReader= null;
        try {
             csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String [] nextLine;
            while((nextLine= csvReader.readNext())!=null&& count<=77){
                count++;
                emirateId=nextLine[1];
            }


        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }



        logManager.STEP("Get Wealth Items Application Not Founds","");
        getWealthItems.requestServiceWithEid(emirateId);
        /*Assert.assertEquals(getWealthItems.getresponse(getWealthItems).application.hasDataIssues,true);
        Assert.assertEquals(getWealthItems.getresponse(getWealthItems).responseStatus.statusCode,200);
        Assert.assertFalse(getWealthItems.getresponse(getWealthItems).application.isEligible);*/

    }

}



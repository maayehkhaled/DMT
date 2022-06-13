package com.qpros.test.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.qpros.common.web.Base;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.service.AddNewPremise;
import com.ssa.core.service.CancelApplication;
import com.ssa.core.service.VerifyEligibilityService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

@Listeners(com.qpros.common.LogManager.class)
public class AddNewPremiseApplicationNotFound extends Base {


    VerifyEligibilityService verifyEligibilityService = new VerifyEligibilityService();

    AddNewPremise addNewPremise=new AddNewPremise();

    CancelApplication cancelApp=new CancelApplication();
    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());
    }

    @BeforeMethod(enabled = false)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser(true);
    }



    @Test(description = "Add New Premise Application Not Found", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void addNewPremiseApplicationNotFound() throws JsonProcessingException {

        logManager.STEP("Read Test Data from Source","");
        //TODO ExcelFileUtils
        String emirateId="";
        int count=0;
        CSVReader csvReader= null;
        try {
             csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String [] nextLine;
            while((nextLine= csvReader.readNext())!=null&& count<=86){
                count++;
                emirateId=nextLine[1];
            }


        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }


        logManager.STEP("AddNewPremiseApplicationNotFound","");
        addNewPremise.requestServiceWithEid(emirateId);
        Assert.assertEquals(addNewPremise.getresponse(addNewPremise).responseStatus.statusCode,200);
        Assert.assertEquals(addNewPremise.getresponse(addNewPremise).application.hasDataIssues,true);
        Assert.assertFalse(addNewPremise.getresponse(addNewPremise).application.isEligible);

    }


}



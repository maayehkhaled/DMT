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
public class AddNewPremiseAlreadyUse extends Base {


    VerifyEligibilityService verifyEligibilityService = new VerifyEligibilityService();
    GetBillData getBillData=new GetBillData();
    AddNewPremise addNewPremise=new AddNewPremise();
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



    @Test(description = "Add New Premise Confirmed Bill Data", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void test() throws JsonProcessingException {
        getData();
        verifyEligibility();
        addNewPremiseConfirmedBillData();
        AddNewPremiseAlreadyUse();
        cancelApp();
    }
    public void addNewPremiseConfirmedBillData() throws JsonProcessingException {
        logManager.STEP("Add New Premise Confirmed Bill Data","");
        addNewPremise.requestServiceWithEid(emirateId);
        Assert.assertEquals(addNewPremise.getresponse(addNewPremise).responseStatus.statusCode,200);

    }
    public void getData() {
        logManager.STEP("Read Test Data from Source", "");
        //TODO ExcelFileUtils

        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 80) {
                count++;
                emirateId = nextLine[1];
            }


        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    public void cancelApp() throws JsonProcessingException {
        logManager.STEP("Cancel application","");
        cancelApp.requestServiceWithEid(emirateId);
        Assert.assertEquals(cancelApp.getresponse(cancelApp).responseStatus.statusCode,200);
        Assert.assertEquals(cancelApp.getresponse(cancelApp).responseStatus.message,"Success");

    }



    public void verifyEligibility() throws JsonProcessingException {
        logManager.STEP("The User Trigger Verify Eligibility Service","");
        verifyEligibilityService.requestServiceWithParam(emirateId);
        Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).responseStatus.statusCode,200);
    }
    public void AddNewPremiseAlreadyUse() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source","");
        //TODO ExcelFileUtils
        String emirateId="";
        int count=0;
        CSVReader csvReader= null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String [] nextLine;
            while((nextLine= csvReader.readNext())!=null&& count<=82){
                count++;
                emirateId=nextLine[1];
            }


        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        logManager.STEP("Add New Premise Already Use","");
        addNewPremise.requestServiceWithEid(emirateId);
        Assert.assertEquals(addNewPremise.getresponse(addNewPremise).responseStatus.statusCode,200);
        Assert.assertEquals(addNewPremise.getresponse(addNewPremise).application.hasDataIssues,false);

    }

}



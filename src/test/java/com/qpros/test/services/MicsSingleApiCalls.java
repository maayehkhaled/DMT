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
public class MicsSingleApiCalls extends Base {


    VerifyEligibilityService verifyEligibilityService = new VerifyEligibilityService();
    GetBillData getBillData=new GetBillData();
    CancelApplication cancelApp=new CancelApplication();
    AddNewMember addNewMember=new AddNewMember();
    GetFamilyData getFamilyData =new GetFamilyData();
    GetApplicationSummary getApplicationSummary=new GetApplicationSummary();
    UpdateContactDetails updateContactDetails=new UpdateContactDetails();
    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());
    }

    @BeforeMethod(enabled = false)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser(true);
    }



    @Test(description = "micsSingleApiCalls", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void micsSingleApiCalls() throws JsonProcessingException {
        cancelApplicationNotFound();
        addNewMemberApplicationNotFound();
        getFamilyDataApplicationNotFound();
        updateContactDetailsApplicationNotFound();
        getApplicationSummaryApplicationSummarySent();
        getApplicationSummaryApplicationNotFound();
    }
    public void cancelApplicationNotFound() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source","");
        //TODO ExcelFileUtils
        String emirateId="";
        int count=0;
        CSVReader csvReader= null;
        try {
             csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String [] nextLine;
            while((nextLine= csvReader.readNext())!=null&& count<=9){
                count++;
                emirateId=nextLine[1];
            }


        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }


        logManager.STEP("Cancel application not found ","");
        cancelApp.requestServiceWithEid(emirateId);
        Assert.assertEquals(cancelApp.getresponse(cancelApp).responseStatus.statusCode,404);
        Assert.assertEquals(cancelApp.getresponse(cancelApp).responseStatus.message,"Not Found");
    }
    public void addNewMemberApplicationNotFound() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source","");
        //TODO ExcelFileUtils
        String emirateId="";
        int count=0;
        CSVReader csvReader= null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String [] nextLine;
            while((nextLine= csvReader.readNext())!=null&& count<=26){
                count++;
                emirateId=nextLine[1];
            }


        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        logManager.STEP("Add new member application not found ","");
        addNewMember.requestServiceWithParam(emirateId);
        Assert.assertEquals(addNewMember.getResponse(addNewMember).responseStatus.statusCode,200);
        Assert.assertEquals(addNewMember.getResponse(addNewMember).application.isEligible,false);
        Assert.assertEquals(addNewMember.getResponse(addNewMember).application.hasDataIssues,true);


    }
    public void getFamilyDataApplicationNotFound() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source","");
        //TODO ExcelFileUtils
        String emirateId="";
        int count=0;
        CSVReader csvReader= null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String [] nextLine;
            while((nextLine= csvReader.readNext())!=null&& count<=11){
                count++;
                emirateId=nextLine[1];
            }


        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        logManager.STEP("get Family Data Application Not Found ","");
        getFamilyData.requestServiceWithEid(emirateId);
        /*Assert.assertEquals(getFamilyData.getresponse(getFamilyData).responseStatus.statusCode,200);
        Assert.assertEquals(getFamilyData.getresponse(getFamilyData).application.isEligible,false);
        Assert.assertEquals(getFamilyData.getresponse(getFamilyData).application.hasDataIssues,true);*/
    }
    public void updateContactDetailsApplicationNotFound() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source","");
        //TODO ExcelFileUtils
        String emirateId="";
        int count=0;
        CSVReader csvReader= null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String [] nextLine;
            while((nextLine= csvReader.readNext())!=null&& count<=29){
                count++;
                emirateId=nextLine[1];
            }


        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        logManager.STEP("update Contact Details Application Not Found ","");
        updateContactDetails.requestServiceWithEid(emirateId);
        Assert.assertEquals(updateContactDetails.getresponse(updateContactDetails).responseStatus.statusCode,400);

    }
    public void getApplicationSummaryApplicationSummarySent() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source","");
        //TODO ExcelFileUtils
        String emirateId="";
        int count=0;
        CSVReader csvReader= null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String [] nextLine;
            while((nextLine= csvReader.readNext())!=null&& count<=33){
                count++;
                emirateId=nextLine[1];
            }


        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }


        logManager.STEP("get Application Summary Application Summary Sent","");
        getApplicationSummary.requestServiceWithEid(emirateId);
        Assert.assertEquals(getApplicationSummary.getresponse(getApplicationSummary).responseStatus.statusCode,200);
        Assert.assertEquals(getApplicationSummary.getresponse(getApplicationSummary).application.isEligible,true);
        Assert.assertEquals(getApplicationSummary.getresponse(getApplicationSummary).application.hasDataIssues,false);
    }
    public void getApplicationSummaryApplicationNotFound() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source","");
        //TODO ExcelFileUtils
        String emirateId="";
        int count=0;
        CSVReader csvReader= null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String [] nextLine;
            while((nextLine= csvReader.readNext())!=null&& count<=34){
                count++;
                emirateId=nextLine[1];
            }


        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        logManager.STEP("get Application Summary Application not found ","");
        getApplicationSummary.requestServiceWithEid(emirateId);
        Assert.assertEquals(getApplicationSummary.getresponse(getApplicationSummary).responseStatus.statusCode,200);
        Assert.assertEquals(getApplicationSummary.getresponse(getApplicationSummary).application.isEligible,false);
        Assert.assertEquals(getApplicationSummary.getresponse(getApplicationSummary).application.hasDataIssues,false);
    }
}



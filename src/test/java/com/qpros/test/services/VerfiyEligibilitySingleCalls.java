package com.qpros.test.services;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.qpros.common.web.Base;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.service.AddNewMember;
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
public class VerfiyEligibilitySingleCalls extends Base {


    VerifyEligibilityService verifyEligibilityService = new VerifyEligibilityService();
    AddNewMember addMember=new AddNewMember();
    CancelApplication cancelApp=new CancelApplication();

    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());
    }

    @BeforeMethod(enabled = false)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser(true);
    }



    @Test(description = "VerifyEligibilitySingleCalls", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void verifyEligibilitySingleCalls() throws JsonProcessingException {
   verifyEligibilityExistingApplication();
   verifyEligibilityBlacklisted();
   verifyEligibilityNonUAENational();
   verifyEligibilityADNationalHoFb();
   verifyEligibilityDeceasedHoFB();
   verifyEligibilityNonHeadOfFamilyBook();
   verifyEligibilityDeceasedApplicant();
   verifyEligibilityNonADFamilyBook();
   verifyEligibilityMultipleWidows();
    }

public void verifyEligibilityExistingApplication() throws JsonProcessingException {
    logManager.STEP("Read Test Data from Source","");
    //TODO ExcelFileUtils
    String emirateId="";
    int count=0;
    CSVReader csvReader= null;
    try {
        csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
        String [] nextLine;
        while((nextLine= csvReader.readNext())!=null&& count<=3){
            count++;

            emirateId=nextLine[1];
        }


    } catch (IOException | CsvValidationException e) {
        e.printStackTrace();
    }

    logManager.STEP("The User Trigger Verify Eligibility Service existing app","");
    verifyEligibilityService.requestServiceWithParam(emirateId);
    Assert.assertFalse(verifyEligibilityService.getresponse(verifyEligibilityService).application.isEligible);
    Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).application.hasDataIssues,false);
    Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).responseStatus.statusCode,200);

}
public void verifyEligibilityBlacklisted() throws JsonProcessingException {
    logManager.STEP("Read Test Data from Source","");
    //TODO ExcelFileUtils
     String emirateId="";
     int count=0;
    CSVReader csvReader= null;
    try {
        csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
        String [] nextLine;
        while((nextLine= csvReader.readNext())!=null&& count<=4){
            count++;

            emirateId=nextLine[1];
        }


    } catch (IOException | CsvValidationException e) {
        e.printStackTrace();
    }

    logManager.STEP("The User Trigger Verify Eligibility Service blacked list","");
    verifyEligibilityService.requestServiceWithParam(emirateId);
    Assert.assertFalse(verifyEligibilityService.getresponse(verifyEligibilityService).application.isEligible);
    Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).application.hasDataIssues,false);
    Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).responseStatus.statusCode,200);
}
public void verifyEligibilityNonUAENational() throws JsonProcessingException {
    logManager.STEP("Read Test Data from Source","");
    //TODO ExcelFileUtils
    String emirateId="";
    int count=0;
    CSVReader csvReader= null;
    try {
        csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
        String [] nextLine;
        while((nextLine= csvReader.readNext())!=null&& count<=5){
            count++;

            emirateId=nextLine[1];
        }


    } catch (IOException | CsvValidationException e) {
        e.printStackTrace();
    }

    logManager.STEP("The User Trigger Verify Eligibility Service non UAE National","");
    verifyEligibilityService.requestServiceWithParam(emirateId);
    Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).responseStatus.statusCode,200);

    Assert.assertFalse(verifyEligibilityService.getresponse(verifyEligibilityService).application.isEligible);
    Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).application.hasDataIssues,false);

}
    public void verifyEligibilityADNationalHoFb() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source","");
        //TODO ExcelFileUtils
        String emirateId="";
        int count=0;
        CSVReader csvReader= null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String [] nextLine;
            while((nextLine= csvReader.readNext())!=null&& count<=6){
                count++;

                emirateId=nextLine[1];
            }


        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        logManager.STEP("The User Trigger Verify Eligibility Service AD National HO FB","");
        verifyEligibilityService.requestServiceWithParam(emirateId);
        Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).responseStatus.statusCode,200);

        Assert.assertTrue(verifyEligibilityService.getresponse(verifyEligibilityService).application.isEligible);
        Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).application.hasDataIssues,false);

    }
    public void verifyEligibilityDeceasedHoFB() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source","");
        //TODO ExcelFileUtils
        String emirateId="";
        int count=0;
        CSVReader csvReader= null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String [] nextLine;
            while((nextLine= csvReader.readNext())!=null&& count<=7){
                count++;

                emirateId=nextLine[1];
            }


        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        logManager.STEP("The User Trigger Verify Eligibility Service DeceasedHoFB","");
        verifyEligibilityService.requestServiceWithParam(emirateId);
        Assert.assertTrue(verifyEligibilityService.getresponse(verifyEligibilityService).application.isEligible);
        Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).application.hasDataIssues,false);
        Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).responseStatus.statusCode,200);

    }
    public void verifyEligibilityNonHeadOfFamilyBook() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source","");
        //TODO ExcelFileUtils
        String emirateId="";
        int count=0;
        CSVReader csvReader= null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String [] nextLine;
            while((nextLine= csvReader.readNext())!=null&& count<=8){
                count++;

                emirateId=nextLine[1];
            }


        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        logManager.STEP("The User Trigger Verify Eligibility Service NonHeadOfFamilyBook","");
        verifyEligibilityService.requestServiceWithParam(emirateId);
        Assert.assertFalse(verifyEligibilityService.getresponse(verifyEligibilityService).application.isEligible);
        Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).application.hasDataIssues,false);
        Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).responseStatus.statusCode,200);
    }
    public void verifyEligibilityDeceasedApplicant() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source","");
        //TODO ExcelFileUtils
        String emirateId="";
        int count=0;
        CSVReader csvReader= null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String [] nextLine;
            while((nextLine= csvReader.readNext())!=null&& count<=97){
                count++;

                emirateId=nextLine[1];
            }


        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        logManager.STEP("The User Trigger Verify Eligibility Service DeceasedApplicant","");
        verifyEligibilityService.requestServiceWithParam(emirateId);
        Assert.assertFalse(verifyEligibilityService.getresponse(verifyEligibilityService).application.isEligible);
        Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).application.hasDataIssues,false);
        Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).responseStatus.statusCode,200);
    }
    public void verifyEligibilityNonADFamilyBook() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source","");
        //TODO ExcelFileUtils
        String emirateId="";
        int count=0;
        CSVReader csvReader= null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String [] nextLine;
            while((nextLine= csvReader.readNext())!=null&& count<=98){
                count++;

                emirateId=nextLine[1];
            }


        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        logManager.STEP("The User Trigger Verify Eligibility Service NonADFamilyBook","");
        verifyEligibilityService.requestServiceWithParam(emirateId);
        Assert.assertFalse(verifyEligibilityService.getresponse(verifyEligibilityService).application.isEligible);
        Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).application.hasDataIssues,false);
        Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).responseStatus.statusCode,200);    }
    public void verifyEligibilityMultipleWidows() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source","");
        //TODO ExcelFileUtils
        String emirateId="";
        int count=0;
        CSVReader csvReader= null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String [] nextLine;
            while((nextLine= csvReader.readNext())!=null&& count<=99){
                count++;

                emirateId=nextLine[1];
            }


        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        logManager.STEP("The User Trigger Verify Eligibility Service MultipleWidows","");
        verifyEligibilityService.requestServiceWithParam(emirateId);
        Assert.assertFalse(verifyEligibilityService.getresponse(verifyEligibilityService).application.isEligible);
        Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).application.hasDataIssues,false);
        Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).responseStatus.statusCode,200);    }
}




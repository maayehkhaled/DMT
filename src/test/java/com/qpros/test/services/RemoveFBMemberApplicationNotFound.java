package com.qpros.test.services;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.qpros.common.web.Base;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.service.CancelApplication;
import com.ssa.core.service.RemoveFBMember;
import com.ssa.core.service.VerifyEligibilityService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

@Listeners(com.qpros.common.LogManager.class)
public class RemoveFBMemberApplicationNotFound extends Base {


    VerifyEligibilityService verifyEligibilityService = new VerifyEligibilityService();
    RemoveFBMember removeFBMember=new RemoveFBMember();
    CancelApplication cancelApp=new CancelApplication();
    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());
    }

    @BeforeMethod(enabled = false)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser(true);
    }



    @Test(description = "remove Fb member app not found", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void removeFBMemberApplicationNotFound() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source","");
        //TODO ExcelFileUtils
        String emirateId="";
        int count=0;
        CSVReader csvReader= null;
        try {
             csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String [] nextLine;
            while((nextLine= csvReader.readNext())!=null&& count<=15){
                count++;
                emirateId=nextLine[1];
            }


        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }


        logManager.STEP("remove Fb member app not found","");
        removeFBMember.requestServiceWithEid(emirateId);
        /*Assert.assertEquals(removeFBMember.getresponse(removeFBMember).application.hasDataIssues,true);
        Assert.assertEquals(removeFBMember.getresponse(removeFBMember).responseStatus.statusCode,200);*/
    }

}



package com.qpros.test.restfull12;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.qpros.common.web.Base;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.service.CancelApplication;
import com.ssa.core.service.GetFamilyData;
import com.ssa.core.service.VerifyEligibilityService;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

@Listeners(com.qpros.common.LogManager.class)

public class GetFamilyDataModelConfirmed extends Base{

    VerifyEligibilityService verifyEligibilityService = new VerifyEligibilityService();
    GetFamilyData getFamilyData=new GetFamilyData();
    CancelApplication cancelApplication=new CancelApplication();

    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());
    }

    @BeforeMethod(enabled = false)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser();
    }

    @SneakyThrows
    @Test(description = "Get Data Of Confirmed Family", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void getFamily()  {
        logManager.STEP("Read Test Data from Source","");
        //TODO ExcelFileUtils
        String emirateId="";
        int count=0;
        CSVReader csvReader= null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String [] nextLine;
            while((nextLine= csvReader.readNext())!=null&& count<=10){
                count++;
                emirateId=nextLine[1];
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        logManager.STEP("The User Trigger Verify Eligibility Service","");
        verifyEligibilityService.requestServiceWithParam(emirateId);
        Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).responseStatus.statusCode,200);
        //Contains familybook ??

        Assert.assertFalse(verifyEligibilityService.getresponse(verifyEligibilityService).application.hasDataIssues);
        Assert.assertTrue(verifyEligibilityService.getresponse(verifyEligibilityService).application.isEligible);

        logManager.STEP("Get Data Of Confirmed Family","");
        getFamilyData.requestServiceWithParam(emirateId);
        //200 ??
        //Contains familybooknumber ??
        //$.Household[0].IsRemoved false ??
        //$.Application.HasDataIssues false ??

        logManager.STEP("Cancel Family Application","");
        cancelApplication.requestServiceWithParam(emirateId);
        Assert.assertEquals(cancelApplication.getresponse(cancelApplication).responseStatus.statusCode,200);
        Assert.assertTrue(cancelApplication.getresponse(cancelApplication).responseStatus.message.contains("Success"));
    }
}

package com.qpros.test.restfull12;
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
public class AddFamilyMember extends Base {


    VerifyEligibilityService verifyEligibilityService = new VerifyEligibilityService();
    AddNewMember addMember=new AddNewMember();
    CancelApplication cancelApp=new CancelApplication();
    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());
    }

    @BeforeMethod(enabled = false)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser();
    }

    @Test(description = "Confirm Single Addition", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void confirmSingleAddition() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source","");
        //TODO ExcelFileUtils
        String emirateId="";
        int count=0;
        CSVReader csvReader= null;
        try {
             csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String [] nextLine;
            while((nextLine= csvReader.readNext())!=null&& count<=0){
                count++;
                emirateId=nextLine[1];
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        logManager.STEP("The User Trigger Verify Eligibility Service","");
        verifyEligibilityService.requestServiceWithParam(emirateId);
        Assert.assertTrue(verifyEligibilityService.getresponse(verifyEligibilityService).application.isEligible);
        Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).headOfFamilyBook.emiratesId,emirateId);
        logManager.STEP("Add new member","");
        addMember.requestServiceWithParam(emirateId);
        addMember.getResponse(addMember);
        logManager.STEP("Cancel application","");
        cancelApp.requestServiceWithParam(emirateId);
        cancelApp.getresponse(cancelApp);
    }
}


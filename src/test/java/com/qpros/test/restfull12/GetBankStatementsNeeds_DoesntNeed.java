package com.qpros.test.restfull12;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.qpros.common.web.Base;
import com.ssa.core.service.CancelApplication;
import com.ssa.core.service.GetBankStatementsNeeds;
import com.ssa.core.service.UpdateSelfDeclaredBankStatements;
import com.ssa.core.service.VerifyEligibilityService;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

@Listeners(com.qpros.common.LogManager.class)
public class GetBankStatementsNeeds_DoesntNeed extends Base {
    VerifyEligibilityService verifyEligibilityService=new VerifyEligibilityService();
    GetBankStatementsNeeds getBankStatementsNeeds=new GetBankStatementsNeeds();
    UpdateSelfDeclaredBankStatements updateSelfDeclaredBankStatements=new UpdateSelfDeclaredBankStatements();
    CancelApplication cancelApp=new CancelApplication();

    @Test(description = "Verify Eligibility", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void verifyEligibility() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        //
        String emirateId = "";
        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 117) {
                count++;
                emirateId = nextLine[1];
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        logManager.STEP("The User Trigger Verify Eligibility Service", "");
        verifyEligibilityService.requestServiceWithParam(emirateId);
        Assert.assertEquals(verifyEligibilityService.getresponse(verifyEligibilityService).responseStatus.statusCode,200);
        //contains: familybook
        Assert.assertTrue(verifyEligibilityService.getresponse(verifyEligibilityService).claimant.familyBookEmirateEN.contains("familybook"));
    }

    @Test(description = "GetBankStatementsNeeds_ApplicationNotFound", priority = 2,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void GetBankStatementsNeeds_NotFound() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        //
        String emirateId = "";
        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 117) {
                count++;
                emirateId = nextLine[1];
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        logManager.STEP("The User Trigger Get Bank Statements Needs Service", "");
        getBankStatementsNeeds.requestServiceWithEid(emirateId);
        getBankStatementsNeeds.getresponse(getBankStatementsNeeds);

        //Contains have an existing application

        //status 200

        //$.BankStatements.NeedsBankStatements :false
    }
    //UpdateSelfDeclaredBankStatements_ApplicationNotFound
    @Test(description = "Update Self Declared Bank Statements_Application Not Found", priority = 3,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void UpdateSelfDeclaredBankStatements() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        //
        String emirateId = "";
        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 117) {
                count++;
                emirateId = nextLine[1];
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        updateSelfDeclaredBankStatements.requestServiceWithParam(emirateId);
        updateSelfDeclaredBankStatements.getResponse(updateSelfDeclaredBankStatements);
        //200
        //contains success
    }
    //CancelApplication_Successfully
    @Test(description = "Cancel Application_Successfully", priority = 4,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void CancelApplication_Successfully() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        //
        String emirateId = "";
        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 103) {
                count++;
                emirateId = nextLine[1];
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        logManager.STEP("Cancel Application_Successfully", "");
        cancelApp.requestServiceWithParam(emirateId);
        cancelApp.getresponse(cancelApp);
        Assert.assertEquals(cancelApp.getresponse(cancelApp).responseStatus.statusCode,200);
        Assert.assertTrue(cancelApp.getresponse(cancelApp).responseStatus.message.contains("Success"));
    }
}

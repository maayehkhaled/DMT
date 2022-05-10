package com.qpros.test.restfull12;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.qpros.common.web.Base;
import com.ssa.core.service.GetBankStatementsNeeds;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

@Listeners(com.qpros.common.LogManager.class)
public class GetBankStatementsNeeds_ApplicationNotFound extends Base {
    GetBankStatementsNeeds getBankStatementsNeeds=new GetBankStatementsNeeds();

    @Test(description = "GetBankStatementsNeeds_ApplicationNotFound", priority = 1,
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
            while ((nextLine = csvReader.readNext()) != null && count <= 91) {
                count++;
                emirateId = nextLine[1];
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        logManager.STEP("The User Trigger Get Bank Statements Needs Service", "");
        getBankStatementsNeeds.requestServiceWithEid(emirateId);
        getBankStatementsNeeds.getresponse(getBankStatementsNeeds);
        //status 200
        //$.BankStatements.NeedsBankStatements :false
        //Empty Expression & results
        //$.ClaimantList false
    }
}

package com.qpros.test.restfull12;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.qpros.common.web.Base;
import com.ssa.core.service.UpdateSelfDeclaredBankStatements;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

@Listeners(com.qpros.common.LogManager.class)
public class UpdateSelfDeclaredBankStatements_ApplicationNotFound extends Base {
    UpdateSelfDeclaredBankStatements updateSelfDeclaredBankStatements=new UpdateSelfDeclaredBankStatements();

    @Test(description = "Update Self Declared Bank Statements_Application Not Found", priority = 1,
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
            while ((nextLine = csvReader.readNext()) != null && count <= 92) {
                count++;
                emirateId = nextLine[1];
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        updateSelfDeclaredBankStatements.requestServiceWithParam(emirateId);
        updateSelfDeclaredBankStatements.getResponse(updateSelfDeclaredBankStatements);
        //200
        //could not retrieve
    }
}

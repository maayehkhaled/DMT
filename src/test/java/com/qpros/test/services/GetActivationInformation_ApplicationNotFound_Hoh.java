package com.qpros.test.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.qpros.common.web.Base;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.service.GetActivationInformation;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

@Listeners(com.qpros.common.LogManager.class)
public class GetActivationInformation_ApplicationNotFound_Hoh extends Base {
    GetActivationInformation getActivationInfo=new GetActivationInformation();

    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());
    }

    @BeforeMethod(enabled = false)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser(true);
    }

    @Test(description = "Get Activation Information", priority = 2, dependsOnMethods = "verifyEligibility",
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void getActivationInformation() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        String emirateId = "";
        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 78) {
                count++;
                emirateId = nextLine[1];
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        logManager.STEP("The User Trigger Get Activation Information Service", "");
        getActivationInfo.requestServiceWithParam(emirateId);
        getActivationInfo.getresponse(getActivationInfo);
    }
}

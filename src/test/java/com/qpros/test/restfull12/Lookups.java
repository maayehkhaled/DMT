package com.qpros.test.restfull12;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.qpros.common.web.Base;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.service.SSALookup;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

@Listeners(com.qpros.common.LogManager.class)
public class Lookups extends Base {

    SSALookup lookups=new SSALookup();

    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());
    }

    @BeforeMethod(enabled = false)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser();
    }

    @Test(description = "Get Lookup_Source Of Income", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void GetLookup_SourceOfIncome() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        String lookupName = "";
        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 42) {
                count++;
                lookupName = nextLine[1];
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        lookups.requestServiceWithParam("SocialSupportRequest","1",lookupName);
        Assert.assertEquals(lookups.getresponse(lookups).lookupTablesList.get(0).lookupTableName,"SourceOfIncome");
        Assert.assertEquals(lookups.getresponse(lookups).responseStatus.statusCode,200);
        //Assert.assertTrue((lookups.getresponse(lookups).lookupTablesList.get(0).lookupValueList.get(6).nameEN).contains("Private sector"));
//Assert.assertTrue((lookups.getresponse(lookups).lookupTablesList.get(0).lookupValueList.get(0).nameEN).contains());
//https://www.baeldung.com/java-stream-filter-lambda
    }

    @Test(description = "Get Lookup_Income Type", priority = 2,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void GetLookup_IncomeType() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        String lookupName = "";
        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 41) {
                count++;
                lookupName = nextLine[1];
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        lookups.requestServiceWithParam("SocialSupportRequest","1",lookupName);
        Assert.assertEquals(lookups.getresponse(lookups).lookupTablesList.get(0).lookupTableName,"IncomeType");
    }

    @Test(description = "Get Lookup_Wealth Type", priority = 3,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void GetLookup_WealthType() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        String lookupName = "";
        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 43) {
                count++;
                lookupName = nextLine[1];
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        lookups.requestServiceWithParam("SocialSupportRequest","1",lookupName);
        Assert.assertEquals(lookups.getresponse(lookups).lookupTablesList.get(0).lookupTableName,"WealthType");
    }

    @Test(description = "Get Lookup_Relationship", priority = 4,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void GetLookup_Relationship() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        String lookupName = "";
        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 44) {
                count++;
                lookupName = nextLine[1];
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        lookups.requestServiceWithParam("SocialSupportRequest","1",lookupName);
        Assert.assertEquals(lookups.getresponse(lookups).lookupTablesList.get(0).lookupTableName,"Relationship");
    }

    @Test(description = "Get Lookup_Removal Reason", priority = 5,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void GetLookup_RemovalReason() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        String lookupName = "";
        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 45) {
                count++;
                lookupName = nextLine[1];
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        lookups.requestServiceWithParam("SocialSupportRequest","1",lookupName);
        Assert.assertEquals(lookups.getresponse(lookups).lookupTablesList.get(0).lookupTableName,"RemovalReason");
        System.out.println(lookupName);
    }

    @Test(description = "Get Lookup_Municipality", priority = 6,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void GetLookup_Municipality() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        String lookupName = "";
        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 46) {
                count++;
                lookupName = nextLine[1];
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        lookups.requestServiceWithParam("SocialSupportRequest","1",lookupName);
        Assert.assertEquals(lookups.getresponse(lookups).lookupTablesList.get(0).lookupTableName,"Municipality");
    }

    @Test(description = "Get Lookup_District", priority = 7,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void GetLookup_District() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        String lookupName = "";
        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 47) {
                count++;
                lookupName = nextLine[1];
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        lookups.requestServiceWithParam("SocialSupportRequest","1",lookupName);
        Assert.assertEquals(lookups.getresponse(lookups).lookupTablesList.get(0).lookupTableName,"District");
    }

    @Test(description = "Get Lookup_Community", priority = 8,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void GetLookup_Community() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        String lookupName = "";
        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 48) {
                count++;
                lookupName = nextLine[1];
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        lookups.requestServiceWithParam("SocialSupportRequest","1",lookupName);
        Assert.assertEquals(lookups.getresponse(lookups).lookupTablesList.get(0).lookupTableName,"Community");
    }

    @Test(description = "Get Lookup_Income Frequency", priority = 9,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void GetLookup_IncomeFrequency() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        String lookupName = "";
        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 49) {
                count++;
                lookupName = nextLine[1];
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        lookups.requestServiceWithParam("SocialSupportRequest","1",lookupName);
        Assert.assertEquals(lookups.getresponse(lookups).lookupTablesList.get(0).lookupTableName,"IncomeFrequency");
    }

    @Test(description = "Get Lookup_Care Type", priority = 10,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void GetLookup_CareType() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        String lookupName = "";
        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 50) {
                count++;
                lookupName = nextLine[1];
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        lookups.requestServiceWithParam("SocialSupportRequest","1",lookupName);
        Assert.assertEquals(lookups.getresponse(lookups).lookupTablesList.get(0).lookupTableName,"CareType");
    }

    @Test(description = "Get Lookup_Education Level", priority = 11,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void GetLookup_EducationLevel() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        String lookupName = "";
        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 51) {
                count++;
                lookupName = nextLine[1];
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        lookups.requestServiceWithParam("SocialSupportRequest","1",lookupName);
        Assert.assertEquals(lookups.getresponse(lookups).lookupTablesList.get(0).lookupTableName,"EducationLevel");
    }

    @Test(description = "Get Lookup_English Certificate", priority = 12,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void GetLookup_EnglishCertificate() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        String lookupName = "";
        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 52) {
                count++;
                lookupName = nextLine[1];
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        lookups.requestServiceWithParam("SocialSupportRequest","1",lookupName);
        Assert.assertEquals(lookups.getresponse(lookups).lookupTablesList.get(0).lookupTableName,"EnglishCertificate");
    }

    @Test(description = "Get Lookup_Experience Type", priority = 13,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void GetLookup_ExperienceType() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        String lookupName = "";
        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 53) {
                count++;
                lookupName = nextLine[1];
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        lookups.requestServiceWithParam("SocialSupportRequest","1",lookupName);
        Assert.assertEquals(lookups.getresponse(lookups).lookupTablesList.get(0).lookupTableName,"ExperienceType");
    }

    @Test(description = "Get Lookup_NService Status", priority = 14,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void GetLookup_NServiceStatus() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        String lookupName = "";
        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 54) {
                count++;
                lookupName = nextLine[1];
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        lookups.requestServiceWithParam("SocialSupportRequest","1",lookupName);
        Assert.assertEquals(lookups.getresponse(lookups).lookupTablesList.get(0).lookupTableName,"NServiceStatus");
    }

    @Test(description = "Get Lookup_Living On Other", priority = 15,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void GetLookup_LivingOnOther() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        String lookupName = "";
        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 55) {
                count++;
                lookupName = nextLine[1];
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        lookups.requestServiceWithParam("SocialSupportRequest","1",lookupName);
        Assert.assertEquals(lookups.getresponse(lookups).lookupTablesList.get(0).lookupTableName,"LivingOnOther");
    }

    @Test(description = "Get Lookup_Legal Disclaimer", priority = 16,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void GetLookup_LegalDisclaimer() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        String lookupName = "";
        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 56) {
                count++;
                lookupName = nextLine[1];
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        lookups.requestServiceWithParam("SocialSupportRequest","1",lookupName);
        Assert.assertEquals(lookups.getresponse(lookups).lookupTablesList.get(0).lookupTableName,"LegalDisclaimer");
    }

    @Test(description = "Get Lookup_WE Company", priority = 17,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void GetLookup_WECompany() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        String lookupName = "";
        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 57) {
                count++;
                lookupName = nextLine[1];
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        lookups.requestServiceWithParam("SocialSupportRequest","1",lookupName);
        Assert.assertEquals(lookups.getresponse(lookups).lookupTablesList.get(0).lookupTableName,"WECompany");
    }

    @Test(description = "Get Lookup_Area Of Study", priority = 18,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void GetLookup_AreaOfStudy() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        String lookupName = "";
        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 58) {
                count++;
                lookupName = nextLine[1];
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        lookups.requestServiceWithParam("SocialSupportRequest","1",lookupName);
        Assert.assertEquals(lookups.getresponse(lookups).lookupTablesList.get(0).lookupTableName,"AreaOfStudy");
    }

    @Test(description = "Get Lookup_Specialization", priority = 19,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void GetLookup_Specialization() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        String lookupName = "";
        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 59) {
                count++;
                lookupName = nextLine[1];
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        lookups.requestServiceWithParam("SocialSupportRequest","555",lookupName);
        Assert.assertEquals(lookups.getresponse(lookups).lookupTablesList.get(0).lookupTableName,"Specialization");
    }

    @Test(description = "Get Lookup_Application Type", priority = 20,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void GetLookup_ApplicationType() throws JsonProcessingException {
        logManager.STEP("Read Test Data from Source", "");
        String lookupName = "";
        int count = 0;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("src/main/resources/DataProvider/data.csv"));
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null && count <= 60) {
                count++;
                lookupName = nextLine[1];
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        lookups.requestServiceWithParam("SocialSupportRequest","555",lookupName);
        Assert.assertEquals(lookups.getresponse(lookups).lookupTablesList.get(0).lookupTableName,"ApplicationType");
    }

    @Test(description = "Get Lookup_Social Support Request", priority = 21,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void GetLookup_SocialSupportRequest() throws JsonProcessingException {

        lookups.requestServiceWithParam("SocialSupportRequest","","");
        lookups.getresponse(lookups);
    }

    @Test(description = "Get Lookup_Missing Parameter", priority = 22,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void GetLookup_MissingParameter() throws JsonProcessingException {

        lookups.requestServiceWithParam("","20","ApplicationType");
        Assert.assertEquals(lookups.getresponse(lookups).responseStatus.message,"Missing Parameter");
    }

    @Test(description = "Get Lookup_Empty Stage And Example Request", priority = 23,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"API"})
    public void GetLookup_EmptyStageAndExampleRequest() throws JsonProcessingException {

        lookups.requestServiceWithParam("SocialSupportRequest","","ApplicationType");
        Assert.assertEquals(lookups.getresponse(lookups).responseStatus.message,"Success");
    }
}

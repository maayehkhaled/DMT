package com.qpros.test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.qpros.common.web.Base;
import com.qpros.pages.web.SSA.*;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.common.locators.urls;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Listeners(com.qpros.common.LogManager.class)

public class ExceptionalCase extends Base{
    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());
    }

    @BeforeMethod(enabled = true)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser();
    }

    LoginPage loginPage = new LoginPage(driver.get());
    ExceptionalCasePage exceptionalPage=new ExceptionalCasePage(driver.get());

    public void startPage(){
        driver.get().navigate().to(urls.agentLogin);
        loginPage.loginWithUser(UserType.Specialist2);
        //exceptionalPage.chooseReferralEntity();
    }

    @Test(description = "Open Exceptional case", priority = 2,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void validateOpenExceptionalCase() {
        driver.get().navigate().to(urls.agentLogin);
        loginPage.loginWithUser(UserType.Specialist2);
        exceptionalPage.openExceptionalCase();
    }

    @Test(description = "Validate More Than 500 Chars", priority = 11,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void validateMaxChar() {
        startPage();
        //ChooseFromDDLexceptionalPage.openExceptionalCase();
        Assert.assertEquals(exceptionalPage.countMaxChars(),500);
    }

    @Test(description = "Validate Less Than 500 Chars", priority = 3,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void validateMinChar() {
        startPage();
        //ChooseFromDDLexceptionalPage.openExceptionalCase();
        Assert.assertTrue(exceptionalPage.countMinChars()<500);
    }

    @Test(description = "Enter wrong EID for HOH and wrong date", priority = 4,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void validateEnterWrongData() {
    startPage();
    exceptionalPage.chooseReferralEntity();
    exceptionalPage.enterHeadOfFamilyData("000-0000-0000000-0",formatDateTime+ Keys.ENTER);
    Assert.assertEquals(exceptionalPage.getValidationMsg(),"إذا كانت المعلومات أعلاه غير صحيحة، يرجى التواصل مع الهيئة الاتحادية للهوية والجنسية بشأن خلاصة القيد.");
    }

    @Test(description = "Enter correct EID for HOH and correct date", priority = 5,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void validateEnterCorrectData() {
        startPage();
        exceptionalPage.chooseReferralEntity();
        exceptionalPage.enterHeadOfFamilyData("784-1991-4064300-0","01/01/1937");//enterCorrectData
        Assert.assertEquals(exceptionalPage.getValidationMsg(),"تم التحقق من رقم الهوية الإماراتية وتاريخ الميلاد");
    }

    @Test(description = "Enter Email with invalid format", priority = 6,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void invalidHOFEmail() {
        startPage();
        exceptionalPage.chooseReferralEntity();
        exceptionalPage.enterHeadOfFamilyData("784-1991-4064300-0","01/01/1937");
        Assert.assertEquals(exceptionalPage.invalidHOFEmail(),"بريد إلكتروني خاطئ");
    }

    @Test(description = "Enter valid Email format and delete mobile number", priority =7,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void validHOFEmail() {
        startPage();
        exceptionalPage.chooseReferralEntity();
        exceptionalPage.enterHeadOfFamilyData("784-1991-4064300-0","01/01/1937");
        exceptionalPage.enterValidHOFEmail();
        Assert.assertEquals(exceptionalPage.enterValidHOFEmail(),"رقم الجوال غير صالح. يجب أن يكون رقمًا إماراتيًا صالحًا به 7 أرقام وأحد البادئات التالية 050, 052, 055, 056, 054, 058");
    }

    @Test(description = "Enter Relatives EID and DOB", priority = 8,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void validateAddRelatives(){
        startPage();
        exceptionalPage.enterHeadOfFamilyData("784-1991-4064300-0","01/01/1937");
        exceptionalPage.chooseReferralEntity();
        //exceptionalPage.enterValidHOFEmail();
        exceptionalPage.addRelatives();
    }
    LocalDateTime datetime1 = LocalDateTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String formatDateTime = datetime1.format(format);

    @Test(description = "Enter Relatives EID and DOB", priority = 9,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void validateAddWrongRelativeInfo(){
        startPage();
        exceptionalPage.chooseReferralEntity();
        exceptionalPage.enterHeadOfFamilyData("784-1991-4064300-0",formatDateTime+ Keys.ENTER);
        exceptionalPage.addWrongRelativeInfo();
        Assert.assertEquals(exceptionalPage.validationMsgForWrongRelative(),"إذا كانت المعلومات أعلاه غير صحيحة، يرجى التواصل مع الهيئة الاتحادية للهوية والجنسية بشأن خلاصة القيد.");
    }

    @Test(description = "Click Delete The Wrong Relative Data", priority = 10,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void validateDeleteWrongDependent(){
        startPage();
        exceptionalPage.chooseReferralEntity();
        exceptionalPage.enterHeadOfFamilyData("784-1991-4064300-0","01/01/1937");
        exceptionalPage.addWrongRelativeInfo();
        exceptionalPage.deleteWrongDependent();
    }

    @Test(description = "Create Full Exceptional Case", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void validateCreateFullExceptionalCase() throws AWTException, InterruptedException, JsonProcessingException {
        startPage();
        exceptionalPage.chooseReferralEntity();
        exceptionalPage.enterHeadOfFamilyData("784-1991-4063300-0","21/03/1988");
        exceptionalPage.createFullExceptionalCase();
        exceptionalPage.searchForId();
        exceptionalPage.completeApprovals();
    }

    @Test(description = "search EID", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void searchEID() throws AWTException, InterruptedException, JsonProcessingException {
        startPage();
        exceptionalPage.searchForId();
    }
}

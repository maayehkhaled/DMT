package com.qpros.test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.qpros.common.web.Base;
import com.qpros.pages.web.SSA.*;
import com.qpros.pages.web.SSA.modules.ApproveApplicationModule;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.common.data.TestData;
import com.ssa.core.common.locators.urls;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import java.io.IOException;
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
    ApproveApplicationModule approve=new ApproveApplicationModule(driver.get());
    ExceptionalCasePage exceptionalPage=new ExceptionalCasePage(driver.get());

    public void startPage(){
        driver.get().navigate().to(urls.agentLogin);
        loginPage.loginWithUser(UserType.Specialist2);
    }
    @Test(description = "Enter correct EID for HOH and correct date", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void validateEnterCorrectData() throws JsonProcessingException {
        startPage();
        exceptionalPage.chooseReferralEntity();
        exceptionalPage.enterHeadOfFamilyData(TestData.EID,"16/07/1983");
        exceptionalPage.createFullExceptionalCase();
    }

    @Test(description = "Specialist Approval", priority = 2,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void validateApprovals()  {
        startPage();
        exceptionalPage.searchForId();
        exceptionalPage.completeApprovals();
    }

    @Test(description = "Senior Specialist Approval", priority = 3,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void validateSeniorSpecialistApprovals() {
        startPage();
        exceptionalPage.searchForId();
        exceptionalPage.completeSeniorSpecialistApprovals();
    }

    @Test(description = "Committee Approval", priority = 4,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void validateCommitteeApprovals()  {
        startPage();
        exceptionalPage.searchForId();
        exceptionalPage.completeCommitteeApproval();
    }

    @Test(description = "Committee Approval", priority = 5,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {""})
    public void validateSuperuserApprovals()  {
        startPage();
        exceptionalPage.searchForId();
        exceptionalPage.paymentSteps();
    }
    /*
    @Test(description = "Open Exceptional case", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void openExceptionalCase() {
        driver.get().navigate().to(urls.agentLogin);
        loginPage.loginWithUser(UserType.Specialist2);
        exceptionalPage.openExceptionalCase();
    }

    @Test(description = "Validate More Than 500 Chars", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void validateMaxChar() {
        startPage();
        exceptionalPage.openExceptionalCase();
        Assert.assertEquals(exceptionalPage.countMaxChars(),500);
    }

    @Test(description = "Validate Less Than 500 Chars", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void validateMinChar() {
        startPage();
        //ChooseFromDDLexceptionalPage.openExceptionalCase();
        Assert.assertTrue(exceptionalPage.countMinChars()<500);
    }

    @Test(description = "Enter wrong EID for HOH and wrong date", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void validateEnterWrongData() {
    startPage();
    exceptionalPage.chooseReferralEntity();
    exceptionalPage.enterHeadOfFamilyData("000-0000-0000000-0","01/01/1922");
    Assert.assertEquals(exceptionalPage.getValidationMsg(),"إذا كانت المعلومات أعلاه غير صحيحة، يرجى التواصل مع الهيئة الاتحادية للهوية والجنسية بشأن خلاصة القيد.");
    }

    @Test(description = "Enter correct EID for HOH and correct date", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void validateEnterCorrectData() {
        startPage();
        exceptionalPage.chooseReferralEntity();
        exceptionalPage.enterHeadOfFamilyData("784-1991-4064300-0","01/01/1937");//enterCorrectData
        Assert.assertEquals(exceptionalPage.getValidationMsg(),"تم التحقق من رقم الهوية الإماراتية وتاريخ الميلاد");
    }

    @Test(description = "Enter Email with invalid format", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void invalidHOFEmail() {
        startPage();
        exceptionalPage.chooseReferralEntity();
        exceptionalPage.enterHeadOfFamilyData("784-1991-4064300-0","01/01/1937");
        Assert.assertEquals(exceptionalPage.invalidHOFEmail(),"بريد إلكتروني خاطئ");
    }

    @Test(description = "Enter valid Email format and delete mobile number", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void validHOFEmail() {
        startPage();
        exceptionalPage.chooseReferralEntity();
        exceptionalPage.enterHeadOfFamilyData("784-1991-4064300-0","01/01/1937");
        exceptionalPage.enterValidHOFEmail();
        Assert.assertEquals(exceptionalPage.enterValidHOFEmail(),"رقم الجوال غير صالح. يجب أن يكون رقمًا إماراتيًا صالحًا به 7 أرقام وأحد البادئات التالية 050, 052, 055, 056, 054, 058");
    }

    @Test(description = "Enter Relatives EID and DOB", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void validateAddRelatives(){
        startPage();
        exceptionalPage.enterHeadOfFamilyData("784-1991-4064300-0","01/01/1937");
        exceptionalPage.chooseReferralEntity();
        //exceptionalPage.enterValidHOFEmail();
        exceptionalPage.addRelatives();
    }

    @Test(description = "Enter Relatives EID and DOB", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void validateAddWrongRelativeInfo(){
        startPage();
        exceptionalPage.chooseReferralEntity();
        exceptionalPage.enterHeadOfFamilyData("784-1991-4064300-0","01/01/1937");
        exceptionalPage.addWrongRelativeInfo();
        Assert.assertEquals(exceptionalPage.validationMsgForWrongRelative(),"إذا كانت المعلومات أعلاه غير صحيحة، يرجى التواصل مع الهيئة الاتحادية للهوية والجنسية بشأن خلاصة القيد.");
    }

    @Test(description = "Click Delete The Wrong Relative Data", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void validateDeleteWrongDependent(){
        startPage();
        exceptionalPage.chooseReferralEntity();
        exceptionalPage.enterHeadOfFamilyData("784-1991-4064300-0","01/01/1937");
        exceptionalPage.addWrongRelativeInfo();
        exceptionalPage.deleteWrongDependent();
    }*/
}

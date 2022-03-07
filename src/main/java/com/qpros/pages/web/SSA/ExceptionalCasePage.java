package com.qpros.pages.web.SSA;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.helpers.FileUtils;
import com.qpros.pages.web.SSA.commonSSA.Popups;
import com.ssa.core.common.data.TestData;
import com.ssa.core.common.locators.urls;
import com.ssa.core.service.DeleteEmirateId;
import com.ssa.core.service.SubmitApplicationService;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Getter
@Setter
public class ExceptionalCasePage extends Base{
    public ExceptionalCasePage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    public static String refCode;
    LocalDateTime datetime1 = LocalDateTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String formatDateTime = datetime1.format(format);
    Faker faker=new Faker(new Random(24));

    Popups PopupsPage=new Popups(driver.get());
    AgentPage agentPage=new AgentPage(driver.get());
    SubmitApplicationService submitApplicationService = new SubmitApplicationService();
    LoginPage loginPage = new LoginPage(driver.get());
    BusinessParametersPage businessParametersPage = new BusinessParametersPage(driver.get());
    PaymentSpecialistPage paymentSpecialistPage = new PaymentSpecialistPage(driver.get());
    DeleteEmirateId deleteId = new DeleteEmirateId();

    private By exceptionalCaseLink= By.xpath("//a[contains(@id,'wt160')]");
    private By addNewEntity=By.xpath("//input[contains(@id,'openPopup')]");
    private By referralEntityNameTextbox=By.xpath("//input[contains(@id,'ReferralEntityName')]");
    private By saveReferralNameBtn=By.xpath("//input[contains(@id,'SaveBtn')]");
    private By caseDescriptionTextarea=By.xpath("//textarea[contains(@id,'ReferralCaseInput')]");
    private By firstApprovalCalendar=By.xpath("//input[contains(@id,'wt558_block_wtInput_wtdt_PreliminaryApprovalDate')]");
    private By referralCalendar=By.xpath("//input[contains(@id,'wt354_block_wtInput_wtdt_DateOfRefferal')]");
    private By nextBtn=By.xpath("//input[contains(@id,'NextBtn')]");
    private By referralEntityDDL=By.xpath("//select[contains(@id,'ReferralEntityList')]");

    //Screen II
    private By hohEIDTextbox=By.xpath("//input[contains(@id,'HOHEmiratesID')]");
    private By dateOfBirthCalendar=By.xpath("//input[contains(@id,'wt578_block_wtInput_wtdt_DateOfBrith')]");
    private By validateDetailsBtn=By.xpath("//input[contains(@id,'ValidateDetailsBtn')]");
    private By validationMsg=By.xpath("//span[contains(@id,'wtSanitizedHtml3')]");

    //Screen III
    private By exceptionalCaseEmail=By.xpath("//input[contains(@id,'EmailInput')]");
    private By familyDetailsNetBtn=By.id("//input");
    private By wrongEmailMsgLbl=By.xpath("//span[contains(@id,'EmailInput')]");
    private By hOFmobileNo=By.xpath("//input[contains(@id,'MobileNumberInput')]");
    private By mobileValidationMsg=By.xpath("//span[contains(@id,'MobileNumberInput')]");

    //Dependent Screen IV
    private By firstDependentEIDTextbox =By.xpath("//input[contains(@id,'ctl00_wttxt_HOHEmiratesID2')]");
    private By dependentDOB=By.xpath("//input[contains(@id,'ctl00_OutSystemsUIWeb_wt532_block_wtInput_wtdt_DateOfBrith2')]");
    private By relationDDL=By.xpath("//select[contains(@id,'ctl00_wtddl_ReferralEntityList2')]"); //4
    private By addAnotherDependentBtn=By.xpath("//input[contains(@id,'AddDependentBtn')]");
    private By validateDependentBtn=By.xpath("//input[contains(@id,'ValidateDependentDetail')]");
    private By validationDependentMsgLbl=By.className("Text_darkRed");
    private By deleteWrongDependent=By.xpath("//a[contains(@id,'wt550')]");
    private By secondDependentEIDTextbox=By.xpath("//input[contains(@id,'ctl02_wttxt_HOHEmiratesID2')]");
    private By secondDependentDOB=By.xpath("//input[contains(@id,'ctl02_OutSystemsUIWeb_wt532_block_wtInput_wtdt_DateOfBrith2')]");
    private By secondDependentRelationDDL=By.xpath("//select[contains(@id,'ctl02_wtddl_ReferralEntityList2')]");
    private By deleteFirstDependent=By.xpath("//a[contains(@id,'ctl00_wt551')]");
    private By deleteSecondDependent=By.xpath("//a[contains(@id,'ctl02_wt551')]");
    //Screen V
    private By familyResidentDDL=By.xpath("//select[contains(@name,'LivingOnLocationList3')]");
    //InternalPortalTheme_wt24_block_wtMainContent_wtddl_LivingOnLocationList
    private By supportDocsLink=By.className("dottedBorder");
    private By uploadMoreSupportDocs=By.xpath("//input[contains(@id,'Upload201200')]");
    private By firstFileDescription=By.xpath("//input[contains(@id,'ctl00_wttxt_Comment')]");
    private By secondFileDescription=By.xpath("//input[contains(@id,'ctl02_wttxt_Comment')]");

    //Files screen
    private By saveFilesBtn=By.xpath("//input[contains(@id,'wtbtn_save')]");
    private By editFileDescription=By.xpath("//a[contains(@id,'ctl03_wtlnk_EditComment')]");

    //All list page
    private By allListLink=By.xpath("//a[contains(@id,'wtMenuItem_wt12')]");
    private By searchForIdTextbox=By.xpath("//input[contains(@id,'SearchFrom')]");
    private By allListTbl=By.xpath("//table[contains(@id,'wtBenefitRequestsList')]");
    private By appIdLink=By.xpath("//a[contains(@id,'ApplicationReview')]");
    private By assignedToLbl=By.className("display-block");
    private By previousViewLink=By.xpath("//a[contains(@id,'wt57')]");

    public void openExceptionalCase(){
        logManager.STEP("Add New referral Entity","The user add new referral entity");
        //faker=new Faker();
        ActionsHelper.retryClick(exceptionalCaseLink,30);
        ActionsHelper.driverWait(2000);
       /* ActionsHelper.retryClick(addNewEntity,30);
        ActionsHelper.driverWait(2000);
        driver.get().switchTo().frame(0);
        ActionsHelper.sendKeys(referralEntityNameTextbox,faker.lorem().sentence(1));
        ActionsHelper.retryClick(saveReferralNameBtn,30);*/
        ActionsHelper.selectOption(referralEntityDDL,"83");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(caseDescriptionTextarea,"description here");
        ActionsHelper.driverWait(3000);
        ActionsHelper.sendKeys(firstApprovalCalendar,formatDateTime+ Keys.ENTER);
        ActionsHelper.driverWait(4000);
        ActionsHelper.sendKeys(referralCalendar,formatDateTime+ Keys.ENTER);
        ActionsHelper.driverWait(4000);
        ActionsHelper.retryClick(nextBtn,30);
        ActionsHelper.driverWait(2000);
    }

    public void chooseReferralEntity() throws JsonProcessingException {
        deleteId.requestService();
        logManager.STEP("Choose From Referral DDL", "The user chooses  one referral entity to start the exceptional request");
        ActionsHelper.retryClick(exceptionalCaseLink,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(referralEntityDDL,"74");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(caseDescriptionTextarea,"Automation case description");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(firstApprovalCalendar,formatDateTime+ Keys.ENTER);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(referralCalendar,formatDateTime+ Keys.ENTER);
        ActionsHelper.driverWait(4000);
        ActionsHelper.retryClick(nextBtn,30);
        ActionsHelper.driverWait(4000);
    }

    public void enterHeadOfFamilyData(String eid, String birthDate){
        logManager.STEP("Enter Head OF Family EID and DOB","The user enters HOF Data");
        ActionsHelper.clickAction(hohEIDTextbox);
        ActionsHelper.sendKeys(hohEIDTextbox,eid);
        ActionsHelper.driverWait(3000);
        ActionsHelper.sendKeys(dateOfBirthCalendar,birthDate);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(validateDetailsBtn,30);
        ActionsHelper.driverWait(8000);
    }
    public String getValidationMsg(){
        return driver.get().findElement(validationMsg).getText();
    }

    public int countMaxChars(){
        faker=new Faker();
        logManager.STEP("Add case description more than 500 chars","The system not accepts more than 500 chars in case description textbox");
        ActionsHelper.retryClick(exceptionalCaseLink,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(referralEntityDDL,"74");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(caseDescriptionTextarea,faker.lorem().sentence(10));
        ActionsHelper.driverWait(2000);
        String s1=(driver.get().findElement(caseDescriptionTextarea)).getAttribute("value");
        return s1.length();
    }
    public int countMinChars(){
        faker=new Faker();
        logManager.STEP("Add case description less than 500 chars","The system accepts less than 500 chars in case description textbox");
        ActionsHelper.retryClick(exceptionalCaseLink,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(referralEntityDDL,"74");
        ActionsHelper.sendKeys(caseDescriptionTextarea,faker.lorem().sentence(5));
        ActionsHelper.driverWait(2000);
        String s1=(driver.get().findElement(caseDescriptionTextarea)).getAttribute("value");
        return s1.length();
    }

    public String invalidHOFEmail(){
        logManager.STEP("Enter Invalid Email","The user enter Invalid email for head of family");
        ActionsHelper.sendKeys(exceptionalCaseEmail,"123HI");
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(familyDetailsNetBtn,30);
        ActionsHelper.driverWait(2000);
        return driver.get().findElement(wrongEmailMsgLbl).getText();
        //return driver.get().findElement(validationMsg).getText();
    }

    public String enterValidHOFEmail() {
        logManager.STEP("Enter Valid Email","The user enter valid email for head of family");
        ActionsHelper.scrollTo(hOFmobileNo);
        driver.get().findElement(hOFmobileNo).clear();
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeysWithClear(exceptionalCaseEmail, "yosra.sawi@gmail.com");
        ActionsHelper.driverWait(2000);
        //ActionsHelper.retryClick(familyDetailsNetBtn, 30);
        //ActionsHelper.driverWait(2000);
        return driver.get().findElement(mobileValidationMsg).getText();
    }

    public void addRelatives() {
            logManager.STEP("Add Correct Data For Relatives","The user add correct EID and DOB for the relatives");
            ActionsHelper.driverWait(4000);
            ActionsHelper.clickAction(firstDependentEIDTextbox);
            ActionsHelper.sendKeys(firstDependentEIDTextbox, "784-1991-4063100-5");
            ActionsHelper.driverWait(2000);
            ActionsHelper.sendKeys(dependentDOB, "01/08/2018");
            ActionsHelper.driverWait(2000);
            ActionsHelper.selectOption(relationDDL, "3");
            ActionsHelper.driverWait(2000);
            ActionsHelper.retryClick(validateDependentBtn, 30);
            ActionsHelper.driverWait(5000);
            ActionsHelper.retryClick(addAnotherDependentBtn, 30);
            ActionsHelper.driverWait(4000);
            ActionsHelper.waitUntilElementIsDisplayed(secondDependentEIDTextbox);
            ActionsHelper.clickAction(secondDependentEIDTextbox);
            ActionsHelper.sendKeys(secondDependentEIDTextbox, "784-1991-4063100-2");
            ActionsHelper.driverWait(2000);
            ActionsHelper.sendKeys(secondDependentDOB, "22/06/1988");
            ActionsHelper.driverWait(2000);
            ActionsHelper.selectOption(secondDependentRelationDDL, "2");
            ActionsHelper.driverWait(2000);
            ActionsHelper.retryClick(validateDependentBtn, 30);
            ActionsHelper.driverWait(2000);
            ActionsHelper.retryClick(nextBtn, 30);
            ActionsHelper.driverWait(2000);
            driver.get().switchTo().alert().accept();
    }

    public void addWrongRelativeInfo(){
        logManager.STEP("Add Wrong Data For Relatives","The user add wrong DOB and wrong EID in relatives info");
        ActionsHelper.retryClick(nextBtn,30);
        ActionsHelper.driverWait(8000);
        ActionsHelper.sendKeysWithClear(firstDependentEIDTextbox,"784198680432055");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeysWithClear(dependentDOB,formatDateTime);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(relationDDL,"5");
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(validateDependentBtn,30);
        ActionsHelper.driverWait(2000);
    }
    public String validationMsgForWrongRelative(){
        return driver.get().findElement(validationDependentMsgLbl).getText();
    }

    public void deleteWrongDependent(){
        ActionsHelper.retryClick(deleteWrongDependent,30);
        ActionsHelper.driverWait(2000);
        driver.get().switchTo().alert().accept();
    }

    public void createFullExceptionalCase() {
        logManager.STEP("Complete Creating Exceptional Case","The user complete all required steps to create the exceptional case");
        ActionsHelper.scrollTo(nextBtn);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(nextBtn,30);
        ActionsHelper.driverWait(2000);
        //addRelatives();
        ActionsHelper.driverWait(3000);
        //ActionsHelper.selectByIndex(driver.get().findElement(familyResidentDDL),1);
        ActionsHelper.selectOption(familyResidentDDL,"Automation Referral Entity Name-حالة استثنائية");
        ActionsHelper.driverWait(2000);
        ActionsHelper.clickAction(nextBtn);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(nextBtn, 30);
        ActionsHelper.driverWait(4000);
        getPopupsPage().uploadDocuments(driver.get().findElement(supportDocsLink),"test.pdf");
        ActionsHelper.driverWait(5000);
        driver.get().switchTo().frame(0);
        ActionsHelper.driverWait(5000);
        ActionsHelper.sendKeys(driver.get().findElement(By.xpath("//input[contains(@id,'ctl00_wttxt_Comment')]")),"PDF File");
        ActionsHelper.driverWait(3000);
       /* getPopupsPage().uploadDocuments(driver.get().findElement(supportDocsLink),"PNGForAutomation.png");
        ActionsHelper.sendKeys(driver.get().findElement(By.xpath("//input[contains(@id,'ctl02_wttxt_Comment')]")),"PNG File");
        ActionsHelper.driverWait(3000);
        getPopupsPage().uploadDocuments(driver.get().findElement(supportDocsLink),"JPGForAutomation.jpg");
        ActionsHelper.sendKeys(driver.get().findElement(By.xpath("//input[contains(@id,'ctl04_wttxt_Comment')]")),"JPG File");
        ActionsHelper.driverWait(3000);
        getPopupsPage().uploadDocuments(driver.get().findElement(supportDocsLink),"DocForAutomation.docx");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(driver.get().findElement(By.xpath("//input[contains(@id,'ctl06_wttxt_Comment')]")),"Doc File");
        ActionsHelper.driverWait(3000);
        getPopupsPage().uploadDocuments(driver.get().findElement(supportDocsLink),"FileToDelete.png");
        ActionsHelper.sendKeys(driver.get().findElement(By.xpath("//input[contains(@id,'ctl08_wttxt_Comment')]")),"To Be Deleted File");
        ActionsHelper.driverWait(3000);
        ActionsHelper.retryClick(driver.get().findElement(By.xpath("//a[contains(@id,'ctl08_wtlnk_delete')]")), 30);
        ActionsHelper.driverWait(3000);
        getPopupsPage().uploadDocuments(driver.get().findElement(supportDocsLink),"MoreThan500MB.docx");
        ActionsHelper.driverWait(5000);*/

        //ActionsHelper.retryClick(driver.get().findElement(By.id("InternalPortalTheme_wt24_block_WebPatterns_wt23_block_RichWidgets_wt9_block_wt33")),30 );
        //ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(saveFilesBtn,30);
        /*ActionsHelper.driverWait(6000);
        ActionsHelper.scrollTo(editFileDescription);
        ActionsHelper.clickElementActions(driver.get().findElement(editFileDescription));
        ActionsHelper.retryClick(editFileDescription,30);
        ActionsHelper.driverWait(5000);
        ActionsHelper.sendKeysWithClear(driver.get().findElement(By.xpath("//input[contains(@id,'ctl03_wttxt_Comment')]")),"new description for file");
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(driver.get().findElement(By.xpath("//a[contains(@id,'ctl03_wt165')]")),30 );*/
        ActionsHelper.driverWait(8000);
        ActionsHelper.scrollTo(nextBtn);
        ActionsHelper.retryClick(nextBtn,30);
        ActionsHelper.driverWait(8000);
        ActionsHelper.retryClick(nextBtn,30);
        ActionsHelper.driverWait(8000);
    }

    public void searchForId()  {
        logManager.STEP("Search for HOF EID Then Open The Exceptional Case","The user open the exceptional case to start approvals");
        ActionsHelper.retryClick(allListLink,30);
        ActionsHelper.sendKeys(searchForIdTextbox, TestData.EID + Keys.ENTER);
        ActionsHelper.driverWait(6000);
        refCode=driver.get().findElement(By.xpath("//td[@id='InternalPortalTheme_wt360_block_wtMainContent_wtBenefitRequestsList_ctl03_wtBenefitRequestsListRow1']")).getText();
        ActionsHelper.retryClick(appIdLink,30);
        ActionsHelper.driverWait(3000);

        refCode.replace("\uE007","");
        FileUtils.createFile("refCodeFile.txt",refCode);
        clickPreviousView();
        ActionsHelper.driverWait(2000);
        agentPage.logOut();
    }

    public void clickPreviousView(){
        ActionsHelper.retryClick(previousViewLink,30);
        ActionsHelper.driverWait(3000);
    }
    public void completeApprovals(){
        logManager.STEP("Start Specialist Approval After 10 Minutes Of Creating The Exceptional Case","The user login with specialist to approve the exceptional case");

        loginPage.loginWithUser(UserType.Specialist100);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(allListLink,30);
        ActionsHelper.driverWait(2000);

        ActionsHelper.sendKeys(searchForIdTextbox,refCode+ Keys.ENTER);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(appIdLink,30);
        ActionsHelper.driverWait(2000);
        clickPreviousView();
        agentPage.specialistApproval(refCode);
    }

    public void completeSeniorSpecialistApprovals() {
        logManager.STEP("Start SeniorSpecialist Approval For The Exceptional Case", "The user login with SeniorSpecialist100 to approve the exceptional case");

        loginPage.loginWithUser(UserType.SeniorSpecialist100);
        ActionsHelper.driverWait(2000);
        clickPreviousView();
        ActionsHelper.driverWait(2000);
        agentPage.seniorSpecialistApproval(refCode);
    }

    public void completeCommitteeApproval() {
        logManager.STEP("Start Committee Approval", "The user login with Committee2 to approve the exceptional case");
        loginPage.loginWithUser(UserType.Committee2);
        ActionsHelper.driverWait(2000);
        clickPreviousView();
        ActionsHelper.driverWait(2000);
        agentPage.committeeSpecialistApproval(refCode);
    }

    public void paymentSteps(){
        driver.get().navigate().to(urls.agentLogin);
        loginPage.loginWithUser(UserType.Superuser);
        ActionsHelper.navigate(urls.businessParameters);
        ActionsHelper.driverWait(3000);
        businessParametersPage.releaseApplication(refCode);
        agentPage.logOut();
        driver.get().navigate().to(urls.paymentList);
        loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
        Assert.assertTrue(paymentSpecialistPage.checkPaymentExistence(refCode));
        agentPage.logOut2();
    }
}

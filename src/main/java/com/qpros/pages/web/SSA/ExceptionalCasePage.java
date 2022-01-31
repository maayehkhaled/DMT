package com.qpros.pages.web.SSA;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.helpers.FileUtils;
import com.qpros.pages.web.SSA.commonSSA.Popups;
import com.ssa.core.service.SubmitApplicationService;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.awt.*;

@Getter
@Setter

public class ExceptionalCasePage extends Base{
    public ExceptionalCasePage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    Popups PopupsPage=new Popups(driver.get());
    AgentPage agentPage=new AgentPage(driver.get());
    SubmitApplicationService submitApplicationService = new SubmitApplicationService();
    LoginPage loginPage = new LoginPage(driver.get());

    private By exceptionalCaseLink= By.xpath("//a[contains(@id,'wt160')]");
    private By addNewEntity=By.xpath("//input[contains(@id,'openPopup')]");
    private By referralEntityNameTextbox=By.xpath("//input[contains(@id,'ReferralEntityName')]");
    private By saveReferralNameBtn=By.xpath("//input[contains(@id,'SaveBtn')]");
    private By caseDescriptionTextarea=By.xpath("//textarea[contains(@id,'wttxt_ReferralCaseInput')]");
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
    private By familyResidentDDL=By.xpath("//select[@id='InternalPortalTheme_wt24_block_wtMainContent_wtddl_LivingOnLocationList']");
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

    String myLongData= "sadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassad" +
            "assadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadas" +
            "sadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassad" +
            "assadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadass" +
            "adassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassada" +
            "ssadassadassadassadassadassadassadassadassadass";
    String shortData="sadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassa";

    public void openExceptionalCase(){
        ActionsHelper.retryClick(exceptionalCaseLink,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(addNewEntity,30);
        ActionsHelper.driverWait(2000);
        driver.get().switchTo().frame(0);
        ActionsHelper.sendKeys(referralEntityNameTextbox,"08 Jan Automation Referral Entity");
        ActionsHelper.retryClick(saveReferralNameBtn,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(firstApprovalCalendar,"04/01/2022");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(referralCalendar,"06/01/2022");
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(nextBtn,30);
        ActionsHelper.driverWait(2000);
    }

    public void chooseReferralEntity(){
        ActionsHelper.retryClick(exceptionalCaseLink,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(referralEntityDDL,"74");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(caseDescriptionTextarea,"Automation case description");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(firstApprovalCalendar,"04/01/2022");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(referralCalendar,"06/01/2022");
        ActionsHelper.driverWait(4000);
        ActionsHelper.retryClick(nextBtn,30);
        ActionsHelper.driverWait(4000);
    }
    public void enterHeadOfFamilyData(String eid, String birthDate){
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
        ActionsHelper.retryClick(exceptionalCaseLink,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(referralEntityDDL,"74");
        ActionsHelper.driverWait(2000);
        //need to solve this problem
        //ActionsHelper.sendKeys(caseDescriptionTextarea,myLongData);
        ActionsHelper.driverWait(2000);
        String s1=(driver.get().findElement(caseDescriptionTextarea)).getAttribute("value");
        return s1.length();
    }
    public int countMinChars(){
        ActionsHelper.retryClick(exceptionalCaseLink,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(referralEntityDDL,"74");
        ActionsHelper.sendKeys(caseDescriptionTextarea,shortData);
        ActionsHelper.driverWait(2000);
        String s1=(driver.get().findElement(caseDescriptionTextarea)).getAttribute("value");
        return s1.length();
    }

    public String invalidHOFEmail(){
        ActionsHelper.sendKeys(exceptionalCaseEmail,"123HI");
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(familyDetailsNetBtn,30);
        ActionsHelper.driverWait(2000);
        return driver.get().findElement(wrongEmailMsgLbl).getText();
        //return driver.get().findElement(validationMsg).getText();
    }

    public String enterValidHOFEmail() {
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
        if (driver.get().findElement(deleteFirstDependent).isDisplayed()) {
            ActionsHelper.retryClick(deleteFirstDependent, 30);
            driver.get().switchTo().alert().accept();
        } else if (driver.get().findElement(deleteSecondDependent).isDisplayed()) {
            ActionsHelper.retryClick(deleteSecondDependent, 30);
            driver.get().switchTo().alert().accept();
        } else {
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
    }

    public void addWrongRelativeInfo(){
            //log.step
        ActionsHelper.retryClick(nextBtn,30);
        ActionsHelper.driverWait(8000);
        ActionsHelper.sendKeysWithClear(firstDependentEIDTextbox,"784198680432055");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeysWithClear(dependentDOB,"29/01/1986");
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

    public void createFullExceptionalCase(){
        ActionsHelper.scrollTo(nextBtn);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(nextBtn,30);
        ActionsHelper.driverWait(2000);
        addRelatives();
        ActionsHelper.driverWait(3000);
        ActionsHelper.selectOption(familyResidentDDL,"4666034082-88-015-2-63");
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
        getPopupsPage().uploadDocuments(driver.get().findElement(supportDocsLink),"PNGForAutomation.png");
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
        ActionsHelper.driverWait(5000);

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
        //ActionsHelper.retryClick(driver.get().findElement(By.xpath("//div[contains(@id,'wtNextButtonContainer')]")),30 );
        //validation msg : //span[contains(@id,'wtSanitizedHtml2')]
    }

    public void searchForId()throws JsonProcessingException, AWTException, InterruptedException{
        ActionsHelper.retryClick(allListLink,30);
        ActionsHelper.sendKeys(searchForIdTextbox,"784199140633000"+ Keys.ENTER);
        ActionsHelper.driverWait(10000);

        if(driver.get().findElement(By.xpath("//td[contains(@id,'wtBenefitRequestsListRow5')]")).getText()=="Specialist100")
        {
            ActionsHelper.retryClick(appIdLink,30);
            ActionsHelper.driverWait(2000);
        }
        else
            driver.get().navigate().refresh();
        /*ActionsHelper.waitUntilElementIsDisplayed(assignedToLbl);
        return driver.get().findElement(assignedToLbl).getText();*/
    }

    public void completeApprovals(){
        agentPage.logOut();
        loginPage.loginWithUser(UserType.Specialist100);
        ActionsHelper.driverWait(50000);
        ActionsHelper.retryClick(allListLink,30);
        ActionsHelper.driverWait(10000);
        ActionsHelper.retryClick(allListLink,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(searchForIdTextbox,"784-1991-4063300-0"+ Keys.ENTER);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(appIdLink,30);
        //agentPage.specialistApproval("784199140633000",incOrDecApp);
    }

    public String  checkRequestAssigned(){
        WebElement allListTable = driver.get().findElement(allListTbl);
        WebElement tableCell = allListTable.findElement(By.xpath("//tbody//td[contains(@id,'ctl03_wtBenefitRequestsListRow5')]"));
        ActionsHelper.driverWait(10000);
        ActionsHelper.retryClick(appIdLink,30);
        return tableCell.getText();
    }
    public String checkIsComplex(){
        return driver.get().findElement(By.xpath("//span[contains(@id,'wtisComplex2')]")).getText();
    }

    public String bigFileValidationMsg(){
        return (driver.get().findElement(By.xpath("//span[contains(@id,'wtSanitizedHtml2')]"))).getText();
        //return driver.get().findElement(By.xpath("//div[contains(@class,'Feedback_Message_Error')]")).getText();
    }
}

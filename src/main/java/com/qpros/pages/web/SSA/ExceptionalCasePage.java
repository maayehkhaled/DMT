package com.qpros.pages.web.SSA;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.pages.web.SSA.commonSSA.Popups;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.UUID;

@Getter
@Setter

public class ExceptionalCasePage extends Base{
    public ExceptionalCasePage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    Popups PopupsPage=new Popups(driver.get());

    private By exceptionalCaseLink= By.xpath("//a[contains(@id,'wt160')]");
    private By addNewEntity=By.xpath("//input[contains(@id,'openPopup')]");
    private By referralEntityNameTextbox=By.xpath("//input[contains(@id,'ReferralEntityName')]");
    private By saveReferralNameBtn=By.xpath("//input[contains(@id,'SaveBtn')]");
    private By caseDescriptionTextarea=By.xpath("//textarea[contains(@id,'wttxt_ReferralCaseInput')]");
    private By firstApprovalCalendar=By.xpath("//input[contains(@id,'wt557_block_wtInput_wtdt_PreliminaryApprovalDate')]");
    private By referralCalendar=By.xpath("//input[contains(@id,'wt354_block_wtInput_wtdt_DateOfRefferal')]");
    private By nextBtn=By.xpath("//input[contains(@id,'NextBtn')]");
    private By referralEntityDDL=By.xpath("//select[contains(@id,'ReferralEntityList')]");

    //Screen II
    private By hohEIDTextbox=By.xpath("//input[contains(@id,'HOHEmiratesID')]");
    private By dateOfBirthCalendar=By.xpath("//input[contains(@id,'wt577_block_wtInput_wtdt_DateOfBrith')]");
    private By validateDetailsBtn=By.xpath("//input[contains(@id,'ValidateDetailsBtn')]");
    private By validationMsg=By.xpath("//span[contains(@id,'wtSanitizedHtml3')]");
            //("//span[co//span[contains(@id,'wtSanitizedHtml3')]ntains(@id,'wtSanitizedHtml2')]");

    //Screen III
    private By exceptionalCaseEmail=By.xpath("//input[contains(@id,'EmailInput')]");
    private By familyDetailsNetBtn=By.id("//input");
    private By wrongEmailMsgLbl=By.xpath("//span[contains(@id,'EmailInput')]");
    private By hOFmobileNo=By.xpath("//input[contains(@id,'MobileNumberInput')]");
    private By mobileValidationMsg=By.xpath("//span[contains(@id,'MobileNumberInput')]");


    //Screen IV
    private By dependentEIDTextbox=By.xpath("//input[contains(@id,'HOHEmiratesID2')]");
    private By dependentDOB=By.xpath("//input[contains(@id,'DateOfBrith2')]");
    private By relationDDL=By.xpath("//select[contains(@id,'ReferralEntityList2')]"); //4
    private By addAnotherDependentBtn=By.xpath("//input[contains(@id,'AddDependentBtn')]");
    private By validateDependentBtn=By.xpath("//input[contains(@id,'ValidateDependentDetail')]");
    private By validationDependentMsgLbl=By.className("Text_darkRed");
    private By deleteWrongDependent=By.xpath("//a[contains(@id,'wt550')]");

    //Screen V
    private By familyResidentDDL=By.xpath("//select[contains(@id,'LivingOnLocationList')]");
    private By supportDocsLink=By.id("fileinputPopup_Upload201200");
    private By uploadMoreSupportDocs=By.xpath("//input[contains(@id,'Upload201200')]");
    private By firstFileDescription=By.xpath("//input[contains(@id,'ctl00_wttxt_Comment')]");
    private By secondFileDescription=By.xpath("//input[contains(@id,'ctl02_wttxt_Comment')]");

    String longData= "sadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadassadass";
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
        ActionsHelper.driverWait(2000);
    }
    public void enterHeadOfFamilyData(String eid, String birthDate){
        ActionsHelper.sendKeys(hohEIDTextbox,eid);
        ActionsHelper.driverWait(2000);
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
        ActionsHelper.sendKeys(caseDescriptionTextarea,longData);
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

    public void addRelatives(){
        ActionsHelper.retryClick(nextBtn,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeysWithClear(dependentEIDTextbox,"784198680432055");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeysWithClear(dependentDOB,"29/01/1986");
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(relationDDL,"4");
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(addAnotherDependentBtn,30);
        ActionsHelper.driverWait(2000);
        //Case enter valid data then this method
        ActionsHelper.retryClick(nextBtn,30);
        ActionsHelper.driverWait(8000);
        driver.get().switchTo().alert().accept();
        //Automation Referral Entity Name  -  حالة استثنائية
    }

    public void addWrongRelativeInfo(){
        ActionsHelper.retryClick(nextBtn,30);
        ActionsHelper.driverWait(8000);
        ActionsHelper.sendKeysWithClear(dependentEIDTextbox,"784198680432055");
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
        ActionsHelper.selectOption(familyResidentDDL,"Automation Referral Entity Name  -  حالة استثنائية");
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(nextBtn,30);
        ActionsHelper.driverWait(2000);
        getPopupsPage().uploadDocuments(driver.get().findElement(supportDocsLink),"test.pdf");
        ActionsHelper.driverWait(2000);
        getPopupsPage().uploadDocuments(driver.get().findElement(uploadMoreSupportDocs),"PNGForAutomation.png");
        ActionsHelper.driverWait(2000);
        /*getPopupsPage().uploadDocuments(driver.get().findElement(uploadMoreSupportDocs),"JPGForAutomation.jpg");
        ActionsHelper.driverWait(2000);
        getPopupsPage().uploadDocuments(driver.get().findElement(uploadMoreSupportDocs),"DocForAutomation.docx");
        ActionsHelper.driverWait(2000);*/

    }
}

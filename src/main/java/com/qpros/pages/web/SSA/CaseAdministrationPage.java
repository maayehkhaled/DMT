package com.qpros.pages.web.SSA;

import com.github.javafaker.Faker;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

//This page contains
@Getter
@Setter
public class CaseAdministrationPage extends Base{
    public CaseAdministrationPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    Faker faker=new Faker(new Random());

    private By casesManagementLink = By.xpath("//a[text()='إدارة الحالات']");
    private By addNewSource = By.xpath("//a[contains(@id,'wtSocialSourcesList_AddRow')]");
    private By sourceTextbox=By.xpath("//input[contains(@id,'rows_ctl28_wttxt_Social_Referral_Source_Name')]");
    private By sourceCheckbox=By.xpath("//input[contains(@id,'rows_ctl28_wtchk_Social_Referral_Source_IsActive')]");
    private By saveNewRow=By.xpath("//a[contains(@class,'SaveRowAction')]");
    private By successMsg=By.xpath("//span[@class='Feedback_Message_Text']");

    @FindBy(xpath = "//input[contains(@class,'InEditMode')]")
    private List<WebElement> caseAdministrationInEditMode;

    private By socialIssueLink=By.xpath("//a[contains(@id,'wtSocialIssueTypeList_AddRow')]");
    private By socialIssueTextBox=By.xpath("//input[contains(@id,'wtSocialIssueTypeList_rows_ctl20_wttxt')]");
    private By sociaIssueCheckbox=By.xpath("//input[contains(@id,'wtContent_wtSocialIssueTypeList_rows_ctl20_wtchk_Social_Referral_LogType_IsActive')]");

    private By partnerSocialLink=By.xpath("//a[contains(@id,'wtSocialPartnerList_AddRow')]");
    private By partnerSocialTextbox=By.xpath("//input[contains(@id,'wtSocialPartnerList_rows_ctl14_wttxt_Social_Referral_LogType_Description')]");
    private By partnerSocialCheckbox=By.xpath("//input[contains(@id,'wtSocialPartnerList_rows_ctl14_wtchk_Social_Referral_LogType_IsActive')]");

    private By caseRecourse=By.xpath("//input[contains(@class,'InEditMode')]");
    private By caseLogLink=By.xpath("//a[contains(@id,'wtSocialReferralLogTypeList_AddRow')]");
    private By checkBox=By.xpath("//input[contains(@class,'InEditMode')]");
    private By lastCase=By.xpath("//input[contains(@id,'ctl32_wttxt_Social_Referral_LogType_Description')]");
    private By elementToScroll=By.xpath("//input[contains(@id,'wt19_block_wtContent_wtbtn_Search3')]");
    private By socialEleScroll=By.xpath("//input[contains(@id,'wt16_block_wtContent_wtbtn_Search3')]");

    public void createNewCase(By newCase,By saveRecord,By scrollElement){
        faker=new Faker();
        ActionsHelper.clickAction(casesManagementLink);
        ActionsHelper.driverWait(4000);
        ActionsHelper.scrollupTo(driver.get().findElement(scrollElement));
        ActionsHelper.driverWait(2000);
        ActionsHelper.clickAction(newCase);
        //ActionsHelper.retryClick(newCase,30);
        ActionsHelper.driverWait(2000);
        //logManager.INFO("Click add new source",false);
        ActionsHelper.sendKeys(driver.get().findElements(caseRecourse).get(0), "My new automation "+faker.lorem().sentence(1));
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(driver.get().findElements(checkBox).get(1),30);
        // ActionsHelper.clickAction(checkBox);
        logManager.INFO("Check box",false);
        ActionsHelper.driverWait(4000);
        ActionsHelper.retryClick(saveRecord,30);
        logManager.INFO("Save all",false);
        //logManager.INFO("Click Save New Case Source",false);
        ActionsHelper.driverWait(4000);
        logManager.INFO("Success Msg",false);
    }

    public void lastStep(By scrollEle){
        ActionsHelper.clickAction(casesManagementLink);
        ActionsHelper.driverWait(2000);
        ActionsHelper.scrollupTo(driver.get().findElement(scrollEle));
        ActionsHelper.driverWait(4000);
        logManager.INFO("The new source",false);
    }

    public String getSuccessMsg(){
        String successMsgTxt=driver.get().findElement(successMsg).getText();
        return(successMsgTxt);
    }

}

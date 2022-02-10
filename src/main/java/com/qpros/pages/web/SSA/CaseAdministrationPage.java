package com.qpros.pages.web.SSA;

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

//This page contains
@Getter
@Setter
public class CaseAdministrationPage extends Base{
    public CaseAdministrationPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    private By casesManagementLink = By.id("DCDTheme_wt70_block_wtMenu_Activation_CW_wt45_block_wtMenuItems_ctl20_RichWidgets_wt6_block_wtMenuItem");
    private By addNewSource = By.xpath("//a[contains(@id,'wtSocialSourcesList_AddRow')]");
    private By sourceTextbox=By.xpath("//input[contains(@id,'rows_ctl28_wttxt_Social_Referral_Source_Name')]");
    private By sourceCheckbox=By.xpath("//input[contains(@id,'rows_ctl28_wtchk_Social_Referral_Source_IsActive')]");
    private By saveNewRow=By.xpath("//a[contains(@class,'SaveRowAction')]");
    private By successMsg=By.id("InternalPortalTheme_wt6_block_WebPatterns_wt23_block_RichWidgets_wt9_block_wtSanitizedHtml3");

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

    public void createNewCase(By newCase,By saveRecord){
        ActionsHelper.retryClick(casesManagementLink,30);
        ActionsHelper.driverWait(8000);
        ActionsHelper.retryClick(newCase,30);
        ActionsHelper.driverWait(2000);

        ActionsHelper.sendKeys(driver.get().findElements(caseRecourse).get(0), "My new automation");
        ActionsHelper.retryClick(driver.get().findElements(By.xpath("//input[contains(@class,'InEditMode')]")).get(1),30);
        ActionsHelper.driverWait(1000);
        ActionsHelper.retryClick(saveRecord,30);
        ActionsHelper.driverWait(8000);
    }

    public String getSuccessMsg(){
        String successMsgTxt=driver.get().findElement(successMsg).getText();
        return(successMsgTxt);
    }

}

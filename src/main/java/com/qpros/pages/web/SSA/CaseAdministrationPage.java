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

    private By casesManagementLink = By.xpath("//a[.='إدارة الحالات']");
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

    public void createNewCase(By newCase,By saveRecord){
        faker=new Faker();
        ActionsHelper.retryClick(casesManagementLink,30);
        ActionsHelper.driverWait(8000);
        ActionsHelper.retryClick(newCase,30);
        ActionsHelper.driverWait(2000);

        ActionsHelper.sendKeys(driver.get().findElements(caseRecourse).get(0), "My new automation "+faker.lorem().sentence(1));
        ActionsHelper.retryClick(driver.get().findElements(checkBox).get(1),30);
        ActionsHelper.driverWait(1000);
        ActionsHelper.retryClick(saveRecord,30);
        ActionsHelper.driverWait(8000);
    }

    public String getSuccessMsg(){
        String successMsgTxt=driver.get().findElement(successMsg).getText();
        return(successMsgTxt);
    }

}

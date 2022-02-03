package com.qpros.pages.web.SSA;

import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.ssa.core.common.data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ManageOffersHouseholdPage extends Base {
    public ManageOffersHouseholdPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    private By houseHoldingTab=By.xpath("//a[contains(@id,'ctl04_RichWidgets_wt6_block_wtMenuItem_wt11')]");
    private By eidTextbox= By.xpath("//input[contains(@id,'EmiratesId')]");
    private By userIdLink=By.xpath("//a[contains(@id,'ctl03_wt71')]");
    private By oppUserEIDLink=By.xpath("//a[contains(@id,'ctl03_wtlnk_Expandview')]");
    private By planBtn=By.xpath("//div[contains(@id,'wt133_block_wtImageWrapper')]");
    private By editPlanBtn=By.xpath("//a[contains(@id,'ctl38_WebPatterns_wt133_block_wtTitle_wtOpportunityActions21')]");
    private By editNew=By.xpath("//a[contains(@id,'ctl38_WebPatterns_wt133_block_wtTitle_wtOpportunityActions18')]");
    private By opportunityStatusDDL=By.xpath("//select[contains(@id,'DDOpportunityStatus')]");
    private By communicationDDL=By.xpath("//select[contains(@id,'DDSourceofCommunication')]");
    private By commentTextarea=By.xpath("//textarea[contains(@id,'Comment')]");
    private By smsTextarea=By.xpath("//textarea[contains(@id,'Sms')]");
    private By saveBtn=By.xpath("//input[contains(@id,'Save')]");
    private By partnerDDL=By.xpath("//select[contains(@id,'FeedbackCategory')]");
    private By partnerComment=By.xpath("//textarea[contains(@id,'FeedbackTextArea')]");

    public void searchForEID(){
        ActionsHelper.retryClick(houseHoldingTab,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(eidTextbox, TestData.pepUserEID+ Keys.ENTER);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(userIdLink,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(oppUserEIDLink,30);
        ActionsHelper.driverWait(2000);
    }

    public void openEmpowermentPlan(){
        ActionsHelper.retryClick(planBtn,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(editNew,30);
        ActionsHelper.driverWait(2000);
        driver.get().switchTo().frame(0);
        ActionsHelper.selectOption(opportunityStatusDDL,"10");
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(communicationDDL,"2");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(commentTextarea,"Automation comment");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(smsTextarea,"Automation MSG");
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(saveBtn,30);
    }

    public void editPartnerComment(){
        ActionsHelper.retryClick(editNew,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(partnerDDL,"5");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(partnerComment,"Partner Comment");
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(saveBtn,30);
        ActionsHelper.driverWait(2000);
    }
}

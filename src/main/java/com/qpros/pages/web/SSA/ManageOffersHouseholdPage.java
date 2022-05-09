package com.qpros.pages.web.SSA;

import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.ssa.core.common.data.StaticValues;
import com.ssa.core.common.data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ManageOffersHouseholdPage extends Base {
    public ManageOffersHouseholdPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    private By houseHoldingTab=By.xpath("//a[text()='ملف الأسرة']");  //("//a[contains(@id,'ctl04_RichWidgets_wt6_block_wtMenuItem_wt11')]");
    private By eidTextbox= By.xpath("//input[contains(@id,'EmiratesId')]");
    private By userIdLink=By.xpath("//a[contains(@id,'ctl03_wt71')]");
    private By oppUserEIDLink=By.xpath("//a[contains(@id,'ctl03_wtlnk_Expandview')]");
    private By planBtn=By.xpath("//div[contains(@id,'wt133_block_wtImageWrapper')]");
    private By editPlanBtn=By.xpath("//a[contains(@id,'ctl38_WebPatterns_wt133_block_wtTitle_wtOpportunityActions21')]");

    private By editFirstTimeBtn=By.xpath("//span[43]//span[@class='fa fa-fw fa-sliders fa-2x']");
    private By scrollToEle=By.xpath("//div[@class='SubTitle_Section']");
    private By editSecondTimeBtn=By.xpath("//span[43]//span[@class='fa fa-fw fa-pencil-square-o fa-lg']");
    private By opportunityStatusDDL=By.xpath("//select[contains(@id,'DDOpportunityStatus')]");
    private By communicationDDL=By.xpath("//select[contains(@id,'DDSourceofCommunication')]");
    private By commentTextarea=By.xpath("//textarea[contains(@id,'Comment')]");
    private By smsTextarea=By.xpath("//textarea[contains(@id,'Sms')]");
    private By saveBtn=By.xpath("//input[contains(@id,'Save')]");
    private By saveBtn2 =By.xpath("//a[contains(@id,'ctl48_WebPatterns_wt139_block_wtTitle')]");
    private By partnerDDL=By.xpath("//select[contains(@id,'FeedbackCategory')]");
    private By partnerComment=By.xpath("//textarea[contains(@id,'FeedbackTextArea')]");
    private By viewDetailsBtn=By.xpath("//div[contains(@id,'ctl38_WebPatterns_wt133_block_wtSectionExpandableArea')]/div/div[2]/span");
    private By noAnswerMsg=By.xpath("//textarea[contains(@id,'wttxt_Sms')]");
    private By suggestedTask=By.xpath("//span[contains(text(),'موصى به')]");

    public void searchForEID(){
        logManager.STEP("Search For EID","The user searches for EID then click Tamkeen");
        ActionsHelper.retryClick(houseHoldingTab,30);
        ActionsHelper.driverWait(2000);
        //ActionsHelper.sendKeys(eidTextbox, TestData.pepUserEID+ Keys.ENTER);
        ActionsHelper.sendKeys(eidTextbox, TestData.opportunityEID+ Keys.ENTER);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(userIdLink,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(oppUserEIDLink,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(planBtn,30);
        ActionsHelper.driverWait(2000);
    }

    public void firstTimeEdit(){
        logManager.STEP("First Edit For The Opportunity","The user click edit to manage opportunity ");
        ActionsHelper.scrollTo(scrollToEle);
        if(driver.get().findElement(suggestedTask).isDisplayed()) {
            ActionsHelper.retryClick(editFirstTimeBtn, 30);
            ActionsHelper.driverWait(2000);
            driver.get().switchTo().frame(0);
            ActionsHelper.selectOption(opportunityStatusDDL, StaticValues.acceptOppStatus);
            ActionsHelper.driverWait(2000);
            ActionsHelper.selectOption(communicationDDL, StaticValues.IELTS);
            ActionsHelper.driverWait(2000);
            ActionsHelper.sendKeys(commentTextarea, "Automation comment....");
            ActionsHelper.driverWait(2000);
            ActionsHelper.sendKeys(smsTextarea, "Automation MSG..");
            ActionsHelper.driverWait(2000);
            ActionsHelper.retryClick(saveBtn, 30);
            ActionsHelper.driverWait(2000);
        }
        else {
            System.out.println("No New Opportunity For This EID");
            driver.get().close();
        }
    }
    public void editStatusOnly(){
        logManager.STEP("Edit Opportunity Status","The user click edit to edit opportunity status ");
        ActionsHelper.retryClick(editSecondTimeBtn,30);
        ActionsHelper.driverWait(2000);
        driver.get().switchTo().frame(0);
        ActionsHelper.selectOption(opportunityStatusDDL,StaticValues.noAnswerOppStatus);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(commentTextarea,"Automation comment no attachments abc");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(noAnswerMsg,"No attachments till now please call us123 abc");
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(saveBtn,30);
        ActionsHelper.driverWait(2000);
    }

    public void secondTimeEdit(){
        logManager.STEP("Second Edit For The Opportunity","The user click edit for the second time to complete editing");
        ActionsHelper.retryClick(editSecondTimeBtn,30);
        ActionsHelper.driverWait(2000);
        driver.get().switchTo().frame(0);
        //ActionsHelper.selectOption(opportunityStatusDDL,StaticValues.acceptNoDocOppStatus);
        ActionsHelper.scrollTo(partnerDDL);
        ActionsHelper.selectByValue(driver.get().findElement(partnerDDL),StaticValues.joblessJan);
        ActionsHelper.driverWait(4000);
        ActionsHelper.sendKeysWithClear(partnerComment,"Partner Comment 24/2");
        ActionsHelper.driverWait(4000);
        ActionsHelper.scrollTo(saveBtn);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(saveBtn,30);
        ActionsHelper.driverWait(6000);
    }
}

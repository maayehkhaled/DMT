package com.qpros.pages.web.SSA;

import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.ssa.core.common.data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class ManageOffersManagementPage extends Base {
    public ManageOffersManagementPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    private By offersLink=By.xpath("//a[contains(@id,'ctl10_RichWidgets_wt6_block_wtMenuItem_wt11')]");
    private By searchEID=By.xpath("//a[contains(@id,'wt207')]");
    private By EidTextbox=By.xpath("//input[contains(@id,'wttxt_Eid')]");
    private By EidLink=By.xpath("//a[contains(@id,'ctl00_WebPatterns_wt9_block_wtTitle_wtlnk_Expandview')]");
    private By settingsBtn=By.xpath("//div[@id='InternalPortalTheme_wt523_block_wtMainContent_wttbl_OpportunityPlanTable_ctl00_WebPatterns_wt9_block_wtTitle_wtMenu']");
    private By changeOfferStatusLink=By.xpath("//div[.='تغيير حالة العرض']");
            //("//span[@id='InternalPortalTheme_wt523_block_wtMainContent_wttbl_OpportunityPlanTable_ctl00_WebPatterns_wt9_block_wtTitle_OutSystemsUIWeb_wt240_block_wtOnHide']");
            //("//div/div/spain[@id='InternalPortalTheme_wt523_block_wtMainContent_wttbl_OpportunityPlanTable_ctl00_WebPatterns_wt9_block_wtTitle_OutSystemsUIWeb_wt240_block_wtOnHide']");
    private By actionDDL=By.xpath("//select[contains(@id,'wt193_block_wtMainContent_wtddl_OpportunityStatus')]");
    private By saveBtn=By.xpath("//input[contains(@id,'Save')]");
    private By partnerNoteLink=By.xpath("//*[text()='إضافة ملاحظات الشريك']");
            //a[@id='InternalPortalTheme_wt523_block_wtMainContent_wttbl_OpportunityPlanTable_ctl00_WebPatterns_wt9_block_wtTitle_OutSystemsUIWeb_wt531_block_wtContent_wtlnk_Eid10']/div");
    private By partnerCommentTextarea=By.xpath("//textarea[contains(@id,'FeedbackTextArea')]");
    private By detailsArrowBtn=By.xpath("//a[@id='InternalPortalTheme_wt523_block_wtMainContent_wttbl_OpportunityPlanTable_ctl00_WebPatterns_wt9_block_wtTitle_OutSystemsUIWeb_wt531_block_wtContent_wtlnk_Eid10']/div");



    public void clickOnEID() {
        ActionsHelper.retryClick(offersLink, 30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(searchEID, 30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(EidTextbox, TestData.pepUserEID + Keys.ENTER);
        ActionsHelper.driverWait(2000);
        /*ActionsHelper.retryClick(EidLink,30);
        ActionsHelper.driverWait(2000);*/
    }
        //Change status method

        //ActionsHelper.clickAction(settingsBtn);
        public void changeStatus(){
        Actions actions = new Actions(driver.get());
        actions.moveToElement(driver.get().findElement(settingsBtn)).build().perform();
        ActionsHelper.driverWait(5000);
        ActionsHelper.retryClick(changeOfferStatusLink, 30);
        ActionsHelper.driverWait(2000);
        driver.get().switchTo().frame(0);
        ActionsHelper.driverWait(5000);
        ActionsHelper.selectOption(actionDDL, "13");
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(saveBtn, 30);
        ActionsHelper.driverWait(4000);
    }

        //add partner comments method
        public void addPartnerComment(){
        Actions actions = new Actions(driver.get());
        actions.moveToElement(driver.get().findElement(settingsBtn)).build().perform();
        ActionsHelper.driverWait(5000);
        ActionsHelper.retryClick(partnerNoteLink,30);
        ActionsHelper.driverWait(2000);
        driver.get().switchTo().frame(0);
        ActionsHelper.driverWait(2000);
        ActionsHelper.scrollTo(partnerCommentTextarea);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(partnerCommentTextarea,"Automation Partner comments");
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(saveBtn,30);
        //open details
        ActionsHelper.retryClick(detailsArrowBtn,30);
        ActionsHelper.driverWait(2000);
    }

}

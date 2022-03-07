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
    private By searchEID=By.xpath("//div[@class='paddingR10 fontweight16 hoverBlue']");
    private By EidTextbox=By.xpath("//input[contains(@id,'wttxt_Eid')]");
    private By settingsBtn=By.xpath("//div[contains(@id,'ctl00_WebPatterns_wt10_block_wtTitle_wtMenu')]");
    private By changeOfferStatusLink=By.xpath("//div[.='تغيير حالة العرض']");
    private By actionDDL=By.xpath("//select[contains(@id,'wt221_block_wtMainContent_wtddl_OpportunityStatus')]"); //("[title='يرجى تحديد الإجراء']");
    private By saveBtn=By.xpath("//input[contains(@id,'Save')]");
    private By msgToUser=By.xpath("//textarea[contains(@id,'Sms')]");
    private By partnerNoteLink=By.xpath("//*[text()='إضافة ملاحظات الشريك']");
    private By partnerCommentTextarea=By.xpath("//textarea[contains(@id,'FeedbackTextArea')]");
    private By viewInfoArrow=By.xpath("//div[@class='halfFit1  topOnly']/span[@class='ListRecords']/div[1]//div[@class='hoverImg']");
    private By displayStatus=By.xpath("//div[@class='ballonhover ballonall']//div[.='تغيير حالة العرض']");

    public void clickOnEID() {
        logManager.STEP("Search For EID","The user searches for specific EID");
        ActionsHelper.retryClick(offersLink, 30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(searchEID, 30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(EidTextbox, TestData.pepUserEID + Keys.ENTER);
        ActionsHelper.driverWait(4000);
    }

    public void changeDisplayStatus(){
        logManager.STEP("Change Display Status","The user changes the display status to accept");
        Actions actions = new Actions(driver.get());
        actions.moveToElement(driver.get().findElement(settingsBtn)).build().perform();
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(displayStatus,30);
        ActionsHelper.driverWait(2000);
        driver.get().switchTo().frame(0);
        ActionsHelper.selectOption(actionDDL,"10");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(msgToUser,"Message to user .. Accept");
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(saveBtn,30);
        ActionsHelper.driverWait(6000);
        logManager.INFO("Accepted",false);
    }

        public void changeStatus(){
        logManager.STEP("Change status","The user clicks on change status");
        Actions actions = new Actions(driver.get());
        ActionsHelper.scrollTo(settingsBtn);
        actions.moveToElement(driver.get().findElement(settingsBtn)).build().perform();
        ActionsHelper.driverWait(5000);
        ActionsHelper.retryClick(changeOfferStatusLink, 30);
        ActionsHelper.driverWait(2000);
        driver.get().switchTo().frame(0);
        ActionsHelper.driverWait(5000);
        ActionsHelper.clickAction(actionDDL);
        ActionsHelper.selectOption(actionDDL, "13");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(msgToUser,"Comment To User");
        ActionsHelper.retryClick(saveBtn, 30);
        ActionsHelper.driverWait(4000);
    }

        public void addPartnerComment() {
        logManager.STEP("Add Partner Comments","The user adds the partner comments after approve the opportunity");
        Actions actions = new Actions(driver.get());
        actions.moveToElement(driver.get().findElement(settingsBtn)).build().perform();
        ActionsHelper.driverWait(5000);
        ActionsHelper.retryClick(partnerNoteLink, 30);
        ActionsHelper.driverWait(2000);
        driver.get().switchTo().frame(0);
        ActionsHelper.driverWait(2000);
        ActionsHelper.scrollTo(partnerCommentTextarea);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeysWithClear(partnerCommentTextarea, "Automation Partner comments");
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(saveBtn, 30);
        ActionsHelper.driverWait(2000);
        }

        public void viewAllInfo(){
        logManager.STEP("open details","The user clicks on the arrow to see all opportunity details");
        ActionsHelper.scrollTo(viewInfoArrow);
        ActionsHelper.retryClick(viewInfoArrow,30);
        ActionsHelper.driverWait(2000);
    }

}

package com.qpros.pages.web.SSA;

import com.qpros.common.annotation.STEP;
import com.qpros.common.web.Base;
import com.qpros.common.web.Util;
import com.qpros.helpers.ActionsHelper;
import com.qpros.pages.web.SSA.commonSSA.Popups;
import com.ssa.core.common.locators.urls;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

@Getter
public class AuditorsManagementPage extends Base {
    public AuditorsManagementPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    Popups feedbackMessage = new Popups(driver.get());

    @FindBy(xpath = "//span[@class=\"select2-results\"]//ul//li")
    private List<WebElement> listOfOptions;

    private By auditorsManagement = By.id("DCDAgentPortalTheme_wt304_block_wtMenu_AgentPortal_CW_wt88_block_RichWidgets_wt31_block_wtMenuItem_wt72");
    private By selectspecid = By.xpath("//span[@class=\"selection\"]");
    //private By selectspecid = By.xpath("//div[contains(@id,'block_wtContent_wtStatus')]");
    private By selectSpecialist = By.cssSelector(".select2-search__field");


    private By inputRef = By.xpath("//*[contains(@id,'block_wtContent_wttxt_Codes')]");
//DCDAgentPortalTheme_wt15_block_wtMainContent_WebPatterns_wt44_block_wtContent_wttxt_Codes
    private By clickSave = By.xpath("//input[@class='Button ThemeGrid_MarginGutter']");

    private By searchField = By.cssSelector(".select2-search__field");
    private By feedBackMessage = By.xpath("//span[@class=\"Feedback_Message_Text\"]");


    //private By listOfOptions = By.xpath("//span[@class=\"select2-results\"]//ul//li");

    @STEP(name = "Set Specialist", description = "Sets the case to a specific specialist")
    public void selectSpecialist(String specialistName, String refNumber) throws InterruptedException, AWTException {
        logManager.STEP("Input Specialist", "Inputs the specialist: " + specialistName);
        ActionsHelper.driverWait(3000);
        ActionsHelper.navigate(urls.agentManagement);
        ActionsHelper.driverWait(3000);
        ActionsHelper.retryClick(selectspecid,10);
        ActionsHelper.driverWait(3000);
        ActionsHelper.retryClick(searchField,10);
        /*
        for (WebElement element : getListOfOptions()){
            if (element.getText().equals(specialistName)){
                ActionsHelper.clickAction(element);
            }
        }
         */
        Util.typeString(specialistName);
        Robot robot = new Robot();
        ActionsHelper.driverWait(500);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        ActionsHelper.driverWait(500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        logManager.INFO("Input Ref"+refNumber+"", false);
        ActionsHelper.sendKeys(inputRef, refNumber);
        ActionsHelper.driverWait(8000);
        ActionsHelper.actionClickStepClick("Clicks the save button", clickSave);
        ActionsHelper.driverWait(6000);
        /*driver.get().switchTo().alert().accept();//
        ActionsHelper.waitForExpectedElement(feedBackMessage, 30);*/
     /*   if (feedbackMessage.feedbackMessage().equals("تم إعادة تعيين الطلبات بنجاح"))
        {
            try {
                driver.get().switchTo().alert().accept();
            } catch (Exception e) {
                logManager.WARN("must be ASSGIN to Specialist");
            }
        }
        else {
            ActionsHelper.sendKeys(inputRef, refNumber);
            ActionsHelper.driverWait(8000);
            ActionsHelper.actionClickStepClick("Clicks the save button", clickSave);
        }*/
        try {
            driver.get().switchTo().alert().accept();
        } catch (Exception e) {
            logManager.WARN("must be ASSGIN to Specialist");
        }

    }
}

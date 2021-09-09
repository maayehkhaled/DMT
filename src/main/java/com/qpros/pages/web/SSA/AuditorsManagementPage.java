package com.qpros.pages.web.SSA;

import com.qpros.common.annotation.STEP;
import com.qpros.common.web.Base;
import com.qpros.common.web.Util;
import com.qpros.helpers.ActionsHelper;
import com.ssa.core.common.locators.urls;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.event.KeyEvent;

public class AuditorsManagementPage extends Base {
    public AuditorsManagementPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    private By auditorsManagement = By.id("DCDAgentPortalTheme_wt304_block_wtMenu_AgentPortal_CW_wt88_block_RichWidgets_wt31_block_wtMenuItem_wt72");
    private By selectspecid = By.xpath("//div[contains(@id,'block_wtContent_wtStatus')]");
    private By selectSpecialist = By.cssSelector(".select2-search__field");


    private By inputRef = By.xpath("//*[contains(@id,'block_wtContent_wttxt_Codes')]");
//DCDAgentPortalTheme_wt15_block_wtMainContent_WebPatterns_wt44_block_wtContent_wttxt_Codes
    private By clickSave = By.xpath("//input[@class='Button ThemeGrid_MarginGutter']");

    private By searchField = By.cssSelector(".select2-search__field");


    @STEP(name = "Set Specialist", description = "Sets the case to a specific specialist")
    public void selectSpecialist(String specialistName, String refNumber) throws AWTException, InterruptedException {
        logManager.STEP("Input Specialist", "Inputs the specialist: " + specialistName);
        ActionsHelper.driverWait(500);
        ActionsHelper.navigate(urls.agentManagement);
        ActionsHelper.driverWait(500);
        ActionsHelper.retryClick(selectspecid,10);
        ActionsHelper.driverWait(500);

        ActionsHelper.retryClick(searchField,10);
        Util.typeString(specialistName);
        Robot robot = new Robot();
        ActionsHelper.driverWait(500);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        ActionsHelper.driverWait(500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        logManager.STEP("Input Ref", "Inputs the reference nubmer: " + refNumber);
        ActionsHelper.sendKeys(inputRef, refNumber);
        ActionsHelper.actionClickStepClick("Clicks the save button", clickSave);
        ActionsHelper.driverWait(500);
    }
}

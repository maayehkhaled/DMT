package com.qpros.pages.web.SSA;

import com.qpros.common.annotation.STEP;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuditorsManagementPage extends Base {
    public AuditorsManagementPage(WebDriver driver){
        PageFactory.initElements(Base.driver.get(), this);
    }

    private By selectSpecialist = By.cssSelector("=.select2-search__field");


    private By inputRef = By.id("DCDAgentPortalTheme_wt15_block_wtMainContent_WebPatterns_wt44_block_wtContent_wt21");

    private By clickSave = By.id("DCDAgentPortalTheme_wt15_block_wtMainContent_WebPatterns_wt44_block_wtContent_wt21");


    @STEP(name = "Set Specialist",description = "Sets the case to a specific specialist")
    public void selectSpecialist(String specialistName, String refNumber){
        logManager.STEP("Input Specialist","Inputs the specialist: " + specialistName);
        ActionsHelper.sendKeys(selectSpecialist,specialistName);
        logManager.STEP("Input Ref","Inputs the reference nubmer: " + refNumber);
        ActionsHelper.sendKeys(inputRef, refNumber);
        ActionsHelper.actionClickStepClick("Clicks the save button",clickSave);
    }
}

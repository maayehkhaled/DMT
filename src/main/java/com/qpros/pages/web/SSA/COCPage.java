package com.qpros.pages.web.SSA;

import com.qpros.common.LogManager;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.ssa.core.common.locators.urls;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class COCPage extends Base {
    public COCPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    private By searchField = By.id("DCDWebPortalTheme_wt15_block_wtMainContent_wt1");
    private By searchFieldSSP = By.id("DCDAgentPortalTheme_wt196_block_wtMainContent_WebPatterns_wt178_block_wtContent_wt131");
    private By firstRequest = By.id("DCDAgentPortalTheme_wt196_block_wtMainContent_WebPatterns_wt178_block_wtContent_wtBenefitRequests_ctl03_wt210");
    private By commentSection = By.id("DCDAgentPortalTheme_wt196_block_wtMainContent_WebPatterns_wt178_block_wtContent_wtCommentIn");
    private By launchCocProcess = By.id("DCDAgentPortalTheme_wt196_block_wtMainContent_WebPatterns_wt178_block_wtContent_wt214");

    public void navigateToCoc(){
        logManager.STEP("Navigate to COC", "Navigate to https://uat.ssa.gov.ae/DCDBusinessParameters/CoC.aspx");
        driver.get().navigate().to(urls.cocPage);
    }

    public void startCocProcess(String refCode){
        logManager.STEP("Input search field", "Inputs the SSP code and press enter");
        ActionsHelper.sendKeys(searchFieldSSP, refCode + Keys.ENTER);
        ActionsHelper.driverWait(1000);
        ActionsHelper.actionClickStepClick("Click on first application checkbox", firstRequest);
        ActionsHelper.sendKeys(commentSection, "testComment");
        ActionsHelper.driverWait(1000);
        ActionsHelper.actionClickStepClick("Click Launch Process", launchCocProcess);
    }



}

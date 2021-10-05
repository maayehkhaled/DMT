package com.qpros.pages.web.SSA;

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

    private By searchButton = By.xpath("//input[@id=\"DCDAgentPortalTheme_wt194_block_wtMainContent_WebPatterns_wt179_block_wtContent_wtbtn_filtereligible\"]");
    private By searchFieldSSP = By.id("DCDAgentPortalTheme_wt194_block_wtMainContent_WebPatterns_wt179_block_wtContent_wttxt_SearchEligible");
    private By firstRequest = By.xpath("//input[@id=\"DCDAgentPortalTheme_wt194_block_wtMainContent_WebPatterns_wt179_block_wtContent_wtBenefitRequests_ctl03_wt211\"]");
    private By commentSection = By.id("DCDAgentPortalTheme_wt194_block_wtMainContent_WebPatterns_wt179_block_wtContent_wttxt_CommentIn");
    private By launchCocProcess = By.id("DCDAgentPortalTheme_wt194_block_wtMainContent_WebPatterns_wt179_block_wtContent_wtbtn_LaunchCOC");
    private By cocPage = By.xpath("//a[@id=\"InternalPortalTheme_wt85_block_wtMenu_AgentPortal_CW_wt90_block_RichWidgets_wt90_block_wtMenuItem_wt55\"]");

    public void navigateToCoc(){
        logManager.STEP("Navigate to COC", "Navigate to https://uat.ssa.gov.ae/DCDBusinessParameters/CoC.aspx");
        driver.get().navigate().to(urls.cocPage);
    }

    public void startCocProcess(String refCode){

        logManager.STEP("Input search field", "Inputs the SSP code and press enter");
        //ActionsHelper.actionClickStepClick("Click on COC page", cocPage);
        ActionsHelper.sendKeys(searchFieldSSP, refCode);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("Click on first application search checkbox", firstRequest);
        ActionsHelper.sendKeys(commentSection, "testComment");
        ActionsHelper.driverWait(1000);
        ActionsHelper.actionClickStepClick("Click Launch Process", launchCocProcess);
        try {
            driver.get().switchTo().alert().accept();
        } catch (Exception e) {
        }
        ActionsHelper.driverWait(5000);
        driver.get().navigate().to(urls.tasksList);

    }



}

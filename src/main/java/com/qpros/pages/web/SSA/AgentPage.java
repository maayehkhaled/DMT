package com.qpros.pages.web.SSA;

import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//This page contains the applications list. Main agent page
public class AgentPage extends Base {

    public AgentPage(WebDriver driver){
        PageFactory.initElements(Base.driver.get(), this);
    }

    private By auditorsManagementNavigation = By.id("DCDAgentPortalTheme_wt290_block_wtMenu_DCDAgentPortalTheme_wt82_block_RichWidgets_wt128_block_wtMenuItem_wt21");

    private By applicationRefFirst = By.cssSelector("#DCDAgentPortalTheme_wt290_block_wtMainContent_wtBenefitRequestsList tr:nth-child(1) > .TableRecords_OddLine:nth-child(1)");

    private By specalistFirstApplication = By.cssSelector("#DCDAgentPortalTheme_wt79_block_wtMainContent_wtListRecords1_ctl00_wt83 .ThemeGrid_Width4:nth-child(3) > span:nth-child(1)"); //Contains app ref number and clickable

    private By specalistSearchApplication = By.id("DCDAgentPortalTheme_wt79_block_wtFilterContainer_wtSearcFrom"); //Enter search text here (ref code)

    private By firstElementAfterSearch = By.cssSelector(".ThemeGrid_Width4:nth-child(3) > span:nth-child(1)"); //Contains app ref number and clickable

    private By agentApproveStep = By.id("DCDAgentPortalTheme_wt363_block_wtActions_wtApproveCurrentSection"); //Contains app ref number and clickable

    private By agentClickNext = By.id("DCDAgentPortalTheme_wt363_block_wtActions_wtNext");

    private By agentClickNext56Step = By.id("DCDAgentPortalTheme_wt363_block_wtActions_wtNext6");

    private By applicationListSecondApplicationRefCode = By.cssSelector("tr:nth-child(2) > .TableRecords_EvenLine:nth-child(1)"); //Contains app ref number and clickable

    private By applicationListFirstApplicationRefCode = By.cssSelector("#DCDAgentPortalTheme_wt290_block_wtMainContent_wtBenefitRequestsList tr:nth-child(1) > .TableRecords_OddLine:nth-child(1)"); //Contains app ref number and clickable

    private By applicationListSecondApplicationSupervisorName = By.cssSelector("tr:nth-child(2) .FlexColContainer"); //Contains supervisor name and role

    private By applicationListFirstApplicationSupervisorName = By.cssSelector("tr:nth-child(1) .FlexColContainer"); //Contains supervisor name and role

    private By supervisorQuickAgreeAll = By.id("DCDAgentPortalTheme_wt363_block_wtMainContent_wt148_WebPatterns_wt119_block_wtContent_wt227"); //click on next6 after (agentClickNext56Step)

    private By clickNameToLogout = By.cssSelector(".HeaderUserName"); //click this before clickLogout button

    private By clickLogout = By.id("DCDAgentPortalTheme_wt290_block_wtHeader_DCDAgentPortalTheme_wt54_block_WebPatterns_wt13_block_wtMenuLink_wt7"); //logout button

    private By committeConfirmall = By.id("DCDAgentPortalTheme_wt230_block_wtMainContent_WebPatterns_wt158_block_wtContent1_WebPatterns_wt160_block_wtContent_wt292_WebPatterns_wt119_block_wtContent_wt227"); //Only one action was needed

    public String specialistApproval(String applicationRef){
        logManager.STEP("Search application","Inputs the reference number in the search field");
        ActionsHelper.sendKeys(specalistSearchApplication,applicationRef + Keys.ENTER);
        ActionsHelper.actionClickStepClick("Click the application",firstElementAfterSearch);
        ActionsHelper.actionClickStepClick("Approve step 1",agentApproveStep);
        ActionsHelper.actionClickStepClick("Click next Step 1",agentClickNext);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickStepClick("Approve step 2",agentApproveStep);
        ActionsHelper.actionClickStepClick("Click next Step 2",agentClickNext);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickStepClick("Approve step 3",agentApproveStep);
        ActionsHelper.actionClickStepClick("Click next Step 3",agentClickNext);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickStepClick("Approve step 4",agentApproveStep);
        ActionsHelper.actionClickStepClick("Click next Step 4",agentClickNext);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickStepClick("Approve step 5",agentApproveStep);
        ActionsHelper.actionClickStepClick("Click next Step 5",agentClickNext56Step);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickStepClick("Approve step 6",agentApproveStep);
        ActionsHelper.actionClickStepClick("Click next Step 6",agentClickNext56Step);
        ActionsHelper.driverWait(5000);

        //TODO: Check is there is a save button
        driver.get().navigate().to("https://10.231.1.100/DCDAgentFrontEnd/AllApplications.aspx");
        ActionsHelper.sendKeys(specalistSearchApplication,applicationRef + Keys.ENTER);
        return driver.get().findElement(applicationListFirstApplicationSupervisorName).getText();

    }

    public String seniorSpecialistApproval(String refCode){
        driver.get().navigate().to("https://10.231.1.100/DCDAgentFrontEnd/AllApplications.aspx");
        ActionsHelper.sendKeys(specalistSearchApplication,refCode + Keys.ENTER);
        ActionsHelper.actionClickStepClick("Click the application",firstElementAfterSearch);
        ActionsHelper.actionClickStepClick("Click approve all",supervisorQuickAgreeAll);
        driver.get().navigate().to("https://10.231.1.100/DCDAgentFrontEnd/AllApplications.aspx");
        ActionsHelper.sendKeys(specalistSearchApplication,refCode + Keys.ENTER);
        return driver.get().findElement(applicationListFirstApplicationSupervisorName).getText();
    }
    public String committeeSpecialistApproval(String refCode){
        driver.get().navigate().to("https://10.231.1.100/DCDAgentFrontEnd/AllApplications.aspx");
        ActionsHelper.sendKeys(specalistSearchApplication,refCode + Keys.ENTER);
        ActionsHelper.actionClickStepClick("Click the application",firstElementAfterSearch);
        ActionsHelper.actionClickStepClick("Click approve all",committeConfirmall);
        driver.get().navigate().to("https://10.231.1.100/DCDAgentFrontEnd/AllApplications.aspx");
        ActionsHelper.sendKeys(specalistSearchApplication,refCode + Keys.ENTER);
        return driver.get().findElement(applicationListFirstApplicationSupervisorName).getText();
    }

    public void logOut(){
        ActionsHelper.actionClickStepClick("Click username before logging out", clickNameToLogout);
        ActionsHelper.actionClickStepClick("Click logout", clickLogout);
    }

    // css="tr:nth-child(2) > .TableRecords_EvenLine:nth-child(1)" - second ref
    // css="tr:nth-child(3) > .TableRecords_OddLine:nth-child(1)" - third ref
    // css="tr:nth-child(4) > .TableRecords_EvenLine:nth-child(1)" - fourth ref
    // css="tr:nth-child(5) > .TableRecords_OddLine:nth-child(1)" - fifth ref





}

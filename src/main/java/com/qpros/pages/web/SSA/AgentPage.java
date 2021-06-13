package com.qpros.pages.web.SSA;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.pages.web.SSA.applicationreview.PersonalInformation;
import com.ssa.core.model.GetFamilyData;
import com.ssa.core.service.GetFamilyDataService;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.event.KeyEvent;

//This page contains the applications list. Main agent page
public class AgentPage extends Base {


    public AgentPage(WebDriver driver){
        PageFactory.initElements(Base.driver.get(), this);
    }

    private By auditorsManagementNavigation = By.id("DCDAgentPortalTheme_wt290_block_wtMenu_DCDAgentPortalTheme_wt82_block_RichWidgets_wt128_block_wtMenuItem_wt21");

    private By applicationRefFirst = By.cssSelector("#DCDAgentPortalTheme_wt290_block_wtMainContent_wtBenefitRequestsList tr:nth-child(1) > .TableRecords_OddLine:nth-child(1)");

    private By specalistFirstApplication = By.cssSelector("#DCDAgentPortalTheme_wt79_block_wtMainContent_wtListRecords1_ctl00_wt83 .ThemeGrid_Width4:nth-child(3) > span:nth-child(1)"); //Contains app ref number and clickable

    private By specalistSearchApplication = By.xpath("//div[@class='InputContainer FlexRowContainer ThemeGrid_Width4']/input[@class='OSFillParent']"); //Enter search text here (ref code)

    private By committeeSearchApplicationField = By.xpath("DCDAgentPortalTheme_wt79$block$wtFilterContainer$wtSearcFrom"); //Enter search text here (ref code)

    private By committeeSearchApplicationButton = By.cssSelector("[value='بحث']"); //Enter search text here (ref code)


    private By seniorSpecialistSearchApplication = By.xpath("//div[@class='InputContainer FlexRowContainer ThemeGrid_Width5']/input[@class='OSFillParent']");

    private By seniorSpecialistSearchApplication2 = By.xpath("//div[@class='InputContainer FlexRowContainer ThemeGrid_Width4']/input[@class='OSFillParent']");

    private By firstElementAfterSearch = By.cssSelector("#DCDAgentPortalTheme_wt79_block_wtMainContent_wtListRecords1_ctl00_wt83 > div.FlexColContainer > div:nth-child(2) > div:nth-child(1)"); //Contains app ref number and clickable

    private By agentApproveStep = By.id("DCDAgentPortalTheme_wt362_block_wtActions_wtApproveCurrentSection"); //Contains app ref number and clickable

    private By agentRejectStep = By.id("DCDAgentPortalTheme_wt362_block_wtActions_wtrbReject"); //Contains app ref number and clickable

    private By agentClickNext = By.id("DCDAgentPortalTheme_wt362_block_wtActions_wtNext");

    private By agentClickNext56Step = By.id("DCDAgentPortalTheme_wt362_block_wtActions_wtNext6");

    private By applicationListSecondApplicationRefCode = By.cssSelector("tr:nth-child(2) > .TableRecords_EvenLine:nth-child(1)"); //Contains app ref number and clickable

    private By applicationListFirstApplicationRefCode = By.cssSelector("#DCDAgentPortalTheme_wt290_block_wtMainContent_wtBenefitRequestsList tr:nth-child(1) > .TableRecords_OddLine:nth-child(1)"); //Contains app ref number and clickable

    private By applicationListSecondApplicationSupervisorName = By.cssSelector("tr:nth-child(2) .FlexColContainer"); //Contains supervisor name and role

    private By applicationListFirstApplicationSupervisorName = By.cssSelector("tr:nth-child(1) .FlexColContainer"); //Contains supervisor name and role

    private By supervisorQuickAgreeAll = By.id("DCDAgentPortalTheme_wt363_block_wtMainContent_wt148_WebPatterns_wt119_block_wtContent_wt227"); //click on next6 after (agentClickNext56Step)

    private By clickNameToLogout = By.id("DCDAgentPortalTheme_wt15_block_wtHeader_DCDAgentPortalTheme_wt12_block_WebPatterns_wt13_block_wtIcon"); //click this before clickLogout button

    private By clickLogout = By.id("DCDAgentPortalTheme_wt15_block_wtHeader_DCDAgentPortalTheme_wt12_block_WebPatterns_wt13_block_wtMenuLink_wt7"); //logout button

    private By committeConfirmall = By.id("DCDAgentPortalTheme_wt230_block_wtMainContent_WebPatterns_wt158_block_wtContent1_WebPatterns_wt160_block_wtContent_wt292_WebPatterns_wt119_block_wtContent_wt227"); //Only one action was needed

    private By rejectApplication = By.id("DCDAgentPortalTheme_wt362_block_wtMainContent_wt149_WebPatterns_wt119_block_wtContent_wt227"); //Only one action was needed

    private By approveButton = By.xpath("//input[@class='Button Button ApproveButton Button ApproveButton']"); //Only one action was needed

    private By nextButton = By.xpath("//input[@class='Button Button ForwardButton Button ForwardButton']"); //Only one action was needed

    private By specalistSearchApplication2 = By.cssSelector("[placeholder='رقم الطلب ,اسم مقدم الطلب او الرقم الموحد']");

    private By nextButton2 = By.cssSelector("[value='التالي']"); //Only one action was needed

    private By finalButtonApprove = By.cssSelector("[value='الموافقة']"); //Only one action was needed

    private By approveRejectButton = By.xpath("//input[@class='Button Is_Default']"); //Only one action was needed

    private By seniorApproveRejectButton = By.cssSelector("[value='مرفوض - رفض']"); //Only one action was needed


    private By seniorApproveRejectButtonConfirmation = By.cssSelector("[value='مرفوض - رفض']"); //Only one action was needed


    public String specialistApproval(String applicationRef) throws AWTException {

        logManager.STEP("Search application","Inputs the reference number in the search field");
        ActionsHelper.sendKeys(By.id("DCDAgentPortalTheme_wt79_block_wtFilterContainer_wtSearcFrom"),applicationRef + Keys.ENTER);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("Click the application",firstElementAfterSearch);

        // Personal Information Check
        GetFamilyDataService getFamilyDataService= new GetFamilyDataService();
        GetFamilyData getFamilyData= new GetFamilyData();
        try {
            getFamilyDataService.requestService();
            getFamilyData=getFamilyDataService.getresponse(getFamilyDataService);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        PersonalInformation personalInformation= new PersonalInformation();
      //  personalInformation.checkPersonalInformation(getFamilyData);


        ActionsHelper.driverWait(15000);
        ActionsHelper.actionClickScrollStepClick("Approve step 1",agentApproveStep);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("Click next Step 1",agentClickNext);
        ActionsHelper.driverWait(15000);
        ActionsHelper.actionClickStepClick("Approve step 2",agentApproveStep);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("Click next Step 2",agentClickNext);
        ActionsHelper.driverWait(15000);
        ActionsHelper.actionClickStepClick("Approve step 3",agentApproveStep);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("Click next Step 3",agentClickNext);
        ActionsHelper.driverWait(15000);

        ActionsHelper.actionClickStepClick("Approve step 4",agentApproveStep);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("Click next Step 4",agentClickNext);

        ActionsHelper.driverWait(15000);
        System.out.println("Attempting step 5");
        ActionsHelper.retryClick(agentClickNext,4);
        ActionsHelper.driverWait(15000);
        System.out.println("Attempting step 6");
        ActionsHelper.retryClick(agentClickNext56Step,3);

        ActionsHelper.retryClick(nextButton,3);

        ActionsHelper.driverWait(3000);
        //ActionsHelper.retryClick(agentClickNext56Step,3);
        //ActionsHelper.driverWait(5000);
        //ActionsHelper.actionClickStepClick("Approve step 5",agentApproveStep);
        //ActionsHelper.driverWait(3000);
        //ActionsHelper.actionClickStepClick("Click next Step 5",agentClickNext);
        //ActionsHelper.driverWait(5000);
        //ActionsHelper.actionClickStepClick("Approve step 6",agentApproveStep);

        //ActionsHelper.driverWait(3000);
        //ActionsHelper.actionClickStepClick("Click next Step 6",agentClickNext56Step);
        //ActionsHelper.driverWait(5000);
        //ActionsHelper.actionClickStepClick("Approve step 7",agentApproveStep);
        System.out.println("Attempting approve");
        ActionsHelper.retryClick(approveButton,4);
        //ActionsHelper.retryClick(agentClickNext56Step,3);
        ActionsHelper.retryClick(nextButton2,3);
        ActionsHelper.retryClick(finalButtonApprove,3);
        ActionsHelper.driverWait(1000);
        try{driver.get().switchTo().alert().accept();}
        catch (Exception e){}


        //TODO: Check is there is a save button
        driver.get().navigate().to("https://10.231.1.100/DCDAgentFrontEnd/AllApplications.aspx");
        ActionsHelper.driverWait(10000);
        driver.get().navigate().to("https://10.231.1.100/DCDAgentFrontEnd/AllApplications.aspx");
        ActionsHelper.sendKeys(specalistSearchApplication,applicationRef + Keys.ENTER);
        ActionsHelper.driverWait(3000);
        ActionsHelper.isElementPresent(applicationListFirstApplicationSupervisorName);
        return driver.get().findElement(applicationListFirstApplicationSupervisorName).getText();

    }

    public String seniorSpecialistApproval(String refCode){
        //driver.get().navigate().to("https://10.231.1.100/DCDAgentFrontEnd/AllApplications.aspx");
        ActionsHelper.sendKeys(seniorSpecialistSearchApplication,refCode + Keys.ENTER);
        ActionsHelper.actionClickScrollStepClick("Click the application",firstElementAfterSearch);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickScrollStepClick("Click approve all",By.cssSelector("[value='مقبول - قبول']"));
        ActionsHelper.driverWait(10000);
        ActionsHelper.waitForExpectedElement(By.xpath("//input[@class='Button Button ApproveButton Button ApproveButton']"));
        ActionsHelper.scrollTo(By.xpath("//input[@class='Button Button ApproveButton Button ApproveButton']"));
        ActionsHelper.driverWait(3000);
        ActionsHelper.retryClick(By.cssSelector("[value='الموافقة']"),30);
        ActionsHelper.driverWait(5000);
        return getAssigneeNameFromAllApplications(refCode);
    }
    public String committeeSpecialistApproval(String refCode){
       // driver.get().navigate().to("https://10.231.1.100/DCDAgentFrontEnd/AllApplications.aspx");
        ActionsHelper.sendKeys(committeeSearchApplicationField,refCode + Keys.ENTER);
        ActionsHelper.actionClickScrollStepClick("Click the application",firstElementAfterSearch);

        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickScrollStepClick("Click approve all",By.cssSelector("[value='مقبول - قبول']"));
        ActionsHelper.driverWait(10000);
        //ActionsHelper.waitForExpectedElement(By.xpath("//input[@class='Button Button ApproveButton Button ApproveButton']"));
       // ActionsHelper.scrollTo(By.xpath("//input[@class='Button Button ApproveButton Button ApproveButton']"));
        //ActionsHelper.driverWait(3000);
        //ActionsHelper.retryClick(By.cssSelector("[value='الموافقة']"),30);

        return getAssigneeNameFromAllApplications(refCode);

    }
    public String committeeSpecialistRejection(String refCode){
       // driver.get().navigate().to("https://10.231.1.100/DCDAgentFrontEnd/AllApplications.aspx");
        ActionsHelper.sendKeys(committeeSearchApplicationField,refCode + Keys.ENTER);
        ActionsHelper.actionClickScrollStepClick("Click the application",firstElementAfterSearch);

        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickScrollStepClick("Click reject all",By.cssSelector(".RejectButton"));
        ActionsHelper.driverWait(10000);
        //ActionsHelper.waitForExpectedElement(By.xpath("//input[@class='Button Button ApproveButton Button ApproveButton']"));
       // ActionsHelper.scrollTo(By.xpath("//input[@class='Button Button ApproveButton Button ApproveButton']"));
        //ActionsHelper.driverWait(3000);
        //ActionsHelper.retryClick(By.cssSelector("[value='الموافقة']"),30);

        return getAssigneeNameFromAllApplications(refCode);

    }

    public void logOut(){
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickScrollStepClick("Click username before logging out", By.xpath("//span[@class='HeaderUserName']"));
        ActionsHelper.actionClickStepClick("Click logout", By.xpath("//a[.='تسجيل خروج']"));
    }


    public String specialistRejectApplication(String applicationRef){

        logManager.STEP("Search application","Inputs the reference number in the search field");
        ActionsHelper.sendKeys(By.id("DCDAgentPortalTheme_wt79_block_wtFilterContainer_wtSearcFrom"),applicationRef + Keys.ENTER);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("Click the application",firstElementAfterSearch);


        ActionsHelper.driverWait(15000);
        ActionsHelper.actionClickScrollStepClick("Reject step 1",agentRejectStep);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("Click next Step 1",agentClickNext);
        ActionsHelper.driverWait(15000);
        ActionsHelper.actionClickStepClick("Reject step 2",agentRejectStep);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("Click next Step 2",agentClickNext);
        ActionsHelper.driverWait(15000);
        ActionsHelper.actionClickStepClick("Reject step 3",agentRejectStep);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("Click next Step 3",agentClickNext);
        ActionsHelper.driverWait(15000);

        ActionsHelper.actionClickStepClick("Reject step 4",agentRejectStep);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("Click next Step 4",agentClickNext);

        ActionsHelper.driverWait(15000);
        System.out.println("Attempting step 5");
        ActionsHelper.retryClick(agentClickNext,4);
        ActionsHelper.driverWait(15000);
        System.out.println("Attempting step 6");
        ActionsHelper.retryClick(agentClickNext56Step,3);

        ActionsHelper.driverWait(3000);

        ActionsHelper.driverWait(10000);
        System.out.println("Attempting reject");
        ActionsHelper.retryClick(approveButton,4);
        ActionsHelper.retryClick(agentClickNext56Step,3);
        ActionsHelper.driverWait(1000);
        try{driver.get().switchTo().alert().accept();}
        catch (Exception e){}
        ActionsHelper.driverWait(3000);
        driver.get().switchTo().frame(0);
        System.out.println("Trying to hit save");
        ActionsHelper.driverWait(1000);
        ActionsHelper.retryClick(approveRejectButton,10);
        ActionsHelper.driverWait(1000);
        System.out.println("Save clicked");
        driver.get().switchTo().defaultContent();
        ActionsHelper.driverWait(3000);
        //TODO: Check is there is a save button
        driver.get().navigate().to("https://10.231.1.100/DCDAgentFrontEnd/AllApplications.aspx");
        ActionsHelper.driverWait(6000);
        driver.get().navigate().to("https://10.231.1.100/DCDAgentFrontEnd/AllApplications.aspx");
        ActionsHelper.sendKeys(specalistSearchApplication,applicationRef + Keys.ENTER);
        ActionsHelper.driverWait(3000);
        ActionsHelper.isElementPresent(applicationListFirstApplicationSupervisorName);
        return driver.get().findElement(applicationListFirstApplicationSupervisorName).getText();

    }

    public String seniorSpecialistRejectApplication(String refCode){
        ActionsHelper.sendKeys(specalistSearchApplication2,refCode + Keys.ENTER);
        ActionsHelper.actionClickScrollStepClick("Click the application",firstElementAfterSearch);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickScrollStepClick("Reject application",seniorApproveRejectButton);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickScrollStepClick("Confirm Rejection",seniorApproveRejectButtonConfirmation);
        ActionsHelper.driverWait(1000);
        try{ActionsHelper.actionClickScrollStepClick("Confirm Rejection",seniorApproveRejectButtonConfirmation);
            ActionsHelper.driverWait(1000);}
        catch (Exception e){}

        try{driver.get().switchTo().alert().accept();}
        catch (Exception e){}

        ActionsHelper.driverWait(3000);
        driver.get().switchTo().frame(0);
        System.out.println("Trying to hit save");
        ActionsHelper.retryClick(approveRejectButton,10);
        ActionsHelper.driverWait(1000);
        driver.get().switchTo().defaultContent();

        return getAssigneeNameFromAllApplications(refCode);
    }

    // css="tr:nth-child(2) > .TableRecords_EvenLine:nth-child(1)" - second ref
    // css="tr:nth-child(3) > .TableRecords_OddLine:nth-child(1)" - third ref
    // css="tr:nth-child(4) > .TableRecords_EvenLine:nth-child(1)" - fourth ref
    // css="tr:nth-child(5) > .TableRecords_OddLine:nth-child(1)" - fifth ref


    public String getAssigneeNameFromAllApplications(String refCode){
        ActionsHelper.driverWait(15000);
        driver.get().navigate().to("https://10.231.1.100/DCDAgentFrontEnd/AllApplications.aspx");
        ActionsHelper.driverWait(5000);
        System.out.println("First refresh");
        driver.get().navigate().to("https://10.231.1.100/DCDAgentFrontEnd/AllApplications.aspx");
        ActionsHelper.driverWait(5000);
        ActionsHelper.retryClick(By.cssSelector("[placeholder='رقم الطلب ,اسم مقدم الطلب او الرقم الموحد']"),30);
        ActionsHelper.sendKeys(By.cssSelector("[placeholder='رقم الطلب ,اسم مقدم الطلب او الرقم الموحد']"),refCode + Keys.ENTER);
        System.out.println("Assigned to name: " + driver.get().findElement(applicationListFirstApplicationSupervisorName).getText());
        String person = driver.get().findElement(applicationListFirstApplicationSupervisorName).getText();
        return person;
    }



}

package com.qpros.pages.web.SSA;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.pages.web.SSA.applicationreview.PersonalInformation;
import com.ssa.core.common.locators.urls;
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


    public AgentPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }


    private By committeeSearchApplicationField = By.xpath("//input[contains(@id,'SearcFrom')]"); //Enter search text here (ref code)

    private By seniorSpecialistSearchApplication = By.xpath("//div[@class='InputContainer FlexRowContainer ThemeGrid_Width5']/input[@class='OSFillParent']");

    private By seniorSpecialistSearchApplication2 = By.xpath("//div[@class='InputContainer FlexRowContainer ThemeGrid_Width4']/input[@class='OSFillParent']");

    private By firstElementAfterSearch = By.xpath("//*[contains(@id,'wtMainContent_wtListRecords1')]"); //Contains app ref number and clickable

    private By agentApproveStepFinal = By.xpath("//*[contains(@id,'wtActions_wtApproveCurrentSection')]"); //Contains app ref number and clickable

    private By agentSendAgainStepFinal = By.xpath("//*[contains(@id,'wtActions_wtcurrentSectionMoreinfo')]"); //Contains app ref number and clickable

    private By agentRejectButtonFinal = By.xpath("//*[contains(@id,'wtActions_wtrbReject')]");

//TODO: Update with deployement
    private By agentClickNextFinal = By.id("DCDAgentPortalTheme_wt390_block_wtActions_wtNext");

    private By agentClickNext56StepFinal = By.xpath("//*[contains(@id,'wtActions_wtbtn_Next6')]");

    private By applicationListFirstApplicationSupervisorName = By.cssSelector(".FlexColContainer"); //Contains supervisor name and role

    private By approveButton = By.xpath("//input[@class='Button Button ApproveButton Button ApproveButton']"); //Only one action was needed

    private By specalistSearchApplicationFinal = By.xpath("//*[contains(@id,'wttxt_SearcFrom')]");


    private By finalButtonApprove = By.cssSelector("[value='الموافقة']");

    private By approveRejectButton = By.xpath("//input[@class='Button Is_Default']");

    private By seniorApproveRejectButton = By.cssSelector("[value='مرفوض - رفض']"); //Only one action was needed

    private By complexRadioBtn = By.xpath("//*[contains(@id,'wtLockOnComplex5')]"); //Only one action was needed
//wtLockOnComplex5
    private By seniorApproveRejectButtonConfirmation = By.cssSelector("[value='مرفوض - رفض']"); //Only one action was needed

    private By reassessmentCheckBox = By.xpath("//input[contains(@id,'IsCommunicatedbyAssessor')]"); //Only one action was needed

    private By allApllicationsSearchInput = By.xpath("//*[contains(@id,'wtFilterContainer_wttxt_SearchFrom')]"); //Only one action was needed

    private By seniorSpecialsitApproveAll1Final = By.xpath("//*[contains(@id,'btn_AcceptedOrRejected')]"); //Only one action was needed
//wtFilterContainer_wttxt_SearchFrom
//btn_AcceptedOrRejected
    public String specialistApproval(String applicationRef) throws AWTException {

        logManager.STEP("Search application", "Inputs the reference number in the search field");
        ActionsHelper.sendKeys(specalistSearchApplicationFinal, applicationRef + Keys.ENTER);
        ActionsHelper.waitForExpectedElement(firstElementAfterSearch);
        ActionsHelper.driverWait(500);
        ActionsHelper.actionClickStepClick("Click the application", firstElementAfterSearch);

        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickScrollStepClick("Approve step 1", agentApproveStepFinal);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickStepClick("Click next Step 1", agentClickNextFinal);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("Approve step 2", agentApproveStepFinal);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickStepClick("Click next Step 2", agentClickNextFinal);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("Approve step 3", agentApproveStepFinal);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickStepClick("Click next Step 3", agentClickNextFinal);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("Approve step 4", agentApproveStepFinal);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickStepClick("Click next Step 4", agentClickNextFinal);

        ActionsHelper.driverWait(2000);
        System.out.println("Attempting step 5");
        ActionsHelper.retryClick(agentClickNext56StepFinal, 4);
        ActionsHelper.driverWait(2000);
        System.out.println("Attempting step 6");
        ActionsHelper.retryClick(agentClickNext56StepFinal, 3);
        logManager.STEP("Approving the application","Click the confirm button");


        try {
            driver.get().switchTo().alert().accept();
        } catch (Exception e) {
        }


        return getAssigneeNameFromAllApplications(applicationRef);

    }//Finished
    public void specialistSendAgain(String applicationRef) throws AWTException {

        logManager.STEP("Search application", "Inputs the reference number in the search field");
        ActionsHelper.sendKeys(specalistSearchApplicationFinal, applicationRef + Keys.ENTER);
        ActionsHelper.waitForExpectedElement(firstElementAfterSearch);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("Click the application", firstElementAfterSearch);

        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickScrollStepClick("Approve step 1", agentSendAgainStepFinal);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickStepClick("Click next Step 1", agentClickNextFinal);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("Approve step 2", agentSendAgainStepFinal);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickStepClick("Click next Step 2", agentClickNextFinal);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("Approve step 3", agentSendAgainStepFinal);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickStepClick("Click next Step 3", agentClickNextFinal);
        ActionsHelper.driverWait(2000);

        ActionsHelper.actionClickStepClick("Approve step 4", agentSendAgainStepFinal);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickStepClick("Click next Step 4", agentClickNextFinal);

        ActionsHelper.driverWait(2000);
        System.out.println("Attempting step 5");
        ActionsHelper.retryClick(agentClickNext56StepFinal, 4);
        ActionsHelper.driverWait(2000);
        System.out.println("Attempting step 6");
        ActionsHelper.retryClick(agentClickNext56StepFinal, 3);

        ActionsHelper.retryClick(approveButton, 4);
        ActionsHelper.retryClick(finalButtonApprove, 3);
        ActionsHelper.driverWait(1000);
        try {
            driver.get().switchTo().alert().accept();
        } catch (Exception e) {
        }
        ActionsHelper.driverWait(10000);
        driver.get().switchTo().frame(0);
        ActionsHelper.driverWait(1000);
        ActionsHelper.retryClick(By.xpath("//input[contains(@id,'wtListRecords1_ctl00_wt37_wtchk_Reasom')]"), 10);
        ActionsHelper.driverWait(1000);
        ActionsHelper.retryClick(By.cssSelector(".select2-selection__rendered"), 10);
        ActionsHelper.driverWait(1000);
        ActionsHelper.retryClick(By.xpath("//li[contains(@id,'PossibleIndividualIds')]"), 10);
        ActionsHelper.driverWait(1000);
        ActionsHelper.retryClick(By.xpath("//div[contains(@id,'SMS')]"), 10);
        System.out.println("Save clicked");
        ActionsHelper.driverWait(4000);
        ActionsHelper.retryClick(By.xpath("//input[contains(@id,'wtMainContent_wtbtn_Submit')]"), 10);
        ActionsHelper.driverWait(2000);
        driver.get().switchTo().defaultContent();
        driver.get().navigate().refresh();
        ActionsHelper.driverWait(5000);
    }
    public String specialistApproval(String applicationRef, boolean incOrDecApp) throws AWTException {


        logManager.STEP("Search application", "Inputs the reference number in the search field");
        ActionsHelper.sendKeys(specalistSearchApplicationFinal, applicationRef + Keys.ENTER);
        ActionsHelper.waitForExpectedElement(firstElementAfterSearch);
        ActionsHelper.driverWait(500);
        ActionsHelper.actionClickStepClick("Click the application", firstElementAfterSearch);

        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickScrollStepClick("Approve step 1", agentApproveStepFinal);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickStepClick("Click next Step 1", agentClickNextFinal);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("Approve step 2", agentApproveStepFinal);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickStepClick("Click next Step 2", agentClickNextFinal);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("Approve step 3", agentApproveStepFinal);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickStepClick("Click next Step 3", agentClickNextFinal);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("Approve step 4", agentApproveStepFinal);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickStepClick("Click next Step 4", agentClickNextFinal);

        ActionsHelper.driverWait(2000);
        if (incOrDecApp) {
            ActionsHelper.actionClickScrollStepClick("Make application complex radio button", complexRadioBtn);
            ActionsHelper.driverWait(5000);
        }
        System.out.println("Attempting step 5");
        ActionsHelper.retryClick(agentClickNext56StepFinal, 4);
        ActionsHelper.driverWait(2000);
        System.out.println("Attempting step 6");
        ActionsHelper.retryClick(agentClickNext56StepFinal, 3);
        logManager.STEP("Approving the application","Click the confirm button");


        try {
            driver.get().switchTo().alert().accept();
        } catch (Exception e) {
        }


        return getAssigneeNameFromAllApplications(applicationRef);
    }//Finished
    public String seniorSpecialistApproval(String refCode) {
        //driver.get().navigate().to("https://uat.ssa.gov.ae/DCDAgentFrontEnd/AllApplications.aspx");
        ActionsHelper.sendKeys(specalistSearchApplicationFinal, refCode + Keys.ENTER);
        ActionsHelper.actionClickScrollStepClick("Click the application", firstElementAfterSearch);
        ActionsHelper.driverWait(1000);
        ActionsHelper.actionClickScrollStepClick("Click approve all", seniorSpecialsitApproveAll1Final);
        ActionsHelper.driverWait(5000);
        ActionsHelper.waitForExpectedElement(agentClickNext56StepFinal);
        ActionsHelper.scrollTo(agentClickNext56StepFinal);
        ActionsHelper.driverWait(1000);
        ActionsHelper.retryClick(agentClickNext56StepFinal, 10);
        ActionsHelper.driverWait(5000);
        return getAssigneeNameFromAllApplications(refCode);
    } //Finished
    public String seniorSpecialistApprovalIncDec(String refCode) {
        //driver.get().navigate().to("https://uat.ssa.gov.ae/DCDAgentFrontEnd/AllApplications.aspx");
        ActionsHelper.sendKeys(seniorSpecialistSearchApplication, refCode + Keys.ENTER);
        ActionsHelper.actionClickScrollStepClick("Click the application", firstElementAfterSearch);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickScrollStepClick("Click on reassessment",reassessmentCheckBox);
        ActionsHelper.driverWait(1000);
        ActionsHelper.actionClickScrollStepClick("Click approve all", By.cssSelector("[value='مقبول - قبول']"));
        ActionsHelper.driverWait(10000);
        ActionsHelper.waitForExpectedElement(By.cssSelector("[value='الموافقة']"));
        ActionsHelper.scrollTo(By.cssSelector("[value='الموافقة']"));
        ActionsHelper.driverWait(3000);
        ActionsHelper.retryClick(By.cssSelector("[value='الموافقة']"), 30);
        ActionsHelper.driverWait(5000);
        return getAssigneeNameFromAllApplications(refCode);
    }
    public void committeeSpecialistApproval(String refCode) {
        // driver.get().navigate().to("https://uat.ssa.gov.ae/DCDAgentFrontEnd/AllApplications.aspx");
        ActionsHelper.sendKeys(specalistSearchApplicationFinal, refCode + Keys.ENTER);
        ActionsHelper.driverWait(1000);
        ActionsHelper.actionClickScrollStepClick("Click the application", firstElementAfterSearch);

        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickScrollStepClick("Click approve all", seniorSpecialsitApproveAll1Final);
        ActionsHelper.driverWait(3000);

    }//Finished
    public String committeeSpecialistRejection(String refCode) {

        ActionsHelper.sendKeys(committeeSearchApplicationField, refCode + Keys.ENTER);
        ActionsHelper.actionClickScrollStepClick("Click the application", firstElementAfterSearch);

        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickScrollStepClick("Click reject all", By.cssSelector(".RejectButton"));
        ActionsHelper.driverWait(2000);

        return getAssigneeNameFromAllApplications(refCode);

    }
    public void logOut() {
        ActionsHelper.driverWait(5000);
        try {
            ActionsHelper.actionClickScrollStepClick("Click username before logging out", By.xpath("//span[@class='HeaderUserName']"));
            ActionsHelper.actionClickStepClick("Click logout", By.xpath("//a[.='تسجيل خروج']"));
        } catch (Exception e) {
            ActionsHelper.actionClickScrollStepClick("Click logout", By.xpath("//div[contains(@id,'Logout')]"));
        }
    }//Finished
    public String specialistRejectApplication(String applicationRef) {

        logManager.STEP("Search application", "Inputs the reference number in the search field");
        ActionsHelper.sendKeys(specalistSearchApplicationFinal, applicationRef + Keys.ENTER);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("Click the application", firstElementAfterSearch);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickScrollStepClick("Reject step 1", agentRejectButtonFinal);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickStepClick("Click next Step 1", agentClickNextFinal);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("Reject step 2", agentRejectButtonFinal);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickStepClick("Click next Step 2", agentClickNextFinal);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("Reject step 3", agentRejectButtonFinal);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickStepClick("Click next Step 3", agentClickNextFinal);
        ActionsHelper.driverWait(2000);

        ActionsHelper.actionClickStepClick("Reject step 4", agentRejectButtonFinal);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickStepClick("Click next Step 4", agentClickNextFinal);

        ActionsHelper.driverWait(2000);
        System.out.println("Attempting step 5");
        ActionsHelper.retryClick(agentClickNext56StepFinal, 4);
        ActionsHelper.driverWait(2000);
        System.out.println("Attempting step 6");
        ActionsHelper.retryClick(agentClickNext56StepFinal, 3);

        ActionsHelper.driverWait(5000);

        ActionsHelper.driverWait(10000);
        System.out.println("Attempting reject");
        ActionsHelper.retryClick(approveButton, 4);
        ActionsHelper.retryClick(agentClickNext56StepFinal, 3);
        ActionsHelper.driverWait(1000);
        try {
            driver.get().switchTo().alert().accept();
        } catch (Exception e) {
        }
        ActionsHelper.driverWait(3000);
        driver.get().switchTo().frame(0);
        System.out.println("Trying to hit save");
        ActionsHelper.driverWait(1000);
        ActionsHelper.retryClick(approveRejectButton, 10);
        ActionsHelper.driverWait(1000);
        System.out.println("Save clicked");
        driver.get().switchTo().defaultContent();
        ActionsHelper.driverWait(3000);
        return getAssigneeNameFromAllApplications(applicationRef);

    } //Finished
    public String seniorSpecialistRejectApplication(String refCode) {
        ActionsHelper.sendKeys(specalistSearchApplicationFinal, refCode + Keys.ENTER);
        ActionsHelper.actionClickScrollStepClick("Click the application", firstElementAfterSearch);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickScrollStepClick("Reject application", seniorApproveRejectButton);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickScrollStepClick("Confirm Rejection", seniorApproveRejectButtonConfirmation);
        ActionsHelper.driverWait(1000);
        try {
            ActionsHelper.actionClickScrollStepClick("Confirm Rejection", seniorApproveRejectButtonConfirmation);
            ActionsHelper.driverWait(1000);
        } catch (Exception e) {
        }

        try {
            driver.get().switchTo().alert().accept();
        } catch (Exception e) {
        }

        ActionsHelper.driverWait(3000);
        driver.get().switchTo().frame(0);
        System.out.println("Trying to hit save");
        ActionsHelper.retryClick(approveRejectButton, 10);
        ActionsHelper.driverWait(1000);
        driver.get().switchTo().defaultContent();

        return getAssigneeNameFromAllApplications(refCode);
    }
    public String getAssigneeNameFromAllApplications(String refCode) {
        ActionsHelper.driverWait(10000);
        driver.get().navigate().to(urls.allApplications);
        ActionsHelper.driverWait(10000);
        logManager.STEP("Refresh","Refreshes the page");
        driver.get().navigate().to(urls.allApplications);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(allApllicationsSearchInput, 5);
        ActionsHelper.sendKeys(allApllicationsSearchInput, refCode + Keys.ENTER);
        String person;
        try {
            System.out.println("Assigned to name: " + driver.get().findElement(applicationListFirstApplicationSupervisorName).getText());
            person = driver.get().findElement(applicationListFirstApplicationSupervisorName).getText();
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            System.out.println("Assigned to name: " + driver.get().findElement(applicationListFirstApplicationSupervisorName).getText());
            person = driver.get().findElement(applicationListFirstApplicationSupervisorName).getText();
        }
        logManager.STEP("Get next person name","Reads the username of the next stage of the process");
        return person;
    }//Finished


}

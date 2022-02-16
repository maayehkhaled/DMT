package com.qpros.pages.web.SSA;

import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.ssa.core.common.locators.urls;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

//This page contains the applications list. Main agent page
public class AgentPage extends Base {


    public AgentPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }


    private final By committeeSearchApplicationField = By.xpath("//input[contains(@id,'SearcFrom')]"); //Enter search text here (ref code)
    private final By seniorSpecialistSearchApplication = By.xpath("//div[@class='InputContainer FlexRowContainer ThemeGrid_Width5']/input[@class='OSFillParent']");
    private final By firstElementAfterSearch = By.xpath("//*[contains(@id,'wtMainContent_wtListRecords1')]"); //Contains app ref number and clickable
    private final By agentApproveStepFinal = By.xpath("//input[contains(@id,'wtActions_wtApproveCurrentSection')]"); //Contains app ref number and clickable
    private final By agentSendAgainStepFinal = By.xpath("//*[contains(@id,'wtActions_wtcurrentSectionMoreinfo')]"); //Contains app ref number and clickable
    private final By agentRejectButtonFinal = By.xpath("//*[contains(@id,'wtActions_wtrbReject')]");
    private final By rejectBtn = By.xpath("//input[@value=\"مرفوض - رفض\"]");
//TODO: Update with deployement          //input[@id="InternalPortalTheme_wt397_block_wtActions_wtbtn_Next6"]
    private final By approveApp=By.xpath("//input[@class='Button Button ApproveButton Button ApproveButton']");
    private final By nextButton=By.xpath("//*[contains(@class,'ForwardButton')]");
    private final By increaseNextButton=By.xpath("//div[contains(@class,'Button ForwardButton')]");
    private final By summaryNextBtn = By.xpath("//input[@id=\"InternalPortalTheme_wt573_block_wtActions_wtbtn_Next6\"]");
    private final By clickNext=By.xpath("//input[@value='التالي']");
    private final By agentClickNextFinal = By.xpath("//div[contains(@class,'ForwardButton')]");
    private final By agentClickNext = By.xpath("//input[contains(@id,'Next6')]");
    private final By agentClickNext56StepFinal = By.xpath("//*[@id=\"InternalPortalTheme_wt397_block_wtActions_wtOperationBtnContainer\"]/div[2]");
    private final By applicationListFirstApplicationSpecialistName = By.xpath("//tbody//tr//td//div[@class=\"FlexColContainer\"]//span[1]");
    private final By approveButton = By.xpath("//input[@class='Button Button ApproveButton Button ApproveButton']"); //Only one action was needed
    private final By specalistSearchApplicationFinal = By.xpath("//*[contains(@id,'wttxt_SearcFrom')]");
    private final By finalButtonApprove = By.cssSelector("[value='الموافقة']");
    private final By approveRejectButton = By.xpath("//input[@class='Button Is_Default']");
    private final By seniorApproveRejectButton = By.cssSelector("[value='مرفوض - رفض']"); //Only one action was needed
    private final By complexRadioBtn = By.xpath("//*[contains(@id,'wtLockOnComplex5')]"); //Only one action was needed
//wtLockOnComplex5
    private final By seniorApproveRejectButtonConfirmation = By.cssSelector("[value='مرفوض - رفض']"); //Only one action was needed
    private final By reassessmentCheckBox = By.xpath("//input[contains(@id,'IsCommunicatedbyAssessor')]"); //Only one action was needed
    private final By allApllicationsSearchInput = By.xpath("//input[contains(@id,'wttxt_SearchFrom')]"); //Only one action was needed
    private final By seniorSpecialsitApproveAll1Final = By.xpath("//*[contains(@id,'btn_AcceptedOrRejected')]"); //Only one action was needed
//wtFilterContainer_wttxt_SearchFrom
//btn_AcceptedOrRejected
    private final By approvalBtn = By.cssSelector("[value='الموافقة']");
    private final By approveAll = By.cssSelector("[value='مقبول - قبول']");
    private final By userNameBeforeLogout = By.xpath("//span[@class='HeaderUserName']");
    private final By logout1 = By.xpath("//a[.='تسجيل خروج']");
    private final By logout2 = By.xpath("//div[contains(@id,'Logout')]");
    private final By appealApplicationCheckbox = By.xpath("//input[@id=\"InternalPortalTheme_wt85_block_wtFilterContainer_wt67\"]");
    private final By  reValueButton=By.xpath("//input[@type='checkbox']");
    private By previousViewLink=By.xpath("//a[contains(@id,'wt57')]");

    /**
     * TO CREATE APPROVAL USING specialist USER
     * @param applicationRef This is SSP of the app
     * @return its return the next assignee
     */
    public String specialistApproval(String applicationRef) {
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
        ActionsHelper.driverWait(4000);
        //
        ActionsHelper.retryClick(By.xpath("//input[contains(@id,'Next6')]"),30);
        ActionsHelper.driverWait(4000);
        System.out.println("Attempting step 5");
        ActionsHelper.retryClick(finalButtonApprove, 4);
        ActionsHelper.driverWait(2000);
        System.out.println("Attempting step 6");
        ActionsHelper.retryClick(finalButtonApprove, 3);
        logManager.STEP("Approving the application","Click the confirm button");
        try {
            driver.get().switchTo().alert().accept();
        } catch (Exception e) {
            logManager.WARN("could not interact with popup msg");
        }
        return getAssigneeNameFromAllApplications(applicationRef);
    }

    /**
     *
     * TO CREATE APPEAl Approval USING specialist USER
     *      * @param applicationRef This is SSP of the app
     *      * @return its return the next assignee
     */
    public String specialistAppealApproval(String refCode){
        logManager.STEP("Search application", "Inputs the reference number in the search field");
        ActionsHelper.driverWait(4000);
        ActionsHelper.waitForExpectedElement(appealApplicationCheckbox);
        ActionsHelper.clickAction(appealApplicationCheckbox);
        ActionsHelper.sendKeys(specalistSearchApplicationFinal, refCode + Keys.ENTER);
        ActionsHelper.waitForExpectedElement(firstElementAfterSearch);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickScrollStepClick("Click the application", firstElementAfterSearch);
        ActionsHelper.driverWait(4000);
        ActionsHelper.actionClickScrollStepClick("Click approve all", seniorSpecialsitApproveAll1Final);
        ActionsHelper.driverWait(5000);
        ActionsHelper.waitForExpectedElement(summaryNextBtn);
        //ActionsHelper.scrollTo(agentClickNext56StepFinal);
        ActionsHelper.driverWait(1000);
        ActionsHelper.retryClick(summaryNextBtn, 10);
        ActionsHelper.driverWait(8000);
        try {
            driver.get().switchTo().alert().accept();
        } catch (Exception e) {
            logManager.WARN("could not interact with popup msg");
        }
        return getAssigneeNameFromAllApplications(refCode);
    }

    /**
     *
     * TO CREATE APPEAl rejection USING specialist USER
     *      *      * @param applicationRef This is SSP of the app
     *      *      * @return its return the next assignee
     */
    public String specialistAppealRejection(String refCode){
        logManager.STEP("Search application", "Inputs the reference number in the search field");
        ActionsHelper.driverWait(4000);
        ActionsHelper.waitForExpectedElement(appealApplicationCheckbox);
        ActionsHelper.clickAction(appealApplicationCheckbox);
        ActionsHelper.sendKeys(specalistSearchApplicationFinal, refCode + Keys.ENTER);
        ActionsHelper.waitForExpectedElement(firstElementAfterSearch);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickScrollStepClick("Click the application", firstElementAfterSearch);
        ActionsHelper.driverWait(4000);
        ActionsHelper.actionClickScrollStepClick("Click on reject this information", agentRejectButtonFinal);
        ActionsHelper.driverWait(7000);
        ActionsHelper.actionClickScrollStepClick("Click on rejection Button", rejectBtn);
        ActionsHelper.driverWait(4000);
        ActionsHelper.waitForExpectedElement(summaryNextBtn);
        ActionsHelper.driverWait(1000);
        ActionsHelper.retryClick(summaryNextBtn, 10);
        ActionsHelper.driverWait(8000);
        try {
            driver.get().switchTo().alert().accept();
        } catch (Exception e) {
            logManager.WARN("does not interact with popup msg");
        }
        return getAssigneeNameFromAllApplications(refCode);
    }

    /**
     *
     * TO CREATE Acoc Approval USING specialist USER
     *      *      * @param applicationRef This is SSP of the app
     *      *      * @return its return the next assignee
     */
    public String specialistAcocApproval(String applicationRef)  {
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
        ActionsHelper.driverWait(4000);
        System.out.println("Attempting step 5");
        ActionsHelper.retryClick(agentClickNext, 4);
        ActionsHelper.driverWait(4000);
        System.out.println("Attempting step 6");
        ActionsHelper.retryClick(agentClickNext, 3);
        logManager.STEP("Approving the application","Click the confirm button");
        try {
            driver.get().switchTo().alert().accept();
        } catch (Exception e) {
            logManager.WARN("does not interact with popup msg");
        }

        return getAssigneeNameFromAllApplications(applicationRef);


    }
    /**
     * send approval again
     * @param applicationRef its SSP for this App
     */
    public void specialistSendAgain(String applicationRef)  {
        logManager.STEP("Search application", "Inputs the reference number in the search field");
        ActionsHelper.sendKeys(specalistSearchApplicationFinal, applicationRef + Keys.ENTER);
        ActionsHelper.waitForExpectedElement(firstElementAfterSearch);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("Click the application", firstElementAfterSearch);
        ActionsHelper.driverWait(8000);
        ActionsHelper.actionClickScrollStepClick("Approve step 1", agentSendAgainStepFinal);
        ActionsHelper.driverWait(8000);
        ActionsHelper.actionClickStepClick("Click next Step 1", agentClickNextFinal);
        ActionsHelper.driverWait(8000);
        ActionsHelper.actionClickStepClick("Approve step 2", agentSendAgainStepFinal);
        ActionsHelper.driverWait(8000);
        ActionsHelper.actionClickStepClick("Click next Step 2", agentClickNextFinal);
        ActionsHelper.driverWait(8000);
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
            logManager.WARN("does mot interact with popup msg");
        }
        ActionsHelper.driverWait(10000);
        driver.get().switchTo().frame(0);
        ActionsHelper.driverWait(1000);
        ActionsHelper.retryClick(By.xpath("//input[@id=\"CloneOfWebPatterns_wt35_block_wtMainContent_wtListRecords1_ctl00_wt15_wtchk_Reasom\"]"), 10);
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

    /**
     *
     *  * TO CREATE APPROVAL USING specialist USER
     *      * @param applicationRef This is SSP of the app/inOrDecApp its a boolean take true or false
     *      * @return its return the next assignee
     */
    public String specialistApproval(String applicationRef, boolean incOrDecApp) {
        logManager.STEP("Search application", "Inputs the reference number in the search field");
        ActionsHelper.sendKeys(specalistSearchApplicationFinal, applicationRef + Keys.ENTER);
        ActionsHelper.waitForExpectedElement(firstElementAfterSearch, 30);
        ActionsHelper.driverWait(10000);
        ActionsHelper.actionClickStepClick("Click the application", firstElementAfterSearch);
        ActionsHelper.driverWait(10000);
        ActionsHelper.actionClickScrollStepClick("Approve Personal Information", agentApproveStepFinal);
        ActionsHelper.driverWait(10000);
        ActionsHelper.actionClickStepClick("Click next Step 1", agentClickNextFinal);
        ActionsHelper.driverWait(10000);
        ActionsHelper.actionClickStepClick("Approve Family Information", agentApproveStepFinal);
        ActionsHelper.driverWait(10000);
        ActionsHelper.actionClickStepClick("Click next Step 2", agentClickNextFinal);
        ActionsHelper.driverWait(10000);
        ActionsHelper.actionClickStepClick("Approve Address", agentApproveStepFinal);
        ActionsHelper.driverWait(10000);
        ActionsHelper.actionClickStepClick("Click next Step 3", agentClickNextFinal);
        ActionsHelper.driverWait(10000);
        ActionsHelper.actionClickStepClick("Approve Income Info", agentApproveStepFinal);
        ActionsHelper.driverWait(10000);
        ActionsHelper.actionClickStepClick("Click next Step 4", agentClickNextFinal);
        ActionsHelper.driverWait(12000);
        if (incOrDecApp) {
            ActionsHelper.actionClickScrollStepClick("Make application complex radio button", complexRadioBtn);
            ActionsHelper.driverWait(8000);
        }
        System.out.println("Attempting step 5");
        ActionsHelper.driverWait(10000);
        ActionsHelper.actionClickStepClick("Click Next Step 5", nextButton);
        System.out.println("Attempting step 6");
        ActionsHelper.driverWait(5000);
        ActionsHelper.retryClick(finalButtonApprove, 10);
        logManager.STEP("Approving the application", "Click the confirm button");
        try {
            driver.get().switchTo().alert().accept();
        } catch (Exception e) {
            logManager.WARN("must be approve the app ");
        }
        return getAssigneeNameFromAllApplications(applicationRef);
    }

    /**
     *
     * * TO CREATE APPROVAL for increase and decrease USING specialist USER
     *      * @param applicationRef This is SSP of the app
     *      * @return its return the next assignee
     */
    public String specialistApproveForIncrease(String applicationRef)
    {
        logManager.STEP("Search application", "Inputs the reference number in the search field");
        ActionsHelper.sendKeys(specalistSearchApplicationFinal, applicationRef + Keys.ENTER);
        ActionsHelper.waitForExpectedElement(firstElementAfterSearch, 30);
        ActionsHelper.driverWait(10000);
        ActionsHelper.actionClickStepClick("Click the application", firstElementAfterSearch);
        ActionsHelper.driverWait(10000);
        ActionsHelper.actionClickScrollStepClick("click on اعادة التقييم",reValueButton);
        ActionsHelper.driverWait(10000);
        ActionsHelper.actionClickScrollStepClick("Approve Personal Information", agentApproveStepFinal);
        ActionsHelper.driverWait(10000);
        ActionsHelper.actionClickStepClick("Click next Step 1", increaseNextButton);
        ActionsHelper.driverWait(10000);
        ActionsHelper.actionClickStepClick("Approve Family Information", agentApproveStepFinal);
        ActionsHelper.driverWait(10000);
        ActionsHelper.actionClickStepClick("Click next Step 2", increaseNextButton);
        ActionsHelper.driverWait(10000);
        ActionsHelper.actionClickStepClick("Approve Address", agentApproveStepFinal);
        ActionsHelper.driverWait(10000);
        ActionsHelper.actionClickStepClick("Click next Step 3", increaseNextButton);
        ActionsHelper.driverWait(10000);
        ActionsHelper.actionClickStepClick("Approve Income Info", agentApproveStepFinal);
        ActionsHelper.driverWait(10000);
        ActionsHelper.actionClickStepClick("Click next Step 4", increaseNextButton);
        ActionsHelper.driverWait(12000);
        //ActionsHelper.actionClickStepClick("Approve step 5", agentApproveStepFinal);
        System.out.println("Attempting step 5");
        //ActionsHelper.retryClick(agentClickNext56StepFinal, 4);
        ActionsHelper.driverWait(10000);
        ActionsHelper.actionClickStepClick("Click Next Step 5", clickNext);
        System.out.println("Attempting step 6");
        //ActionsHelper.actionClickStepClick("Click Approval and Agree", summaryNextBtn);
        ActionsHelper.driverWait(10000);
        ActionsHelper.retryClick(finalButtonApprove, 10);
        logManager.STEP("Approving the application","Click the confirm button");
        try {
            driver.get().switchTo().alert().accept();
        } catch (Exception e) {
            logManager.WARN("must be approve the app ");
        }
        return getAssigneeNameFromAllApplications(applicationRef);
    }

    /**
     *
     *  * TO CREATE APPROVAL USING senior specialist USER
     *      * @param applicationRef This is SSP of the app
     *      * @return its return the next assignee
     */
    public String seniorSpecialistApproval(String refCode) {
        //driver.get().navigate().to("https://uat.ssa.gov.ae/DCDAgentFrontEnd/AllApplications.aspx");
        ActionsHelper.driverWait(3000);
        ActionsHelper.sendKeys(specalistSearchApplicationFinal, refCode + Keys.ENTER);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickScrollStepClick("Click the application", firstElementAfterSearch);
        ActionsHelper.driverWait(6000);
        ActionsHelper.retryClick(previousViewLink,30);
        ActionsHelper.scrollTo(seniorSpecialsitApproveAll1Final);
        ActionsHelper.driverWait(4000);
        ActionsHelper.actionClickScrollStepClick("Click approve all", seniorSpecialsitApproveAll1Final);
        ActionsHelper.driverWait(10000);
        ActionsHelper.scrollTo(approveApp);
        ActionsHelper.driverWait(6000);
        ActionsHelper.actionClickStepClick("click on next button",approveApp);
        // ActionsHelper.waitForExpectedElement(summaryNextBtn);
        //ActionsHelper.scrollTo(agentClickNext56StepFinal);
        ActionsHelper.driverWait(10000);
        ActionsHelper.retryClick(nextButton, 10);
        ActionsHelper.driverWait(8000);
        //ActionsHelper.waitForExpectedElement(agreementBtn);
        //ActionsHelper.scrollTo(agreementBtn);
        //ActionsHelper.clickAction(agreementBtn);
        try {
            driver.get().switchTo().alert().accept();
            ActionsHelper.driverWait(2000);
            driver.get().switchTo().alert().accept();
        } catch (Exception e) {
            logManager.WARN("must be approve the app ");
        }
        return getAssigneeNameFromAllApplications(refCode);
    }

    /**
     *
     *  * TO CREATE APPROVAL Appeal USING senior specialist USER
     *      * @param applicationRef This is SSP of the app
     *      * @return its return the next assignee
     */
    public String seniorSpecialistAppealApproval(String refCode) {
        //driver.get().navigate().to("https://uat.ssa.gov.ae/DCDAgentFrontEnd/AllApplications.aspx");
        logManager.STEP("Search application", "Inputs the reference number in the search field");
        ActionsHelper.driverWait(4000);
        ActionsHelper.waitForExpectedElement(appealApplicationCheckbox);
        ActionsHelper.clickAction(appealApplicationCheckbox);
        ActionsHelper.sendKeys(specalistSearchApplicationFinal, refCode + Keys.ENTER);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickScrollStepClick("Click the application", firstElementAfterSearch);
        ActionsHelper.driverWait(4000);
        ActionsHelper.actionClickScrollStepClick("Click approve all", seniorSpecialsitApproveAll1Final);
        ActionsHelper.driverWait(5000);
        ActionsHelper.waitForExpectedElement(summaryNextBtn);
        //ActionsHelper.scrollTo(agentClickNext56StepFinal);
        ActionsHelper.driverWait(1000);
        ActionsHelper.retryClick(summaryNextBtn, 10);
        ActionsHelper.driverWait(8000);
        //ActionsHelper.waitForExpectedElement(agreementBtn);
        //ActionsHelper.scrollTo(agreementBtn);
        //ActionsHelper.clickAction(agreementBtn);
        try {

            driver.get().switchTo().alert().accept();
        } catch (Exception e) {
            logManager.WARN("does not interact with popup msg");
        }
        return getAssigneeNameFromAllApplications(refCode);
    }

    /**
     * TO CREATE APPROVAL USING senior specialist USER for increase and decrease payment
     * @param refCode This is SSP of the app
     * @return its return the next assignee
     */

    public String seniorSpecialistApprovalIncDec(String refCode) {
        //driver.get().navigate().to("https://uat.ssa.gov.ae/DCDAgentFrontEnd/AllApplications.aspx");
        ActionsHelper.sendKeys(seniorSpecialistSearchApplication, refCode + Keys.ENTER);
        ActionsHelper.actionClickScrollStepClick("Click the application", firstElementAfterSearch);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickScrollStepClick("Click on reassessment",reassessmentCheckBox);
        ActionsHelper.driverWait(1000);
        ActionsHelper.actionClickScrollStepClick("Click approve all", approveAll);
        ActionsHelper.driverWait(10000);
        ActionsHelper.waitForExpectedElement(approvalBtn);
        ActionsHelper.scrollTo(approvalBtn);
        ActionsHelper.driverWait(3000);
        ActionsHelper.retryClick(approvalBtn, 30);
        ActionsHelper.driverWait(5000);
        return getAssigneeNameFromAllApplications(refCode);
    }

    /**
     *
     *  * TO CREATE APPROVAL USING committee USER
     *      * @param applicationRef This is SSP of the app
     *
     */

    public void committeeSpecialistApproval(String refCode) {
        // driver.get().navigate().to("https://uat.ssa.gov.ae/DCDAgentFrontEnd/AllApplications.aspx");
        ActionsHelper.sendKeys(specalistSearchApplicationFinal, refCode + Keys.ENTER);
        ActionsHelper.driverWait(4000);
        ActionsHelper.actionClickScrollStepClick("Click the application", firstElementAfterSearch);
        ActionsHelper.driverWait(8000);
        ActionsHelper.retryClick(previousViewLink,30);
        ActionsHelper.actionClickScrollStepClick("Click approve all", seniorSpecialsitApproveAll1Final);
        ActionsHelper.driverWait(3000);
    }

    /**
     *
     ** TO CREATE reject APPROVAL USING committee USER
     *   * @param applicationRef This is SSP of the app
     *   return to return for any user assignee
     */
    public String committeeSpecialistRejection(String refCode) {

        ActionsHelper.sendKeys(committeeSearchApplicationField, refCode + Keys.ENTER);
        ActionsHelper.actionClickScrollStepClick("Click the application", firstElementAfterSearch);

        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickScrollStepClick("Click reject all", By.cssSelector(".RejectButton"));
        ActionsHelper.driverWait(2000);

        return getAssigneeNameFromAllApplications(refCode);

    }

    /**
     * to logout from pages
     */
    public void logOut() {
        ActionsHelper.driverWait(5000);
        try {
            ActionsHelper.actionClickScrollStepClick("Click username before logging out", userNameBeforeLogout);
            ActionsHelper.actionClickStepClick("Click logout", logout1);
        } catch (Exception e) {
            ActionsHelper.actionClickScrollStepClick("Click logout", logout2);
        }

    }
    /**
     *
     ** TO CREATE reject APPROVAL USING specialist USER
     *    * @param applicationRef This is SSP of the app
     *    return to return for any user assignee
     */

    public void logOut2()
    {

        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickStepClick("click on logout",userNameBeforeLogout);
        ActionsHelper.driverWait(4000);
    }



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
            logManager.DEBUG("must reject the app ");
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

    }

    /**
     *
     * TO CREATE reject APPROVAL USING senior specialist USER
     *      *    * @param applicationRef This is SSP of the app
     *      *    return to return for any user assignee
     */

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
            logManager.WARN("does not interact with confirm Rejection");
        }

        try {
            driver.get().switchTo().alert().accept();
        } catch (Exception e) {
            logManager.WARN("does not interact with popup msg ");
        }

        ActionsHelper.driverWait(3000);
        driver.get().switchTo().frame(0);
        System.out.println("Trying to hit save");
        ActionsHelper.retryClick(approveRejectButton, 10);
        ActionsHelper.driverWait(1000);
        driver.get().switchTo().defaultContent();

        return getAssigneeNameFromAllApplications(refCode);
    }

   

    /**
     * to get assignee user
     * @param refCode this is a SSP for this App
     * @return its return for any user assignee
     */

    public String getAssigneeNameFromAllApplications(String refCode) {
        ActionsHelper.driverWait(10000);
        ActionsHelper.navigate(urls.allApplications);
        ActionsHelper.driverWait(10000);
        logManager.STEP("Refresh","Refreshes the page");
        ActionsHelper.navigate(urls.allApplications);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(allApllicationsSearchInput, 5);
        ActionsHelper.sendKeysWithClear(allApllicationsSearchInput, refCode + Keys.ENTER );
        ActionsHelper.driverWait(4000);
        String person;
        try {
            System.out.println("Assigned to name: " + driver.get().findElement(applicationListFirstApplicationSpecialistName).getText());
            person = driver.get().findElement(applicationListFirstApplicationSpecialistName).getText();
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            System.out.println("Assigned to name: " + driver.get().findElement(applicationListFirstApplicationSpecialistName).getText());
            person = driver.get().findElement(applicationListFirstApplicationSpecialistName).getText();
        }
        logManager.STEP("Get next person name","Reads the username of the next stage of the process");
        return person;
    }


/* duplicated
    public String getAssigneeNameForSpecialist(String refCode) {
        ActionsHelper.driverWait(10000);
        driver.get().navigate().to(urls.allApplications);
        ActionsHelper.driverWait(10000);
        logManager.STEP("Refresh","Refreshes the page");
        driver.get().navigate().to(urls.allApplications);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(allApllicationsSearchInput, 5);
        ActionsHelper.sendKeysWithClear(allApllicationsSearchInput, refCode + Keys.ENTER );
        ActionsHelper.driverWait(4000);
        String person;
        try {
            System.out.println("Assigned to name: " + driver.get().findElement(applicationListFirstApplicationSpecialistName).getText());
            person = driver.get().findElement(applicationListFirstApplicationSpecialistName).getText();
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            System.out.println("Assigned to name: " + driver.get().findElement(applicationListFirstApplicationSpecialistName).getText());
            person = driver.get().findElement(applicationListFirstApplicationSpecialistName).getText();
        }
        logManager.STEP("Get next person name","Reads the username of the next stage of the process");
        return person;
    }
*/


}

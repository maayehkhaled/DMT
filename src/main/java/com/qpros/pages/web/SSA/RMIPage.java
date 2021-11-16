package com.qpros.pages.web.SSA;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.quanta.Status;
import com.qpros.quanta.markuputils.MarkupHelper;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.common.data.TestData;
import com.ssa.core.common.locators.urls;
import com.ssa.core.service.SubmitApplicationService;
import com.ssa.core.service.VerifyEligibilityService;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import sun.security.mscapi.CPublicKey;

import java.awt.*;
import java.util.List;

public class RMIPage extends Base {

    public RMIPage(WebDriver driver){PageFactory.initElements(Base.driver.get(), this);}

    SubmitApplicationService submitApplicationService = new SubmitApplicationService();
    VerifyEligibilityService verifyEligibilityService = new VerifyEligibilityService();
    HomePage homePage = new HomePage(driver.get());
    LoginPage loginPage = new LoginPage(driver.get());
    AgentPage agentPage = new AgentPage(driver.get());
    AuditorsManagementPage auditorsManagementPage = new AuditorsManagementPage(driver.get());
    ClaimantLogin claimantLogin = new ClaimantLogin(driver.get());
    private By finalButtonApprove = By.cssSelector("[value='الموافقة']"); //Only one action was needed
    private By filterApplicationBtn = By.xpath("//input[@id=\"DCDAgentPortalTheme_wt10_block_wtFilterContainer_wtbtn_Filter\"]");
    private By reassessmentBtn = By.linkText("إعادة التقييم");
    private By committeeSearchApplicationField = By.xpath("//input[@id=\"DCDAgentPortalTheme_wt10_block_wtFilterContainer_wttxt_Search\"]");
    private By applicationCheckBox = By.xpath("//input[contains(@id,'wtBenefitRequests')]");
    private By dropdownMenuSelectSupervisor = By.xpath("//select[@id=\"DCDAgentPortalTheme_wt10_block_wtMainContent_wtddl_useridIn\"]");
    private By submitApplication = By.xpath("//input[contains(@id,'wtCorrectData')]");
    private By dropdownMenuReason = By.xpath("//select[@id=\"DCDAgentPortalTheme_wt10_block_wtMainContent_wtddl_ReasonIn\"]");

    private By firstElementAfterSearch = By.cssSelector(".ThemeGrid_Width4:nth-child(1)"); //Contains app ref number and clickable


public void firstAssessment() throws JsonProcessingException, InterruptedException, AWTException {


    QuantaTestManager.getTest().log(Status.INFO, MarkupHelper.createCodeBlock(verifyEligibilityService.response.getBody()));

    if (verifyEligibilityService.getresponse(verifyEligibilityService).application.isEligible) {
        QuantaTestManager.getTest().assignCategory("1st Assessment");
        this.logManager.STEP("Submit Application from 12x12 API", "The System Submit Application by calling 12X12 API");
        this.logManager.INFO("Submit Application Service Call", false);
        submitApplicationService.requestService();
        QuantaTestManager.getTest().log(Status.INFO, MarkupHelper.createCodeBlock(submitApplicationService.response.getBody()));

        String refCode = submitApplicationService.getresponse(submitApplicationService).applicationSummary.referenceNumber;
        homePage.navigateToLogin();

        loginPage.loginWithUser(UserType.Superuser);
        this.logManager.STEP("VE from 12x12 API", "The System Verify the User Eligibility by calling 12X12 API");
        this.logManager.STEP(" Login by super user, and assign the application to specialist from ادارة المراجعين ", "");
        auditorsManagementPage.selectSpecialist(UserType.Specialist2.getUserName(), refCode);
        agentPage.logOut();

        this.logManager.STEP(("Step 4") , "Login By specialist");
        loginPage.loginWithUser(UserType.Specialist2);
        ActionsHelper.driverWait(5000);
        this.logManager.STEP(("Step 5") , "Look for SSP code under قائمة المهام");
        this.logManager.STEP(("Step 6") , "Click on application to view the details page");
        this.logManager.STEP(("Step 7") , "Go through steps and select ارسالة مرة اخرى then submit");
        this.logManager.STEP(("Step 8") , "Select the reason of RMIe.x: هوية غير صحية and select he EID, then select حفظ");
        agentPage.specialistSendAgain(refCode);
        this.logManager.STEP(("Step 9") ,"Logout");
        agentPage.logOut();


        this.logManager.STEP(("Step 10") ,"Login by EID to the claiment side");
        driver.get().navigate().to(urls.claimantLogin);
        claimantLogin.claimantLogin(TestData.EID);

        this.logManager.STEP(("Step 11") ,"The use should be redirected to the RMI page, click on submit");
        ActionsHelper.retryClick(submitApplication, 5);
        driver.get().switchTo().frame(0);
        List<WebElement> checkboxes = driver.get().findElements(By.xpath("//input[contains(@id,'Column1_wtchk')]"));
        checkboxes.stream().forEachOrdered(checkbox ->
                ActionsHelper.retryClick(checkbox, 10));
        ActionsHelper.retryClick(By.xpath("//input[contains(@id,'wtSubmit')]"),5);
        System.out.println("Save clicked");
        driver.get().switchTo().defaultContent();
        ActionsHelper.driverWait(5000);


        this.logManager.STEP(("Step 12") ,"Logout");
        ActionsHelper.retryClick(By.xpath("//input[contains(@id,'wtlogout')]"),5);

        this.logManager.STEP(("Step 13") ,"Login by specialist");
        driver.get().navigate().to(urls.agentLogin);

        this.logManager.STEP(("Step 13") ,"Look for this SSP and click on it");
        loginPage.loginWithUser(UserType.Specialist2);
        logManager.STEP("Search application", "Inputs the reference number in the search field");
        ActionsHelper.sendKeys(By.xpath("//span[contains(@id,'SearcFrom')]"), refCode + Keys.ENTER);
        ActionsHelper.waitForExpectedElement(firstElementAfterSearch);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("Click the application", firstElementAfterSearch);

    }

}


public void rmiReassessment() throws AWTException {

    logManager.STEP("2. Login as Committee", "");
    ActionsHelper.driverWait(5000);
    loginPage.loginWithUser(UserType.Committee1);

    logManager.STEP("3. Go to اعادة تقييم screen", "");
    ActionsHelper.driverWait(5000);
    driver.get().navigate().to(urls.reassessApplications);

    logManager.STEP("4. Insert the SSP in SSP code or EID field then click on filter", "");
    ActionsHelper.sendKeys(committeeSearchApplicationField, TestData.EID );
    ActionsHelper.driverWait(5000);
    ActionsHelper.clickAction(filterApplicationBtn);
    ActionsHelper.driverWait(5000);

    logManager.STEP("5. In the screen it will list the SSP with check box, select it", "");
    ActionsHelper.waitVisibility(ActionsHelper.element(applicationCheckBox), 7);
    System.out.println(ActionsHelper.isElementPresent(ActionsHelper.element(applicationCheckBox)));
    ActionsHelper.retryClick(applicationCheckBox, 7);

    logManager.STEP("6. Insert reason and select supervisor then launch the reassessment", "");
    ActionsHelper.driverWait(10000);
    ActionsHelper.waitForExpectedElement(dropdownMenuSelectSupervisor);
    ActionsHelper.selectOption(dropdownMenuSelectSupervisor, "12");
    ActionsHelper.selectByValue(ActionsHelper.element(dropdownMenuReason), "1");

    agentPage.logOut();
    ActionsHelper.driverWait(5000);
    logManager.STEP("7. Login by the supervisor", "");
    loginPage.loginWithUser(UserType.SeniorSpecialist2);

    logManager.STEP("8. Look for SSP and click on it to view tis details page", "");
    logManager.STEP("9. Click on application to view the details page", "");
    logManager.STEP("10. Go through steps and select ارسالة مرة اخرى then submit", "");
    logManager.STEP("11. Select the reason of RMIe.x:هوية غير صحية and select he EID, then select حفظ", "");
    agentPage.specialistSendAgain(TestData.EID);
    logManager.STEP("12. Logout", "");
    agentPage.logOut();

    logManager.STEP("13. Login by EID to the claiment side", "");
    claimantLogin.claimantLogin(TestData.EID);
    logManager.STEP("14. The user should be redirected to the RMI page, click on submit", "");
    this.logManager.STEP(("Step 11") ,"The use should be redirected to the RMI page, click on submit");
    ActionsHelper.retryClick(submitApplication, 5);
    driver.get().switchTo().frame(0);
    List<WebElement> checkboxes = driver.get().findElements(By.xpath("//input[contains(@id,'Column1_wtchk')]"));
    checkboxes.stream().forEachOrdered(checkbox ->
            ActionsHelper.retryClick(checkbox, 10));
    ActionsHelper.retryClick(By.xpath("//input[contains(@id,'wtSubmit')]"),5);
    System.out.println("Save clicked");
    driver.get().switchTo().defaultContent();
    ActionsHelper.driverWait(5000);
    logManager.STEP("15. Logout", "");
    agentPage.logOut();

    logManager.STEP("16 Login by supervisor", "");
    loginPage.loginWithUser(UserType.Specialist2);
    logManager.STEP("17. Look for this SSP and click on it", "");
    ActionsHelper.sendKeys(By.xpath("//span[contains(@id,'SearcFrom')]"), TestData.EID + Keys.ENTER);
    ActionsHelper.waitForExpectedElement(firstElementAfterSearch);
    ActionsHelper.driverWait(3000);
    ActionsHelper.actionClickStepClick("Click the application", firstElementAfterSearch);

}



}

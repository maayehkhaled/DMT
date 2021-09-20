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

    private By submitApplication = By.xpath("//input[contains(@id,'wtCorrectData')]");

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

        loginPage.loginWithUser(UserType.Specialist2);
        ActionsHelper.driverWait(5000);
        agentPage.specialistSendAgain(refCode);
        agentPage.logOut();
        driver.get().navigate().to(urls.claimantLogin);
        claimantLogin.claimantLogin(TestData.EID);
        ActionsHelper.retryClick(submitApplication, 5);
        driver.get().switchTo().frame(0);
        List<WebElement> checkboxes = driver.get().findElements(By.xpath("//input[contains(@id,'Column1_wtchk')]"));
        checkboxes.stream().forEachOrdered(checkbox ->
                ActionsHelper.retryClick(checkbox, 10));
        ActionsHelper.retryClick(By.xpath("//input[contains(@id,'wtSubmit')]"),5);
        System.out.println("Save clicked");
        driver.get().switchTo().defaultContent();
        ActionsHelper.driverWait(5000);
        ActionsHelper.retryClick(By.xpath("//input[contains(@id,'wtlogout')]"),5);
        driver.get().navigate().to(urls.agentLogin);
        loginPage.loginWithUser(UserType.Specialist2);
        logManager.STEP("Search application", "Inputs the reference number in the search field");
        ActionsHelper.sendKeys(By.xpath("//span[contains(@id,'SearcFrom')]"), refCode + Keys.ENTER);
        ActionsHelper.waitForExpectedElement(firstElementAfterSearch);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("Click the application", firstElementAfterSearch);

    }

}


public void rmiReassessment(){

    logManager.STEP("2. Login as Committee", "");
    ActionsHelper.driverWait(5000);
    loginPage.loginWithUser(UserType.Committee1);


    logManager.STEP("3. Go to اعادة تقييم screen", "");
    ActionsHelper.driverWait(5000);
    ActionsHelper.navigate("https://uat.ssa.gov.ae/DCDBusinessParametersN/ReassessApplications.aspx");
    logManager.STEP("4. Insert the SSP in SSP code or EID field then click on filter", "");
    ActionsHelper.isElementPresent(By.cssSelector("[placeholder='SSP code or Emirates ID']"));
    ActionsHelper.sendKeys(By.cssSelector("[placeholder='SSP code or Emirates ID']"),TestData.EID);
    ActionsHelper.retryClick(By.xpath("//input[@class='Button Is_Default']"),30);
    ActionsHelper.driverWait(5000);
    logManager.STEP("5. In the screen it will list the SSP with check box, select it", "");
    ActionsHelper.selectByValue(ActionsHelper.element(By.id("DCDAgentPortalTheme_wt10_block_wtMainContent_wtddl_useridIn")),UserType.SeniorSpecialist2.getUserName());
    ActionsHelper.driverWait(2000);
    ActionsHelper.selectByValue(ActionsHelper.element(By.id("DCDAgentPortalTheme_wt10_block_wtMainContent_wtddl_ReasonIn")),"Other");
    ActionsHelper.driverWait(2000);
    logManager.STEP("6. Insert reason and select supervisor then launch the reassessment", "");
    ActionsHelper.sendKeys(By.xpath("//div[@class='MainContent']//textarea[@class='ThemeGrid_Width7']"),"Reassignment text test");
    ActionsHelper.driverWait(3000);
    ActionsHelper.isElementPresent(By.xpath("//div[@class='MainContent']//input[@class='Button']"));
    ActionsHelper.retryClick(By.xpath("//div[@class='MainContent']//input[@class='Button']"),30);
    ActionsHelper.driverWait(2000);
    agentPage.logOut();
    ActionsHelper.driverWait(5000);
    logManager.STEP("7. Login by the supervisor", "");
    loginPage.loginWithUser(UserType.SeniorSpecialist2);

    logManager.STEP("8. Look for SSP and click on it to view tis details page", "");
    logManager.STEP("9. Click on application to view the details page", "");
    logManager.STEP("10. Go through steps and select ارسالة مرة اخرى then submit", "");
    logManager.STEP("11. Select the reason of RMIe.x:هوية غير صحية and select he EID, then select حفظ", "");
    logManager.STEP("12. Logout", "");
    agentPage.logOut();
    logManager.STEP("13. Login by EID to the claiment side", "");
    claimantLogin.claimantLogin(TestData.EID);
    logManager.STEP("14. The user should be redirected to the RMI page, click on submit", "");
    logManager.STEP("15. Logout", "");
    agentPage.logOut();
    logManager.STEP("16 Login by supervisor", "");
    loginPage.loginWithUser(UserType.Specialist2);
    logManager.STEP("17. Look for this SSP and click on it", "");

}



}

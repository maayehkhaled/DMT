package com.qpros.test;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.qpros.common.LogManager;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.pages.web.SSA.*;
import com.qpros.quanta.Status;
import com.qpros.quanta.markuputils.MarkupHelper;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.common.data.TestData;
import com.ssa.core.common.locators.urls;
import com.ssa.core.service.SubmitApplicationService;
import com.ssa.core.service.VerifyEligibilityService;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@Listeners(LogManager.class)
public class RMIFirstAssessment extends Base {
    @BeforeClass
    public void initiSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());

    }

    @BeforeMethod(enabled = true)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser();
    }

    SubmitApplicationService submitApplicationService = new SubmitApplicationService();
    VerifyEligibilityService verifyEligibilityService = new VerifyEligibilityService();
    HomePage homePage = new HomePage(driver.get());
    LoginPage loginPage = new LoginPage(driver.get());
    AgentPage agentPage = new AgentPage(driver.get());
    AuditorsManagementPage auditorsManagementPage = new AuditorsManagementPage(driver.get());
    ClaimantLogin claimantLogin = new ClaimantLogin(driver.get());

    private By submitApplication = By.xpath("//input[contains(@id,'wtCorrectData')]");

    private By firstElementAfterSearch = By.cssSelector(".ThemeGrid_Width4:nth-child(1)"); //Contains app ref number and clickable

    @Test(description = "RMI First Assessment", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void RMIFirstAssessment() throws JsonProcessingException, AWTException, InterruptedException {
        driver.get().navigate().to(urls.agentLogin);

        this.logManager.STEP("VE from 12x12 API", "The System Verify the User Eligibility by calling 12X12 API");
        this.logManager.INFO("Verify Eligibility Service Call", false);

        verifyEligibilityService.requestService();

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
}

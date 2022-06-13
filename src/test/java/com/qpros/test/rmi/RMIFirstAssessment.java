package com.qpros.test.rmi;


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
        this.setUpBrowser(true);
    }

    SubmitApplicationService submitApplicationService = new SubmitApplicationService();
    VerifyEligibilityService verifyEligibilityService = new VerifyEligibilityService();
    HomePage homePage = new HomePage(driver.get());
    LoginPage loginPage = new LoginPage(driver.get());
    AgentPage agentPage = new AgentPage(driver.get());
    AuditorsManagementPage auditorsManagementPage = new AuditorsManagementPage(driver.get());
    ClaimantLogin claimantLogin = new ClaimantLogin(driver.get());
    RMIPage rmiPage = new RMIPage(driver.get());

    private By submitApplication = By.xpath("//input[contains(@id,'wtCorrectData')]");

    private By firstElementAfterSearch = By.cssSelector(".ThemeGrid_Width4:nth-child(1)"); //Contains app ref number and clickable

    @Test(description = "RMI First Assessment", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void RMIFirstAssessment() throws JsonProcessingException, AWTException, InterruptedException {
        driver.get().navigate().to(urls.agentLogin);

        this.logManager.STEP("VE from 12x12 API", "The System Verify the User Eligibility by calling 12X12 API");
        this.logManager.INFO("Verify Eligibility Service Call", false);

        verifyEligibilityService.requestService();
        rmiPage.firstAssessment();

    }
}

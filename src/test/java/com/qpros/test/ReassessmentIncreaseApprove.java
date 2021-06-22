package com.qpros.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qpros.common.LogManager;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.pages.web.SSA.*;
import com.qpros.pages.web.SSA.modules.ApproveApplicationModule;
import com.qpros.quanta.Status;
import com.qpros.quanta.markuputils.MarkupHelper;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.service.SubmitApplicationService;
import com.ssa.core.service.VerifyEligibilityService;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

@Listeners(LogManager.class)
public class ReassessmentIncreaseApprove extends Base {

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
    BusinessParametersPage businessParametersPage = new BusinessParametersPage(driver.get());
    PaymentSpecialistPage paymentSpecialistPage = new PaymentSpecialistPage(driver.get());
    ApproveApplicationModule approveApplication = new ApproveApplicationModule(driver.get());
    private By reassessmentBtn = By.linkText("إعادة التقييم");
    private By committeeSearchApplicationField = By.cssSelector("[placeholder='SSP code or Emirates ID']");
    private By applicationCheckBox = By.xpath("//input[contains(@id,'wtBenefitRequests')]");
    private By dropdownMenuSelect = By.id("DCDAgentPortalTheme_wt10_block_wtMainContent_wtuseridIn");
    private By dropdownMenuReason = By.id("DCDAgentPortalTheme_wt10_block_wtMainContent_wtReasonIn");
    private By commentFieldTextBox = By.id("DCDAgentPortalTheme_wt10_block_wtMainContent_wtCommentIn");
    private By launchBtn = By.id("DCDAgentPortalTheme_wt10_block_wtMainContent_wt97");
    private By searchForApplication = By.xpath("//input[contains(@id,'SearcFrom')]");
    private By firstElementAfterSearch = By.cssSelector(".ThemeGrid_Width4:nth-child(1)"); //Contains app ref number and clickable
    private By updateAmountBtn = By.xpath("//div[contains(@id,'UpdateAmount2')]"); //Contains app ref number and clickable
    private By amountField = By.xpath("//input[contains(@id,'amount_mask')]"); //Contains app ref number and clickable
    private By approveUpdate = By.xpath("//input[contains(@id,'Submit')]"); //Contains app ref number and clickable
    private By amountText = By.xpath("//span[contains(@id,'wtColumn2_wtBenefitAmount')]"); //Contains app ref number and clickable
    private By acceptedOrRejectedBtn = By.xpath("//input[contains(@id,'AcceptedOrRejected')]"); //Contains app ref number and clickable
    private By superuserSearchApp = By.xpath("//input[contains(@id,'SearchFrom')]");
    private By applicationLink = By.xpath("//a[contains(@id,'wtlnk_ApplicationReview')]");
    private By backBtn = By.xpath("//div[contains(@id,'wtPrev2')]"); //Contains app ref number and clickable
    private By benefitAmount = By.xpath("//span[contains(@id,'wtContent5_wtBenefitAmount')]"); //Contains app ref number and clickable

    @Test(description = "Approve an application", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void approveApplication() throws JsonProcessingException, AWTException {
        approveApplication.approveApplication(true);
        System.out.println(approveApplication.committeeName);
        System.out.println(approveApplication.refCode);
        homePage.navigateToLogin();
        String committeeName =approveApplication.committeeName.replace("\n", "");
//        String committeeName = "Committee100Committee".replace("\n", "");//delete
        homePage.navigateToLogin();
        if (committeeName.contains(UserType.Committee100.getUserName())) {
            loginPage.loginWithUser(UserType.Committee100);
        } else {
            loginPage.loginWithUser(UserType.Committee1);
        }
        ActionsHelper.retryClick(reassessmentBtn,4);
        ActionsHelper.sendKeys(committeeSearchApplicationField, approveApplication.refCode + Keys.ENTER);
//        ActionsHelper.sendKeys(committeeSearchApplicationField, "SSP-11338" + Keys.ENTER);//delete

        ActionsHelper.waitVisibility(ActionsHelper.element(applicationCheckBox),7);
        System.out.println(ActionsHelper.isElementPresent(ActionsHelper.element(applicationCheckBox)));
        ActionsHelper.retryClick(applicationCheckBox, 7);
        ActionsHelper.driverWait(10000);
        ActionsHelper.waitForExpectedElement(dropdownMenuSelect);
        ActionsHelper.selectOption(dropdownMenuSelect, "12");
        ActionsHelper.selectByValue(ActionsHelper.element(dropdownMenuReason), "1");

        ActionsHelper.sendKeys(commentFieldTextBox, "Just a test reason");
        ActionsHelper.retryClick(launchBtn, 4);
        agentPage.logOut();


        homePage.navigateToLogin();
        loginPage.loginWithUser(UserType.SeniorSpecialist1);
        committeeName = agentPage.seniorSpecialistApproval(approveApplication.refCode);
//        committeeName = agentPage.seniorSpecialistApprovalIncDec("SSP-11338");

        System.out.println("Committee: " + committeeName);
        driver.get().navigate().to("https://10.231.1.100/DCDAgentFrontEnd/TasksList.aspx");
        agentPage.logOut();
        committeeName = committeeName.replace("\n", "");
        if (committeeName.contains(UserType.Committee100.getUserName())) {
            loginPage.loginWithUser(UserType.Committee100);
        } else {
            loginPage.loginWithUser(UserType.Committee1);
        }
//        ActionsHelper.sendKeys(searchForApplication, approveApplication.refCode + Keys.ENTER);
        ActionsHelper.sendKeys(searchForApplication, "SSP-11338" + Keys.ENTER);
        ActionsHelper.actionClickScrollStepClick("Click the application", firstElementAfterSearch);
        ActionsHelper.actionClickScrollStepClick("Click on update Amount", updateAmountBtn);
        ActionsHelper.driverWait(3000);
        driver.get().switchTo().frame(0);
        String[] arrOfStr = ActionsHelper.element(amountField).getAttribute("value").split(",");
        double amount = Double.parseDouble(String.join("", arrOfStr));
        double newAmount = amount + 2000;
        ActionsHelper.sendKeysWithClear(amountField, String.format("%.2f", newAmount));
        ActionsHelper.driverWait(1000);
        ActionsHelper.retryClick(approveUpdate, 10);
        ActionsHelper.driverWait(1000);
        System.out.println("Save clicked");
        driver.get().switchTo().defaultContent();
        driver.get().navigate().refresh();
        String amountTxt = ActionsHelper.element(amountText).getText();
        System.out.println("amountTxt:" + amountTxt + "   amount:" + amount + "    newAmount:" + newAmount);
        ActionsHelper.actionClickStepClick("Approve Button", acceptedOrRejectedBtn);
        agentPage.logOut();
        homePage.navigateToLogin();
        loginPage.loginWithUser(UserType.Superuser);
        driver.get().navigate().to("https://10.231.1.100/DCDBusinessParameters/BusinessParameters.aspx");
        businessParametersPage.releaseAppliaction(approveApplication.refCode);
//        businessParametersPage.releaseAppliaction("SSP-11338");

        driver.get().navigate().to("https://10.231.1.100/DCDAgentFrontEnd/AllApplications.aspx");
//        ActionsHelper.sendKeys(superuserSearchApp, "SSP-11338" + Keys.ENTER);
        ActionsHelper.sendKeys(superuserSearchApp, approveApplication.refCode + Keys.ENTER);
        ActionsHelper.retryClick(applicationLink, 5);
        ActionsHelper.waitForExpectedElement(acceptedOrRejectedBtn, 7);
        ActionsHelper.actionClickStepClick("Approve Button", acceptedOrRejectedBtn);
        ActionsHelper.actionClickStepClick("accepted Button", acceptedOrRejectedBtn);
        ActionsHelper.actionClickStepClick("back Button", backBtn);
        String benefits = ActionsHelper.element(benefitAmount).getAttribute("innerText");
        benefits = benefits.replace(" درهم إماراتي", "").replace("،", "");
        Assert.assertEquals(benefits, String.format("%.2f",newAmount));
    }
}
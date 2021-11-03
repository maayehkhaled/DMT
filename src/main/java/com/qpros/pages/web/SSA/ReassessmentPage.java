package com.qpros.pages.web.SSA;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.pages.web.SSA.modules.ApproveApplicationModule;
import com.ssa.core.common.locators.urls;
import com.ssa.core.service.SubmitApplicationService;
import com.ssa.core.service.VerifyEligibilityService;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.awt.*;

public class ReassessmentPage extends Base {
        public ReassessmentPage(WebDriver driver) {
            PageFactory.initElements(Base.driver.get(), this);
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

    public double amount;
    public double newAmount;


    private By reassessmentBtn = By.linkText("إعادة التقييم");
    private By committeeSearchApplicationField = By.xpath("//input[@id=\"DCDAgentPortalTheme_wt10_block_wtFilterContainer_wttxt_Search\"]");
    private By applicationCheckBox = By.xpath("//input[contains(@id,'wtBenefitRequests')]");
    private By dropdownMenuSelectSupervisor = By.xpath("//select[@id=\"DCDAgentPortalTheme_wt10_block_wtMainContent_wtddl_useridIn\"]");
    private By dropdownMenuReason = By.xpath("//select[@id=\"DCDAgentPortalTheme_wt10_block_wtMainContent_wtddl_ReasonIn\"]");
    private By commentFieldTextBox = By.xpath("//textarea[@id=\"DCDAgentPortalTheme_wt10_block_wtMainContent_wttxt_CommentIn\"]");
    //By.xpath("//input[contains(@id,'btn_LaunchReassess')]");
    private By launchBtn = By.xpath("//input[@id=\"DCDAgentPortalTheme_wt10_block_wtMainContent_wtbtn_LaunchReassess\"]");
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
    private By finalButtonApprove = By.cssSelector("[value='الموافقة']"); //Only one action was needed
    private By filterApplicationBtn = By.xpath("//input[@id=\"DCDAgentPortalTheme_wt10_block_wtFilterContainer_wtbtn_Filter\"]");
    String refCode = "SSP-13405";

    public void reassessmentApprove(){
        String committeeName = "test";

        homePage.navigateToLogin();
        loginPage.loginWithUser(UserType.Committee1);
        /*
        if (committeeName.contains(UserType.Committee100.getUserName())) {
            loginPage.loginWithUser(UserType.Committee100);
        } else {
            loginPage.loginWithUser(UserType.Committee1);
        }
         */
        ActionsHelper.driverWait(5000);
        driver.get().navigate().to(urls.reassessApplications);
        //ActionsHelper.retryClick(reassessmentBtn, 4);
        ActionsHelper.sendKeys(committeeSearchApplicationField, refCode );
        ActionsHelper.driverWait(5000);
        ActionsHelper.clickAction(filterApplicationBtn);
        ActionsHelper.waitVisibility(ActionsHelper.element(applicationCheckBox), 7);
        System.out.println(ActionsHelper.isElementPresent(ActionsHelper.element(applicationCheckBox)));
        ActionsHelper.retryClick(applicationCheckBox, 7);
        ActionsHelper.driverWait(10000);
        ActionsHelper.waitForExpectedElement(dropdownMenuSelectSupervisor);
        ActionsHelper.selectOption(dropdownMenuSelectSupervisor, "12");
        ActionsHelper.selectByValue(ActionsHelper.element(dropdownMenuReason), "1");

        ActionsHelper.sendKeys(commentFieldTextBox, "Just a test reason");
        ActionsHelper.retryClick(launchBtn, 4);
        agentPage.logOut();


        homePage.navigateToLogin();
        loginPage.loginWithUser(UserType.SeniorSpecialist1);
        committeeName = agentPage.seniorSpecialistApproval(approveApplication.refCode);

        System.out.println("Committee: " + committeeName);
        driver.get().navigate().to(urls.tasksList);
        agentPage.logOut();
        committeeName = committeeName.replace("\n", "");
        if (committeeName.contains(UserType.Committee100.getUserName())) {
            loginPage.loginWithUser(UserType.Committee100);
        } else {
            loginPage.loginWithUser(UserType.Committee1);
        }
        ActionsHelper.sendKeys(searchForApplication, approveApplication.refCode + Keys.ENTER);
        ActionsHelper.actionClickScrollStepClick("Click the application", firstElementAfterSearch);
        ActionsHelper.actionClickScrollStepClick("Click on update Amount", updateAmountBtn);
        ActionsHelper.driverWait(3000);
        driver.get().switchTo().frame(0);

    }



    public void reassessToAmount() throws JsonProcessingException, AWTException {
        //System.out.println(approveApplication.committeeName);
        //System.out.println(approveApplication.refCode);
        //String committeeName =approveApplication.committeeName.replace("\n", "");
        String committeeName = "test";
        homePage.navigateToLogin();
        if (committeeName.contains(UserType.Committee100.getUserName())) {
            loginPage.loginWithUser(UserType.Committee100);
        } else {
            loginPage.loginWithUser(UserType.Committee1);
        }
        ActionsHelper.driverWait(5000);
        driver.get().navigate().to(urls.reassessApplications);
        ActionsHelper.retryClick(reassessmentBtn, 4);
        ActionsHelper.sendKeys(committeeSearchApplicationField, approveApplication.refCode + Keys.ENTER);
        ActionsHelper.driverWait(5000);

        ActionsHelper.waitVisibility(ActionsHelper.element(applicationCheckBox), 7);
        System.out.println(ActionsHelper.isElementPresent(ActionsHelper.element(applicationCheckBox)));
        ActionsHelper.retryClick(applicationCheckBox, 7);
        ActionsHelper.driverWait(10000);
        ActionsHelper.waitForExpectedElement(dropdownMenuSelectSupervisor);
        ActionsHelper.selectOption(dropdownMenuSelectSupervisor, "12");
        ActionsHelper.selectByValue(ActionsHelper.element(dropdownMenuReason), "1");

        ActionsHelper.sendKeys(commentFieldTextBox, "Just a test reason");
        ActionsHelper.retryClick(launchBtn, 4);
        agentPage.logOut();


        homePage.navigateToLogin();
        loginPage.loginWithUser(UserType.SeniorSpecialist1);
        committeeName = agentPage.seniorSpecialistApproval(approveApplication.refCode);

        System.out.println("Committee: " + committeeName);
        driver.get().navigate().to(urls.tasksList);
        agentPage.logOut();
        committeeName = committeeName.replace("\n", "");
        if (committeeName.contains(UserType.Committee100.getUserName())) {
            loginPage.loginWithUser(UserType.Committee100);
        } else {
            loginPage.loginWithUser(UserType.Committee1);
        }
        ActionsHelper.sendKeys(searchForApplication, approveApplication.refCode + Keys.ENTER);
        ActionsHelper.actionClickScrollStepClick("Click the application", firstElementAfterSearch);
        ActionsHelper.actionClickScrollStepClick("Click on update Amount", updateAmountBtn);
        ActionsHelper.driverWait(3000);
        driver.get().switchTo().frame(0);


    }

    public void decreaseAmount(){
        String[] arrOfStr = ActionsHelper.element(amountField).getAttribute("value").split(",");
        amount = Double.parseDouble(String.join("", arrOfStr));
        newAmount = amount - 10;
        ActionsHelper.sendKeysWithClear(amountField, String.format("%.2f", newAmount));
    }

    public void increaseAmount(){
        String[] arrOfStr = ActionsHelper.element(amountField).getAttribute("value").split(",");
        double amount = Double.parseDouble(String.join("", arrOfStr));
        double newAmount = amount + 2000;
        ActionsHelper.sendKeysWithClear(amountField, String.format("%.2f", newAmount));
    }

    public void reassessAfterAmount(){

        ActionsHelper.driverWait(1000);
        ActionsHelper.retryClick(approveUpdate, 10);
        ActionsHelper.driverWait(1000);
        System.out.println("Save clicked");
        driver.get().switchTo().defaultContent();
        driver.get().navigate().refresh();
        ActionsHelper.actionClickStepClick("Approve Button", finalButtonApprove);
        String amountTxt = ActionsHelper.element(amountText).getText();
        System.out.println("amountTxt:" + amountTxt + "   amount:" + amount + "    newAmount:" + newAmount);
        agentPage.logOut();

        homePage.navigateToLogin();
        loginPage.loginWithUser(UserType.Superuser);
        driver.get().navigate().to(urls.businessParameters);
        businessParametersPage.releaseAppliaction(approveApplication.refCode);

        driver.get().navigate().to(urls.allApplications);
        ActionsHelper.sendKeys(superuserSearchApp, approveApplication.refCode + Keys.ENTER);
        ActionsHelper.retryClick(applicationLink, 5);
        ActionsHelper.waitForExpectedElement(acceptedOrRejectedBtn, 7);
        ActionsHelper.actionClickStepClick("Approve Button", acceptedOrRejectedBtn);
        ActionsHelper.actionClickStepClick("accepted Button", acceptedOrRejectedBtn);
        ActionsHelper.actionClickStepClick("back Button", backBtn);
        String benefits = ActionsHelper.element(benefitAmount).getAttribute("innerText");
        benefits = benefits.replace(" درهم إماراتي", "").replace("،", "");
        System.out.println(benefits + "     " + String.format("%.2f", newAmount));
        Assert.assertEquals(benefits, String.format("%.2f", newAmount));
    }
}

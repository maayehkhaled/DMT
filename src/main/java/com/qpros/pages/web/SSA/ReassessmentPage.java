package com.qpros.pages.web.SSA;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.helpers.FileUtils;
import com.qpros.pages.web.SSA.modules.ApproveApplicationModule;
import com.qpros.quanta.Status;
import com.qpros.quanta.markuputils.MarkupHelper;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.common.locators.urls;
import com.ssa.core.service.DeleteEmirateId;
import com.ssa.core.service.SubmitApplicationService;
import com.ssa.core.service.VerifyEligibilityService;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    ApproveApplicationModule approveApplicationModule =new ApproveApplicationModule(driver.get());

    DeleteEmirateId deleteEmirateId=new DeleteEmirateId();
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
    String refCode = "SSP-20375";
    private static final Pattern p = Pattern.compile("(^[^\\s]+)");
    Matcher matcher;
    public void reassessmentApprove() throws InterruptedException, AWTException, JsonProcessingException {
        deleteEmirateId.requestService();
        driver.get().navigate().to(urls.agentLogin);
        logManager.STEP("VE from 12x12 API", "The System Verify the User Eligibility by calling 12X12 API");
        logManager.INFO("Verify Eligibility Service Call", false);
        verifyEligibilityService.requestService();

        //if the emirates id is eligable for a service then request the service else finish scenario
        QuantaTestManager.getTest().log(Status.INFO, MarkupHelper.createCodeBlock(verifyEligibilityService.response.getBody()));
        if (verifyEligibilityService.getresponse(verifyEligibilityService).application.isEligible) {
            QuantaTestManager.getTest().assignCategory("1st Assessment");
            this.logManager.STEP("Submit Application from 12x12 API", "The System Submit Application by calling 12X12 API");
            this.logManager.INFO("Submit Application Service Call", false);
            submitApplicationService.requestService();
            QuantaTestManager.getTest().log(Status.INFO, MarkupHelper.createCodeBlock(submitApplicationService.response.getBody()));

            //read the referance code application is
            refCode = submitApplicationService.getresponse(submitApplicationService).applicationSummary.referenceNumber;
            refCode.replace("\uE007", "");
            FileUtils.createFile("refCodeFile.txt", refCode);

            homePage.navigateToLogin();

            //login with super user
            loginPage.loginWithUser(UserType.Superuser);
            this.logManager.STEP(" Login by super user, and assign the application to specialist from ادارة المراجعين ", "");

            //start assign process by selecting specialist 2 with appication id
            auditorsManagementPage.selectSpecialist(UserType.Specialist2.getUserName(), refCode);

            agentPage.logOut();
            //login with specialist 100
            loginPage.loginWithUser(UserType.Specialist2);
            ActionsHelper.driverWait(8000);
            String seniorSpecialist = agentPage.specialistRejectApplication(refCode);
            ActionsHelper.driverWait(8000);
            System.out.println("Senior Specialist : " + seniorSpecialist);
            agentPage.logOut();
            loginPage.loginWithUser(UserType.valueOf(seniorSpecialist));
            ActionsHelper.driverWait(8000);
             agentPage.seniorSpecialistRejectApplication(refCode);
            ActionsHelper.driverWait(8000);
            System.out.println("Senior Specialist : " + seniorSpecialist);
            agentPage.logOut();
            loginPage.loginWithUser(UserType.Superuser);
            driver.get().navigate().to(urls.businessParameters);
            businessParametersPage.releaseAppliaction(refCode);
            agentPage.logOut();
            approveApplicationModule.approveApplication(false);
        }
    }
public void test()
{
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

    refCode=FileUtils.readFile("refCodeFile.txt").get(0);
    ActionsHelper.driverWait(5000);
    driver.get().navigate().to(urls.reassessApplications);
    //ActionsHelper.retryClick(reassessmentBtn, 4);
    ActionsHelper.sendKeys(committeeSearchApplicationField, refCode );
    ActionsHelper.driverWait(5000);
    ActionsHelper.clickAction(filterApplicationBtn);
    ActionsHelper.driverWait(4000);
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
    committeeName = agentPage.seniorSpecialistApproval(approveApplicationModule.refCode);

    System.out.println("Committee: " + committeeName);
    driver.get().navigate().to(urls.tasksList);
    agentPage.logOut();
    committeeName = committeeName.replace("\n", "");
    if (committeeName.contains(UserType.Committee100.getUserName())) {
        loginPage.loginWithUser(UserType.Committee100);
    } else {
        loginPage.loginWithUser(UserType.Committee1);
    }
    ActionsHelper.sendKeys(searchForApplication, approveApplicationModule.refCode + Keys.ENTER);
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
        ActionsHelper.sendKeys(committeeSearchApplicationField, approveApplicationModule.refCode + Keys.ENTER);
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
        committeeName = agentPage.seniorSpecialistApproval(approveApplicationModule.refCode);

        System.out.println("Committee: " + committeeName);
        driver.get().navigate().to(urls.tasksList);
        agentPage.logOut();
        committeeName = committeeName.replace("\n", "");
        if (committeeName.contains(UserType.Committee100.getUserName())) {
            loginPage.loginWithUser(UserType.Committee100);
        } else {
            loginPage.loginWithUser(UserType.Committee1);
        }
        ActionsHelper.sendKeys(searchForApplication, approveApplicationModule.refCode + Keys.ENTER);
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
        businessParametersPage.releaseAppliaction(approveApplicationModule.refCode);

        driver.get().navigate().to(urls.allApplications);
        ActionsHelper.sendKeys(superuserSearchApp, approveApplicationModule.refCode + Keys.ENTER);
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

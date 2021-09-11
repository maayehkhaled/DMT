package com.qpros.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.pages.web.SSA.*;
import com.qpros.pages.web.SSA.modules.ApproveApplicationModule;
import com.qpros.pages.web.SSA.modules.RejectApplicationModule;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.common.data.TestData;
import com.ssa.core.common.locators.urls;
import com.ssa.core.service.SubmitApplicationService;
import com.ssa.core.service.VerifyEligibilityService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@Listeners(com.qpros.common.LogManager.class)
public class AppealApprove extends Base {

    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());

    }

    @BeforeMethod(enabled = true)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser();
    }

    ApproveApplicationModule approveApplicationModule = new ApproveApplicationModule(driver.get());
    ClaimantApplicationPage claimantApplicationPage = new ClaimantApplicationPage(driver.get());
    RejectApplicationModule rejectApplicationModule = new RejectApplicationModule(driver.get());
    ClaimantLogin claimantLogin = new ClaimantLogin(driver.get());
    COCPage cocPage = new COCPage(driver.get());
    SubmitApplicationService submitApplicationService = new SubmitApplicationService();
    VerifyEligibilityService verifyEligibilityService = new VerifyEligibilityService();
    HomePage homePage = new HomePage(driver.get());
    LoginPage loginPage = new LoginPage(driver.get());
    AgentPage agentPage = new AgentPage(driver.get());
    AuditorsManagementPage auditorsManagementPage = new AuditorsManagementPage(driver.get());
    BusinessParametersPage businessParametersPage = new BusinessParametersPage(driver.get());
    PaymentSpecialistPage paymentSpecialistPage = new PaymentSpecialistPage(driver.get());

    @Test(description = "AppealApprove")
    public void AppealApprove() throws JsonProcessingException, InterruptedException, AWTException {
        //1st assessment - Approve
        approveApplicationModule.approveApplication(false);
        // approveApplicationModule.approveExistingApplication(ApproveApplicationModule.refCode);
        logManager.STEP("2. Login to beneficiary side with the EID", "the Beneficiary User conduct login using EID" + TestData.EID);
        ActionsHelper.navigate(urls.claimantLogin);
        claimantLogin.claimantLogin(TestData.EID);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("مراجعة شروط و معايير البرنامج و المدفوعات", By.xpath("//div[@class='HomePageRow']//div[@class='text']/div[.='مراجعة شروط ومعايير البرنامج والمدفوعات']"));
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickScrollStepClick("تقديم طلب تظلم", By.xpath("//input[@value='تقديم طلب تظلم']"));
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickScrollStepClick(" طلب تظلم", By.id("DCDWebPortalTheme_wtLayout_block_wtMainContent_wtbtn_Appeal"));
        ActionsHelper.driverWait(2000);
        ActionsHelper.scrollTo(By.xpath("//textarea[@class='OSFillParent']"));
        ActionsHelper.sendKeys(By.xpath("//textarea[@class='OSFillParent']"), "test appeal text");
        java.util.List<WebElement> salaryElementList = driver.get().findElements(By.xpath("//input[contains(@id,fileinputPopup_Upload)]"));
        salaryElementList.stream().forEachOrdered(salElemnt ->
        {
            ActionsHelper.sendKeys(salElemnt, "C:\\Users\\KhaledMa'ayeh\\Downloads\\pdf-test.pdf");

        });
        java.util.List<WebElement> fillcommentTestBoxs = driver.get().findElements(By.xpath("//textarea[contains(@id,wttxt_Description)]"));
        fillcommentTestBoxs.stream().forEachOrdered(commnt ->
        {
            ActionsHelper.sendKeys(commnt, "test appeal text");

        });

        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("apply Appeal Request", By.xpath("//input[@class='Button ForwardButton Is_Default']"));

        ActionsHelper.driverWait(3000);
        driver.get().switchTo().frame(0);
        List<WebElement> listOfAgree = driver.get().findElements(By.xpath("//input[contains(@id,wtchk_canProceed)]"));
        listOfAgree.stream().forEachOrdered(agreeitem -> {
            if (!agreeitem.isSelected()) {
                agreeitem.click();
            }
        });
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("click approve on appeal", By.xpath("//div[@class='PopupActionsContainer']"));
        ActionsHelper.driverWait(10000);
        ActionsHelper.actionClickStepClick("click on profile page ", By.xpath("//input[@class='Button MenuButton UserProfileButton OSFillParent']"));
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("click on logout", By.cssSelector("[tabindex='4'] > .OvalIcon"));
        ActionsHelper.driverWait(5000);
        homePage.navigateToLogin();
        loginPage.loginWithUser(UserType.Superuser);

    }
}

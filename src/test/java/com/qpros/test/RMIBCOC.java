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
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@Listeners(com.qpros.common.LogManager.class)
public class RMIBCOC extends Base {

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
    ClaimantApplicationPage claimantPage = new ClaimantApplicationPage(driver.get());

    @Test(description = "RMI - BCOC", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void rmiBCOCStep() throws JsonProcessingException, AWTException, InterruptedException {

        //1st assessment - Approve
        //approveApplicationModule.approveApplication(false);
        // approveApplicationModule.approveExistingApplication(ApproveApplicationModule.refCode);
        logManager.STEP("2. Login to beneficiary side with the EID", "the Beneficiary User conduct login using EID" + TestData.EID);
        ActionsHelper.navigate(urls.claimantLogin);
        claimantLogin.claimantLogin(TestData.EID);
        logManager.STEP("3. Click on التغير في الظروف المعيشية box", "the Beneficiary User Click on التغير في الظروف المعيشية box" + TestData.EID);
        ActionsHelper.driverWait(3000);
        /*
d        ActionsHelper.actionClickStepClick("Click on update family Data", By.xpath("//div[@class='HomePageRow']/div[1]//div[@class='text']/div[1]"));
        ActionsHelper.driverWait(3000);
        ActionsHelper.driver.get().switchTo().frame(0);
        ActionsHelper.selectOption(By.id("CloneOfWebPatterns_wt20_block_wtMainContent_wtddl_WebPortalLocation2"),"2");
        ActionsHelper.retryClick(By.xpath("//input[@class='Button Is_Default']"),30);
        driver.get().switchTo().defaultContent();
        ActionsHelper.driverWait(40000);

         */
        ActionsHelper.driverWait(5000);
        claimantPage.personalInformation();
        claimantPage.familyInformation();
        claimantPage.addressAndContactInformation();
        claimantPage.incomeAndPensionData();
        claimantPage.support();
        claimantPage.businessRecord();

        ActionsHelper.retryClick(By.xpath("//input[@class='Button MenuButton LogoutButton']"), 30);
        logManager.STEP("14. Login by super user, and assign the application to specialist from ادارة المراجعين", "Login by super user, and assign the application to specialist from ادارة المراجعين ");

        homePage.navigateToLogin();
        loginPage.loginWithUser(UserType.Superuser);
        auditorsManagementPage.selectSpecialist(UserType.Specialist2.getUserName(), "SSP-13345");
        agentPage.logOut();
        logManager.STEP("15. Login by the specialist", "Login by the specialist");
        homePage.navigateToLogin();
        loginPage.loginWithUser(UserType.Specialist2);

        logManager.STEP("16. Look for SSP code under قائمة المهام", "Look for SSP code under قائمة المهام ");
        ActionsHelper.driverWait(2000);
        //Change the specialist approval function to match the send again step IN RMI COC
        String seniorSpecialist = agentPage.specialistApproval(ApproveApplicationModule.refCode,false);

        ActionsHelper.driverWait(2000);
        System.out.println(seniorSpecialist);
        agentPage.logOut();
        logManager.STEP("17. Click on application to view the details page", "Click on application to view the details page");
        agentPage.specialistSendAgain("SSP-13345");
        logManager.STEP("18. Go through steps and select ارسالة مرة اخرى then submit", "Go through steps and select ارسالة مرة اخرى then submit ");

        logManager.STEP("19. Select the reason of RMIe.x: هوية غير صحية and select he EID, then select حفظ", " Select the reason of RMIe.x: هوية غير صحية and select he EID, then select حفظ");
        logManager.STEP("20. Logout", "Logout");
        logManager.STEP("21. Login by EID to the claiment side", "Login by EID to the claiment side ");
        logManager.STEP("23. Click on التغير في الظروف المعيشية box", "Click on التغير في الظروف المعيشية box");
        logManager.STEP("24. The use should be redirected to the RMI page, click on submit", "The use should be redirected to the RMI page, click on submit ");
        logManager.STEP("25. Logout", " Logout");
        agentPage.logOut();
        logManager.STEP("26. Login by specialist", " Login by specialist");
        loginPage.loginWithUser(UserType.Specialist2);
        logManager.STEP("27. Look for this SSP and click on it", " Look for this SSP and click on it");

    }


}


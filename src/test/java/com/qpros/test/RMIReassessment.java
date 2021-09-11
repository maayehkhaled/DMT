package com.qpros.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.pages.web.SSA.*;
import com.qpros.pages.web.SSA.modules.ApproveApplicationModule;
import com.qpros.pages.web.SSA.modules.RejectApplicationModule;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.common.data.TestData;
import com.ssa.core.service.SubmitApplicationService;
import com.ssa.core.service.VerifyEligibilityService;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

@Listeners(com.qpros.common.LogManager.class)
public class RMIReassessment extends Base {

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

    @Test(description = "RMI- Reassesmsnet ")
    public void RMIReassesmsnet() throws JsonProcessingException, InterruptedException, AWTException {
        //1st assessment - Approve
        approveApplicationModule.approveApplication(false);
        // approveApplicationModule.approveExistingApplication(ApproveApplicationModule.refCode);

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

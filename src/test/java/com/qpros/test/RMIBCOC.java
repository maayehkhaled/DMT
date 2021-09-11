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
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
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

    @Test(description = "RMI - BCOC", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void rmiBCOCStep() throws JsonProcessingException, AWTException, InterruptedException {

        //1st assessment - Approve
        approveApplicationModule.approveApplication(false);
        // approveApplicationModule.approveExistingApplication(ApproveApplicationModule.refCode);
        logManager.STEP("2. Login to beneficiary side with the EID", "the Beneficiary User conduct login using EID" + TestData.EID);
        ActionsHelper.navigate(urls.claimantLogin);
        claimantLogin.claimantLogin(TestData.EID);
//        logManager.STEP("3. Click on التغير في الظروف المعيشية box", "the Beneficiary User Click on التغير في الظروف المعيشية box" + TestData.EID);
//        ActionsHelper.driverWait(3000);
//        ActionsHelper.actionClickStepClick("Click on update family Data", By.xpath("//div[@class='HomePageRow']/div[1]//div[@class='text']/div[1]"));
//        ActionsHelper.driverWait(3000);
//
//        ActionsHelper.driver.get().switchTo().frame(0);
//        ActionsHelper.selectOption(By.id("CloneOfWebPatterns_wt20_block_wtMainContent_wtddl_WebPortalLocation2"),"2");
//        ActionsHelper.retryClick(By.xpath("//input[@class='Button Is_Default']"),30);
//        driver.get().switchTo().defaultContent();
//        ActionsHelper.driverWait(40000);
        ActionsHelper.driverWait(5000);
        logManager.STEP("4. Click on التالي in البيانات الشخصيه tab", "Click on التالي in البيانات الشخصيه tab");
        ActionsHelper.waitForExpectedElement(By.xpath("//div[@class='PH Tabs__content active']//div[@class='card']"));
        ActionsHelper.actionClickScrollStepClick("Click on التالي in البيانات الشخصيه tab", By.xpath("//div[@class='PH Tabs__content active']//div[@class='card']"));
        logManager.STEP("5. Click on التالي in بيانات العائلة tab", "Click on التالي in بيانات العائلة tab");
        ActionsHelper.driverWait(3000);
        ActionsHelper.waitForExpectedElement(By.xpath("//div[@class='PH Tabs__content active']//div[@class='card']"));
        ActionsHelper.actionClickScrollStepClick("Click on التالي in بيانات العائلة tab", By.xpath("//div[@class='PH Tabs__content active']//div[@class='card']"));
        logManager.STEP("6. Fill address information and contact information in العنوان tab (make sure that the tab marked as completed)", "Fill address information and contact information in العنوان tab (make sure that the tab marked as completed) ");
        ActionsHelper.driverWait(3000);
        ActionsHelper.scrollTo(By.xpath("//span[.='تفاصيل مكان السكن']"));
        ActionsHelper.isElementPresent(By.xpath("//span[.='تفاصيل مكان السكن']"));
        List<WebElement> residentList = driver.get().findElements(By.xpath("//select"));
        residentList.stream().forEachOrdered(selectElement -> {
            ActionsHelper.driverWait(2000);
            Select select = new Select(selectElement);
            select.selectByIndex(select.getOptions().size() - 1);
        });

        logManager.STEP("7. click on التالي", "click on التالي");
        ActionsHelper.actionClickScrollStepClick("next", By.xpath("//div[@class='PH Tabs__content active']//div[@class='card']"));


        logManager.STEP("8. Fill the mandatory information for income in the income table and select all check boxes under pension table in بيانات الدخل tab (make sure that the tab marked as completed)", "Fill the mandatory information for income in the income table and select all check boxes under pension table in بيانات الدخل tab (make sure that the tab marked as completed) ");
        ActionsHelper.driverWait(3000);
        List<WebElement> incomeList = driver.get().findElements(By.xpath("//*[contains(@id,\"SelftAddSalaryDetails\")]"));
        incomeList.stream().forEachOrdered(income -> {
            ActionsHelper.isElementPresent(income);
            ActionsHelper.driverWait(2000);
            ActionsHelper.retryClick(income, 30);
            ActionsHelper.driverWait(2000);
            driver.get().switchTo().frame(0);
            ActionsHelper.retryClick(By.cssSelector("[tabindex='2']"), 30);
            ActionsHelper.selectOption(By.cssSelector("[tabindex='2']"), "__ossli_1");
            ActionsHelper.driverWait(2000);
            driver.get().findElement(By.id("fileinputPopup_AddMemberIncome99050")).sendKeys("C:\\Users\\KhaledMa'ayeh\\Downloads\\pdf-test.pdf");
            ActionsHelper.driverWait(2000);
            ActionsHelper.retryClick(By.cssSelector(".button"), 30);
            driver.get().switchTo().defaultContent();


        });
        ActionsHelper.scrollTo(By.xpath("//span[.='لائحة التقاعد']"));
        ActionsHelper.isElementPresent(By.xpath("//span[.='لائحة التقاعد']"));
        List<WebElement> salarySetList = driver.get().findElements(By.xpath("//input[contains(@id,\"DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent4_wtApplicantIncomeInfo_wtGetMemberList\")]"));
        salarySetList.stream().forEachOrdered(salaryElememnt -> {
            ActionsHelper.driverWait(2000);
            if (!salaryElememnt.isSelected()) {

            }
            ActionsHelper.retryClick(salaryElememnt, 30);
        });
        logManager.STEP("9. Click on التالي", "Click on التالي");
        ActionsHelper.driverWait(3000);
        ActionsHelper.waitForExpectedElement(By.xpath("//div[@class='PH Tabs__content active']//div[@class='card']"));
        ActionsHelper.actionClickScrollStepClick("Click on التالي in بيانات العائلة tab", By.xpath("//div[@class='PH Tabs__content active']//div[@class='card']"));
        logManager.STEP("10. Check all boxes under بيانات الدعم  tab (make sure that the tab marked as completed)", "Check all boxes under بيانات الدعم  tab (make sure that the tab marked as completed)");
        List<WebElement> incomeElements = driver.get().findElements(By.xpath("//input[contains(@id,\"DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent5_wtSupportIcomeTab_wtGetFamilyMemberList\")]"));
        incomeElements.stream().forEachOrdered(incomeElement -> {
            ActionsHelper.driverWait(2000);
            if (!incomeElement.isSelected()) {
                ActionsHelper.retryClick(incomeElement, 30);
            }
        });
        logManager.STEP("11. Click on التالي", "Click on التالي");
        ActionsHelper.driverWait(3000);
        ActionsHelper.waitForExpectedElement(By.xpath("//div[@class='PH Tabs__content active']//div[@class='card']"));
        ActionsHelper.actionClickScrollStepClick("Click on التالي in بيانات العائلة tab", By.xpath("//div[@class='PH Tabs__content active']//div[@class='card']"));
        logManager.STEP("12. Fill the mandatory information in دخل الاعمال التجارية  tab (make sure that the tab marked as completed)", "Fill the mandatory information in دخل الاعمال التجارية  tab (make sure that the tab marked as completed)");
        List<WebElement> listStoreIncome = driver.get().findElements(By.xpath("//input[contains(@id,\"DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent6_wtBusinessIncomeInfo_wtApplicationIndividualByProperty\")]"));
        listStoreIncome.stream().forEachOrdered(storeIncome -> {
            ActionsHelper.driverWait(2000);
            ActionsHelper.retryClick(storeIncome, 30);
        });
        logManager.STEP("13. Click on التالي", "Click on التالي");
        ActionsHelper.driverWait(3000);
        ActionsHelper.waitForExpectedElement(By.xpath("//div[@class='PH Tabs__content active']//div[@class='card']"));
        ActionsHelper.actionClickScrollStepClick("Click on التالي in بيانات العائلة tab", By.xpath("//div[@class='PH Tabs__content active']//div[@class='card']"));
        ActionsHelper.retryClick(By.id("DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent8_wtbtn_ab7Submit"), 30);
        driver.get().switchTo().frame(0);
        List<WebElement> complianceWith = driver.get().findElements(By.xpath("//input[contains(@id,\"CloneOfWebPatterns_wt9_block_wtMainContent_WebPatterns_wt\")]"));
        complianceWith.stream().forEachOrdered(comp -> {
            ActionsHelper.driverWait(2000);
            if (!comp.isSelected()) {
                ActionsHelper.retryClick(comp, 30);
            }
        });

        ActionsHelper.driverWait(3000);
        ActionsHelper.retryClick(By.xpath("//input[@class='Button Is_Default']"), 30);
        driver.get().switchTo().defaultContent();
        ActionsHelper.driverWait(5000);
        driver.get().switchTo().frame(0);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(By.xpath("//input[@class='Button Is_Default']"), 30);
        ActionsHelper.driverWait(5000);
        ActionsHelper.isElementPresent(By.xpath("//*[contains(@id,\"DCDWebPortalTheme_wt90_block_wtMainContent_wt193_wtSSPCode3\")]"));

        ActionsHelper.retryClick(By.xpath("//input[@class='Button MenuButton LogoutButton']"), 30);
        logManager.STEP("14. Login by super user, and assign the application to specialist from ادارة المراجعين", "Login by super user, and assign the application to specialist from ادارة المراجعين ");
        homePage.navigateToLogin();
        loginPage.loginWithUser(UserType.Superuser);
        auditorsManagementPage.selectSpecialist(UserType.Specialist2.getUserName(), "SSP-12256");
        agentPage.logOut();
        logManager.STEP("15. Login by the specialist", "Login by the specialist");
        homePage.navigateToLogin();
        loginPage.loginWithUser(UserType.Specialist2);

        logManager.STEP("16. Look for SSP code under قائمة المهام", "Look for SSP code under قائمة المهام ");
        logManager.STEP("17. Click on application to view the details page", "Click on application to view the details page");
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


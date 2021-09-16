package com.qpros.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qpros.common.web.Base;
import com.qpros.common.web.Util;
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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.qpros.pages.web.SSA.modules.ApproveApplicationModule.refCode;
@Listeners(com.qpros.common.LogManager.class)
public class BCOCApprove extends Base {


    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());

    }

    @BeforeMethod(enabled = true)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser();
    }
    ApproveApplicationModule approveApplicationModule = new ApproveApplicationModule(driver.get());
    ClaimantLogin claimantLogin = new ClaimantLogin(driver.get());
    HomePage homePage = new HomePage(driver.get());
    LoginPage loginPage = new LoginPage(driver.get());
    AgentPage agentPage = new AgentPage(driver.get());
    AuditorsManagementPage auditorsManagementPage = new AuditorsManagementPage(driver.get());
    BusinessParametersPage businessParametersPage = new BusinessParametersPage(driver.get());
    PaymentSpecialistPage paymentSpecialistPage = new PaymentSpecialistPage(driver.get());
    ClaimantApplicationPage claimantPage = new ClaimantApplicationPage(driver.get());

    private static final Pattern p = Pattern.compile("(^[^\\s]+)");
    Matcher matcher;
    public String committeeName;
    /*
    @Test(description = "Approve an application", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void approveApplication() throws JsonProcessingException, AWTException, InterruptedException {
        approveApplicationModule.approveApplication(false);
    }


     */

    @Test(description = "Bcoc Approve", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class, groups = {"Daily"})
    public void bcocApprove() throws JsonProcessingException, AWTException, InterruptedException {
//        //1st assessment - Approve
       approveApplicationModule.approveApplication(false);
//        // approveApplicationModule.approveExistingApplication(ApproveApplicationModule.refCode);
        logManager.STEP("2. Login to beneficiary side with the EID", "the Beneficiary User conduct login using EID" + TestData.EID);
          ActionsHelper.driverWait(3000);
        claimantLogin.claimantLogin(TestData.EID);
        claimantPage.bcocApprove();
        ActionsHelper.retryClick(By.xpath("//input[@class='Button MenuButton LogoutButton']"), 30);
        logManager.STEP("14. Login by super user, and assign the application to specialist from ادارة المراجعين", "Login by super user, and assign the application to specialist from ادارة المراجعين ");
        homePage.navigateToLogin();
        loginPage.loginWithUser(UserType.Superuser);
        auditorsManagementPage.selectSpecialist(UserType.Specialist2.getUserName(), refCode);
        agentPage.logOut();
        logManager.STEP("15. Login by the specialist", "Login by the specialist");
        homePage.navigateToLogin();
        loginPage.loginWithUser(UserType.Specialist2);
        logManager.STEP("16. Look for SSP code under قائمة المهام", "Look for SSP code under قائمة المهام ");
        ActionsHelper.driverWait(2000);
        String seniorSpecialist = agentPage.specialistApproval(refCode,false);
        ActionsHelper.driverWait(2000);


       logManager.STEP("19. Go to الطلبات المقدمة tab and look for this SSP and check to whom its assigned","");
        matcher = p.matcher(seniorSpecialist);
        if (matcher.find()) {
            System.out.println(matcher.group(0));
            seniorSpecialist =matcher.group(0);
        }
        System.out.println(seniorSpecialist);

        agentPage.logOut();
        //String seniorSpecialist = UserType.SeniorSpecialist100.getUserName();
        ActionsHelper.driverWait(5000);
        loginPage.loginWithUser(UserType.valueOf(seniorSpecialist));
        // loginPage.loginWithUser(UserType.SeniorSpecialist100);
        committeeName = agentPage.seniorSpecialistApproval(refCode);

        System.out.println("Committee: " + committeeName);
        driver.get().navigate().to(urls.tasksList);
        //committeeName = committeeName.replace("Committee", "").replace("\n", "");
        agentPage.logOut();
        if (committeeName.contains("ApplicationDirector")) {
            committeeName = committeeName.replace("Manager", "").replace("\n", "");
            loginPage.loginWithUser(UserType.valueOf(committeeName));
            agentPage.seniorSpecialistApproval(refCode);
            driver.get().navigate().to(urls.tasksList);
            agentPage.logOut();
        } else {
            System.out.println("this is comettee nammeeee here plz " +committeeName);
            committeeName = committeeName.replace("\n", "");
            if (committeeName.contains(UserType.Committee100.getUserName())) {
                loginPage.loginWithUser(UserType.Committee100);
            } else {
                loginPage.loginWithUser(UserType.Committee1);
            }

            agentPage.committeeSpecialistApproval(refCode);
            //driver.get().navigate().to("https://uat.ssa.gov.ae/DCDAgentFrontEnd/TasksList.aspx");
            agentPage.logOut();
        }

        driver.get().navigate().to(urls.agentLogin);
        //String refCode = "SSP-10679";
        loginPage.loginWithUser(UserType.Superuser);
        driver.get().navigate().to(urls.businessParameters);
        businessParametersPage.releaseAppliaction(refCode);
        agentPage.logOut();
        loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
        Assert.assertTrue(paymentSpecialistPage.checkPaymentExistence(refCode));
        agentPage.logOut();


    }






}


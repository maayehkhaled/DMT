package com.qpros.pages.web.SSA.modules;

import com.qpros.common.web.Base;
import com.qpros.helpers.FileUtils;
import com.qpros.pages.web.SSA.*;
import com.qpros.quanta.Status;
import com.qpros.quanta.markuputils.MarkupHelper;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.common.locators.urls;
import com.ssa.core.service.DeleteEmirateId;
import com.ssa.core.service.SubmitApplicationService;
import com.ssa.core.service.VerifyEligibilityService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class RejectApplicationModule extends Base {


    public RejectApplicationModule(WebDriver driver){
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
    DeleteEmirateId deleteEmirateId=new DeleteEmirateId();
   public String refCode ;
    public void RejectApplication() throws Exception {
        //URL: https://uat.ssa.gov.ae/DCDAgentPortalTheme/Login.aspx
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

            loginPage.loginWithUser(UserType.Superuser);
            auditorsManagementPage.selectSpecialist(UserType.Specialist2.getUserName(), refCode);
            agentPage.logOut();

            loginPage.loginWithUser(UserType.Specialist2);
            String seniorSpecialist = agentPage.specialistRejectApplication(refCode);
            System.out.println(seniorSpecialist);
            seniorSpecialist = seniorSpecialist.replace("Supervisor", "").replace("\n", "");

            System.out.println(seniorSpecialist);
            agentPage.logOut();

            loginPage.loginWithUser(UserType.valueOf(seniorSpecialist));

            String committeeName = agentPage.seniorSpecialistRejectApplication(refCode);
            System.out.println(committeeName);
            agentPage.logOut();

            homePage.navigateToLogin();

            //String refCode = "SSP-10679";
            loginPage.loginWithUser(UserType.Superuser);
            driver.get().navigate().to(urls.businessParameters);
            businessParametersPage.releaseAppliaction(refCode);
            agentPage.logOut();
            loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
            Assert.assertFalse(paymentSpecialistPage.checkPaymentExistence(refCode));
        }
    }
    public void rejectExistingApplication (String refCode){
        loginPage.loginWithUser(UserType.Specialist2);
        String seniorSpecialist = agentPage.specialistRejectApplication(refCode);
        System.out.println(seniorSpecialist);
        seniorSpecialist = seniorSpecialist.replace("Supervisor", "").replace("\n", "");

        System.out.println(seniorSpecialist);
        agentPage.logOut();

        loginPage.loginWithUser(UserType.valueOf(seniorSpecialist));
        String committeeName = agentPage.seniorSpecialistRejectApplication(refCode);
        System.out.println(committeeName);
        agentPage.logOut();

        homePage.navigateToLogin();

        //String refCode = "SSP-10679";
        loginPage.loginWithUser(UserType.Superuser);
        driver.get().navigate().to(urls.businessParameters);
        businessParametersPage.releaseAppliaction(refCode);
        agentPage.logOut();
        loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
        Assert.assertFalse(paymentSpecialistPage.checkPaymentExistence(refCode));
    }
}

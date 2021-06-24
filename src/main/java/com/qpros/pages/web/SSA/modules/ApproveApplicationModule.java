package com.qpros.pages.web.SSA.modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.pages.web.SSA.*;
import com.qpros.quanta.Status;
import com.qpros.quanta.markuputils.MarkupHelper;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.service.SubmitApplicationService;
import com.ssa.core.service.VerifyEligibilityService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApproveApplicationModule extends Base {


    public ApproveApplicationModule(WebDriver driver) {
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
    private static final Pattern p = Pattern.compile("(^[^\\s]+)");
    Matcher matcher;
    public String committeeName;
    public static String refCode;
    public void approveApplication(boolean incOrDecApp) throws JsonProcessingException, AWTException {
        //URL: https://10.231.1.100/DCDAgentPortalTheme/Login.aspx
        driver.get().navigate().to("https://10.231.1.100/DCDAgentPortalTheme/Login.aspx");

        this.logManager.STEP("VE from 12x12 API", "The System Verify the User Eligibility by calling 12X12 API");
        this.logManager.INFO("Verify Eligibility Service Call", false);
        verifyEligibilityService.requestService();
        QuantaTestManager.getTest().log(Status.INFO, MarkupHelper.createCodeBlock(verifyEligibilityService.response.getBody()));
        if (verifyEligibilityService.getresponse(verifyEligibilityService).application.isEligible) {
            QuantaTestManager.getTest().assignCategory("1st Assessment");
            this.logManager.STEP("Submit Application from 12x12 API", "The System Submit Application by calling 12X12 API");
            this.logManager.INFO("Submit Application Service Call", false);
            submitApplicationService.requestService();
            QuantaTestManager.getTest().log(Status.INFO, MarkupHelper.createCodeBlock(submitApplicationService.response.getBody()));

            refCode = submitApplicationService.getresponse(submitApplicationService).applicationSummary.referenceNumber;
            //String refCode = "SSP-10676";
            homePage.navigateToLogin();

            loginPage.loginWithUser(UserType.Superuser);
            this.logManager.STEP("VE from 12x12 API", "The System Verify the User Eligibility by calling 12X12 API");
            this.logManager.STEP(" Login by super user, and assign the application to specialist from ادارة المراجعين ", "");


            auditorsManagementPage.selectSpecialist(UserType.Specialist2.getUserName(), refCode);
            agentPage.logOut();

            loginPage.loginWithUser(UserType.Specialist2);
            ActionsHelper.driverWait(5000);
            String seniorSpecialist = agentPage.specialistApproval(refCode,incOrDecApp);
            if (seniorSpecialist.contains("-")) {
                agentPage.getAssigneeNameFromAllApplications(refCode);
            }
            ActionsHelper.driverWait(5000);
            System.out.println(seniorSpecialist);
//            seniorSpecialist = seniorSpecialist.replace("Supervisor", "").replace("\n", "");
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
            driver.get().navigate().to("https://10.231.1.100/DCDAgentFrontEnd/TasksList.aspx");
            //committeeName = committeeName.replace("Committee", "").replace("\n", "");
            agentPage.logOut();
            if (committeeName.contains("ApplicationDirector")) {
                committeeName = committeeName.replace("Manager", "").replace("\n", "");
                loginPage.loginWithUser(UserType.valueOf(committeeName));
                agentPage.seniorSpecialistApproval(refCode);
                driver.get().navigate().to("https://10.231.1.100/DCDAgentFrontEnd/TasksList.aspx");
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
                //driver.get().navigate().to("https://10.231.1.100/DCDAgentFrontEnd/TasksList.aspx");
                agentPage.logOut();
            }

            driver.get().navigate().to("https://10.231.1.100/DCDAgentPortalTheme/Login.aspx");
            //String refCode = "SSP-10679";
            loginPage.loginWithUser(UserType.Superuser);
            driver.get().navigate().to("https://10.231.1.100/DCDBusinessParameters/BusinessParameters.aspx");
            businessParametersPage.releaseAppliaction(refCode);
            agentPage.logOut();
            loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
            Assert.assertTrue(paymentSpecialistPage.checkPaymentExistence(refCode));
            agentPage.logOut();
        }

    }

    public void approveExistingApplication(String refCode) throws AWTException {
        loginPage.loginWithUser(UserType.Specialist2);
        ActionsHelper.driverWait(5000);
        String seniorSpecialist = agentPage.specialistApproval(refCode, false);
        if (seniorSpecialist.contains("-")) {
            agentPage.getAssigneeNameFromAllApplications(refCode);
        }
        ActionsHelper.driverWait(5000);
        System.out.println(seniorSpecialist);
//            seniorSpecialist = seniorSpecialist.replace("Supervisor", "").replace("\n", "");
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
        driver.get().navigate().to("https://10.231.1.100/DCDAgentFrontEnd/TasksList.aspx");
        //committeeName = committeeName.replace("Committee", "").replace("\n", "");
        agentPage.logOut();
        if (committeeName.contains("ApplicationDirector")) {
            committeeName = committeeName.replace("Manager", "").replace("\n", "");
            loginPage.loginWithUser(UserType.valueOf(committeeName));
            agentPage.seniorSpecialistApproval(refCode);
            driver.get().navigate().to("https://10.231.1.100/DCDAgentFrontEnd/TasksList.aspx");
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
            //driver.get().navigate().to("https://10.231.1.100/DCDAgentFrontEnd/TasksList.aspx");
            agentPage.logOut();
        }

        driver.get().navigate().to("https://10.231.1.100/DCDAgentPortalTheme/Login.aspx");
        //String refCode = "SSP-10679";
        loginPage.loginWithUser(UserType.Superuser);
        driver.get().navigate().to("https://10.231.1.100/DCDBusinessParameters/BusinessParameters.aspx");
        businessParametersPage.releaseAppliaction(refCode);
        agentPage.logOut();
        loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
        Assert.assertTrue(paymentSpecialistPage.checkPaymentExistence(refCode));
        agentPage.logOut();
    }
}
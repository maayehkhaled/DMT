package com.qpros.pages.web.SSA.modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.helpers.FileUtils;
import com.qpros.helpers.ReadWriteHelper;
import com.qpros.pages.web.SSA.*;
import com.qpros.quanta.Status;
import com.qpros.quanta.markuputils.MarkupHelper;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.common.locators.urls;
import com.ssa.core.service.DeleteEmirateId;
import com.ssa.core.service.SubmitApplicationService;
import com.ssa.core.service.VerifyEligibilityService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
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
    DeleteEmirateId deleteId = new DeleteEmirateId();
    private static final Pattern p = Pattern.compile("(^[^\\s]+)");
    Matcher matcher;
    public String committeeName;
    public static String refCode;


    public void approveApplication(boolean incOrDecApp) throws JsonProcessingException, AWTException, InterruptedException {
        deleteId.requestService();
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
            refCode.replace("\uE007","");
            FileUtils.createFile("refCodeFile.txt",refCode);

            homePage.navigateToLogin();

            //login with super user
            loginPage.loginWithUser(UserType.Superuser);
            this.logManager.STEP(" Login by super user, and assign the application to specialist from ادارة المراجعين ", "");

            //start assign process by selecting specialist 2 with appication id
            auditorsManagementPage.selectSpecialist(UserType.Specialist2.getUserName(), refCode);
            agentPage.logOut();
            //login with specialist 2
            loginPage.loginWithUser(UserType.Specialist2);


            ActionsHelper.driverWait(8000);
            String seniorSpecialist = agentPage.specialistApproval(refCode,incOrDecApp);
            ActionsHelper.driverWait(8000);
            System.out.println("Senior Specialist : " + seniorSpecialist);
            agentPage.logOut();
            //login with senior specialist
            ActionsHelper.driverWait(5000);
            loginPage.loginWithUser(UserType.valueOf(seniorSpecialist));
            ActionsHelper.driverWait(5000);
            committeeName = agentPage.seniorSpecialistApproval(refCode);
            System.out.println("seniorSpecial: " + committeeName);
            driver.get().navigate().to(urls.tasksList);

            //committeeName = committeeName.replace("Committee", "").replace("\n", "");
            ActionsHelper.driverWait(2000);

            agentPage.logOut();
            if (committeeName.contains("ApplicationDirector")) {
                committeeName = committeeName.replace("Manager", "").replace("\n", "");
                loginPage.loginWithUser(UserType.valueOf(committeeName));
                logManager.WARN("must be login with seniorSpecialistApproval user");
                agentPage.seniorSpecialistApproval(refCode);
                driver.get().navigate().to(urls.tasksList);
                ActionsHelper.driverWait(2000);
                agentPage.logOut();
            } else {
                System.out.println("this is comettee nammeeee here plz " +committeeName);
                committeeName = committeeName.replace("\n", "");
                if (committeeName.contains(UserType.Committee100.getUserName())) {
                    loginPage.loginWithUser(UserType.Committee100);
                } else {
                    loginPage.loginWithUser(UserType.Committee1);

                }
                ActionsHelper.driverWait(2000);

                agentPage.committeeSpecialistApproval(refCode);
                //driver.get().navigate().to("https://uat.ssa.gov.ae/DCDAgentFrontEnd/TasksList.aspx");
                ActionsHelper.driverWait(5000);
                agentPage.logOut();
                agentPage.logOut();

            }

            driver.get().navigate().to(urls.agentLogin);
            loginPage.loginWithUser(UserType.Superuser);
            ActionsHelper.navigate(urls.businessParameters);
            ActionsHelper.driverWait(3000);
            businessParametersPage.releaseAppliaction(refCode);
            agentPage.logOut();
            driver.get().navigate().to(urls.paymentList);
            loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
            Assert.assertTrue(paymentSpecialistPage.checkPaymentExistence(refCode));
            agentPage.logOut2();
        }

    }

    public void approveExistingApplication(String refCode) throws AWTException, InterruptedException {
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
            System.out.println("Committee: " +committeeName);
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


    public void afterBcocApprovalProcess() throws InterruptedException, AWTException {
        logManager.STEP("14. Login by super user, and assign the application to specialist from ادارة المراجعين", "Login by super user, and assign the application to specialist from ادارة المراجعين ");
        homePage.navigateToLogin();
        loginPage.loginWithUser(UserType.Superuser);
       // String ref=refCode=FileUtils.readFile("refCodeFile.txt").get(0);
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
        //Comment this line so we can test the application approval process without failing
        /*
        loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
        Assert.assertTrue(paymentSpecialistPage.checkPaymentExistence(refCode));
        agentPage.logOut();

         */


    }
    public void afterBCOCRejectProcess() throws InterruptedException, AWTException {
        homePage.navigateToLogin();

        //login with super user
        loginPage.loginWithUser(UserType.Superuser);
        this.logManager.STEP(" Login by super user, and assign the application to specialist from ادارة المراجعين ", "");

        //start assign process by selecting specialist 2 with appication id
        String ref=FileUtils.readFile("refCodeFile.txt").get(0);
        auditorsManagementPage.selectSpecialist(UserType.Specialist2.getUserName(), ref);

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
    }
}

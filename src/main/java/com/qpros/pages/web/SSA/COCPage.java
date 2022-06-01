package com.qpros.pages.web.SSA;

import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.pages.web.SSA.commonSSA.Steps;
import com.ssa.core.common.locators.urls;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class COCPage extends Base {
    public COCPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    private By searchButton = By.xpath("//input[@class='Button ButtonDefault Is_Default']");
    private By searchFieldSSP = By.id("DCDAgentPortalTheme_wt194_block_wtMainContent_WebPatterns_wt179_block_wtContent_wttxt_SearchEligible");
    private By firstRequest = By.xpath("//input[@id=\"DCDAgentPortalTheme_wt194_block_wtMainContent_WebPatterns_wt179_block_wtContent_wtBenefitRequests_ctl03_wt211\"]");
    private By commentSection = By.id("DCDAgentPortalTheme_wt194_block_wtMainContent_WebPatterns_wt179_block_wtContent_wttxt_CommentIn");
    private By launchCocProcess = By.id("DCDAgentPortalTheme_wt194_block_wtMainContent_WebPatterns_wt179_block_wtContent_wtbtn_LaunchCOC");
    private By cocPage = By.xpath("//a[@id=\"InternalPortalTheme_wt85_block_wtMenu_AgentPortal_CW_wt90_block_RichWidgets_wt90_block_wtMenuItem_wt55\"]");
    public String committeeName;

    BusinessParametersPage businessParametersPage = new BusinessParametersPage(driver.get());
    PaymentSpecialistPage paymentSpecialistPage = new PaymentSpecialistPage(driver.get());
    Matcher matcher;
    AgentPage agentPage = new AgentPage(driver.get());
    private static final Pattern p = Pattern.compile("(^[^\\s]+)");
    LoginPage loginPage = new LoginPage(driver.get());
    Steps step = new Steps(driver.get());

    /**
     * This method to go cocPage
     */
    public void navigateToCoc(){
        logManager.STEP("Navigate to COC", "Navigate to https://uat.ssa.gov.ae/DCDBusinessParameters/CoC.aspx");
       ActionsHelper.driverWait(3000);
        driver.get().navigate().to(urls.cocPage);
    }

    public void startCocProcess(String refCode){
        logManager.STEP("Input search field", "Inputs the SSP code and press enter");
        //ActionsHelper.actionClickStepClick("Click on COC page", cocPage);
        ActionsHelper.driverWait(3000);
        ActionsHelper.sendKeys(searchFieldSSP, refCode);
        ActionsHelper.driverWait(4000);
        logManager.STEP("Search For the Application", "Inputs the SSP code and press enter");
        ActionsHelper.driverWait(3000);
        ActionsHelper.clickAction(searchButton);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickStepClick("Click on first application search checkbox", firstRequest);
        ActionsHelper.sendKeys(commentSection, "testComment");
        ActionsHelper.driverWait(1000);
        ActionsHelper.actionClickStepClick("Click Launch Process", launchCocProcess);

        try {

            driver.get().switchTo().alert().accept();
            ActionsHelper.driverWait(5000);
            logManager.INFO("Accept coc msg ",false);
        } catch (Exception e) {
            logManager.WARN("does not interact with popup msg");
        }
        ActionsHelper.driverWait(5000);
        driver.get().navigate().to(urls.tasksList);

    }


    public void acocApprove(String refCode,boolean incOrDecApp) {
        startCocProcess(refCode);
        logManager.STEP("Search application", "Inputs the reference number in the search field");
        logManager.INFO("Search on Application", false);
        String specialist = step.refreshTheListOfApplications(refCode);
        System.out.println("Specialist type " + specialist);
        agentPage.logOut();
        loginPage.loginWithUser(UserType.valueOf(specialist));
        ActionsHelper.driverWait(8000);
        String seniorSpecialist = agentPage.specialistApproval(refCode, incOrDecApp);
        ActionsHelper.driverWait(8000);
        System.out.println("Senior Specialist : " + seniorSpecialist);
        agentPage.logOut();

        ActionsHelper.driverWait(5000);
        loginPage.loginWithUser(UserType.valueOf(seniorSpecialist));
        ActionsHelper.driverWait(5000);
        committeeName = agentPage.seniorSpecialistApproval(refCode);
        System.out.println("Committee: " + committeeName);
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
            System.out.println("this is comettee nammeeee here plz " + committeeName);
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
    public void acocReject(String refCode)  {
        //startCocProcess(refCode);
        logManager.STEP("Search application", "Inputs the reference number in the search field");
        String specialist = step.refreshTheListOfApplications(refCode);
        System.out.println("Specialist type " + specialist);
        agentPage.logOut();
        loginPage.loginWithUser(UserType.valueOf(specialist));
        String seniorSpecialist = agentPage.specialistRejectApplication(refCode);
        ActionsHelper.driverWait(4000);
        System.out.println("Senior Specialist : " + seniorSpecialist);
        agentPage.logOut();
        //String seniorSpecialist = UserType.SeniorSpecialist100.getUserName();
        ActionsHelper.driverWait(5000);
        loginPage.loginWithUser(UserType.valueOf(seniorSpecialist));
        //Change the function for seniorSpecialistApproval
        ActionsHelper.driverWait(5000);
        agentPage.seniorSpecialistRejectApplication(refCode);
        String assignedUser = agentPage.seniorSpecialistRejectApplication(refCode);
        loginPage.loginWithUser(UserType.valueOf(assignedUser));
        ActionsHelper.driverWait(4000);
        agentPage.committeeSpecialistRejection(refCode);
        //driver.get().navigate().to("https://uat.ssa.gov.ae/DCDAgentFrontEnd/TasksList.aspx");
        agentPage.logOut();
        // Add a function for application approval

        driver.get().navigate().to(urls.tasksList);
        //committeeName = committeeName.replace("Committee", "").replace("\n", "");
        agentPage.logOut();
        loginPage.loginWithUser(UserType.Superuser);
        driver.get().navigate().to(urls.businessParameters);
        businessParametersPage.releaseAppliaction(refCode);
        agentPage.logOut();

        driver.get().navigate().to(urls.paymentList);
        loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
        ActionsHelper.driverWait(4000);
        Assert.assertTrue(paymentSpecialistPage.checkPaymentExistence(refCode));
        //agentPage.logOut();





    }

    public void refreshCOCApprove(String refCode) throws AWTException {
        logManager.STEP("Search application", "Inputs the reference number in the search field");
        loginPage.loginWithUser(UserType.Specialist2);
        ActionsHelper.driverWait(5000);
        String seniorSpecialist = agentPage.specialistApproval(refCode);
        matcher = p.matcher(seniorSpecialist);
        if (matcher.find()) {
            System.out.println(matcher.group(0));
            seniorSpecialist =matcher.group(0);
        }
        System.out.println("Senior Specialist : " + seniorSpecialist);

        agentPage.logOut();
        //String seniorSpecialist = UserType.SeniorSpecialist100.getUserName();
        ActionsHelper.driverWait(5000);
        agentPage.seniorSpecialistRejectApplication(refCode);
        // loginPage.loginWithUser(UserType.SeniorSpecialist100);
        String user = agentPage.seniorSpecialistApproval(refCode);
        loginPage.loginWithUser(UserType.valueOf(user));
        // Add a function for application approval
        agentPage.committeeSpecialistApproval(refCode);
        //driver.get().navigate().to("https://uat.ssa.gov.ae/DCDAgentFrontEnd/TasksList.aspx");
        agentPage.logOut();

        driver.get().navigate().to(urls.tasksList);
        loginPage.loginWithUser(UserType.Superuser);
        driver.get().navigate().to(urls.businessParameters);
        businessParametersPage.releaseAppliaction(refCode);
        agentPage.logOut();
        loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
        Assert.assertTrue(paymentSpecialistPage.checkPaymentExistence(refCode));
        agentPage.logOut();



    }

    public void refreshCOCReject(String refCode) throws AWTException {
        logManager.STEP("Search application", "Inputs the reference number in the search field");
        loginPage.loginWithUser(UserType.Specialist2);
        ActionsHelper.driverWait(5000);
        String seniorSpecialist = agentPage.specialistApproval(refCode);
        matcher = p.matcher(seniorSpecialist);
        if (matcher.find()) {
            System.out.println(matcher.group(0));
            seniorSpecialist =matcher.group(0);
        }
        System.out.println("Senior Specialist : " + seniorSpecialist);

        agentPage.logOut();
        //String seniorSpecialist = UserType.SeniorSpecialist100.getUserName();
        ActionsHelper.driverWait(5000);
        agentPage.seniorSpecialistRejectApplication(refCode);
        // loginPage.loginWithUser(UserType.SeniorSpecialist100);
        String user = agentPage.seniorSpecialistRejectApplication(refCode);
        loginPage.loginWithUser(UserType.valueOf(user));
        // Add a function for application approval
        agentPage.committeeSpecialistApproval(refCode);
        //driver.get().navigate().to("https://uat.ssa.gov.ae/DCDAgentFrontEnd/TasksList.aspx");
        agentPage.logOut();

        driver.get().navigate().to(urls.tasksList);
        loginPage.loginWithUser(UserType.Superuser);
        driver.get().navigate().to(urls.businessParameters);
        businessParametersPage.releaseAppliaction(refCode);
        agentPage.logOut();
        loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
        Assert.assertTrue(paymentSpecialistPage.checkPaymentExistence(refCode));
        agentPage.logOut();



    }





}

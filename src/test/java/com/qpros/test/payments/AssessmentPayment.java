package com.qpros.test.payments;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.pages.web.SSA.LoginPage;
import com.qpros.pages.web.SSA.UserType;
import com.qpros.pages.web.SSA.modules.ApproveApplicationModule;
import com.qpros.pages.web.SSA.payments.AssessmentPaymentPage;
import com.qpros.reporting.QuantaTestManager;
import com.ssa.core.common.locators.urls;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;

@Listeners(com.qpros.common.LogManager.class)
public class AssessmentPayment extends Base {


    LoginPage loginPage = new LoginPage(driver.get());
    AssessmentPaymentPage assessmentPaymentPage = new AssessmentPaymentPage(driver.get());
    ApproveApplicationModule approveApplicationModule = new ApproveApplicationModule(driver.get());

    @BeforeClass
    public void initSuite() {
        QuantaTestManager.startTestSuite(getClass().getSimpleName());

    }

    @BeforeMethod(enabled = true)
    public synchronized void setTestSuite() throws IOException {
        this.setUpBrowser();
    }


        @Test(description = "New schedules, create card, and generate instructions for new 1st assessment Application  ",priority = 1,retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
        public void newSchedulesNewCard() throws ParseException, JsonProcessingException, InterruptedException, AWTException {
                approveApplicationModule.approveApplication(false);

            ActionsHelper.navigate(urls.paymentList);
            loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
            assessmentPaymentPage.differenceBetweenDate();
        }
        @Test(description = "Change card status",priority = 2,retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
                public void changeCardStatus () throws JsonProcessingException, InterruptedException, AWTException {
                approveApplicationModule.approveApplication(false);

            ActionsHelper.navigate(urls.paymentList);
            loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
            assessmentPaymentPage.paymentScenario2();
        }

        @Test(
        description = "close Card",priority = 3,retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
        public void closeCard() throws JsonProcessingException, InterruptedException, AWTException {
        approveApplicationModule.approveApplication(false);
            ActionsHelper.navigate(urls.paymentList);
            loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
            assessmentPaymentPage.paymentScenario3();
        }

        @Test(
                description = "Block Card",priority = 4,retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
        public void blockCard() throws JsonProcessingException, InterruptedException, AWTException {
                approveApplicationModule.approveApplication(false);

            ActionsHelper.navigate(urls.paymentList);
            loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
            assessmentPaymentPage.paymentScenario4();
        }
    @Test(
            description = "Create Variances – Overpayment ",priority = 5, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void createVariancesOverpayment () throws JsonProcessingException, InterruptedException, AWTException {
        approveApplicationModule.approveApplication(false);

        ActionsHelper.navigate(urls.paymentList);
        loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
        assessmentPaymentPage.paymentScenario5();
    }

    @Test(
            description = "Create Variances – Under payment ",priority = 6, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void createVariancesUnderPayment() throws JsonProcessingException, InterruptedException, AWTException {

       approveApplicationModule.approveApplication(false);
        ActionsHelper.navigate(urls.paymentList);
        loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
        assessmentPaymentPage.paymentScenario6();
}

    @Test(
            description = "Update Payments Instructions ", priority = 7,retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void updatePaymentsInstructions() throws JsonProcessingException, InterruptedException, AWTException {
            approveApplicationModule.approveApplication(false);

        ActionsHelper.navigate(urls.paymentList);
        loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
        assessmentPaymentPage.paymentScenario7();
    }

    @Test(description = "Update Payments Status – Hold payment",priority = 8, retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void updatePaymentsStatusHoldPayment () throws JsonProcessingException, InterruptedException, AWTException {
            approveApplicationModule.approveApplication(false);

        ActionsHelper.navigate(urls.paymentList);
        loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
        assessmentPaymentPage.paymentScenario8();
    }

    @Test
            (description = "Update Payments Status – Release payment ", priority = 9,retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void updatePaymentsStatusReleasePayment () throws JsonProcessingException, InterruptedException, AWTException {

        approveApplicationModule.approveApplication(false);
        ActionsHelper.navigate(urls.paymentList);
        loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
        assessmentPaymentPage.paymentScenario9();
    }

    @Test
            (description = "Update Payments Status – Terminate payment ",priority = 10,retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void updatePaymentsStatusTerminatePayment () throws JsonProcessingException, InterruptedException, AWTException {
        approveApplicationModule.approveApplication(false);
        ActionsHelper.navigate(urls.paymentList);
        loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
        assessmentPaymentPage.paymentScenario10();
    }
}
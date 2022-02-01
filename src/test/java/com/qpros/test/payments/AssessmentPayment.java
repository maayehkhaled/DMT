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

/*
        @Test(description = "Payment Scenario",retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
        public void payment() throws ParseException {
                approveApplicationModule.approveApplication(false);

            ActionsHelper.navigate(urls.paymentList);
            loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
            assessmentPaymentPage.differenceBetweenDate();
        }
        @Test(description = "Payment Scenario 2",retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
                public void paymentS2() throws JsonProcessingException, InterruptedException, AWTException {
                approveApplicationModule.approveApplication(false);

            ActionsHelper.navigate(urls.paymentList);
            loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
            assessmentPaymentPage.paymentScenario2();
        }*/
/*
        @Test(
        description = "Payment Scenario 3",retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
        public void paymentS3() {
                approveApplicationModule.approveApplication(false);

            ActionsHelper.navigate(urls.paymentList);
            loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
            assessmentPaymentPage.paymentScenario3();
        }

        @Test(
                description = "Payment Scenario 4",retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
        public void paymentS4() {
                approveApplicationModule.approveApplication(false);

            ActionsHelper.navigate(urls.paymentList);
            loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
            assessmentPaymentPage.paymentScenario4();
        }
    @Test(
            description = "Payment Scenario 5", retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void paymentS5() throws JsonProcessingException, InterruptedException, AWTException {
        approveApplicationModule.approveApplication(false);

        ActionsHelper.navigate(urls.paymentList);
        loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
        assessmentPaymentPage.paymentScenario5();
    }

    @Test(
            description = "Payment Scenario 6", retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void paymentS6() throws JsonProcessingException, InterruptedException, AWTException {
        approveApplicationModule.approveApplication(false);
        ActionsHelper.navigate(urls.paymentList);
        loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
        assessmentPaymentPage.paymentScenario6();

}*/

    @Test(
            description = "Payment Scenario 7", retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void paymentS7() throws JsonProcessingException, InterruptedException, AWTException {
            approveApplicationModule.approveApplication(false);

        ActionsHelper.navigate(urls.paymentList);
        loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
        assessmentPaymentPage.paymentScenario7();
    }
/*
    @Test(description = "Payment Scenario 8", retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void paymentS8() throws JsonProcessingException, InterruptedException, AWTException {
            approveApplicationModule.approveApplication(false);

        ActionsHelper.navigate(urls.paymentList);
        loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
        assessmentPaymentPage.paymentScenario8();
    }

    @Test
            (description = "Payment Scenario 9", retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void paymentS9() throws JsonProcessingException, InterruptedException, AWTException {
            approveApplicationModule.approveApplication(false);

        ActionsHelper.navigate(urls.paymentList);
        loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
        assessmentPaymentPage.paymentScenario8();
        assessmentPaymentPage.paymentScenario9();
    }

    @Test
            (description = "Payment Scenario 10", retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void paymentS10() throws JsonProcessingException, InterruptedException, AWTException {
            approveApplicationModule.approveApplication(false);

        ActionsHelper.navigate(urls.paymentList);
        loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
        assessmentPaymentPage.paymentScenario10();
    }*/
}
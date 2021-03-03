package com.qpros.test;

import com.qpros.common.LogManager;
import com.qpros.common.web.Base;
import com.qpros.common.annotation.STEP;
import com.qpros.pages.web.ContactUsPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

@Listeners(LogManager.class)
public class TC_2_ContactUs extends Base {

    @Test(description = "Test Contact Us section – Invalid Email format")
    public void performnavigation() throws NoSuchMethodException {
        ContactUsPage contactUsPage= new ContactUsPage(driver);
        Method m= contactUsPage.getClass().getDeclaredMethod("navigateToContact");
        STEP step= m.getAnnotation(STEP.class);
        logManager.STEP(step.name(),step.description());
        contactUsPage.navigateToContact();
        m= contactUsPage.getClass().getDeclaredMethod("fillContactUsForm");
        step=m.getAnnotation(STEP.class);
        logManager.STEP(step.name(), step.description());
        contactUsPage.fillContactUsForm();
        m= contactUsPage.getClass().getDeclaredMethod("verifyTestOutCome");
        step=m.getAnnotation(STEP.class);
        logManager.STEP(step.name(), step.description());
        contactUsPage.verifyTestOutCome();

    }
}

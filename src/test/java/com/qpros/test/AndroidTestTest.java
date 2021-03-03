package com.qpros.test;

import com.qpros.common.LogManager;
import com.qpros.common.annotation.STEP;
import com.qpros.common.mobile.MobileBase;
import com.qpros.pages.mobile.MobileContactsPage;
import com.qpros.reporting.QuantaTestManager;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.qpros.helpers.ActionsHelper.LOG_MANAGER;

@Listeners(LogManager.class)
public class AndroidTestTest extends MobileBase {

    @Test(description = "add contact to the mobile application")
    public void performnavigation() throws InterruptedException, NoSuchMethodException {
        QuantaTestManager.getTest().assignCategory("Mobile");
        MobileContactsPage mobileContactsPage= new MobileContactsPage(driver);
        Method m= mobileContactsPage.getClass().getDeclaredMethod("clickOnAddContact");
        STEP step= m.getAnnotation(STEP.class);
        LOG_MANAGER.STEP(step.name(),step.description());
        mobileContactsPage.clickOnAddContact();
        m= mobileContactsPage.getClass().getDeclaredMethod("fillForm");
         step= m.getAnnotation(STEP.class);
        LOG_MANAGER.STEP(step.name(),step.description());
        mobileContactsPage.fillForm();
        m= mobileContactsPage.getClass().getDeclaredMethod("saveContact");
        step= m.getAnnotation(STEP.class);
        LOG_MANAGER.STEP(step.name(),step.description());
        mobileContactsPage.saveContact();

    }

}

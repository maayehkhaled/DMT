package com.qpros.test;

import com.qpros.common.LogManager;
import com.qpros.common.web.Base;
import com.qpros.common.annotation.STEP;
import com.qpros.pages.web.TestingTypesPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

@Listeners(LogManager.class)
public class TC_1_TestingTypes extends Base {

    @Test(description = "Browse QPros Services - Testing Types")
    public void performnavigation() throws NoSuchMethodException {
        TestingTypesPage testingTypesPage= new TestingTypesPage(driver);
        Method m= testingTypesPage.getClass().getDeclaredMethod("navigateToSoftwareTesting");
        STEP step= m.getAnnotation(STEP.class);
        logManager.STEP(step.name(),step.description());
        testingTypesPage.navigateToSoftwareTesting();
        m= testingTypesPage.getClass().getDeclaredMethod("checkTestingTypes");
        step=m.getAnnotation(STEP.class);
        logManager.STEP(step.name(), step.description());
        testingTypesPage.checkTestingTypes();

    }
}

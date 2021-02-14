package com.qpros.test;

import com.qpros.common.Base;
import com.qpros.common.DriverType;
import com.qpros.helpers.ActionsHelper;
import com.qpros.helpers.ReadWriteHelper;
import org.openqa.selenium.By;
import org.testng.annotations.*;

@Listeners(com.qpros.common.LogManger.class)
public class TS_3_Payment extends Base {


    @BeforeClass
    public void setUpBrowser() {
        String OsType = OsValidator.getDeviceOs();
        DriverType browser = getBrowser();
        initiateDriver(OsType, browser);
        driver.navigate().to(ReadWriteHelper.ReadData("BaseURL"));
    }

    public static By quickPay = By.linkText("Quick Pay");

    @Test(description = "The User operate quickPay for PostPaid Line Home Page", suiteName = "Payment", priority = 2,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void quickPayment() throws Exception {
        com.qpros.pages.payment.Payment payment= new com.qpros.pages.payment.Payment(driver);
        payment.navigateToQuickPay();
        ActionsHelper.driverWait(3000);

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }
}

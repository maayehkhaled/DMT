package com.qpros.test;


import com.qpros.common.Base;
import com.qpros.common.DriverType;
import com.qpros.common.UserType;
import com.qpros.helpers.ReadWriteHelper;
import com.qpros.pages.accountSettings.AccountSettings;
import com.qpros.pages.checkorder.CheckOrder;
import com.qpros.pages.login.LoginPage;
import com.qpros.pages.placeorder.PlaceOrder;
import org.testng.annotations.*;


@Listeners(com.qpros.common.LogManger.class)
public class TS_2_Order extends Base {
    LoginPage loginPage;

    @BeforeClass
    public void setUpBrowser() {
        String OsType = OsValidator.getDeviceOs();
        DriverType browser = getBrowser();
        initiateDriver(OsType, browser);
        driver.navigate().to(ReadWriteHelper.ReadData("BaseURL"));
    }



    @Test (description = "The User add item to cart",suiteName = "Order", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void placeOrder() throws Exception {
        loginPage = new LoginPage(driver);
        loginPage.signIn(UserType.B2C.getUserName(), UserType.B2C.getPassword());
        PlaceOrder.checkPlaceOrder();

    }

    @Test (description = "Track Order History Page", priority = 2,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void trackOrder() throws InterruptedException {
        CheckOrder.checkOrders();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }

}

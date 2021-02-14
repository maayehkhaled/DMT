package com.qpros.common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.qpros.helpers.ActionsHelper;
import com.qpros.reporting.ExceptionListner;
import com.qpros.reporting.ExtentManager;
import com.qpros.reporting.ExtentTestManager;
import com.qpros.utils.ConsoleColors;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class LogManger implements ITestListener {

    static Logger logger = Logger.getLogger(LogManger.class.getSimpleName());
    private static ExtentReports extent = new ExtentReports();

    static {
        extent = ExtentManager.createInstance();
    }

    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();


    public LogManger() {
    }

    public LogManger(String classObjName) {
        logger = Logger.getLogger(classObjName);
    }

    public void INFO(String message) {
        logger.info(ConsoleColors.BLUE + message + ConsoleColors.RESET);
        try {
            Capture();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ExtentTestManager.getTest().info("Screen Shot",MediaEntityBuilder.createScreenCaptureFromBase64String(ActionsHelper.takeScreenShot()).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ExtentTestManager.getTest().log(Status.INFO, message);
    }

    public void Capture() throws Exception {
        logger.info(ConsoleColors.BLUE + ActionsHelper.takeScreenShot() + ConsoleColors.RESET);

        ExtentTestManager.getTest().addScreenCaptureFromBase64String(ActionsHelper.takeScreenShot());
    }

    public void INFO(Integer message) throws Exception {
        logger.info(ConsoleColors.BLUE + message + ConsoleColors.RESET);

        ExtentTestManager.getTest().log(Status.INFO, String.valueOf(message));

    }

    public void DEBUG(String message) {
        logger.debug(ConsoleColors.GREEN + message + ConsoleColors.RESET);
        ExtentTestManager.getTest().log(Status.DEBUG, message);

    }

    public void WARN(String message) {
        logger.warn(ConsoleColors.YELLOW + message + ConsoleColors.RESET);
        ExtentTestManager.getTest().log(Status.WARNING, message);

    }

    public void ERROR(String message) {
        logger.info(ConsoleColors.RED + message + ConsoleColors.RESET);
        ExtentTestManager.getTest().log(Status.ERROR, message);

    }

    public void FATAL(String message) {
        logger.info(ConsoleColors.RED_BACKGROUND + message + ConsoleColors.RESET);
        ExtentTestManager.getTest().log(Status.FATAL, message);
    }

    public void TRACE(String message) {
        logger.trace(ConsoleColors.PURPLE + message + ConsoleColors.RESET);
        ExtentTestManager.getTest().log(Status.DEBUG, message);

    }

    public void INFO(String message, Exception exception) {
        logger.info(ConsoleColors.BLUE + message + ConsoleColors.RESET, exception);
        ExtentTestManager.getTest().log(Status.INFO, message);

    }

    public void DEBUG(String message, Exception exception) {
        logger.debug(ConsoleColors.GREEN + message + ConsoleColors.RESET, exception);
        ExtentTestManager.getTest().log(Status.DEBUG, message);

    }

    public void WARN(String message, Exception exception) {
        logger.warn(ConsoleColors.YELLOW + message + ConsoleColors.RESET, exception);
        ExtentTestManager.getTest().log(Status.WARNING, message);

    }

    public void ERROR(String message, Exception exception) {
        logger.error(ConsoleColors.RED + message + ConsoleColors.RESET, exception);
        ExtentTestManager.getTest().log(Status.ERROR, message);

    }

    public void ERROR(String message, String base64ImgString) throws Exception {
        logger.error(ConsoleColors.RED + message + ConsoleColors.RESET);
        Capture();
        ExtentTestManager.getTest().log(Status.ERROR, message);

    }

    public void ERROR(String message, Throwable exception) {
        logger.error(ConsoleColors.RED + message + ConsoleColors.RESET, exception);
        ExtentTestManager.getTest().log(Status.ERROR, exception.getLocalizedMessage());

    }

    public void FATAL(String message, Exception exception) {
        logger.fatal(ConsoleColors.RED_BACKGROUND + message + ConsoleColors.RESET, exception);
        ExtentTestManager.getTest().log(Status.FATAL, exception);

    }

    public void TRACE(String message, Exception exception) {
        logger.trace(ConsoleColors.PURPLE + message + ConsoleColors.RESET, exception);
        ExtentTestManager.getTest().log(Status.DEBUG, message);

    }


    public void PASS(String message, ThreadLocal<ExtentTest> test) {
        test.get().pass("Test passed");
        ExtentTestManager.getTest().log(Status.PASS, message);

    }

    public void FAIL(ExceptionListner exceptionListener, ITestResult result, ThreadLocal<ExtentTest> test) throws IOException {
        test.get().fail(exceptionListener.checkException(result.getThrowable().toString()));
        test.get().fail("details", MediaEntityBuilder.createScreenCaptureFromPath("1.png").build());
        ExtentTestManager.getTest().log(Status.FAIL, result.getMethod().getMethodName());

    }

    public void SKIP(ExceptionListner exceptionListener, ITestResult result, ThreadLocal<ExtentTest> test) {
        test.get().skip(exceptionListener.checkException(result.getThrowable().toString()));
        ExtentTestManager.getTest().log(Status.SKIP, result.getMethod().getMethodName());

    }


    @Override public void onTestStart(ITestResult result) {
        ExtentTestManager.startTest(result.getMethod().getDescription());
        logger.info("****************************************************************");
        logger.info("$$$$$$$$$$$$$$$$$$$$$ " + result.getMethod().getDescription() + "  $$$$$$$$$$$$$$$$$$$$$$$$$");
        logger.info("******************************************************************");
    }

    @Override public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
    }

    @Override public void onTestFailure(ITestResult result) {
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed on method :" + result.getThrowable());

    }

    @Override public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
    }

    @Override public void onStart(ITestContext context) {
        System.out.println("*** Test Suite " + context.getName() + " started ***");
    }

    @SneakyThrows @Override public void onFinish(ITestContext context) {
        System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
        ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();
    }

}

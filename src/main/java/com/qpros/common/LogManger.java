package com.qpros.common;

import com.qpros.quanta.QuantaReports;
import com.qpros.quanta.QuantaTest;
import com.qpros.quanta.MediaEntityBuilder;
import com.qpros.quanta.Status;
import com.qpros.helpers.ActionsHelper;
import com.qpros.reporting.ExceptionListner;
import com.qpros.reporting.QuantaManager;
import com.qpros.reporting.QuantaTestManager;
import com.qpros.utils.ConsoleColors;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.StringUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class LogManger implements ITestListener {

    static Logger logger = Logger.getLogger(LogManger.class.getSimpleName());
    private static QuantaReports extent = new QuantaReports();

    static {
        extent = QuantaManager.createInstance();
    }

    public static ThreadLocal<QuantaTest> test = new ThreadLocal<>();


    public LogManger() {
    }

    public LogManger(String classObjName) {
        logger = Logger.getLogger(classObjName);
    }

    public void INFO(String message) {
        logger.info(ConsoleColors.BLUE + message + ConsoleColors.RESET);
        try {
            QuantaTestManager.getTestNode();
            //QuantaTestManager.getTestNode().getModel().getNodeContext().getLast()..info(message,MediaEntityBuilder.createScreenCaptureFromBase64String(ActionsHelper.takeScreenShot()).build());
            QuantaTestManager.getTest().info(message,MediaEntityBuilder.createScreenCaptureFromBase64String(ActionsHelper.takeScreenShot()).build());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void STEP(String message) {

        if(StringUtils.isNotEmpty(message)) {
            logger.info(ConsoleColors.GREEN + message + ConsoleColors.RESET);
            QuantaTestManager.stepTest(QuantaTestManager.getTest(),message);
            //QuantaTestManager.getTest().log(Status.PASS, message);
        }
    }

    public void Capture() throws Exception {
        logger.info(ConsoleColors.BLUE + ActionsHelper.takeScreenShot() + ConsoleColors.RESET);

        QuantaTestManager.getTestNode().addScreenCaptureFromBase64String(ActionsHelper.takeScreenShot());
    }

    public void INFO(Integer message) throws Exception {
        logger.info(ConsoleColors.BLUE + message + ConsoleColors.RESET);

        QuantaTestManager.getTestNode().log(Status.INFO, String.valueOf(message));

    }

    public void DEBUG(String message) {
//        logger.debug(ConsoleColors.GREEN + message + ConsoleColors.RESET);
//        QuantaTestManager.getTest().log(Status.DEBUG, message);

    }

    public void WARN(String message) {
//        logger.warn(ConsoleColors.YELLOW + message + ConsoleColors.RESET);
//        QuantaTestManager.getTest().log(Status.WARNING, message);

    }

    public void ERROR(String message) {
//        logger.info(ConsoleColors.RED + message + ConsoleColors.RESET);
//        QuantaTestManager.getTest().log(Status.ERROR, message);

    }

    public void FATAL(String message) {
//        logger.info(ConsoleColors.RED_BACKGROUND + message + ConsoleColors.RESET);
//        QuantaTestManager.getTest().log(Status.FATAL, message);
    }

    public void TRACE(String message) {
//        logger.trace(ConsoleColors.PURPLE + message + ConsoleColors.RESET);
//        QuantaTestManager.getTest().log(Status.DEBUG, message);

    }

    public void INFO(String message, Exception exception) {
        logger.info(ConsoleColors.BLUE + message + ConsoleColors.RESET, exception);
        QuantaTestManager.getTest().log(Status.INFO, message);

    }

    public void DEBUG(String message, Exception exception) {
//        logger.debug(ConsoleColors.GREEN + message + ConsoleColors.RESET, exception);
//        QuantaTestManager.getTest().log(Status.DEBUG, message);

    }

    public void WARN(String message, Exception exception) {
//        logger.warn(ConsoleColors.YELLOW + message + ConsoleColors.RESET, exception);
//        QuantaTestManager.getTest().log(Status.WARNING, message);

    }

    public void ERROR(String message, Exception exception) {
//        logger.error(ConsoleColors.RED + message + ConsoleColors.RESET, exception);
//        QuantaTestManager.getTest().log(Status.ERROR, message);

    }

    public void ERROR(String message, String base64ImgString) throws Exception {
//        logger.error(ConsoleColors.RED + message + ConsoleColors.RESET);
//        Capture();
//        QuantaTestManager.getTest().log(Status.ERROR, message);

    }

    public void ERROR(String message, Throwable exception) {
//        logger.error(ConsoleColors.RED + message + ConsoleColors.RESET, exception);
//        QuantaTestManager.getTest().log(Status.ERROR, exception.getLocalizedMessage());

    }

    public void FATAL(String message, Exception exception) {
//        logger.fatal(ConsoleColors.RED_BACKGROUND + message + ConsoleColors.RESET, exception);
//        QuantaTestManager.getTest().log(Status.FATAL, exception);

    }

    public void TRACE(String message, Exception exception) {
//        logger.trace(ConsoleColors.PURPLE + message + ConsoleColors.RESET, exception);
//        QuantaTestManager.getTest().log(Status.DEBUG, message);

    }


    public void PASS(String message, ThreadLocal<QuantaTest> test) {
        test.get().pass("Test passed");
        QuantaTestManager.getTest().log(Status.PASS, message);

    }

    public void FAIL(ExceptionListner exceptionListener, ITestResult result, ThreadLocal<QuantaTest> test) throws IOException {
        test.get().fail(exceptionListener.checkException(result.getThrowable().toString()));
        test.get().fail("details", MediaEntityBuilder.createScreenCaptureFromPath("1.png").build());
        QuantaTestManager.getTest().log(Status.FAIL, result.getMethod().getMethodName());

    }

    public void SKIP(ExceptionListner exceptionListener, ITestResult result, ThreadLocal<QuantaTest> test) {
        test.get().skip(exceptionListener.checkException(result.getThrowable().toString()));
        QuantaTestManager.getTest().log(Status.SKIP, result.getMethod().getMethodName());

    }


    @Override public void onTestStart(ITestResult result) {
        QuantaTestManager.startTest(result.getMethod().getDescription());
        logger.info("****************************************************************");
        logger.info("$$$$$$$$$$$$$$$$$$$$$ " + result.getMethod().getDescription() + "  $$$$$$$$$$$$$$$$$$$$$$$$$");
        logger.info("******************************************************************");
    }

    @Override public void onTestSuccess(ITestResult result) {
        //QuantaTestManager.getTest().log(Status.PASS, "Test passed");
    }

    @Override public void onTestFailure(ITestResult result) {
        //QuantaTestManager.getTest().log(Status.FAIL, "Test Failed on method :" + result.getThrowable());

    }

    @Override public void onTestSkipped(ITestResult result) {
        //QuantaTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
    }

    @Override public void onStart(ITestContext context) {
        System.out.println("*** Test Suite " + context.getName() + " started ***");
    }

    @SneakyThrows @Override public void onFinish(ITestContext context) {
        System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
        QuantaTestManager.endTest();
        QuantaManager.getInstance().flush();
    }

}

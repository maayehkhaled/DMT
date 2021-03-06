package com.qpros.common;

import com.qpros.helpers.ActionsHelper;
import com.qpros.helpers.MobileActionHelper;
import com.qpros.quanta.MediaEntityBuilder;
import com.qpros.quanta.QuantaReports;
import com.qpros.quanta.QuantaTest;
import com.qpros.quanta.Status;
import com.qpros.quanta.markuputils.MarkupHelper;
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
import java.io.UnsupportedEncodingException;

public class LogManager implements ITestListener {

    static Logger logger = Logger.getLogger(LogManager.class.getSimpleName());
    private static QuantaReports extent = new QuantaReports();

    static {
        extent = QuantaManager.createInstance();
    }

    public static ThreadLocal<QuantaTest> test = new ThreadLocal<>();


    public LogManager() {
    }

    public LogManager(String classObjName) {
        logger = Logger.getLogger(classObjName);
    }

    public void INFO(String message, Boolean isMobile) {
        logger.info(ConsoleColors.BLUE + message + ConsoleColors.RESET);
        try {
            if (isMobile) {
                QuantaTestManager.getTestNode().log(Status.PASS, message, MediaEntityBuilder.createScreenCaptureFromBase64String(MobileActionHelper.takeScreenShot()).build());
            } else {
                QuantaTestManager.getTestNode().log(Status.PASS, message, MediaEntityBuilder.createScreenCaptureFromBase64String(ActionsHelper.takeScreenShot()).build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void STEP(String message, String description) {

        if (StringUtils.isNotEmpty(message)) {
            logger.info(ConsoleColors.GREEN + message + ConsoleColors.RESET);
            QuantaTestManager.stepTest(QuantaTestManager.getTest(), message, description);
            QuantaTestManager.getTest().info(message);
        }
    }

    public void Capture() {
        try {
            logger.info(ConsoleColors.BLUE + ActionsHelper.takeScreenShot() + ConsoleColors.RESET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            QuantaTestManager.getTestNode().addScreenCaptureFromBase64String(ActionsHelper.takeScreenShot());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void INFO(Integer message) {
        logger.info(ConsoleColors.BLUE + message + ConsoleColors.RESET);

        QuantaTestManager.getTestNode().log(Status.INFO, String.valueOf(message));

    }

    public void CodeBLOCK(String message){
        logger.info(ConsoleColors.BLUE + message + ConsoleColors.RESET);
        QuantaTestManager.getTestNode().log(Status.INFO, MarkupHelper.createCodeBlock(message));
    }

    public void DEBUG(String message) {
        logger.debug(ConsoleColors.GREEN + message + ConsoleColors.RESET);
        QuantaTestManager.getTestNode().log(Status.DEBUG, message);

    }

    public void WARN(String message) {
        logger.warn(ConsoleColors.YELLOW + message + ConsoleColors.RESET);
        QuantaTestManager.getTestNode().log(Status.WARNING, message);

    }

    public void ERROR(String message, boolean isMobile) {

    }

    public void FATAL(String message) {
    }

    public void TRACE(String message) {

    }

    public void INFO(String message, Exception exception) {
        logger.info(ConsoleColors.BLUE + message + ConsoleColors.RESET, exception);
        QuantaTestManager.getTestNode().log(Status.INFO, message);

    }

    public void DEBUG(String message, Exception exception) {

    }

    public void WARN(String message, Exception exception) {

    }

    public void ERROR(String message, Exception exception) {

    }

    public void ERROR(String message, String base64ImgString) {

    }

    public void ERROR(String message, Throwable exception) {

    }

    public void FATAL(String message, Exception exception) {

    }

    public void TRACE(String message, Exception exception) {

    }


    public void PASS(String message, ThreadLocal<QuantaTest> test) {
        test.get().pass("Test passed");
        QuantaTestManager.getTest().log(Status.PASS, message);

    }

    public void FAIL(ExceptionListner exceptionListener, ITestResult result, ThreadLocal<QuantaTest> test) throws IOException {
        test.get().fail(exceptionListener.checkException(result.getThrowable().toString()));
        QuantaTestManager.getTest().log(Status.FAIL, result.getMethod().getMethodName());

    }

    public void SKIP(ExceptionListner exceptionListener, ITestResult result, ThreadLocal<QuantaTest> test) {
        test.get().skip(exceptionListener.checkException(result.getThrowable().toString()));
        QuantaTestManager.getTest().log(Status.SKIP, result.getMethod().getMethodName());

    }

    private String getTestIdentifier(ITestResult result) {
        return String.format("%s.%s",
                        result.getInstanceName(),
                        result.getMethod().getMethodName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        QuantaTestManager.startTest(result.getMethod().getDescription());
        StringBuilder builder = new StringBuilder();
        builder.append("**********************");
        for (int i = 0; i < getTestIdentifier(result).length(); i++) {
            builder.append("*");
        }
        builder.append("**********************");
        logger.info(builder.toString());
        logger.info("*$$$$$$$$$$$$$$$$$$$$ " + getTestIdentifier(result) + " $$$$$$$$$$$$$$$$$$$$*");
        logger.info(builder.toString());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        //QuantaTestManager.getTest().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        //QuantaTestManager.getTest().log(Status.FAIL, "Test Failed on method :" + result.getThrowable());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        //QuantaTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("*** Test Suite " + context.getName() + " started ***");
    }

    @SneakyThrows
    @Override
    public void onFinish(ITestContext context) {
        System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
        QuantaTestManager.endTest();
        QuantaManager.getInstance().flush();
    }

    public static String formatMessage(String message, Object... messageArgs) {

        try {
            return String.format(message, messageArgs);
        } catch (Exception e) {
            int argumentNotationCount = StringUtils.countMatches(message, "%s");
            for (int i = 0; i < (argumentNotationCount - messageArgs.length); i++) {
                message = StringUtils.replaceOnce(message, "%s", "").trim();
            }
        }
        return String.format(message, messageArgs);
    }
}

package com.qpros.reporting;

import com.qpros.quanta.QuantaReports;
import com.qpros.quanta.QuantaTest;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class Listeners extends TestListenerAdapter{

    private static QuantaReports extent = QuantaManager.createInstance();
    public static ThreadLocal<QuantaTest> test = new ThreadLocal<>();
    ExceptionListner exceptionListener = new ExceptionListner();
    public static boolean CONSOLE;

//    @Override
//    public synchronized void onStart(ITestContext context) {
//        if (CONSOLE) {
//
//            System.out.println("Test Suite started!");
//        }
//
//    }
//
//    @Override
//    public synchronized void onFinish(ITestContext context) {
//        if (CONSOLE) {
//            System.out.println(("Test Suite is ending!"));
//
//        }
//        extent.flush();
//    }
//
//    @Override
//    public synchronized void onTestStart(ITestResult result) {
//        if (CONSOLE) {
//            System.out.println((result.getMethod().getMethodName() + " started!"));
//
//        }
//        QuantaTest QuantaTest = extent.createTest(result.getMethod().getDescription(),
//                result.getMethod().getMethodName());
//        test.set(QuantaTest);
//    }
//
//    @Override
//    public synchronized void onTestSuccess(ITestResult result) {
//        if (CONSOLE) {
//            System.out.println((result.getMethod().getMethodName() + " passed!"));
//        }
//
//        test.get().pass("Test passed");
//    }
//
//    @Override
//    public synchronized void onTestFailure(ITestResult result) {
//        if (CONSOLE) {
//            System.out.println((result.getMethod().getMethodName() + " failed!"));
//        }
//
//        test.get().fail(exceptionListener.checkException(result.getThrowable().toString()));
//
//    }
//
//    @Override
//    public synchronized void onTestSkipped(ITestResult result) {
//        if (CONSOLE) {
//            System.out.println((result.getMethod().getMethodName() + " skipped!"));
//        }
//        test.get().skip(exceptionListener.checkException(result.getThrowable().toString()));
//    }
//
//    @Override
//    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//        if (CONSOLE) {
//            System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
//
//        }
//
//    }
}

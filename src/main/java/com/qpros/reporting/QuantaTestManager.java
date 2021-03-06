package com.qpros.reporting;

import com.qpros.quanta.QuantaReports;
import com.qpros.quanta.QuantaTest;

import java.lang.management.OperatingSystemMXBean;
import java.util.HashMap;
import java.util.Map;

public class QuantaTestManager {


    static Map<Integer, QuantaTest> quantaSuiteTestMap = new HashMap<Integer, QuantaTest>();
    static Map<Integer, QuantaTest> quantaTestMap = new HashMap<Integer, QuantaTest>();
    static Map<Integer, QuantaTest> quantaTestNodeMap = new HashMap<>();
    static QuantaReports quantaReports;
    private static ThreadLocal<QuantaTest> quantaTestThreadLocal= new ThreadLocal<>();
    private static ThreadLocal<QuantaTest> quantaNodeThreadLocal= new ThreadLocal<>();
    private static ThreadLocal<QuantaTest>  suite= new ThreadLocal<>();

    static {
        quantaReports = QuantaManager.getInstance();
    }

    public static synchronized QuantaTest getTestSuite() {
        return quantaSuiteTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized QuantaTest getTest() {
        return quantaTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized QuantaTest getTestNode() {
        return quantaTestNodeMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized void endTest() {
        quantaReports.flush();
    }

    public static synchronized QuantaTest startTestSuite(String testSuiteName) {
        suite.set(quantaReports.createTest(testSuiteName));
//        suite.get().assignAuthor("Run User");
//        suite.get().assignAuthor(System.getProperty("user.name"));
        suite.get().assignDevice("Operating System");
        suite.get().assignDevice(System.getProperty("os.name"));
        quantaSuiteTestMap.put((int) Thread.currentThread().getId(), suite.get());
        return suite.get();
    }

    public static synchronized QuantaTest startTest(String testName) {
        quantaTestThreadLocal.set(suite.get().createNode(testName));
//        quantaTestThreadLocal.get().assignAuthor("Run User");
//        quantaTestThreadLocal.get().assignAuthor(System.getProperty("user.name"));
        quantaTestThreadLocal.get().assignDevice("Operating System");
        quantaTestThreadLocal.get().assignDevice(System.getProperty("os.name"));
        quantaTestMap.put((int) Thread.currentThread().getId(), quantaTestThreadLocal.get());
        return quantaTestThreadLocal.get();
    }

    public static synchronized QuantaTest stepTest(QuantaTest testName, String nodeName, String description) {
        quantaNodeThreadLocal.set(testName.createNode(nodeName, description));
        quantaTestNodeMap.put((int) Thread.currentThread().getId(), quantaNodeThreadLocal.get());
        return quantaNodeThreadLocal.get();
    }

}

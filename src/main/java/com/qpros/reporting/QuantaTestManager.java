package com.qpros.reporting;

import com.qpros.quanta.QuantaReports;
import com.qpros.quanta.QuantaTest;

import java.lang.management.OperatingSystemMXBean;
import java.util.HashMap;
import java.util.Map;

public class QuantaTestManager {

    static Map<Integer, QuantaTest> QuantaTestMap = new HashMap<Integer, QuantaTest>();
    static Map<Integer, QuantaTest> QuantaTestNodeMap = new HashMap<>();
    static QuantaReports quantaReports;

    static {
        quantaReports = QuantaManager.getInstance();
    }

    public static synchronized QuantaTest getTest() {
        return QuantaTestMap.get((int) Thread.currentThread().getId());
    }
    public static synchronized QuantaTest getTestNode() {
        return QuantaTestNodeMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized void endTest() {
        quantaReports.flush();
    }

    public static synchronized QuantaTest startTest(String testName) {
        QuantaTest test = quantaReports.createTest(testName);
        test.assignAuthor("Run User");
        test.assignAuthor(System.getProperty("user.name"));
        test.assignDevice("Operating System");
        test.assignDevice(System.getProperty("os.name"));
        QuantaTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }

    public static synchronized QuantaTest stepTest(QuantaTest testName,String nodeName,String description) {
        QuantaTest stepTest=testName.createNode(nodeName,description);
        QuantaTestNodeMap.put((int)Thread.currentThread().getId(),stepTest);
        return stepTest;
    }

}

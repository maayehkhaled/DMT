package com.qpros.reporting;

import com.qpros.quanta.QuantaReports;
import com.qpros.quanta.QuantaTest;

import java.util.HashMap;
import java.util.Map;

public class QuantaTestManager {

    static Map<Integer, QuantaTest> QuantaTestMap = new HashMap<Integer, QuantaTest>();
    static Map<QuantaTest,QuantaTest > QuantaTestNodeMap = new HashMap<QuantaTest, QuantaTest>();
    static QuantaReports extent;

    static {
        extent = QuantaManager.getInstance();
    }

    public static synchronized QuantaTest getTest() {
        return QuantaTestMap.get((int) Thread.currentThread().getId());
    }
    public static synchronized QuantaTest getTestNode() {
        return QuantaTestNodeMap.get(QuantaTestManager.getTest().getModel().getNodeContext().get(QuantaTestManager.getTest().getModel().getNodeContext().size()-1));
    }

    public static synchronized void endTest() {
        extent.flush();
    }

    public static synchronized QuantaTest startTest(String testName) {
        QuantaTest test = extent.createTest(testName);
        QuantaTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }

    public static synchronized QuantaTest stepTest(QuantaTest testName,String nodeName) {
        testName.createNode(nodeName);
//        testName.pass(testName.getModel().getHierarchicalName());
        return testName;
    }

}

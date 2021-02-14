package com.qpros.reporting;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentEmailReporter;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qpros.common.Base;
import com.qpros.helpers.ActionsHelper;

public class ExtentManager extends Base {

    private static ExtentReports extent;
    private static String reportFileName = "QPros-Automation_Report-"+ ActionsHelper.getTodayDate() + "-" +
            System.currentTimeMillis() + ".html";
    private static String path = System.getProperty("user.dir") + "/src/main/resources/Reports/";

    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    public static ExtentReports createInstance() {
        StateHelper.setStepState("reportName", reportFileName);
        ExtentHtmlReporter htmlReporter= new ExtentHtmlReporter(path + reportFileName);
        htmlReporter.config().setReportName("QPros-Test");
        htmlReporter.setAnalysisStrategy(AnalysisStrategy.SUITE);
        htmlReporter.config().setTheme( Theme.STANDARD);
        htmlReporter.config().setEncoding("utf-8");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        return extent;
    }

}

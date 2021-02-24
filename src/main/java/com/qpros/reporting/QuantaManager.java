package com.qpros.reporting;

import com.qpros.quanta.AnalysisStrategy;
import com.qpros.quanta.QuantaReports;
import com.qpros.quanta.reporter.*;
import com.qpros.quanta.reporter.configuration.Theme;
import com.qpros.common.Base;
import com.qpros.helpers.ActionsHelper;

public class QuantaManager extends Base {

    private static QuantaReports extent;
    private static String reportFileName = "QPros-Automation_Report-"+ ActionsHelper.getTodayDate() + "-" +
            System.currentTimeMillis() + ".html";
    private static String path = System.getProperty("user.dir") + "/src/main/resources/Reports/";

    public static QuantaReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    public static QuantaReports createInstance() {
        StateHelper.setStepState("reportName", reportFileName);
        QuantaHtmlReporter htmlReporter= new QuantaHtmlReporter(path + reportFileName);
        htmlReporter.config().setReportName("QPros-Test");
        htmlReporter.setAnalysisStrategy(AnalysisStrategy.TEST);
        htmlReporter.config().setTheme( Theme.STANDARD);
        htmlReporter.config().setEncoding("utf-8");
        extent = new QuantaReports();
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
        extent.attachReporter(htmlReporter);

        return extent;
    }

}

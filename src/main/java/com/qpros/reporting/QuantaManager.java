package com.qpros.reporting;

import com.qpros.quanta.AnalysisStrategy;
import com.qpros.quanta.QuantaReports;
import com.qpros.quanta.reporter.*;
import com.qpros.quanta.reporter.configuration.Theme;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;

public class QuantaManager extends Base {

    private static QuantaReports extent;
    private static final String reportFileName = "QPros-Automation_Report-"+ ActionsHelper.getTodayDate() + "-" +
            System.currentTimeMillis() + ".html";
    private static final String path = System.getProperty("user.dir") + "/src/main/resources/Reports/";

    public static QuantaReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    public static QuantaReports createInstance() {
        StateHelper.setStepState("reportName", reportFileName);
        QuantaHtmlReporter htmlReporter= new QuantaHtmlReporter(path + reportFileName);
        htmlReporter.config().setReportName("QPros-Test");
        htmlReporter.setAnalysisStrategy(AnalysisStrategy.SUITE);
        htmlReporter.config().setTheme( Theme.DARK);
        htmlReporter.config().setEncoding("utf-8");
        extent = new QuantaReports();
        extent.setSystemInfo("Operating System",System.getProperty("os.name"));
        extent.setSystemInfo("Operating System Version",System.getProperty("os.version"));
        extent.setSystemInfo("Operating system architecture",System.getProperty("os.arch"));
        extent.setSystemInfo("Run User",System.getProperty("user.name"));
        extent.setSystemInfo("Java runtime",System.getProperty("java.runtime.version"));
        extent.setAnalysisStrategy(AnalysisStrategy.SUITE);
        extent.attachReporter(htmlReporter);

        return extent;
    }

}

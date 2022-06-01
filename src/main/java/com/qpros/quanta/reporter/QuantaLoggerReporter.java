package com.qpros.quanta.reporter;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.qpros.quanta.ReportAggregates;
import com.qpros.quanta.model.Category;
import com.qpros.quanta.model.SubCategory;
import com.qpros.quanta.model.Test;
import com.qpros.quanta.reporter.configuration.QuantaLoggerFormatterConfiguration;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * The QuantaHtmlReporter creates a rich standalone HTML file. It allows several
 * configuration options via the <code>config()</code> method.
 */
public class QuantaLoggerReporter extends BasicFileReporter {

    private static final Logger logger = Logger.getLogger(QuantaLoggerReporter.class.getName());
    private static final String REPORTER_NAME = "logger";
    private static final String TEMPLATE_NAME = "logger/logger-test.ftl";
    private static final String DASHBOARD_TEMPLATE_NAME = "logger/logger-dashboard.ftl";
    private static final String CATEGORY_TEMPLATE_NAME = "logger/logger-tag.ftl";
    private static final String EXCEPTION_TEMPLATE_NAME = "logger/logger-exception.ftl";
    private static final String[] DEFAULT_CONFIG_FILE_PATH = new String[] { "logger.properties",
            "src/main/resources/logger.properties" };

    private QuantaLoggerFormatterConfiguration userConfig = new QuantaLoggerFormatterConfiguration(this);

    public QuantaLoggerReporter(String path) {
        super(path);
        init(DEFAULT_CONFIG_FILE_PATH, config());
    }

    public QuantaLoggerReporter(File file) {
        super(file);
        init(DEFAULT_CONFIG_FILE_PATH, config());
    }

    public QuantaLoggerFormatterConfiguration config() {
        return userConfig;
    }

    @Override
    public synchronized void flush(ReportAggregates reportAggregates) {
        super.flush(reportAggregates);

        if (getTestList().isEmpty())
            return;

        if (enforceOfflineMode())
            userConfig.enableOfflineMode(true);

        loadUserConfig();

        try {
            Template template = getFreemarkerConfig().getTemplate(TEMPLATE_NAME);
            processTemplate(template, new File(destination + "index.html"));
            if (String.valueOf(configContext.getValue("enableDashboard")).equalsIgnoreCase("true")) {
                template = getFreemarkerConfig().getTemplate(DASHBOARD_TEMPLATE_NAME);
                processTemplate(template, new File(destination + "dashboard.html"));
            }
            if (!getCategoryContextInfo().getTestAttributeTestContextList().isEmpty()) {
                template = getFreemarkerConfig().getTemplate(CATEGORY_TEMPLATE_NAME);
                processTemplate(template, new File(destination + "tag.html"));
            }
            if (!getExceptionContextInfo().getExceptionTestContextList().isEmpty()) {
                template = getFreemarkerConfig().getTemplate(EXCEPTION_TEMPLATE_NAME);
                processTemplate(template, new File(destination + "exception.html"));
            }
        } catch (IOException | TemplateException e) {
            logger.log(Level.SEVERE, "An exception occurred", e);
        }
    }

    @Override
    public String getReporterName() {
        return REPORTER_NAME;
    }


    @Override public void onSubCategoryAssigned(Test test, SubCategory subCategory) {

    }
}

package com.qpros.quanta.reporter;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.qpros.quanta.ReportAggregates;
import com.qpros.quanta.model.Category;
import com.qpros.quanta.model.SubCategory;
import com.qpros.quanta.model.Test;
import com.qpros.quanta.reporter.configuration.QuantaHtmlReporterConfiguration;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * The QuantaHtmlReporter creates a rich standalone HTML file. It allows several
 * configuration options via the <code>config()</code> method.
 */
public class QuantaHtmlReporter extends BasicFileReporter {

    private static final Logger logger = Logger.getLogger(QuantaHtmlReporter.class.getName());
    private static final String REPORTER_NAME = "html";
    private static final String TEMPLATE_NAME = "v3html/v3-html-index.ftl";
    private static final String[] DEFAULT_CONFIG_FILE_PATH = new String[] { "html.properties",
            "src/main/resources/html.properties" };

    private QuantaHtmlReporterConfiguration userConfig = new QuantaHtmlReporterConfiguration(this);

    public QuantaHtmlReporter(String path) {
        super(path);
        init(DEFAULT_CONFIG_FILE_PATH, config());
    }

    public QuantaHtmlReporter(File file) {
        super(file);
        init(DEFAULT_CONFIG_FILE_PATH, config());
    }

    public QuantaHtmlReporterConfiguration config() {
        return userConfig;
    }

    @Override
    public synchronized void flush(ReportAggregates reportAggregates) {
        super.flush(reportAggregates);

        if (getTestList().isEmpty())
            return;

        loadUserConfig();

        try {
            Template template = getFreemarkerConfig().getTemplate(TEMPLATE_NAME);
            processTemplate(template, new File(filePath));
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

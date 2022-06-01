package com.qpros.quanta;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import com.qpros.quanta.gherkin.GherkinDialectProvider;
import com.qpros.quanta.gherkin.model.IGherkinFormatterModel;
import com.qpros.quanta.model.SystemAttribute;

/**
 * <p>
 * The quanta report client for starting reporters and building reports. For most applications,
 * you should have one quanta instance for the entire JVM. 
 * </p>
 * 
 * <p>
 * quanta itself does not build any reports, but allows reporters to access information, which in
 * turn build the said reports. An example of building an HTML report and adding information to QuantaX:
 * </p>
 * 
 * <pre>
 * QuantaHtmlReporter html = new QuantaHtmlReporter("Quanta.html");
 * QuantaXReporter Quantax = new QuantaXReporter("localhost");
 * 
 * quanta Quanta = new quanta();
 * Quanta.attachReporter(html, Quantax);
 * 
 * Quanta.createTest("TestName").pass("Test Passed");
 * 
 * Quanta.flush();
 * </pre>
 * 
 * <p>
 * A few notes: 
 * </p>
 * 
 * <ul>
 *  <li>It is mandatory to call the <code>flush</code> method to ensure information is written to the started
 * reporters.</li>
 * 	<li>You can create standard and BDD-style tests using the <code>createTest</code> method</li>
 * </ul>
 * 
 * @see QuantaTest
 * @see GherkinKeyword
 * @see IGherkinFormatterModel
 * @see Status
 */
public class QuantaReports
	extends QuantaObservable {
     
    /**
     * Attach a {@link QuantaReporter} reporter, allowing it to access all started tests, nodes and logs 
     * 
     * <p>
     * Available reporter types are:
     * </p>
     * 
     * <ul>
     *  <li>QuantaHtmlReporter provided by artifactId "Quanta-html-formatter"</li>
     *  <li>QuantaEmailReporter (pro-only) provided by artifactId "Quanta-email-formatter"</li>
     *  <li>KlovReporter provided by artifactId "Quanta-klov-reporter"</li>
     *  <li>ConsoleLogger</li>
     * </ul>
     * 
     * @param reporter {@link QuantaReporter} reporter
     */
    public void attachReporter(QuantaReporter... reporter) {
        Arrays.stream(reporter).forEach(this::register);
    }
    
    /**
     * Returns a list of started reporters
     * 
     * @return A list of {@link QuantaReporter}
     */
    public List<QuantaReporter> getStartedReporters() {
    	return getReporterCollection();
    }

    /**
     * Creates a BDD-style test with description representing one of the {@link IGherkinFormatterModel}
     * classes such as:
     * 
     * <ul>
     *  <li>{@link com.qpros.quanta.gherkin.model.Feature}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Background}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Scenario}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Given}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.When}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Then}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.And}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.But}</li>
     * </ul>
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * Quanta.createTest(Feature.class, "feature", "description");
     * Quanta.createTest(Scenario.class, "scenario", "description");
     * Quanta.createTest(Given.class, "given", "description");
     * </pre>
     * 
     * @param type A {@link IGherkinFormatterModel} type
     * @param testName Name of test
     * @param description A short description of the test
     * 
     * @return {@link QuantaTest} object
     */
    public synchronized QuantaTest createTest(Class<? extends IGherkinFormatterModel> type, String testName, String description) {
        QuantaTest t = new QuantaTest(this, type, testName, description);
        applyCommonTestSettings(t);
        
        saveTest(t.getModel());
        
        return t;
    }
    
    /**
     * Creates a BDD-style test representing one of the {@link IGherkinFormatterModel} classes such as:
     * 
     * <ul>
     *  <li>{@link com.qpros.quanta.gherkin.model.Feature}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Background}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Scenario}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Given}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.When}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Then}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.And}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.But}</li>
     * </ul>
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * Quanta.createTest(Feature.class, "feature");
     * Quanta.createTest(Scenario.class, "scenario");
     * Quanta.createTest(Given.class, "given");
     * </pre>
     * 
     * @param type A {@link IGherkinFormatterModel} type
     * @param testName Name of test
     * 
     * @return {@link QuantaTest} object
     */
    public synchronized QuantaTest createTest(Class<? extends IGherkinFormatterModel> type, String testName) {
        return createTest(type, testName, null);
    }
    
    /**
     * Creates a BDD-style test with description using name of the Gherkin model such as:
     * 
     * <ul>
     *  <li>{@link com.qpros.quanta.gherkin.model.Feature}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Background}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Scenario}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Given}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.When}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Then}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.And}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.But}</li>
     * </ul>
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * Quanta.createTest(new GherkinKeyword("Feature"), "feature", "description");
     * Quanta.createTest(new GherkinKeyword("Scenario"), "scenario", "description");
     * Quanta.createTest(new GherkinKeyword("Given"), "given", "description");
     * </pre>
     * 
     * @param gherkinKeyword Name of the {@link GherkinKeyword} 
     * @param testName Name of test
     * @param description A short description of the test
     * 
     * @return {@link QuantaTest} object
     */
    public synchronized QuantaTest createTest(GherkinKeyword gherkinKeyword, String testName, String description) {
        Class<? extends IGherkinFormatterModel> clazz = gherkinKeyword.getKeyword().getClass();
        return createTest(clazz, testName, description);
    }
    
    /**
     * Creates a BDD-style test using name of the Gherkin model such as:
     * 
     * <ul>
     *  <li>{@link com.qpros.quanta.gherkin.model.Feature}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Background}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Scenario}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Given}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.When}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Then}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.And}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.But}</li>
     * </ul>
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * Quanta.createTest(new GherkinKeyword("Feature"), "feature");
     * Quanta.createTest(new GherkinKeyword("Scenario"), "scenario");
     * Quanta.createTest(new GherkinKeyword("Given"), "given");
     * </pre>
     * 
     * @param gherkinKeyword Name of the {@link GherkinKeyword}
     * @param testName Name of test
     * 
     * @return {@link QuantaTest} object
     */
    public synchronized QuantaTest createTest(GherkinKeyword gherkinKeyword, String testName) {
        return createTest(gherkinKeyword, testName, null);
    }
    
    /**
     * Creates a test with description
     * 
     * @param testName Name of test
     * @param description A short test description
     * 
     * @return {@link QuantaTest} object
     */
    public synchronized QuantaTest createTest(String testName, String description) {
        QuantaTest t = new QuantaTest(this, testName, description);
        applyCommonTestSettings(t);
        
        saveTest(t.getModel());
        
        return t;
    }

    /**
     * Creates a test
     * 
     * @param testName Name of test
     * 
     * @return {@link QuantaTest} object
     */
    public synchronized QuantaTest createTest(String testName) {
        return createTest(testName, null);
    }
    
    private synchronized void applyCommonTestSettings(QuantaTest QuantaTest) {
        QuantaTest.setUseManualConfiguration(getAllowManualConfig());
    }
      
    /**
     * Removes a test
     * 
     * @param test {@link QuantaTest} object
     */
    public synchronized void removeTest(QuantaTest test) {
        super.removeTest(test.getModel());
    }    
    
    /**
     * Writes test information from the started reporters to their output view
     * 
     * <ul>
     *  <li>Quanta-html-formatter: flush output to HTML file</li>
     *  <li>Quanta-klov-reporter: updates MongoDB collections</li>
     *  <li>Quanta-email-formatter (pro-only): creates or appends to an HTML file</li>
     *  <li>ConsoleLogger: no action taken</li>
     * </ul>
     */
    @Override
    public synchronized void flush() {
        super.flush();
    }

    /**
     * Adds any applicable system information to all started reporters
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * Quanta.setSystemInfo("HostName", "qpros-PC");
     * </pre>
     * 
     * @param k Name of system variable
     * @param v Value of system variable
     */
    public void setSystemInfo(String k, String v) {
        SystemAttribute sa = new SystemAttribute(k, v);       
        super.setSystemInfo(sa);
    }
    
    /**
     * Adds logs from test framework tools to the test-runner logs view (if available in the reporter)
     * 
     * <p>
     * TestNG usage example:
     * </p>
     * 
     * <pre>
     * Quanta.setTestRunnerOutput(Reporter.getOutput());
     * </pre>
     *
     * @param log Log string from the test runner frameworks such as TestNG or JUnit
     */
    public void setTestRunnerOutput(List<String> log) {
        log.forEach(this::setTestRunnerLogs);
    }
    
    /**
     * Adds logs from test framework tools to the test-runner logs view (if available in the reporter)
     * 
     * <p>
     * TestNG usage example:
     * </p>
     * 
     * <pre>
     * for (String s : Reporter.getOutput()) {
     *   Quanta.setTestRunnerOutput(s);
     * }
     * </pre>
     *
     * @param log Log string from the test runner frameworks such as TestNG or JUnit
     */
    public void setTestRunnerOutput(String log) {
        setTestRunnerLogs(log);
    }
    
    /**
     * Use this setting when building post-execution reports, such as from TestNG IReporter.
     * This setting allows setting test with your own time-stamps. With this enabled, Quanta 
     * does not use time-stamps for tests at the time they were created.
     * 
     * <p>
     * If this setting is enabled and time-stamps are not specified explicitly, the time-stamps
     * of test creation are used.
     * 
     * @param useManualConfig Set to true if building reports at the end of execution with manual configuration
     */
    public void setReportUsesManualConfiguration(boolean useManualConfig) {
        setAllowManualConfig(useManualConfig);
    }

	/**
     * Type of AnalysisStrategy for the reporter. Not all reporters support this setting.
     * 
     * <p>
     * There are 2 types of strategies available:
     * 
     * <ul>
     *  <li>TEST: Shows analysis at the test and step level</li>
     *  <li>SUITE: Shows analysis at the suite, test and step level</li>
     * </ul>
     * 
     * @param strategy {@link AnalysisStrategy} determines the type of analysis (dashboard)
     * created for the reporter. Not all reporters will support this setting.
     */
    @Override   
    public void setAnalysisStrategy(AnalysisStrategy strategy) {
        super.setAnalysisStrategy(strategy);
    }
    
    /**
     * Provides common report configurations
     * 
     * @return an instance of {@link ReportConfigurator}
     */
    public ReportConfigurator config() {
        return ReportConfigurator.getInstance();
    }
    
    /**
     * Allows setting the target language for Gherkin keywords.
     * 
     * <p>
     * Default setting is "en"
     * 
     * @param language A valid dialect from 
     * <a href="https://github.com/cucumber/cucumber/blob/master/gherkin/gherkin-languages.json">gherkin-languages.json</a>
     * 
     * @throws UnsupportedEncodingException Thrown if the language is one of the supported language from
     * <a href="https://github.com/cucumber/cucumber/blob/master/gherkin/gherkin-languages.json">gherkin-languages.json</a> 
     */
    public void setGherkinDialect(String language) throws UnsupportedEncodingException {
        GherkinDialectProvider.setLanguage(language);
    }
    
    /**
     * Returns an instance of {@link ReportStatusStats} with counts of tests executed
     * by their status (pass, fail, skip etc)
     * 
     * @return an instance of {@link ReportStatusStats}
     */
    @Override
    public ReportStatusStats getStats() {
    	return super.getStats();
    }
    
}

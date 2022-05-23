package com.qpros.quanta;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

import com.qpros.quanta.gherkin.model.IGherkinFormatterModel;
import com.qpros.quanta.markuputils.Markup;
import com.qpros.quanta.model.*;
import com.qpros.quanta.utils.StringUtil;

/**
 * Defines a test. You can add logs, snapshots, assign author and categories to a test and its children.
 *
 * <p>
 * The below log types will all be logged with <code>Status.PASS</code>:
 * </p>
 *
 * <pre>
 * test.log(Status.PASS, "details");
 * test.pass("details");
 * test.pass(MarkupHelper.createCodeBlock(code));
 * </pre>
 *
 * <p>
 * A few notes:
 * </p>
 *
 * <ul>
 * 	<li>Tests started with the <code>createTest</code> method are parent-level, always level 0</li>
 * 	<li>Tests started with the <code>createNode</code> method are children, always level 1 and greater</li>
 * </ul>
 */
public class QuantaTest implements IAddsMedia<QuantaTest>, RunResult, Serializable {

    private static final long serialVersionUID = 9199820968410788862L;

    /**
     * An instance of {@link QuantaReports} to which this {@link QuantaTest} belongs
     */
    private transient QuantaReports Quanta;

    /**
     * Internal model
     */
    private Test test;

    /**
     * Creates a BDD style parent test representing one of the {@link IGherkinFormatterModel}
     * classes. This method would ideally be used for creating the parent, ie {@link com.qpros.quanta.gherkin.model.Feature).
     *
     * <ul>
     *  <li>{@link com.qpros.quanta.gherkin.model.Feature}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Background}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Scenario}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Given}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.When}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Then}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.And}</li>
     * </ul>
     *
     * <p>
     * Example:
     * </p>
     *
     * <pre>
     * Quanta.createTest(Feature.class, "Feature Name", "Description");
     * </pre>
     *
     * @param Quanta      An {@link QuantaReports} object
     * @param type        A {@link IGherkinFormatterModel} type
     * @param testName    Test name
     * @param description Test description
     */
    QuantaTest(QuantaReports Quanta, Class<? extends IGherkinFormatterModel> type, String testName, String description) {
        if (testName == null || testName.isEmpty())
            throw new IllegalArgumentException("testName cannot be null or empty");

        this.Quanta = Quanta;

        test = new Test();
        test.setName(testName.trim());
        test.setDescription(description == null ? "" : description.trim());

        if (type != null) {
            test.setBehaviorDrivenType(type);
        }
    }

    /**
     * Create a test with description
     *
     * @param Quanta      An {@link QuantaReports} object
     * @param testName    Test name
     * @param description Test description
     */
    QuantaTest(QuantaReports Quanta, String testName, String description) {
        this(Quanta, null, testName, description);
    }

    /**
     * Creates a BDD-style node with description representing one of the {@link IGherkinFormatterModel}
     * classes:
     *
     * <ul>
     *  <li>{@link com.qpros.quanta.gherkin.model.Feature}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Background}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Scenario}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Given}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.When}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Then}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.And}</li>
     * </ul>
     *
     * <p>
     * Example:
     * </p>
     *
     * <pre>
     * test.createNode(Scenario.class, "bddNode", "description");
     * </pre>
     *
     * @param type        A {@link IGherkinFormatterModel} type
     * @param name        Name of node
     * @param description A short description
     * @return {@link QuantaTest} object
     */
    public synchronized QuantaTest createNode(Class<? extends IGherkinFormatterModel> type, String name, String description) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("nodeName cannot be null or empty");

        QuantaTest t;
        if (type == null) {
            t = new QuantaTest(Quanta, name, description);
        } else {
            t = new QuantaTest(Quanta, type, name, description);
        }

        applyCommonNodeSettings(t);
        addNodeToReport(t);
        return t;
    }

    /**
     * Creates a node with description
     *
     * @param name        Name of node
     * @param description A short description
     * @return {@link QuantaTest} object
     */
    public synchronized QuantaTest createNode(String name, String description) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("nodeName cannot be null or empty");

        QuantaTest t = new QuantaTest(Quanta, name, description);
        applyCommonNodeSettings(t);
        addNodeToReport(t);
        return t;
    }

    /**
     * Creates a BDD-style node representing one of the {@link IGherkinFormatterModel} classes such as:
     *
     * <ul>
     *  <li>{@link com.qpros.quanta.gherkin.model.Feature}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Background}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Scenario}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Given}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.When}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Then}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.And}</li>
     * </ul>
     *
     * <p>
     * Example:
     * </p>
     *
     * <pre>
     * test.createNode(Scenario.class, "bddNode");
     * </pre>
     *
     * @param type A {@link IGherkinFormatterModel} type
     * @param name Name of node
     * @return {@link QuantaTest} object
     */
    public synchronized QuantaTest createNode(Class<? extends IGherkinFormatterModel> type, String name) {
        return createNode(type, name, null);
    }

    /**
     * Creates a BDD-style node with description using name of the Gherkin model such as:
     *
     * <ul>
     *  <li>{@link com.qpros.quanta.gherkin.model.Feature}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Background}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Scenario}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Given}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.When}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Then}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.And}</li>
     * </ul>
     *
     * <p>
     * Example:
     * </p>
     *
     * <pre>
     * test.createNode(new GherkinKeyword("Scenario"), "bddTest", "description");
     * </pre>
     *
     * @param gherkinKeyword Name of the {@link GherkinKeyword}
     * @param name           Name of node
     * @param description    A short description
     * @return {@link QuantaTest}
     */
    public synchronized QuantaTest createNode(GherkinKeyword gherkinKeyword, String name, String description) {
        Class<? extends IGherkinFormatterModel> clazz = gherkinKeyword.getKeyword().getClass();
        return createNode(clazz, name, description);
    }

    /**
     * Creates a BDD-style node using name of the Gherkin model such as:
     *
     * <ul>
     *  <li>{@link com.qpros.quanta.gherkin.model.Feature}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Background}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Scenario}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Given}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.When}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Then}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.And}</li>
     * </ul>
     *
     * <p>
     * Example:
     * </p>
     *
     * <pre>
     * test.createNode(new GherkinKeyword("Scenario"), "bddTest");
     * </pre>
     *
     * @param gherkinKeyword Name of the {@link GherkinKeyword}
     * @param name           Name of node
     * @return {@link QuantaTest} object
     */
    public synchronized QuantaTest createNode(GherkinKeyword gherkinKeyword, String name) {
        return createNode(gherkinKeyword, name, null);
    }

    /**
     * Creates a node
     *
     * @param name Name of node
     * @return {@link QuantaTest} object
     */
    public synchronized QuantaTest createNode(String name) {
        return createNode(name, null);
    }

    private void applyCommonNodeSettings(QuantaTest QuantaTest) {
        QuantaTest.getModel().setLevel(test.getLevel() + 1);
        QuantaTest.getModel().setParent(getModel());
        test.getNodeContext().add(QuantaTest.getModel());
    }

    private void addNodeToReport(QuantaTest QuantaNode) {
        Quanta.addNode(QuantaNode.getModel());
    }

    /**
     * Logs an event with {@link Status}, details and a media object: {@link Screencast} or
     * {@link ScreenCapture}
     *
     * <p>
     * Example:
     * </p>
     *
     * <pre>
     * test.log(Status.FAIL, "details", MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     *
     * @param status   {@link Status}
     * @param details  Details
     * @param provider A {@link MediaEntityModelProvider} object
     * @return An {@link QuantaTest} object
     */
    public synchronized QuantaTest log(Status status, String details, MediaEntityModelProvider provider) {
        Log evt = createLog(status, details);
        addMedia(evt, provider);
        return addLog(evt);
    }

    private synchronized void addMedia(Log evt, MediaEntityModelProvider provider) {
        if (provider != null) {
            Class<? extends Media> clazz = provider.getMedia().getClass();

            if (clazz.equals(ScreenCapture.class)) {
                ScreenCapture sc = (ScreenCapture) provider.getMedia();
                evt.setScreenCapture(sc);
            } else {
                evt.setScreencast((Screencast) provider.getMedia());
            }
        }
    }

    /**
     * Logs an event with {@link Status} and details
     *
     * @param status  {@link Status}
     * @param details Details
     * @return An {@link QuantaTest} object
     */
    public synchronized QuantaTest log(Status status, String details) {
        return log(status, details, null);
    }

    /**
     * Logs an event with {@link Status} and custom {@link Markup} such as:
     *
     * <ul>
     * 	<li>Code block</li>
     * 	<li>Label</li>
     * 	<li>Table</li>
     * </ul>
     *
     * @param status {@link Status}
     * @param markup {@link Markup}
     * @return An {@link QuantaTest} object
     */
    public synchronized QuantaTest log(Status status, Markup markup) {
        String details = markup.getMarkup();
        return log(status, details);
    }

    private synchronized QuantaTest addLog(Log evt) {
        test.getLogContext().add(evt);
        test.end();

        Quanta.addLog(test, evt);

        if (evt.hasScreenCapture()) {
            try {
                Quanta.addScreenCapture(evt, evt.getScreenCaptureContext().getLast());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return this;
    }

    private Log createLog(Status status) {
        Log evt = new Log(this);
        evt.setStatus(status);
        evt.setSequence(test.getLogContext().getAll().size() + 1);
        return evt;
    }

    private Log createLog(Status status, String details) {
        Log evt = createLog(status);
        evt.setDetails(details == null ? "" : details.trim());
        return evt;
    }

    /**
     * Logs an event with {@link Status}, an exception and a media object: {@link Screencast} or
     * {@link ScreenCapture}
     *
     * <p>
     * Example:
     * </p>
     *
     * <pre>
     * Exception exception = new NullPointerException();
     * test.log(Status.FAIL, exception, MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     *
     * @param status   {@link Status}
     * @param t        {@link Throwable}
     * @param provider A {@link MediaEntityModelProvider} object
     * @return An {@link QuantaTest} object
     */
    public synchronized QuantaTest log(Status status, Throwable t, MediaEntityModelProvider provider) {
        ExceptionInfo exInfo = new ExceptionInfo(t);
        getModel().setExceptionInfo(exInfo);
        Log evt = createLog(status);
        evt.setExceptionInfo(exInfo);
        addMedia(evt, provider);
        return addLog(evt);
    }

    /**
     * Logs an event with {@link Status} and exception
     *
     * @param status {@link Status}
     * @param t      {@link Throwable}
     * @return An {@link QuantaTest} object
     */
    public synchronized QuantaTest log(Status status, Throwable t) {
        return log(status, t, null);
    }

    /**
     * Logs an <code>Status.INFO</code> event with details and a media object:
     * {@link Screencast} or {@link ScreenCapture}
     *
     * <p>
     * Example:
     * </p>
     *
     * <pre>
     * test.info("details", MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     *
     * @param details  Details
     * @param provider A {@link MediaEntityModelProvider} object
     * @return An {@link QuantaTest} object
     */
    public QuantaTest info(String details, MediaEntityModelProvider provider) {
        log(Status.INFO, details, provider);
        return this;
    }

    /**
     * Logs an event with <code>Status.INFO</code> with details
     *
     * @param details Details
     * @return {@link QuantaTest} object
     */
    public QuantaTest info(String details) {
        return info(details, null);
    }

    /**
     * Logs an <code>Status.INFO</code> event with an exception and a media object:
     * {@link Screencast} or {@link ScreenCapture}
     *
     * <p>
     * Example:
     * </p>
     *
     * <pre>
     * Exception exception = new NullPointerException();
     * test.info(exception, MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     *
     * @param t        {@link Throwable}
     * @param provider A {@link MediaEntityModelProvider} object
     * @return An {@link QuantaTest} object
     */
    public QuantaTest info(Throwable t, MediaEntityModelProvider provider) {
        log(Status.INFO, t, provider);
        return this;
    }

    /**
     * Logs an event with <code>Status.INFO</code> and exception
     *
     * @param t {@link Throwable}
     * @return {@link QuantaTest} object
     */
    public QuantaTest info(Throwable t) {
        return info(t, null);
    }

    /**
     * Logs an event with <code>Status.INFO</code> and custom {@link Markup} such as:
     *
     * <ul>
     * 	<li>Code block</li>
     * 	<li>Label</li>
     * 	<li>Table</li>
     * </ul>
     *
     * @param m {@link Markup}
     * @return {@link QuantaTest} object
     */
    public QuantaTest info(Markup m) {
        log(Status.INFO, m);
        return this;
    }

    /**
     * Logs an <code>Status.PASS</code> event with details and a media object:
     * {@link Screencast} or {@link ScreenCapture}
     *
     * <p>
     * Example:
     * </p>
     *
     * <pre>
     * test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     *
     * @param details  Details
     * @param provider A {@link MediaEntityModelProvider} object
     * @return An {@link QuantaTest} object
     */
    public QuantaTest pass(String details, MediaEntityModelProvider provider) {
        log(Status.PASS, details, provider);
        return this;
    }

    /**
     * Logs an event <code>Status.PASS</code> with details
     *
     * @param details Details
     * @return {@link QuantaTest} object
     */
    public QuantaTest pass(String details) {
        return pass(details, null);
    }

    /**
     * Logs an <code>Status.PASS</code> event with an exception and a media object:
     * {@link Screencast} or {@link ScreenCapture}
     *
     * <p>
     * Example:
     * </p>
     *
     * <pre>
     * Exception exception = new NullPointerException();
     * test.pass(exception, MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     *
     * @param t        {@link Throwable}
     * @param provider A {@link MediaEntityModelProvider} object
     * @return An {@link QuantaTest} object
     */
    public QuantaTest pass(Throwable t, MediaEntityModelProvider provider) {
        log(Status.PASS, t, provider);
        return this;
    }

    /**
     * Logs an event with <code>Status.PASS</code> and exception
     *
     * @param t {@link Throwable}
     * @return An {@link QuantaTest} object
     */
    public QuantaTest pass(Throwable t) {
        return pass(t, null);
    }

    /**
     * Logs an event with <code>Status.PASS</code> and custom {@link Markup} such as:
     *
     * <ul>
     * 	<li>Code block</li>
     * 	<li>Label</li>
     * 	<li>Table</li>
     * </ul>
     *
     * @param m {@link Markup}
     * @return An {@link QuantaTest} object
     */
    public QuantaTest pass(Markup m) {
        log(Status.PASS, m);
        return this;
    }

    /**
     * Logs an <code>Status.FAIL</code> event with details and a media object:
     * {@link Screencast} or {@link ScreenCapture}
     *
     * @param details  Details
     * @param provider A {@link MediaEntityModelProvider} object
     * @return An {@link QuantaTest} object
     */
    public QuantaTest fail(String details, MediaEntityModelProvider provider) {
        log(Status.FAIL, details, provider);
        return this;
    }

    /**
     * Logs an event <code>Status.FAIL</code> with details
     *
     * @param details Details
     * @return {@link QuantaTest} object
     */
    public QuantaTest fail(String details) {
        return fail(details, null);
    }

    /**
     * Logs an <code>Status.FAIL</code> event with an exception and a media object:
     * {@link Screencast} or {@link ScreenCapture}
     *
     * <p>
     * Example:
     * </p>
     *
     * <pre>
     * Exception exception = new NullPointerException();
     * test.fail(exception, MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     *
     * @param t        {@link Throwable}
     * @param provider A {@link MediaEntityModelProvider} object
     * @return An {@link QuantaTest} object
     */
    public QuantaTest fail(Throwable t, MediaEntityModelProvider provider) {
        log(Status.FAIL, t, provider);
        return this;
    }

    /**
     * Logs an event with <code>Status.FAIL</code> and exception
     *
     * @param t {@link Throwable}
     * @return {@link QuantaTest} object
     */
    public QuantaTest fail(Throwable t) {
        return fail(t, null);
    }

    /**
     * Logs an event with <code>Status.FAIL</code> and custom {@link Markup} such as:
     *
     * <ul>
     * 	<li>Code block</li>
     * 	<li>Label</li>
     * 	<li>Table</li>
     * </ul>
     *
     * @param m {@link Markup}
     * @return {@link QuantaTest} object
     */
    public QuantaTest fail(Markup m) {
        log(Status.FAIL, m);
        return this;
    }

    /**
     * Logs an <code>Status.DATAL</code> event with an exception and a media object:
     * {@link Screencast} or {@link ScreenCapture}
     *
     * @param details  Details
     * @param provider A {@link MediaEntityModelProvider} object
     * @return An {@link QuantaTest} object
     */
    public QuantaTest fatal(String details, MediaEntityModelProvider provider) {
        log(Status.FATAL, details, provider);
        return this;
    }

    /**
     * Logs an event <code>Status.FATAL</code> with details
     *
     * @param details Details
     * @return An {@link QuantaTest} object
     */
    public QuantaTest fatal(String details) {
        log(Status.FATAL, details);
        return this;
    }

    /**
     * Logs an <code>Status.FATAL</code> event with an exception and a media object:
     * {@link Screencast} or {@link ScreenCapture}
     *
     * <p>
     * Example:
     * </p>
     *
     * <pre>
     * Exception exception = new NullPointerException();
     * test.fatal(exception, MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     *
     * @param t        {@link Throwable}
     * @param provider A {@link MediaEntityModelProvider} object
     * @return An {@link QuantaTest} object
     */
    public QuantaTest fatal(Throwable t, MediaEntityModelProvider provider) {
        log(Status.FATAL, t, provider);
        return this;
    }

    /**
     * Logs an event with <code>Status.FATAL</code> and exception
     *
     * @param t {@link Throwable}
     * @return {@link QuantaTest} object
     */
    public QuantaTest fatal(Throwable t) {
        log(Status.FATAL, t);
        return this;
    }

    /**
     * Logs an event with <code>Status.FATAL</code> and custom {@link Markup} such as:
     *
     * <ul>
     * 	<li>Code block</li>
     * 	<li>Label</li>
     * 	<li>Table</li>
     * </ul>
     *
     * @param m {@link Markup}
     * @return An {@link QuantaTest} object
     */
    public QuantaTest fatal(Markup m) {
        log(Status.FATAL, m);
        return this;
    }

    /**
     * Logs an <code>Status.WARNING</code> event with an exception and a media object:
     * {@link Screencast} or {@link ScreenCapture}
     *
     * @param details  Details
     * @param provider A {@link MediaEntityModelProvider} object
     * @return An {@link QuantaTest} object
     */
    public QuantaTest warning(String details, MediaEntityModelProvider provider) {
        log(Status.WARNING, details, provider);
        return this;
    }

    /**
     * Logs an event <code>Status.WARNING</code> with details
     *
     * @param details Details
     * @return {@link QuantaTest} object
     */
    public QuantaTest warning(String details) {
        return warning(details, null);
    }

    /**
     * Logs an <code>Status.WARNING</code> event with an exception and a media object:
     * {@link Screencast} or {@link ScreenCapture}
     *
     * <p>
     * Example:
     * </p>
     *
     * <pre>
     * Exception exception = new NullPointerException();
     * test.warning(exception, MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     *
     * @param t        {@link Throwable}
     * @param provider A {@link MediaEntityModelProvider} object
     * @return An {@link QuantaTest} object
     */
    public QuantaTest warning(Throwable t, MediaEntityModelProvider provider) {
        log(Status.WARNING, t, provider);
        return this;
    }

    /**
     * Logs an event with <code>Status.WARNING</code> and exception
     *
     * @param t {@link Throwable}
     * @return {@link QuantaTest} object
     */
    public QuantaTest warning(Throwable t) {
        return warning(t, null);
    }

    /**
     * Logs an event with <code>Status.WARNING</code> and custom {@link Markup} such as:
     *
     * <ul>
     * 	<li>Code block</li>
     * 	<li>Label</li>
     * 	<li>Table</li>
     * </ul>
     *
     * @param m {@link Markup}
     * @return An {@link QuantaTest} object
     */
    public QuantaTest warning(Markup m) {
        log(Status.WARNING, m);
        return this;
    }

    /**
     * Logs an <code>Status.ERROR</code> event with an exception and a media object:
     * {@link Screencast} or {@link ScreenCapture}
     *
     * @param details  Details
     * @param provider A {@link MediaEntityModelProvider} object
     * @return An {@link QuantaTest} object
     */
    public QuantaTest error(String details, MediaEntityModelProvider provider) {
        log(Status.ERROR, details, provider);
        return this;
    }

    /**
     * Logs an event <code>Status.ERROR</code> with details
     *
     * @param details Details
     * @return {@link QuantaTest} object
     */
    public QuantaTest error(String details) {
        return error(details, null);
    }

    /**
     * Logs an <code>Status.ERROR</code> event with an exception and a media object:
     * {@link Screencast} or {@link ScreenCapture}
     *
     * <p>
     * Example:
     * </p>
     *
     * <pre>
     * Exception exception = new NullPointerException();
     * test.error(exception, MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     *
     * @param t        {@link Throwable}
     * @param provider A {@link MediaEntityModelProvider} object
     * @return An {@link QuantaTest} object
     */
    public QuantaTest error(Throwable t, MediaEntityModelProvider provider) {
        log(Status.ERROR, t, provider);
        return this;
    }

    /**
     * Logs an event with <code>Status.ERROR</code> and exception
     *
     * @param t {@link Throwable}
     * @return {@link QuantaTest} object
     */
    public QuantaTest error(Throwable t) {
        return error(t, null);
    }

    /**
     * Logs an event with <code>Status.ERROR</code> and custom {@link Markup} such as:
     *
     * <ul>
     * 	<li>Code block</li>
     * 	<li>Label</li>
     * 	<li>Table</li>
     * </ul>
     *
     * @param m {@link Markup}
     * @return {@link QuantaTest} object
     */
    public QuantaTest error(Markup m) {
        log(Status.ERROR, m);
        return this;
    }

    /**
     * @param details  Details
     * @param provider A {@link MediaEntityModelProvider} object
     * @return An {@link QuantaTest} object
     */
    public QuantaTest skip(String details, MediaEntityModelProvider provider) {
        log(Status.SKIP, details, provider);
        return this;
    }

    /**
     * Logs an event <code>Status.SKIP</code> with details
     *
     * @param details Details
     * @return {@link QuantaTest} object
     */
    public QuantaTest skip(String details) {
        return skip(details, null);
    }

    /**
     * Logs an <code>Status.SKIP</code> event with an exception and a media object:
     * {@link Screencast} or {@link ScreenCapture}
     *
     * <p>
     * Example:
     * </p>
     *
     * <pre>
     * Exception exception = new NullPointerException();
     * test.skip(exception, MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     *
     * @param t        {@link Throwable}
     * @param provider A {@link MediaEntityModelProvider} object
     * @return An {@link QuantaTest} object
     */
    public QuantaTest skip(Throwable t, MediaEntityModelProvider provider) {
        log(Status.SKIP, t, provider);
        return this;
    }

    /**
     * Logs an event with <code>Status.SKIP</code> and exception
     *
     * @param t {@link Throwable}
     * @return {@link QuantaTest} object
     */
    public QuantaTest skip(Throwable t) {
        return skip(t, null);
    }

    /**
     * Logs an event with <code>Status.SKIP</code> and custom {@link Markup} such as:
     *
     * <ul>
     * 	<li>Code block</li>
     * 	<li>Label</li>
     * 	<li>Table</li>
     * </ul>
     *
     * @param m {@link Markup}
     * @return {@link QuantaTest} object
     */
    public QuantaTest skip(Markup m) {
        log(Status.SKIP, m);
        return this;
    }

    /**
     * Logs an <code>Status.DEBUG</code> event with an exception and a media object:
     * {@link Screencast} or {@link ScreenCapture}
     *
     * @param details  Details
     * @param provider A {@link MediaEntityModelProvider} object
     * @return An {@link QuantaTest} object
     */
    public QuantaTest debug(String details, MediaEntityModelProvider provider) {
        log(Status.DEBUG, details, provider);
        return this;
    }

    /**
     * Logs an event <code>Status.DEBUG</code> with details
     *
     * @param details Details
     * @return {@link QuantaTest} object
     */
    public QuantaTest debug(String details) {
        return debug(details, null);
    }

    /**
     * Logs an <code>Status.DEBUG</code> event with an exception and a media object:
     * {@link Screencast} or {@link ScreenCapture}
     *
     * <p>
     * Example:
     * </p>
     *
     * <pre>
     * Exception exception = new NullPointerException();
     * test.debug(exception, MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     *
     * @param t        {@link Throwable}
     * @param provider A {@link MediaEntityModelProvider} object
     * @return An {@link QuantaTest} object
     */
    public QuantaTest debug(Throwable t, MediaEntityModelProvider provider) {
        log(Status.DEBUG, t, provider);
        return this;
    }

    /**
     * Logs an event with <code>Status.SKIP</code> and exception
     *
     * @param t {@link Throwable}
     * @return {@link QuantaTest} object
     */
    public QuantaTest debug(Throwable t) {
        return debug(t, null);
    }

    /**
     * Logs an event with <code>Status.DEBUG</code> and custom {@link Markup} such as:
     *
     * <ul>
     *  <li>Code block</li>
     *  <li>Label</li>
     *  <li>Table</li>
     * </ul>
     *
     * @param m {@link Markup}
     * @return {@link QuantaTest} object
     */
    public QuantaTest debug(Markup m) {
        log(Status.DEBUG, m);
        return this;
    }

    /**
     * Assigns a category or group
     *
     * @param category Category name
     * @return {@link QuantaTest} object
     */
    public QuantaTest assignCategory(String[] category, String[] subCategory) {
        if (category == null)
            return this;

        Arrays.stream(category).filter(StringUtil::isNotNullOrEmpty).forEach(c -> {
            Category objCategory = new Category(c.replace(" ", ""));
            Arrays.stream(subCategory).filter(StringUtil::isNotNullOrEmpty).forEach(subC -> {
                objCategory.subCategoryList.add(new SubCategory(subC.replace(" ", "")));

            });
            test.setCategory(objCategory);
            Quanta.assignCategory(test, objCategory);
        });
        return this;
    }
    public QuantaTest assignCategory(String... category) {
        if (category == null)
            return this;

        Arrays.stream(category).filter(StringUtil::isNotNullOrEmpty).forEach(c -> {
            Category objCategory = new Category(c.replace(" ", ""));
            test.setCategory(objCategory);
            Quanta.assignCategory(test, objCategory);
        });
        return this;
    }

    /**
     * Assigns an author
     *
     * @param author Author name
     * @return {@link QuantaTest} object
     */
    public QuantaTest assignAuthor(String... author) {
        Arrays.stream(author).filter(StringUtil::isNotNullOrEmpty).forEach(x -> {
            Author a = new Author(x.replace(" ", ""));
            test.setAuthor(a);
            Quanta.assignAuthor(test, a);
        });
        return this;
    }

    /**
     * Assign a device
     *
     * @param device Device name
     * @return {@link QuantaTest} object
     */
    public QuantaTest assignDevice(String... device) {
        Arrays.stream(device).filter(StringUtil::isNotNullOrEmpty).forEach(x -> {
            Device d = new Device(x.replace(" ", ""));
            test.setDevice(d);
            Quanta.assignDevice(test, d);
        });
        return this;
    }

    @Override public QuantaTest addScreenCaptureFromPath(String imagePath, String title) throws IOException {
        if (imagePath == null || imagePath.isEmpty())
            throw new IllegalArgumentException("imagePath cannot be null or empty");

        ScreenCapture screenCapture = new ScreenCapture();
        screenCapture.setPath(imagePath);
        if (title != null) {
            screenCapture.setName(title);
        }
        screenCapture.setMediaType(MediaType.IMG);
        if (test.getObjectId() != null) {
            screenCapture.setTestObjectId(test.getObjectId());
        }
        Quanta.addScreenCapture(test, screenCapture);
        return addScreenCapture(screenCapture);
    }

    private QuantaTest addScreenCapture(ScreenCapture screenCapture) {
        test.setScreenCapture(screenCapture);
        if (test.getObjectId() != null) {
            int sequence = test.getScreenCaptureList().size();
            screenCapture.setTestObjectId(test.getObjectId());
            screenCapture.setSequence(sequence);
        }
        return this;
    }

    @Override public QuantaTest addScreenCaptureFromPath(String imagePath) throws IOException {
        return addScreenCaptureFromPath(imagePath, null);
    }

    @Override public QuantaTest addScreenCaptureFromBase64String(String s, String title) {
        ScreenCapture screenCapture = new ScreenCapture();
        screenCapture.setBase64String(s);
        screenCapture.setName(title);
        screenCapture.setMediaType(MediaType.IMG);

        if (test.getObjectId() != null)
            screenCapture.setTestObjectId(test.getObjectId());

        try {
            Quanta.addScreenCapture(test, screenCapture);
        } catch (IOException e) {
        }

        return addScreenCapture(screenCapture);
    }

    @Override public QuantaTest addScreenCaptureFromBase64String(String s) {
        return addScreenCaptureFromBase64String(s, null);
    }

    @Override public QuantaTest addScreencastFromPath(String screencastPath) throws IOException {
        Screencast screencast = new Screencast();
        screencast.setPath(screencastPath);
        screencast.setMediaType(MediaType.VID);
        test.setScreencast(screencast);
        if (test.getObjectId() != null) {
            int sequence = test.getScreencastList().size();
            screencast.setTestObjectId(test.getObjectId());
            screencast.setSequence(sequence);
        }
        Quanta.addScreencast(test, screencast);
        return this;
    }

    /**
     * Provides the current run status of the test or node
     *
     * @return {@link Status}
     */
    public Status getStatus() {
        return getModel().getStatus();
    }

    /**
     * Returns the underlying test which controls the internal model
     *
     * @return {@link Test} object
     */
    public Test getModel() {
        return test;
    }

    /**
     * Returns the {@link QuantaReports} instance associated with this test
     *
     * @return the {@link QuantaReports} instance associated with this test
     */
    public QuantaReports getQuanta() {
        return Quanta;
    }

    void setUseManualConfiguration(Boolean b) {
        getModel().setUseManualConfiguration(b);
    }

}

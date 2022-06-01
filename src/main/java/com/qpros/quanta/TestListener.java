package com.qpros.quanta;

import java.io.IOException;

import com.qpros.quanta.model.*;

/**
 * Listener methods invoked when an event occurs in the report:
 * 
 * <ul>
 *  <li>A test or node is started</li>
 *  <li>A category or author is added</li>
 *  <li>A media object is added etc.</li>
 * </ul>
 */
public interface TestListener {
    
    /**
     * Invoked when a test is started using <code>createTest()</code>
     * 
     * @param test {@link Test} object
     */
    void onTestStarted(Test test);
    
    /**
     * Invoked when a test is removed using <code>removeTest()</code>
     * 
     * @param test {@link Test} object
     */
    void onTestRemoved(Test test);
    
    /**
     * Invoked when a node is started using <code>createNode()</code>
     * 
     * @param node {@link Test} object
     */
    void onNodeStarted(Test node);
    
    /**
     * Invoked each time a log is added to any test/node
     * 
     * @param test {@link Test} object
     * @param log {@link Log} object
     */
    void onLogAdded(Test test, Log log);
    
    /**
     * Invoked each time a category is assigned to any test/node
     * 
     * @param test {@link Test} object
     * @param category {@link Category} object
     */
    void onCategoryAssigned(Test test, Category category);

    /**
     * Invoked each time a category is assigned to any test/node
     *
     * @param subCategory {@link SubCategory} object
     * @param subCategory {@link SubCategory} object
     */
    void onSubCategoryAssigned(Test test, SubCategory subCategory);
    /**
     * Invoked each time an author is assigned to any test/node
     * 
     * @param test {@link Test} object
     * @param author {@link Author} object
     */
    void onAuthorAssigned(Test test, Author author);
    
    /**
     * Invoked each time a device is assigned to any test/node
     * 
     * @param test {@link Test} object
     * @param device {@link Device} object
     */
    void onDeviceAssigned(Test test, Device device);
    
    /**
     * Invoked each time a screencapture is added to test
     * 
     * @param test {@link Test} object
     * @param screenCapture {@link ScreenCapture} object
     * @throws IOException Exception thrown if the media object is not found
     */
    void onScreenCaptureAdded(Test test, ScreenCapture screenCapture) throws IOException;
    
    /**
     * Invoked each time a screencapture is added to log
     * 
     * @param log {@link Log} object
     * @param screenCapture {@link ScreenCapture} object
     * @throws IOException Exception thrown if the media object is not found
     */
    void onScreenCaptureAdded(Log log, ScreenCapture screenCapture) throws IOException;
    
    /**
     * Invoked each time a screencast is added
     * 
     * @param test {@link Test} object
     * @param screencast {@link Screencast} object
     * @throws IOException Exception thrown if the media object is not found
     */
    void onScreencastAdded(Test test, Screencast screencast) throws IOException;
    
}

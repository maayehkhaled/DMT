package com.qpros.quanta;

import java.util.List;

import com.qpros.quanta.model.Author;
import com.qpros.quanta.model.Category;
import com.qpros.quanta.model.Device;
import com.qpros.quanta.model.Test;

/**
 * Allows sharing of aggregates/collections to be shared with the started reporter. A few examples
 * of these collections include:
 * 
 * <ul>
 *  <li>Category context - list of all categories and associated tests</li>
 *  <li>Exception context - list of all exceptions and associated tests</li>
 *  <li>Complete list of started tests</li>
 *  <li>System information</li>
 * </ul>
 */
public interface ReportAggregatesListener {
    
    /**
     * Allows sharing the complete list of tests with the reporter
     * 
     * @param testList list of all tests created by {@link quanta}
     */
    void setTestList(List<Test> testList);
    
    /**
     * Passes the complete list of logs to the reporter
     * 
     * @param logs testrunner logs
     */
    void setTestRunnerLogs(List<String> logs);
    
    /**
     * Allows sharing the complete list of category and associated tests with the reporter
     * 
     * @param categoryContext collection containing categories and all associated tests
     */
    void setCategoryContextInfo(TestAttributeTestContextProvider<Category> categoryContext);

    /**
     * Allows sharing the complete list of author and associated tests with the reporter
     *
     * @param authorContext collection containing author and all associated tests
     */
    void setAuthorContextInfo(TestAttributeTestContextProvider<Author> authorContext);
    
    /**
     * Allows sharing the complete list of device and associated tests with the reporter
     *
     * @param deviceContext collection containing devices and all associated tests
     */
    void setDeviceContextInfo(TestAttributeTestContextProvider<Device> deviceContext);
    
    
    /**
     * Allows sharing the complete list of exceptions and associated tests with the reporter
     * 
     * @param exceptionContext collection containing exception and all associated tests
     */
    void setExceptionContextInfo(ExceptionTestContextImpl exceptionContext);
    
    /**
     * Passes all system information to the reporter
     * 
     * @param systemAttributeContext system information
     */
    void setSystemAttributeContext(SystemAttributeContext systemAttributeContext);
    
    /**
     * Report Status stats of the run session for all possible levels:
     * 
     * <ul>
     *  <li>Features/Tests</li>
     *  <li>Scenarios/Logs</li>
     *  <li>Steps</li>
     * </ul>
     * 
     * @param sc {@link ReportStatusStats} represents stats of each hierarchical level of test/event 
     */
    void setReportStatusStats(ReportStatusStats sc);
    
    /**
     * A distinct list of status assigned to tests
     * 
     * @param statusList a distinct list of {@link Status}
     */
    void setStatusList(List<Status> statusList);
    
}

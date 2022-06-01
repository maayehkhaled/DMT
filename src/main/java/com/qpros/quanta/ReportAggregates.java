package com.qpros.quanta;

import java.util.Date;
import java.util.List;

import com.qpros.quanta.model.*;

/**
 * Aggregator for report elements and collections
 */
public class ReportAggregates {

    private List<Test> testList;
    private List<String> testRunnerLogs;
    private TestAttributeTestContextProvider<Category> categoryContext;
    private TestAttributeTestContextProvider<SubCategory> subCategoryContext;
    private TestAttributeTestContextProvider<Author> authorContext;
    private TestAttributeTestContextProvider<Device> deviceContext;
    private ExceptionTestContextImpl exceptionContext;
    private SystemAttributeContext systemAttributeContext;
    private ReportStatusStats reportStatusStats;
    private List<Status> statusList;
    private Date startTime;
    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Status getStatus() {
        return Status.getHighestStatus(getStatusList());
    }

    public List<Test> getTestList() {
        return testList;
    }

    public void setTestList(List<Test> testList) {
        this.testList = testList;
    }

    public List<String> getTestRunnerLogs() {
        return testRunnerLogs;
    }

    public void setTestRunnerLogs(List<String> testRunnerLogs) {
        this.testRunnerLogs = testRunnerLogs;
    }

    public TestAttributeTestContextProvider<Category> getCategoryContext() {
        return categoryContext;
    }

    public TestAttributeTestContextProvider<SubCategory> getSubCategoryContext() {
        return subCategoryContext;
    }

    public void setCategoryContext(TestAttributeTestContextProvider<Category> categoryContext) {
        this.categoryContext = categoryContext;
    }

    public void setSubCategoryContext(TestAttributeTestContextProvider<SubCategory> subCategoryContext) {
        this.subCategoryContext = subCategoryContext;
    }

    public TestAttributeTestContextProvider<Author> getAuthorContext() {
        return authorContext;
    }

    public void setAuthorContext(TestAttributeTestContextProvider<Author> authorContext) {
        this.authorContext = authorContext;
    }

    public TestAttributeTestContextProvider<Device> getDeviceContext() {
        return deviceContext;
    }

    public void setDeviceContext(TestAttributeTestContextProvider<Device> deviceContext) {
        this.deviceContext = deviceContext;
    }

    public ExceptionTestContextImpl getExceptionContext() {
        return exceptionContext;
    }

    public void setExceptionContext(ExceptionTestContextImpl exceptionContext) {
        this.exceptionContext = exceptionContext;
    }

    public SystemAttributeContext getSystemAttributeContext() {
        return systemAttributeContext;
    }

    public void setSystemAttributeContext(SystemAttributeContext systemAttributeContext) {
        this.systemAttributeContext = systemAttributeContext;
    }

    public ReportStatusStats getReportStatusStats() {
        return reportStatusStats;
    }

    public void setReportStatusStats(ReportStatusStats reportStatusStats) {
        this.reportStatusStats = reportStatusStats;
    }

    public List<Status> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Status> statusList) {
        this.statusList = statusList;
    }

}

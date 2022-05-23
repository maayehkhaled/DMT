package com.qpros.quanta.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.bson.types.ObjectId;

import com.qpros.quanta.QuantaTest;
import com.qpros.quanta.RunResult;
import com.qpros.quanta.Status;
import com.qpros.quanta.markuputils.Markup;

public class Log 
	implements RunResult, Serializable, BasicMongoReportElement {

    private static final long serialVersionUID = 1594512136869286425L;

    private AbstractStructure<ScreenCapture> screenCaptureContext;
    private AbstractStructure<Screencast> screencastContext;
    private QuantaTest parent;
    private Test parentModel;
    private Markup markup;
    private Date timestamp = Calendar.getInstance().getTime();
    private Status logStatus;
    private String stepName;
    private String stepTest;
    private String details;
    private int sequence;
    private ObjectId objectId;
    private ExceptionInfo exceptionInfo;
    
    public Log(Test test) { 
    	this.parentModel = test;
    }
    
    public Log(QuantaTest test) {
    	this.parent = test;
    }
    
    public Date getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setStatus(Status logStatus) {
        this.logStatus = logStatus;
    }
    public Status getStatus() {
        return logStatus;
    }
   
    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public String getStepTest() {
        return stepTest;
    }

    public void setStepTest(String stepTest) {
        this.stepTest = stepTest;
    }

    public String getStepName() {
        return stepName;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    public String getDetails() {
        return details;
    }
    
    public void setMarkup(Markup markup) {
        this.markup = markup;
    }
    public Markup getMarkup() {
        return markup;
    }
    
    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
    public int getSequence() {
        return sequence;
    }
    
    public void setScreenCapture(ScreenCapture screenCapture) {
        if (screenCaptureContext == null) {
            screenCaptureContext = new AbstractStructure<>();
        }
        screenCaptureContext.add(screenCapture);
        screenCapture.setTestObjectId(getParent().getModel().getObjectId());
    }
    
    public AbstractStructure<ScreenCapture> getScreenCaptureContext() {
        return screenCaptureContext;
    }
    
    public boolean hasScreenCapture() {
        return screenCaptureContext != null 
                && screenCaptureContext.size() > 0;
    }
    
    public void setScreencast(Screencast screencast) {
    	if (screencastContext == null) {
    		screencastContext = new AbstractStructure<>();
    	}
    	screencastContext.add(screencast);
    }
    
    public AbstractStructure<Screencast> getScreencastContext() {
    	return screencastContext;
    }
    
    public boolean hasScreencast() {
        return screencastContext != null 
                && screencastContext.size() > 0;
    }
    
    public QuantaTest getParent() {
        return parent;
    }
    
    public Test getParentModel() {
    	return parent == null 
    			? parentModel 
				: parent.getModel();
    }

    @Override
    public ObjectId getObjectId() {
        return objectId;
    }

    @Override
    public void setObjectId(ObjectId id) {
        objectId = id;
    }
    
    public void setExceptionInfo(ExceptionInfo exceptionInfo) {
    	this.exceptionInfo = exceptionInfo;
    }
    
    public ExceptionInfo getExceptionInfo() {
    	return exceptionInfo;
    }
    
    @Override
    public String toString() {
    	return "[log] " +
    			" {timestamp: " + getTimestamp() + "," +
    			" step: " + getStepTest() + "," +
    			" status: " + getStatus() + "," +
    			" details: " + getDetails() +
				" }";
    }
    
}

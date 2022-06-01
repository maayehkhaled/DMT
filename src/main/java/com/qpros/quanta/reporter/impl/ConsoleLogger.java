package com.qpros.quanta.reporter.impl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.qpros.quanta.model.*;
import com.qpros.quanta.reporter.AbstractReporter;

public class ConsoleLogger
	extends AbstractReporter {

	private static final Logger LOGGER = Logger.getAnonymousLogger();
	private static final String REPORTER_NAME = "consolelogger";
	
	@Override
	public void start() {
		LOGGER.log(Level.INFO, getClass().getName() + " was registered");
	}

	@Override
	public void stop() {
		LOGGER.log(Level.INFO, getClass().getName() + " stopping..");
	}

	public void onTestStarted(Test test) {
		LOGGER.log(Level.INFO, "Starting test " + test.getName());
	}

	@Override
	public void onTestRemoved(Test test) {
		LOGGER.log(Level.INFO, "Test with name " + test.getName() + " was removed");
	}

	public void onNodeStarted(Test node) {
		LOGGER.log(Level.INFO, "A new node " + node.getName() + " for test " + node.getParent().getName() + " created");
	}

	public void onLogAdded(Test test, Log log) {
		LOGGER.log(Level.INFO, "A new log event with details " + log.getDetails() + " for test " + test.getName() + " created");
	}

	@Override
	public void onCategoryAssigned(Test test, Category category) {
		LOGGER.log(Level.INFO, "A new category " + category.getName() + " for test " + test.getName() + " assigned");
	}

	@Override public void onSubCategoryAssigned(Test test, SubCategory subCategory) {
		LOGGER.log(Level.INFO, "A new category " + subCategory.getName() + " for test " + test.getName() + " assigned");
	}


	@Override
	public void onAuthorAssigned(Test test, Author author) {
		LOGGER.log(Level.INFO, "A new author " + author.getName() + " for test " + test.getName() + " assigned");
	}
	
	@Override
	public void onDeviceAssigned(Test test, Device device) {
		LOGGER.log(Level.INFO, "A new device " + device.getName() + " for test " + test.getName() + " assigned");
	}

	@Override
	public void onScreenCaptureAdded(Test test, ScreenCapture screenCapture) throws IOException {
		LOGGER.log(Level.INFO, "A new screencapture for test " + test.getName() + " created");
	}

	@Override
	public void onScreenCaptureAdded(Log log, ScreenCapture screenCapture) {
		LOGGER.log(Level.INFO, "A new screencapture for log " + log.getDetails() + " created");
	}

	@Override
	public void onScreencastAdded(Test test, Screencast screencast) { }

	@Override
	public String getReporterName() {
		return REPORTER_NAME;
	}

}

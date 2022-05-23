package com.qpros.quanta;

import com.qpros.quanta.reporter.AbstractReporter;

/**
 * The main service for {@link quanta} which allows attaching a reporter of type
 * {@link AbstractReporter} 
 */
public interface ReportService {
    
	void attachReporter(QuantaReporter... reporter);	
	
}

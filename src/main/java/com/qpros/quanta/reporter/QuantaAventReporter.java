package com.qpros.quanta.reporter;

import java.io.File;

import com.qpros.quanta.ReportAggregates;
import com.qpros.quanta.model.Category;
import com.qpros.quanta.model.SubCategory;
import com.qpros.quanta.model.Test;

/**
 * The QuantaHtmlReporter creates a rich standalone HTML file. It allows several configuration options
 * via the <code>config()</code> method.
 */
public class QuantaAventReporter 
	extends BasicFileReporter {
    
    private static final String REPORTER_NAME = "avent";
    
    public QuantaAventReporter(String path) {
        super(path);
    }
    
    public QuantaAventReporter(File file) {
    	super(file);
    }
    
    @Override
    public synchronized void flush(ReportAggregates reportAggregates) {
    }

	@Override
	public String getReporterName() {
		return REPORTER_NAME;
	}


    @Override public void onSubCategoryAssigned(Test test, SubCategory subCategory) {

    }
}

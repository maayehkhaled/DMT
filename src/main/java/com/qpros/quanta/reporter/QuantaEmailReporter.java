package com.qpros.quanta.reporter;

import java.io.File;

import com.qpros.quanta.ReportAggregates;
import com.qpros.quanta.SourceProvider;
import com.qpros.quanta.model.Category;
import com.qpros.quanta.model.SubCategory;
import com.qpros.quanta.model.Test;

/**
 * The QuantaHtmlReporter creates a rich standalone HTML file. It allows several configuration options
 * via the <code>config()</code> method.
 */
public class QuantaEmailReporter 
	extends BasicFileReporter
	implements SourceProvider {
    
    private static final String REPORTER_NAME = "email";
    
    public QuantaEmailReporter(String path) {
        super(path);
    }
    
    public QuantaEmailReporter(File file) {
    	super(file);
    }
    
    @Override
    public synchronized void flush(ReportAggregates reportAggregates) {
    }

	@Override
	public String getReporterName() {
		return REPORTER_NAME;
	}

	@Override
	public String getSource() {
		return super.getSource();
	}



	@Override public void onSubCategoryAssigned(Test test, SubCategory subCategory) {

	}
}

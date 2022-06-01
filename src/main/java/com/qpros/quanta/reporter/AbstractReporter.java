package com.qpros.quanta.reporter;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.qpros.quanta.AnalysisStrategy;
import com.qpros.quanta.ReportAggregates;
import com.qpros.quanta.Status;

/**
 * A base class for all Reporter types
 * 
 */
public abstract class AbstractReporter 
	extends ConfigurableReporter {

	protected List<Status> levels;
	protected Date startTime = Calendar.getInstance().getTime();
	protected Date endTime = startTime;
	protected List<Status> statusList;
    
	private AnalysisStrategy strategy = AnalysisStrategy.TEST;

	public void flush(ReportAggregates reportAggregates) {
	    this.startTime = reportAggregates.getStartTime();
		this.endTime = reportAggregates.getEndTime();
		this.statusList = reportAggregates.getStatusList();
	}
	
	protected Date getStartTime() {
		return startTime;
	}
	
	protected void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	protected Date getEndTime() {
		return endTime;
	}
	
    @Override
    public void setAnalysisStrategy(AnalysisStrategy strategy){
      this.strategy = strategy;
    }
    
    @Override
    public AnalysisStrategy getAnalysisStrategy() {
      return strategy;
    }

    public long getRunDuration() { 
        return endTime.getTime() - startTime.getTime(); 
    }
    
    public String getLongRunDuration() {
		long millis = getRunDuration();
        
        long secs = millis / 1000;
        long ms = millis % 1000;
        long mins = secs / 60;
        secs = (secs % 60);
        long hours = mins / 60;
        mins = mins % 60;
        
        return new DecimalFormat("00").format(hours) + ":" +new DecimalFormat("00").format( mins) + ":" + new DecimalFormat("00").format(secs) + ":" + new DecimalFormat("00").format(ms);
    }

	public List<Status> getStatusList() {
		return statusList;
	}

}

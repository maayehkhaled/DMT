package com.qpros.quanta.reporter.configuration;

import com.qpros.quanta.reporter.AbstractReporter;

/**
 * Contains configuration for rich reporters such as Avent, Tabular, Cards etc.
 *
 */
public abstract class RichViewReporterConfiguration
	extends BasicFileConfiguration 
	implements IReporterConfiguration {
	
	private Protocol protocol;
	private Theme theme;
	
	protected RichViewReporterConfiguration(AbstractReporter reporter) {
		super(reporter);
	}
	
	/**
     * Sets the protocol of accessing CSS/JS resources from CDN
     * 
     * <p>
     * Default protocol value: HTTPS
     * </p>
     * 
     * @param protocol Protocol, HTTPS or HTTP
     */
    public void setProtocol(Protocol protocol) {
        usedConfigs.put("protocol", String.valueOf(protocol).toLowerCase());
        this.protocol = protocol; 
    }
    
    public Protocol getProtocol() {
    	return protocol; 
	}
    
    /**
     * Sets the {@link Theme} of the report
     * 
     * @param theme {@link Theme}
     */
    public void setTheme(Theme theme) {
        usedConfigs.put("theme", String.valueOf(theme).toLowerCase());
        this.theme = theme; 
    }
    
    public Theme getTheme() { 
    	return theme; 
	}
    
    /**
	 * Enable or disable the Timeline section in the Dashboard view
	 *
	 * @param v Setting to enable or disable the Timeline section in the Dashboard
	 *          view
	 */
	public void enableTimeline(boolean v) {
		usedConfigs.put("enableTimeline", String.valueOf(v));
	}
	
	/**
	 * Setting to automatically store screen shots relative to the path. This method
	 * also sets the new relative path as a link from the report. Example:
	 * 
	 * <pre>
	 * /
	 *   Report.html
	 *   Report.0
	 *     - 1.png
	 *     - 2.png
	 *   Report.1
	 *     - 1.png
	 *     - 2.png
	 * </pre>
	 * 
	 * <p>
	 * Report.0 directory will contain media from the 1st run. Report.1 directory
	 * will contain media from the 2nd run.
	 * </p>
	 * 
	 * @param v Setting to enable this feature
	 */
	public void setAutoCreateRelativePathMedia(boolean v) {
		usedConfigs.put("autoCreateRelativePathMedia", String.valueOf(v));
	}
	
	/**
	 * Allows selecting a CDN/resource loader for your FileReporter
	 * 
	 * @param resourceCDN the {@link ResourceCDN}
	 */
	public void setResourceCDN(ResourceCDN resourceCDN) {
        usedConfigs.put("resourceCDN", String.valueOf(resourceCDN).toLowerCase());
    }
}

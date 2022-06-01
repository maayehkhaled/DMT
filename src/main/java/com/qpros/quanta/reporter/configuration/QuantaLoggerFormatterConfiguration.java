package com.qpros.quanta.reporter.configuration;

import java.io.File;
import java.util.stream.Stream;

import com.qpros.quanta.QuantaReports;
import com.qpros.quanta.reporter.AbstractReporter;
import com.qpros.quanta.reporter.QuantaLoggerReporter;
import com.qpros.quanta.resource.OfflineResxDelegate;
import com.qpros.quanta.utils.FileUtil;

/**
 * Defines configuration settings for the HTML reporter
 */
public class QuantaLoggerFormatterConfiguration 
	extends RichViewReporterConfiguration {

    public QuantaLoggerFormatterConfiguration(AbstractReporter reporter) {
    	super(reporter);
    }
    
    /**
     * Creates the HTML report, saving all resources (css, js, fonts) in the same location, so the
     * report can be viewed without an internet connection
     * 
     * @param offline Setting to enable an offline accessible report
     */
    public void enableOfflineMode(Boolean offline) {
    	usedConfigs.put("enableOfflineMode", String.valueOf(offline));
    	usedConfigs.put("offlineDirectory", getReporter().getReporterName() + "/");
    	if (offline) {
    		File f = getTargetDirectory(((QuantaLoggerReporter)getReporter()).getFileFile());
    		String s = "/";
    		String resourcePackagePath = QuantaReports.class.getPackage().getName().replace(".", s);
            resourcePackagePath += s + "offline" + s;
    		String[] resx = combine(getJSFiles(), 
    				getCSSFiles(),
    				getIconFiles(), 
    				getImgFiles());
    		OfflineResxDelegate.saveOfflineResources(resourcePackagePath, resx, f.getAbsolutePath());
    	}
    }

    private File getTargetDirectory(File f) {
    	String dir;
    	if (FileUtil.isDirectory(f)) {
    		dir = f.getAbsolutePath().replace("\\", "/");
        } else {
        	dir = f.getAbsolutePath().replace("\\", "/");
        	dir = new File(dir).getParent();
        }
    	dir += "/" + getReporter().getReporterName();
    	return new File(dir);
    }
    
    private String[] combine(String[]... array) {
    	String[] result = new String[] {};
    	for (String[] arr : array) {
    		result = Stream.of(result, arr).flatMap(Stream::of).toArray(String[]::new);
    	}
    	return result;
    }

    private String[] getJSFiles() {
    	String commonsPath = "commons/js/";
        String reporterPath = getReporter().getReporterName() + "/js/";
        String[] files = { 
        		commonsPath + "attr.js",
        		commonsPath + "dashboard.js",
        		reporterPath + "logger-scripts.js"
        };
        return files;
    }
    
    private String[] getCSSFiles() {
    	String stylesPath = "css/";
    	String reporterPath = getReporter().getReporterName() + "/" + stylesPath + "/";
        String[] files = { 
        		reporterPath + "logger-style.css", 
        };
        return files;
    }
    
    private String[] getIconFiles() {
        String path = "commons/css/icons/";
        String iconDirPath = "fontawesome/";
        String[] files = {
                path + "font-awesome.min.css",
                path + iconDirPath + "fontawesome-webfont.eot",
                path + iconDirPath + "fontawesome-webfont.svg",
                path + iconDirPath + "fontawesome-webfont.ttf",
                path + iconDirPath + "fontawesome-webfont.woff",
                path + iconDirPath + "fontawesome-webfont.woff2",
                path + iconDirPath + "FontAwesome.otf",
        };
        return files;
    }

    private String[] getImgFiles() {
        String path = "commons/img/";
        String[] files = {
                path + "logo.png"
        };
        return files;
    }
    
}

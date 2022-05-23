package com.qpros.quanta;

import java.io.IOException;

import com.qpros.quanta.model.Media;
import com.qpros.quanta.model.MediaType;
import com.qpros.quanta.model.ScreenCapture;

/**
 * Media builder for base64 and binary screenshots
 *
 */
public class MediaEntityBuilder {

	private static ThreadLocal<Media> media = new ThreadLocal<>();
	
	private static class MediaBuilderInstance {
        static final MediaEntityBuilder INSTANCE = new MediaEntityBuilder();
        
        private MediaBuilderInstance() { }
    }

    private MediaEntityBuilder() { }
	
    private static synchronized MediaEntityBuilder getInstance() {
    	return MediaBuilderInstance.INSTANCE;
    }
    
	public MediaEntityModelProvider build() {
		return new MediaEntityModelProvider(media.get());
	}
	
	public static synchronized MediaEntityBuilder createScreenCaptureFromPath(String path, String title) throws IOException {
        if (path == null || path.isEmpty())
            throw new IOException("ScreenCapture path cannot be null or empty.");
        
        return createScreenCapture(path, title, false);
    }
    
    public static synchronized MediaEntityBuilder createScreenCaptureFromPath(String path) throws IOException {
        return createScreenCaptureFromPath(path, null);
    }
    
    public static synchronized MediaEntityBuilder createScreenCaptureFromBase64String(String base64String) throws IOException {
        if (base64String == null || base64String.trim().equals(""))
            throw new IOException("Base64 string cannot be null or empty.");
        
        return createScreenCapture(base64String, null, true);
    }
    
    private static synchronized MediaEntityBuilder createScreenCapture(String pathOrBase64String, String title, boolean isBase64String) {
        ScreenCapture sc = new ScreenCapture();
        sc.setMediaType(MediaType.IMG);
        if (isBase64String)
            sc.setBase64String(pathOrBase64String);
        else
            sc.setPath(pathOrBase64String);
        
        if (title != null)
            sc.setName(title);

        media.set(sc);
        
        return getInstance();
    }
	
}

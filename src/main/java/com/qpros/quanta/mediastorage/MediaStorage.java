package com.qpros.quanta.mediastorage;

import java.io.IOException;

import com.qpros.quanta.model.Media;

public interface MediaStorage {

	void init(String v) throws IOException;
	
    void storeMedia(Media m) throws IOException;
    
}

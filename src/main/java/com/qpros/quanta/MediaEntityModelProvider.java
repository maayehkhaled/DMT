package com.qpros.quanta;

import com.qpros.quanta.model.Media;

/**
 * Media singleton
 *
 */
public class MediaEntityModelProvider {

	private Media m;
	
	public MediaEntityModelProvider(Media m) {
		this.m = m;
	}
	
	public Media getMedia() {
		return m;
	}

}

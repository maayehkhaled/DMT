package com.qpros.quanta.mediastorage;

import java.io.IOException;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.qpros.quanta.mediastorage.impl.HttpMediaManagerImplKlov;
import com.qpros.quanta.mediastorage.model.KlovMedia;
import com.qpros.quanta.model.BasicMongoReportElement;
import com.qpros.quanta.model.ScreenCapture;
import com.qpros.quanta.model.Test;
import com.qpros.quanta.utils.MongoUtil;

public class KlovMediaStorageHandler {

	private MediaStorage mediaStorage;
	private KlovMedia klovMedia;
	
	public KlovMediaStorageHandler(String url, KlovMedia klovMedia) throws IOException {
		if (url == null || url.isEmpty()) {
			throw new IllegalArgumentException("Invalid URL or resource not found");
		}
		this.klovMedia = klovMedia;
		this.mediaStorage = new HttpMediaManagerImplKlov();
		mediaStorage.init(url);
	}

	public void saveScreenCapture(BasicMongoReportElement el, ScreenCapture media) throws IOException {
    	Document doc = new Document("project", klovMedia.getProjectId())
                .append("report", klovMedia.getReportId())
                .append("sequence", media.getSequence())
                .append("mediaType", media.getMediaType().toString().toLowerCase())
                .append("test", media.getTestObjectId());

        if (el.getClass() != Test.class) {
            doc.append("log", el.getObjectId());
        } else {
            doc.append("testName", ((Test)el).getName());
        }
        
        klovMedia.getMediaCollection().insertOne(doc);
        ObjectId mediaId = MongoUtil.getId(doc);
        media.setObjectId(mediaId);
        
        media.setReportObjectId(klovMedia.getReportId());
        mediaStorage.storeMedia((ScreenCapture)media);
    }
	
}

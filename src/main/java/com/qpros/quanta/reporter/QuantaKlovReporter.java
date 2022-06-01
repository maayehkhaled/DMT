package com.qpros.quanta.reporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.qpros.quanta.model.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.qpros.quanta.ReportAggregates;
import com.qpros.quanta.ReportStatusStats;
import com.qpros.quanta.Status;
import com.qpros.quanta.SystemAttributeContext;
import com.qpros.quanta.TestAttributeTestContextProvider;
import com.qpros.quanta.externalconfig.model.Config;
import com.qpros.quanta.mediastorage.KlovMediaStorageHandler;
import com.qpros.quanta.mediastorage.model.KlovMedia;
import com.qpros.quanta.utils.IntUtils;
import com.qpros.quanta.utils.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * QuantaKlovReporter is a NoSQL database reporter (MongoDB), which updates information in
 * the database which is then used by the QuantaX server to display in-depth analysis. 
 */
public class QuantaKlovReporter
	extends AbstractReporter {

	private static final String DEFAULT_PROJECT_NAME_PROP = "klov.project.name";
	private static final String DEFAULT_REPORT_NAME_PROP = "klov.report.name";
	private static final String DEFAULT_MONGODB_HOST_PROP = "mongodb.host";
	private static final String DEFAULT_MONGODB_PORT_PROP = "mongodb.port";
	private static final String DEFAULT_MONGODB_URI_PROP = "mongodb.uri";
	private static final String DEFAULT_KLOV_HOST_PROP = "klov.host";
	private static final String DEFAULT_KLOV_PORT_PROP = "klov.port";
	private static final String REPORTER_NAME = "klov";
	private static final String DB_NAME = "klov";
    private static final String DEFAULT_PROJECT_NAME = "Default";
    
    private String url;
    
    private List<Test> testList;
    private ReportStatusStats stats;
    private SystemAttributeContext systemAttributeContext;
    private TestAttributeTestContextProvider<Category> categoryContext;
    private TestAttributeTestContextProvider<Author> authorContext;
    private TestAttributeTestContextProvider<Device> deviceContext;
    private Map<String, ObjectId> categoryNameObjectIdCollection = new HashMap<>();
    private Map<String, ObjectId> authorNameObjectIdCollection = new HashMap<>();
    private Map<String, ObjectId> deviceNameObjectIdCollection = new HashMap<>();
    private Map<String, ObjectId> exceptionNameObjectIdCollection = new HashMap<>();
    
    private ObjectId reportId;
    private String reportName;
    private ObjectId projectId;
    private String projectName;
    
    private MongoClient mongoClient;
    
    private MongoCollection<Document> projectCollection;
    private MongoCollection<Document> reportCollection;
    private MongoCollection<Document> testCollection;
    private MongoCollection<Document> logCollection;
    private MongoCollection<Document> exceptionCollection;
    private MongoCollection<Document> mediaCollection;
    private MongoCollection<Document> categoryCollection;
    private MongoCollection<Document> authorCollection;
    private MongoCollection<Document> deviceCollection;
    private MongoCollection<Document> environmentCollection;
    
    static {
        /* use mongodb reporting for only critical/severe events */
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);
    }

    /**
     * Initializes the KlovReporter
     */
    public QuantaKlovReporter() { }
    
    /**
     * Initializes the KlovReporter with project and report names
     * 
     * @param projectName Name of the project
     * @param reportName Name of the report
     */
    public QuantaKlovReporter(String projectName, String reportName) {
        this.projectName = projectName;
        this.reportName = reportName;
    }
    
    /**
     * Sets the project name
     * 
     * @param projectName Name of the project
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Sets the report name
     * 
     * @param reportName Name of the report
     */
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    /**
     * Initialize Mongo DB connection with host and default port: 27017
     * 
     * @param host host name
     * @return a {@link QuantaKlovReporter} object
     */
    public QuantaKlovReporter initMongoDbConnection(String host) {
        mongoClient = new MongoClient(host, 27017);
        return this;
    }
    
    /**
     * Initialize Mongo DB connection with host and {@link MongoClientOptions}
     * 
     * @param host host name
     * @param options {@link MongoClientOptions} options
     * @return a {@link QuantaKlovReporter} object
     */
    public QuantaKlovReporter initMongoDbConnection(String host, MongoClientOptions options) {
        mongoClient = new MongoClient(host, options);
        return this;
    }
    
    /**
     * Initialize Mongo DB connection with host and post
     * 
     * @param host host name
     * @param port port number
     * @return a {@link QuantaKlovReporter} object
     */
    public QuantaKlovReporter initMongoDbConnection(String host, int port) {
        mongoClient = new MongoClient(host, port);
        return this;
    }
    
    /**
     * Initialize Mongo DB connection with a {@link MongoClientURI}
     * 
     * @param uri {@link MongoClientURI} uri
     * @return a {@link QuantaKlovReporter} object
     */
    public QuantaKlovReporter initMongoDbConnection(MongoClientURI uri) {
        mongoClient = new MongoClient(uri);
        return this;
    }
    
    /**
     * Initializes the Mongo DB connection with {@link ServerAddress}
     *  
     * @param addr {@link ServerAddress} server address
     * @return a {@link QuantaKlovReporter} object
     */
    public QuantaKlovReporter initMongoDbConnection(ServerAddress addr) {
        mongoClient = new MongoClient(addr);
        return this;
    }
    
    /**
     * Initializes the Mongo DB connection with a list of {@link ServerAddress} 
     * addresses
     * 
     * @param seeds A list of {@link ServerAddress} server addresses
     * @return a {@link QuantaKlovReporter} object
     */
    public QuantaKlovReporter initMongoDbConnection(List<ServerAddress> seeds) {
        mongoClient = new MongoClient(seeds);
        return this;
    }
    
    /**
     * Initializes the Mongo DB connection with a list of {@link ServerAddress} 
     * and {@link MongoCredential}
     * 
     * @param seeds A list of {@link ServerAddress} server addresses
     * @param credentialsList A list of {@link MongoCredential} credentials
     * @return a {@link QuantaKlovReporter} object
     */
    public QuantaKlovReporter initMongoDbConnection(List<ServerAddress> seeds, List<MongoCredential> credentialsList) {
        mongoClient = new MongoClient(seeds, credentialsList);
        return this;
    }
    
    /**
     * Initializes the Mongo DB connection with a list of {@link ServerAddress}, 
     * {@link MongoCredential} and {@link MongoClientOptions}
     * 
     * @param seeds A list of {@link ServerAddress} server addresses
     * @param credentialsList A list of {@link MongoCredential} credentials
     * @param options {@link MongoClientOptions} options
     * @return a {@link QuantaKlovReporter} object
     */
    public QuantaKlovReporter initMongoDbConnection(List<ServerAddress> seeds, List<MongoCredential> credentialsList, MongoClientOptions options) {
        mongoClient = new MongoClient(seeds, credentialsList, options);
        return this;
    }
    
    /**
     * Initializes the Mongo DB connection with a list of {@link ServerAddress} 
     * and {@link MongoClientOptions}
     * 
     * @param seeds A list of {@link ServerAddress} server addresses
     * @param options {@link MongoClientOptions} options
     * @return a {@link QuantaKlovReporter} object
     */
    public QuantaKlovReporter initMongoDbConnection(List<ServerAddress> seeds, MongoClientOptions options) {
        mongoClient = new MongoClient(seeds, options);
        return this;
    }
    
    /**
     * Initializes the Mongo DB connection with {@link ServerAddress} and a list of 
     * {@link MongoCredential} credentials
     * 
     * @param addr {@link ServerAddress} server address
     * @param credentialsList A list of {@link MongoCredential} credentials
     * @return a {@link QuantaKlovReporter} object
     */
    public QuantaKlovReporter initMongoDbConnection(ServerAddress addr, List<MongoCredential> credentialsList) {
        mongoClient = new MongoClient(addr, credentialsList);
        return this;
    }
    
    /**
     * Initializes the Mongo DB connection with a list of {@link ServerAddress}, 
     * {@link MongoCredential} and {@link MongoClientOptions}
     * 
     * @param addr A list of {@link ServerAddress} server addresses
     * @param credentialsList A list of {@link MongoCredential} credentials
     * @param options {@link MongoClientOptions} options
     * @return a {@link QuantaKlovReporter} object
     */
    public QuantaKlovReporter initMongoDbConnection(ServerAddress addr, List<MongoCredential> credentialsList, MongoClientOptions options) {
        mongoClient = new MongoClient(addr, credentialsList, options);
        return this;
    }
    
    /**
     * Initializes the Mongo DB connection with a {@link ServerAddress} and 
     * {@link MongoClientOptions}
     * 
     * @param addr A list of {@link ServerAddress} server addresses
     * @param options {@link MongoClientOptions} options
     * @return a {@link QuantaKlovReporter} object
     */
    public QuantaKlovReporter initMongoDbConnection(ServerAddress addr, MongoClientOptions options) {
        mongoClient = new MongoClient(addr, options);
        return this;
    }
    
    /**
     * Initializes the Mongo DB connection with a connection url
     * 
     * @param url Url string
     * @return a {@link QuantaKlovReporter} object
     */
    public QuantaKlovReporter initKlovServerConnection(String url) {
        this.url = url;
        return this;
    }
    
    public void loadInitializationParams(String propertiesPath) throws FileNotFoundException {
    	File f = new File(propertiesPath);
    	InputStream stream = new FileInputStream(f);
    	loadConfig(stream);
    	loadInitializationParams();
    }
    
    public void loadInitializationParams(Properties props) {
    	loadConfig(props);
    	loadInitializationParams();
    }
    
    private void loadInitializationParams() {
    	List<Config> configList = getConfigContext().getConfigList();
    	
    	String mongoUri = getConfigValue(configList, DEFAULT_MONGODB_URI_PROP);
    	if (mongoUri == null || mongoUri.isEmpty()) {
        	String mongoHost = getConfigValue(configList, DEFAULT_MONGODB_HOST_PROP);
        	String mongoPort = getConfigValue(configList, DEFAULT_MONGODB_PORT_PROP);
        	int mongoPortInt = IntUtils.tryParseInt(mongoPort) == true ? Integer.valueOf(mongoPort) : -1;
        	if (mongoHost != null && mongoPortInt != -1) {
                initMongoDbConnection(mongoHost, mongoPortInt);
            } else if (mongoHost != null) {
                initMongoDbConnection(mongoHost);
            }
    	} else {
    	    initMongoDbConnection(new MongoClientURI(mongoUri));
    	}

    	String projectName = System.getProperty(DEFAULT_PROJECT_NAME_PROP);
    	projectName = projectName == null || projectName.isEmpty() ? getConfigValue(configList, DEFAULT_PROJECT_NAME_PROP) : projectName;
    	this.projectName = projectName == null || projectName.isEmpty() ? this.projectName : projectName;
    	
    	String reportName = System.getProperty(DEFAULT_REPORT_NAME_PROP);
    	reportName = reportName == null || reportName.isEmpty() ? getConfigValue(configList, DEFAULT_REPORT_NAME_PROP) : reportName;
        this.reportName = reportName == null || reportName.isEmpty() ? this.reportName : reportName;

        String klovHost = getConfigValue(configList, DEFAULT_KLOV_HOST_PROP);
		String klovPort = getConfigValue(configList, DEFAULT_KLOV_PORT_PROP);

		if (klovHost != null && klovPort != null) {
			String uri = klovHost + ":" + klovPort;
			initKlovServerConnection(uri);
		} else if (klovHost != null) {
			initKlovServerConnection(klovHost);
		} else { }
    }
    
    private String getConfigValue(List<Config> configList, String key) {
    	Config c = configList.stream()
			.filter(x -> x.getKey().equalsIgnoreCase(key))
			.findFirst()
			.get();
    	if (c != null)
    		return String.valueOf(c.getValue());
    	return null;
    }
    
    @Override
    public void start() {
        MongoDatabase db = mongoClient.getDatabase(DB_NAME);
        initCollections(db);
        setupProject();
    }
    
    private void initCollections(MongoDatabase db) {
    	projectCollection = db.getCollection("project");
        reportCollection = db.getCollection("report");
        testCollection = db.getCollection("test");
        logCollection = db.getCollection("log");
        exceptionCollection = db.getCollection("exception");
        mediaCollection = db.getCollection("media");
        categoryCollection = db.getCollection("category");
        authorCollection = db.getCollection("author");
        deviceCollection = db.getCollection("device");
        environmentCollection = db.getCollection("environment");
    }

    private void setupProject() {
        String projectName = this.projectName == null || this.projectName.isEmpty()
                ? DEFAULT_PROJECT_NAME
                : this.projectName;

        Document doc = new Document("name", projectName);
        Document project = projectCollection.find(doc).first();
        
        if (project != null) {
            projectId = project.getObjectId("_id");
        }
        else {
        	doc.append("createdAt", Calendar.getInstance().getTime());
            projectCollection.insertOne(doc);
            projectId = MongoUtil.getId(doc);
        }
        
        setupReport(projectName);
    }
    
    private void setupReport(String projectName) {
        String reportName =
                this.reportName == null || this.reportName.isEmpty()
                ? "Build " + Calendar.getInstance().getTimeInMillis()
                : this.reportName;
        
        Document doc = new Document("name", reportName)
                .append("startTime", getStartTime())
                .append("project", projectId)
                .append("projectName", projectName);

        reportCollection.insertOne(doc);
        reportId = MongoUtil.getId(doc);
    }
    
    @Override
    public void stop() {
        mongoClient.close();
    }
    
    @Override
    public synchronized void flush(ReportAggregates reportAggregates) {
    	setEndTime(Calendar.getInstance().getTime());
    	this.testList = reportAggregates.getTestList();
    	
        if (testList == null || testList.isEmpty()) {
            return;
        }
        
        this.authorContext = reportAggregates.getAuthorContext();
    	this.categoryContext = reportAggregates.getCategoryContext();
    	this.deviceContext = reportAggregates.getDeviceContext();
    	this.stats = reportAggregates.getReportStatusStats();
    	this.systemAttributeContext = reportAggregates.getSystemAttributeContext();

    	Status buildStatus = reportAggregates.getStatus();

        Document doc = new Document("endTime", getEndTime())
                        .append("duration", getRunDuration())
                        .append("status", String.valueOf(buildStatus))
                        .append("parentLength", stats.getParentCount())
                        .append("passParentLength", stats.getParentCountPass())
                        .append("failParentLength", stats.getParentCountFail())
                        .append("fatalParentLength", stats.getParentCountFatal())
                        .append("errorParentLength", stats.getParentCountError())
                        .append("warningParentLength", stats.getParentCountWarning())
                        .append("skipParentLength", stats.getParentCountSkip())
                        .append("exceptionsParentLength", stats.getChildCountExceptions())
                        .append("childLength", stats.getChildCount())
                        .append("passChildLength", stats.getChildCountPass())
                        .append("failChildLength", stats.getChildCountFail())
                        .append("fatalChildLength", stats.getChildCountFatal())
                        .append("errorChildLength", stats.getChildCountError())
                        .append("warningChildLength", stats.getChildCountWarning())
                        .append("skipChildLength", stats.getChildCountSkip())
                        .append("infoChildLength", stats.getChildCountInfo())
                        .append("exceptionsChildLength", stats.getChildCountExceptions())                        
                        .append("grandChildLength", stats.getGrandChildCount())
                        .append("passGrandChildLength", stats.getGrandChildCountPass())
                        .append("failGrandChildLength", stats.getGrandChildCountFail())
                        .append("fatalGrandChildLength", stats.getGrandChildCountFatal())
                        .append("errorGrandChildLength", stats.getGrandChildCountError())
                        .append("warningGrandChildLength", stats.getGrandChildCountWarning())
                        .append("skipGrandChildLength", stats.getGrandChildCountSkip())
                        .append("exceptionsGrandChildLength", stats.getGrandChildCountExceptions())
                        .append("analysisStrategy", String.valueOf(getAnalysisStrategy()));
        
        reportCollection.updateOne(
                new Document("_id", reportId), 
                new Document("$set", doc));
        
        insertUpdateSystemAttribute();
    }

    public List<ObjectId> getCollectionValues(Map<String, ObjectId> collection) {
    	if (collection == null || collection.isEmpty())
    		return null;
    	return collection.entrySet()
			.stream()
	        .map(Map.Entry::getValue)
	        .collect(Collectors.toList());
    }
    
    private void insertUpdateSystemAttribute() {        
        List<SystemAttribute> systemAttrList = systemAttributeContext.getSystemAttributeList();
        Document doc;
        
        for (SystemAttribute attr : systemAttrList) {
            doc = new Document("project", projectId)
                    .append("report", reportId)
                    .append("name", attr.getName());
            
            Document envSingle = environmentCollection.find(doc).first();
            
            if (envSingle == null) {
                doc.append("value", attr.getValue());
                environmentCollection.insertOne(doc);
            } else {
                ObjectId id = envSingle.getObjectId("_id");
                doc = new Document("_id", id)
                        .append("value", attr.getValue());
                environmentCollection.updateOne(
                        new Document("_id", id),
                        new Document("$set", doc));
            }
        }
    }
    
    @Override
    public void onTestStarted(Test test) {
        onTestStartedHelper(test);
    }
    
    @Override
    public synchronized void onNodeStarted(Test node) {
        onTestStartedHelper(node);
    }
    
    private void onTestStartedHelper(Test test) {
    	Document doc = new Document("project", projectId)
                .append("report", reportId)
                .append("reportName", reportName)
                .append("level", test.getLevel())
                .append("name", test.getName())
                .append("status", test.getStatus().toString())
                .append("description", test.getDescription())
                .append("startTime", test.getStartTime())
                .append("endTime", test.getEndTime())
                .append("bdd", test.isBehaviorDrivenType())
                .append("leaf", test.getNodeContext().size()==0)
                .append("childNodesLength", test.getNodeContext().size());
        
        if (test.isBehaviorDrivenType()) {
            doc.append("bddType", test.getBehaviorDrivenType().getSimpleName());
        }
        
        if (test.getParent() != null) {
        	doc.append("parent", test.getParent().getObjectId())
            	.append("parentName", test.getParent().getName());
            updateTestChildrenCount(test.getParent());
            updateTestDesc(test.getParent());
        }
        
        testCollection.insertOne(doc);
        ObjectId testId = MongoUtil.getId(doc);
        test.setObjectId(testId);
    }
    
    private void updateTestDesc(Test test) {
        Document doc = new Document("description", test.getDescription());
        testCollection.updateOne(
                new Document("_id", test.getObjectId()),
                new Document("$set", doc));
    }
    
    private void updateTestChildrenCount(Test test) {
        Document doc = new Document("childNodesLength", test.getNodeContext().size());
        testCollection.updateOne(
                new Document("_id", test.getObjectId()),
                new Document("$set", doc));
    }

    @Override
    public synchronized void onLogAdded(Test test, Log log) {
    	Document doc = new Document("test", test.getObjectId())
                .append("project", projectId)
                .append("report", reportId)
                .append("testName", test.getName())
                .append("sequence", log.getSequence())
                .append("status", log.getStatus().toString())
                .append("timestamp", log.getTimestamp())
                .append("details", log.getDetails());
		
		if (log.hasScreenCapture() && log.getScreenCaptureContext().getFirst().isBase64()) {
            doc.append("details", log.getDetails() + log.getScreenCaptureContext().getFirst().getSource());
        }
		
        logCollection.insertOne(doc);
        
        ObjectId logId = MongoUtil.getId(doc);
        log.setObjectId(logId);
        
        // check for exceptions..
        if (test.hasException()) {
            if (exceptionNameObjectIdCollection == null)
                exceptionNameObjectIdCollection = new HashMap<>();
            
            ExceptionInfo ex = test.getExceptionInfoList().get(0);
            
            ObjectId exceptionId;
            doc = new Document("report", reportId)
                    .append("project", projectId)
                    .append("name", ex.getExceptionName());
                    
            FindIterable<Document> iterable = exceptionCollection.find(doc);
            Document docException = iterable.first();
            
            // check if a matching exception name is available in 'Exception' collection (MongoDB)
            // if a matching exception name is found, associate with this exception's ObjectId
            if (!exceptionNameObjectIdCollection.containsKey(ex.getExceptionName())) {               
                if (docException != null) {
                    exceptionNameObjectIdCollection.put(ex.getExceptionName(), docException.getObjectId("_id"));
                } else {
                    doc = new Document("project", projectId)
                            .append("report", reportId)
                            .append("name", ex.getExceptionName())
                            .append("stacktrace", ex.getStackTrace())
                            .append("testCount", 0);
                    
                    exceptionCollection.insertOne(doc);
                    
                    exceptionId = MongoUtil.getId(doc);
                    docException = exceptionCollection.find(new Document("_id", exceptionId)).first();
                    
                    exceptionNameObjectIdCollection.put(ex.getExceptionName(), exceptionId);
                }
            }

            Integer testCount = ((Integer) (docException.get("testCount"))) + 1;
            doc = new Document("testCount", testCount);
            
            exceptionCollection.updateOne(
                    new Document("_id", docException.getObjectId("_id")),
                    new Document("$set", doc));
            
            doc = new Document("exception", exceptionNameObjectIdCollection.get(ex.getExceptionName()));
            
            testCollection.updateOne(
                    new Document("_id", test.getObjectId()),
                    new Document("$set", doc));
            updateTestDesc(test);
        }
        
        endTestRecursive(test);
    }
    
    private void endTestRecursive(Test test) {
        Document doc = new Document("status", test.getStatus().toString())
                .append("endTime", test.getEndTime())
                .append("duration", test.getRunDurationMillis())
                .append("leaf", test.getNodeContext().size()==0)
                .append("childNodesLength", test.getNodeContext().size())
                .append("categorized", test.hasCategory())
                .append("description", test.getDescription());
        
        testCollection.updateOne(
                new Document("_id", test.getObjectId()),
                new Document("$set", doc));

        if (test.getLevel() > 0) {
            endTestRecursive(test.getParent());
        }
    }
    
    public <T extends TestAttribute> void assignAttribute(Test test, TestAttribute attribute, Map<String, ObjectId> nameObjectIdCollection, 
    		MongoCollection<Document> mongoCollection, TestAttributeTestContextProvider<T> attributeContext) {
    }
    
    @Override
    public void onCategoryAssigned(Test test, Category category) {
    	assignAttribute(test, category, categoryNameObjectIdCollection, categoryCollection, categoryContext);
    }

    @Override public void onSubCategoryAssigned(Test test, SubCategory subCategory) {

    }



    @Override
    public void onAuthorAssigned(Test test, Author author) { 
    	assignAttribute(test, author, authorNameObjectIdCollection, authorCollection, authorContext);
    }
    
    private KlovMediaStorageHandler mediaStorageHandler;
    
    @Override
    public void onScreenCaptureAdded(Test test, ScreenCapture screenCapture) throws IOException {
    	saveScreenCapture(test, screenCapture);
    }
    
    @Override
    public void onScreenCaptureAdded(Log log, ScreenCapture screenCapture) throws IOException {
    	screenCapture.setLogObjectId(log.getObjectId());
        saveScreenCapture(log, screenCapture);
    }
    
    private void saveScreenCapture(BasicMongoReportElement el, ScreenCapture screenCapture) throws IOException {
    	if (mediaStorageHandler == null) {
    		KlovMedia klovMedia = new KlovMedia(projectId, reportId, mediaCollection);
    		mediaStorageHandler = new KlovMediaStorageHandler(url, klovMedia);
    	}
    	mediaStorageHandler.saveScreenCapture(el, screenCapture);
    }
    
    @Override
    public void onScreencastAdded(Test test, Screencast screencast) throws IOException { }

    /**
     * Returns the active Project ID
     * 
     * @return A {@link ObjectId} object
     */
    public ObjectId getProjectId() {       
        return projectId;       
    }      
    
    /**
     * Returns the active Report ID 
     * 
     * @return A {@link ObjectId} object
     */
    public ObjectId getReportId() {     
        return reportId;        
    }

	@Override
	public void onTestRemoved(Test test) { }

	@Override
	public void onDeviceAssigned(Test test, Device device) {
		assignAttribute(test, device, deviceNameObjectIdCollection, deviceCollection, deviceContext);
	}

	@Override
	public String getReporterName() {
		return REPORTER_NAME;
	}
	
}

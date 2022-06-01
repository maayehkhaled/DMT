package com.qpros.quanta.model;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.qpros.quanta.QuantaReports;
import org.bson.types.ObjectId;

import com.qpros.quanta.RunResult;
import com.qpros.quanta.Status;
import com.qpros.quanta.gherkin.model.IGherkinFormatterModel;

public class Test 
	implements BasicMongoReportElement, RunResult, Serializable {

    private static final long serialVersionUID = 5590943223572254960L;
    private static final AtomicInteger atomicInt = new AtomicInteger(0);
    
    /**
     * An instance of {@link QuantaReports}
     */
    private transient QuantaReports Quanta;
    
    /**
     * Level describes the hierarchy of the test in the tree. A level 0 indicates a parent
     * test. Level 1 indicates an immediate child of the parent, level 2 indicates an immediate
     * child of the Child and so on.. The bottom-most test in the hierarchy is considered
     * a leaf
     */
    private int level = 0;
    
    /**
     * A unique ID, generated by AtomicInteger
     */
    private int testId = atomicInt.incrementAndGet();    
    
    /**
     * If this Test is at the top-most level, or in other words, has a level value of 0, parent
     * will be null. This field will only contain a value if the Test is a child, or has a level
     * 1 or greater.
     */
    private Test parent;
    
    /**
     * {@link Status} of this test, defaults to PASS
     */
    private Status status = Status.PASS;
    
    /**
     * A structure containing all nodes of this test
     */
    private AbstractStructure<Test> node;
    
    /**
     * A structure containing all logs/events of this test
     */
    private AbstractStructure<Log> log;
    
    /**
     * A structure containing all categories assigned to this test
     */
    private AbstractStructure<TestAttribute> category; /**
     * A structure containing all categories assigned to this test
     */
    private AbstractStructure<TestAttribute> subCategory;

    /**
     * A structure containing all authors assigned to this test
     */
    private AbstractStructure<TestAttribute> author;
    
    /**
     * A structure containing all devices assigned to this test
     */
    private AbstractStructure<TestAttribute> device;
    
    /**
     * Time the test was started 
     */
    private Date startTime = Calendar.getInstance().getTime();
    
    /**
     * Time the test was ended
     */
    private Date endTime = Calendar.getInstance().getTime();
    
    /**
     * An assigned {@link ObjectId}  
     */
    private ObjectId mongoId;
    
    /**
     * Type of BDD object represented by {@link IGherkinFormatterModel}. It can have one
     * of the following values:
     * 
     * <ul>
     *  <li>{@link com.qpros.quanta.gherkin.model.Feature}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Background}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Scenario}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Given}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.When}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.Then}</li>
     *  <li>{@link com.qpros.quanta.gherkin.model.And}</li>
     * </ul>
     */
    private Class<? extends IGherkinFormatterModel> bddType;
    
    /**
     * A list of {@link ScreenCapture}
     */
    private transient List<ScreenCapture> screenCaptureList;
    
    /**
     * A list of {@link Screencast}
     */
    private transient List<Screencast> screencastList;
    
    /**
     * A list of {@link ExceptionInfo}
     */
    private transient List<ExceptionInfo> exceptionList;
    
    /**
     * Name of the test
     */
    private String name;
    
    /**
     * Hierarchical name of the test 
     * 
     * <p>
     * Note: This field will equal the name if the test is the top-most level.
     * 
     * <p>
     * For tests having level 1 or greater, the hierarchical name would be separated by
     * a dot character as:
     * 
     * <code>Test.Child.GrandChild</code>
     */
    private String hierarchicalName;
    
    /**
     * Description
     */
    private String description;
    
    /**
     * Indicates if the test has ended
     */
    private boolean ended = false;
    
    /**
     * This setting allows setting test with your own time-stamps. With this enabled, Quanta 
     * does not use time-stamps for tests at the time they were created
     */
    private boolean usesManualConfiguration = false;
    
    
    // if used via listener, allow manual configuration of model
    public void setUseManualConfiguration(boolean b) {
        this.usesManualConfiguration = b;
    }
    
    public QuantaReports getQuantaInstance() {
        return Quanta;
    }
    
    public void setQuantaInstance(QuantaReports Quanta) {
        this.Quanta = Quanta;
    }

    public boolean isChildNode() {
        return level > 0;
    }
    
    public int getLevel() {
        return level;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }

   
    public void setParent(Test parent) {
        this.parent = parent;
    }
    
    public Test getParent() { return parent; }

    public AbstractStructure<Test> getNodeContext() {
        if (node == null) {
            node = new AbstractStructure<>();
        }

        return node;
    }
    
    public boolean hasChildren() {
        return node != null && node.getAll() != null && node.getAll().size() > 0;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
    private void setEndTimeFromChildren() {
        if (hasChildren()) {
            setStartTime(getNodeContext().getFirst().getStartTime());
            setEndTime(getNodeContext().getLast().getEndTime());
        } else if (hasLog()) {
            Date lastLogEndTime = getLogContext().getLast().getTimestamp(); 
            setEndTime(lastLogEndTime);
        }
    }

    public Date getEndTime() {
        return endTime;
    }

    public boolean hasEnded() {
        return ended;
    }
    
    public String getRunDuration() {
        long diff = endTime.getTime() - startTime.getTime();
        
        long secs = diff / 1000;
        long millis = diff % 1000;
        long mins = secs / 60;
        secs = secs % 60;
        long hours = mins / 60;
        mins = mins % 60;
        
        return new DecimalFormat("00").format(hours) + ":" +new DecimalFormat("00").format( mins) + ":" + new DecimalFormat("00").format(secs) + ":" + new DecimalFormat("00").format(millis);
    }
    
    public Long getRunDurationMillis() {
    	return endTime.getTime() - startTime.getTime();
    }

    // default status when the test starts
    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void trackLastRunStatus() {
        getLogContext().getAll().forEach(x -> updateStatus(x.getStatus()));

        status = (status == Status.INFO || status == Status.DEBUG)
                ? Status.PASS
                : status;
    }
    
    private synchronized void updateStatus(Status logStatus) {
        int logStatusIndex = Status.getStatusHierarchy().indexOf(logStatus);        
        int testStatusIndex = Status.getStatusHierarchy().indexOf(status);
        
        status = logStatusIndex < testStatusIndex ? logStatus : status;
    }

    public void end() {
        updateTestStatusRecursive(this);
        endChildrenRecursive(this);
        
        status = (status == Status.INFO || status == Status.DEBUG)
                ? Status.PASS
                : status;
        
        if (!usesManualConfiguration)
            setEndTimeFromChildren();
    }

    private synchronized void updateTestStatusRecursive(Test test) {
        test.getLogContext().getAll().forEach(x -> updateStatus(x.getStatus()));

        if (test.hasChildren()) {
            test.getNodeContext().getAll().forEach(this::updateTestStatusRecursive);
        }
        
        // if not all children are marked SKIP, then:
        // ensure the parent has a status that is not SKIP
        if (!test.isBehaviorDrivenType()) {
        	boolean hasNodeNotSkipped = test.getNodeContext().getAll()
        		.stream()
        		.anyMatch(x -> x.getStatus() != Status.SKIP);
	        
	        if (status == Status.SKIP && hasNodeNotSkipped) {
	            // reset status
	            status = Status.PASS;
	            // compute new status
	            test.getNodeContext().getAll()
	            	.stream()
            		.filter(x -> x.getStatus() != Status.SKIP)
            		.forEach(this::updateTestStatusRecursive);
	        }
        }
    }
    
    private void endChildrenRecursive(Test test) {
        test.getNodeContext().getAll().forEach(Test::end);
    }

    public AbstractStructure<Log> getLogContext() {
        if (log == null) {
            log = new AbstractStructure<>();
        }
        return log;
    }
    
    public boolean hasLog() {
        return log != null && !log.isEmpty();
    }
    
    public void setDescription(String description) { 
        this.description = description; 
    }

    public String getDescription() { return description; }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() { 
    	return name; 
	}

    public String getHierarchicalName() {
    	hierarchicalName = parent == null 
    			? name
				: String.join(".", parent.getHierarchicalName(), getName());
    	return hierarchicalName; 
	}
    
    public boolean hasAttributes() {
        return hasAuthor() || hasCategory() || hasDevice();
    }
    
    public AbstractStructure<TestAttribute> getCategoryContext() {
        if (category == null) {
            category = new AbstractStructure<>();
        }
        return category;
    }
    public AbstractStructure<TestAttribute> getSubCategoryContext() {
        if (subCategory == null) {
            subCategory = new AbstractStructure<>();
        }
        return subCategory;
    }
    
    public boolean hasCategory(String name,String value) {
    	return hasCategory() 
    			&& category.getAll()
    				.stream()
    				.anyMatch(x -> x.getName().equals(name));
    }
    public boolean hasSubCategory(String name,String value) {
        return hasCategory()
                        && subCategory.getAll()
                        .stream()
                        .anyMatch(x -> x.getName().equals(name));
    }
    
    public boolean hasCategoryNode(String name,String value) {
    	return getNodeContext().getAll()
				.stream()
				.anyMatch(x -> x.hasCategory(name,value));
    }
    
    public boolean hasCategory() {
        return category != null && !category.isEmpty();
    }
    public boolean hasSubCategory() {
        return subCategory != null && !subCategory.isEmpty();
    }
    
    public void setCategory(TestAttribute category) {
        getCategoryContext().add(category);
    }
    public void setSubCategory(TestAttribute subcategory) {
        getSubCategoryContext().add(subcategory);
    }
    public TestAttribute getCategory(Integer index) {
        if (hasCategory() && index < category.size()) {
            return category.get(index);
        }
        return null;
    }
    public TestAttribute getSubCategory(Integer index) {
        if (hasSubCategory() && index < subCategory.size()) {
            return subCategory.get(index);
        }
        return null;
    }
    
    public AbstractStructure<TestAttribute> getAuthorContext() {
        if (author == null) {
            author = new AbstractStructure<>();
        }
        return author;
    }
    
    public boolean hasAuthor() {
        return author != null && !author.isEmpty();
    }
    
    public void setAuthor(TestAttribute author) {
        getAuthorContext().add(author);
    }
    
    public TestAttribute getAuthor(Integer index) {
        if (hasAuthor() && index < author.size()) {
            return author.get(index);
        }
        return null;
    }
    
    public AbstractStructure<TestAttribute> getDeviceContext() {
        if (device == null) {
            device = new AbstractStructure<>();
        }
        return device;
    }
    
    public boolean hasDevice() {
    	return device != null && !device.isEmpty();
    }
    
    public void setDevice(TestAttribute device) {
    	getDeviceContext().add(device);
    }
    
    public TestAttribute getDevice(Integer index) {
    	if (hasDevice() && index < author.size()) {
    		return device.get(index);
    	}
    	return null;
    }

    public void setExceptionInfo(ExceptionInfo exceptionInfo) {
        if (exceptionList == null) {
            exceptionList = new ArrayList<>();
        }
        exceptionList.add(exceptionInfo);
    }
    
    public List<ExceptionInfo> getExceptionInfoList() { 
    	return exceptionList; 
	}
    
    public boolean hasException() {
        return exceptionList != null && !exceptionList.isEmpty();
    }
    
    public boolean hasMedia() {
        return screenCaptureList != null 
                && !screenCaptureList.isEmpty()
                && screencastList != null
                && !screencastList.isEmpty();
    }
    
    public void setScreenCapture(ScreenCapture screenCapture) {
        if (screenCaptureList == null) {
            screenCaptureList = new ArrayList<>();
        }
        screenCaptureList.add(screenCapture);
    }
    
    public List<ScreenCapture> getScreenCaptureList() { 
    	return screenCaptureList; 
	}
    
    public Boolean hasScreenCapture() {
    	return screenCaptureList != null && !screenCaptureList.isEmpty();
    }
    
    public void setScreencast(Screencast screencast) {
        if (screencastList == null) {
            screencastList = new ArrayList<>();
        }
        screencastList.add(screencast);
    }
    
    public List<Screencast> getScreencastList() { 
    	return screencastList; 
	}
    
    public boolean isBehaviorDrivenType() { 
    	return bddType != null; 
	}
    
    public void setBehaviorDrivenType(IGherkinFormatterModel type) { 
    	bddType = type.getClass(); 
	}
    
    public void setBehaviorDrivenType(Class<? extends IGherkinFormatterModel> type) { 
    	bddType = type; 
	}
    
    public Class<? extends IGherkinFormatterModel> getBehaviorDrivenType() { 
    	return bddType; 
	}
    
    public String getBehaviorDrivenTypeName() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    	Method method = bddType.getMethod("getGherkinName");
    	Object o = method.invoke(null, (Object[]) null);
    	return o.toString();
    }
        
    void setID(int id) { 
    	testId = id; 
	}
    
    public int getID() { 
    	return testId; 
	}
    
    public void setObjectId(ObjectId id) { 
    	mongoId = id; 
	}
    
    public ObjectId getObjectId() { 
    	return mongoId; 
	}
}

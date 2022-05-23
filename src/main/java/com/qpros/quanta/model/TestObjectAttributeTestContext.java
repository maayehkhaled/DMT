package com.qpros.quanta.model;

import com.qpros.quanta.Status;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestObjectAttributeTestContext<T extends TestObjectAttribute>
	implements Serializable {

    private static final long serialVersionUID = 2595632998970711190L;

    private T testObjectAttribute;
    private List<Test> testList = new ArrayList<>();
    private int passed = 0;
    private int failed = 0;
    private int skip = 0;
    private int others = 0;

    public TestObjectAttributeTestContext(T testObjectAttribute) {
    	this.testObjectAttribute = testObjectAttribute;
	}
    
    public void setTest(Test test) {
        updateTestStatusCounts(test);        
        testList.add(test);
    }
    
    private void updateTestStatusCounts(Test test) {
        passed += test.getStatus() == Status.PASS ? 1 : 0;
        failed += test.getStatus() == Status.FAIL || test.getStatus() == Status.FATAL ? 1 : 0;
        skip += test.getStatus() == Status.SKIP ? 1 : 0;
        others += test.getStatus() != Status.PASS 
        		&& test.getStatus() != Status.FATAL 
        		&& test.getStatus() != Status.FAIL 
        		&& test.getStatus() != Status.SKIP? 1 : 0;
    }
    
    public void refreshTestStatusCounts() {
        passed = 0;
        failed = 0;
        skip = 0;
        others = 0;
        testList.forEach(this::updateTestStatusCounts);
    }
    
    public List<Test> getTestList() { 
    	return testList; 
	}
    
    public String getName() { 
    	return testObjectAttribute.getName();
	}
    
    public int getPassed() { 
    	return passed; 
	}
    
    public int getFailed() { 
    	return failed; 
	}
    
    public int getSkipped() {
    	return skip;
    }
    
    public int getOthers() { 
    	return others; 
	}
    
    public int size() { 
    	return testList == null ? 0 : testList.size(); 
	}
    
    public boolean isEmpty() {
    	return size() == 0;
    }
    
    public T getAttribute() { 
    	return testObjectAttribute;
	}
}


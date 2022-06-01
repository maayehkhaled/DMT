package com.qpros.quanta;

import com.qpros.quanta.model.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * Uses an attribute context for {@link TestObjectAttribute} (SubCategory, SubDevice, SubAuthor etc.)
 * and tracks the collection of tests segregated by the type {@link TestObjectAttribute}
 *
 * @param <T> A {@link TestObjectAttribute} type
 */
public class TestObjectAttributeTestContextProvider<T extends TestObjectAttribute> {

    private List<TestObjectAttributeTestContext<T>> testAttrCollection;

    public TestObjectAttributeTestContextProvider() {
        testAttrCollection = new ArrayList<>();
    }
    
    public void setAttributeContext(T attr, Test test) {
        Optional<TestObjectAttributeTestContext<T>> testOptionalTestContext = testAttrCollection
                .stream()
                .filter(x -> x.getName().equals(attr.getName()))
                .findFirst();
        
        if (testOptionalTestContext.isPresent()) {
            List<Test> testList = testOptionalTestContext.get().getTestList();
            
            boolean b = testList
                    .stream()
                    .anyMatch(t -> t.getID() == test.getID());
            
            if (!b) {
                testOptionalTestContext.get().setTest(test);
            }
            testOptionalTestContext.get().refreshTestStatusCounts();
        }
        else {
            TestObjectAttributeTestContext<T> testAttrContext = new TestObjectAttributeTestContext<>(attr);
            testAttrContext.setTest(test);
            testAttrCollection.add(testAttrContext);
        }
    }
    
    public void removeTest(Test test) {
		Iterator<TestObjectAttributeTestContext<T>> iter = testAttrCollection.iterator();
		while (iter.hasNext()) {
            TestObjectAttributeTestContext<T> context = iter.next();
			TestRemover.remove(context.getTestList(), test);
			if (context.isEmpty()) {
				iter.remove();
			}
    	}
    }
    
    public List<TestObjectAttributeTestContext<T>> getTestAttributeTestContextList() {
        return testAttrCollection;
    }
    
}

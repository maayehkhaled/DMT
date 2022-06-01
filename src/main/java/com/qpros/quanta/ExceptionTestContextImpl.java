package com.qpros.quanta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.qpros.quanta.model.ExceptionInfo;
import com.qpros.quanta.model.ExceptionTestContext;
import com.qpros.quanta.model.Test;

/**
 * Provides and tracks the collection of tests segregated by the type of {@link Exception}
 * 
 */
public class ExceptionTestContextImpl {
    private List<ExceptionTestContext> exTestContextList;
    
    public ExceptionTestContextImpl() { 
        exTestContextList = new ArrayList<>();
    }
    
    public void setExceptionContext(ExceptionInfo ei, Test test) {
        Optional<ExceptionTestContext> exOptionalTestContext = exTestContextList
                .stream()
                .filter(x -> x.getExceptionInfo().getExceptionName().equals(ei.getExceptionName()))
                .findFirst();
        
        if (exOptionalTestContext.isPresent()) {
            List<Test> testList = exOptionalTestContext.get().getTestList();
            
            boolean b = testList.stream()
                    .anyMatch(t -> t.getID() == test.getID());
            
            if (!b) {
                exOptionalTestContext.get().setTest(test);
            }
        }
        else {
            ExceptionTestContext exTestContext = new ExceptionTestContext(ei);
            exTestContext.setTest(test);
            exTestContextList.add(exTestContext);
        }
    }

    public List<ExceptionTestContext> getExceptionTestContextList() { 
        return exTestContextList; 
    }
}

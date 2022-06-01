package com.qpros.quanta.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Suite implements Serializable {
    private static final long serialVersionUID = 5590943333572254960L;
    private static final AtomicInteger atomicInt = new AtomicInteger(0);
    private String name;
    private long id=atomicInt.getAndIncrement();
    private long[] testIds;
    private List<Suite> subSuite= new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long[] getTestIds() {
        return testIds;
    }

    public void setTestIds(long[] testIds) {
        this.testIds = testIds;
    }

    public List<Suite> getSubSuite() {
        return subSuite;
    }

    public void setSubSuite(List<Suite> subSuite) {
        this.subSuite = subSuite;
    }


    public boolean hasSubSuite(){
        if(subSuite.isEmpty()){
            return false;
        }else {
            return true;
        }
    }
}

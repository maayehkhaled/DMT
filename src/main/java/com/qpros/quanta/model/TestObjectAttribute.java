package com.qpros.quanta.model;

import java.io.Serializable;

public abstract class TestObjectAttribute
	extends Attribute
	implements Serializable {

    static final long serialVersionUID = 1010210091204302766L;

    public TestObjectAttribute(String k) {
        super(k);
    }
    
    public String getName() {
        return getKey();
    }
    
    public void setName(String name) {
        setKey(name);
    }
    
    public String getDescription() {
        return getValue();
    }
    
    public void setDescription(String description) {
        setValue(description);
    }
    
}

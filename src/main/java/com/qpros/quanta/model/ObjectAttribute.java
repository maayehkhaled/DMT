package com.qpros.quanta.model;

import java.io.Serializable;

abstract class ObjectAttribute
	implements Serializable {

    private static final long serialVersionUID = 6491172989326625178L;

    private Object k;
    private Object v;

    public ObjectAttribute(Object k, Object v) {
        this.k = k;
        this.v = v;
    }

    public ObjectAttribute(Object k) {
        this(k, null);
    }

    protected Object getKey() {
        return k;
    }
    
    protected void setKey(Object k) {
        this.k = k;
    }
    
    public Object getValue() {
        return v;
    }
    
    public void setValue(Object v) {
        this.v = v;
    }

}

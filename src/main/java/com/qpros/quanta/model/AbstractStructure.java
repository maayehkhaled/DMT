package com.qpros.quanta.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class AbstractStructure<T> implements Serializable {

    private static final long serialVersionUID = -2630417398255980331L;
    
    private transient List<T> list;

    AbstractStructure() {
        list = Collections.synchronizedList(new ArrayList<>());
    }
    
    public void add(T t) {
        list.add(t);
    }

    public T get(int x) {
        return list.get(x);
    }
    
    public T getFirst() {
        return list.isEmpty() ? null : list.get(0);
    }
    
    public T getLast() {
        return list.isEmpty() ? null : list.get(list.size()-1);
    }

    public List<T> getAll() {
        return list;
    }
    
    public int size() {
        if (list == null)
            return 0;
        return list.size();
    }
    
    public boolean isEmpty() {
    	return size() == 0;
    }

    public TIterator getIterator() { return new TIterator(); }

    private class TIterator implements Iterator<T> {
        private int index;

        TIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return list != null && list.size() >= index + 1;
        }

        @Override
        public T next() {
            if (hasNext()) {
                return list.get(index++);
            }

            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            list.remove(index);
        }
    }
    
}

package com.qpros.quanta.model;

import java.util.ArrayList;
import java.util.List;

public class Category
	extends TestAttribute {
    public List<SubCategory> subCategoryList= new ArrayList<>();

    /**
     * Level describes the hierarchy of the test in the tree. A level 0 indicates a parent
     * test. Level 1 indicates an immediate child of the parent, level 2 indicates an immediate
     * child of the Child and so on.. The bottom-most test in the hierarchy is considered
     * a leaf
     */

    private static final long serialVersionUID = -7850780488330456977L;

    public Category(String k) {
        super(k);
    }
    
}

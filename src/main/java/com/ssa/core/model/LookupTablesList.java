package com.ssa.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class LookupTablesList {
    @JsonProperty("LookupTableName")
    public String lookupTableName;
    @JsonProperty("LookupValueList")
    public ArrayList<LookupValueList> lookupValueList;

}

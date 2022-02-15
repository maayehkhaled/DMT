package com.ssa.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SSALookupModel {
    @JsonProperty("Service")
    public String service;
    @JsonProperty("Stage")
    public String stage;
    @JsonProperty("LookupTable")
    public String lookupTable;
}

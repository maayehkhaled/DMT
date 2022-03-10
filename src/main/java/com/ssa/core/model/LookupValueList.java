package com.ssa.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LookupValueList {
    @JsonProperty("ValueId")
    public int valueId;
    @JsonProperty("NameEN")
    public String nameEN;
    @JsonProperty("NameAR")
    public String nameAR;
}

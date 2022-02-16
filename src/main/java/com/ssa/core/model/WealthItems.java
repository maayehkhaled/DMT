package com.ssa.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WealthItems {
    @JsonProperty("EmiratesId")
    public String emiratesId;
    @JsonProperty("WealthType")
    public String wealthType;
}

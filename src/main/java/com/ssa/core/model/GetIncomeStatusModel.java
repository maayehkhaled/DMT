package com.ssa.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetIncomeStatusModel {
    @JsonProperty("EmiratesId")
    public String emiratesId;
    @JsonProperty("IncomeType")
    public String incomeType;
}

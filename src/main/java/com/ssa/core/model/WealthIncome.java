package com.ssa.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class WealthIncome {
    @JsonProperty("EmiratesId")
    public String emiratesId;
    @JsonProperty("WealthType")
    public String wealthType;
    @JsonProperty("WealthDescription")
    public String wealthDescription;
    @JsonProperty("IsBusinessActive")
    public boolean isBusinessActive;
    @JsonProperty("IncomeAmount")
    public int incomeAmount;
    @JsonProperty("IncomeFrequency")
    public String incomeFrequency;
    @JsonProperty("AttachmentList")
    public List<AttachmentList> attachmentList;
}

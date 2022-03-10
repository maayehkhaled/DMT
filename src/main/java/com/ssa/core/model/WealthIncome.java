package com.ssa.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

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
    public double incomeAmount;
    @JsonProperty("IncomeFrequency")
    public String incomeFrequency;
    @JsonProperty("AttachmentList")
    public ArrayList<AttachmentList> attachmentList;
}

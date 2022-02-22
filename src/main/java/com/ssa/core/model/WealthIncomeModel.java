package com.ssa.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class WealthIncomeModel {

    @JsonProperty("EmiratesId")
    public String emiratesId;
    @JsonProperty("WealthIncome")
    public List<WealthIncome> wealthIncome;

    public class AttachmentList{
        @JsonProperty("BinaryFile")
        public String binaryFile;
        @JsonProperty("ContentType")
        public String contentType;
        @JsonProperty("Filename")
        public String filename;
    }

    public class WealthIncome{
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
}

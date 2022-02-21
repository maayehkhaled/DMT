package com.ssa.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SelfDeclaredIncomeModel {
    public class AttachmentList{
        @JsonProperty("BinaryFile")
        public String binaryFile;
        @JsonProperty("ContentType")
        public String contentType;
        @JsonProperty("Filename")
        public String filename;
    }

    public class SelfDeclaredIncome{
        @JsonProperty("EmiratesId")
        public String emiratesId;
        @JsonProperty("IncomeType")
        public String incomeType;
        @JsonProperty("IncomeSource")
        public String incomeSource;
        @JsonProperty("IsUnemployed")
        public boolean isUnemployed;
        @JsonProperty("Amount")
        public double amount;
        @JsonProperty("Frequency")
        public String frequency;
        @JsonProperty("Description")
        public String description;
        @JsonProperty("AttachmentList")
        public List<AttachmentList> attachmentList;
    }
    @JsonProperty("EmiratesId")
    public String emiratesId;
    @JsonProperty("SelfDeclaredIncome")
    public List<SelfDeclaredIncome> selfDeclaredIncome;

}

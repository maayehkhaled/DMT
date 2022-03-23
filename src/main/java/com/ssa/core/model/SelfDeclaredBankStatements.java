package com.ssa.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SelfDeclaredBankStatements {
    @JsonProperty("EmiratesId")
    public String emiratesId;
    @JsonProperty("AttachmentList")
    public List<AttachmentList> attachmentList;

}

package com.ssa.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SelfDeclaredBankStatements {
    public class AttachmentList{
        @JsonProperty("BinaryFile")
        public String binaryFile;
        @JsonProperty("ContentType")
        public String contentType;
        @JsonProperty("Filename")
        public String filename;
    }

    @JsonProperty("EmiratesId")
    public String emiratesId;
    @JsonProperty("AttachmentList")
    public List<AttachmentList> attachmentList;

}

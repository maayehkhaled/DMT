package com.ssa.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AttachmentList {
    @JsonProperty("BinaryFile")
    public String binaryFile;
    @JsonProperty("ContentType")
    public String contentType;
    @JsonProperty("Filename")
    public String filename;
}

package com.ssa.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetApplicationStatusModel {
    public class SignedContract{
        @JsonProperty("BinaryFile")
        public String binaryFile;
        @JsonProperty("ContentType")
        public String contentType;
        @JsonProperty("Filename")
        public String filename;
    }


        @JsonProperty("EmiratesId")
        public String emiratesId;
        @JsonProperty("SignedContract")
        public SignedContract signedContract;
        @JsonProperty("CallADLocker")
        public boolean callADLocker;

}

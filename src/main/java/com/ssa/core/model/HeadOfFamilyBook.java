package com.ssa.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HeadOfFamilyBook {
    @JsonProperty("EmiratesId")
    public String emiratesId;
    @JsonProperty("FullNameEN")
    public String fullNameEN;
    @JsonProperty("FullNameAR")
    public String fullNameAR;
    @JsonProperty("StatusEN")
    public String statusEN;
    @JsonProperty("StatusAR")
    public String statusAR;

}

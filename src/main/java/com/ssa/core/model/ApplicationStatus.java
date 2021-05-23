package com.ssa.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApplicationStatus {
    @JsonProperty("IsEligible")
    public String isEligible;
    @JsonProperty("TitleEN")
    public String titleEN;
    @JsonProperty("TitleAR")
    public String titleAR;
    @JsonProperty("MessageEN")
    public String messageEN;
    @JsonProperty("MessageAR")
    public String messageAR;
}

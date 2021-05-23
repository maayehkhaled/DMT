package com.ssa.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Application {
    @JsonProperty("IsEligible")
    public boolean isEligible;
    @JsonProperty("HasDataIssues")
    public boolean hasDataIssues;
    @JsonProperty("MessageEN")
    public String messageEN;
    @JsonProperty("MessageAR")
    public String messageAR;
    @JsonProperty("IncorrectInfoMessageEN")
    public String incorrectInfoMessageEN;
    @JsonProperty("IncorrectInfoMessageAR")
    public String incorrectInfoMessageAR;
}

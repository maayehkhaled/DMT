package com.ssa.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseRoot {
    @JsonProperty("ResponseStatus")
    public ResponseStatus responseStatus;
    @JsonProperty("ApplicationSummary")
    public ApplicationSummary applicationSummary;
    @JsonProperty("ApplicationStatus")
    public ApplicationStatus applicationStatus;
}

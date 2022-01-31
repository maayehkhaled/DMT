package com.ssa.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApplicationSummary {
    @JsonProperty("ReferenceNumber")
    public String referenceNumber;
    @JsonProperty("SubmissionDate")
    public String submissionDate;
    @JsonProperty("SupportAmount")
    public int supportAmount;
    @JsonProperty("ContractStartDate")
    public String contractStartDate;
    @JsonProperty("ContractEndDate")
    public String contractEndDate;
    @JsonProperty("EstimatedSupportAmount")
    public String EstimatedSupportAmount;

}

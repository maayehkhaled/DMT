package com.ssa.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Premise {
    @JsonProperty("EmiratesId")
    public String emiratesId;
    @JsonProperty("WECompany")
    public String wECompany;
    @JsonProperty("AccountEmiratesId")
    public String accountEmiratesId;
    @JsonProperty("AccountNumber")
    public String accountNumber;
    @JsonProperty("PremiseNumber")
    public String premiseNumber;
    @JsonProperty("Reason")
    public String reason;

}

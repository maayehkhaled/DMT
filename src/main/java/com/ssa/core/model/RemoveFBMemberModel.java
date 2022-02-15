package com.ssa.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RemoveFBMemberModel {
    @JsonProperty("EmiratesId")
    public String emiratesId;
    @JsonProperty("ToRemoveEmiratesId")
    public String toRemoveEmiratesId;
    @JsonProperty("RemovalReasonKey")
    public String removalReasonKey;
    @JsonProperty("RemovalReasonDescription")
    public String removalReasonDescription;
}

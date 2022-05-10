package com.ssa.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddNewMemberModel {

    @JsonProperty("EmiratesId")
    public String emiratesId;
    @JsonProperty("ToAddEmiratesId")
    public String toAddEmiratesId;
    @JsonProperty("RelationshipToHoHKey")
    public String relationshipToHoHKey;
    @JsonProperty("DateOfBirth")
    public String dateOfBirth;
    @JsonProperty("ReasonForAdding")
    public String reasonForAdding;

}

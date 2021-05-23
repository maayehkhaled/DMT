package com.ssa.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Claimant {
    @JsonProperty("FamilyBookNumber")
    public int familyBookNumber;
    @JsonProperty("FamilyBookEmirateEN")
    public String familyBookEmirateEN;
    @JsonProperty("FamilyBookEmirateAR")
    public String familyBookEmirateAR;
    @JsonProperty("EmiratesId")
    public String emiratesId;
    @JsonProperty("IsHoFB")
    public boolean isHoFB;
    @JsonProperty("NationalityEN")
    public String nationalityEN;
    @JsonProperty("NationalityAR")
    public String nationalityAR;
    @JsonProperty("FullNameEN")
    public String fullNameEN;
    @JsonProperty("FullNameAR")
    public String fullNameAR;
    @JsonProperty("GenderEN")
    public String genderEN;
    @JsonProperty("GenderAR")
    public String genderAR;
    @JsonProperty("DateOfBirth")
    public String dateOfBirth;
    @JsonProperty("MaritalStatusEN")
    public String maritalStatusEN;
    @JsonProperty("MaritalStatusAR")
    public String maritalStatusAR;
    @JsonProperty("IsPoD")
    public boolean isPoD;
}

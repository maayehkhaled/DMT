package com.ssa.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UpdateContactDetailsModel {
    public class HouseholdContactDetail{
        @JsonProperty("EmiratesId")
        public String emiratesId;
        @JsonProperty("MobileNumber")
        public String mobileNumber;
        @JsonProperty("Email")
        public String email;
        @JsonProperty("SecondaryNumber")
        public String secondaryNumber;
    }

        @JsonProperty("EmiratesId")
        public String emiratesId;
        @JsonProperty("HouseholdContactDetails")
        public List<HouseholdContactDetail> householdContactDetails;
}

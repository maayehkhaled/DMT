package com.ssa.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Root{
    @JsonProperty("HeadOfFamilyBook")
    public HeadOfFamilyBook headOfFamilyBook;
    @JsonProperty("Claimant")
    public Claimant claimant;
    @JsonProperty("ResponseStatus")
    public ResponseStatus responseStatus;
    @JsonProperty("Application")
    public Application application;
}
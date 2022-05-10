package com.ssa.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GetFamilyDataModel {
    @JsonProperty("ResponseStatus")
    public ResponseStatus responseStatus;
    @JsonProperty("Application")
    public Application application;
    @JsonProperty("Household")
    public List<Household> household;
}

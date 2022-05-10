package com.ssa.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Lookup_Root {
    @JsonProperty("ResponseStatus")
    public ResponseStatus responseStatus;
    @JsonProperty("LookupTablesList")
    public ArrayList<LookupTablesList> lookupTablesList;
}

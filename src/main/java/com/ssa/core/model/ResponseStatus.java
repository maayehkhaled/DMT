package com.ssa.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseStatus {
    @JsonProperty("StatusCode")
    public int statusCode;
    @JsonProperty("Message")
    public String message;
    @JsonIgnore
    @JsonProperty("Errors")
    public String errors;
}
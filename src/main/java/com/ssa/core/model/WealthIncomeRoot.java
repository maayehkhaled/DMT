package com.ssa.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class WealthIncomeRoot {

    @JsonProperty("EmiratesId")
    public String emiratesId;
    @JsonProperty("WealthIncome")
    public List<WealthIncome> wealthIncome;


}

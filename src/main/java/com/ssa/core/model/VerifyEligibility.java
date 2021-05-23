package com.ssa.core.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class VerifyEligibility {
    private String EmiratesId;
    private String UAEPassMobileNumber;
    private String UAEPassEmail;
    private String ApplicationType;

    public VerifyEligibility() {
    }

    public VerifyEligibility(String emiratesId, String UAEPassMobileNumber, String UAEPassEmail, String applicationType) {
        EmiratesId = emiratesId;
        this.UAEPassMobileNumber = UAEPassMobileNumber;
        this.UAEPassEmail = UAEPassEmail;
        ApplicationType = applicationType;
    }

    public String getEmiratesId() {
        return EmiratesId;
    }

    public void setEmiratesId(String emiratesId) {
        EmiratesId = emiratesId;
    }

    public String getUAEPassMobileNumber() {
        return UAEPassMobileNumber;
    }

    public void setUAEPassMobileNumber(String UAEPassMobileNumber) {
        this.UAEPassMobileNumber = UAEPassMobileNumber;
    }

    public String getUAEPassEmail() {
        return UAEPassEmail;
    }

    public void setUAEPassEmail(String UAEPassEmail) {
        this.UAEPassEmail = UAEPassEmail;
    }

    public String getApplicationType() {
        return ApplicationType;
    }

    public void setApplicationType(String applicationType) {
        ApplicationType = applicationType;
    }

    public String toJson(Object obj) throws JsonProcessingException {
        ObjectWriter objectwriter=new ObjectMapper().writer().withDefaultPrettyPrinter();
            return objectwriter.writeValueAsString(obj);

    }

}

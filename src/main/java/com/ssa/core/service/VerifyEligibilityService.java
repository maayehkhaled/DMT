package com.ssa.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ssa.core.model.ResponseRoot;
import com.ssa.core.model.VerifyEligibility;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class VerifyEligibilityService {
    public HttpResponse<String> response;

    public void requestService() throws JsonProcessingException {
        Unirest.config().connectTimeout(0);
        Unirest.config().verifySsl(false);
        response = Unirest.post("https://10.231.1.100/ApplicationWS/rest/SocialSupportSupportRequest/VerifyEligibility")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic QVBJQWRtaW46MTIzNDU2")
                .body(requestBody())
                .asString();
    }

    public String requestBody() throws JsonProcessingException {
        VerifyEligibility verifyEligibility = new VerifyEligibility();
        verifyEligibility.setEmiratesId("784197519436477");
        verifyEligibility.setUAEPassMobileNumber("0551499312");
        verifyEligibility.setUAEPassEmail("test@test.com");
        verifyEligibility.setApplicationType("1st assessment");
        System.out.println(verifyEligibility.toJson(verifyEligibility));
        return verifyEligibility.toJson(verifyEligibility);
    }

    public ResponseRoot getresponse(VerifyEligibilityService submitApplicationService) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        return om.readValue(submitApplicationService.response.getBody(), ResponseRoot.class);

    }

    public static void main(String[] args) throws JsonProcessingException {
        VerifyEligibilityService submitApplicationService = new VerifyEligibilityService();
        submitApplicationService.requestService();
        System.out.print(submitApplicationService.getresponse(submitApplicationService).applicationSummary.referenceNumber);
    }

}

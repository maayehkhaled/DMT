package com.ssa.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssa.core.common.data.TestData;
import com.ssa.core.model.ResponseRoot;
import com.ssa.core.model.VerifyEligibility;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class GetIncomeStatus {
 public HttpResponse<String> response;

    public void requestService() throws JsonProcessingException {
        Unirest.config().connectTimeout(0);
        Unirest.config().verifySsl(false);
         response = Unirest.post("https://10.231.1.100/ApplicationWS/rest/SocialSupportSupportRequest/GetIncomeStatus")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic QVBJQWRtaW46MTIzNDU2")
                .body(requestBody())
                .asString();
    }

    public String requestBody() throws JsonProcessingException {
        VerifyEligibility verifyEligibility = new VerifyEligibility();
        verifyEligibility.setEmiratesId(TestData.EID);
        System.out.println(verifyEligibility.toJson(verifyEligibility));
        return verifyEligibility.toJson(verifyEligibility);
    }

    public ResponseRoot getresponse(GetIncomeStatus submitApplicationService) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        return om.readValue(submitApplicationService.response.getBody(), ResponseRoot.class);

    }
    public static void main(String[] args) throws JsonProcessingException {
        GetIncomeStatus submitApplicationService = new GetIncomeStatus();
        submitApplicationService.requestService();
        System.out.print(submitApplicationService.getresponse(submitApplicationService).applicationSummary.referenceNumber);
    }

}

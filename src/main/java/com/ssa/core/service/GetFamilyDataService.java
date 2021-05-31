package com.ssa.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssa.core.model.GetFamilyData;
import com.ssa.core.model.ResponseRoot;
import com.ssa.core.model.VerifyEligibility;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class GetFamilyDataService {
 public HttpResponse<String> response;

    public void requestService() throws JsonProcessingException {
        Unirest.config().connectTimeout(0);
        Unirest.config().verifySsl(false);
         response = Unirest.post("https://10.231.1.100/ApplicationWS/rest/SocialSupportSupportRequest/GetFamilyData")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic QVBJQWRtaW46MTIzNDU2")
                .body(requestBody())
                .asString();
    }

    public String requestBody() throws JsonProcessingException {
        VerifyEligibility verifyEligibility = new VerifyEligibility();
        verifyEligibility.setEmiratesId("784196505074045");
        System.out.println(verifyEligibility.toJson(verifyEligibility));
        return verifyEligibility.toJson(verifyEligibility);
    }

    public GetFamilyData getresponse(GetFamilyDataService submitApplicationService) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        return om.readValue(submitApplicationService.response.getBody(), GetFamilyData.class);

    }
    public static void main(String[] args) throws JsonProcessingException {
        GetFamilyDataService getFamilyDataService = new GetFamilyDataService();
        getFamilyDataService.requestService();
        System.out.print(getFamilyDataService.getresponse(getFamilyDataService).household.get(0).emiratesId);
    }

}

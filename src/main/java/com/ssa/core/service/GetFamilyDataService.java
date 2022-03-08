package com.ssa.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssa.core.common.data.TestData;
import com.ssa.core.model.GetFamilyData;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class GetFamilyDataService {
 public HttpResponse<String> response;

    public void requestService() throws JsonProcessingException {
        Unirest.config().reset();
        Unirest.config().connectTimeout(0);
        Unirest.config().verifySsl(false);
         response = Unirest.post("https://uat.ssa.gov.ae/ApplicationWS_API/rest/SocialSupportSupportRequest/GetFamilyData")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic QVBJQWRtaW46MTIzNDU2")
                .body(requestBody())
                .asString();
    }


    public String requestBody() throws JsonProcessingException {
        return "{\n" +
                "     \"EmiratesId\": \""+TestData.EID+"\"\n" +
                " \n" +
                "}";
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

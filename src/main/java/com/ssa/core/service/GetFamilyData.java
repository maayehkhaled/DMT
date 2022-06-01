package com.ssa.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ssa.core.model.FamilyData;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class GetFamilyData {
    public HttpResponse<String> response;

    public String toJson(Object obj) throws JsonProcessingException {
        ObjectWriter objectwriter=new ObjectMapper().writer().withDefaultPrettyPrinter();
        return objectwriter.writeValueAsString(obj);
    }

    public void requestService() throws JsonProcessingException {
        Unirest.config().reset();
        Unirest.config().connectTimeout(7000);
        Unirest.config().verifySsl(false);
        response = Unirest.post("https://uat.ssa.gov.ae/ApplicationWS_API/rest/SocialSupportSupportRequest/GetFamilyData")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic QVBJQWRtaW46MTIzNDU2")
                .body(requestBody())
                .asString();
        System.out.println(response.getBody());
    }
    public void requestServiceWithEid(String Eid) throws JsonProcessingException {
        Unirest.config().reset();
        Unirest.config().connectTimeout(7000);
        Unirest.config().verifySsl(false);
        response = Unirest.post("https://uat.ssa.gov.ae/ApplicationWS_API/rest/SocialSupportSupportRequest/GetFamilyData")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic QVBJQWRtaW46MTIzNDU2")
                .body(requestBodyWithEid(Eid))
                .asString();
        System.out.println(response.getBody());
    }

    private byte[] requestBodyWithEid(String eid) {
        return new byte[0];
    }

    public void requestServiceWithParam(String eid) throws JsonProcessingException {
        Unirest.config().reset();
        Unirest.config().connectTimeout(7000);
        Unirest.config().verifySsl(false);
        response = Unirest.post("https://uat.ssa.gov.ae/ApplicationWS_API/rest/SocialSupportSupportRequest/GetFamilyData")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic QVBJQWRtaW46MTIzNDU2")
                .body(requestBodyWithEid(eid))
                .asString();
        System.out.println(response.getBody());
    }


    public String requestBody() throws JsonProcessingException {

        FamilyData member= new FamilyData();
        member.emiratesId ="";
        System.out.println(toJson(member));
        return toJson(member);
    }

    public FamilyData getresponse(GetFamilyData submitApplicationService) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        return om.readValue(submitApplicationService.response.getBody(), FamilyData.class);
    }
/*
    public static void main(String[] args) throws JsonProcessingException {
        GetFamilyData submitApplicationService = new GetFamilyData();
        submitApplicationService.requestService();
        System.out.print(submitApplicationService.getresponse(submitApplicationService).application.messageEN);
    }
*/
}

package com.ssa.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ssa.core.model.RemoveFBMemberModel;
import com.ssa.core.model.Root;
import com.ssa.core.model.WealthItems;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class RemoveFBMember {
    public HttpResponse<String> response;

    public String toJson(Object obj) throws JsonProcessingException {
        ObjectWriter objectwriter=new ObjectMapper().writer().withDefaultPrettyPrinter();
        return objectwriter.writeValueAsString(obj);
    }

    public void requestService() throws JsonProcessingException {
        Unirest.config().reset();
        Unirest.config().connectTimeout(7000);
        Unirest.config().verifySsl(false);
        response = Unirest.post("https://uat.ssa.gov.ae/ApplicationWS_API/rest/SocialSupportSupportRequest/RemoveFBMember")
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
        response = Unirest.post("https://uat.ssa.gov.ae/ApplicationWS_API/rest/SocialSupportSupportRequest/RemoveFBMember")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic QVBJQWRtaW46MTIzNDU2")
                .body(requestBodyWithEid(Eid))
                .asString();
        System.out.println(response.getBody());
    }
    public String requestBodyWithEid(String Eid) throws JsonProcessingException {

//        Data Body To Be Sent

//        {
//            "EmiratesId": "string",
//            "ToRemoveEmiratesId": "string",
//            "RemovalReasonKey": "string",
//            "RemovalReasonDescription": "string"
//        }

        RemoveFBMemberModel member= new RemoveFBMemberModel();
        member.emiratesId =Eid;
        member.toRemoveEmiratesId="784200255608604";
        member.removalReasonKey="Married";
        member.removalReasonDescription="test";
        System.out.println(toJson(member));
        return toJson(member);
    }

    public String requestBody() throws JsonProcessingException {

//        Data Body To Be Sent

//        {
//            "EmiratesId": "string",
//            "ToRemoveEmiratesId": "string",
//            "RemovalReasonKey": "string",
//            "RemovalReasonDescription": "string"
//        }

        RemoveFBMemberModel member= new RemoveFBMemberModel();
        member.emiratesId ="";
        member.toRemoveEmiratesId="";
        member.removalReasonKey="";
        member.removalReasonDescription="";
//        System.out.println(toJson(member));
        return toJson(member);
    }

    public RemoveFBMemberModel getresponse(RemoveFBMember submitApplicationService) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        return om.readValue(submitApplicationService.response.getBody(), RemoveFBMemberModel.class);
    }

    public static void main(String[] args) throws JsonProcessingException {
        RemoveFBMember submitApplicationService = new RemoveFBMember();
        submitApplicationService.requestService();
       // System.out.print(submitApplicationService.getresponse(submitApplicationService).application.isEligible);
    }

}

package com.ssa.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ssa.core.model.Root;
import com.ssa.core.model.SSALookupModel;
import com.ssa.core.model.SubmitApplicationModel;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class SubmitApplication {
    public HttpResponse<String> response;

    public String toJson(Object obj) throws JsonProcessingException {
        ObjectWriter objectwriter=new ObjectMapper().writer().withDefaultPrettyPrinter();
        return objectwriter.writeValueAsString(obj);
    }

    public void requestService() throws JsonProcessingException {
        Unirest.config().reset();
        Unirest.config().connectTimeout(7000);
        Unirest.config().verifySsl(false);
        response = Unirest.post("https://uat.ssa.gov.ae/ApplicationWS_API/rest/SocialSupportSupportRequest/SubmitApplication")
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
        response = Unirest.post("https://uat.ssa.gov.ae/ApplicationWS_API/rest/SocialSupportSupportRequest/SubmitApplication")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic QVBJQWRtaW46MTIzNDU2")
                .body(requestBodyWithEid(Eid))
                .asString();
        System.out.println(response.getBody());
    }
    public String requestBodyWithEid(String Eid) throws JsonProcessingException {

//        Data Body To Be Sent
//        {
//            "EmiratesId": "string"
//        }

        SubmitApplicationModel member= new SubmitApplicationModel();
        member.emiratesId =Eid;
      System.out.println(toJson(member));
        return toJson(member);
    }

    public String requestBody() throws JsonProcessingException {

//        Data Body To Be Sent
//        {
//            "EmiratesId": "string"
//        }

        SubmitApplicationModel member= new SubmitApplicationModel();
        member.emiratesId ="";
//        System.out.println(toJson(member));
        return toJson(member);
    }

    public Root getresponse(SubmitApplication submitApplicationService) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        return om.readValue(submitApplicationService.response.getBody(), Root.class);
    }

    public static void main(String[] args) throws JsonProcessingException {
        VerifyEligibilityService submitApplicationService = new VerifyEligibilityService();
        submitApplicationService.requestService();
        System.out.print(submitApplicationService.getresponse(submitApplicationService).application.isEligible);
    }

}

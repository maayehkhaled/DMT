package com.ssa.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ssa.core.model.ActivationInformation;
import com.ssa.core.model.CancelApplicationModel;
import com.ssa.core.model.Root;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class GetActivationInformation {
    public HttpResponse<String> response;

    public String toJson(Object obj) throws JsonProcessingException {
        ObjectWriter objectwriter=new ObjectMapper().writer().withDefaultPrettyPrinter();
        return objectwriter.writeValueAsString(obj);
    }

    public void requestService() throws JsonProcessingException {
        Unirest.config().reset();
        Unirest.config().connectTimeout(7000);
        Unirest.config().verifySsl(false);
        response = Unirest.post("https://uat.ssa.gov.ae/ApplicationWS_API/rest/SocialSupportSupportRequest/GetActivationInformation")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic QVBJQWRtaW46MTIzNDU2")
                .body(requestBody())
                .asString();
        System.out.println(response.getBody());
    }

    public String requestBody() throws JsonProcessingException {

        ActivationInformation member= new ActivationInformation();
        member.emiratesId ="784196208107357";
        System.out.println(toJson(member));
        return toJson(member);
    }

    public void requestServiceWithParam(String eid) throws JsonProcessingException {
        Unirest.config().reset();
        Unirest.config().connectTimeout(7000);
        Unirest.config().verifySsl(false);
        response = Unirest.post("https://uat.ssa.gov.ae/ApplicationWS_API/rest/SocialSupportSupportRequest/GetActivationInformation")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic QVBJQWRtaW46MTIzNDU2")
                .body(requestBodyWithEID(eid))
                .asString();
        System.out.println(response.getBody());
    }

    public String requestBodyWithEID(String eid) throws JsonProcessingException {

        ActivationInformation member= new ActivationInformation();
        member.emiratesId =eid;
        System.out.println(toJson(member));
        return toJson(member);
    }

    public Root getresponse(GetActivationInformation submitApplicationService) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        return om.readValue(submitApplicationService.response.getBody(), Root.class);
    }

    public static void main(String[] args) throws JsonProcessingException {
        GetActivationInformation submitApplicationService = new GetActivationInformation();
        submitApplicationService.requestService();
        System.out.print(submitApplicationService.getresponse(submitApplicationService).application.isEligible);
    }
}

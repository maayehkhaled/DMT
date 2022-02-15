package com.ssa.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ssa.core.model.RemoveFBMemberModel;
import com.ssa.core.model.Root;
import com.ssa.core.model.SSALookupModel;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class SSALookup {
    public HttpResponse<String> response;

    public String toJson(Object obj) throws JsonProcessingException {
        ObjectWriter objectwriter=new ObjectMapper().writer().withDefaultPrettyPrinter();
        return objectwriter.writeValueAsString(obj);
    }

    public void requestService() throws JsonProcessingException {
        Unirest.config().reset();
        Unirest.config().connectTimeout(7000);
        Unirest.config().verifySsl(false);
        response = Unirest.post("https://uat.ssa.gov.ae/ApplicationWS_API/rest/SocialSupportSupportRequest/SSALookup")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic QVBJQWRtaW46MTIzNDU2")
                .body(requestBody())
                .asString();
        System.out.println(response.getBody());
    }

    public String requestBody() throws JsonProcessingException {

//        Data Body To Be Sent
//         {
//          "Service": "string",
//          "Stage": "string",
//          "LookupTable": "string"
//          }

        SSALookupModel member= new SSALookupModel();
        member.service ="";
        member.stage="";
        member.lookupTable="";
//        System.out.println(toJson(member));
        return toJson(member);
    }

    public Root getresponse(SSALookup submitApplicationService) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        return om.readValue(submitApplicationService.response.getBody(), Root.class);
    }

//    public static void main(String[] args) throws JsonProcessingException {
//        VerifyEligibilityService submitApplicationService = new VerifyEligibilityService();
//        submitApplicationService.requestService();
//        System.out.print(submitApplicationService.getresponse(submitApplicationService).application.isEligible);
//    }

}
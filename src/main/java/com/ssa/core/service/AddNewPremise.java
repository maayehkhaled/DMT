package com.ssa.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ssa.core.common.data.TestData;
import com.ssa.core.model.Premise;
import com.ssa.core.model.Root;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.lang.reflect.Member;

public class AddNewPremise {
    public HttpResponse<String> response;

    public String toJson(Object obj) throws JsonProcessingException {
        ObjectWriter objectwriter=new ObjectMapper().writer().withDefaultPrettyPrinter();
        return objectwriter.writeValueAsString(obj);
    }

    public void requestService() throws JsonProcessingException {
        Unirest.config().reset();
        Unirest.config().connectTimeout(7000);
        Unirest.config().verifySsl(false);
        response = Unirest.post("https://uat.ssa.gov.ae/ApplicationWS_API/rest/SocialSupportSupportRequest/AddNewPremise")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic QVBJQWRtaW46MTIzNDU2")
                .body(requestBody())
                .asString();
        System.out.println(response.getBody());
    }

    public String requestBody() throws JsonProcessingException {

//        Data Body To Be Sent
//        {
//            "EmiratesId": "784196718621707",
//            "WECompany": "AADC",
//            "AccountEmiratesId": "784196985915162",
//            "AccountNumber": "6550634124",
//            "PremiseNumber": "8029978694",
//            "Reason": "Test"
//        }

        Premise premise = new Premise();
        premise.emiratesId="784196718621707";
        premise.wECompany="AADC";
        premise.accountEmiratesId="784196985915162";
        premise.accountNumber="6550634124";
        premise.premiseNumber="8029978694";
        premise.reason="Test";
//        System.out.println(AddNewPremise.toJson(AddNewPremise));
        return toJson(premise);
    }

//    public Root getresponse(AddNewPremise submitApplicationService) throws JsonProcessingException {
//        ObjectMapper om = new ObjectMapper();
//        return om.readValue(submitApplicationService.response.getBody(), Root.class);
//    }

//    public static void main(String[] args) throws JsonProcessingException {
//        VerifyEligibilityService submitApplicationService = new VerifyEligibilityService();
//        submitApplicationService.requestService();
//        System.out.print(submitApplicationService.getresponse(submitApplicationService).application.isEligible);
//    }
}

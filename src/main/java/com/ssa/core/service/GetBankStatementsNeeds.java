package com.ssa.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.qpros.common.web.Base;
import com.ssa.core.model.GetApplicationSummaryModel;
import com.ssa.core.model.Root;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class GetBankStatementsNeeds extends Base {
    public HttpResponse<String> response;

    public String toJson(Object obj) throws JsonProcessingException {
        ObjectWriter objectwriter=new ObjectMapper().writer().withDefaultPrettyPrinter();
        return objectwriter.writeValueAsString(obj);
    }

    public void requestService() throws JsonProcessingException {
        Unirest.config().reset();
        Unirest.config().connectTimeout(7000);
        Unirest.config().verifySsl(false);
        response = Unirest.post("https://uat.ssa.gov.ae/ApplicationWS_API/rest/SocialSupportSupportRequest/GetBankStatementsNeeds")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic QVBJQWRtaW46MTIzNDU2")
                .body(requestBody())
                .asString();
        logManager.CodeBLOCK(response.getBody());
    }
    public void requestServiceWithEid(String eid) throws JsonProcessingException {
        Unirest.config().reset();
        Unirest.config().connectTimeout(7000);
        Unirest.config().verifySsl(false);
        response = Unirest.post("https://uat.ssa.gov.ae/ApplicationWS_API/rest/SocialSupportSupportRequest/GetBankStatementsNeeds")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic QVBJQWRtaW46MTIzNDU2")
                .body(requestBodyWithEid(eid))
                .asString();
        logManager.CodeBLOCK(response.getBody());
    }
    public String requestBodyWithEid(String eid) throws JsonProcessingException {
        GetApplicationSummaryModel member= new GetApplicationSummaryModel();
        member.emiratesId =eid;
        System.out.println(toJson(member));
        return toJson(member);
    }

    public String requestBody() throws JsonProcessingException {

//        Data Body To Be Sent
//        {
//            "EmiratesId": "string",
//        }

        GetApplicationSummaryModel member= new GetApplicationSummaryModel();
        member.emiratesId ="";
//        System.out.println(toJson(member));
        return toJson(member);
    }

    public GetApplicationSummaryModel getresponse(GetBankStatementsNeeds submitApplicationService) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        return om.readValue(submitApplicationService.response.getBody(), GetApplicationSummaryModel.class);
    }

//    public static void main(String[] args) throws JsonProcessingException {
//        VerifyEligibilityService submitApplicationService = new VerifyEligibilityService();
//        submitApplicationService.requestService();
//        System.out.print(submitApplicationService.getresponse(submitApplicationService).application.isEligible);
//    }

}

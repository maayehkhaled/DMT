package com.ssa.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qpros.common.LogManager;
import com.qpros.quanta.markuputils.CodeLanguage;
import com.qpros.quanta.markuputils.Markup;
import com.qpros.quanta.markuputils.MarkupHelper;
import com.ssa.core.common.data.TestData;
import com.ssa.core.model.ResponseRoot;
import com.ssa.core.model.VerifyEligibility;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class SubmitApplicationService {
    private final LogManager logManger= new LogManager(this.getClass().getSimpleName());
 public HttpResponse<String> response;

    public void requestService() throws JsonProcessingException {
        Unirest.config().reset();
        Unirest.config().connectTimeout(0);
        Unirest.config().verifySsl(false);
         response = Unirest.post("https://uat.ssa.gov.ae/ApplicationWS_API/rest/SocialSupportSupportRequest/SubmitApplication")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic QVBJQWRtaW46MTIzNDU2")
                .body(requestBody())
                .asString();
        Markup m = MarkupHelper.createCodeBlock(response.getBody(), CodeLanguage.JSON);
        //QuantaTestManager.getTestNode().log(Status.INFO,m);
    }

    public String requestBody() throws JsonProcessingException {
        VerifyEligibility verifyEligibility = new VerifyEligibility();
        verifyEligibility.setEmiratesId(TestData.EID);
        verifyEligibility.setUAEPassMobileNumber("0551499312");
        verifyEligibility.setUAEPassEmail("test@test.com");
        verifyEligibility.setApplicationType("1st assessment");
        System.out.println(verifyEligibility.toJson(verifyEligibility));
        return verifyEligibility.toJson(verifyEligibility);
    }

    public ResponseRoot getresponse(SubmitApplicationService submitApplicationService) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        System.out.println(submitApplicationService.response.getBody());
        return om.readValue(submitApplicationService.response.getBody(), ResponseRoot.class);

    }
    public static void main(String[] args) throws JsonProcessingException {
        SubmitApplicationService submitApplicationService = new SubmitApplicationService();
        submitApplicationService.requestService();
        System.out.print(submitApplicationService.getresponse(submitApplicationService).applicationSummary.referenceNumber);
    }

}

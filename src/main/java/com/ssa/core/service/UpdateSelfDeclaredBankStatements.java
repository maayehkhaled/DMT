package com.ssa.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ssa.core.model.Root;
import com.ssa.core.model.SelfDeclaredBankStatements;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class UpdateSelfDeclaredBankStatements {
    public HttpResponse<String> response;

    public String toJson(Object obj) throws JsonProcessingException {
        ObjectWriter objectwriter=new ObjectMapper().writer().withDefaultPrettyPrinter();
        return objectwriter.writeValueAsString(obj);
    }

    public void requestService() throws JsonProcessingException {
        Unirest.config().reset();
        Unirest.config().connectTimeout(7000);
        Unirest.config().verifySsl(false);
        response = Unirest.post("https://uat.ssa.gov.ae/ApplicationWS_API/rest/SocialSupportSupportRequest/UpdateSelfDeclaredBankStatements")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic QVBJQWRtaW46MTIzNDU2")
                .body(requestBody())
                .asString();
        System.out.println(response.getBody());
    }
    public void requestServiceWithParam(String eid) throws JsonProcessingException {
        Unirest.config().reset();
        Unirest.config().connectTimeout(7000);
        Unirest.config().verifySsl(false);
        response = Unirest.post("https://uat.ssa.gov.ae/ApplicationWS_API/rest/SocialSupportSupportRequest/UpdateSelfDeclaredBankStatements")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic QVBJQWRtaW46MTIzNDU2")
                .body(requestBodyWithParam(eid))
                .asString();
        System.out.println(response.getBody());
    }
    public String requestBodyWithParam(String eid) throws JsonProcessingException {
        SelfDeclaredBankStatements bankStatements=new SelfDeclaredBankStatements();
        bankStatements.emiratesId="";
        bankStatements.attachmentList.get(0).binaryFile="";
        bankStatements.attachmentList.get(0).contentType="";
        bankStatements.attachmentList.get(0).filename="";
        return toJson(bankStatements);
    }

        public String requestBody() throws JsonProcessingException {
            SelfDeclaredBankStatements bankStatements=new SelfDeclaredBankStatements();
            bankStatements.emiratesId="";
            bankStatements.attachmentList.get(0).binaryFile="";
            bankStatements.attachmentList.get(0).contentType="";
            bankStatements.attachmentList.get(0).filename="";
            return toJson(bankStatements);
        }

    public SelfDeclaredBankStatements getResponse(UpdateSelfDeclaredBankStatements submitApplicationService) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        return om.readValue(submitApplicationService.response.getBody(), SelfDeclaredBankStatements.class);
    }

    public static void main(String[] args) throws JsonProcessingException {
        UpdateSelfDeclaredBankStatements updateSelfDeclaredBankStatements = new UpdateSelfDeclaredBankStatements();
        updateSelfDeclaredBankStatements.requestService();
        //System.out.print(updateSelfDeclaredBankStatements.getResponse(updateSelfDeclaredBankStatements).statusCode);
    }
}

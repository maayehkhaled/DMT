package com.ssa.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ssa.core.model.Root;
import com.ssa.core.model.SelfDeclaredIncomeModel;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.lang.reflect.Array;

public class UpdateSelfDeclaredIncome {
    public HttpResponse<String> response;

    public String toJson(Object obj) throws JsonProcessingException {
        ObjectWriter objectwriter=new ObjectMapper().writer().withDefaultPrettyPrinter();
        return objectwriter.writeValueAsString(obj);
    }

    public void requestService() throws JsonProcessingException {
        Unirest.config().reset();
        Unirest.config().connectTimeout(7000);
        Unirest.config().verifySsl(false);
        response = Unirest.post("https://uat.ssa.gov.ae/ApplicationWS_API/rest/SocialSupportSupportRequest/UpdateSelfDeclaredIncome")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic QVBJQWRtaW46MTIzNDU2")
                .body(requestBody())
                .asString();
        System.out.println(response.getBody());
    }

    public String requestBody() throws JsonProcessingException {
        SelfDeclaredIncomeModel declaredIncome=new SelfDeclaredIncomeModel();
        declaredIncome.emiratesId="";
        declaredIncome.selfDeclaredIncome.get(0).emiratesId="";
        declaredIncome.selfDeclaredIncome.get(0).incomeType="";
        declaredIncome.selfDeclaredIncome.get(0).incomeSource="";
        declaredIncome.selfDeclaredIncome.get(0).isUnemployed=true;
        declaredIncome.selfDeclaredIncome.get(0).amount=13.5;
        declaredIncome.selfDeclaredIncome.get(0).frequency="";
        declaredIncome.selfDeclaredIncome.get(0).description="";
        declaredIncome.selfDeclaredIncome.get(0).attachmentList.get(0).binaryFile="";
        declaredIncome.selfDeclaredIncome.get(0).attachmentList.get(0).contentType="";
        declaredIncome.selfDeclaredIncome.get(0).attachmentList.get(0).filename="";
        return toJson(declaredIncome);
    }

    public Root getResponse(UpdateLivingOn submitApplicationService) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        return om.readValue(submitApplicationService.response.getBody(), Root.class);
    }

    public static void main(String[] args) throws JsonProcessingException {
        UpdateSelfDeclaredIncome updateSelfDeclaredIncome = new UpdateSelfDeclaredIncome();
        updateSelfDeclaredIncome.requestService();
        //System.out.print(updateSelfDeclaredIncome.getResponse(updateSelfDeclaredIncome).statusCode);
    }
}

package com.ssa.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ssa.core.model.Root;
import com.ssa.core.model.WealthIncomeModel;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class UpdateWealthIncome {
    public HttpResponse<String> response;

    public String toJson(Object obj) throws JsonProcessingException {
        ObjectWriter objectwriter=new ObjectMapper().writer().withDefaultPrettyPrinter();
        return objectwriter.writeValueAsString(obj);
    }

    public void requestService() throws JsonProcessingException {
        Unirest.config().reset();
        Unirest.config().connectTimeout(7000);
        Unirest.config().verifySsl(false);
        response = Unirest.post("https://uat.ssa.gov.ae/ApplicationWS_API/rest/SocialSupportSupportRequest/UpdateWealthIncome")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic QVBJQWRtaW46MTIzNDU2")
                .body(requestBody())
                .asString();
        System.out.println(response.getBody());
    }

    public String requestBody() throws JsonProcessingException {
        WealthIncomeModel wealthIncomeModel=new WealthIncomeModel();
        wealthIncomeModel.emiratesId="";
        wealthIncomeModel.wealthIncome.get(0).emiratesId="";
        wealthIncomeModel.wealthIncome.get(0).wealthType="";
        wealthIncomeModel.wealthIncome.get(0).wealthDescription="";
        wealthIncomeModel.wealthIncome.get(0).isBusinessActive=true;
        wealthIncomeModel.wealthIncome.get(0).incomeAmount=5000;
        wealthIncomeModel.wealthIncome.get(0).incomeFrequency="";
        wealthIncomeModel.wealthIncome.get(0).attachmentList.get(0).binaryFile="";
        wealthIncomeModel.wealthIncome.get(0).attachmentList.get(0).contentType="";
        wealthIncomeModel.wealthIncome.get(0).attachmentList.get(0).contentType="";
        System.out.println(wealthIncomeModel);
        return toJson(wealthIncomeModel);
    }

    public Root getResponse(UpdateLivingOn submitApplicationService) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        return om.readValue(submitApplicationService.response.getBody(), Root.class);
    }

    public static void main(String[] args) throws JsonProcessingException {
        UpdateWealthIncome updateWealthIncome = new UpdateWealthIncome();
        updateWealthIncome.requestService();
        //System.out.print(updateWealthIncome.getResponse(updateWealthIncome).statusCode);
    }
}

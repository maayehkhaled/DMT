package com.ssa.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ssa.core.model.AttachmentList;
import com.ssa.core.model.Root;
import com.ssa.core.model.WealthIncome;
import com.ssa.core.model.WealthIncomeRoot;
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
    public void requestServiceWithEid(String Eid) throws JsonProcessingException {
        Unirest.config().reset();
        Unirest.config().connectTimeout(7000);
        Unirest.config().verifySsl(false);
        response = Unirest.post("https://uat.ssa.gov.ae/ApplicationWS_API/rest/SocialSupportSupportRequest/UpdateWealthIncome")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic QVBJQWRtaW46MTIzNDU2")
                .body(requestBodyWithEid(Eid))
                .asString();
        System.out.println(response.getBody());
    }
    public String requestBodyWithEid(String Eid) throws JsonProcessingException {
        //WealthIncomeModel wealthIncomeModel=new WealthIncomeModel();
        WealthIncome wealthIncomeModel=new WealthIncome();
        wealthIncomeModel.emiratesId=Eid;
        wealthIncomeModel.wealthDescription="";
        wealthIncomeModel.emiratesId="";
        wealthIncomeModel.wealthType="";
        //wealthIncomeModel.wealthIncome.get(0).wealthDescription="";
        wealthIncomeModel.isBusinessActive=true;
        wealthIncomeModel.incomeAmount=5000;
        wealthIncomeModel.incomeFrequency="";
        wealthIncomeModel.attachmentList.get(0).binaryFile="";
        wealthIncomeModel.attachmentList.get(0).contentType="";
        wealthIncomeModel.attachmentList.get(0).contentType="";
        System.out.println(wealthIncomeModel);
        return toJson(wealthIncomeModel);
    }

    public void requestServiceWithParam(String eid) throws JsonProcessingException {
        Unirest.config().reset();
        Unirest.config().connectTimeout(7000);
        Unirest.config().verifySsl(false);
        response = Unirest.post("https://uat.ssa.gov.ae/ApplicationWS_API/rest/SocialSupportSupportRequest/UpdateWealthIncome")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic QVBJQWRtaW46MTIzNDU2")
                .body(requestBodyWithParam(eid))
                .asString();
        System.out.println(response.getBody());
    }
    public String requestBodyWithParam(String eid) throws JsonProcessingException {
        WealthIncomeRoot wealthIncomeRoot =new WealthIncomeRoot();
        WealthIncome wealthIncome=new WealthIncome();
        AttachmentList attachmentList=new AttachmentList();
        wealthIncomeRoot.emiratesId=eid;

        wealthIncome.emiratesId=eid;
        wealthIncome.wealthType="Property";
        wealthIncome.wealthDescription="Property";
        wealthIncome.isBusinessActive=true;
        wealthIncome.incomeAmount=50;
        wealthIncome.incomeFrequency="monthly";
        attachmentList.binaryFile="0110";
        attachmentList.contentType="pdf";
        attachmentList.filename="test";
        System.out.println(wealthIncomeRoot);
        return toJson(wealthIncomeRoot);
    }

    public String requestBody() throws JsonProcessingException {
        WealthIncomeRoot wealthIncomeRoot =new WealthIncomeRoot();
        wealthIncomeRoot.emiratesId="";
        wealthIncomeRoot.wealthIncome.get(0).emiratesId="";
        wealthIncomeRoot.wealthIncome.get(0).wealthType="";
        wealthIncomeRoot.wealthIncome.get(0).wealthDescription="";
        wealthIncomeRoot.wealthIncome.get(0).isBusinessActive=true;
        wealthIncomeRoot.wealthIncome.get(0).incomeAmount=5000;
        wealthIncomeRoot.wealthIncome.get(0).incomeFrequency="";
        wealthIncomeRoot.wealthIncome.get(0).attachmentList.get(0).binaryFile="";
        wealthIncomeRoot.wealthIncome.get(0).attachmentList.get(0).contentType="";
        wealthIncomeRoot.wealthIncome.get(0).attachmentList.get(0).contentType="";
        System.out.println(wealthIncomeRoot);
        return toJson(wealthIncomeRoot);
    }

    public WealthIncomeRoot getResponse(UpdateWealthIncome submitApplicationService) throws JsonProcessingException {

        ObjectMapper om = new ObjectMapper();
        return om.readValue(submitApplicationService.response.getBody(), WealthIncomeRoot.class);
    }
/*
    public static void main(String[] args) throws JsonProcessingException {
        UpdateWealthIncome updateWealthIncome = new UpdateWealthIncome();
        updateWealthIncome.requestService();
        System.out.print(updateWealthIncome.getResponse(updateWealthIncome).wealthIncome);
    }*/
}

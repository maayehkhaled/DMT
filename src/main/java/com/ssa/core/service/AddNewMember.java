package com.ssa.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ssa.core.model.AddNewMemberModel;
import com.ssa.core.model.Premise;
import com.ssa.core.model.Root;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.SneakyThrows;

public class AddNewMember {
    public HttpResponse<String> response;

    @SneakyThrows
    public String toJson(Object obj) {
        ObjectWriter objectwriter=new ObjectMapper().writer().withDefaultPrettyPrinter();
        return objectwriter.writeValueAsString(obj);
    }

    public void requestService() throws JsonProcessingException {
        Unirest.config().reset();
        Unirest.config().connectTimeout(7000);
        Unirest.config().verifySsl(false);
        response = Unirest.post("https://uat.ssa.gov.ae/ApplicationWS_API/rest/SocialSupportSupportRequest/AddNewMember")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic QVBJQWRtaW46MTIzNDU2")
                .body(requestBody())
                .asString();
        System.out.println(response.getBody());
    }

    public void requestServiceWithParam(String eid){
        Unirest.config().reset();
        Unirest.config().connectTimeout(7000);
        Unirest.config().verifySsl(false);
        response = Unirest.post("https://uat.ssa.gov.ae/ApplicationWS_API/rest/SocialSupportSupportRequest/AddNewMember")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic QVBJQWRtaW46MTIzNDU2")
                .body(requestBodyWithEID(eid))
                .asString();
        System.out.println(response.getBody());
    }
    public String requestBodyWithEID(String eid)  {
        AddNewMemberModel addNewMember=new AddNewMemberModel();
        addNewMember.emiratesId="";
        addNewMember.toAddEmiratesId="";
        addNewMember.relationshipToHoHKey="";
        addNewMember.dateOfBirth="";
        addNewMember.secondDocument="";
        return toJson(addNewMember);
    }

    public String requestBody() throws JsonProcessingException {
        AddNewMemberModel addNewMember=new AddNewMemberModel();
        addNewMember.emiratesId="";
        addNewMember.toAddEmiratesId="";
        addNewMember.relationshipToHoHKey="";
        addNewMember.dateOfBirth="";
        addNewMember.secondDocument="";

        return toJson(addNewMember);
    }

    public Root getResponse(UpdateLivingOn submitApplicationService) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        return om.readValue(submitApplicationService.response.getBody(), Root.class);
    }

    public static void main(String[] args) throws JsonProcessingException {
        AddNewMember addNewMember = new AddNewMember();
        addNewMember.requestService();
        //System.out.print(updateSelfDeclaredIncome.getResponse(updateSelfDeclaredIncome).statusCode);
    }
}

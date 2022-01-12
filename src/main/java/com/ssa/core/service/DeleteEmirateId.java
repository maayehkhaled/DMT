package com.ssa.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssa.core.common.data.TestData;
import com.ssa.core.model.ResponseStatus;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class DeleteEmirateId {


  public HttpResponse<String> response;

  public void requestService() throws JsonProcessingException {
    Unirest.config().reset();
    Unirest.config().connectTimeout(0);
    Unirest.config().verifySsl(false);
     response = Unirest.delete("https://uat.ssa.gov.ae/AutomationSupport_API/rest/DeleteEmiratesId/CallDelete")
        .header("Content-Type", "application/json")
        .header("Authorization", "Basic c3VwZXJ1c2VyOjEyMzQ1Ng==")
        .body(requestBody())
        .asString();
  }

  public String requestBody() throws JsonProcessingException {
    return "{\n" +
            "    \"Emiratesid\": [\n" +
            "        {\n" +
            "            \"Emiratesid\": \""+TestData.EID+"\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";
  }

  public ResponseStatus getresponse(DeleteEmirateId serviceObject) throws JsonProcessingException {
    ObjectMapper om = new ObjectMapper();
    return om.readValue(serviceObject.response.getBody(), ResponseStatus.class);

  }

  public static void main(String[] args) throws JsonProcessingException {
    DeleteEmirateId deleteEmirateId = new DeleteEmirateId();
    deleteEmirateId.requestService();
  }


}

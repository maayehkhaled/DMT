package com.ssa.core.service;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class SubmitApplicationService {
 public HttpResponse<String> response;

    public void requestService(){
        Unirest.config().verifySsl(false);
         response = Unirest.post("https://10.231.1.100/ApplicationWS/rest/SocialSupportSupportRequest/SubmitApplication")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic QVBJQWRtaW46MTIzNDU2")
                .body(requestBody())
                .asString();
    }

    public String requestBody(){
        String body="{\n" +
                "  \"EmiratesId\": \"784197519436477\",\n" +
                "  \"UAEPassMobileNumber\": \"0551499312\",\n" +
                "  \"UAEPassEmail\": \"test@test.com\",\n" +
                "  \"ApplicationType\": \"1st assessment\"\n" +
                "}";
        return body;
    }
   public static void main(String [] args){
        SubmitApplicationService submitApplicationService= new SubmitApplicationService();
        submitApplicationService.requestService();
        System.out.print(submitApplicationService.response);
   }

}

package com.ssa.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ssa.core.model.Root;
import com.ssa.core.model.SubmitApplicationModel;
import com.ssa.core.model.UpdateActivationInformationModel;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class UpdateActivationInformation {
    public HttpResponse<String> response;

    public String toJson(Object obj) throws JsonProcessingException {
        ObjectWriter objectwriter=new ObjectMapper().writer().withDefaultPrettyPrinter();
        return objectwriter.writeValueAsString(obj);
    }

    public void requestService() throws JsonProcessingException {
        Unirest.config().reset();
        Unirest.config().connectTimeout(7000);
        Unirest.config().verifySsl(false);
        response = Unirest.post("https://uat.ssa.gov.ae/ApplicationWS_API/rest/SocialSupportSupportRequest/UpdateActivationInformation")
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
        response = Unirest.post("https://uat.ssa.gov.ae/ApplicationWS_API/rest/SocialSupportSupportRequest/UpdateActivationInformation")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic QVBJQWRtaW46MTIzNDU2")
                .body(requestBody())
                .asString();
        System.out.println(response.getBody());
    }
    public String requestBodyWithEid(String eid) throws JsonProcessingException {
        UpdateActivationInformationModel member= new UpdateActivationInformationModel();
        member.emiratesId ="";
        member.individualsList.get(0).emiratesId="";
        member.individualsList.get(0).fullNameEN="";
        member.individualsList.get(0).fullNameAR="";
        member.individualsList.get(0).isCaretaker=true;
        member.individualsList.get(0).careTypeList.get(0).careTypeKey="";
        member.individualsList.get(0).isCurrentlyStudying=true;
        member.individualsList.get(0).educationLevelKey="";
        member.individualsList.get(0).graduationYear=0;
        member.individualsList.get(0).areaOfStudyKey="";
        member.individualsList.get(0).specializationKey="";
        member.individualsList.get(0).specializationDescription="";
        member.individualsList.get(0).englishCertificateKey="";
        member.individualsList.get(0).englishCertificateScore=0.1;
        member.individualsList.get(0).hasPreviousExperience=true;
        member.individualsList.get(0).previousExpYear=0;
        member.individualsList.get(0).previousExpMonth=0;
        member.individualsList.get(0).experienceList.get(0).experienceTypeKey="";
        member.individualsList.get(0).experienceList.get(0).company="";
        member.individualsList.get(0).experienceList.get(0).startDate="2014-12-31";
        member.individualsList.get(0).experienceList.get(0).endDate="2014-12-31";
        member.individualsList.get(0).experienceList.get(0).isCurrentJob=true;
        member.individualsList.get(0).nServiceStatusKey="";
        member.individualsList.get(0).nServiceStatusRank=0;
        System.out.println(toJson(member));
        return toJson(member);
    }

    public String requestBody() throws JsonProcessingException {

//        Data Body To Be Sent
//        {
//            "EmiratesId": "string",
//                "IndividualsList": [
//            {
//                "EmiratesId": "string",
//                    "FullNameEN": "string",
//                    "FullNameAR": "string",
//                    "IsCaretaker": true,
//                    "CareTypeList": [
//                {
//                    "CareTypeKey": "string"
//                }
//      ],
//                "IsCurrentlyStudying": true,
//                    "EducationLevelKey": "string",
//                    "GraduationYear": 0,
//                    "AreaOfStudyKey": "string",
//                    "SpecializationKey": "string",
//                    "SpecializationDescription": "string",

//                    "EnglishCertificateKey": "string",
//                    "EnglishCertificateScore": 0.1,
//                    "HasPreviousExperience": true,
//                    "PreviousExpYear": 0,
//                    "PreviousExpMonth": 0,
//                    "ExperienceList": [
//                {
//                    "ExperienceTypeKey": "string",
//                        "Company": "string",
//                        "StartDate": "2014-12-31",
//                        "EndDate": "2014-12-31",
//                        "IsCurrentJob": true
//                }
//      ],
//                "NServiceStatusKey": "string",
//                    "NServiceStatusRank": 0
//            }
//  ]
//        }

        UpdateActivationInformationModel member= new UpdateActivationInformationModel();
        member.emiratesId ="";
        member.individualsList.get(0).emiratesId="";
        member.individualsList.get(0).fullNameEN="";
        member.individualsList.get(0).fullNameAR="";
        member.individualsList.get(0).isCaretaker=true;
        member.individualsList.get(0).careTypeList.get(0).careTypeKey="";
        member.individualsList.get(0).isCurrentlyStudying=true;
        member.individualsList.get(0).educationLevelKey="";
        member.individualsList.get(0).graduationYear=0;
        member.individualsList.get(0).areaOfStudyKey="";
        member.individualsList.get(0).specializationKey="";
        member.individualsList.get(0).specializationDescription="";
        member.individualsList.get(0).englishCertificateKey="";
        member.individualsList.get(0).englishCertificateScore=0.1;
        member.individualsList.get(0).hasPreviousExperience=true;
        member.individualsList.get(0).previousExpYear=0;
        member.individualsList.get(0).previousExpMonth=0;
        member.individualsList.get(0).experienceList.get(0).experienceTypeKey="";
        member.individualsList.get(0).experienceList.get(0).company="";
        member.individualsList.get(0).experienceList.get(0).startDate="2014-12-31";
        member.individualsList.get(0).experienceList.get(0).endDate="2014-12-31";
        member.individualsList.get(0).experienceList.get(0).isCurrentJob=true;
        member.individualsList.get(0).nServiceStatusKey="";
        member.individualsList.get(0).nServiceStatusRank=0;
                System.out.println(toJson(member));
        return toJson(member);
    }

    public Root getresponse(UpdateActivationInformation submitApplicationService) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        return om.readValue(submitApplicationService.response.getBody(), Root.class);
    }

    public static void main(String[] args) throws JsonProcessingException {
        UpdateActivationInformation submitApplicationService = new UpdateActivationInformation();
        submitApplicationService.requestService();
        System.out.print(submitApplicationService.getresponse(submitApplicationService).application.isEligible);
    }

}

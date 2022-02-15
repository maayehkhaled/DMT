package com.ssa.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class UpdateActivationInformationModel {
    public class CareTypeList{
        @JsonProperty("CareTypeKey")
        public String careTypeKey;
    }

    public class ExperienceList{
        @JsonProperty("ExperienceTypeKey")
        public String experienceTypeKey;
        @JsonProperty("Company")
        public String company;
        @JsonProperty("StartDate")
        public String startDate;
        @JsonProperty("EndDate")
        public String endDate;
        @JsonProperty("IsCurrentJob")
        public boolean isCurrentJob;
    }

    public class IndividualsList{
        @JsonProperty("EmiratesId")
        public String emiratesId;
        @JsonProperty("FullNameEN")
        public String fullNameEN;
        @JsonProperty("FullNameAR")
        public String fullNameAR;
        @JsonProperty("IsCaretaker")
        public boolean isCaretaker;
        @JsonProperty("CareTypeList")
        public List<CareTypeList> careTypeList;
        @JsonProperty("IsCurrentlyStudying")
        public boolean isCurrentlyStudying;
        @JsonProperty("EducationLevelKey")
        public String educationLevelKey;
        @JsonProperty("GraduationYear")
        public int graduationYear;
        @JsonProperty("AreaOfStudyKey")
        public String areaOfStudyKey;
        @JsonProperty("SpecializationKey")
        public String specializationKey;
        @JsonProperty("SpecializationDescription")
        public String specializationDescription;
        @JsonProperty("EnglishCertificateKey")
        public String englishCertificateKey;
        @JsonProperty("EnglishCertificateScore")
        public double englishCertificateScore;
        @JsonProperty("HasPreviousExperience")
        public boolean hasPreviousExperience;
        @JsonProperty("PreviousExpYear")
        public int previousExpYear;
        @JsonProperty("PreviousExpMonth")
        public int previousExpMonth;
        @JsonProperty("ExperienceList")
        public List<ExperienceList> experienceList;
        @JsonProperty("NServiceStatusKey")
        public String nServiceStatusKey;
        @JsonProperty("NServiceStatusRank")
        public int nServiceStatusRank;
    }

        @JsonProperty("EmiratesId")
        public String emiratesId;
        @JsonProperty("IndividualsList")
        public List<IndividualsList> individualsList;
}

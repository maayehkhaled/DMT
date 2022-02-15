package com.ssa.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.util.List;

public class UpdateLivingOnModel {
    public class RemovedPremisesList{
        @JsonProperty("PremiseNumber")
        public String premiseNumber;
    }

    public class SelfDeclaredLocation{
        @JsonProperty("PremiseNumber")
        public String premiseNumber;
        @JsonProperty("MunicipalityKey")
        public String municipalityKey;
        @JsonProperty("DistrictKey")
        public String districtKey;
        @JsonProperty("CommunityKey")
        public String communityKey;
    }

    public class LivingOnPremisesList{
        @JsonProperty("EmiratesId")
        public String emiratesId;
        @JsonProperty("PremiseNumber")
        public String premiseNumber;
        @JsonProperty("LivingOnOther")
        public String livingOnOther;
    }

    public class LivingOnPremises{
        @JsonProperty("RemovedPremisesList")
        public List<RemovedPremisesList> removedPremisesList;
        @JsonProperty("SelfDeclaredLocation")
        public List<SelfDeclaredLocation> selfDeclaredLocation;
        @JsonProperty("LivingOnPremisesList")
        public List<LivingOnPremisesList> livingOnPremisesList;
    }

        @JsonProperty("EmiratesId")
        public String emiratesId;
        @JsonProperty("LivingOnPremises")
        public LivingOnPremises livingOnPremises;

}

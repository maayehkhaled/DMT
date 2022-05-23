package com.ssa.core.common.data;

import com.qpros.helpers.FileUtils;

public interface StaticValues {


    public int currencySubString=5;
    public int yearsFullMonths=12;
    int upperBound = 999;
    int lowerBound = 100;
    int lowerMonth=0;
    int upperMonth=12;
    int leftLimit = 97; // letter 'a'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 10;
    String choseValue="9403";
     String refCode= FileUtils.readFile("refCodeFile.txt").get(0);
    //String refCode="SSP-30728";
    public int index=0;
    public String generatedString="";
    public String DateTimeFormatYearMonthDay="yyyy/MM/dd";
    //public String DateTimeFormatDayMonthYear="dd/MM/yyyy";
    public String getDay="dd";
    public String getmonth="mm";
    public String DateTimeFormatDayMonthYear="dd/mm/yyyy";
    public String actionAssignedAutomationOption="1708";
    public String enforcementType="21";
    public String infoSource="54";
    public String enforcementLogType="1";
    public String actionToday="184";
    public String graduationMonthOption="5";
    public String graduationYearOption="2022";
    public String masterDegree="3";
    public String masterGraduation="2025";
    public String joblessJan="5";
    public String joblessY="2019";
    public String IELTS="2";
    public String partner="127";
    public String countryPalestine="12";
    public String secondCountryEgypt="13";
    public String csDept="26";
    public String acceptOppStatus="10";
    public String noAnswerOppStatus="19";
    public String acceptNoDocOppStatus="19";
    public String SSP="SSP-7026";
    public String oppName="177";
    public String defaultOppName="__ossli_0";
    public String minAge="18";
    public String maxAge="60";
    public String weight="80";
    public String length="180";
    public String fileName="test.pdf";
}

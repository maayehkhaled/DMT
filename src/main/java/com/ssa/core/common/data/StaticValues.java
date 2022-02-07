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
    public int index=0;
    public String generatedString="";
    public String DateTimeFormatYearMonthDay="yyyy/MM/dd";
    public String DateTimeFormatDayMonthYear="dd/MM/yyyy";
}

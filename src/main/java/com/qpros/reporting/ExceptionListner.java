package com.qpros.reporting;

public class ExceptionListner {

    public String checkException(String exception) {
        if (exception.contains("Assert")) {
            exception = exception.substring(exception.indexOf("expected"), exception.lastIndexOf("]"));
        } else if (exception.contains("depends on not successfully")) {
            exception = "Skipped, since its depends on failed method";
        } else if (exception.contains("NoSuchElementException")) {
            exception = "Locator Not Exist for the Following : "+exception.substring(exception.lastIndexOf("Element info"));
        }
        return exception;

    }
}

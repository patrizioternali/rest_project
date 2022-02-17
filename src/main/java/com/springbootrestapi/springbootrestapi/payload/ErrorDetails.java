package com.springbootrestapi.springbootrestapi.payload;

import java.util.Date;

public class ErrorDetails {

    private Date theDate;
    private String message;
    private String details;

    public ErrorDetails(Date theDate, String message, String details) {
        this.theDate = theDate;
        this.message = message;
        this.details = details;
    }

    public Date getTheDate() {
        return theDate;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}

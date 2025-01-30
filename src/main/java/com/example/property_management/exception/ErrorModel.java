package com.example.property_management.exception;

public class ErrorModel {

    private String code;
    private String message;

    public ErrorModel() {
        // Empty constructor
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

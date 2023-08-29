package com.tfkfan.service.dto;

/**
 * @author Baltser Artem tfkfan
 */
public class Error {
    private String code;
    private String message;
    private Object details;

    public Error(){

    }

    public Error(String code, String message, Object details) {
        this.code = code;
        this.message = message;
        this.details = details;
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

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }
}

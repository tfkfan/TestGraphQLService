package com.tfkfan.service.dto;

/**
 * @author Baltser Artem tfkfan
 */
public class ValidationDetails {
    private String fields;

    public ValidationDetails() {
    }

    public ValidationDetails(String fields) {
        this.fields = fields;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }
}

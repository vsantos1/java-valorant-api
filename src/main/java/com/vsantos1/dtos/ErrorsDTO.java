package com.vsantos1.dtos;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ErrorsDTO {

    private Date timestamp;
    private String message;
    private Integer status;
    private HashMap<String, String> errors;
    private String path;

    public ErrorsDTO(Date timestamp, String message, Integer status, HashMap<String, String> errors, String path) {
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;
        this.errors = errors;
        this.path = path;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public void setErrors(HashMap<String, String> errors) {
        this.errors = errors;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}


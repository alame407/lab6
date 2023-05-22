package com.alame.lab6.common.response;

import java.io.Serializable;

public class Response<T> implements Serializable {
    private ResponseStatus status;
    private T response;
    private String errors;
    public Response(ResponseStatus status, T response, String errors){
        this.status = status;
        this.response = response;
        this.errors = errors;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public String getErrors() {
        return errors;
    }

    public T getResponse() {
        return response;
    }
}

package com.wishihab.weflixjava.model.core;

public class ErrorResponse {
    private ErrorData error;
    private ErrorMeta meta;

    // some backend use message at the root of response
    private String message;

    public ErrorResponse() {

    }

    public ErrorData getErrorData() {
        return error;
    }

    public void setErrorData(ErrorData errorData) {
        this.error = errorData;
    }

    public ErrorMeta getErrorMeta() {
        return meta;
    }

    public void setErrorMeta(ErrorMeta errorMeta) {
        this.meta = errorMeta;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "error=" + error +
                ", meta=" + meta +
                ", message='" + message + '\'' +
                '}';
    }
}

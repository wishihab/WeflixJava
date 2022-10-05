package com.wishihab.weflixjava.model.core;

public class ErrorMeta {

    private String code;
    private String message;
    private Boolean tryAgain;

    public ErrorMeta() {

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

    public Boolean getTryAgain() {
        return tryAgain;
    }

    public void setTryAgain(Boolean tryAgain) {
        this.tryAgain = tryAgain;
    }

    @Override
    public String toString() {
        return "ErrorData{" +
               "code='" + code + '\'' +
               ", message='" + message + '\'' +
               '}';
    }
}

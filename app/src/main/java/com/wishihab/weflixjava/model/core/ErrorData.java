package com.wishihab.weflixjava.model.core;

public class ErrorData {

    private String code;
    private String message;
    private Boolean otp;

    public ErrorData() {

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

    public Boolean getOtp() {
        return otp;
    }

    public void setOtp(Boolean otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "ErrorData{" +
               "code='" + code + '\'' +
               ", message='" + message + '\'' +
               '}';
    }
}

package com.iban.dto;

public class ErrorDto {

    private String message;
    private String cause;

    public ErrorDto(String message, String cause) {
        this.message = message;
        this.cause = cause;
    }

    public String getMessage() {
        return message;
    }

    public String getCause() {
        return cause;
    }
}

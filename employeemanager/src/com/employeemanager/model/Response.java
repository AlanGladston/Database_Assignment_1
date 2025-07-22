package com.employeemanager.model;

public class Response {
    private int statusCode;
    private String message;

    public Response(int code, String message) {
        this.statusCode = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "[" + statusCode + "] " + message;
    }

    public int getStatusCode() { return statusCode; }
    public String getMessage() { return message; }
}

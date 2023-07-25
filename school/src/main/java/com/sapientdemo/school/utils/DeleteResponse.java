package com.sapientdemo.school.utils;

public class DeleteResponse {
    private final boolean success;
    private final String message;

    public DeleteResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}

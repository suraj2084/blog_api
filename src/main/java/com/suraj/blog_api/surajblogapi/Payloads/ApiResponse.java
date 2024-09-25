package com.suraj.blog_api.surajblogapi.Payloads;

public class ApiResponse {
    private String message;
    private boolean success;

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}

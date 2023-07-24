package com.example.shopappp;

public class signup_response_model
{
    String message;

    public signup_response_model(String message) {
        this.message = message;
    }

    public signup_response_model() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

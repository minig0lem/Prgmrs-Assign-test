package com.programmers.Assignment.orders;

import javax.validation.constraints.Size;

public class OrderRequest {
    @Size(max = 1000, message = "message length must be less than 1000 characters")
    private String message;

    public OrderRequest() {}

    public OrderRequest(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
package com.programmers.Assignment.orders;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ReviewRequest {
    @NotBlank(message = "content must be provided")
    @Size(max = 1000, message = "content length must be less than 1000 characters")
    private String content;

    public ReviewRequest() {};

    public ReviewRequest(String content) {
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
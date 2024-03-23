package com.programmers.Assignment.orders;

public enum State {
    REQUESTED("REQUESTED"),
    ACCEPTED("ACCEPTED"),
    SHIPPING("SHIPPING"),
    COMPLETED("COMPLETED"),
    REJECTED("REJECTED");

    private String status;

    State(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
package com.programmers.Assignment.orders;

import java.time.LocalDateTime;

public class OrderResponse {
    private final Long seq;
    private final Long productId;
    private ReviewResponse review;
    private State state;
    private String requestMessage;
    private String rejectMessage;
    private LocalDateTime completedAt;
    private LocalDateTime rejectedAt;
    private final LocalDateTime createAt;

    public OrderResponse(Order order, ReviewResponse review) {
        this.seq = order.getSeq();
        this.productId = order.getProductSeq();
        this.review = review == null ? null : review;
        this.state = order.getState();
        this.requestMessage = order.getRequestMsg();
        this.rejectMessage = order.getRejectedMsg();
        this.completedAt = order.getCompletedAt();
        this.rejectedAt = order.getRejectedAt();
        this.createAt = order.getCreateAt();
    }

    public Long getSeq() {
        return seq;
    }

    public Long getProductId() {
        return productId;
    }

    public ReviewResponse getReview() {
        return review;
    }

    public State getState() {
        return state;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public String getRejectMessage() {
        return rejectMessage;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public LocalDateTime getRejectedAt() {
        return rejectedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createAt;
    }
}

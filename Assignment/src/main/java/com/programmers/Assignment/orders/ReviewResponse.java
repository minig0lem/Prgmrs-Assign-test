package com.programmers.Assignment.orders;

import java.time.LocalDateTime;

public class ReviewResponse {
    private final Long seq;
    private final Long productId;
    private final String content;
    private final LocalDateTime createAt;

    public ReviewResponse(Review review) {
        this.seq = review.getSeq();
        this.productId = review.getProductSeq();
        this.content = review.getContent();
        this.createAt = review.getCreateAt();
    }

    public Long getSeq() {
        return seq;
    }

    public Long getProductId() {
        return productId;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }
}
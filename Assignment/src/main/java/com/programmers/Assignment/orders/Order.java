package com.programmers.Assignment.orders;

import java.time.LocalDateTime;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static java.time.LocalDateTime.now;

public class Order {
    private final Long seq;

    private final Long user_seq;

    private final Long product_seq;

    private Long review_seq;

    private State state;

    private String request_msg;

    private String reject_msg;

    private LocalDateTime completed_at;

    private LocalDateTime rejected_at;

    private final LocalDateTime create_at;

    public Order(Long seq, Long user_seq, Long product_seq, Long review_seq,
                 State state, String request_msg, String reject_msg, LocalDateTime completed_at,
                 LocalDateTime rejected_at, LocalDateTime create_at) {
        checkNotNull(user_seq, "user_seq must be provided");
        checkNotNull(product_seq, "product_seq must be provided");
        checkArgument(isEmpty(request_msg) || request_msg.length() <= 1000,
                "request_msg length must be less than 1000 characters");
        checkArgument(isEmpty(reject_msg) || reject_msg.length() <= 1000,
                "reject_msg length must be less than 1000 characters");

        this.seq = seq;
        this.user_seq = user_seq;
        this.product_seq = product_seq;
        this.review_seq = review_seq == 0 ? null : review_seq;
        this.state = state;
        this.request_msg = request_msg;
        this.reject_msg = reject_msg;
        this.completed_at = completed_at;
        this.rejected_at = rejected_at;
        this.create_at = defaultIfNull(create_at, now());
    }

    public void setReviewSeq(Long review_seq) {
        this.review_seq = review_seq;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setRequestMsg(String request_msg) {
        checkArgument(isEmpty(request_msg) || request_msg.length() <= 1000,
                "request_msg length must be less than 1000 characters");
        this.request_msg = request_msg;
    }

    public void setRejectMsg(String reject_msg) {
        checkArgument(isEmpty(reject_msg) || reject_msg.length() <= 1000,
                "reject_msg length must be less than 1000 characters");
        this.reject_msg = reject_msg;
    }

    public void setCompletedAt(LocalDateTime completed_at) {
        this.completed_at = completed_at;
    }

    public void setRejectedAt(LocalDateTime rejected_at) {
        this.rejected_at = rejected_at;
    }

    public Long getSeq() {
        return seq;
    }

    public Long getUserSeq() {
        return user_seq;
    }

    public Long getProductSeq() {
        return product_seq;
    }

    public Long getReviewSeq() {
        return review_seq;
    }

    public State getState() {
        return state;
    }

    public String getRequestMsg() {
        return request_msg;
    }

    public String getRejectedMsg() {
        return reject_msg;
    }

    public LocalDateTime getCompletedAt() {
        return completed_at;
    }

    public LocalDateTime getRejectedAt() {
        return rejected_at;
    }

    public LocalDateTime getCreateAt() {
        return create_at;
    }

    public static class Builder {
        private Long seq;
        private Long user_seq;
        private Long product_seq;
        private Long review_seq;
        private State state;
        private String request_msg;
        private String reject_msg;
        private LocalDateTime completed_at;
        private LocalDateTime rejected_at;
        private LocalDateTime create_at;

        public Builder() {}

        public Builder(Order order) {
            this.seq = order.seq;
            this.user_seq = order.user_seq;
            this.product_seq = order.product_seq;
            this.review_seq = order.review_seq;
            this.state = order.state;
            this.request_msg = order.request_msg;
            this.reject_msg = order.reject_msg;
            this.completed_at = order.completed_at;
            this.rejected_at = order.rejected_at;
            this.create_at = order.create_at;
        }

        public Builder seq(Long seq) {
            this.seq = seq;
            return this;
        }

        public Builder user_seq(Long user_seq) {
            this.user_seq = user_seq;
            return this;
        }

        public Builder product_seq(Long product_seq) {
            this.product_seq = product_seq;
            return this;
        }

        public Builder review_seq(Long review_seq) {
            this.review_seq = review_seq;
            return this;
        }

        public Builder state(String status) {
            this.state = State.valueOf(status);
            return this;
        }

        public Builder request_msg(String request_msg) {
            this.request_msg = request_msg;
            return this;
        }

        public Builder reject_msg(String reject_msg) {
            this.reject_msg = reject_msg;
            return this;
        }

        public Builder completed_at(LocalDateTime completed_at) {
            this.completed_at = completed_at;
            return this;
        }

        public Builder rejected_at(LocalDateTime rejected_at) {
            this.rejected_at = rejected_at;
            return this;
        }

        public Builder create_at(LocalDateTime create_at) {
            this.create_at = create_at;
            return this;
        }

        public Order build() {
            return new Order(
                    seq,
                    user_seq,
                    product_seq,
                    review_seq,
                    state,
                    request_msg,
                    reject_msg,
                    completed_at,
                    rejected_at,
                    create_at
            );
        }
    }
}

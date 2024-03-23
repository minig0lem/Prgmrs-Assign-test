package com.programmers.Assignment.orders;

import java.time.LocalDateTime;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static java.time.LocalDateTime.now;

public class Review {
    private final Long seq;

    private final Long user_seq;

    private final Long product_seq;

    private String content;

    private final LocalDateTime create_at;

    public Review(Long seq, Long user_seq, Long product_seq, String content, LocalDateTime create_at) {
        checkNotNull(user_seq, "user_seq must be provided");
        checkNotNull(product_seq, "product_seq must be provided");
        checkArgument(isNotEmpty(content), "content must be provided");
        checkArgument(isEmpty(content) || content.length() <= 1000,
                "content length must be less than 1000 characters");

        this.seq = seq;
        this.user_seq = user_seq;
        this.product_seq = product_seq;
        this.content = content;
        this.create_at = defaultIfNull(create_at, now());
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getSeq() {
        return seq;
    }

    public Long getProductSeq() {
        return product_seq;
    }

    public Long getUserSeq() {
        return user_seq;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreateAt() {
        return create_at;
    }

    static public class Builder {
        private Long seq;
        private Long user_seq;
        private Long product_seq;
        private String content;
        private LocalDateTime create_at;

        public Builder() {}

        public Builder(Review review) {
            this.seq = review.seq;
            this.user_seq = review.user_seq;
            this.product_seq = review.product_seq;
            this.content = review.content;
            this.create_at = review.create_at;
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

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder create_at(LocalDateTime create_at) {
            this.create_at = create_at;
            return this;
        }

        public Review build() {
            return new Review(
                    seq,
                    user_seq,
                    product_seq,
                    content,
                    create_at
            );
        }
    }
}

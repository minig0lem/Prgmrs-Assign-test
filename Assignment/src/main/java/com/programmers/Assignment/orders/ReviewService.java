package com.programmers.Assignment.orders;

import com.programmers.Assignment.errors.NotFoundException;
import com.programmers.Assignment.products.Product;
import com.programmers.Assignment.products.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkArgument;

import java.util.Optional;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public ReviewService(ReviewRepository reviewRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Optional<Review> review(Long orderId, String content) {
        checkNotNull(orderId, "orderId must be provided");

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Could not found order for " + orderId));

        String status = order.getState().getStatus();

        //주문 상태 "COMPLETED" 아닌 경우 오류
        checkArgument(status.equals("COMPLETED"),
                "Could not write review for order " + orderId + " because state(" + status + ") is not allowed");

        //중복 리뷰 작성 오류
        checkArgument(order.getReviewSeq() == null,
                "Could not write review for order " + orderId + " because have already written");

        //리뷰 작성
        Long reviewId = reviewRepository.save(
                new Review(null, order.getUserSeq(), order.getProductSeq(), content, null)
        );

        Product product = productRepository.findById(order.getProductSeq())
                .orElseThrow(() -> new NotFoundException("Could not found product for " + order.getProductSeq()));

        //상품 테이블의 리뷰 수 증가
        product.setReviewCount(1);
        productRepository.update(product);
        //주문 테이블의 리뷰 id 설정
        order.setReviewSeq(reviewId);
        orderRepository.update(order);

        return this.findById(reviewId);
    }

    @Transactional(readOnly = true)
    public Optional<Review> findById(Long id) {
        return reviewRepository.findById(id);
    }
}


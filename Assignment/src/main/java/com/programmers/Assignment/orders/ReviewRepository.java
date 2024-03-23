package com.programmers.Assignment.orders;

import java.util.Optional;

public interface ReviewRepository {
    Long save(Review review);

    Optional<Review> findById(Long id);
}
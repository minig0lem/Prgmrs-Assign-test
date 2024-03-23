package com.programmers.Assignment.orders;

import com.programmers.Assignment.configures.web.Pageable;

import java.util.List;
import java.util.Optional;


public interface OrderRepository {
    Optional<Order> findById(Long id);

    void update(Order order);

    List<Order> findAll(Pageable pageable);
}
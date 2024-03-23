package com.programmers.Assignment.orders;

import java.util.List;
import java.util.Optional;

import com.programmers.Assignment.configures.web.Pageable;
import com.programmers.Assignment.errors.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.time.LocalDateTime.now;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Order> findById(Long orderId) {
        checkNotNull(orderId, "orderId must be provided");

        return orderRepository.findById(orderId);
    }

    @Transactional(readOnly = true)
    public List<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Transactional
    public Boolean accept(Long orderId) {
        checkNotNull(orderId, "orderId must be provided");

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Could not found order for " + orderId));

        if(order.getState() == State.REQUESTED) {
            order.setState(State.ACCEPTED);
            orderRepository.update(order);
            return true;
        }
        return false;
    }

    @Transactional
    public Boolean reject(Long orderId, String message) {
        checkNotNull(orderId, "orderId must be provided");
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Could not found order for " + orderId));

        if(order.getState() == State.REQUESTED) {
            order.setState(State.REJECTED);
            order.setRejectMsg(message);
            order.setRejectedAt(now());
            orderRepository.update(order);
            return true;
        }
        return false;
    }

    @Transactional
    public Boolean shipping(Long orderId) {
        checkNotNull(orderId, "orderId must be provided");
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Could not found order for " + orderId));

        if(order.getState() == State.ACCEPTED) {
            order.setState(State.SHIPPING);
            orderRepository.update(order);
            return true;
        }
        return false;
    }

    @Transactional
    public Boolean complete(Long orderId) {
        checkNotNull(orderId, "orderId must be provided");
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Could not found order for " + orderId));

        if(order.getState() == State.SHIPPING) {
            order.setState(State.COMPLETED);
            order.setCompletedAt(now());
            orderRepository.update(order);
            return true;
        }
        return false;
    }
}

package com.programmers.Assignment.orders;


import com.programmers.Assignment.configures.web.Pageable;
import com.programmers.Assignment.errors.NotFoundException;
import com.programmers.Assignment.utils.ApiUtils.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.programmers.Assignment.utils.ApiUtils.success;
import static java.util.stream.Collectors.toList;
import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("api/orders")
public class OrderRestController {
    private final OrderService orderService;
    private final ReviewService reviewService;

    public OrderRestController(OrderService orderService, ReviewService reviewService) {
        this.orderService = orderService;
        this.reviewService = reviewService;
    }

    // TODO findAll, findById, accept, reject, shipping, complete 메소드 구현이 필요합니다.

    @GetMapping()
    public ApiResult<List<OrderResponse>> findAll(Pageable pageable) {
        return success(
                orderService.findAll(pageable)
                        .stream()
                        .map(o -> {
                            Review review = o.getReviewSeq() == null ? null : reviewService.findById(o.getReviewSeq()).get();
                            return review == null ? new OrderResponse(o, null)
                                    : new OrderResponse(o, new ReviewResponse(review));
                        })
                        .collect(toList())
        );
    }

    @GetMapping(path = "{id}")
    public ApiResult<OrderResponse> findById(@PathVariable Long id) {
        Order order = orderService.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not found order for " + id));

        Review review = order.getReviewSeq() == null ? null : reviewService.findById(order.getReviewSeq()).get();

        return review == null ? success(new OrderResponse(order, null))
                : success(new OrderResponse(order, new ReviewResponse(review)));
    }

    @PatchMapping(path = "{id}/accept")
    public ApiResult<Boolean> accept(@PathVariable Long id) {
        return success(orderService.accept(id));
    }

    @PatchMapping(path = "{id}/reject")
    public ApiResult<Boolean> reject(@PathVariable Long id, @Valid @RequestBody(required = false) OrderRequest orderRequest) {
        checkArgument(orderRequest != null, "request body must be provided");
        return success(orderService.reject(id, orderRequest.getMessage()));
    }

    @PatchMapping(path = "{id}/shipping")
    public ApiResult<Boolean> shipping(@PathVariable Long id){
        return success(orderService.shipping(id));
    }

    @PatchMapping(path = "{id}/complete")
    public ApiResult<Boolean> complete(@PathVariable Long id) {
        return success(orderService.complete(id));
    }

}

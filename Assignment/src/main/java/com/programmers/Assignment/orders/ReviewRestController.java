package com.programmers.Assignment.orders;

import com.programmers.Assignment.errors.NotFoundException;
import com.programmers.Assignment.utils.ApiUtils.ApiResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;

import static com.programmers.Assignment.utils.ApiUtils.success;

@RestController
@RequestMapping("api/orders")
public class ReviewRestController {
    private final ReviewService reviewService;

    public ReviewRestController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // TODO review 메소드 구현이 필요합니다.
    @PostMapping(path = "{id}/review")
    public ApiResult<ReviewResponse> review(@PathVariable Long id,
                                            @Valid @RequestBody ReviewRequest reviewRequest) {
        return success(new ReviewResponse(
                reviewService.review(id, reviewRequest.getContent())
                        .orElseThrow(() -> new NotFoundException("리뷰 작성 오류"))
        ));
    }
}

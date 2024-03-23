package com.programmers.Assignment.orders;

import java.util.List;
import java.util.Optional;


import com.programmers.Assignment.configures.web.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import static com.programmers.Assignment.utils.DateTimeUtils.dateTimeOf;


@Repository
public class JdbcOrderRepository implements OrderRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Order> findById(Long id) {
        String sql = "SELECT * FROM orders WHERE seq=?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, mapper, id));
    }

    @Override
    public void update(Order order) {
        String sql = "UPDATE orders SET review_seq=?, state=?, request_msg=?, reject_msg=?, completed_at=?, rejected_at=? WHERE seq=?";
        jdbcTemplate.update(sql,
                order.getReviewSeq(),
                order.getState().getStatus(),
                order.getRequestMsg(),
                order.getRejectedMsg(),
                order.getCompletedAt(),
                order.getRejectedAt(),
                order.getSeq()
        );
    }

    @Override
    public List<Order> findAll(Pageable pageable) {
        Long offset = pageable.getOffset();
        int size = pageable.getSize();
        return jdbcTemplate.query(
                "SELECT * FROM orders ORDER BY seq DESC LIMIT ?, ?"
                ,mapper, offset, size
        );
    }

    static RowMapper<Order> mapper = (rs, rowNum) ->
            new Order.Builder()
                    .seq(rs.getLong("seq"))
                    .user_seq(rs.getLong("user_seq"))
                    .product_seq(rs.getLong("product_seq"))
                    .review_seq(rs.getLong("review_seq"))
                    .state(rs.getString("state"))
                    .request_msg(rs.getString("request_msg"))
                    .reject_msg(rs.getString("reject_msg"))
                    .completed_at(dateTimeOf(rs.getTimestamp("completed_at")))
                    .rejected_at(dateTimeOf(rs.getTimestamp("rejected_at")))
                    .create_at(dateTimeOf(rs.getTimestamp("create_at")))
                    .build();
}
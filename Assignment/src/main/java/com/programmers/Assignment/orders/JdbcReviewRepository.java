package com.programmers.Assignment.orders;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.util.Optional;

import static com.programmers.Assignment.utils.DateTimeUtils.dateTimeOf;

@Repository
public class JdbcReviewRepository implements ReviewRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcReviewRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long save(Review review) {
        String sql = "INSERT INTO reviews(user_seq, product_seq, content) VALUES(?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc -> {
            PreparedStatement ps = psc.prepareStatement(sql, new String[]{"seq"});
            ps.setLong(1, review.getUserSeq());
            ps.setLong(2, review.getProductSeq());
            ps.setString(3, review.getContent());
            return ps;
        }, keyHolder);
        long key = keyHolder.getKey().longValue();

        return key;
    }

    @Override
    public Optional<Review> findById(Long id) {
        String sql = "SELECT * FROM reviews WHERE seq=?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, mapper, id));
    }

    static RowMapper<Review> mapper = (rs, rowNum) ->
            new Review.Builder()
                    .seq(rs.getLong("seq"))
                    .user_seq(rs.getLong("user_seq"))
                    .product_seq(rs.getLong("product_seq"))
                    .content(rs.getString("content"))
                    .create_at(dateTimeOf(rs.getTimestamp("create_at")))
                    .build();

}
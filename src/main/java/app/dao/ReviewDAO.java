package app.dao;

import entity.Review;
import java.util.List;

public interface ReviewDao {
    void save(Review review);
    Review findById(int reviewId);
    List<Review> findAll();
    void update(Review review);
    void delete(int reviewId);
}

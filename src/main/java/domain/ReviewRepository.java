package domain;

import entity.OperationResult;
import entity.Review;

import java.util.Optional;
import java.util.List;

public interface ReviewRepository {

    OperationResult add(Review review);

    List<Review> findUserReviews(String restaurantId);

    Optional<Review> findSummarizedReview(String restaurantId);

    List<Review> findAll();

    OperationResult save(Review review);

    OperationResult update(Review review);

    boolean deleteUserById(String restaurantId);

    boolean deleteSummarizedById(String restaurantId);

    boolean deleteAllById(String restaurantId);

    boolean clearAll();
}

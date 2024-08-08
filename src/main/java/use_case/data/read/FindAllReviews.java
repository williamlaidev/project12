package use_case.data.read;

import domain.ReviewRepository;
import entity.review.Review;
import java.util.List;

/**
 * Use case for retrieving all reviews.
 * It uses the {@link ReviewRepository} to perform the retrieval.
 */
public class FindAllReviews {

    private final ReviewRepository repository;

    /**
     * Creates an instance with the given repository.
     *
     * @param repository the {@link ReviewRepository} for retrieving all reviews.
     */
    public FindAllReviews(ReviewRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all reviews.
     *
     * @return a {@link List} of {@link Review}.
     */
    public List<Review> execute() {
        return repository.findAll();
    }
}

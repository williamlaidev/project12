package use_case.data;

import domain.ReviewRepository;

public class ClearAllReviews {

    private final ReviewRepository repository;

    public ClearAllReviews(ReviewRepository repository) {
        this.repository = repository;
    }

    public boolean execute() {
        return repository.clearAll();
    }
}

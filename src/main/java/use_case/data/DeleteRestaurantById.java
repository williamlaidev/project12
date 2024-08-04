package use_case.data;

import domain.RestaurantRepository;

public class DeleteRestaurantById {
    private final RestaurantRepository repository;

    public DeleteRestaurantById(RestaurantRepository repository) {
        this.repository = repository;
    }

    public boolean execute(String id) {
        return repository.deleteById(id);
    }
}

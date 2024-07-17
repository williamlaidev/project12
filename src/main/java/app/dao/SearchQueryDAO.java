package app.dao;

import main.java.entity.SearchQuery;
import java.util.List;

public interface SearchQueryDao {
    void save(SearchQuery searchQuery);
    SearchQuery findById(int searchQueryId);
    List<SearchQuery> findAll();
    void update(SearchQuery searchQuery);
    void delete(int searchQueryId);
}

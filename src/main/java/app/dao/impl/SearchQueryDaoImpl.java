import app.dao.SearchQueryDao;
import main.java.entity.SearchQuery;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class SearchQueryDaoImpl implements SearchQueryDao {
    private Connection connection;

    public SearchQueryDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(SearchQuery searchQuery) {
        // SQL to save search query
    }

    @Override
    public SearchQuery findById(int searchQueryId) {
        // SQL to find search query by ID
        return null;
    }

    @Override
    public List<SearchQuery> findAllByUserId(int userId) {
        // SQL to find all search queries by user ID
        return new ArrayList<>();
    }

    @Override
    public void delete(int searchQueryId) {
        // SQL to delete search query
    }
}

package interface_adapter;

import use_case.SearchInputBoundary;
import use_case.SearchInputData;

import java.awt.Point;

public class SearchController {
    final SearchInputBoundary searchInputBoundary;

    public SearchController(SearchInputBoundary searchInputBoundary) {
        this.searchInputBoundary = searchInputBoundary;
    }

    public void execute(Point mousePosition, String distance, String dishType) {
        SearchInputData searchInputData = new SearchInputData(mousePosition, distance, dishType);
        searchInputBoundary.execute(searchInputData);
    }
}

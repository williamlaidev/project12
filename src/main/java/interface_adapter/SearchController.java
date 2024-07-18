package main.java.interface_adapter;

import main.java.use_case.SearchInputBoundary;
import main.java.use_case.SearchInputData;

import java.awt.*;

public class SearchController {

    final SearchInputBoundary userSearchUseCaseInteractor;
    public SearchController(SearchInputBoundary userSearchUseCaseInteractor) {
        this.userSearchUseCaseInteractor = userSearchUseCaseInteractor;
    }

    public void execute(Point mapPosition, String distance, String dishType) {
        SearchInputData inputData = new SearchInputData(mapPosition, distance, dishType);
        userSearchUseCaseInteractor.execute(inputData);
    }

}

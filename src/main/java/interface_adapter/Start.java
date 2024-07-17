package main.java.interface_adapter;

import main.java.use_case.SearchInputBoundary;
import main.java.use_case.SearchInputData;
import main.java.view.SearchView;

import javax.swing.*;
import java.awt.*;

public class Start {
    public static void main(String[] args) {
        JFrame application = new JFrame("Search Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        SearchViewModel searchViewModel = new SearchViewModel();
        SearchInputBoundary searchInputBoundary = new SearchInputBoundary() {
            @Override
            public void execute(SearchInputData searchInputData) {

            }
        };
        SearchController searchController = new SearchController(searchInputBoundary);
        SearchView searchView = new SearchView(searchController, searchViewModel);
        views.add(searchView, searchView.viewName);
    }
}


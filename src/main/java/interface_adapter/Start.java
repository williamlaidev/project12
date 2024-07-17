package main.java.interface_adapter;



import main.java.interface_adapter.SearchController;
import main.java.interface_adapter.SearchViewModel;
import main.java.view.SearchView;
import main.java.use_case.SimpleSearchInteractor;

import javax.swing.*;

public class Start {
    public static void main(String[] args) {
        // Create the SimpleSearchInteractor
        SimpleSearchInteractor simpleSearchInteractor = new SimpleSearchInteractor();

        // Create the controller and view model
        SearchController searchController = new SearchController(simpleSearchInteractor);
        SearchViewModel searchViewModel = new SearchViewModel();

        // Create the SearchView
        SearchView searchView = new SearchView(searchController, searchViewModel);

        // Set up the JFrame
        JFrame frame = new JFrame("Search Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setContentPane(searchView);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }
}

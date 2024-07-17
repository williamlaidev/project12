package main.java.view;

import main.java.interface_adapter.SearchController;
import main.java.interface_adapter.SearchState;
import main.java.interface_adapter.SearchViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SearchView extends JPanel implements ActionListener, PropertyChangeListener, MouseListener{
    public final String viewName = "search";

    private final SearchViewModel searchViewModel;
    private final JOptionPane dishTypeChoiceField = new JOptionPane();
    private final JTextField distanceInputField = new JTextField(15);
    private Point mousePosition;

    private final SearchController searchController;

    private JButton searchButton = new JButton("Search");

    public SearchView(SearchController controller, SearchViewModel searchViewModel){
        this.searchController = controller;
        this.searchViewModel = searchViewModel;
        searchViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(searchViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel distanceInfo = new LabelTextPanel(
                new JLabel(searchViewModel.DISTANCE_LABEL), distanceInputField);

        addMouseListener(this);
        Point mapPosition = mousePosition;

        String[] options = {"--", "dish1", "dish2", "dish3", "dish4", "dish5"};
        String dishType = (String) dishTypeChoiceField.showInputDialog(null,"choose your dish type\n",
                "Title",JOptionPane.QUESTION_MESSAGE,new ImageIcon("D://地球.png"), options,"--");

        JPanel buttons = new JPanel();
        searchButton = new JButton(searchViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(searchButton);
        searchButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(searchButton)) {
                            searchController.execute(mapPosition,

                                    distanceInputField.getText(),

                                    dishType

                                    );
                        }
                    }
                }
        );




    }

    public void mouseClicked(MouseEvent mouseEvent) {
        int x = mouseEvent.getX();
        int y = mouseEvent.getY();
        if (x< 100 && y< 100){
            this.mousePosition = mouseEvent.getPoint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Not implemented yet.");
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}



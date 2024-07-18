package main.java.view;
import java.awt.event.*;
import javax.swing.*;

public class StartFrame extends JFrame {

    private JPanel contentPane;
    private JButton searchButton;
    private JTextField searchBar;
    private JLabel searchLabel;

    public StartFrame() {

        int windowWidth = 960;
        int windowHeight = 600;


        JFrame startMenu = new JFrame("Start Window");


        searchBar = new JTextField();
        int[] searchBarPosition = CalculatePosition.SearchBar(windowWidth, windowHeight);
        searchBar.setBounds(searchBarPosition[0], searchBarPosition[1], searchBarPosition[2], searchBarPosition[3]);




        searchLabel = new JLabel("Add your desired dish here!");
        int[] searchHintPosition = CalculatePosition.SearchHint(windowWidth, windowHeight);
        searchLabel.setBounds(searchHintPosition[0], searchHintPosition[1],
                searchHintPosition[2], searchHintPosition[3]);

        searchButton=  new JButton("Search");
        int[] searchButtonPosition = CalculatePosition.SearchButton(windowWidth, windowHeight);
        searchButton.setBounds(searchButtonPosition[0],searchButtonPosition[1],
                searchButtonPosition[2],searchButtonPosition[3]);
        searchButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                searchLabel.setText("The map should pop here {map}");
            }
        });

        startMenu.add(searchButton);
        startMenu.add(searchBar);
        startMenu.add(searchLabel);
        startMenu.setSize(windowWidth, windowHeight);
        startMenu.setLayout(null);
        startMenu.setVisible(true);
        startMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
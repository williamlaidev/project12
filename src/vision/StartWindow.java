package vision;
import java.awt.event.*;
import javax.swing.*;

public class StartWindow {

    public static void main(String[] args) {

        int width = 960;
        int height = 600;


        JFrame startMenu=new JFrame("Start Window");

        final JTextField searchField=new JTextField();
        int[] searchBarPosition = CalculatePosition.SearchBar(width, height);
        searchField.setBounds(searchBarPosition[0], searchBarPosition[1], searchBarPosition[2], searchBarPosition[3]);

        JButton searchButton=new JButton("Search");
        int[] searchButtonPosition = CalculatePosition.SearchButton(width, height);
        searchButton.setBounds(searchButtonPosition[0],searchButtonPosition[1],
                searchButtonPosition[2],searchButtonPosition[3]);
        searchButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                searchField.setText("Welcome to Javatpoint.");
            }
        });


        JLabel searchHint = new JLabel("Add your desired dish here!");
        int[] searchHintPosition = CalculatePosition.SearchHint(width, height);
        searchHint.setBounds(searchHintPosition[0], searchHintPosition[1],
                searchHintPosition[2], searchHintPosition[3]);

        startMenu.add(searchButton);
        startMenu.add(searchField);
        startMenu.add(searchHint);
        startMenu.setSize(width, height);
        startMenu.setLayout(null);
        startMenu.setVisible(true);
        startMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

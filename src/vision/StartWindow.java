package vision;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class StartWindow {
    private static int width;
    private static int height;
    private ArrayList CalculatePosition(String type) {
        ArrayList result = new ArrayList();
        if (type.equals("Search")) {
            result.add(width/3*2);
            result.add(height/3*2);
        }
        return result;
    };
    public static void main(String[] args) {

        width = 1920;
        height = 1080;


        JFrame startMenu=new JFrame("Start Window");

        final JTextField searchField=new JTextField();
        searchField.setBounds(50,50, 150,20);

        JButton searchButton=new JButton("Search");
        int[] searchPosition = CalculatePosition.Search(width, height);
        searchButton.setBounds(searchPosition[0],searchPosition[1],searchPosition[2],searchPosition[3]);
        searchButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                searchField.setText("Welcome to Javatpoint.");
            }
        });

        JLabel searchHint = new JLabel("Add your desired dish here!");
        searchHint.setBounds(100,200, 500,30);

        startMenu.add(searchButton);
        startMenu.add(searchField);
        startMenu.add(searchHint);
        startMenu.setSize(width,height);
        startMenu.setLayout(null);
        startMenu.setVisible(true);
        startMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

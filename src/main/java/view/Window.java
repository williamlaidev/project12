package main.java.view;
import javax.swing.*;

public class Window {
    public static void main(String[] args) {
        JFrame f=new JFrame("Window Example");
        JButton b=new JButton("Click Here");
        b.setBounds(50,100,95,30);
        f.add(b);
        f.setSize(900,600);
        f.setLayout(null);
        f.setVisible(true);
    }
}

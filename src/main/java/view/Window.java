package main.java.view;
import javax.swing.*;

public class Window {
    public static void main(String[] args) {
        JFrame f=new JFrame("Window Example");
        JButton b=new JButton("Search");
        b.setBounds(50,300,95,30);
        f.add(b);

        JTextField t1,t2;
        t1=new JTextField("Enter your distance here");
        t1.setBounds(50,100, 200,30);
        t2=new JTextField("Enter your dish type here");
        t2.setBounds(50,150, 200,30);
        f.add(t1); f.add(t2);

        f.setSize(900,600);
        f.setLayout(null);
        f.setVisible(true);
    }
}

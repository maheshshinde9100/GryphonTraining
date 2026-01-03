package UI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Day6_FormWithEventHandling implements ActionListener{
    JLabel l1,l2;
    JTextField tf1,tf2;
    JButton b1,b2,b3;
    JFrame frame;
    Day6_FormWithEventHandling(){
        frame = new JFrame("This is Basic Form");
        l1 = new JLabel("Enter Username : ");
        l2 = new JLabel("Enter Password : ");
        tf1 = new JTextField(15);
        tf2 = new JTextField(15);
        b1 = new JButton("Clear");
        b2 = new JButton("Submit");
        b3 = new JButton("Exit");

        frame.add(l1);
        frame.add(tf1);
        frame.add(l2);
        frame.add(tf2);
        frame.add(b1);
        frame.add(b2);
        frame.add(b3);

        frame.setLayout(new FlowLayout());
        frame.setSize(400,150);
        frame.setVisible(true);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new Day6_FormWithEventHandling();
    }
}

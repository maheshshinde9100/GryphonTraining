package UI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Day6_SwingCalculator implements ActionListener{
    JLabel l1,l2,l3;
    JTextField tf1,tf2;
    JButton b1,b2,b3,b4;
    JFrame frame;
    Day6_SwingCalculator(){
        frame = new JFrame("Basic Calculator : ");
        l1 = new JLabel("Enter Num1 : ");
        l2 = new JLabel("Enter Num2 : ");
        l3 = new JLabel("OUTPUT : ");
        tf1 = new JTextField(15);
        tf2 = new JTextField(15);
        b1 = new JButton("ADDITION");
        b2 = new JButton("SUBSTRACTION");
        b3 = new JButton("DIVISION");
        b4 = new JButton("MULTIPLICATION");

        frame.add(l1);
        frame.add(tf1);
        frame.add(l2);
        frame.add(tf2);
        frame.add(b1);
        frame.add(b2);
        frame.add(b3);
        frame.add(b4);
        frame.add(l3);

        frame.setLayout(new GridLayout(4,2));
        frame.setSize(400,150);
        frame.setVisible(true);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int n1 = Integer.parseInt(tf1.getText());
        int n2 = Integer.parseInt(tf2.getText());
        JButton source = (JButton) e.getSource();
        if(source.getText()=="ADDITION"){
            l3.setText("OUTPUT : "+(n1+n2));
        }else if(source.getText()=="SUBSTRACTION"){
            l3.setText("OUTPUT : "+(n1-n2));
        }else if(source.getText()=="MULTIPLICATION"){
            l3.setText("OUTPUT : "+(n1*n2));
        }else if(source.getText()=="DIVISION"){
            l3.setText("OUTPUT : "+(n1/n2));
        }
    }

    public static void main(String[] args) {
        new Day6_SwingCalculator();
    }
}

package UI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class Day6_CardLayout implements ActionListener {
    JFrame frame;
    JButton openCalculator;
    JPanel pp;
    CardLayout cd;
    Day6_CardLayout(){
        frame = new JFrame("CardLayout Example");
        cd = new CardLayout();
        openCalculator = new JButton("Click to OPEN calculator: ");
        openCalculator.addActionListener(this);
        //creating parent panel that containing 2 child panels
        pp = new JPanel();
        pp.setLayout(cd);
//        creating two child panels
        JPanel cp1 = new JPanel(); //numbers
        JPanel cp2 = new JPanel(); //alphabets

        //creating 2 parent buttons(numbers and alphabets)
        JButton b1 = new JButton("AMAZON");
        JButton b2 = new JButton("META");

//        creating three child1 buttons(1,2,3)
        JButton b3 = new JButton("Frontend Engineer");
        JButton b4 = new JButton("Java Developer");
        JButton b5 = new JButton("Java + Microservices developer");
//        adding b3,b4,b5 to child panel1
        cp1.add(b3);
        cp1.add(b4);
        cp1.add(b5);
        cp1.setVisible(true);
//        cp1.setLayout(new FlowLayout());
        cp1.setBackground(Color.CYAN);

        //        creating four child12 buttons(A,B,C,D)
        JButton b6 = new JButton("AI Engineer");
        JButton b7 = new JButton("Backend Developer");
        JButton b8 = new JButton("Data Scientist");
        JButton b9 = new JButton("Fullstack Developer");

//        adding b3,b4,b5 to child panel2
        cp2.add(b6);
        cp2.add(b7);
        cp2.add(b8);
        cp2.add(b9);
        cp2.setVisible(true);
//        cp2.setLayout(new FlowLayout());
        cp2.setBackground(Color.GREEN);

        pp.add(cp1,"Amazon");
        pp.add(cp2,"Meta");
        pp.setLayout(cd);
        pp.setVisible(true);

        b1.addActionListener(this);
        b2.addActionListener(this);

        frame.add(b1);
        frame.add(b2);
        frame.add(pp);
        frame.add(openCalculator);
        frame.setVisible(true);
        frame.setSize(500,500);
        frame.setLayout(new FlowLayout());
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand() =="Amazon"){
            cd.show(pp,"Amazon");
            frame.setBackground(Color.CYAN);
        }else if(e.getActionCommand()=="Meta"){
            cd.show(pp,"Meta");
            frame.setBackground(Color.GREEN);
        }else {
            JButton calcBtn = (JButton) e.getSource();
            if(calcBtn.getText()=="Click to OPEN calculator: "){
                openCalc();
            }
        }
    }
    public static void openCalc(){
        Day6_SwingCalculator obj = new Day6_SwingCalculator();
    }
    public static void main(String[] args) {
        new Day6_CardLayout();
    }
}

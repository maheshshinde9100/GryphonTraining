package UI;

import java.awt.*;
import java.awt.event.*;

public class Day6_MouseEvent extends WindowAdapter implements MouseListener {
    Label l1,l2;
    Frame f;
    String str;
    Day6_MouseEvent(){
        f = new Frame("WINDOW");
        l1 = new Label("Handling mouse events in the frame window..",Label.CENTER);
        l2 = new Label();
        f.add(l1);
        f.add(l2);
        f.setVisible(true);
        f.setSize(500,500);
        f.setLayout(new FlowLayout());
        f.addMouseListener(this);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    public void mouseEntered(MouseEvent e){
        l2.setText("Mouse Entered..");
        f.setBackground(Color.RED);
    }
    public void mouseClicked(MouseEvent e){
        l2.setText("Mouse Clicked..");
        f.setBackground(Color.GREEN);
    }
    public void mouseReleased(MouseEvent e){
        l2.setText("Mouse Released..");
        f.setBackground(Color.CYAN);

    }
    public void mousePressed(MouseEvent e){
        l2.setText("Mouse Pressed..");
        f.setBackground(Color.YELLOW);

    }
    public void mouseExited(MouseEvent e){
        l2.setText("Mouse Exited..");
        f.setBackground(Color.PINK);
    }

    public static void main(String[] args) {
        new Day6_MouseEvent();
    }

}

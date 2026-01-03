package UI;
import java.awt.*;
import java.awt.event.*;
//By using Adapter classes we can use only required methods instead overriding all from Event Listeners
public class Day6_UI_2 extends Frame {
    Label l;
    TextField tf;
    Day6_UI_2(){
        l = new Label("Enter You Name: ");
        tf = new TextField("eg.,mahesh");
        add(l);
        add(tf);
        setSize(500,500);
        setVisible(true);
        setLayout(new FlowLayout());
        setTitle("This is Frame Title");

       this.addWindowListener(new WindowAdapter() {
           @Override
           public void windowClosing(WindowEvent e) {
               System.exit(0);
           }
       });
    }

    public static void main(String[] args) {
        Day6_UI_2 obj = new Day6_UI_2();
    }
}

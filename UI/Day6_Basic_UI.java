package UI;
import java.awt.*;

public class Day6_Basic_UI extends Frame{
    Day6_Basic_UI(){
        Button b = new Button("Home");
        b.setBounds(250,250,100,50);
        add(b);
        setSize(300,300);
        setLayout(null);
        setVisible(true);

    }
    public static void main(String[] args) {
        Day6_Basic_UI obj = new Day6_Basic_UI();
    }
}

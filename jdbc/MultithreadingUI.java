import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JTextField;

class BookingSystem extends Thread{
    static int available = 10;
    private int seat;
    private String user;
    BookingSystem(int seat,String user){
        this.seat = seat;
        this.user = user;
    }
    public void run(){
        synchronized(BookingSystem.class){
            if(available>=seat){
                MultithreadingUI.textArea.append("User : "+user+",  Seats : "+seat);
                available = available - seat;
            }else{

            }
        }
    }
}

public class MultithreadingUI extends JFrame implements ActionListener{
    JLabel l1,l2,l3;
    JTextField tf1,tf2;
    static JTextArea textArea;
    JButton b1;
    MultithreadingUI(int seat,String user){
        
    }

    MultithreadingUI(){
        setTitle("Multithreading Seat Booking : ");
        setSize(500,500);
        setLayout(new FlowLayout());

        l1 = new JLabel("Enter Username : ");
        tf1 = new JTextField(50);
        l2 = new JLabel("Enter Seats to book(comma seperated) : ");
        tf2 = new JTextField(50);

        textArea = new JTextArea(10,45);
        textArea.setEditable(false);

        l3 = new JLabel("Enter Seat numbers seperated by commas : ");

        add(l1);
        add(tf1);
        add(l2);
        add(tf2);
        add(textArea);
        add(b1);

        b1.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

    }
    public void actionPerformed(ActionEvent e){
        try{
            String baseName= tf1.getText().trim();
            String[] seatArr = tf2.getText().trim().split(",");
            for(int i=0;i<seatArr.length;i++){
                int seat = Integer.parseInt(seatArr[i].trim());
                String threadName = baseName+" : Thread : "+(i+1);

                BookingSystem op = new BookingSystem(seat,threadName);
                op.start();
            }

        }catch(NumberFormatException ex){
            l3.setText("Error : Please enter valid seat numbers seperated by commas..");
        }
    }
    public static void main(String[] args) {
        new MultithreadingUI();
    }
}

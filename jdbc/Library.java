import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
public class Library implements ActionListener {
    Frame f;
    Label l1, l2, l3,l4;
    Button first, next, prev, last,avg,highest, insert ;
    TextField tf1, tf2, tf3,tf4;
    Label showAvg;


    final String username = "root";
    final String password = "mahesh";
    final String dbUrl = "jdbc:mysql://localhost:3306/crud_db";
    ResultSet rs;
    Library() {
        f = new Frame();

        l1 = new Label("Book ID : ");
        l1.setBounds(10, 50, 100, 50);
        l2 = new Label("Boook Name : ");
        l2.setBounds(10, 100, 100, 50);
        l3 = new Label("Book Price : ");
        l3.setBounds(10, 150, 100, 50);

        l4 = new Label("Book Author");
        l4.setBounds(10,200,100,50);
        tf4  = new TextField();
        tf4.setBounds(200, 200, 100, 50);

        tf1 = new TextField(50);
        tf1.setBounds(200, 50, 100, 50);
        tf2 = new TextField(50);
        tf2.setBounds(200, 100, 100, 50);

        tf3 = new TextField(50);
        tf3.setBounds(200, 150, 100, 50);


        first = new Button("First");
        first.setBounds(50, 300, 100, 100);

        next = new Button("Next");
        next.setBounds(200, 300, 100, 100);
        prev = new Button("Prev");
        prev.setBounds(50, 400, 100, 100);
        last = new Button("Last");
        last.setBounds(200, 400, 100, 100);

        highest = new Button("Highest");
        highest.setBounds(350, 300, 100, 100);

        insert = new Button("Insert");
        insert.setBounds(50, 500, 100, 100);

        avg = new Button("Get Average Book Price : ");
        avg.setBounds(250, 550, 100, 100);

        showAvg = new Label("Average Price : ");
        showAvg.setBounds(400,550,200,100);

        f.add(l1);
        f.add(tf1);
        f.add(l2);
        f.add(tf2);
        f.add(l3);
        f.add(tf3);
        f.add(l4);
        f.add(tf4);

        f.add(first);
        f.add(next);
        f.add(prev);
        f.add(last);
        f.add(highest);
        f.add(insert);
        f.add(avg);
        f.add(showAvg);

        first.addActionListener(this);
        next.addActionListener(this);
        prev.addActionListener(this);
        last.addActionListener(this);
        highest.addActionListener(this);
        insert.addActionListener(this);

        f.setLayout(null);
        f.setVisible(true);
        f.setSize(500, 500);
        setData(dbUrl, username, password, "select * from library;");
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == first) {
            setFirst(rs);
        }
        if (e.getSource() == next) {
            setNext(rs);
        }
        if (e.getSource() == prev) {
            setPrev(rs);
        }
        if (e.getSource() == last) {
            setLast(rs);
        }
        if(e.getSource()==avg){
            getAvg(rs);
        }
        if(e.getSource()==highest){
            getHighest(rs);
        }
        if(e.getSource()==insert){
            insertBook();
        }
    }

    void getAvg(ResultSet rs){
        try{
            int currentRow = rs.getRow();
            boolean wasBeforeFirst = rs.isBeforeFirst();
            rs.beforeFirst();
            double sum = 0;
            int count = 0;
            while (rs.next()) {
                sum += rs.getDouble(3);
                count++;
            }
            double avg = count > 0 ? sum / count : 0;
            showAvg.setText("Average Price: " + String.format("%.2f", avg));
            if (wasBeforeFirst) {
                rs.beforeFirst();
            } else {
                rs.absolute(currentRow);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    void setData(String dbUrl, String username, String password,String query) {
        try {

            Connection con = DriverManager.getConnection(dbUrl, username, password);
            Statement stmt = con.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
            );
            this.rs = stmt.executeQuery(query);
            setFirst(rs);
            getAvg(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void setLast(ResultSet rs){
        try {

            if (rs.last()) {
               tf1.setText(String.valueOf(rs.getInt(1)));
                tf2.setText(rs.getString(2));
                tf3.setText(String.valueOf(rs.getInt(3)));
                tf4.setText(rs.getString(4));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void setFirst(ResultSet rs){
        try {
            if (rs.first()) {
               tf1.setText(String.valueOf(rs.getInt(1)));
                tf2.setText(rs.getString(2));
                tf3.setText(String.valueOf(rs.getInt(3)));
                tf4.setText(rs.getString(4));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void setNext(ResultSet rs) {
        try {

            if (rs.next()) {
                tf1.setText(String.valueOf(rs.getInt(1)));
                tf2.setText(rs.getString(2));
                tf3.setText(String.valueOf(rs.getInt(3)));
                tf4.setText(rs.getString(4));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        void setPrev(ResultSet rs) {
        try {
            if(rs.previous()){
                tf1.setText(String.valueOf(rs.getInt(1)));
                tf2.setText(rs.getString(2));
                tf3.setText(String.valueOf(rs.getInt(3)));
                tf4.setText(rs.getString(4));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void getHighest(ResultSet rs){
        try{
            int currentRow = rs.getRow();
            boolean wasBeforeFirst = rs.isBeforeFirst();
            rs.beforeFirst();
            double maxPrice = Double.MIN_VALUE;
            int maxRow = -1;
            while (rs.next()) {
                double price = rs.getDouble(3);
                if (price > maxPrice) {
                    maxPrice = price;
                    maxRow = rs.getRow();
                }
            }
            if (maxRow != -1) {
                rs.absolute(maxRow);
                tf1.setText(String.valueOf(rs.getInt(1)));
                tf2.setText(rs.getString(2));
                tf3.setText(String.valueOf(rs.getInt(3)));
                tf4.setText(rs.getString(4));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    void insertBook(){
        try{
            Connection con = DriverManager.getConnection(dbUrl, username, password);
            String query = "INSERT INTO library(id,bookname, price, author) VALUES (?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, Integer.parseInt(tf1.getText()));
            pstmt.setString(2, tf2.getText());
            pstmt.setDouble(3, Double.parseDouble(tf3.getText()));
            pstmt.setString(4, tf4.getText());
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
            setData(dbUrl, username, password, "select * from library;");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        new Library();
    }
}



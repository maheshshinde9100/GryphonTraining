import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class JdbcWithUI implements ActionListener {
    Frame f;
    Label l1, l2, l3;
    Button first, next, prev, last;
    TextField tf1, tf2, tf3;

    final String username = "root";
    final String password = "mahesh";
    final String dbUrl = "jdbc:mysql://localhost:3306/crud_db";
    ResultSet rs;
    JdbcWithUI() {
        f = new Frame();

        l1 = new Label("RollNo : ");
        l1.setBounds(10, 50, 100, 50);
        l2 = new Label("Name : ");
        l2.setBounds(10, 100, 100, 50);
        l3 = new Label("Stipend : ");
        l3.setBounds(10, 150, 100, 50);

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

        f.add(l1);
        f.add(tf1);
        f.add(l2);
        f.add(tf2);
        f.add(l3);
        f.add(tf3);

        f.add(first);
        f.add(next);
        f.add(prev);
        f.add(last);

        first.addActionListener(this);
        next.addActionListener(this);
        prev.addActionListener(this);
        last.addActionListener(this);

        f.setLayout(null);
        f.setVisible(true);
        f.setSize(500, 500);
        setData(dbUrl, username, password, "select * from students;");
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
    }

    void setData(String dbUrl, String username, String password,String query) {
        try {

            Connection con = DriverManager.getConnection(dbUrl, username, password);
            Statement stmt = con.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
            );
            this.rs = stmt.executeQuery(query);
            // setFirst(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void setLast(ResultSet rs){
        try {

            if (rs.last()) {
                tf1.setText(String.valueOf(rs.getInt(1)));
                tf2.setText(rs.getString(2));
                tf3.setText(String.valueOf(rs.getDouble(3)));
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
                tf3.setText(String.valueOf(rs.getDouble(3)));
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
                tf3.setText(String.valueOf(rs.getDouble(3)));
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
                tf3.setText(String.valueOf(rs.getDouble(3)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new JdbcWithUI();
    }

}
